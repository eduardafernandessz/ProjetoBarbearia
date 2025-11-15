package menu;

import gerenciadores.GerenciadorServicos;
import modelo.Servico;
import modelo.Estacao;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MenuServico {

    private Scanner sc = new Scanner(System.in);
    private GerenciadorServicos gerenciador = new GerenciadorServicos();

    // --- MENU PRINCIPAL ---
    public void exibirMenu() {
        int opc;
        do {
            System.out.println("\n--- MENU SERVIÇOS ---");
            System.out.println("1 - Listar Serviços");
            System.out.println("2 - Adicionar Serviço");
            System.out.println("3 - Buscar Serviço por ID");
            System.out.println("4 - Editar Serviço");
            System.out.println("5 - Remover Serviço");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opc = sc.nextInt(); sc.nextLine();

            switch (opc) {
                case 1 -> listarServicos();
                case 2 -> adicionarServico();
                case 3 -> buscarServico();
                case 4 -> editarServico();
                case 5 -> removerServico();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opc != 0);
    }

    // ======================
    // LISTAR SERVIÇOS
    // ======================
    private void listarServicos() {
        List<Servico> lista = gerenciador.listarServicos();
        if (lista.isEmpty()) System.out.println("Nenhum serviço cadastrado.");
        else lista.forEach(s -> {
            String estacoes = s.getEstacoesPossiveis() != null
                    ? s.getEstacoesPossiveis().stream().map(Estacao::name).collect(Collectors.joining(", "))
                    : "Não definidas";
            System.out.println(s.getId() + " - " + s.getNome() + " | R$ " + s.getPreco() + " | Duração: " + s.getDuracaoMinutos() + " min | Estações: " + estacoes);
        });
    }

    // ======================
    // ADICIONAR SERVIÇO
    // ======================
    private void adicionarServico() {
        System.out.print("Nome do serviço: "); String nome = sc.nextLine();
        System.out.print("Preço: "); double preco = sc.nextDouble(); sc.nextLine();
        System.out.print("Duração (minutos): "); int duracao = sc.nextInt(); sc.nextLine();

        List<Estacao> estacoes = lerEstacoes();

        gerenciador.adicionarServico(nome, preco, duracao, estacoes);
        System.out.println("✔ Serviço adicionado!");
    }

    // ======================
    // BUSCAR SERVIÇO
    // ======================
    private void buscarServico() {
        System.out.print("ID do serviço: "); int id = sc.nextInt(); sc.nextLine();
        Servico s = gerenciador.buscarServicoPorId(id);
        if (s == null) System.out.println("Serviço não encontrado.");
        else imprimirServico(s);
    }

    // ======================
    // EDITAR SERVIÇO
    // ======================
    private void editarServico() {
        System.out.print("ID do serviço para editar: "); int id = sc.nextInt(); sc.nextLine();
        gerenciador.editarServico(id, sc);
        System.out.println("✔ Serviço atualizado!");
    }

    // ======================
    // REMOVER SERVIÇO
    // ======================
    private void removerServico() {
        System.out.print("ID do serviço para remover: "); int id = sc.nextInt(); sc.nextLine();
        if (gerenciador.removerServico(id)) System.out.println("✔ Serviço removido!");
        else System.out.println("Serviço não encontrado.");
    }

    // ======================
    // MÉTODOS AUXILIARES
    // ======================
    private List<Estacao> lerEstacoes() {
        List<Estacao> estacoes = new java.util.ArrayList<>();
        System.out.println("Escolha as estações possíveis (vazio para finalizar):");
        for (Estacao e : Estacao.values()) {
            System.out.println((e.ordinal() + 1) + " - " + e.name());
        }

        while (true) {
            System.out.print("Número da estação: ");
            String input = sc.nextLine();
            if (input.isBlank()) break;
            try {
                int num = Integer.parseInt(input);
                if (num >= 1 && num <= Estacao.values().length) {
                    Estacao escolhida = Estacao.values()[num - 1];
                    if (!estacoes.contains(escolhida)) estacoes.add(escolhida);
                } else {
                    System.out.println("Número inválido!");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Digite um número válido!");
            }
        }

        return estacoes;
    }

    private void imprimirServico(Servico s) {
        String estacoes = s.getEstacoesPossiveis() != null
                ? s.getEstacoesPossiveis().stream().map(Estacao::name).collect(Collectors.joining(", "))
                : "Não definidas";
        System.out.println("ID: " + s.getId());
        System.out.println("Nome: " + s.getNome());
        System.out.println("Preço: R$ " + s.getPreco());
        System.out.println("Duração: " + s.getDuracaoMinutos() + " min");
        System.out.println("Estações possíveis: " + estacoes);
    }
}
