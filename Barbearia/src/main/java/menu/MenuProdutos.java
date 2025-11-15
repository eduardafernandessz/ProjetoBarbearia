package menu;

import java.util.Scanner;
import modelo.Produto;
import gerenciadores.GerenciadorProdutos;
import modelo.Produto;

public class MenuProdutos {

    private GerenciadorProdutos gerenciador;
    private Scanner sc;

    public MenuProdutos() {
        gerenciador = new GerenciadorProdutos();
        sc = new Scanner(System.in);
    }

    // ==========================================================
    // MENU PRINCIPAL
    // ==========================================================
    public void exibirMenu() {
        int op;

        do {
            System.out.println("\n===== MENU DE PRODUTOS =====");
            System.out.println("1. Adicionar produto");
            System.out.println("2. Editar produto");
            System.out.println("3. Remover produto");
            System.out.println("4. Listar produtos");
            System.out.println("5. Salvar alterações");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> adicionar();
                case 2 -> editar();
                case 3 -> remover();
                case 4 -> listar();
                case 5 -> gerenciador.salvar();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }

        } while (op != 0);
    }

    // ==========================================================
    // ADICIONAR
    // ==========================================================
    private void adicionar() {
        System.out.println("\n=== ADICIONAR PRODUTO ===");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Preço de venda: ");
        double preco = sc.nextDouble();

        System.out.print("Qtd em estoque: ");
        int quantidadeEstoque = sc.nextInt();
        sc.nextLine();

        Produto p = new Produto();
        p.setNome(nome);
        p.setPreco(preco);
        p.setQuantidadeEstoque(quantidadeEstoque);

        gerenciador.adicionar(p);

        System.out.println(" Produto adicionado!");
    }

    // ==========================================================
    // EDITAR
    // ==========================================================
    private void editar() {
        System.out.println("\n=== EDITAR PRODUTO ===");

        System.out.print("ID do produto: ");
        int id = sc.nextInt();
        sc.nextLine();

        Produto existente = gerenciador.buscarPorId(id);

        if (existente == null) {
            System.out.println("❌ Produto não encontrado.");
            return;
        }

        System.out.print("Novo nome (" + existente.getNome() + "): ");
        String nome = sc.nextLine();

        System.out.print("Novo preço venda (" + existente.getPreco() + "): ");
        double preco = sc.nextDouble();

        System.out.print("Nova quantidade (" + existente.getQuantidadeEstoque() + "): ");
        int quantidadeEstoque = sc.nextInt();
        sc.nextLine();

        Produto novo = new Produto();
        novo.setId(id); // manter id
        novo.setNome(nome);
        novo.setPreco(preco);
        novo.setQuantidadeEstoque(quantidadeEstoque);

        if (gerenciador.editar(id, novo))
            System.out.println("✔ Produto editado!");
        else
            System.out.println("❌ Erro ao editar.");
    }

    // ==========================================================
    // REMOVER
    // ==========================================================
    private void remover() {
        System.out.println("\n=== REMOVER PRODUTO ===");
        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        gerenciador.remover(id);
        System.out.println(" Produto removido!");
    }

    // ==========================================================
    // LISTAR
    // ==========================================================
    private void listar() {
        System.out.println("\n=== LISTA DE PRODUTOS ===");

        var lista = gerenciador.listar();

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
}
