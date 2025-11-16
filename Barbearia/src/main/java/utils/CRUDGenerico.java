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
 * <p>Permite adicionar, remover, editar e listar objetos em memória.</p>
 * <p>Para persistir os dados em arquivo JSON, é necessário chamar o método salvar().</p>
 *
 * @param <T> Tipo de objeto que o CRUD irá gerenciar
 */
public class CRUDGenerico<T> {

    /** Caminho do arquivo JSON onde os dados serão salvos/carregados. */
    private final String caminhoArquivo;

    /** Classe do tipo T, usada para desserialização do JSON. */
    private final Class<T> clazz;

    /** Lista em memória com os objetos carregados. */
    private List<T> lista;

    /** ObjectMapper do Jackson usado para conversão entre objetos e JSON. */
    private final ObjectMapper mapper;

    /**
     * Construtor do CRUD genérico.
     *
     * @param caminhoArquivo Caminho do arquivo JSON
     * @param clazz Classe do objeto que será gerenciado
     */
    public CRUDGenerico(String caminhoArquivo, Class<T> clazz) {
        this.caminhoArquivo = caminhoArquivo;
        this.clazz = clazz;

        this.mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        this.lista = carregar();
    }

    /**
     * Carrega os objetos do arquivo JSON para a lista em memória.
     *
     * <p>Se o arquivo não existir, retorna uma lista vazia.</p>
     * <p>Se houver erro na leitura do JSON, imprime o erro e retorna lista vazia.</p>
     *
     * @return Lista de objetos carregados do arquivo JSON
     */
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

    /** Salva a lista de objetos em arquivo JSON. */
    public void salvar() {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(caminhoArquivo), lista);

        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo JSON: " + caminhoArquivo);
            e.printStackTrace();
        }
    }

    /** Gera o próximo ID disponível para um novo objeto. */
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

    /**
     * Adiciona um objeto à lista e atribui um novo ID automaticamente.
     *
     * @param obj Objeto a ser adicionado
     */
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

    /**
     * Remove um objeto da lista.
     *
     * @param obj Objeto a ser removido
     */
    public void remover(T obj) {
        lista.remove(obj);
    }

    /**
     * Substitui o objeto na posição indicada.
     *
     * @param indice Posição do objeto a ser substituído
     * @param obj Novo objeto que irá substituir
     */
    public void editar(int indice, T obj) {
        if (indice >= 0 && indice < lista.size()) {
            lista.set(indice, obj);
        }
    }

    /**
     * Retorna todos os objetos da lista em memória.
     *
     * @return Lista de objetos
     */
    public List<T> listar() {
        return new ArrayList<>(lista);
    }

    /**
     * Busca um objeto pelo seu ID.
     *
     * @param id ID do objeto
     * @return Objeto encontrado ou null se não existir
     */
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
