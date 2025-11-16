package gerenciadores;

import modelo.Estacao;
import modelo.Servico;
import utils.CRUDGenerico;

import java.util.List;

public class GerenciadorServicos {

    private final CRUDGenerico<Servico> crud;

    public GerenciadorServicos() {
        crud = new CRUDGenerico<>("src/main/java/repositorio/servicos.json", Servico.class);
    }

    // ==============================
    // LISTAR
    // ==============================
    public List<Servico> listar() {
        return crud.listar();
    }

    // ==============================
    // ADICIONAR
    // ==============================
    public void adicionar(Servico s) {
        crud.adicionar(s);
    }

    // ==============================
    // BUSCAR POR ID
    // ==============================
    public Servico buscarPorId(int id) {
        return crud.buscarPorId(id);
    }

    // ==============================
    // EDITAR
    // ==============================
    /**
     * Edita um serviço.
     * Os parâmetros podem ser nulos ou negativos para manter o valor atual.
     * @param id ID do serviço a ser editado
     * @param novoNome Novo nome (null para manter)
     * @param novoPreco Novo preço (negativo para manter)
     * @param novaDuracao Nova duração em minutos (negativa para manter)
     * @param novasEstacoes Lista de estações possíveis (null para manter)
     * @return true se editado, false se serviço não encontrado
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

    // ==============================
    // REMOVER
    // ==============================
    public boolean remover(int id) {
        Servico s = crud.buscarPorId(id);
        if (s != null) {
            crud.remover(s);
            return true;
        }
        return false;
    }

    // ==============================
    // SALVAR NO JSON
    // ==============================
    public void salvar() {
        crud.salvar();
    }
}
