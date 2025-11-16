package menu;

import java.util.List;
import java.util.Scanner;
import modelo.Produto;
import gerenciadores.GerenciadorProdutos;

/**
 * Menu responsável por realizar operações relacionadas aos produtos do sistema.
 * Permite listar, adicionar, buscar, editar, remover e salvar produtos.
 */
public class MenuProdutos {

    /** Gerenciador responsável por manipular os produtos. */
    private final GerenciadorProdutos gerenciador = new GerenciadorProdutos();

    /** Scanner utilizado para leitura das entradas do usuário. */
    private final Scanner sc = new Scanner(System.in);

    /**
     * Exibe o menu principal de produtos e controla a navegação entre as opções.
     */
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

    /**
     * Lista todos os produtos cadastrados no sistema.
     */
    private void listarProdutos() {
        List<Produto> lista = gerenciador.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.println("\n--- LISTA DE PRODUTOS ---");
        for (Produto p : lista) {
            System.out.println("-----------------------------");
            System.out.println(p); // Chama automaticamente o toString()
        }
        System.out.println("-----------------------------");
    }

    /**
     * Adiciona um novo produto ao sistema, pedindo os dados ao usuário.
     */
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

    /**
     * Busca um produto pelo ID informado pelo usuário.
     */
    private void buscarProduto() {
        System.out.print("ID do produto: ");
        int id = Integer.parseInt(sc.nextLine());

        Produto p = gerenciador.buscarProdutoPorId(id);
        if (p != null) {
            System.out.println("-----------------------------");
            System.out.println(p);
            System.out.println("-----------------------------");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    /**
     * Edita as informações de um produto existente.
     */
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
            System.out.println(atualizado);
            System.out.println("✔ Produto atualizado!");
        } else {
            System.out.println("❌ Erro ao atualizar produto.");
        }
    }

    /**
     * Remove um produto do sistema pelo ID.
     */
    private void removerProduto() {
        System.out.print("ID do produto para remover: ");
        int id = Integer.parseInt(sc.nextLine());

        if (gerenciador.remover(id)) {
            System.out.println("✔ Produto removido!");
        } else {
            System.out.println("❌ Produto não encontrado.");
        }
    }

    /**
     * Salva as alterações feitas na lista de produtos.
     */
    private void salvarProdutos() {
        gerenciador.salvar();
        System.out.println("✔ Produtos salvos!");
    }
}
