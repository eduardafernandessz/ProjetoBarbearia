package modelo;

public class Funcionario extends Pessoa{

    protected String cargo;
    protected double salario;
    protected String login;
    protected int senha;



    public Funcionario(String cargo, double salario, String login, int senha, int id, String nome, String cpf, String telefone, String email, String endereco) {
        super(id, nome, cpf, telefone, email, endereco);
        this.cargo = cargo;
        this.salario = salario;
        this.login = login;
        this.senha = senha;
    }
    
    public Funcionario() {
}    

    public String getCargo() {
        return cargo;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }


    public String toString() {
        return "Funcionario:\n" +
                "ID: " + id + "\n" +
                "Nome: " + nome + "\n" +
                "Telefone: " + telefone + "\n" +
                "Email: " + email + "\n" +
                "CPF: " + cpf + "\n" +
                "Endereço: " + endereco + "\n" +
                "Cargo: " + cargo + "\n" +
                "Salário: " + salario;
    }
}
