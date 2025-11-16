package gerenciadores;

import java.time.LocalDateTime;
import java.util.List;
import modelo.Agendamento;
import modelo.Estacao;
import modelo.Estacao;

public class Agenda {

    /**
     * Verifica se a estação está livre no intervalo informado.
     *
     * Cada agendamento possui UMA estaçãoEscolhida (enum Estacao)
     * Cada serviço possui várias estacoesPossiveis
     * Aqui verificamos apenas se a estaçãoEscolhida de outro agendamento é a mesma estação
     *
     * @param agendamentos lista de agendamentos existentes
     * @param estacao estação que deseja verificar
     * @param inicio horário de início
     * @param fim horário de fim
     * @return true se a estação estiver livre, false se estiver ocupada
     */
    public boolean estacaoDisponivel(List<Agendamento> agendamentos, Estacao estacao,
                                     LocalDateTime inicio, LocalDateTime fim) {

        for (Agendamento ag : agendamentos) {

            // Se esse agendamento não tem estação, ignora
            if (ag.getEstacaoEscolhida() == null) continue;

            // Se não é a mesma estação, não há conflito
            if (ag.getEstacaoEscolhida() != estacao) continue;

            // Verifica sobreposição de horários
            if (intervalosColidem(inicio, fim, ag.getHorarioInicio(), ag.getHorarioFim())) {
                return false; // estação ocupada
            }
        }

        return true; // estação livre
    }

    /**
     * Verifica se dois intervalos de tempo se sobrepõem.
     * 
     * <p>Dois intervalos [inicio1, fim1) e [inicio2, fim2) colidem
     * se existe pelo menos um instante em que ambos estão ativos ao mesmo tempo.</p>
     *
     * @param inicio1 o início do primeiro intervalo (inclusive)
     * @param fim1 o fim do primeiro intervalo (exclusive)
     * @param inicio2 o início do segundo intervalo (inclusive)
     * @param fim2 o fim do segundo intervalo (exclusive)
     * @return {@code true} se os intervalos se sobrepõem, {@code false} caso contrário
     */
    private boolean intervalosColidem(LocalDateTime inicio1, LocalDateTime fim1,
                                      LocalDateTime inicio2, LocalDateTime fim2) {
        return inicio1.isBefore(fim2) && fim1.isAfter(inicio2);
    }
}
