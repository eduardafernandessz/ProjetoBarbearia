package menu;

import usuarios.Cliente;
import usuarios.Funcionario;
import utils.CRUDGenerico;

import java.util.List;
import java.util.Scanner;

public class MenuPessoa {

    private Scanner sc = new Scanner(System.in);

    private CRUDGenerico<Cliente> crudClientes = new CRUDGenerico<>("src/main/java/repositorio/clientes.json", Cliente.class);
    private CRUDGenerico<Funcionario> crudFuncionarios = new CRUDGenerico<>("src/main/java/repositorio/funcionarios.json", Funcionario.class);

    // --- MENU PARA CLIENTES ---
    public void exibirMenuClientes() {
        int opc;
        do {
            System.out.println("\n--- MENU CLIENTES ---");
            System.out.println("1 - Listar Clientes");
            System.out.println("2 - Adicionar Cliente");
            System.out.println("3 - Buscar Cliente por ID");
            System.out.println("4 - Editar Cliente");
            System.out.println("5 - Remover Cliente");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1 -> listarClientes();
                case 2 -> adicionarCliente();
                case 3 -> buscarCliente();
                case 4 -> editarCliente();
                case 5 -> removerCliente();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opc != 0);
    }

    // --- MENU PARA FUNCIONÁRIOS ---
    public void exibirMenuFuncionarios() {
        int opc;
        do {
            System.out.println("\n--- MENU FUNCIONÁRIOS ---");
            System.out.println("1 - Listar Funcionários");
            System.out.println("2 - Adicionar Funcionário");
            System.out.println("3 - Buscar Funcionário por ID");
            System.out.println("4 - Editar Funcionário");
            System.out.println("5 - Remover Funcionário");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1 -> listarFuncionarios();
                case 2 -> adicionarFuncionario();
                case 3 -> buscarFuncionario();
                case 4 -> editarFuncionario();
                case 5 -> removerFuncionario();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opc != 0);
    }

    // ---------- CLIENTES ----------
    private void listarClientes() {
        List<Cliente> lista = crudClientes.listar();
        if (lista.isEmpty()) System.out.println("Nenhum cliente cadastrado.");
        else lista.forEach(System.out::println);
    }

    private void adicionarCliente() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Endereço: ");
        String endereco = sc.nextLine();

        Cliente c = new Cliente(0, nome, cpf, telefone, email, endereco);
        crudClientes.adicionar(c);
        System.out.println("Cliente adicionado!");
    }

    private void buscarCliente() {
        System.out.print("ID do cliente: ");
        int id = sc.nextInt(); sc.nextLine();
        Cliente c = crudClientes.buscarPorId(id);
        System.out.println(c != null ? c : "Cliente não encontrado.");
    }

    private void editarCliente() {
        System.out.print("ID do cliente para editar: ");
        int id = sc.nextInt(); sc.nextLine();
        Cliente c = crudClientes.buscarPorId(id);
        if (c != null) {
            System.out.print("Nome (" + c.getNome() + "): ");
            String nome = sc.nextLine();
            if (!nome.isEmpty()) c.setNome(nome);

            System.out.print("Telefone (" + c.getTelefone() + "): ");
            String telefone = sc.nextLine();
            if (!telefone.isEmpty()) c.setTelefone(telefone);

            System.out.print("Email (" + c.getEmail() + "): ");
            String email = sc.nextLine();
            if (!email.isEmpty()) c.setEmail(email);

            System.out.print("Endereço (" + c.getEndereco() + "): ");
            String endereco = sc.nextLine();
            if (!endereco.isEmpty()) c.setEndereco(endereco);

            crudClientes.salvar(); // salvar alterações
            System.out.println("Cliente atualizado!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private void removerCliente() {
        System.out.print("ID do cliente para remover: ");
        int id = sc.nextInt(); sc.nextLine();
        Cliente c = crudClientes.buscarPorId(id);
        if (c != null) {
            crudClientes.remover(c);
            System.out.println("Cliente removido!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    // ---------- FUNCIONÁRIOS ----------
    private void listarFuncionarios() {
        List<Funcionario> lista = crudFuncionarios.listar();
        if (lista.isEmpty()) System.out.println("Nenhum funcionário cadastrado.");
        else lista.forEach(System.out::println);
    }

    private void adicionarFuncionario() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Endereço: ");
        String endereco = sc.nextLine();
        System.out.print("Cargo: ");
        String cargo = sc.nextLine();
        System.out.print("Salário: ");
        double salario = sc.nextDouble(); sc.nextLine();
        System.out.print("Login: ");
        String login = sc.nextLine();
        System.out.print("Senha: ");
        int senha = sc.nextInt(); sc.nextLine();

        Funcionario f = new Funcionario(cargo, salario, login, senha, 0, nome, cpf, telefone, email, endereco);
        crudFuncionarios.adicionar(f);
        System.out.println("Funcionário adicionado!");
    }

    private void buscarFuncionario() {
        System.out.print("ID do funcionário: ");
        int id = sc.nextInt(); sc.nextLine();
        Funcionario f = crudFuncionarios.buscarPorId(id);
        System.out.println(f != null ? f : "Funcionário não encontrado.");
    }

    private void editarFuncionario() {
        System.out.print("ID do funcionário para editar: ");
        int id = sc.nextInt(); sc.nextLine();
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

            crudFuncionarios.salvar();
            System.out.println("Funcionário atualizado!");
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    private void removerFuncionario() {
        System.out.print("ID do funcionário para remover: ");
        int id = sc.nextInt(); sc.nextLine();
        Funcionario f = crudFuncionarios.buscarPorId(id);
        if (f != null) {
            crudFuncionarios.remover(f);
            System.out.println("Funcionário removido!");
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }
}
