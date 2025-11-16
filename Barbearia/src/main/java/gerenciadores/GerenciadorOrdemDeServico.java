package gerenciadores;

import modelo.*;
import utils.CRUDGenerico;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorOrdemDeServico {

    private final CRUDGenerico<OrdemDeServico> crudOS;
    private final GerenciadorPessoas gerenciadorPessoas;
    private final GerenciadorAgendamentos gerenciadorAgendamentos;
    private final GerenciadorVendas gerenciadorVendas;
    private final List<OrdemDeServico> ordens;

    public GerenciadorOrdemDeServico(GerenciadorPessoas gerenciadorPessoas,
                                     GerenciadorAgendamentos gerenciadorAgendamentos,
                                     GerenciadorVendas gerenciadorVendas) {
        this.gerenciadorPessoas = gerenciadorPessoas;
        this.gerenciadorAgendamentos = gerenciadorAgendamentos;
        this.gerenciadorVendas = gerenciadorVendas;
        this.crudOS = new CRUDGenerico<>("src/main/java/repositorio/OrdemDeServico.json", OrdemDeServico.class);
        this.ordens = crudOS.listar() != null ? crudOS.listar() : new ArrayList<>();
    }

    // Criar OS apenas com ID do cliente
    public OrdemDeServico criarOrdem(int idCliente) {
        Cliente cliente = gerenciadorPessoas.buscarClientePorId(idCliente);
        if (cliente == null) return null;

        int novoId = ordens.size() + 1;
        OrdemDeServico os = new OrdemDeServico(novoId, cliente); // sem funcionário

        // Vincula todos os agendamentos do cliente
        for (Agendamento ag : gerenciadorAgendamentos.listar()) {
            if (ag.getCliente().getId() == idCliente) {
                os.adicionarAgendamento(ag);
            }
        }

        // Vincula todas as vendas do cliente
        for (Venda v : gerenciadorVendas.listarVendas()) {
            if (v.getCliente().getId() == idCliente) {
                os.adicionarVenda(v);
            }
        }

        crudOS.adicionar(os);
        return os;
    }

    // Listar todas as OS
    public List<OrdemDeServico> listar() {
        return ordens;
    }

    // Buscar por ID
    public OrdemDeServico buscarPorId(int id) {
        return ordens.stream().filter(o -> o.getId() == id).findFirst().orElse(null);
    }

    // Remover OS
    public boolean remover(int id) {
        OrdemDeServico os = buscarPorId(id);
        if (os == null) return false;
        ordens.remove(os);
        return true;
    }

    // Salvar no JSON
    public void salvar() {
        crudOS.salvar();
    }

    // Adicionar Agendamento a OS
    public boolean adicionarAgendamentoNaOS(int idOS, int idAgendamento) {
        OrdemDeServico os = buscarPorId(idOS);
        Agendamento ag = gerenciadorAgendamentos.buscarPorId(idAgendamento);
        if (os == null || ag == null) return false;
        os.adicionarAgendamento(ag);
        return true;
    }

    // Adicionar Venda a OS
    public boolean adicionarVendaNaOS(int idOS, int idVenda) {
        OrdemDeServico os = buscarPorId(idOS);
        Venda v = gerenciadorVendas.buscarPorId(idVenda);
        if (os == null || v == null) return false;
        os.adicionarVenda(v);
        return true;
    }

}
