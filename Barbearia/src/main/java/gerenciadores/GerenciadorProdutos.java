package gerenciadores;

import modelo.Produto;
import utils.CRUDGenerico;

import java.util.List;

public class GerenciadorProdutos {

    private final CRUDGenerico<Produto> crud;

    public GerenciadorProdutos() {
        crud = new CRUDGenerico<>("src/main/java/repositorio/produtos.json", Produto.class);
    }

    // ==============================
    // ADICIONAR
    // ==============================
    public void adicionar(Produto p) {
        crud.adicionar(p);
    }

    // ==============================
    // EDITAR
    // ==============================
    public boolean editar(int id, Produto atualizado) {
        Produto antigo = crud.buscarPorId(id);
        if (antigo != null) {
            crud.remover(antigo);
            crud.adicionar(atualizado);
            return true;
        }
        return false;
    }

    // ==============================
    // REMOVER
    // ==============================
    public boolean remover(int id) {
        Produto p = crud.buscarPorId(id);
        if (p != null) {
            crud.remover(p);
            return true;
        }
        return false;
    }

    // ==============================
    // LISTAR
    // ==============================
    public List<Produto> listar() {
        return crud.listar();
    }

    // ==============================
    // BUSCAR POR ID
    // ==============================
    public Produto buscarPorId(int id) {
        return crud.buscarPorId(id);
    }

    // ==============================
    // SALVAR NO JSON
    // ==============================
    public void salvar() {
        crud.salvar();
        System.out.println("Produtos salvos!");
    }
}
