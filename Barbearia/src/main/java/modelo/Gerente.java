package modelo;

import modelo.Funcionario;

public class Gerente extends Funcionario {


    public Gerente(String cargo, double salario, String login, int senha, int id, String nome, String cpf, String telefone, String email, String endereco) {
        super( cargo, salario, login, senha, id, nome, cpf, telefone, email, endereco);
    }
    
    public Gerente() {
    }

    @Override
   public String toString() {
       return "Gerente" +
               "-------------------------------\n" +
              "\n  ID: " + id +
              "\n  Nome: " + nome +
              "\n  CPF: " + cpf +
              "\n  Telefone: " + telefone +
              "\n  Email: " + email +
              "\n  Endereço: " + endereco +
              "\n  Cargo: " + cargo +
              "\n  Salário: R$ " + String.format("%.2f", salario);
   }

}


