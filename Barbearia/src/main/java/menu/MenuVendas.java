package menu;

import gerenciadores.GerenciadorVendas;
import gerenciadores.GerenciadorPessoas;
import gerenciadores.GerenciadorProdutos;
import modelo.Venda;

import java.util.List;
import java.util.Scanner;

public class MenuVendas {

    private Scanner sc = new Scanner(System.in);
    private GerenciadorVendas gerenciadorVendas;

    // Construtor que inicializa o gerenciador de vendas
    public MenuVendas(GerenciadorPessoas gerPessoas, GerenciadorProdutos gerProdutos) {
        this.gerenciadorVendas = new GerenciadorVendas(gerPessoas, gerProdutos);
    }

    // --- MENU PRINCIPAL ---
    public void exibirMenu() {
        int opc;
        do {
            System.out.println("\n--- MENU VENDAS ---");
            System.out.println("1 - Listar Vendas");
            System.out.println("2 - Registrar Venda");
            System.out.println("3 - Buscar Venda por ID");
            System.out.println("4 - Editar Venda");
            System.out.println("5 - Remover Venda");
            System.out.println("6 - Salvar Vendas");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opc = sc.nextInt(); sc.nextLine();

            switch (opc) {
                case 1 -> listarVendas();
                case 2 -> registrarVenda();
                case 3 -> buscarVenda();
                case 4 -> editarVenda();
                case 5 -> removerVenda();
                case 6 -> salvarVendas();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opc != 0);
    }

    // --- LISTAR VENDAS ---
    private void listarVendas() {
        List<Venda> lista = gerenciadorVendas.listarVendas();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
        } else {
            for (Venda v : lista) {
                System.out.println("ID: " + v.getId());
                System.out.println("Cliente: " + v.getCliente().getNome());
                System.out.println("Produto: " + v.getProduto().getNome());
                System.out.println("Quantidade: " + v.getQuantidade());
                System.out.println("Data: " + v.getData());
                System.out.println("---------------------------");
            }
        }
    }

    // --- REGISTRAR VENDA ---
    private void registrarVenda() {
        System.out.print("ID do Cliente: ");
        int idCliente = sc.nextInt(); sc.nextLine();

        System.out.print("ID do Produto: ");
        int idProduto = sc.nextInt(); sc.nextLine();

        System.out.print("Quantidade: ");
        int quantidade = sc.nextInt(); sc.nextLine();

        boolean sucesso = gerenciadorVendas.adicionarVenda(idCliente, idProduto, quantidade);
        System.out.println(sucesso
                ? "✔ Venda registrada! Estoque atualizado em memória."
                : "❌ Falha ao registrar venda. Verifique IDs e estoque disponível.");
    }

    // --- BUSCAR VENDA ---
    private void buscarVenda() {
        System.out.print("ID da venda: ");
        int id = sc.nextInt(); sc.nextLine();
        Venda v = gerenciadorVendas.buscarVendaPorId(id);
        if (v != null) {
            System.out.println("ID: " + v.getId());
            System.out.println("Cliente: " + v.getCliente().getNome());
            System.out.println("Produto: " + v.getProduto().getNome());
            System.out.println("Quantidade: " + v.getQuantidade());
            System.out.println("Data: " + v.getData());
        } else {
            System.out.println("Venda não encontrada.");
        }
    }

    // --- EDITAR VENDA ---
    private void editarVenda() {
        System.out.print("ID da venda para editar: ");
        int id = sc.nextInt(); sc.nextLine();

        System.out.print("Novo ID do Cliente: ");
        int idCliente = sc.nextInt(); sc.nextLine();

        System.out.print("Novo ID do Produto: ");
        int idProduto = sc.nextInt(); sc.nextLine();

        System.out.print("Nova quantidade: ");
        int quantidade = sc.nextInt(); sc.nextLine();

        boolean sucesso = gerenciadorVendas.editarVenda(id, idCliente, idProduto, quantidade);
        System.out.println(sucesso ? "✔ Venda atualizada!" : "❌ Falha ao atualizar venda. Verifique IDs e estoque.");
    }

    // --- REMOVER VENDA ---
    private void removerVenda() {
        System.out.print("ID da venda para remover: ");
        int id = sc.nextInt(); sc.nextLine();

        boolean sucesso = gerenciadorVendas.removerVenda(id);
        System.out.println(sucesso ? "✔ Venda removida e estoque reposto!" : "❌ Venda não encontrada.");
    }

    // --- SALVAR VENDAS ---
    private void salvarVendas() {
        gerenciadorVendas.salvar();
        System.out.println("✔ Vendas salvas com sucesso!");
    }
}
