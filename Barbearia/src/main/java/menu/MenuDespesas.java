package menu;

import gerenciadores.GerenciadorDespesas;
import modelo.Despesas;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuDespesas {

    private Scanner sc = new Scanner(System.in);
    private GerenciadorDespesas gerenciador = new GerenciadorDespesas();

    // --- MENU PRINCIPAL ---
    public void exibirMenu() {
        int opc;
        do {
            System.out.println("\n--- MENU DESPESAS ---");
            System.out.println("1 - Listar Despesas");
            System.out.println("2 - Adicionar Despesa");
            System.out.println("3 - Buscar Despesa por ID");
            System.out.println("4 - Editar Despesa");
            System.out.println("5 - Remover Despesa");
            System.out.println("6 - Salvar alterações");
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

    // --- LISTAR DESPESAS ---
    private void listarDespesas() {
        List<Despesas> lista = gerenciador.listarDespesas();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma despesa cadastrada.");
            return;
        }

        System.out.println("\n--- LISTA DE DESPESAS ---");
        for (Despesas d : lista) {
            System.out.println("---------------------------");
            System.out.println("ID: " + d.getId());
            System.out.println("Descrição: " + d.getDescricao());
            System.out.println("Valor: R$ " + d.getValor());
            System.out.println("Data: " + d.getData());
            System.out.println("Categoria: " + d.getCategoria());
        }
        System.out.println("---------------------------");
    }

    // --- ADICIONAR DESPESA ---
    private void adicionarDespesa() {
        System.out.print("Descrição: ");
        String desc = sc.nextLine();
        System.out.print("Valor: ");
        double valor = sc.nextDouble(); sc.nextLine();
        System.out.print("Data [yyyy-MM-dd]: ");
        LocalDate data = LocalDate.parse(sc.nextLine());
        System.out.print("Categoria: ");
        String cat = sc.nextLine();

        gerenciador.adicionarDespesa(desc, valor, data, cat);
        System.out.println("✔ Despesa adicionada!");
    }

    // --- BUSCAR DESPESA ---
    private void buscarDespesa() {
        System.out.print("ID da despesa: ");
        int id = sc.nextInt(); sc.nextLine();
        Despesas d = gerenciador.buscarDespesaPorId(id);
        if (d != null) {
            System.out.println("---------------------------");
            System.out.println("Descrição: " + d.getDescricao());
            System.out.println("Valor: R$ " + d.getValor());
            System.out.println("Data: " + d.getData());
            System.out.println("Categoria: " + d.getCategoria());
            System.out.println("---------------------------");
        } else {
            System.out.println("Despesa não encontrada.");
        }
    }

    private void editarDespesa() {
        System.out.print("ID da despesa para editar: ");
        int id = sc.nextInt(); sc.nextLine();

        gerenciador.editarDespesa(id, sc);
        System.out.println("✔ Despesa atualizada!");
    }


    // --- REMOVER DESPESA ---
    private void removerDespesa() {
        System.out.print("ID da despesa para remover: ");
        int id = sc.nextInt(); sc.nextLine();

        if (gerenciador.removerDespesa(id)) {
            System.out.println("✔ Despesa removida!");
        } else {
            System.out.println("Despesa não encontrada.");
        }
    }

    // --- SALVAR DESPESAS ---
    private void salvarDespesas() {
        gerenciador.salvar();
    }
}
