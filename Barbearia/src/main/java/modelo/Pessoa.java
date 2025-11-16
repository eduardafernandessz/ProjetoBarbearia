package modelo;

/**
 * Classe abstrata que representa uma pessoa.
 * 
 * Essa classe serve como base para outras classes que representam tipos
 * específicos de pessoas, como Cliente, Funcionario e gerente. Contém informações
 * básicas, como id, nome, CPF, telefone, email e endereço.
 */
public abstract class Pessoa {

    /** Identificador único da pessoa. */
    protected int id;

    /** Nome completo da pessoa. */
    protected String nome;

    /** CPF da pessoa, usado como documento de identificação. */
    protected String cpf;

    /** Telefone de contato da pessoa. */
    protected String telefone;

    /** Email de contato da pessoa. */
    protected String email;

    /** Endereço residencial ou comercial da pessoa. */
    protected String endereco;


    /**
     * Construtor completo da classe Pessoa.
     *
     * @param id Identificador único da pessoa.
     * @param nome Nome completo da pessoa.
     * @param cpf CPF da pessoa.
     * @param telefone Telefone de contato da pessoa.
     * @param email Email de contato da pessoa.
     * @param endereco Endereço da pessoa.
     */
    public Pessoa(int id, String nome, String cpf, String telefone, String email, String endereco) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }
    
    /**
     * Construtor padrão da classe Pessoa.
     * <p>Cria uma pessoa sem inicializar nenhum atributo.</p>
     */
    public Pessoa() {
    }

    /**
     * Retorna o identificador da pessoa.
     *
     * @return id da pessoa.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador da pessoa.
     *
     * @param id Novo id da pessoa.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o nome da pessoa.
     *
     * @return nome da pessoa.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da pessoa.
     *
     * @param nome Novo nome da pessoa.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o CPF da pessoa.
     *
     * @return CPF da pessoa.
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define o CPF da pessoa.
     *
     * @param cpf Novo CPF da pessoa.
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Retorna o telefone da pessoa.
     *
     * @return telefone da pessoa.
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone da pessoa.
     *
     * @param telefone Novo telefone da pessoa.
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Retorna o email da pessoa.
     *
     * @return email da pessoa.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o email da pessoa.
     *
     * @param email Novo email da pessoa.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna o endereço da pessoa.
     *
     * @return endereço da pessoa.
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Define o endereço da pessoa.
     *
     * @param endereco Novo endereço da pessoa.
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}
