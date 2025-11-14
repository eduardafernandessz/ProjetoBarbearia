package servico;

import utils.CRUDGenerico;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciadorServicos {

    private CRUDGenerico<Servico> crud;

    public GerenciadorServicos() {
        crud = new CRUDGenerico<>("src/main/java/repositorio/servicos.json", Servico.class);
    }

    // ======================
    // LISTAR SERVIÇOS
    // ======================
    public List<Servico> listarServicos() {
        return crud.listar();
    }

    // ======================
    // ADICIONAR SERVIÇO
    // ======================
    public void adicionarServico(String nome, double preco, int duracao, List<Estacao> estacoes) {
        Servico s = new Servico(0, nome, preco, duracao, estacoes);
        crud.adicionar(s);
        crud.salvar();
    }

    // ======================
    // BUSCAR SERVIÇO
    // ======================
    public Servico buscarServicoPorId(int id) {
        return crud.buscarPorId(id);
    }

    // ======================
    // EDITAR SERVIÇO
    // ======================
    public void editarServico(int id, Scanner sc) {
        Servico s = crud.buscarPorId(id);
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

        System.out.print("Deseja alterar estações possíveis? (s/n): ");
        String alterar = sc.nextLine();
        if (alterar.equalsIgnoreCase("s")) {
            List<Estacao> novas = new ArrayList<>();
            System.out.println("Escolha as novas estações possíveis (vazio para finalizar):");
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
                        if (!novas.contains(escolhida)) novas.add(escolhida);
                    } else {
                        System.out.println("Número inválido!");
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Digite um número válido!");
                }
            }

            s.setEstacoesPossiveis(novas);
        }

        crud.salvar();
    }

    // ======================
    // REMOVER SERVIÇO
    // ======================
    public boolean removerServico(int id) {
        Servico s = crud.buscarPorId(id);
        if (s != null) {
            crud.remover(s);
            crud.salvar();
            return true;
        }
        return false;
    }
}
