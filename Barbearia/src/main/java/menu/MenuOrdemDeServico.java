package menu;

import gerenciadores.*;
import modelo.*;

import java.util.List;
import java.util.Scanner;

public class MenuOrdemDeServico {

    private final Scanner sc = new Scanner(System.in);
    private final GerenciadorOrdemDeServico gerenciadorOS;

    public MenuOrdemDeServico(GerenciadorPessoas gerenciadorPessoas,
                              GerenciadorAgendamentos gerenciadorAgendamentos,
                              GerenciadorVendas gerenciadorVendas) {
        this.gerenciadorOS = new GerenciadorOrdemDeServico(
                gerenciadorPessoas,
                gerenciadorAgendamentos,
                gerenciadorVendas
        );
    }

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

            opc = sc.nextInt(); sc.nextLine();

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

    private void listarOS() {
        List<OrdemDeServico> lista = gerenciadorOS.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma OS registrada.");
            return;
        }
        lista.forEach(System.out::println);
    }

    private void criarOS() {
        System.out.print("ID do Cliente: ");
        int idCliente = sc.nextInt(); sc.nextLine();

        OrdemDeServico os = gerenciadorOS.criarOrdem(idCliente);
        System.out.println(os != null ? "OS criada com sucesso!" : "Cliente não encontrado.");
    }

    private void buscarOS() {
        System.out.print("ID da OS: ");
        int id = sc.nextInt(); sc.nextLine();

        OrdemDeServico os = gerenciadorOS.buscarPorId(id);
        System.out.println(os != null ? os : "OS não encontrada.");
    }

    private void adicionarAgendamento() {
        System.out.print("ID da OS: ");
        int idOS = sc.nextInt(); sc.nextLine();
        System.out.print("ID do Agendamento: ");
        int idAg = sc.nextInt(); sc.nextLine();

        boolean sucesso = gerenciadorOS.adicionarAgendamentoNaOS(idOS, idAg);
        System.out.println(sucesso ? "Agendamento adicionado!" : "Falha: OS ou Agendamento não encontrados.");
    }

    private void adicionarVenda() {
        System.out.print("ID da OS: ");
        int idOS = sc.nextInt(); sc.nextLine();
        System.out.print("ID da Venda: ");
        int idVenda = sc.nextInt(); sc.nextLine();

        boolean sucesso = gerenciadorOS.adicionarVendaNaOS(idOS, idVenda);
        System.out.println(sucesso ? "Venda adicionada!" : "Falha: OS ou Venda não encontrados.");
    }


    private void removerOS() {
        System.out.print("ID da OS para remover: ");
        int idOS = sc.nextInt(); sc.nextLine();
        boolean sucesso = gerenciadorOS.remover(idOS);
        System.out.println(sucesso ? "OS removida!" : "OS não encontrada.");
    }

    private void salvarOS() {
        gerenciadorOS.salvar();
        System.out.println("Ordens de serviço salvas!");
    }
}
