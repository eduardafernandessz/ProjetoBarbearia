package menu;

import java.util.List;
import java.util.Scanner;
import modelo.Produto;
import gerenciadores.GerenciadorProdutos;

public class MenuProdutos {

    private final GerenciadorProdutos gerenciador = new GerenciadorProdutos();
    private final Scanner sc = new Scanner(System.in);

    // ===========================
    // MENU PRINCIPAL
    // ===========================
    public void exibirMenu() {
        int op;
        do {
            System.out.println("\n===== MENU DE PRODUTOS =====");
            System.out.println("1 - Listar produtos");
            System.out.println("2 - Adicionar produto");
            System.out.println("3 - Buscar produto por ID");
            System.out.println("4 - Editar produto");
            System.out.println("5 - Remover produto");
            System.out.println("6 - Salvar alterações");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 -> listarProdutos();
                case 2 -> adicionarProduto();
                case 3 -> buscarProduto();
                case 4 -> editarProduto();
                case 5 -> removerProduto();
                case 6 -> salvarProdutos();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (op != 0);
    }

    // ===========================
    // LISTAR
    // ===========================
    private void listarProdutos() {
        List<Produto> lista = gerenciador.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        for (Produto p : lista) {
            System.out.println("-----------------------------");
            System.out.println("ID: " + p.getId());
            System.out.println("Nome: " + p.getNome());
            System.out.println("Preço Venda: R$ " + p.getPreco());
            System.out.println("Estoque: " + p.getQuantidadeEstoque());
        }
        System.out.println("-----------------------------");
    }

    // ===========================
    // ADICIONAR
    // ===========================
    private void adicionarProduto() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Preço de venda: ");
        double preco = Double.parseDouble(sc.nextLine());

        System.out.print("Quantidade em estoque: ");
        int quantidadeEstoque = Integer.parseInt(sc.nextLine());

        Produto p = new Produto();
        p.setNome(nome);
        p.setPreco(preco);
        p.setQuantidadeEstoque(quantidadeEstoque);

        gerenciador.adicionar(p);
        System.out.println("✔ Produto adicionado!");
    }

    // ===========================
    // BUSCAR
    // ===========================
    private void buscarProduto() {
        System.out.print("ID do produto: ");
        int id = Integer.parseInt(sc.nextLine());

        Produto p = gerenciador.buscarProdutoPorId(id);
        if (p != null) {
            imprimirProduto(p);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    // ===========================
    // EDITAR
    // ===========================
    private void editarProduto() {
        System.out.print("ID do produto para editar: ");
        int id = Integer.parseInt(sc.nextLine());

        Produto existente = gerenciador.buscarProdutoPorId(id);
        if (existente == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Novo nome (" + existente.getNome() + "): ");
        String nome = sc.nextLine();
        if (nome.isBlank()) nome = existente.getNome();

        System.out.print("Novo preço (" + existente.getPreco() + "): ");
        String precoStr = sc.nextLine();
        double preco = precoStr.isBlank() ? existente.getPreco() : Double.parseDouble(precoStr);

        System.out.print("Nova quantidade (" + existente.getQuantidadeEstoque() + "): ");
        String qtdStr = sc.nextLine();
        int quantidadeEstoque = qtdStr.isBlank() ? existente.getQuantidadeEstoque() : Integer.parseInt(qtdStr);

        Produto atualizado = new Produto();
        atualizado.setId(id);
        atualizado.setNome(nome);
        atualizado.setPreco(preco);
        atualizado.setQuantidadeEstoque(quantidadeEstoque);

        if (gerenciador.editar(id, atualizado)) {
            System.out.println("✔ Produto atualizado!");
        } else {
            System.out.println("❌ Erro ao atualizar produto.");
        }
    }

    // ===========================
    // REMOVER
    // ===========================
    private void removerProduto() {
        System.out.print("ID do produto para remover: ");
        int id = Integer.parseInt(sc.nextLine());

        if (gerenciador.remover(id)) {
            System.out.println("✔ Produto removido!");
        } else {
            System.out.println("❌ Produto não encontrado.");
        }
    }

    // ===========================
    // SALVAR
    // ===========================
    private void salvarProdutos() {
        gerenciador.salvar();
        System.out.println("✔ Produtos salvos!");
    }

    // ===========================
    // IMPRIMIR PRODUTO
    // ===========================
    private void imprimirProduto(Produto p) {
        System.out.println("-----------------------------");
        System.out.println("ID: " + p.getId());
        System.out.println("Nome: " + p.getNome());
        System.out.println("Preço Venda: R$ " + p.getPreco());
        System.out.println("Estoque: " + p.getQuantidadeEstoque());
        System.out.println("-----------------------------");
    }
}
