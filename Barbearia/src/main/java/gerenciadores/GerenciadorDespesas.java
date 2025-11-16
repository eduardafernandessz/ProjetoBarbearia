package gerenciadores;

import modelo.Despesas;
import utils.CRUDGenerico;

import java.time.LocalDate;
import java.util.List;

public class GerenciadorDespesas {

    private final CRUDGenerico<Despesas> crud;

    public GerenciadorDespesas() {
        crud = new CRUDGenerico<>("src/main/java/repositorio/despesas.json", Despesas.class);
    }

    // ==============================
    // ADICIONAR DESPESA
    // ==============================
    public void adicionarDespesa(Despesas despesa) {
        crud.adicionar(despesa);
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
    public boolean editarDespesa(int id, Despesas atualizada) {
        Despesas original = crud.buscarPorId(id);
        if (original != null) {
            // Atualiza campos
            original.setDescricao(atualizada.getDescricao());
            original.setValor(atualizada.getValor());
            original.setData(atualizada.getData());
            original.setCategoria(atualizada.getCategoria());
            return true;
        }
        return false;
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
    }
}
