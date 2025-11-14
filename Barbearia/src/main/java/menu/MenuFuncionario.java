package menu;

import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.JSONUtils;

public class MenuFuncionario {
    private static final String CAMINHO_CLIENTES = "src/main/java/repositorio/Clientes.json";
    
    public void menuFuncionario() {
         Scanner scanner = new Scanner(System.in);
         int opcao = 0;

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

    } while (opcao != 0);}
    
    private int gerarId(String caminho) {
            // Lê os dados existentes do arquivo JSON
            JSONArray dados = JSONUtils.lerArquivo(caminho);

            int maiorId = 0;

            // Percorre todos os objetos do JSON para encontrar o maior ID já usado
            for (int i = 0; i < dados.length(); i++) {
                JSONObject obj = dados.getJSONObject(i);
                int idAtual = obj.getInt("id");

                if (idAtual > maiorId) {
                    maiorId = idAtual;
                }
            }

            // Retorna o próximo ID disponível
            return maiorId + 1;
    }
            
    public void cadastrarCliente(Scanner scanner) {
        System.out.println("\n--- Cadastro de Cliente ---");

        JSONObject cliente = new JSONObject();
        cliente.put("tipo", "Cliente");

        System.out.print("Nome: ");
        cliente.put("nome", scanner.nextLine());

        System.out.print("CPF: ");
        cliente.put("cpf", scanner.nextLine());

        System.out.print("Telefone: ");
        cliente.put("telefone", scanner.nextLine());

        System.out.print("Email: ");
        cliente.put("email", scanner.nextLine());

        System.out.print("Endereço: ");
        cliente.put("endereco", scanner.nextLine());

        cliente.put("id", gerarId(CAMINHO_CLIENTES));

        JSONArray clientes = JSONUtils.lerArquivo(CAMINHO_CLIENTES);
        clientes.put(cliente);
        JSONUtils.salvarArquivo(CAMINHO_CLIENTES, clientes);

        System.out.println("Cliente cadastrado com sucesso!");
    }
    
    public void listarClientes() {

        JSONArray clientes = JSONUtils.lerArquivo(CAMINHO_CLIENTES);

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado!");
            return;
        }

        System.out.println("\n===== Lista de Clientes =====");
        for (int i = 0; i < clientes.length(); i++) {
            JSONObject c = clientes.getJSONObject(i);
            System.out.println("ID: " + c.getInt("id"));
            System.out.println("Nome: " + c.getString("nome"));
            System.out.println("CPF: " + c.getString("cpf"));
            System.out.println("Telefone: " + c.getString("telefone"));
            System.out.println("Email: " + c.getString("email"));
            System.out.println("Endereço: " + c.getString("endereco"));
            System.out.println("-----------------------");
          }
        }
    
    public void editarCliente(Scanner scanner) {

            JSONArray clientes = JSONUtils.lerArquivo(CAMINHO_CLIENTES);

            if (clientes.isEmpty()) {
                System.out.println("Nenhum cliente encontrado.");
                return;
            }

            System.out.println("\n--- Lista de Clientes ---");
            for (int i = 0; i < clientes.length(); i++) {
                JSONObject c = clientes.getJSONObject(i);
                System.out.println("ID: " + c.getInt("id") + " | Nome: " + c.getString("nome"));
            }

            System.out.print("Digite o ID do cliente a editar: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            JSONObject cliente = null;
            for (int i = 0; i < clientes.length(); i++) {
                if (clientes.getJSONObject(i).getInt("id") == id) {
                    cliente = clientes.getJSONObject(i);
                    break;
                }
            }

            if (cliente == null) {
                System.out.println("Cliente não encontrado.");
                return;
            }

            System.out.print("Novo nome (" + cliente.getString("nome") + "): ");
            cliente.put("nome", scanner.nextLine());

            System.out.print("Novo CPF (" + cliente.getString("cpf") + "): ");
            cliente.put("cpf", scanner.nextLine());

            System.out.print("Novo telefone (" + cliente.getString("telefone") + "): ");
            cliente.put("telefone", scanner.nextLine());

            System.out.print("Novo email (" + cliente.getString("email") + "): ");
            cliente.put("email", scanner.nextLine());

            System.out.print("Novo endereço (" + cliente.getString("endereco") + "): ");
            cliente.put("endereco", scanner.nextLine());

            JSONUtils.salvarArquivo(CAMINHO_CLIENTES, clientes);

            System.out.println("Cliente atualizado com sucesso!");
            }
    
    public void excluirCliente(Scanner scanner) {

    JSONArray clientes = JSONUtils.lerArquivo(CAMINHO_CLIENTES);

    if (clientes.isEmpty()) {
        System.out.println("Nenhum cliente encontrado.");
        return;
    }

    System.out.println("\n--- Lista de Clientes ---");
    for (int i = 0; i < clientes.length(); i++) {
        JSONObject c = clientes.getJSONObject(i);
        System.out.println("ID: " + c.getInt("id") + " | Nome: " + c.getString("nome"));
    }

    System.out.print("ID do cliente para excluir: ");
    int id = scanner.nextInt();
    scanner.nextLine();

    boolean removido = false;

    for (int i = 0; i < clientes.length(); i++) {
        if (clientes.getJSONObject(i).getInt("id") == id) {
            clientes.remove(i);
            removido = true;
            break;
        }
    }

    if (removido) {
        JSONUtils.salvarArquivo(CAMINHO_CLIENTES, clientes);
        System.out.println("Cliente removido com sucesso!");
    } else {
        System.out.println("ID não encontrado.");
    }
    }
}