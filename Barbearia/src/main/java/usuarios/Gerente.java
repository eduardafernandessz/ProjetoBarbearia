package usuarios;

import usuarios.Funcionario;

public class Gerente extends Funcionario {


    public Gerente(String cargo, double salario, String login, int senha, int id, String nome, String cpf, String telefone, String email, String endereco) {
        super( cargo, salario, login, senha, id, nome, cpf, telefone, email, endereco);
    }
    
    public Gerente() {
    }

    public String toString() {
        return "Gerente:\n" +
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


