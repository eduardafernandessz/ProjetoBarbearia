package gerenciadores;

import modelo.Despesas;
import utils.CRUDGenerico;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class GerenciadorDespesas {

    private CRUDGenerico<Despesas> crud;

    public GerenciadorDespesas() {
        crud = new CRUDGenerico<>("src/main/java/repositorio/despesas.json", Despesas.class);
    }

    // ==============================
    // ADICIONAR DESPESA
    // ==============================
    public void adicionarDespesa(String descricao, double valor, LocalDate data, String categoria) {
        Despesas d = new Despesas(0, descricao, valor, data, categoria);
        crud.adicionar(d);
    }

    // ==============================
    // LISTAR DESPESAS
    // ==============================
    public List<Despesas> listarDespesas() {
        return crud.listar();
    }

    // ==============================
    // BUSCAR DESPESA POR ID
    // ==============================
    public Despesas buscarDespesaPorId(int id) {
        return crud.buscarPorId(id);
    }

    // ==============================
    // EDITAR DESPESA
    // ==============================
    public void editarDespesa(int id, Scanner sc) {
        Despesas d = crud.buscarPorId(id);
        if (d != null) {
            System.out.print("Descrição (" + d.getDescricao() + "): ");
            String desc = sc.nextLine();
            if (!desc.isEmpty()) d.setDescricao(desc);

            System.out.print("Valor (" + d.getValor() + "): ");
            String valStr = sc.nextLine();
            if (!valStr.isEmpty()) d.setValor(Double.parseDouble(valStr));

            System.out.print("Data (" + d.getData() + ") [yyyy-MM-dd]: ");
            String dataStr = sc.nextLine();
            if (!dataStr.isEmpty()) d.setData(LocalDate.parse(dataStr));

            System.out.print("Categoria (" + d.getCategoria() + "): ");
            String cat = sc.nextLine();
            if (!cat.isEmpty()) d.setCategoria(cat);
        }
    }

    // ==============================
    // REMOVER DESPESA
    // ==============================
    public boolean removerDespesa(int id) {
        Despesas d = crud.buscarPorId(id);
        if (d != null) {
            crud.remover(d);
            return true;
        }
        return false;
    }

    // ==============================
    // SALVAR DESPESAS
    // ==============================
    public void salvar() {
        crud.salvar();
        System.out.println(" Alterações salvas!");
    }
}
