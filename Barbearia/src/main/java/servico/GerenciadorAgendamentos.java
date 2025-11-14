package servico;

import java.time.LocalDateTime;
import java.util.List;
import utils.CRUDGenerico;

public class GerenciadorAgendamentos {

    private CRUDGenerico<Agendamento> crud;
    private Agenda agendaChecker; // usa a classe Agenda para verificar estação

    public GerenciadorAgendamentos() {
        crud = new CRUDGenerico<>("src/main/java/repositorio/agendamentos.json", Agendamento.class);
        agendaChecker = new Agenda();
    }

    // =====================================================================
    // ADICIONAR AGENDAMENTO
    // =====================================================================
    public boolean adicionar(Agendamento novo) {
        String conflito = verificarConflitos(novo);

        if (conflito != null) {
            System.out.println(conflito);
            return false;
        }

        crud.adicionar(novo);
        return true;
    }

    // =====================================================================
    // EDITAR AGENDAMENTO
    // =====================================================================
    public boolean editar(int id, Agendamento atualizado) {
        String conflito = verificarConflitos(atualizado);

        if (conflito != null) {
            System.out.println(conflito);
            return false;
        }

        Agendamento antigo = crud.buscarPorId(id);
        if (antigo != null) {
            crud.remover(antigo); // remove pelo objeto
            crud.adicionar(atualizado); // adiciona o atualizado
            return true;
        } else {
            System.out.println("Agendamento não encontrado para editar.");
            return false;
        }
    }

    // =====================================================================
    // REMOVER
    // =====================================================================
    public void remover(int id) {
        Agendamento ag = crud.buscarPorId(id);
        if (ag != null) {
            crud.remover(ag);
        }
    }

    // =====================================================================
    // BUSCAR
    // =====================================================================
    public Agendamento buscarPorId(int id) {
        return crud.buscarPorId(id);
    }

    // =====================================================================
    // LISTAR
    // =====================================================================
    public List<Agendamento> listar() {
        return crud.listar();
    }

    // =====================================================================
    // VERIFICAR CONFLITOS
    // =====================================================================
    private String verificarConflitos(Agendamento novo) {

        List<Agendamento> ags = crud.listar();
        LocalDateTime ini = novo.getHorarioInicio();
        LocalDateTime fim = novo.getHorarioFim();

        for (Agendamento existente : ags) {

            // -----------------------
            //   FUNCIONÁRIO OCUPADO
            // -----------------------
            if (novo.getFuncionario().getId() == existente.getFuncionario().getId()) {
                if (intervalosColidem(ini, fim, existente.getHorarioInicio(), existente.getHorarioFim())) {
                    return "⚠ Conflito: O funcionário **"
                            + novo.getFuncionario().getNome()
                            + "** já possui outro atendimento nesse horário!";
                }
            }

            // -----------------------
            //   ESTAÇÃO OCUPADA
            // -----------------------
            if (novo.getEstacaoEscolhida() != null) {
                boolean livre = agendaChecker.estacaoDisponivel(
                        ags,
                        novo.getEstacaoEscolhida(),
                        novo.getHorarioInicio(),
                        novo.getHorarioFim()
                );

                if (!livre) {
                    return "⚠ Conflito: A estação **"
                            + novo.getEstacaoEscolhida().name()
                            + "** já está ocupada nesse horário!";
                }
            }
        }

        return null;
    }

    // =====================================================================
    // MÉTODO AUXILIAR: verifica se intervalos colidem
    // =====================================================================
    private boolean intervalosColidem(LocalDateTime inicio1, LocalDateTime fim1,
                                      LocalDateTime inicio2, LocalDateTime fim2) {
        return inicio1.isBefore(fim2) && fim1.isAfter(inicio2);
    }
}
