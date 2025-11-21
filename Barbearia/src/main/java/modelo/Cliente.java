package modelo;

/**
 * Representa um cliente do sistema.
 * 
 * <p>Essa classe herda da classe {@link Pessoa} e mantém todas as informações
 * básicas de uma pessoa, como id, nome, CPF, telefone, email e endereço.</p>
 * 
 * <p>Pode ser utilizada para registrar clientes em um sistema de vendas,
 * agendamento ou qualquer aplicação que necessite do objeto pessoa.</p>
 */
public class Cliente extends Pessoa {

    /**
     * Construtor completo da classe Cliente.
     *
     * @param id Identificador único do cliente.
     * @param nome Nome completo do cliente.
     * @param cpf CPF do cliente.
     * @param telefone Telefone de contato do cliente.
     * @param email Email de contato do cliente.
     * @param endereco Endereço do cliente.
     */
    public Cliente(int id, String nome, String cpf, String telefone, String email, String endereco) {
        super(id, nome, cpf, telefone, email, endereco);
    }

    /**
     * Construtor padrão da classe Cliente.
     * <p>Cria um cliente sem inicializar nenhum atributo.</p>
     */
    public Cliente() {
    }

    /**
     * Retorna uma representação em String do cliente.
     *
     * <p>Inclui todas as informações herdadas da classe {@link Pessoa}:</p>
     * @return String formatada com os dados do cliente.
     */
    @Override
    public String toString() {
        return  
                "-------------------------------\n" +
                "\nId:" + getId() +
                "\nNome: " + getNome() +
                "\nCpf: " + getCpf() +
                "\nTelefone: " + getTelefone() +
                "\nEmail: " + getEmail() +
                "\nEndereco: " + getEndereco();
    }
}
