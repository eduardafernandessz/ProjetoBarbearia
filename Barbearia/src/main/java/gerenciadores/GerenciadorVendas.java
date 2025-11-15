package gerenciadores;

import modelo.Cliente;
import modelo.Produto;
import modelo.Venda;
import utils.CRUDGenerico;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorVendas {

    private List<Venda> vendas;
    private GerenciadorPessoas gerenciadorPessoas;
    private GerenciadorProdutos gerenciadorProdutos;
    private CRUDGenerico<Venda> crudVendas; // Para salvar/load JSON

    public GerenciadorVendas(GerenciadorPessoas gerenciadorPessoas, GerenciadorProdutos gerenciadorProdutos) {
        this.gerenciadorPessoas = gerenciadorPessoas;
        this.gerenciadorProdutos = gerenciadorProdutos;

        // Inicializa CRUD para persistência
        this.crudVendas = new CRUDGenerico<>("src/main/java/repositorio/vendas.json", Venda.class);
        this.vendas = crudVendas.carregar();
        if (vendas == null) {
            vendas = new ArrayList<>();
        }
    }

    // =====================
    // ADICIONAR VENDA
    // =====================
    public boolean adicionarVenda(int idCliente, int idProduto, int quantidade) {
        Cliente cliente = gerenciadorPessoas.buscarClientePorId(idCliente);
        Produto produto = gerenciadorProdutos.buscarPorId(idProduto);

        if (cliente == null || produto == null) return false;
        if (quantidade <= 0 || quantidade > produto.getQuantidadeEstoque()) return false;

        // Gera ID automático
        int novoId = vendas.stream().mapToInt(Venda::getId).max().orElse(0) + 1;

        // Criar venda
        Venda venda = new Venda(novoId, cliente, produto, quantidade, LocalDate.now());
        vendas.add(venda);

        // Atualizar estoque
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);

        return true;
    }

    // =====================
    // EDITAR VENDA
    // =====================
    public boolean editarVenda(int id, int idCliente, int idProduto, int quantidade) {
        Venda v = buscarVendaPorId(id);
        if (v == null) return false;

        Cliente cliente = gerenciadorPessoas.buscarClientePorId(idCliente);
        Produto produto = gerenciadorProdutos.buscarPorId(idProduto);

        if (cliente == null || produto == null) return false;
        if (quantidade <= 0 || quantidade > produto.getQuantidadeEstoque()) return false;

        // Ajusta estoque do produto antigo
        Produto antigoProduto = v.getProduto();
        antigoProduto.setQuantidadeEstoque(antigoProduto.getQuantidadeEstoque() + v.getQuantidade());

        // Atualiza venda
        v.setCliente(cliente);
        v.setProduto(produto);
        v.setQuantidade(quantidade);

        // Ajusta estoque do novo produto
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);

        return true;
    }

    // =====================
    // REMOVER VENDA
    // =====================
    public boolean removerVenda(int id) {
        Venda v = buscarVendaPorId(id);
        if (v != null) {
            // Repor estoque
            Produto produto = v.getProduto();
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + v.getQuantidade());

            vendas.remove(v);
            return true;
        }
        return false;
    }

    // =====================
    // BUSCAR VENDA
    // =====================
    public Venda buscarVendaPorId(int id) {
        return vendas.stream().filter(v -> v.getId() == id).findFirst().orElse(null);
    }

    // =====================
    // LISTAR VENDAS
    // =====================
    public List<Venda> listarVendas() {
        return new ArrayList<>(vendas);
    }

    // =====================
    // SALVAR VENDAS
    // =====================
    public void salvar() {
        crudVendas.salvar();   
        gerenciadorProdutos.salvar();    // persiste estoque atualizado
        System.out.println("Alterações salvas no JSON!");
    }
}
