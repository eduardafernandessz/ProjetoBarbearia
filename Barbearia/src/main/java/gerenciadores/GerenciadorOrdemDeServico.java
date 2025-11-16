package gerenciadores;

import modelo.*;
import utils.CRUDGenerico;

import java.util.ArrayList;
import java.util.List;

    /**
     * Gerencia as Ordens de Serviço (OS) da barbearia.
     * <p>
     * Permite criar, listar, buscar, remover e salvar ordens de serviço,
     * além de adicionar agendamentos e vendas a uma OS específica.
     * </p>
     */
public class GerenciadorOrdemDeServico {

    /** CRUD genérico para persistência das Ordens de Serviço em JSON. */
    private final CRUDGenerico<OrdemDeServico> crudOS;

    /** Gerenciador de pessoas (clientes e funcionários). */
    private final GerenciadorPessoas gerenciadorPessoas;

    /** Gerenciador de agendamentos. */
    private final GerenciadorAgendamentos gerenciadorAgendamentos;

    /** Gerenciador de vendas. */
    private final GerenciadorVendas gerenciadorVendas;

    /** Lista de todas as Ordens de Serviço. */
    private final List<OrdemDeServico> ordens;

    /**
     * Construtor do gerenciador de Ordens de Serviço.
     *
     * @param gerenciadorPessoas Gerenciador de pessoas.
     * @param gerenciadorAgendamentos Gerenciador de agendamentos.
     * @param gerenciadorVendas Gerenciador de vendas.
     */
    public GerenciadorOrdemDeServico(GerenciadorPessoas gerenciadorPessoas,
                                     GerenciadorAgendamentos gerenciadorAgendamentos,
                                     GerenciadorVendas gerenciadorVendas) {
        this.gerenciadorPessoas = gerenciadorPessoas;
        this.gerenciadorAgendamentos = gerenciadorAgendamentos;
        this.gerenciadorVendas = gerenciadorVendas;
        this.crudOS = new CRUDGenerico<>("src/main/java/repositorio/OrdemDeServico.json", OrdemDeServico.class);
        this.ordens = crudOS.listar() != null ? crudOS.listar() : new ArrayList<>();
    }

    /**
     * Cria uma nova Ordem de Serviço para um cliente.
     * <p>
     * A OS será vinculada automaticamente aos agendamentos e vendas do cliente.
     * </p>
     *
     * @param idCliente Identificador do cliente.
     * @return Ordem de Serviço criada ou null se o cliente não existir.
     */
    public OrdemDeServico criarOrdem(int idCliente) {
        Cliente cliente = gerenciadorPessoas.buscarClientePorId(idCliente);
        if (cliente == null) return null;

        int novoId = ordens.size() + 1;
        OrdemDeServico os = new OrdemDeServico(novoId, cliente);

        // Vincula agendamentos do cliente
        for (Agendamento ag : gerenciadorAgendamentos.listar()) {
            if (ag.getCliente().getId() == idCliente) {
                os.adicionarAgendamento(ag);
            }
        }

        // Vincula vendas do cliente
        for (Venda v : gerenciadorVendas.listarVendas()) {
            if (v.getCliente().getId() == idCliente) {
                os.adicionarVenda(v);
            }
        }

        crudOS.adicionar(os);
        return os;
    }

    /**
     * Retorna todas as Ordens de Serviço cadastradas.
     *
     * @return Lista de Ordens de Serviço.
     */
    public List<OrdemDeServico> listar() {
        return ordens;
    }

    /**
     * Busca uma Ordem de Serviço pelo seu identificador.
     *
     * @param id Identificador da OS.
     * @return Ordem de Serviço encontrada ou null se não existir.
     */
    public OrdemDeServico buscarPorId(int id) {
        return ordens.stream().filter(o -> o.getId() == id).findFirst().orElse(null);
    }

    /**
     * Remove uma Ordem de Serviço pelo seu identificador.
     *
     * @param id Identificador da OS a ser removida.
     * @return true se a OS foi removida; false se não foi encontrada.
     */
    public boolean remover(int id) {
        OrdemDeServico os = buscarPorId(id);
        if (os == null) return false;
        ordens.remove(os);
        return true;
    }

    /**
     * Salva todas as Ordens de Serviço no arquivo JSON.
     */
    public void salvar() {
        crudOS.salvar();
    }

    /**
     * Adiciona um agendamento a uma Ordem de Serviço existente.
     *
     * @param idOS Identificador da OS.
     * @param idAgendamento Identificador do agendamento.
     * @return true se o agendamento foi adicionado; false se OS ou agendamento não existir.
     */
    public boolean adicionarAgendamentoNaOS(int idOS, int idAgendamento) {
        OrdemDeServico os = buscarPorId(idOS);
        Agendamento ag = gerenciadorAgendamentos.buscarPorId(idAgendamento);
        if (os == null || ag == null) return false;
        os.adicionarAgendamento(ag);
        return true;
    }

    /**
     * Adiciona uma venda a uma Ordem de Serviço existente.
     *
     * @param idOS Identificador da OS.
     * @param idVenda Identificador da venda.
     * @return true se a venda foi adicionada; false se OS ou venda não existir.
     */
    public boolean adicionarVendaNaOS(int idOS, int idVenda) {
        OrdemDeServico os = buscarPorId(idOS);
        Venda v = gerenciadorVendas.buscarPorId(idVenda);
        if (os == null || v == null) return false;
        os.adicionarVenda(v);
        return true;
    }
}
