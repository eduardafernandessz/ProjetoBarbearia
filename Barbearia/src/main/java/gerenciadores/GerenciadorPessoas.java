package gerenciadores;

import modelo.Cliente;
import modelo.Funcionario;
import utils.CRUDGenerico;

import java.util.List;

public class GerenciadorPessoas {

    private final CRUDGenerico<Cliente> crudClientes;
    private final CRUDGenerico<Funcionario> crudFuncionarios;

    public GerenciadorPessoas() {
        crudClientes = new CRUDGenerico<>("src/main/java/repositorio/clientes.json", Cliente.class);
        crudFuncionarios = new CRUDGenerico<>("src/main/java/repositorio/funcionarios.json", Funcionario.class);
    }

    // ==============================
    // CLIENTES
    // ==============================
    public void adicionarCliente(Cliente cliente) {
        crudClientes.adicionar(cliente);
    }

    public List<Cliente> listarClientes() {
        return crudClientes.listar();
    }

    public Cliente buscarClientePorId(int id) {
        return crudClientes.buscarPorId(id);
    }

    public boolean editarCliente(Cliente clienteEditado) {
        Cliente c = crudClientes.buscarPorId(clienteEditado.getId());
        if (c == null) return false;

        c.setNome(clienteEditado.getNome());
        c.setTelefone(clienteEditado.getTelefone());
        c.setEmail(clienteEditado.getEmail());
        c.setEndereco(clienteEditado.getEndereco());

        return true;
    }

    public boolean removerCliente(int id) {
        Cliente c = crudClientes.buscarPorId(id);
        if (c != null) {
            crudClientes.remover(c);
            return true;
        }
        return false;
    }

    // ==============================
    // FUNCIONÁRIOS
    // ==============================
    public void adicionarFuncionario(Funcionario funcionario) {
        crudFuncionarios.adicionar(funcionario);
    }

    public List<Funcionario> listarFuncionarios() {
        return crudFuncionarios.listar();
    }

    public Funcionario buscarFuncionarioPorId(int id) {
        return crudFuncionarios.buscarPorId(id);
    }

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

    public boolean removerFuncionario(int id) {
        Funcionario f = crudFuncionarios.buscarPorId(id);
        if (f != null) {
            crudFuncionarios.remover(f);
            return true;
        }
        return false;
    }

    // ==============================
    // SALVAR
    // ==============================
    public void salvar() {
        crudClientes.salvar();
        crudFuncionarios.salvar();
    }
}
