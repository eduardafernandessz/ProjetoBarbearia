package gerenciadores;

import modelo.Venda;
import modelo.Cliente;
import modelo.Produto;
import utils.CRUDGenerico;

import java.time.LocalDate;
import java.util.List;

/**
 * Gerencia operações relacionadas a vendas.
 * <p>
 * É responsável por adicionar, editar, remover e listar vendas,
 * além de garantir o controle correto do estoque dos produtos.
 * </p>
 */
public class GerenciadorVendas {

    /** CRUD genérico responsável pelo armazenamento das vendas. */
    public final CRUDGenerico<Venda> crudVendas;

    /** Gerenciador responsável por consultar clientes. */
    private final GerenciadorPessoas gerenciadorPessoas;

    /** Gerenciador responsável por consultar e atualizar produtos. */
    private final GerenciadorProdutos gerenciadorProdutos;

    /**
     * Construtor do gerenciador de vendas.
     *
     * @param gerenciadorPessoas   Gerenciador de clientes.
     * @param gerenciadorProdutos  Gerenciador de produtos.
     */
    public GerenciadorVendas(GerenciadorPessoas gerenciadorPessoas, GerenciadorProdutos gerenciadorProdutos) {
        this.gerenciadorPessoas = gerenciadorPessoas;
        this.gerenciadorProdutos = gerenciadorProdutos;

        this.crudVendas = new CRUDGenerico<>("src/main/java/repositorio/vendas.json", Venda.class);
    }

    // =====================================================
    // ADICIONAR VENDA
    // =====================================================

    /**
     * Adiciona uma nova venda ao sistema.
     * <p>
     * Também atualiza o estoque do produto comprado.
     * </p>
     *
     * @param idCliente ID do cliente que está comprando.
     * @param idProduto ID do produto comprado.
     * @param quantidade Quantidade do produto.
     * @return true se a venda foi realizada; false caso cliente, produto ou estoque inválido.
     */
    public boolean adicionarVenda(int idCliente, int idProduto, int quantidade) {

        Cliente cliente = gerenciadorPessoas.buscarClientePorId(idCliente);
        Produto produto = gerenciadorProdutos.buscarProdutoPorId(idProduto);

        if (cliente == null || produto == null) return false;
        if (quantidade <= 0 || quantidade > produto.getQuantidadeEstoque()) return false;

        // Atualiza estoque
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);

        // Cria a venda (ID autogerado pelo CRUD)
        Venda venda = new Venda(0, cliente, produto, quantidade, LocalDate.now());

        crudVendas.adicionar(venda);
        return true;
    }

    // =====================================================
    // LISTAR
    // =====================================================

    /**
     * Lista todas as vendas.
     *
     * @return Lista de vendas.
     */
    public List<Venda> listarVendas() {
        return crudVendas.listar();
    }

    // =====================================================
    // BUSCAR POR ID
    // =====================================================

    /**
     * Busca uma venda pelo ID.
     *
     * @param id ID da venda.
     * @return A venda encontrada, ou null se não existir.
     */
    public Venda buscarPorId(int id) {
        return crudVendas.buscarPorId(id);
    }

    // =====================================================
    // EDITAR
    // =====================================================

    /**
     * Edita uma venda já existente.
     * <p>
     * O método repõe o estoque do produto antigo e desconta o estoque
     * do novo produto. Caso algo dê errado, o estoque é revertido.
     * </p>
     *
     * @param idVenda ID da venda.
     * @param novoIdCliente Novo ID do cliente.
     * @param novoIdProduto Novo ID do produto.
     * @param novaQuantidade Nova quantidade comprada.
     * @return true se editou corretamente; false caso cliente/produto
     *         inválidos, estoque insuficiente ou venda inexistente.
     */
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

            // Desfaz reposição se não puder concluir
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

    /**
     * Remove uma venda do sistema.
     * <p>
     * O estoque do produto é restaurado conforme a quantidade vendida.
     * </p>
     *
     * @param id ID da venda a remover.
     * @return true se removeu; false se a venda não existir.
     */
    public boolean removerVenda(int id) {

        Venda venda = crudVendas.buscarPorId(id);
        if (venda == null) return false;

        Produto produto = venda.getProduto();

        // Repor estoque
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + venda.getQuantidade());

        crudVendas.remover(venda);

        return true;
    }

    // =====================================================
    // SALVAR
    // =====================================================

    /**
     * Salva todas as vendas e atualizações de estoque no arquivo JSON.
     */
    public void salvar() {
        crudVendas.salvar();
        gerenciadorProdutos.salvar();
    }
}
