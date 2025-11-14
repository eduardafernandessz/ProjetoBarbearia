package servico;

import java.time.LocalDateTime;
import java.util.List;

public class Agenda {

    /**
     * Verifica se a estação está livre no intervalo informado.
     * A diferença agora é:
     *
     * ✔ cada agendamento possui UMA estaçãoEscolhida
     * ✔ cada serviço possui várias estacoesPossiveis
     * ✔ aqui verificamos apenas se a estaçãoEscolhida de outro agendamento é a mesma estação
     */
    public boolean estacaoDisponivel(List<Agendamento> agendamentos, Estacao estacao,
                                     LocalDateTime inicio, LocalDateTime fim) {

        for (Agendamento ag : agendamentos) {

            // Se esse agendamento não usa estação (não deveria acontecer), ignora
            if (ag.getEstacaoEscolhida() == null) continue;

            // Verifica se o agendamento atual usa a estação que estamos tentando usar
            if (ag.getEstacaoEscolhida().getId() != estacao.getId()) {
                continue; // não usa a mesma estação → não conflita
            }

            // Verifica sobreposição de horários
            if (intervalosColidem(inicio, fim, ag.getHorarioInicio(), ag.getHorarioFim())) {
                return false; // estação ocupada
            }
        }

        return true; // estação livre
    }

    /**
     * Retorna true se os intervalos colidem (ou seja, estão ocupando o mesmo horário)
     */
    private boolean intervalosColidem(LocalDateTime inicio1, LocalDateTime fim1,
                                      LocalDateTime inicio2, LocalDateTime fim2) {

        return inicio1.isBefore(fim2) && fim1.isAfter(inicio2);
    }
}
