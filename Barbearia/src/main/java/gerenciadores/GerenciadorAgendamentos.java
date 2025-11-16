package gerenciadores;

import modelo.Agendamento;
import modelo.Estacao;
import utils.CRUDGenerico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

    /**
     * Gerencia os agendamentos da barbearia.
     * <p>
     * Permite adicionar, editar, remover, listar e verificar conflitos de agendamentos.
     * Também integra a verificação de disponibilidade das estações.
     * </p>
     */
public class GerenciadorAgendamentos {

    /** Lista de agendamentos atualmente cadastrados. */
    private final List<Agendamento> lista;

    /** CRUD genérico para persistência dos agendamentos em JSON. */
    private final CRUDGenerico<Agendamento> crud;

    /** Utilitário para verificar disponibilidade das estações. */
    private final Agenda agendaChecker;

    /**
     * Construtor do gerenciador.
     * <p>
     * Inicializa a lista de agendamentos carregando do JSON
     * e prepara o utilitário de verificação de agenda.
     * </p>
     */
    public GerenciadorAgendamentos() {
        crud = new CRUDGenerico<>("src/main/java/repositorio/agendamentos.json", Agendamento.class);
        lista = crud.listar(); // carrega do JSON
        agendaChecker = new Agenda();
    }

    /**
     * Adiciona um novo agendamento à lista.
     * <p>
     * Antes de adicionar, verifica se não há conflitos com outros agendamentos.
     * </p>
     *
     * @param novo Agendamento a ser adicionado.
     * @return true se o agendamento foi adicionado sem conflitos; false caso contrário.
     */
    public boolean adicionar(Agendamento novo) {
        return verificarConflitos(novo) == null && lista.add(novo);
    }

    /**
     * Edita um agendamento existente.
     * <p>
     * Substitui o agendamento antigo pelo atualizado se não houver conflitos.
     * </p>
     *
     * @param id Identificador do agendamento a ser editado.
     * @param atualizado Novo agendamento com dados atualizados.
     * @return true se a edição foi realizada com sucesso; false caso contrário.
     */
    public boolean editar(int id, Agendamento atualizado) {
        Agendamento antigo = buscarPorId(id);
        if (antigo == null || verificarConflitos(atualizado) != null) {
            return false;
        }
        lista.remove(antigo);
        lista.add(atualizado);
        return true;
    }

    /**
     * Remove um agendamento da lista.
     *
     * @param id Identificador do agendamento a ser removido.
     * @return true se o agendamento foi removido; false se não foi encontrado.
     */
    public boolean remover(int id) {
        Agendamento ag = buscarPorId(id);
        if (ag != null) {
            lista.remove(ag);
            return true;
        }
        return false;
    }

    /**
     * Busca um agendamento pelo seu identificador.
     *
     * @param id Identificador do agendamento.
     * @return Agendamento encontrado ou null se não existir.
     */
    public Agendamento buscarPorId(int id) {
        for (Agendamento ag : lista) {
            if (ag.getId() == id) return ag;
        }
        return null;
    }

    /**
     * Retorna uma cópia da lista de agendamentos.
     *
     * @return Lista de agendamentos.
     */
    public List<Agendamento> listar() {
        return new ArrayList<>(lista);
    }

    /**
     * Salva os agendamentos no arquivo JSON.
     */
    public void salvar() {
        crud.salvar();
    }

    /**
     * Verifica se o novo agendamento possui conflitos com outros agendamentos.
     * <p>
     * Checa conflitos de horário do funcionário e da estação.
     * </p>
     *
     * @param novo Agendamento a ser verificado.
     * @return Mensagem de conflito caso exista; null se não houver conflito.
     */
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

    /**
     * Verifica se dois intervalos de tempo colidem.
     *
     * @param inicio1 Início do primeiro intervalo.
     * @param fim1 Fim do primeiro intervalo.
     * @param inicio2 Início do segundo intervalo.
     * @param fim2 Fim do segundo intervalo.
     * @return true se os intervalos colidem; false caso contrário.
     */
    private boolean intervalosColidem(LocalDateTime inicio1, LocalDateTime fim1,
                                      LocalDateTime inicio2, LocalDateTime fim2) {
        return inicio1.isBefore(fim2) && fim1.isAfter(inicio2);
    }
}
