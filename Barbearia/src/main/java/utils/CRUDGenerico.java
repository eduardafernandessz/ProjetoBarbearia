package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

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
 * @param <T> Tipo de objeto que será gerenciado (Cliente, Funcionario, Gerente...)
 */
public class CRUDGenerico<T> {

    // Caminho do arquivo JSON
    private final String caminhoArquivo;

    // Classe do tipo de objeto que vamos gerenciar
    private final Class<T> clazz;

    // Lista que vai guardar os objetos em memória
    private List<T> lista;

    // Jackson ObjectMapper para converter objetos ↔ JSON
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Construtor do CRUD genérico
     * @param caminhoArquivo caminho do arquivo JSON
     * @param clazz classe do tipo de objeto
     */
    public CRUDGenerico(String caminhoArquivo, Class<T> clazz) {
        this.caminhoArquivo = caminhoArquivo;
        this.clazz = clazz;
        this.lista = carregar(); // carrega os dados do arquivo para memória
    }

    /**
     * Carrega os dados do JSON para a lista de objetos
     * Se o arquivo não existir, retorna lista vazia
     */
    private List<T> carregar() {
        File arquivo = new File(caminhoArquivo);
        if (!arquivo.exists()) return new ArrayList<>(); // lista vazia se arquivo não existe

        try {
            CollectionType tipoLista = mapper.getTypeFactory()
                    .constructCollectionType(List.class, clazz);

            return mapper.readValue(arquivo, tipoLista);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>(); // em caso de erro, lista vazia
        }
    }

    /**
     * Salva a lista de objetos no arquivo JSON
     */
    private void salvar() {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(caminhoArquivo), lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gera um ID único pegando o maior ID da lista e somando 1
     */
    private int gerarId() {
        int maiorId = 0;
        for (T obj : lista) {
            try {
                // Chama getId() do objeto para pegar o valor atual
                int idAtual = (int) obj.getClass().getMethod("getId").invoke(obj);
                if (idAtual > maiorId) {
                    maiorId = idAtual;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return maiorId + 1;
    }

    /**
     * Adiciona um objeto à lista, gera ID automático e salva
     * @param obj objeto a adicionar
     */
    public void adicionar(T obj) {
        int novoId = gerarId(); // pega o próximo ID disponível
        try {
            // Define o ID do objeto
            obj.getClass().getMethod("setId", int.class).invoke(obj, novoId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        lista.add(obj); // adiciona na lista
        salvar();       // salva no JSON
    }

    /**
     * Remove um objeto da lista e salva
     * @param obj objeto a remover
     */
    public void remover(T obj) {
        lista.remove(obj);
        salvar();
    }

    /**
     * Lista todos os objetos
     * @return nova lista (cópia) para evitar alterar lista interna
     */
    public List<T> listar() {
        return new ArrayList<>(lista);
    }

    /**
     * Edita um objeto na lista substituindo pelo índice e salva
     * @param indice índice do objeto a editar
     * @param obj novo objeto
     */
    public void editar(int indice, T obj) {
        if (indice >= 0 && indice < lista.size()) {
            lista.set(indice, obj);
            salvar();
        }
    }

    /**
     * Busca um objeto pelo ID
     * @param id ID do objeto a buscar
     * @return objeto encontrado ou null
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
