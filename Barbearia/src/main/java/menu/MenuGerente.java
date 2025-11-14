package menu;

import java.util.Scanner;
import usuarios.Cliente;
import usuarios.Funcionario;
import usuarios.Gerente;
import utils.CRUDGenerico;

/**
 * Menu para operações do gerente no sistema.
 * Permite gerenciar clientes, funcionários e outros gerentes.
 */
public class MenuGerente {

    private static final String CAMINHO_CLIENTES = "src/main/java/repositorio/Clientes.json";
    private static final String CAMINHO_FUNCIONARIOS = "src/main/java/repositorio/Funcionarios.json";
    private static final String CAMINHO_GERENTES = "src/main/java/repositorio/Gerentes.json";

    private final CRUDGenerico<Cliente> crudClientes;
    private final CRUDGenerico<Funcionario> crudFuncionarios;
    private final CRUDGenerico<Gerente> crudGerentes;

    /**
     * Construtor do menu do gerente.
     * Inicializa os CRUDs genéricos.
     */
    public MenuGerente() {
        crudClientes = new CRUDGenerico<>(CAMINHO_CLIENTES, Cliente.class);
        crudFuncionarios = new CRUDGenerico<>(CAMINHO_FUNCIONARIOS, Funcionario.class);
        crudGerentes = new CRUDGenerico<>(CAMINHO_GERENTES, Gerente.class);
    }

    /**
     * Exibe o menu principal do gerente e trata as opções.
     */
    public void menuGerente() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n------ MENU DO GERENTE ------");
            System.out.println("1 - Gerenciar Clientes");
            System.out.println("2 - Gerenciar Funcionários");
            System.out.println("3 - Gerenciar Gerentes");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1 -> menuCRUDClientes(scanner);
                case 2 -> menuCRUDFuncionarios(scanner);
                case 3 -> menuCRUDGerentes(scanner);
                case 0 -> System.out.println("Saindo do menu do gerente...");
                default -> System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    /** -------------------- MENUS ESPECÍFICOS -------------------- */

    private void menuCRUDClientes(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n--- Gerenciar Clientes ---");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Listar Clientes");
            System.out.println("3 - Editar Cliente");
            System.out.println("4 - Excluir Cliente");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarCliente(scanner);
                case 2 -> listarClientes();
                case 3 -> editarCliente(scanner);
                case 4 -> excluirCliente(scanner);
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void menuCRUDFuncionarios(Scanner scanner) {
        System.out.println("Menu de funcionários ainda precisa ser implementado");
        // Mesma lógica do menu de clientes, mas para Funcionario
    }

    private void menuCRUDGerentes(Scanner scanner) {
        System.out.println("Menu de gerentes ainda precisa ser implementado");
        // Mesma lógica do menu de clientes, mas para Gerente
    }

    /** -------------------- MÉTODOS CRUD CLIENTE -------------------- */

    private void cadastrarCliente(Scanner scanner) {
        System.out.println("\n--- Cadastro de Cliente ---");
        Cliente cliente = new Cliente();

        System.out.print("Nome: "); cliente.setNome(scanner.nextLine());
        System.out.print("CPF: "); cliente.setCpf(scanner.nextLine());
        System.out.print("Telefone: "); cliente.setTelefone(scanner.nextLine());
        System.out.print("Email: "); cliente.setEmail(scanner.nextLine());
        System.out.print("Endereço: "); cliente.setEndereco(scanner.nextLine());

        crudClientes.adicionar(cliente); // gera ID automaticamente
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private void listarClientes() {
        var lista = crudClientes.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado!");
            return;
        }

        System.out.println("\n===== Lista de Clientes =====");
        for (Cliente c : lista) {
            System.out.println("ID: " + c.getId());
            System.out.println("Nome: " + c.getNome());
            System.out.println("CPF: " + c.getCpf());
            System.out.println("Telefone: " + c.getTelefone());
            System.out.println("Email: " + c.getEmail());
            System.out.println("Endereço: " + c.getEndereco());
            System.out.println("-----------------------");
        }
    }

    private void editarCliente(Scanner scanner) {
        listarClientes();
        System.out.print("Digite o ID do cliente a editar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Cliente cliente = crudClientes.buscarPorId(id);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Novo nome (" + cliente.getNome() + "): "); cliente.setNome(scanner.nextLine());
        System.out.print("Novo CPF (" + cliente.getCpf() + "): "); cliente.setCpf(scanner.nextLine());
        System.out.print("Novo telefone (" + cliente.getTelefone() + "): "); cliente.setTelefone(scanner.nextLine());
        System.out.print("Novo email (" + cliente.getEmail() + "): "); cliente.setEmail(scanner.nextLine());
        System.out.print("Novo endereço (" + cliente.getEndereco() + "): "); cliente.setEndereco(scanner.nextLine());

        crudClientes.editar(cliente.getId(), cliente);
        System.out.println("Cliente atualizado com sucesso!");
    }

    private void excluirCliente(Scanner scanner) {
        listarClientes();
        System.out.print("Digite o ID do cliente a excluir: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Cliente cliente = crudClientes.buscarPorId(id);
        if (cliente != null) {
            crudClientes.remover(cliente);
            System.out.println("Cliente removido com sucesso!");
        } else {
            System.out.println("ID não encontrado.");
        }
    }
}
