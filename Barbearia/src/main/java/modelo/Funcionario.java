package modelo;

/**
 * Representa um funcionário do sistema.
 * 
 * <p>Essa classe herda da classe {@link Pessoa} e adiciona informações
 * específicas de um funcionário, como cargo, salário, login e senha.</p>
 */
public class Funcionario extends Pessoa {

    protected String cargo;
    protected double salario;
    protected String login;
    protected int senha;

    /**
     * Construtor completo da classe Funcionario.
     *
     * @param cargo Cargo do funcionário.
     * @param salario Salário do funcionário.
     * @param login Login do funcionário.
     * @param senha Senha do funcionário.
     * @param id Identificador único do funcionário.
     * @param nome Nome completo do funcionário.
     * @param cpf CPF do funcionário.
     * @param telefone Telefone de contato do funcionário.
     * @param email Email do funcionário.
     * @param endereco Endereço do funcionário.
     */
    public Funcionario(String cargo, double salario, String login, int senha, int id, String nome, String cpf, String telefone, String email, String endereco) {
        super(id, nome, cpf, telefone, email, endereco);
        this.cargo = cargo;
        this.salario = salario;
        this.login = login;
        this.senha = senha;
    }
    
    /**
     * Construtor padrão da classe Funcionario.
     * <p>Cria um funcionário sem inicializar nenhum atributo.</p>
     */
    public Funcionario() {
    }    

    /**
     * Retorna o cargo do funcionário.
     *
     * @return cargo do funcionário.
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Define o cargo do funcionário.
     *
     * @param cargo Novo cargo do funcionário.
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * Retorna o salário do funcionário.
     *
     * @return salário do funcionário.
     */
    public double getSalario() {
        return salario;
    }

    /**
     * Define o salário do funcionário.
     *
     * @param salario Novo salário do funcionário.
     */
    public void setSalario(double salario) {
        this.salario = salario;
    }

    /**
     * Retorna o login do funcionário.
     *
     * @return login do funcionário.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Define o login do funcionário.
     *
     * @param login Novo login do funcionário.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Retorna a senha do funcionário.
     *
     * @return senha do funcionário.
     */
    public int getSenha() {
        return senha;
    }

    /**
     * Define a senha do funcionário.
     *
     * @param senha Nova senha do funcionário.
     */
    public void setSenha(int senha) {
        this.senha = senha;
    }

    /**
     * Retorna uma representação em String do funcionário.
     *
     * <p>Inclui todas as informações herdadas da classe {@link Pessoa}
     * e os atributos específicos da classe {@link Funcionario}.</p>
     *
     * @return String formatada com os dados do funcionário.
     */
    @Override
    public String toString() {
        return "Funcionario" +
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
