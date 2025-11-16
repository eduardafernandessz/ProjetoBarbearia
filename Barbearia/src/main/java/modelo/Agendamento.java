package modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um agendamento em uma barbearia ou salão.
 * 
 * <p>Cada agendamento contém informações sobre o cliente, funcionário, serviços, 
 * horário de início e fim, preço total e a estação escolhida.</p>
 */
public class Agendamento {

    /** Identificador único do agendamento */
    private int id;

    /** Cliente associado ao agendamento */
    private Cliente cliente;

    /** Funcionário responsável pelo atendimento */
    private Funcionario funcionario;

    /** Lista de serviços incluídos no agendamento */
    private List<Servico> servicos; 

    /** Horário de início do atendimento */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime horarioInicio;

    /** Horário de término do atendimento */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime horarioFim;

    /** Preço total do agendamento, calculado a partir dos serviços */
    private double precoTotal;

    /** Estação onde o atendimento ocorrerá */
    private Estacao estacaoEscolhida;

    /**
     * Construtor completo do agendamento.
     * Calcula automaticamente o preço total e o horário final com base nos serviços.
     *
     * @param id Identificador do agendamento
     * @param cliente Cliente do agendamento
     * @param funcionario Funcionário responsável
     * @param servicos Lista de serviços
     * @param horarioInicio Horário de início
     * @param estacaoEscolhida Estação escolhida
     */
    public Agendamento(int id, Cliente cliente, Funcionario funcionario,
                       List<Servico> servicos, LocalDateTime horarioInicio, 
                       Estacao estacaoEscolhida) {

        this.id = id;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.servicos = servicos != null ? new ArrayList<>(servicos) : new ArrayList<>();
        this.horarioInicio = horarioInicio;
        this.estacaoEscolhida = estacaoEscolhida;

        calcularPrecoTotal();
        calcularHorarioFim();
    }

    /**
     * Construtor sem lista de serviços.
     *
     * @param id Identificador do agendamento
     * @param cliente Cliente do agendamento
     * @param funcionario Funcionário responsável
     * @param horarioInicio Horário de início
     * @param estacaoEscolhida Estação escolhida
     */
    public Agendamento(int id, Cliente cliente, Funcionario funcionario,
                       LocalDateTime horarioInicio, Estacao estacaoEscolhida) {

        this.id = id;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.horarioInicio = horarioInicio;
        this.servicos = new ArrayList<>();
        this.estacaoEscolhida = estacaoEscolhida;

        calcularPrecoTotal();
        calcularHorarioFim();
    }

    /** Construtor vazio */
    public Agendamento() {}

    /**
     * Calcula o preço total somando o valor de todos os serviços do agendamento.
     */
    private void calcularPrecoTotal() {
        this.precoTotal = servicos.stream()
                .mapToDouble(Servico::getPreco)
                .sum();
    }

    /**
     * Calcula o horário final somando a duração de todos os serviços ao horário de início.
     */
    private void calcularHorarioFim() {
        int duracaoTotal = servicos.stream()
                .mapToInt(Servico::getDuracaoMinutos)
                .sum();

        if (horarioInicio != null) {
            this.horarioFim = horarioInicio.plusMinutes(duracaoTotal);
        }
    }

    // =======================
    // GETTERS E SETTERS
    // =======================
    /**
     * Retorna o ID do agendamento.
     *
     * @return Identificador único do agendamento
     */
    public int getId() { return id; }

    /**
     * Define o ID do agendamento.
     *
     * @param id Identificador único do agendamento
     */
    public void setId(int id) { this.id = id; }

    /**
     * Retorna o cliente associado ao agendamento.
     *
     * @return Cliente do agendamento
     */
    public Cliente getCliente() { return cliente; }

    /**
     * Define o cliente do agendamento.
     *
     * @param cliente Cliente a ser associado ao agendamento
     */
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    /**
     * Retorna o funcionário responsável pelo agendamento.
     *
     * @return Funcionário do agendamento
     */
    public Funcionario getFuncionario() { return funcionario; }

    /**
     * Define o funcionário responsável pelo agendamento.
     *
     * @param funcionario Funcionário a ser associado
     */
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }

    /**
     * Retorna a lista de serviços do agendamento.
     *
     * @return Lista de serviços
     */
    public List<Servico> getServicos() { return servicos; }

    /**
     * Define a lista de serviços do agendamento.
     * <p>Ao definir, recalcula o preço total e o horário final automaticamente.</p>
     *
     * @param servicos Lista de serviços a ser associada
     */
    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos != null ? servicos : new ArrayList<>();
        calcularPrecoTotal();
        calcularHorarioFim();
    }

    /**
     * Retorna o horário de início do agendamento.
     *
     * @return Horário de início
     */
    public LocalDateTime getHorarioInicio() { return horarioInicio; }

    /**
     * Define o horário de início do agendamento.
     * <p>Ao definir, recalcula automaticamente o horário final com base nos serviços.</p>
     *
     * @param horarioInicio Horário de início
     */
    public void setHorarioInicio(LocalDateTime horarioInicio) {
        this.horarioInicio = horarioInicio;
        calcularHorarioFim();
    }

    /**
     * Retorna o horário de término do agendamento.
     *
     * @return Horário final do atendimento
     */
    public LocalDateTime getHorarioFim() { return horarioFim; }

    /**
     * Retorna o preço total do agendamento.
     *
     * @return Preço total calculado a partir dos serviços
     */
    public double getPrecoTotal() { return precoTotal; }

    /**
     * Define o preço total do agendamento.
     *
     * @param precoTotal Valor total a ser definido
     */
    public void setPrecoTotal(double precoTotal) { this.precoTotal = precoTotal; }

    /**
     * Retorna a estação escolhida para o atendimento.
     *
     * @return Estação selecionada
     */
    public Estacao getEstacaoEscolhida() { return estacaoEscolhida; }

    /**
     * Define a estação escolhida para o atendimento.
     *
     * @param estacaoEscolhida Estação a ser definida
     */
    public void setEstacaoEscolhida(Estacao estacaoEscolhida) { this.estacaoEscolhida = estacaoEscolhida; }
        /**
         * Adiciona um serviço ao agendamento e recalcula preço e horário final.
         *
         * @param servico Serviço a ser adicionado
         */
        public void adicionarServico(Servico servico) {
            if (servico != null) {
                servicos.add(servico);
                calcularPrecoTotal();
                calcularHorarioFim();
            }
        }
    
    /**
     * Retorna uma representação textual detalhada do agendamento.
     *
     * <p>Inclui informações do cliente, funcionário, estação, horário inicial e final,
     * preço total e lista de serviços.</p>
     *
     * @return String formatada com os dados do agendamento
     */
    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        String texto =
                "=== Agendamento ===\n" +
                "ID: " + id + "\n" +
                "Cliente: " + (cliente != null ? cliente.getNome() : "N/A") + "\n" +
                "Funcionário: " + (funcionario != null ? funcionario.getNome() : "N/A") + "\n" +
                "Estação: " + (estacaoEscolhida != null ? estacaoEscolhida.name() : "N/A") + "\n" +
                "Horário Inicial: " + (horarioInicio != null ? horarioInicio.format(fmt) : "N/A") + "\n" +
                "Horário Final: " + (horarioFim != null ? horarioFim.format(fmt) : "N/A") + "\n" +
                "Preço Total: R$ " + precoTotal + "\n" +
                "Serviços:\n";

        if (servicos != null && !servicos.isEmpty()) {
            for (Servico s : servicos) {
                texto += " - " + s.getNome() +
                        " (R$ " + s.getPreco() +
                        ", " + s.getDuracaoMinutos() + " min)\n";
            }
        } else {
            texto += " - Nenhum serviço informado\n";
        }

        return texto;
    }
}
