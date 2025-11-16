package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

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
}
