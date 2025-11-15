package gerenciadores;

import modelo.Cliente;
import modelo.Funcionario;
import utils.CRUDGenerico;

import java.util.List;
import java.util.Scanner;

public class GerenciadorPessoas {

    private CRUDGenerico<Cliente> crudClientes;
    private CRUDGenerico<Funcionario> crudFuncionarios;

    public GerenciadorPessoas() {
        crudClientes = new CRUDGenerico<>("src/main/java/repositorio/clientes.json", Cliente.class);
        crudFuncionarios = new CRUDGenerico<>("src/main/java/repositorio/funcionarios.json", Funcionario.class);
    }

    // ==============================
    // CLIENTES
    // ==============================

    public void adicionarCliente(String nome, String cpf, String telefone, String email, String endereco) {
        Cliente c = new Cliente(0, nome, cpf, telefone, email, endereco);
        crudClientes.adicionar(c);
    }

    public List<Cliente> listarClientes() {
        return crudClientes.listar();
    }

    public Cliente buscarClientePorId(int id) {
        return crudClientes.buscarPorId(id);
    }

    public void editarCliente(int id, Scanner sc) {
        Cliente c = crudClientes.buscarPorId(id);
        if (c != null) {
            System.out.print("Nome (" + c.getNome() + "): ");
            String nome = sc.nextLine();
            if (!nome.isEmpty()) c.setNome(nome);

            System.out.print("Telefone (" + c.getTelefone() + "): ");
            String tel = sc.nextLine();
            if (!tel.isEmpty()) c.setTelefone(tel);

            System.out.print("Email (" + c.getEmail() + "): ");
            String email = sc.nextLine();
            if (!email.isEmpty()) c.setEmail(email);

            System.out.print("Endereço (" + c.getEndereco() + "): ");
            String end = sc.nextLine();
            if (!end.isEmpty()) c.setEndereco(end);

        }
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

    public void adicionarFuncionario(String nome, String cpf, String telefone, String email,
                                     String endereco, String cargo, double salario, String login, int senha) {
        Funcionario f = new Funcionario(cargo, salario, login, senha, 0, nome, cpf, telefone, email, endereco);
        crudFuncionarios.adicionar(f);
    }

    public List<Funcionario> listarFuncionarios() {
        return crudFuncionarios.listar();
    }

    public Funcionario buscarFuncionarioPorId(int id) {
        return crudFuncionarios.buscarPorId(id);
    }

    public void editarFuncionario(int id, Scanner sc) {
        Funcionario f = crudFuncionarios.buscarPorId(id);
        if (f != null) {
            System.out.print("Nome (" + f.getNome() + "): ");
            String nome = sc.nextLine();
            if (!nome.isEmpty()) f.setNome(nome);

            System.out.print("Cargo (" + f.getCargo() + "): ");
            String cargo = sc.nextLine();
            if (!cargo.isEmpty()) f.setCargo(cargo);

            System.out.print("Salário (" + f.getSalario() + "): ");
            String salarioStr = sc.nextLine();
            if (!salarioStr.isEmpty()) f.setSalario(Double.parseDouble(salarioStr));

            System.out.print("Telefone (" + f.getTelefone() + "): ");
            String telefone = sc.nextLine();
            if (!telefone.isEmpty()) f.setTelefone(telefone);

            System.out.print("Email (" + f.getEmail() + "): ");
            String email = sc.nextLine();
            if (!email.isEmpty()) f.setEmail(email);

            System.out.print("Endereço (" + f.getEndereco() + "): ");
            String endereco = sc.nextLine();
            if (!endereco.isEmpty()) f.setEndereco(endereco);

            System.out.print("Login (" + f.getLogin() + "): ");
            String login = sc.nextLine();
            if (!login.isEmpty()) f.setLogin(login);

            System.out.print("Senha (" + f.getSenha() + "): ");
            String senhaStr = sc.nextLine();
            if (!senhaStr.isEmpty()) f.setSenha(Integer.parseInt(senhaStr));

        }
    }

    public boolean removerFuncionario(int id) {
        Funcionario f = crudFuncionarios.buscarPorId(id);
        if (f != null) {
            crudFuncionarios.remover(f);
            return true;
        }
        return false;
    }
    
        public void salvar() {
            crudClientes.salvar();
            crudFuncionarios.salvar();
            System.out.println(" Alterações salvas!");
        }
}
