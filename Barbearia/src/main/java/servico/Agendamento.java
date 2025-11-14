
package servico;

import java.time.LocalDateTime;
import usuarios.Cliente;
import usuarios.Funcionario;

public class Agendamento {
    private int id;
    private Cliente cliente;
    private Funcionario funcionario;
    private Servico servico;
    private LocalDateTime horarioInicio;

    public Agendamento(int id, Cliente cliente, Funcionario funcionario, Servico servico, LocalDateTime horarioInicio) {
        this.id = id;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.servico = servico;
        this.horarioInicio = horarioInicio;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Funcionario getFuncionario() {
        return funcionario;
    }
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    public Servico getServico() {
        return servico;
    }
    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public LocalDateTime getHorarioInicio() {
        return horarioInicio;
    }
    public void setHorarioInicio(LocalDateTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }
}

 