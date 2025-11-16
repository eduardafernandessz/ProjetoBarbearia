package utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD genérico para qualquer tipo de objeto.
 *
 * Métodos alteram somente em memória.
 * Para persistir: chamar salvar().
 */
public class CRUDGenerico<T> {

    private final String caminhoArquivo;
    private final Class<T> clazz;
    private List<T> lista;

    private final ObjectMapper mapper;

    public CRUDGenerico(String caminhoArquivo, Class<T> clazz) {
        this.caminhoArquivo = caminhoArquivo;
        this.clazz = clazz;

        // ⚠ IMPORTANTE → configurar antes de carregar.
        this.mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        this.lista = carregar();
    }

    private List<T> carregar() {
        File arquivo = new File(caminhoArquivo);
        if (!arquivo.exists()) return new ArrayList<>();

        try {
            CollectionType tipo = mapper.getTypeFactory()
                    .constructCollectionType(List.class, clazz);
            return mapper.readValue(arquivo, tipo);

        } catch (IOException e) {
            System.out.println("Erro ao carregar arquivo JSON: " + caminhoArquivo);
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void salvar() {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(caminhoArquivo), lista);

        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo JSON: " + caminhoArquivo);
            e.printStackTrace();
        }
    }

    private int gerarId() {
        int maiorId = 0;
        for (T obj : lista) {
            try {
                int idAtual = (int) obj.getClass().getMethod("getId").invoke(obj);
                if (idAtual > maiorId) maiorId = idAtual;

            } catch (Exception e) {
                System.out.println("Classe sem getId(): " + clazz.getSimpleName());
                e.printStackTrace();
            }
        }
        return maiorId + 1;
    }

    public void adicionar(T obj) {
        int novoId = gerarId();
        try {
            obj.getClass().getMethod("setId", int.class).invoke(obj, novoId);
        } catch (Exception e) {
            System.out.println("Classe sem setId(): " + clazz.getSimpleName());
            e.printStackTrace();
        }
        lista.add(obj);
    }

    public void remover(T obj) {
        lista.remove(obj);
    }

    public void editar(int indice, T obj) {
        if (indice >= 0 && indice < lista.size()) {
            lista.set(indice, obj);
        }
    }

    public List<T> listar() {
        return new ArrayList<>(lista);
    }

    public T buscarPorId(int id) {
        for (T obj : lista) {
            try {
                int idAtual = (int) obj.getClass().getMethod("getId").invoke(obj);
                if (idAtual == id) return obj;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
