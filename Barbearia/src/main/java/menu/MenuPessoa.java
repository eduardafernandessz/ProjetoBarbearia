package menu;

import gerenciadores.GerenciadorPessoas;
import modelo.Cliente;
import modelo.Funcionario;

import java.util.List;
import java.util.Scanner;

public class MenuPessoa {

    private Scanner sc = new Scanner(System.in);
    private GerenciadorPessoas gerenciador = new GerenciadorPessoas();

    // --- MENU PRINCIPAL CLIENTES ---
    public void exibirMenuClientes() {
        int opc;
        do {
            System.out.println("\n--- MENU CLIENTES ---");
            System.out.println("1 - Listar Clientes");
            System.out.println("2 - Adicionar Cliente");
            System.out.println("3 - Buscar Cliente por ID");
            System.out.println("4 - Editar Cliente");
            System.out.println("5 - Remover Cliente");
            System.out.println("6 - Salvar Alterações");
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
                case 6 -> salvar();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opc != 0);
    }

    // --- MENU PRINCIPAL FUNCIONÁRIOS ---
    public void exibirMenuFuncionarios() {
        int opc;
        do {
            System.out.println("\n--- MENU FUNCIONÁRIOS ---");
            System.out.println("1 - Listar Funcionários");
            System.out.println("2 - Adicionar Funcionário");
            System.out.println("3 - Buscar Funcionário por ID");
            System.out.println("4 - Editar Funcionário");
            System.out.println("5 - Remover Funcionário");
            System.out.println("6 - Salvar Alterações");
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
                case 6 -> salvar();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opc != 0);
    }

    // ======================
    // SALVAR
    // ======================
    private void salvar() {
        gerenciador.salvar();
        System.out.println(" Alterações salvas!");
    }

    // ======================
    // CLIENTES
    // ======================
    private void listarClientes() {
        List<Cliente> lista = gerenciador.listarClientes();
        if (lista.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        System.out.println("\n--- LISTA DE CLIENTES ---");
        for (Cliente c : lista) {
            System.out.println("-----------------------------");
            System.out.println("ID: " + c.getId());
            System.out.println("Nome: " + c.getNome());
            System.out.println("Telefone: " + c.getTelefone());
            System.out.println("Endereço: " + c.getEndereco());
            System.out.println("Email: " + c.getEmail());
            System.out.println("CPF: " + c.getCpf());
        }
        System.out.println("-----------------------------");
    }

    private void adicionarCliente() {
        System.out.print("Nome: "); String nome = sc.nextLine();
        System.out.print("CPF: "); String cpf = sc.nextLine();
        System.out.print("Telefone: "); String telefone = sc.nextLine();
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("Endereço: "); String endereco = sc.nextLine();

        gerenciador.adicionarCliente(nome, cpf, telefone, email, endereco);
        System.out.println("✔ Cliente adicionado!");
    }

    private void buscarCliente() {
        System.out.print("ID do cliente: ");
        int id = sc.nextInt(); sc.nextLine();
        Cliente c = gerenciador.buscarClientePorId(id);
        if (c != null) {
            System.out.println(c);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private void editarCliente() {
        System.out.print("ID do cliente para editar: ");
        int id = sc.nextInt(); sc.nextLine();
        gerenciador.editarCliente(id, sc);
        System.out.println("✔ Cliente atualizado!");
    }

    private void removerCliente() {
        System.out.print("ID do cliente para remover: ");
        int id = sc.nextInt(); sc.nextLine();
        if (gerenciador.removerCliente(id)) {
            System.out.println("✔ Cliente removido!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    // ======================
    // FUNCIONÁRIOS
    // ======================
    private void listarFuncionarios() {
        List<Funcionario> lista = gerenciador.listarFuncionarios();
        if (lista.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }
        System.out.println("\n--- LISTA DE FUNCIONÁRIOS ---");
        for (Funcionario f : lista) {
            System.out.println("-----------------------------");
            System.out.println("ID: " + f.getId());
            System.out.println("Nome: " + f.getNome());
            System.out.println("Telefone: " + f.getTelefone());
            System.out.println("Endereço: " + f.getEndereco());
            System.out.println("Email: " + f.getEmail());
            System.out.println("CPF: " + f.getCpf());
            System.out.println("Cargo: " + f.getCargo());
            System.out.println("Salário: R$ " + f.getSalario());
        }
        System.out.println("-----------------------------");
    }

    private void adicionarFuncionario() {
        System.out.print("Nome: "); String nome = sc.nextLine();
        System.out.print("CPF: "); String cpf = sc.nextLine();
        System.out.print("Telefone: "); String telefone = sc.nextLine();
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("Endereço: "); String endereco = sc.nextLine();
        System.out.print("Cargo: "); String cargo = sc.nextLine();
        System.out.print("Salário: "); double salario = sc.nextDouble(); sc.nextLine();
        System.out.print("Login: "); String login = sc.nextLine();
        System.out.print("Senha: "); int senha = sc.nextInt(); sc.nextLine();

        gerenciador.adicionarFuncionario(nome, cpf, telefone, email, endereco, cargo, salario, login, senha);
        System.out.println("✔ Funcionário adicionado!");
    }

    private void buscarFuncionario() {
        System.out.print("ID do funcionário: ");
        int id = sc.nextInt(); sc.nextLine();
        Funcionario f = gerenciador.buscarFuncionarioPorId(id);
        if (f != null) {
            System.out.println(f);
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    private void editarFuncionario() {
        System.out.print("ID do funcionário para editar: ");
        int id = sc.nextInt(); sc.nextLine();
        gerenciador.editarFuncionario(id, sc);
        System.out.println("✔ Funcionário atualizado!");
    }

    private void removerFuncionario() {
        System.out.print("ID do funcionário para remover: ");
        int id = sc.nextInt(); sc.nextLine();
        if (gerenciador.removerFuncionario(id)) {
            System.out.println("✔ Funcionário removido!");
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }
}
