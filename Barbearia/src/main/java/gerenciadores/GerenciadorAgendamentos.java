package gerenciadores;

import modelo.Agendamento;
import modelo.Estacao;
import utils.CRUDGenerico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorAgendamentos {

    private final List<Agendamento> lista;
    private final CRUDGenerico<Agendamento> crud;
    private final Agenda agendaChecker;

    public GerenciadorAgendamentos() {
        crud = new CRUDGenerico<>("src/main/java/repositorio/agendamentos.json", Agendamento.class);
        lista = crud.listar(); // carrega do JSON
        agendaChecker = new Agenda();
    }

    // ===================== ADICIONAR =====================
    public boolean adicionar(Agendamento novo) {
        return verificarConflitos(novo) == null && lista.add(novo);
    }

    // ===================== EDITAR =====================
    public boolean editar(int id, Agendamento atualizado) {
        Agendamento antigo = buscarPorId(id);
        if (antigo == null || verificarConflitos(atualizado) != null) {
            return false;
        }
        lista.remove(antigo);
        lista.add(atualizado);
        return true;
    }

    // ===================== REMOVER =====================
    public boolean remover(int id) {
        Agendamento ag = buscarPorId(id);
        if (ag != null) {
            lista.remove(ag);
            return true;
        }
        return false;
    }

    // ===================== BUSCAR =====================
    public Agendamento buscarPorId(int id) {
        for (Agendamento ag : lista) {
            if (ag.getId() == id) return ag;
        }
        return null;
    }

    // ===================== LISTAR =====================
    public List<Agendamento> listar() {
        return new ArrayList<>(lista);
    }

    // ===================== SALVAR =====================
    public void salvar() {
        crud.salvar();
    }

    // ===================== CONFLITOS =====================
    public String verificarConflitos(Agendamento novo) {
        LocalDateTime ini = novo.getHorarioInicio();
        LocalDateTime fim = novo.getHorarioFim();

        for (Agendamento existente : lista) {
            // Checa conflito de funcionário
            if (novo.getFuncionario().getId() == existente.getFuncionario().getId() &&
                intervalosColidem(ini, fim, existente.getHorarioInicio(), existente.getHorarioFim())) {
                return "O funcionário " + novo.getFuncionario().getNome() + " já possui outro atendimento nesse horário!";
            }

            // Checa conflito de estação
            if (novo.getEstacaoEscolhida() != null) {
                boolean livre = agendaChecker.estacaoDisponivel(lista, novo.getEstacaoEscolhida(), ini, fim);
                if (!livre) {
                    return "A estação " + novo.getEstacaoEscolhida().name() + " já está ocupada nesse horário!";
                }
            }
        }
        return null; // sem conflitos
    }

    private boolean intervalosColidem(LocalDateTime inicio1, LocalDateTime fim1,
                                      LocalDateTime inicio2, LocalDateTime fim2) {
        return inicio1.isBefore(fim2) && fim1.isAfter(inicio2);
    }
}
