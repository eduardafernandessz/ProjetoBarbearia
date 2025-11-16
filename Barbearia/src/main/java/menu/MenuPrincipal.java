package menu;

import gerenciadores.GerenciadorPessoas;
import gerenciadores.GerenciadorProdutos;
import modelo.Funcionario;
import modelo.Gerente;

import java.util.Scanner;

public class MenuPrincipal {

    private GerenciadorPessoas gerenciadorPessoas;
    private GerenciadorProdutos gerenciadorProdutos;

    private MenuAgendamento menuAgendamento = new MenuAgendamento();
    private MenuPessoa menuPessoa = new MenuPessoa();
    private MenuServico menuServico = new MenuServico();
    private MenuDespesas menuDespesa = new MenuDespesas();
    private MenuProdutos menuProduto = new MenuProdutos();
    private MenuVendas menuVendas; // será inicializado no construtor
    private MenuControlePonto menuControlePonto = new MenuControlePonto();

    private Scanner sc = new Scanner(System.in);

    public MenuPrincipal(GerenciadorPessoas gerenciadorPessoas,
                         GerenciadorProdutos gerenciadorProdutos) {

        this.gerenciadorPessoas = gerenciadorPessoas;
        this.gerenciadorProdutos = gerenciadorProdutos;

        // MenuVendas recebe os gerenciadores corretamente
        this.menuVendas = new MenuVendas(gerenciadorPessoas, gerenciadorProdutos);
    }

    public void exibir(Object usuario) {
        if (usuario instanceof Funcionario f) {
            exibirMenuFuncionario(f);
        } else if (usuario instanceof Gerente g) {
            exibirMenuGerente(g);
        }
    }

    // --- MENU FUNCIONÁRIO ---
    private void exibirMenuFuncionario(Funcionario f) {
        int opc;
        do {
            System.out.println("\n--- MENU FUNCIONÁRIO ---");
            System.out.println("1 - Agendamentos");
            System.out.println("2 - Clientes");
            System.out.println("3 - Produtos");
            System.out.println("4 - Registrar Vendas");
            System.out.println("5 - Controle de Ponto");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opc = sc.nextInt(); sc.nextLine();

            switch (opc) {
                case 1 -> menuAgendamento.exibirMenu();
                case 2 -> menuPessoa.exibirMenuClientes();
                case 3 -> menuProduto.exibirMenu();
                case 4 -> menuVendas.exibirMenu();
                case 5 -> menuControlePonto.exibirMenu(f.getId()); // passa o id do funcionário
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opc != 0);
    }

    // --- MENU GERENTE ---
    private void exibirMenuGerente(Gerente g) {
        int opc;
        do {
            System.out.println("\n--- MENU GERENTE ---");
            System.out.println("1 - Agendamentos");
            System.out.println("2 - Clientes");
            System.out.println("3 - Funcionários");
            System.out.println("4 - Serviços");
            System.out.println("5 - Produtos");
            System.out.println("6 - Despesas");
            System.out.println("7 - Registrar Vendas");
            System.out.println("8 - Controle de Ponto");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opc = sc.nextInt(); sc.nextLine();

            switch (opc) {
                case 1 -> menuAgendamento.exibirMenu();
                case 2 -> menuPessoa.exibirMenuClientes();
                case 3 -> menuPessoa.exibirMenuFuncionarios();
                case 4 -> menuServico.exibirMenu();
                case 5 -> menuProduto.exibirMenu();
                case 6 -> menuDespesa.exibirMenu();
                case 7 -> menuVendas.exibirMenu();
                case 8 -> {
                    // Gerente escolhe o funcionário para consultar ou registrar ponto
                    System.out.print("Digite o ID do funcionário: ");
                    int idFunc = sc.nextInt(); sc.nextLine();
                    menuControlePonto.exibirMenu(idFunc); // passa o id do funcionário selecionado
                }
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opc != 0);
    }
}
