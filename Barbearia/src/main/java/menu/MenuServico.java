package menu;

import gerenciadores.GerenciadorServicos;
import modelo.Servico;
import modelo.Estacao;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.ArrayList;

/**
 * Menu de gerenciamento de serviços.
 * Permite listar, adicionar, buscar, editar, remover e salvar serviços.
 */
public class MenuServico {

    /** Scanner para leitura de dados do usuário */
    private final Scanner sc = new Scanner(System.in);

    /** Gerenciador responsável pelas operações de CRUD de serviços */
    private final GerenciadorServicos gerenciador = new GerenciadorServicos();

    /**
     * Exibe o menu principal e controla o fluxo das opções escolhidas.
     */
    public void exibirMenu() {
        int opc;
        do {
            System.out.println("\n--- MENU SERVIÇOS ---");
            System.out.println("1 - Listar Serviços");
            System.out.println("2 - Adicionar Serviço");
            System.out.println("3 - Buscar Serviço por ID");
            System.out.println("4 - Editar Serviço");
            System.out.println("5 - Remover Serviço");
            System.out.println("6 - Salvar Alterações");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opc = sc.nextInt(); sc.nextLine();

            switch (opc) {
                case 1 -> listarServicos();
                case 2 -> adicionarServico();
                case 3 -> buscarServico();
                case 4 -> editarServico();
                case 5 -> removerServico();
                case 6 -> salvarServicos();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opc != 0);
    }

    /**
     * Lista todos os serviços cadastrados no sistema.
     * Caso não existam serviços, avisa o usuário.
     */
    private void listarServicos() {
        List<Servico> lista = gerenciador.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum serviço cadastrado.");
            return;
        }
        for (Servico s : lista) {
            imprimirServico(s);
        }
    }

    /**
     * Lê dados do usuário e adiciona um novo serviço ao sistema.
     */
    private void adicionarServico() {
        System.out.print("Nome do serviço: ");
        String nome = sc.nextLine();

        double preco;
        while (true) {
            System.out.print("Preço: ");
            String precoStr = sc.nextLine();
            if (precoStr.isEmpty()) continue;
            try {
                preco = Double.parseDouble(precoStr);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido!");
            }
        }

        int duracao;
        while (true) {
            System.out.print("Duração (minutos): ");
            String durStr = sc.nextLine();
            if (durStr.isEmpty()) continue;
            try {
                duracao = Integer.parseInt(durStr);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido!");
            }
        }

        List<Estacao> estacoes = lerEstacoes();

        Servico s = new Servico(0, nome, preco, duracao, estacoes);
        gerenciador.adicionar(s);
        System.out.println(" Serviço adicionado!");
    }

    /**
     * Busca e exibe um serviço por ID.
     */
    private void buscarServico() {
        System.out.print("ID do serviço: ");
        int id = sc.nextInt(); sc.nextLine();
        Servico s = gerenciador.buscarPorId(id);
        if (s != null) imprimirServico(s);
        else System.out.println("Serviço não encontrado.");
    }

    /**
     * Edita um serviço já existente.
     * Permite alterar nome, preço, duração e estações.
     */
    private void editarServico() {
        System.out.print("ID do serviço para editar: ");
        int id = sc.nextInt(); sc.nextLine();
        Servico s = gerenciador.buscarPorId(id);
        if (s == null) {
            System.out.println(" Serviço não encontrado.");
            return;
        }

        System.out.print("Nome (" + s.getNome() + "): ");
        String nome = sc.nextLine();
        nome = nome.isBlank() ? null : nome;

        Double preco = null;
        System.out.print("Preço (" + s.getPreco() + "): ");
        String precoStr = sc.nextLine();
        if (!precoStr.isBlank()) {
            try { preco = Double.parseDouble(precoStr); }
            catch (NumberFormatException e) { System.out.println("Valor inválido, preço não alterado."); }
        }

        Integer duracao = null;
        System.out.print("Duração (" + s.getDuracaoMinutos() + " min): ");
        String durStr = sc.nextLine();
        if (!durStr.isBlank()) {
            try { duracao = Integer.parseInt(durStr); }
            catch (NumberFormatException e) { System.out.println("Valor inválido, duração não alterada."); }
        }

        List<Estacao> estacoes = null;
        System.out.print("Deseja alterar estações? (s/n): ");
        String alt = sc.nextLine();
        if (alt.equalsIgnoreCase("s")) estacoes = lerEstacoes();

        boolean ok = gerenciador.editar(id, nome, preco, duracao, estacoes);
        System.out.println(ok ? " Serviço atualizado!" : " Falha ao atualizar serviço.");
    }

    /**
     * Remove um serviço do sistema com base no ID informado.
     */
    private void removerServico() {
        System.out.print("ID do serviço para remover: ");
        int id = sc.nextInt(); sc.nextLine();
        boolean ok = gerenciador.remover(id);
        System.out.println(ok ? " Serviço removido!" : " Serviço não encontrado.");
    }

    /**
     * Salva todas as alterações feitas na base JSON.
     */
    private void salvarServicos() {
        gerenciador.salvar();
        System.out.println(" Alterações salvas!");
    }

    /**
     * Lê as estações permitidas para um serviço.
     * O usuário pode selecionar várias estações.
     *
     * @return lista de estações escolhidas
     */
    private List<Estacao> lerEstacoes() {
        List<Estacao> estacoes = new ArrayList<>();
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
                } else System.out.println("Número inválido!");
            } catch (NumberFormatException ex) {
                System.out.println("Digite um número válido!");
            }
        }
        return estacoes;
    }

    /**
     * Imprime as informações completas de um serviço.
     *
     * @param s serviço a ser exibido
     */
    private void imprimirServico(Servico s) {
        String estacoes = (s.getEstacoesPossiveis() != null && !s.getEstacoesPossiveis().isEmpty())
                ? s.getEstacoesPossiveis().stream().map(Estacao::name).collect(Collectors.joining(", "))
                : "Não definidas";

        System.out.println("-----------------------------");
        System.out.println("ID: " + s.getId());
        System.out.println("Nome: " + s.getNome());
        System.out.println("Preço: R$ " + s.getPreco());
        System.out.println("Duração: " + s.getDuracaoMinutos() + " min");
        System.out.println("Estações: " + estacoes);
        System.out.println("-----------------------------");
    }
}
