package gerenciadores;

import modelo.Venda;
import modelo.Cliente;
import modelo.Produto;
import utils.CRUDGenerico;

import java.time.LocalDate;
import java.util.List;

public class GerenciadorVendas {

    private final CRUDGenerico<Venda> crudVendas;
    private final GerenciadorPessoas gerenciadorPessoas;
    private final GerenciadorProdutos gerenciadorProdutos;

    public GerenciadorVendas(GerenciadorPessoas gerenciadorPessoas, GerenciadorProdutos gerenciadorProdutos) {
        this.gerenciadorPessoas = gerenciadorPessoas;
        this.gerenciadorProdutos = gerenciadorProdutos;

        this.crudVendas = new CRUDGenerico<>("src/main/java/repositorio/vendas.json", Venda.class);
    }

    // =====================================================
    // ADICIONAR VENDA
    // =====================================================
    public boolean adicionarVenda(int idCliente, int idProduto, int quantidade) {

        Cliente cliente = gerenciadorPessoas.buscarClientePorId(idCliente);
        Produto produto = gerenciadorProdutos.buscarProdutoPorId(idProduto);

        if (cliente == null || produto == null) return false;

        if (quantidade <= 0 || quantidade > produto.getQuantidadeEstoque()) return false;

        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);

        Venda venda = new Venda(0, cliente, produto, quantidade, LocalDate.now());

        crudVendas.adicionar(venda);
        return true;
    }

    // =====================================================
    // LISTAR
    // =====================================================
    public List<Venda> listarVendas() {
        return crudVendas.listar();
    }

    // =====================================================
    // BUSCAR POR ID
    // =====================================================
    public Venda buscarVendaPorId(int id) {
        return crudVendas.buscarPorId(id);
    }

    // =====================================================
    // EDITAR
    // =====================================================
    public boolean editarVenda(int idVenda, int novoIdCliente, int novoIdProduto, int novaQuantidade) {

        Venda venda = crudVendas.buscarPorId(idVenda);
        if (venda == null) return false;

        Cliente clienteAntigo = venda.getCliente();
        Produto produtoAntigo = venda.getProduto();
        int quantidadeAntiga = venda.getQuantidade();

        Cliente novoCliente = gerenciadorPessoas.buscarClientePorId(novoIdCliente);
        Produto novoProduto = gerenciadorProdutos.buscarProdutoPorId(novoIdProduto);

        if (novoCliente == null || novoProduto == null) return false;
        if (novaQuantidade <= 0) return false;

        // Repor estoque do produto antigo
        produtoAntigo.setQuantidadeEstoque(produtoAntigo.getQuantidadeEstoque() + quantidadeAntiga);

        // Verificar estoque do novo produto
        if (novaQuantidade > novoProduto.getQuantidadeEstoque()) {

            // Voltar o estoque do produto antigo caso falhe
            produtoAntigo.setQuantidadeEstoque(produtoAntigo.getQuantidadeEstoque() - quantidadeAntiga);

            return false;
        }

        // Descontar do novo produto
        novoProduto.setQuantidadeEstoque(novoProduto.getQuantidadeEstoque() - novaQuantidade);

        // Atualizar venda
        venda.setCliente(novoCliente);
        venda.setProduto(novoProduto);
        venda.setQuantidade(novaQuantidade);

        return true;
    }

    // =====================================================
    // REMOVER
    // =====================================================
    public boolean removerVenda(int id) {

        Venda venda = crudVendas.buscarPorId(id);
        if (venda == null) return false;

        Produto produto = venda.getProduto();
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + venda.getQuantidade());

        crudVendas.remover(venda);

        return true;
    }

    // =====================================================
    // SALVAR
    // =====================================================
    public void salvar() {
        crudVendas.salvar();
        gerenciadorProdutos.salvar();
    }
}
