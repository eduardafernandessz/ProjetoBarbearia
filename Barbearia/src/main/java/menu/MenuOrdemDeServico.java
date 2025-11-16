package menu;

import gerenciadores.*;
import modelo.*;

import java.util.List;
import java.util.Scanner;

/**
 * Menu responsável por exibir as operações relacionadas às
 * Ordens de Serviço (OS) do sistema, permitindo criar, listar,
 * editar, buscar, remover e salvar OS.
 *
 * Este menu interage diretamente com os gerenciadores de Pessoas,
 * Agendamentos e Vendas por meio do GerenciadorOrdemDeServico.
 */
public class MenuOrdemDeServico {

    /** Scanner usado para entrada de dados do usuário. */
    private final Scanner sc = new Scanner(System.in);

    /** Gerenciador responsável pelas operações da Ordem de Serviço. */
    private final GerenciadorOrdemDeServico gerenciadorOS;

    /**
     * Construtor do menu de OS.
     *
     * @param gerenciadorPessoas        Gerenciador de clientes e funcionários.
     * @param gerenciadorAgendamentos   Gerenciador de agendamentos.
     * @param gerenciadorVendas         Gerenciador de vendas.
     */
    public MenuOrdemDeServico(GerenciadorPessoas gerenciadorPessoas,
                              GerenciadorAgendamentos gerenciadorAgendamentos,
                              GerenciadorVendas gerenciadorVendas) {
        this.gerenciadorOS = new GerenciadorOrdemDeServico(
                gerenciadorPessoas,
                gerenciadorAgendamentos,
                gerenciadorVendas
        );
    }

    /**
     * Exibe o menu principal de operações da Ordem de Serviço,
     * permitindo que o usuário navegue entre as opções.
     */
    public void exibirMenu() {
        int opc;
        do {
            System.out.println("\n--- MENU ORDEM DE SERVIÇO ---");
            System.out.println("1 - Listar Ordens de Serviço");
            System.out.println("2 - Criar Ordem de Serviço");
            System.out.println("3 - Buscar OS por ID");
            System.out.println("4 - Adicionar Agendamento à OS");
            System.out.println("5 - Adicionar Venda à OS");
            System.out.println("6 - Fechar OS");
            System.out.println("7 - Remover OS");
            System.out.println("8 - Salvar OS");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1 -> listarOS();
                case 2 -> criarOS();
                case 3 -> buscarOS();
                case 4 -> adicionarAgendamento();
                case 5 -> adicionarVenda();
                case 7 -> removerOS();
                case 8 -> salvarOS();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }

        } while (opc != 0);
    }

    /**
     * Lista todas as Ordens de Serviço registradas no sistema.
     */
    private void listarOS() {
        List<OrdemDeServico> lista = gerenciadorOS.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma OS registrada.");
            return;
        }
        lista.forEach(System.out::println);
    }

    /**
     * Cria uma nova Ordem de Serviço vinculada a um cliente.
     */
    private void criarOS() {
        System.out.print("ID do Cliente: ");
        int idCliente = sc.nextInt();
        sc.nextLine();

        OrdemDeServico os = gerenciadorOS.criarOrdem(idCliente);
        System.out.println(os != null ? "OS criada com sucesso!" : "Cliente não encontrado.");
    }

    /**
     * Busca uma Ordem de Serviço pelo ID informado.
     */
    private void buscarOS() {
        System.out.print("ID da OS: ");
        int id = sc.nextInt();
        sc.nextLine();

        OrdemDeServico os = gerenciadorOS.buscarPorId(id);
        System.out.println(os != null ? os : "OS não encontrada.");
    }

    /**
     * Adiciona um agendamento existente a uma Ordem de Serviço específica.
     */
    private void adicionarAgendamento() {
        System.out.print("ID da OS: ");
        int idOS = sc.nextInt();
        sc.nextLine();

        System.out.print("ID do Agendamento: ");
        int idAg = sc.nextInt();
        sc.nextLine();

        boolean sucesso = gerenciadorOS.adicionarAgendamentoNaOS(idOS, idAg);
        System.out.println(sucesso ? "Agendamento adicionado!" : "Falha: OS ou Agendamento não encontrados.");
    }

    /**
     * Adiciona uma venda existente a uma Ordem de Serviço específica.
     */
    private void adicionarVenda() {
        System.out.print("ID da OS: ");
        int idOS = sc.nextInt();
        sc.nextLine();

        System.out.print("ID da Venda: ");
        int idVenda = sc.nextInt();
        sc.nextLine();

        boolean sucesso = gerenciadorOS.adicionarVendaNaOS(idOS, idVenda);
        System.out.println(sucesso ? "Venda adicionada!" : "Falha: OS ou Venda não encontrados.");
    }

    /**
     * Remove uma Ordem de Serviço pelo ID.
     */
    private void removerOS() {
        System.out.print("ID da OS para remover: ");
        int idOS = sc.nextInt();
        sc.nextLine();

        boolean sucesso = gerenciadorOS.remover(idOS);
        System.out.println(sucesso ? "OS removida!" : "OS não encontrada.");
    }

    /**
     * Salva todas as Ordens de Serviço em arquivo.
     */
    private void salvarOS() {
        gerenciadorOS.salvar();
        System.out.println("Ordens de serviço salvas!");
    }
}
