package gerenciadores;

import modelo.Cliente;
import modelo.Funcionario;
import utils.CRUDGenerico;

import java.util.List;

/**
 * Classe responsável por gerenciar clientes, funcionários.
 *
 * <p>Utiliza CRUD genérico para criar, armazenar, buscar, editar e remover
 * objetos Cliente, Funcionario em memória e em arquivos JSON.</p>
 */
public class GerenciadorPessoas {

    /** CRUD genérico para gerenciar clientes. */
    private final CRUDGenerico<Cliente> crudClientes;

    /** CRUD genérico para gerenciar funcionários. */
    private final CRUDGenerico<Funcionario> crudFuncionarios;

    /**
     * Construtor que inicializa os CRUDs de clientes e funcionários
     * apontando para os arquivos JSON correspondentes.
     */
    public GerenciadorPessoas() {
        crudClientes = new CRUDGenerico<>("src/main/java/repositorio/clientes.json", Cliente.class);
        crudFuncionarios = new CRUDGenerico<>("src/main/java/repositorio/funcionarios.json", Funcionario.class);
    }


    // CLIENTES


    /**
     * Busca um cliente pelo nome.
     *
     * @param nome Nome do cliente
     * @return Cliente encontrado ou null se não existir
     */
    public Cliente buscarCliente(String nome) {
        List<Cliente> lista = crudClientes.listar();

        for (Cliente c : lista) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                return c;
            }
        }

        return null; 
    }

    /**
     * Adiciona um cliente.
     *
     * @param cliente Cliente a ser adicionado
     */
    public void adicionarCliente(Cliente cliente) {
        crudClientes.adicionar(cliente);
    }

    /**
     * Lista todos os clientes.
     *
     * @return Lista de clientes
     */
    public List<Cliente> listarClientes() {
        return crudClientes.listar();
    }

    /**
     * Busca um cliente pelo ID.
     *
     * @param id ID do cliente
     * @return Cliente encontrado ou null se não existir
     */
    public Cliente buscarClientePorId(int id) {
        return crudClientes.buscarPorId(id);
    }

    /**
     * Edita os dados de um cliente existente.
     *
     * @param clienteEditado Cliente com os dados atualizados
     * @return true se a edição foi realizada, false se o cliente não foi encontrado
     */
    public boolean editarCliente(Cliente clienteEditado) {
        Cliente c = crudClientes.buscarPorId(clienteEditado.getId());
        if (c == null) return false;

        c.setNome(clienteEditado.getNome());
        c.setTelefone(clienteEditado.getTelefone());
        c.setEmail(clienteEditado.getEmail());
        c.setEndereco(clienteEditado.getEndereco());

        return true;
    }

    /**
     * Remove um cliente pelo ID.
     *
     * @param id ID do cliente
     * @return true se a remoção foi realizada, false se o cliente não foi encontrado
     */
    public boolean removerCliente(int id) {
        Cliente c = crudClientes.buscarPorId(id);
        if (c != null) {
            crudClientes.remover(c);
            return true;
        }
        return false;
    }

    // FUNCIONÁRIOS

    /**
     * Adiciona um funcionário.
     *
     * @param funcionario Funcionário a ser adicionado
     */
    public void adicionarFuncionario(Funcionario funcionario) {
        crudFuncionarios.adicionar(funcionario);
    }

    /**
     * Lista todos os funcionários.
     *
     * @return Lista de funcionários
     */
    public List<Funcionario> listarFuncionarios() {
        return crudFuncionarios.listar();
    }

    /**
     * Busca um funcionário pelo ID.
     *
     * @param id ID do funcionário
     * @return Funcionário encontrado ou null se não existir
     */
    public Funcionario buscarFuncionarioPorId(int id) {
        return crudFuncionarios.buscarPorId(id);
    }

    /**
     * Edita os dados de um funcionário existente.
     *
     * @param funcionarioEditado Funcionário com os dados atualizados
     * @return true se a edição foi realizada, false se o funcionário não foi encontrado
     */
    public boolean editarFuncionario(Funcionario funcionarioEditado) {
        Funcionario f = crudFuncionarios.buscarPorId(funcionarioEditado.getId());
        if (f == null) return false;

        f.setNome(funcionarioEditado.getNome());
        f.setCargo(funcionarioEditado.getCargo());
        f.setSalario(funcionarioEditado.getSalario());
        f.setTelefone(funcionarioEditado.getTelefone());
        f.setEmail(funcionarioEditado.getEmail());
        f.setEndereco(funcionarioEditado.getEndereco());
        f.setLogin(funcionarioEditado.getLogin());
        f.setSenha(funcionarioEditado.getSenha());

        return true;
    }

    /**
     * Remove um funcionário pelo ID.
     *
     * @param id ID do funcionário
     * @return true se a remoção foi realizada, false se o funcionário não foi encontrado
     */
    public boolean removerFuncionario(int id) {
        Funcionario f = crudFuncionarios.buscarPorId(id);
        if (f != null) {
            crudFuncionarios.remover(f);
            return true;
        }
        return false;
    }

    // SALVAR

    /**
     * Salva os clientes e funcionários nos arquivos JSON correspondentes.
     */
    public void salvar() {
        crudClientes.salvar();
        crudFuncionarios.salvar();
    }
}
