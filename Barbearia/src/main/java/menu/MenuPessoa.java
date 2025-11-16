package menu;

import gerenciadores.GerenciadorPessoas;
import modelo.Cliente;
import modelo.Funcionario;

import java.util.List;
import java.util.Scanner;

public class MenuPessoa {

    private final Scanner sc = new Scanner(System.in);
    private final GerenciadorPessoas gerenciador = new GerenciadorPessoas();

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
            opc = sc.nextInt(); sc.nextLine();

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
            opc = sc.nextInt(); sc.nextLine();

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
        System.out.println("Alterações salvas!");
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
            System.out.println(c); // Chama automaticamente o toString() da classe Cliente
            System.out.println("-----------------------------");
        }
    }


    private void adicionarCliente() {
        System.out.print("Nome: "); String nome = sc.nextLine();
        System.out.print("CPF: "); String cpf = sc.nextLine();
        System.out.print("Telefone: "); String telefone = sc.nextLine();
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("Endereço: "); String endereco = sc.nextLine();

        Cliente c = new Cliente(0, nome, cpf, telefone, email, endereco);
        gerenciador.adicionarCliente(c);
        System.out.println("Cliente adicionado!");
    }

    private void buscarCliente() {
        System.out.print("ID do cliente: ");
        int id = sc.nextInt(); sc.nextLine();
        Cliente c = gerenciador.buscarClientePorId(id);
        System.out.println(c != null ? c : "Cliente não encontrado.");
    }

    private void editarCliente() {
        System.out.print("ID do cliente para editar: ");
        int id = sc.nextInt(); sc.nextLine();

        Cliente c = gerenciador.buscarClientePorId(id);
        if (c == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Novo nome (" + c.getNome() + "): ");
        String nome = sc.nextLine(); 
        System.out.print("Novo telefone (" + c.getTelefone() + "): ");
        String telefone = sc.nextLine(); 
        System.out.print("Novo email (" + c.getEmail() + "): ");
        String email = sc.nextLine(); 
        System.out.print("Novo endereço (" + c.getEndereco() + "): ");
        String endereco = sc.nextLine();

        // Prepara objeto atualizado
        Cliente editado = new Cliente(c.getId(),
                nome.isBlank() ? c.getNome() : nome,
                c.getCpf(),
                telefone.isBlank() ? c.getTelefone() : telefone,
                email.isBlank() ? c.getEmail() : email,
                endereco.isBlank() ? c.getEndereco() : endereco);

        boolean ok = gerenciador.editarCliente(editado);
        System.out.println(ok ? "Cliente atualizado!" : "Erro ao atualizar cliente.");
    }

    private void removerCliente() {
        System.out.print("ID do cliente para remover: ");
        int id = sc.nextInt(); sc.nextLine();
        boolean ok = gerenciador.removerCliente(id);
        System.out.println(ok ? "Cliente removido!" : "Cliente não encontrado.");
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
            System.out.println(f); // Chama automaticamente o toString() da classe Funcionario
            System.out.println("-----------------------------");
        }
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

        Funcionario f = new Funcionario(cargo, salario, login, senha, 0, nome, cpf, telefone, email, endereco);
        gerenciador.adicionarFuncionario(f);
        System.out.println("Funcionário adicionado!");
    }

    private void buscarFuncionario() {
        System.out.print("ID do funcionário: ");
        int id = sc.nextInt(); sc.nextLine();
        Funcionario f = gerenciador.buscarFuncionarioPorId(id);
        System.out.println(f != null ? f : "Funcionário não encontrado.");
    }

    private void editarFuncionario() {
        System.out.print("ID do funcionário para editar: ");
        int id = sc.nextInt(); sc.nextLine();

        Funcionario f = gerenciador.buscarFuncionarioPorId(id);
        if (f == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }

        System.out.print("Novo nome (" + f.getNome() + "): ");
        String nome = sc.nextLine();
        System.out.print("Novo cargo (" + f.getCargo() + "): ");
        String cargo = sc.nextLine();
        System.out.print("Novo salário (" + f.getSalario() + "): ");
        String salarioStr = sc.nextLine();
        Double salario = salarioStr.isBlank() ? f.getSalario() : Double.parseDouble(salarioStr);
        System.out.print("Novo telefone (" + f.getTelefone() + "): ");
        String telefone = sc.nextLine();
        System.out.print("Novo email (" + f.getEmail() + "): ");
        String email = sc.nextLine();
        System.out.print("Novo endereço (" + f.getEndereco() + "): ");
        String endereco = sc.nextLine();
        System.out.print("Novo login (" + f.getLogin() + "): ");
        String login = sc.nextLine();
        System.out.print("Nova senha (" + f.getSenha() + "): ");
        String senhaStr = sc.nextLine();
        Integer senha = senhaStr.isBlank() ? f.getSenha() : Integer.parseInt(senhaStr);

        // Prepara objeto atualizado
        Funcionario editado = new Funcionario(
                cargo.isBlank() ? f.getCargo() : cargo,
                salario,
                login.isBlank() ? f.getLogin() : login,
                senha,
                f.getId(),
                nome.isBlank() ? f.getNome() : nome,
                f.getCpf(),
                telefone.isBlank() ? f.getTelefone() : telefone,
                email.isBlank() ? f.getEmail() : email,
                endereco.isBlank() ? f.getEndereco() : endereco
        );

        boolean ok = gerenciador.editarFuncionario(editado);
        System.out.println(ok ? "Funcionário atualizado!" : "Erro ao atualizar funcionário.");
    }

    private void removerFuncionario() {
        System.out.print("ID do funcionário para remover: ");
        int id = sc.nextInt(); sc.nextLine();
        boolean ok = gerenciador.removerFuncionario(id);
        System.out.println(ok ? "Funcionário removido!" : "Funcionário não encontrado.");
    }
}
