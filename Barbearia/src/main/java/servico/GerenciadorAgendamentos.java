package servico;

import java.time.LocalDateTime;
import java.util.List;
import utils.CRUDGenerico;

public class GerenciadorAgendamentos {

    private CRUDGenerico<Agendamento> crud;

    public GerenciadorAgendamentos() {
        crud = new CRUDGenerico<>("src/main/java/repositorio/agendamentos.json", Agendamento.class);
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
    // EDITAR
    // =====================================================================
    public boolean editar(int id, Agendamento atualizado) {

        String conflito = verificarConflitos(atualizado);

        if (conflito != null) {
            System.out.println(conflito);
            return false;
        }

        // busca o agendamento antigo
        Agendamento antigo = crud.buscarPorId(id);
        if (antigo != null) {
            crud.remover(antigo); // remove pelo objeto
            crud.adicionar(atualizado); // adiciona o atualizado
            return true;
        } else {
            System.out.println(" Agendamento não encontrado para editar.");
            return false;
        }
    }


    // =====================================================================
    // REMOVER
    // =====================================================================
    public void remover(int id) {
    Agendamento ag = crud.buscarPorId(id); // pega o objeto
    if (ag != null) {
        crud.remover(ag); // passa o objeto, não o ID
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

            boolean horarioBate =
                    ini.isBefore(existente.getHorarioFim()) &&
                    fim.isAfter(existente.getHorarioInicio());

            if (!horarioBate) continue;

            // -----------------------
            //   FUNCIONÁRIO OCUPADO
            // -----------------------
            if (novo.getFuncionario().getId() == existente.getFuncionario().getId()) {
                return "⚠ Conflito: O funcionário **"
                        + novo.getFuncionario().getNome()
                        + "** já possui outro atendimento nesse horário!";
            }

            // -----------------------
            //   ESTAÇÃO OCUPADA
            // -----------------------
            if (novo.getEstacaoEscolhida() != null &&
                existente.getEstacaoEscolhida() != null &&
                novo.getEstacaoEscolhida().getId() == existente.getEstacaoEscolhida().getId()) {

                return " Conflito: A estação **"
                        + novo.getEstacaoEscolhida().getNome()
                        + "** já está ocupada nesse horário!";
            }
        }

        return null;
    }
}
