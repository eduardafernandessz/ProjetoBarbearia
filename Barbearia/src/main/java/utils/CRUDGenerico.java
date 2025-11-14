package utils;

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
 * Funciona assim:
 * - Abre o arquivo JSON e transforma em lista de objetos
 * - Permite adicionar, remover, listar, editar, buscar
 * - Gera ID automaticamente pegando o maior existente
 * - Salva de volta no arquivo JSON
 *
 * OBS: Todas as classes devem ter os métodos getId() e setId(int)
 *
 * @param <T> Tipo de objeto que será gerenciado (Cliente, Funcionario, Agendamento...)
 */
public class CRUDGenerico<T> {

    private final String caminhoArquivo;
    private final Class<T> clazz;
    private List<T> lista;

    // Jackson ObjectMapper para converter objetos ↔ JSON
    private final ObjectMapper mapper = new ObjectMapper();

    public CRUDGenerico(String caminhoArquivo, Class<T> clazz) {
        this.caminhoArquivo = caminhoArquivo;
        this.clazz = clazz;
        this.lista = carregar();

        // Configura Jackson para lidar com LocalDateTime e JSON legível
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    private List<T> carregar() {
        File arquivo = new File(caminhoArquivo);
        if (!arquivo.exists()) return new ArrayList<>();
        try {
            CollectionType tipoLista = mapper.getTypeFactory()
                    .constructCollectionType(List.class, clazz);
            return mapper.readValue(arquivo, tipoLista);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void salvar() {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(caminhoArquivo), lista);
        } catch (IOException e) {
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
            e.printStackTrace();
        }
        lista.add(obj);
        salvar();
    }

    public void remover(T obj) {
        lista.remove(obj);
        salvar();
    }

    public List<T> listar() {
        return new ArrayList<>(lista);
    }

    public void editar(int indice, T obj) {
        if (indice >= 0 && indice < lista.size()) {
            lista.set(indice, obj);
            salvar();
        }
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
