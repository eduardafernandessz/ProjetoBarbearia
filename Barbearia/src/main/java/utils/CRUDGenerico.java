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
 * Agora, os métodos adicionar, remover e editar apenas alteram a lista em memória.
 * Para persistir no JSON, é necessário chamar manualmente o método salvar().
 *
 * @param <T> Tipo de objeto que será gerenciado (Cliente, Funcionario, Agendamento...)
 */
public class CRUDGenerico<T> {

    private final String caminhoArquivo;
    private final Class<T> clazz;
    private List<T> lista;

    private final ObjectMapper mapper = new ObjectMapper();

    public CRUDGenerico(String caminhoArquivo, Class<T> clazz) {
        this.caminhoArquivo = caminhoArquivo;
        this.clazz = clazz;
        this.lista = carregar();

        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public List<T> carregar() {
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

    /**
     * Salva a lista atual em arquivo JSON
     */
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

    /**
     * Apenas adiciona na lista em memória
     */
    public void adicionar(T obj) {
        int novoId = gerarId();
        try {
            obj.getClass().getMethod("setId", int.class).invoke(obj, novoId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        lista.add(obj);
    }

    /**
     * Apenas remove da lista em memória
     */
    public void remover(T obj) {
        lista.remove(obj);
    }

    /**
     * Apenas substitui o objeto na lista em memória
     */
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
