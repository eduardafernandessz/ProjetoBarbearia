package gerenciadores;

import modelo.Estacao;
import modelo.Servico;
import utils.CRUDGenerico;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciadorServicos {

    private final CRUDGenerico<Servico> crud;

    public GerenciadorServicos() {
        crud = new CRUDGenerico<>("src/main/java/repositorio/servicos.json", Servico.class);
    }

    // ==============================
    // LISTAR
    // ==============================
    public List<Servico> listar() {
        return crud.listar();
    }

    // ==============================
    // ADICIONAR
    // ==============================
    public void adicionar(Servico s) {
        crud.adicionar(s);
    }

    // ==============================
    // BUSCAR POR ID
    // ==============================
    public Servico buscarPorId(int id) {
        return crud.buscarPorId(id);
    }

    // ==============================
    // EDITAR
    // ==============================
    public boolean editar(int id, Scanner sc) {
        Servico s = crud.buscarPorId(id);
        if (s == null) return false;

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

        return true;
    }

    // ==============================
    // REMOVER
    // ==============================
    public boolean remover(int id) {
        Servico s = crud.buscarPorId(id);
        if (s != null) {
            crud.remover(s);
            return true;
        }
        return false;
    }

    // ==============================
    // SALVAR NO JSON
    // ==============================
    public void salvar() {
        crud.salvar();
        System.out.println("✔ Serviços salvos!");
    }
}
