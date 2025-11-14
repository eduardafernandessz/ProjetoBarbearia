package menu;

import servico.Servico;
import servico.Estacao;
import utils.CRUDGenerico;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MenuServico {

    private Scanner sc = new Scanner(System.in);

    private CRUDGenerico<Servico> crudServicos =
            new CRUDGenerico<>("src/main/java/repositorio/servicos.json", Servico.class);

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
            opc = sc.nextInt();
            sc.nextLine();

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

    // ---------- MÉTODOS ----------

    private void listarServicos() {
        List<Servico> lista = crudServicos.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum serviço cadastrado.");
            return;
        }

        for (Servico s : lista) {
            String estacoes = "Não definidas";
            if (s.getEstacoesPossiveis() != null && !s.getEstacoesPossiveis().isEmpty()) {
                estacoes = s.getEstacoesPossiveis().stream()
                        .map(Estacao::name)
                        .collect(Collectors.joining(", "));
            }

            System.out.println(
                    "ID: " + s.getId() +
                    "\nNome: " + s.getNome() +
                    "\nPreço: " + s.getPreco() +
                    "\nDuração: " + s.getDuracaoMinutos() + " min" +
                    "\nEstações possíveis: " + estacoes +
                    "\n---------------------------"
            );
        }
    }

    private void adicionarServico() {
        System.out.print("Nome do serviço: ");
        String nome = sc.nextLine();

        System.out.print("Preço: ");
        double preco = sc.nextDouble();
        sc.nextLine();

        System.out.print("Duração (minutos): ");
        int duracao = sc.nextInt();
        sc.nextLine();

        // --- selecionar estações possíveis via enum ---
        List<Estacao> estacoes = new ArrayList<>();
        System.out.println("Escolha as estações possíveis (1 a 3, vazio para parar):");
        for (Estacao e : Estacao.values()) {
            System.out.println(e.ordinal() + 1 + " - " + e.name());
        }

        while (true) {
            System.out.print("Número da estação: ");
            String input = sc.nextLine();
            if (input.isBlank()) break;
            try {
                int num = Integer.parseInt(input);
                if (num >= 1 && num <= Estacao.values().length) {
                    Estacao escolhida = Estacao.values()[num - 1];
                    if (!estacoes.contains(escolhida)) {
                        estacoes.add(escolhida);
                    }
                } else {
                    System.out.println("Número inválido!");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Digite um número válido!");
            }
        }

        Servico s = new Servico(0, nome, preco, duracao, estacoes);
        crudServicos.adicionar(s);

        System.out.println("Serviço adicionado!");
    }

    private void buscarServico() {
        System.out.print("ID do serviço: ");
        int id = sc.nextInt();
        sc.nextLine();

        Servico s = crudServicos.buscarPorId(id);

        if (s == null) {
            System.out.println("Serviço não encontrado.");
            return;
        }

        String estacoes = "Não definidas";
        if (s.getEstacoesPossiveis() != null && !s.getEstacoesPossiveis().isEmpty()) {
            estacoes = s.getEstacoesPossiveis().stream()
                    .map(Estacao::name)
                    .collect(Collectors.joining(", "));
        }

        System.out.println(
                "ID: " + s.getId() +
                "\nNome: " + s.getNome() +
                "\nPreço: " + s.getPreco() +
                "\nDuração: " + s.getDuracaoMinutos() + " min" +
                "\nEstações possíveis: " + estacoes
        );
    }

    private void editarServico() {
        System.out.print("ID do serviço para editar: ");
        int id = sc.nextInt();
        sc.nextLine();

        Servico s = crudServicos.buscarPorId(id);

        if (s == null) {
            System.out.println("Serviço não encontrado.");
            return;
        }

        System.out.print("Nome (" + s.getNome() + "): ");
        String nome = sc.nextLine();
        if (!nome.isEmpty()) s.setNome(nome);

        System.out.print("Preço (" + s.getPreco() + "): ");
        String precoStr = sc.nextLine();
        if (!precoStr.isEmpty()) s.setPreco(Double.parseDouble(precoStr));

        System.out.print("Duração (" + s.getDuracaoMinutos() + "): ");
        String duracaoStr = sc.nextLine();
        if (!duracaoStr.isEmpty()) s.setDuracaoMinutos(Integer.parseInt(duracaoStr));

        System.out.println("Editar estações possíveis? (s/n): ");
        String editar = sc.nextLine();

        if (editar.equalsIgnoreCase("s")) {
            List<Estacao> novas = new ArrayList<>();
            System.out.println("Escolha as novas estações possíveis:");
            for (Estacao e : Estacao.values()) {
                System.out.println(e.ordinal() + 1 + " - " + e.name());
            }

            while (true) {
                System.out.print("Número da estação: ");
                String input = sc.nextLine();
                if (input.isBlank()) break;
                try {
                    int num = Integer.parseInt(input);
                    if (num >= 1 && num <= Estacao.values().length) {
                        Estacao escolhida = Estacao.values()[num - 1];
                        if (!novas.contains(escolhida)) {
                            novas.add(escolhida);
                        }
                    } else {
                        System.out.println("Número inválido!");
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Digite um número válido!");
                }
            }
            s.setEstacoesPossiveis(novas);
        }

        crudServicos.salvar();
        System.out.println("Serviço atualizado!");
    }

    private void removerServico() {
        System.out.print("ID do serviço para remover: ");
        int id = sc.nextInt();
        sc.nextLine();

        Servico s = crudServicos.buscarPorId(id);
        if (s != null) {
            crudServicos.remover(s);
            System.out.println("Serviço removido!");
        } else {
            System.out.println("Serviço não encontrado.");
        }
    }
}
