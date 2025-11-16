package modelo;



import com.fasterxml.jackson.annotation.JsonFormat;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import modelo.Estacao;
import modelo.Servico;
import modelo.Cliente;
import modelo.Funcionario;

public class Agendamento {

    private int id;
    private Cliente cliente;
    private Funcionario funcionario;
    private List<Servico> servicos; 
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime horarioInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime horarioFim;
    private double precoTotal;
    private Estacao estacaoEscolhida;

    // Construtor completo
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

    // Construtor sem lista de serviços
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

    public Agendamento() {}

    // Calcula o preço total somando todos os serviços
    private void calcularPrecoTotal() {
        this.precoTotal = servicos.stream()
                .mapToDouble(Servico::getPreco)
                .sum();
    }

    // Calcula o horário final somando a duração de todos os serviços
    private void calcularHorarioFim() {
        int duracaoTotal = servicos.stream()
                .mapToInt(Servico::getDuracaoMinutos)
                .sum();

        if (horarioInicio != null) {
            this.horarioFim = horarioInicio.plusMinutes(duracaoTotal);
        }
    }

    // --- GETTERS E SETTERS ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }

    public List<Servico> getServicos() { return servicos; }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos != null ? servicos : new ArrayList<>();
        calcularPrecoTotal();
        calcularHorarioFim();
    }

    public LocalDateTime getHorarioInicio() { return horarioInicio; }

    public void setHorarioInicio(LocalDateTime horarioInicio) {
        this.horarioInicio = horarioInicio;
        calcularHorarioFim();
    }

    public LocalDateTime getHorarioFim() { return horarioFim; }

    public double getPrecoTotal() { return precoTotal; }
    public void setPrecoTotal(double precoTotal) { this.precoTotal = precoTotal; }

    public Estacao getEstacaoEscolhida() { return estacaoEscolhida; }
    public void setEstacaoEscolhida(Estacao estacaoEscolhida) { this.estacaoEscolhida = estacaoEscolhida; }

    // Adiciona um serviço ao agendamento
    public void adicionarServico(Servico servico) {
        if (servico != null) {
            servicos.add(servico);
            calcularPrecoTotal();
            calcularHorarioFim();
        }
    }
    


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
