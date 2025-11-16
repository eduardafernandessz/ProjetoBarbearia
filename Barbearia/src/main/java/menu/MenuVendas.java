package menu;

import gerenciadores.GerenciadorVendas;
import gerenciadores.GerenciadorPessoas;
import gerenciadores.GerenciadorProdutos;
import modelo.Venda;

import java.util.List;
import java.util.Scanner;

public class MenuVendas {

    private final Scanner sc = new Scanner(System.in);
    private final GerenciadorVendas gerenciadorVendas;

    public MenuVendas(GerenciadorPessoas gerenciadorPessoas, GerenciadorProdutos gerenciadorProdutos) {
        this.gerenciadorVendas = new GerenciadorVendas(gerenciadorPessoas, gerenciadorProdutos);
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
            return;
        }

        System.out.println("\n--- LISTA DE VENDAS ---");
        for (Venda v : lista) {
            System.out.println(v); // Agora usa o toString() do objeto Venda
        }
        System.out.println("---------------------------");
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
                ? " Venda registrada! Estoque atualizado."
                : " Falha ao registrar venda. Verifique IDs e estoque disponível.");
    }

    // --- BUSCAR VENDA ---
    private void buscarVenda() {
        System.out.print("ID da venda: ");
        int id = sc.nextInt(); sc.nextLine();

        Venda v = gerenciadorVendas.buscarPorId(id);
        if (v == null) {
            System.out.println("Venda não encontrada.");
            return;
        }
        System.out.println(v);
    }

    // --- EDITAR VENDA ---
    private void editarVenda() {
        System.out.print("ID da venda para editar: ");
        int id = sc.nextInt(); sc.nextLine();

        Venda v = gerenciadorVendas.buscarPorId(id);
        if (v == null) {
            System.out.println("Venda não encontrada.");
            return;
        }

        // --- CLIENTE ---
        System.out.print("ID do Cliente (" + v.getCliente().getId() + "): ");
        String entradaCliente = sc.nextLine();
        int novoIdCliente = entradaCliente.isEmpty() ? v.getCliente().getId() : Integer.parseInt(entradaCliente);

        // --- PRODUTO ---
        System.out.print("ID do Produto (" + v.getProduto().getId() + "): ");
        String entradaProduto = sc.nextLine();
        int novoIdProduto = entradaProduto.isEmpty() ? v.getProduto().getId() : Integer.parseInt(entradaProduto);

        // --- QUANTIDADE ---
        System.out.print("Quantidade (" + v.getQuantidade() + "): ");
        String entradaQtd = sc.nextLine();
        int novaQtd = entradaQtd.isEmpty() ? v.getQuantidade() : Integer.parseInt(entradaQtd);

        boolean sucesso = gerenciadorVendas.editarVenda(id, novoIdCliente, novoIdProduto, novaQtd);
        System.out.println(sucesso ? " Venda atualizada!" : " Falha ao atualizar venda. Verifique IDs e estoque.");
    }

    // --- REMOVER VENDA ---
    private void removerVenda() {
        System.out.print("ID da venda para remover: ");
        int id = sc.nextInt(); sc.nextLine();

        boolean sucesso = gerenciadorVendas.removerVenda(id);
        System.out.println(sucesso ? " Venda removida e estoque reposto!" : " Venda não encontrada.");
    }

    // --- SALVAR VENDAS ---
    private void salvarVendas() {
        gerenciadorVendas.salvar();
        System.out.println(" Vendas salvas com sucesso!");
    }
}
