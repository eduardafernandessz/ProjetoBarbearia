package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ControlePonto {

    private int idFuncionario;
    private LocalDate data;
    private LocalTime horarioEntrada;
    private LocalTime horarioSaida;

    public ControlePonto(int idFuncionario, LocalDate data) {
        this.idFuncionario = idFuncionario;
        this.data = data;
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
    
        @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        String entrada = (horarioEntrada != null) ? horarioEntrada.format(timeFormatter) : "Não registrado";
        String saida = (horarioSaida != null) ? horarioSaida.format(timeFormatter) : "Não registrado";

        return "ControlePonto {" +
               "ID Funcionário=" + idFuncionario +
               ", Data=" + data.format(dateFormatter) +
               ", Entrada=" + entrada +
               ", Saida=" + saida +
               '}';
    }
}

