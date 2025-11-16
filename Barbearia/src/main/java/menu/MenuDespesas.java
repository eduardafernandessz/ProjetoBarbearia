package menu;

import gerenciadores.GerenciadorDespesas;
import modelo.Despesas;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Classe responsável por exibir o menu de gerenciamento de despesas
 * e permitir operações como listar, adicionar, editar, buscar e remover
 * despesas cadastradas no sistema.
 *
 * <p>Essa classe funciona como interface de interação com o usuário,
 * utilizando o console para leitura de entradas e exibição de resultados.</p>
 */
public class MenuDespesas {

    /** Scanner utilizado para ler entradas do usuário pelo console */
    private final Scanner sc = new Scanner(System.in);

    /** Gerenciador responsável por manipular os dados das despesas */
    private final GerenciadorDespesas gerenciador = new GerenciadorDespesas();

    /**
     * Exibe o menu principal de despesas e controla o fluxo das opções escolhidas.
     * <p>Permite ao usuário realizar operações CRUD sobre despesas.</p>
     */
    public void exibirMenu() {
        int opc;
        do {
            System.out.println("\n--- MENU DESPESAS ---");
            System.out.println("1 - Listar Despesas");
            System.out.println("2 - Adicionar Despesa");
            System.out.println("3 - Buscar Despesa por ID");
            System.out.println("4 - Editar Despesa");
            System.out.println("5 - Remover Despesa");
            System.out.println("6 - Salvar Alterações");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opc = sc.nextInt(); sc.nextLine();

            switch (opc) {
                case 1 -> listarDespesas();
                case 2 -> adicionarDespesa();
                case 3 -> buscarDespesa();
                case 4 -> editarDespesa();
                case 5 -> removerDespesa();
                case 6 -> salvarDespesas();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opc != 0);
    }

    // ======================
    // LISTAR
    // ======================

    /**
     * Lista todas as despesas cadastradas no sistema.
     * <p>Caso não haja despesas, informa ao usuário.</p>
     */
    private void listarDespesas() {
        List<Despesas> lista = gerenciador.listarDespesas();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma despesa cadastrada.");
            return;
        }
        System.out.println("\n--- LISTA DE DESPESAS ---");
        for (Despesas d : lista) {
            System.out.println("-----------------------------");
            System.out.println(d);
        }
        System.out.println("-----------------------------");
    }

    // ======================
    // ADICIONAR
    // ======================

    /**
     * Solicita informações ao usuário e adiciona uma nova despesa ao sistema.
     * <p>Os campos preenchidos são: descrição, valor, data e categoria.</p>
     */
    private void adicionarDespesa() {
        System.out.print("Descrição: ");
        String desc = sc.nextLine();

        System.out.print("Valor: ");
        double valor = sc.nextDouble(); sc.nextLine();

        System.out.print("Data [yyyy-MM-dd]: ");
        String dataStr = sc.nextLine();

        System.out.print("Categoria: ");
        String categoria = sc.nextLine();

        LocalDate data = LocalDate.parse(dataStr);
        gerenciador.adicionarDespesa(new Despesas(0, desc, valor, data, categoria));

        System.out.println(" Despesa adicionada!");
    }

    // ======================
    // BUSCAR
    // ======================

    /**
     * Busca uma despesa pelo ID informado pelo usuário.
     * <p>Se existir, exibe seus dados. Caso contrário, mostra mensagem de erro.</p>
     */
    private void buscarDespesa() {
        System.out.print("ID da despesa: ");
        int id = sc.nextInt(); sc.nextLine();

        Despesas d = gerenciador.buscarDespesaPorId(id);
        if (d != null) {
            System.out.println("-----------------------------");
            System.out.println(d);
            System.out.println("-----------------------------");
        } else {
            System.out.println(" Despesa não encontrada.");
        }
    }

    // ======================
    // EDITAR
    // ======================

    /**
     * Edita uma despesa existente com base no ID informado.
     * <p>Permite alterar descrição, valor, categoria e data.
     * Campos deixados vazios mantêm o valor original.</p>
     */
    private void editarDespesa() {
        System.out.print("ID da despesa para editar: ");
        int id = sc.nextInt(); sc.nextLine();

        Despesas d = gerenciador.buscarDespesaPorId(id);
        if (d == null) {
            System.out.println("Despesa não encontrada.");
            return;
        }

        System.out.print("Descrição (" + d.getDescricao() + "): ");
        String desc = sc.nextLine();
        String novaDesc = desc.isEmpty() ? d.getDescricao() : desc;

        System.out.print("Valor (" + d.getValor() + "): ");
        String valStr = sc.nextLine();
        double novoValor = valStr.isEmpty() ? d.getValor() : Double.parseDouble(valStr);

        System.out.print("Data (" + d.getData() + ") [yyyy-MM-dd]: ");
        String dataStr = sc.nextLine();
        LocalDate novaData = dataStr.isEmpty() ? d.getData() : LocalDate.parse(dataStr);

        System.out.print("Categoria (" + d.getCategoria() + "): ");
        String cat = sc.nextLine();
        String novaCategoria = cat.isEmpty() ? d.getCategoria() : cat;

        gerenciador.editarDespesa(id, new Despesas(id, novaDesc, novoValor, novaData, novaCategoria));
        System.out.println("✔ Despesa atualizada!");
    }

    // ======================
    // REMOVER
    // ======================

    /**
     * Remove uma despesa do sistema com base no ID informado.
     *
     * <p>Exibe mensagem indicando se a remoção foi bem-sucedida.</p>
     */
    private void removerDespesa() {
        System.out.print("ID da despesa para remover: ");
        int id = sc.nextInt(); sc.nextLine();

        boolean ok = gerenciador.removerDespesa(id);
        System.out.println(ok ? "✔ Despesa removida!" : "❌ Despesa não encontrada.");
    }

    // ======================
    // SALVAR
    // ======================

    /**
     * Salva todas as alterações realizadas no arquivo JSON.
     */
    private void salvarDespesas() {
        gerenciador.salvar();
    }
}
