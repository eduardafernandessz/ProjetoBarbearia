package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import modelo.Funcionario;

public class ControlePonto {

    private int idFuncionario;
    private LocalDate data;
    private LocalTime horarioEntrada;
    private LocalTime horarioSaida;
    private Funcionario funcionario; 

    public ControlePonto(int idFuncionario, LocalDate data, Funcionario funcionario) {
        this.idFuncionario = idFuncionario;
        this.data = data;
        this.funcionario = funcionario; 
    }

    public int getIdFuncionario() { return idFuncionario; }
    public LocalDate getData() { return data; }
    public LocalTime getHorarioEntrada() { return horarioEntrada; }
    public LocalTime getHorarioSaida() { return horarioSaida; }

    public void setHorarioEntrada(LocalTime horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
    }

    public void setHorarioSaida(LocalTime horarioSaida) {
        this.horarioSaida = horarioSaida;
    }
    
    public Funcionario getFuncionario() {
        return funcionario;
    }


    @Override
    public String toString() {
        // Definindo os formatos de data e horário
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Formatando a data e horário
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

