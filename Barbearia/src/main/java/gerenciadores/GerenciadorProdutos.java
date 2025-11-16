package gerenciadores;

import modelo.Produto;
import utils.CRUDGenerico;

import java.util.List;

/**
 * Gerencia os produtos da barbearia.
 * <p>
 * Permite adicionar, editar, remover, listar e buscar produtos,
 * utilizando persistência em JSON através do CRUD genérico.
 * </p>
 */
public class GerenciadorProdutos {

    /** CRUD genérico para manipulação de produtos no arquivo JSON. */
    private final CRUDGenerico<Produto> crud;

    /**
     * Construtor do gerenciador de produtos.
     * <p>
     * Inicializa o CRUD genérico apontando para o arquivo de produtos.
     * </p>
     */
    public GerenciadorProdutos() {
        this.crud = new CRUDGenerico<>("src/main/java/repositorio/produtos.json", Produto.class);
    }

    /**
     * Adiciona um novo produto.
     *
     * @param p Produto a ser adicionado.
     */
    public void adicionar(Produto p) {
        crud.adicionar(p);
    }

    /**
     * Edita um produto existente.
     * <p>
     * Substitui o produto antigo pelo atualizado.
     * </p>
     *
     * @param id Identificador do produto a ser editado.
     * @param atualizado Produto com os dados atualizados.
     * @return true se a edição foi realizada; false se o produto não foi encontrado.
     */
    public boolean editar(int id, Produto atualizado) {
        Produto antigo = crud.buscarPorId(id);
        if (antigo != null) {
            crud.remover(antigo);
            crud.adicionar(atualizado);
            return true;
        }
        return false;
    }

    /**
     * Remove um produto pelo seu identificador.
     *
     * @param id Identificador do produto a ser removido.
     * @return true se o produto foi removido; false se não foi encontrado.
     */
    public boolean remover(int id) {
        Produto p = crud.buscarPorId(id);
        if (p != null) {
            crud.remover(p);
            return true;
        }
        return false;
    }

    /**
     * Retorna todos os produtos cadastrados.
     *
     * @return Lista de produtos.
     */
    public List<Produto> listar() {
        return crud.listar();
    }

    /**
     * Busca um produto pelo seu identificador.
     *
     * @param id Identificador do produto.
     * @return Produto encontrado ou null se não existir.
     */
    public Produto buscarProdutoPorId(int id) {
        return crud.buscarPorId(id);
    }

    /**
     * Salva os produtos no arquivo JSON.
     */
    public void salvar() {
        crud.salvar();
    }
}
