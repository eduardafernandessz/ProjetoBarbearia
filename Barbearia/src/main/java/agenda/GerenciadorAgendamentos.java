package agenda;

import agenda.Agendamento;
import agenda.Agenda;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import utils.CRUDGenerico;

public class GerenciadorAgendamentos {

    private List<Agendamento> lista;
    private CRUDGenerico<Agendamento> crud;
    private Agenda agendaChecker;

    public GerenciadorAgendamentos() {
        crud = new CRUDGenerico<>("src/main/java/repositorio/agendamentos.json", Agendamento.class);
        lista = crud.listar(); // carrega do JSON
        agendaChecker = new Agenda();
    }

    // ===================== ADICIONAR =====================
    public boolean adicionar(Agendamento novo) {
        String conflito = verificarConflitos(novo);
        if (conflito != null) {
            System.out.println(conflito);
            return false;
        }
        lista.add(novo);
        return true;
    }

    // ===================== EDITAR =====================
    public boolean editar(int id, Agendamento atualizado) {
        String conflito = verificarConflitos(atualizado);
        if (conflito != null) {
            System.out.println(conflito);
            return false;
        }
        Agendamento antigo = buscarPorId(id);
        if (antigo != null) {
            lista.remove(antigo);
            lista.add(atualizado);
            return true;
        }
        System.out.println("Agendamento não encontrado para editar.");
        return false;
    }

    // ===================== REMOVER =====================
    public void remover(int id) {
        Agendamento ag = buscarPorId(id);
        if (ag != null) lista.remove(ag);
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
        System.out.println(" Alterações salvas no JSON!");
    }

    // ===================== CONFLITOS =====================
    private String verificarConflitos(Agendamento novo) {
        LocalDateTime ini = novo.getHorarioInicio();
        LocalDateTime fim = novo.getHorarioFim();

        for (Agendamento existente : lista) {
            if (novo.getFuncionario().getId() == existente.getFuncionario().getId()) {
                if (intervalosColidem(ini, fim, existente.getHorarioInicio(), existente.getHorarioFim())) {
                    return " Conflito: O funcionário " + novo.getFuncionario().getNome() + " já possui outro atendimento nesse horário!";
                }
            }
            if (novo.getEstacaoEscolhida() != null) {
                boolean livre = agendaChecker.estacaoDisponivel(lista, novo.getEstacaoEscolhida(), ini, fim);
                if (!livre) return " Conflito: A estação " + novo.getEstacaoEscolhida().name() + " já está ocupada nesse horário!";
            }
        }
        return null;
    }

    private boolean intervalosColidem(LocalDateTime inicio1, LocalDateTime fim1,
                                      LocalDateTime inicio2, LocalDateTime fim2) {
        return inicio1.isBefore(fim2) && fim1.isAfter(inicio2);
    }
}
