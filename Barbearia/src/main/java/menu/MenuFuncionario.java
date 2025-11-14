package menu;

import java.util.Scanner;
import controle.GerenciarPessoas;
import usuarios.Cliente;
import utils.CRUDGenerico;

/**
 * Menu para operações de funcionários no sistema.
 * Permite cadastrar, listar, editar e excluir clientes.
 */
public class MenuFuncionario {

    // Caminho do arquivo JSON de clientes
    private static final String CAMINHO_CLIENTES = "src/main/java/repositorio/Clientes.json";

    // CRUD genérico para clientes
    private final CRUDGenerico<Cliente> crudClientes;

    /**
     * Construtor do menu do funcionário.
     * Inicializa o CRUD genérico para clientes.
     */
    public MenuFuncionario() {
        crudClientes = new CRUDGenerico<>(CAMINHO_CLIENTES, Cliente.class);
    }

    /**
     * Exibe o menu do funcionário e trata as opções.
     */
    public void menuFuncionario() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n------ MENU DO FUNCIONÁRIO ------");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Listar Clientes");
            System.out.println("3 - Editar Cliente");
            System.out.println("4 - Excluir Cliente");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1 -> cadastrarCliente(scanner);
                case 2 -> listarClientes();
                case 3 -> editarCliente(scanner);
                case 4 -> excluirCliente(scanner);
                case 0 -> System.out.println("Saindo do menu do funcionário...");
                default -> System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    /**
     * Cadastra um novo cliente.
     * Os dados são coletados do usuário e passados para o CRUD genérico,
     * que já gera o ID automaticamente.
     *
     * @param scanner Scanner para leitura do usuário
     */
    private void cadastrarCliente(Scanner scanner) {
        System.out.println("\n--- Cadastro de Cliente ---");

        Cliente cliente = new Cliente();

        System.out.print("Nome: ");
        cliente.setNome(scanner.nextLine());

        System.out.print("CPF: ");
        cliente.setCpf(scanner.nextLine());

        System.out.print("Telefone: ");
        cliente.setTelefone(scanner.nextLine());

        System.out.print("Email: ");
        cliente.setEmail(scanner.nextLine());

        System.out.print("Endereço: ");
        cliente.setEndereco(scanner.nextLine());

        // Adiciona o cliente pelo CRUD genérico (gera ID automaticamente)
        crudClientes.adicionar(cliente);

        System.out.println("Cliente cadastrado com sucesso!");
    }

    /**
     * Lista todos os clientes cadastrados.
     */
    private void listarClientes() {
        var clientes = crudClientes.listar();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado!");
            return;
        }

        System.out.println("\n===== Lista de Clientes =====");
        for (Cliente c : clientes) {
            System.out.println("ID: " + c.getId());
            System.out.println("Nome: " + c.getNome());
            System.out.println("CPF: " + c.getCpf());
            System.out.println("Telefone: " + c.getTelefone());
            System.out.println("Email: " + c.getEmail());
            System.out.println("Endereço: " + c.getEndereco());
            System.out.println("-----------------------");
        }
    }

    /**
     * Edita um cliente existente.
     * O usuário escolhe pelo ID e atualiza os campos desejados.
     *
     * @param scanner Scanner para leitura do usuário
     */
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

        System.out.print("Novo nome (" + cliente.getNome() + "): ");
        cliente.setNome(scanner.nextLine());

        System.out.print("Novo CPF (" + cliente.getCpf() + "): ");
        cliente.setCpf(scanner.nextLine());

        System.out.print("Novo telefone (" + cliente.getTelefone() + "): ");
        cliente.setTelefone(scanner.nextLine());

        System.out.print("Novo email (" + cliente.getEmail() + "): ");
        cliente.setEmail(scanner.nextLine());

        System.out.print("Novo endereço (" + cliente.getEndereco() + "): ");
        cliente.setEndereco(scanner.nextLine());

        // Salva as alterações
        crudClientes.editar(cliente.getId(), cliente);

        System.out.println("Cliente atualizado com sucesso!");
    }

    /**
     * Exclui um cliente pelo ID.
     *
     * @param scanner Scanner para leitura do usuário
     */
    private void excluirCliente(Scanner scanner) {
        listarClientes();

        System.out.print("ID do cliente para excluir: ");
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
