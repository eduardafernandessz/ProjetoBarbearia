package gerenciadores;

import modelo.Estacao;
import modelo.Servico;
import utils.CRUDGenerico;

import java.util.List;

/**
 * Gerencia os serviços da barbearia.
 * <p>
 * Permite adicionar, editar, remover, listar e buscar serviços,
 * utilizando persistência em JSON através do CRUD genérico.
 * </p>
 */
public class GerenciadorServicos {

    /** CRUD genérico para manipulação de serviços no arquivo JSON. */
    private final CRUDGenerico<Servico> crud;

    /**
     * Construtor do gerenciador de serviços.
     * <p>
     * Inicializa o CRUD genérico apontando para o arquivo de serviços.
     * </p>
     */
    public GerenciadorServicos() {
        crud = new CRUDGenerico<>("src/main/java/repositorio/servicos.json", Servico.class);
    }

    /**
     * Retorna todos os serviços cadastrados.
     *
     * @return Lista de serviços.
     */
    public List<Servico> listar() {
        return crud.listar();
    }

    /**
     * Adiciona um novo serviço.
     *
     * @param s Serviço a ser adicionado.
     */
    public void adicionar(Servico s) {
        crud.adicionar(s);
    }

    /**
     * Busca um serviço pelo seu identificador.
     *
     * @param id Identificador do serviço.
     * @return Serviço encontrado ou null se não existir.
     */
    public Servico buscarPorId(int id) {
        return crud.buscarPorId(id);
    }

    /**
     * Edita um serviço existente.
     * <p>
     * Os parâmetros podem ser nulos ou negativos para manter o valor atual.
     * </p>
     *
     * @param id ID do serviço a ser editado.
     * @param novoNome Novo nome (null para manter).
     * @param novoPreco Novo preço (negativo ou null para manter).
     * @param novaDuracao Nova duração em minutos (negativa ou null para manter).
     * @param novasEstacoes Lista de estações possíveis (null para manter).
     * @return true se editado; false se o serviço não foi encontrado.
     */
    public boolean editar(int id, String novoNome, Double novoPreco, Integer novaDuracao, List<Estacao> novasEstacoes) {
        Servico s = crud.buscarPorId(id);
        if (s == null) return false;

        if (novoNome != null && !novoNome.isEmpty()) s.setNome(novoNome);
        if (novoPreco != null && novoPreco >= 0) s.setPreco(novoPreco);
        if (novaDuracao != null && novaDuracao >= 0) s.setDuracaoMinutos(novaDuracao);
        if (novasEstacoes != null) s.setEstacoesPossiveis(novasEstacoes);

        return true;
    }

    /**
     * Remove um serviço pelo seu identificador.
     *
     * @param id Identificador do serviço a ser removido.
     * @return true se o serviço foi removido; false se não foi encontrado.
     */
    public boolean remover(int id) {
        Servico s = crud.buscarPorId(id);
        if (s != null) {
            crud.remover(s);
            return true;
        }
        return false;
    }

    /**
     * Salva os serviços no arquivo JSON.
     */
    public void salvar() {
        crud.salvar();
    }
}
