package modelo;

import modelo.Funcionario;

/**
 * Representa um gerente no sistema.
 * 
 * <p>Essa classe mantém todas as informações de um funcionário,
 * incluindo id, nome, CPF, telefone, email, endereço, cargo, salário,
 * login e senha para acesso ao sistema.</p>
 */
public class Gerente extends Funcionario {

    /**
     * Construtor completo da classe Gerente.
     *
     * @param cargo Cargo do gerente.
     * @param salario Salário do gerente.
     * @param login Login do gerente para acessar o sistema.
     * @param senha Senha do gerente para autenticação no sistema.
     * @param id Identificador único do gerente.
     * @param nome Nome completo do gerente.
     * @param cpf CPF do gerente.
     * @param telefone Telefone de contato do gerente.
     * @param email Email do gerente.
     * @param endereco Endereço do gerente.
     */
    public Gerente(String cargo, double salario, String login, int senha, int id, String nome, String cpf, String telefone, String email, String endereco) {
        super(cargo, salario, login, senha, id, nome, cpf, telefone, email, endereco);
    }

    /**
     * Construtor padrão da classe Gerente.
     * <p>Cria um gerente sem inicializar nenhum atributo.</p>
     */
    public Gerente() {
    }

    /**
     * Retorna uma representação em String do gerente.
     *
     * <p>Inclui todos os dados do gerente: id, nome, CPF, telefone,
     * email, endereço, cargo e salário.</p>
     *
     * @return String formatada com os dados do gerente.
     */
    @Override
    public String toString() {
        return "Gerente\n" +
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
