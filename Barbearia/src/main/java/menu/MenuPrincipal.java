package menu;

import modelo.Funcionario;
import modelo.Gerente;
import utils.CRUDGenerico;
import utils.Login;

public class MenuPrincipal {

    // CRUDs separados para funcionários e gerentes
    private CRUDGenerico<Funcionario> crudFuncionarios =
            new CRUDGenerico<>("src/main/java/repositorio/funcionarios.json", Funcionario.class);

    private CRUDGenerico<Gerente> crudGerentes =
            new CRUDGenerico<>("src/main/java/repositorio/gerente.json", Gerente.class);

    // Menus
    private MenuAgendamento menuAgendamento = new MenuAgendamento();
    private MenuPessoa menuPessoa = new MenuPessoa(); // clientes e funcionários
    private MenuServico menuServico = new MenuServico(); // menu de serviços
    private MenuProdutos menuProduto = new MenuProdutos(); // menu de produtos
    private MenuDespesas menuDespesa = new MenuDespesas(); // menu de despesas

    public void iniciarSistema() {
        System.out.println("===== BEM-VINDO À BARBEARIA =====");

        int tipoUsuario = escolherTipoUsuario();
        
        if (tipoUsuario == 1) { // Funcionário
            Login<Funcionario> loginFuncionario = new Login<>(crudFuncionarios);
            Funcionario usuario = null;

            while (usuario == null) {
                usuario = loginFuncionario.autenticar();
            }

            System.out.println(" Login realizado! Bem-vindo(a), " + usuario.getNome());
            menuFuncionario(usuario);

        } else { // Gerente
            Login<Gerente> loginGerente = new Login<>(crudGerentes);
            Gerente gerente = null;

            while (gerente == null) {
                gerente = loginGerente.autenticar();
            }

            System.out.println("✔ Login realizado! Bem-vindo(a), " + gerente.getNome());
            menuGerente(gerente);
        }

        System.out.println("Saindo do sistema...");
    }

    private int escolherTipoUsuario() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        int opc;
        do {
            System.out.print("Você é (1) Funcionário ou (2) Gerente? ");
            opc = sc.nextInt();
        } while (opc != 1 && opc != 2);
        return opc;
    }

    private void menuFuncionario(Funcionario f) {
        int opc;
        java.util.Scanner sc = new java.util.Scanner(System.in);
        do {
            System.out.println("\n--- MENU FUNCIONÁRIO ---");
            System.out.println("1 - Gerenciar Agendamentos");
            System.out.println("2 - Gerenciar Clientes");
            System.out.println("3 - Gerenciar Produtos");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opc = sc.nextInt();

            switch (opc) {
                case 1 -> menuAgendamento.exibirMenu();
                case 2 -> menuPessoa.exibirMenuClientes(); 
                case 3 -> menuProduto.exibirMenu();   // produtos
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opc != 0);
    }

    private void menuGerente(Gerente g) {
        int opc;
        java.util.Scanner sc = new java.util.Scanner(System.in);
        do {
            System.out.println("\n--- MENU GERENTE ---");
            System.out.println("1 - Gerenciar Agendamentos");
            System.out.println("2 - Gerenciar Clientes");
            System.out.println("3 - Gerenciar Funcionários");
            System.out.println("4 - Gerenciar Serviços");
            System.out.println("5 - Gerenciar Produtos");
            System.out.println("6 - Gerenciar Despesas");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opc = sc.nextInt();

            switch (opc) {
                case 1 -> menuAgendamento.exibirMenu();
                case 2 -> menuPessoa.exibirMenuClientes();
                case 3 -> menuPessoa.exibirMenuFuncionarios();
                case 4 -> menuServico.exibirMenu();
                case 5 -> menuProduto.exibirMenu();   
                case 6 -> menuDespesa.exibirMenu();   
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opc != 0);
    }

    public static void main(String[] args) {
        new MenuPrincipal().iniciarSistema();
    }
}
