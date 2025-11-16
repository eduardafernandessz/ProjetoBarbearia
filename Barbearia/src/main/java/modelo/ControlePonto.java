package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import modelo.Funcionario;

/**
 * Representa o controle de ponto de um funcionário.
 *
 * <p>Registra a data, horário de entrada e saída de um funcionário.</p>
 */
public class ControlePonto {

    /** ID do funcionário */
    private int idFuncionario;

    /** Data do registro */
    private LocalDate data;

    /** Horário de entrada do funcionário */
    private LocalTime horarioEntrada;

    /** Horário de saída do funcionário */
    private LocalTime horarioSaida;

    /** Funcionário relacionado ao ponto */
    private Funcionario funcionario; 

    /**
     * Construtor que cria um registro de ponto para um funcionário em uma data específica.
     *
     * @param idFuncionario ID do funcionário
     * @param data Data do ponto
     * @param funcionario Objeto do funcionário
     */
    public ControlePonto(int idFuncionario, LocalDate data, Funcionario funcionario) {
        this.idFuncionario = idFuncionario;
        this.data = data;
        this.funcionario = funcionario; 
    }
    
    /** Construtor vazio */
    public ControlePonto() {
    }

    /** @return ID do funcionário */
    public int getIdFuncionario() { return idFuncionario; }

    /** @return Data do ponto */
    public LocalDate getData() { return data; }

    /** @return Horário de entrada */
    public LocalTime getHorarioEntrada() { return horarioEntrada; }

    /** @return Horário de saída */
    public LocalTime getHorarioSaida() { return horarioSaida; }

    /** Define o horário de entrada do ponto
     * @param horarioEntrada Horário de entrada
     */
    public void setHorarioEntrada(LocalTime horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
    }

    /** Define o horário de saída do ponto
     * @param horarioSaida Horário de saída
     */
    public void setHorarioSaida(LocalTime horarioSaida) {
        this.horarioSaida = horarioSaida;
    }
    
    /** @return Funcionário relacionado ao ponto */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    /**
     * Retorna uma representação em string do controle de ponto.
     *
     * @return String formatada com informações do funcionário, data, entrada e saída
     */
    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        String formattedDate = (data != null) ? data.format(dateFormatter) : "N/A";
        String formattedEntrada = (horarioEntrada != null) ? horarioEntrada.format(timeFormatter) : "N/A";
        String formattedSaida = (horarioSaida != null) ? horarioSaida.format(timeFormatter) : "N/A";

        return "Controle de Ponto\n" +
                "---------------------\n" +
                "Funcionário: " + (funcionario != null ? funcionario.getNome() : "N/A") + "\n"+
                "Data: " + formattedDate + "\n" +
                "Entrada: " + formattedEntrada + "\n" +
                "Saída: " + formattedSaida + "\n";
    }
}
