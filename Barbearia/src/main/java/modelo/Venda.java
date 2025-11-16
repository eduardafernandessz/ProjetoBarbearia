package modelo;

import java.time.LocalDate;

/**
 * Representa uma venda de produto realizada para um cliente na barbearia.
 * <p>
 * Cada venda possui um identificador, cliente, produto, quantidade e data da venda.
 * Também permite calcular o valor total da venda.
 * </p>
 */
public class Venda {

    /** Identificador único da venda. */
    private int id;

    /** Cliente que realizou a compra. */
    private Cliente cliente;

    /** Produto vendido. */
    private Produto produto;

    /** Quantidade do produto vendido. */
    private int quantidade;

    /** Data em que a venda foi realizada. */
    private LocalDate data;

    /**
     * Construtor principal da venda.
     *
     * @param id Identificador único da venda.
     * @param cliente Cliente que realizou a compra.
     * @param produto Produto vendido.
     * @param quantidade Quantidade do produto.
     * @param data Data da venda.
     */
    public Venda(int id, Cliente cliente, Produto produto, int quantidade, LocalDate data) {
        this.id = id;
        this.cliente = cliente;
        this.produto = produto;
        this.quantidade = quantidade;
        this.data = data;
    }

    /**
     * Construtor vazio.
     * <p>
     * Inicializa a venda sem atributos definidos.
     * </p>
     */
    public Venda() {}

    /**
     * Retorna o identificador da venda.
     *
     * @return id da venda.
     */
    public int getId() { 
        return id; 
    }

    /**
     * Define o identificador da venda.
     *
     * @param id Identificador a ser definido.
     */
    public void setId(int id) { 
        this.id = id; 
    }

    /**
     * Retorna o cliente da venda.
     *
     * @return Cliente associado à venda.
     */
    public Cliente getCliente() { 
        return cliente; 
    }

    /**
     * Define o cliente da venda.
     *
     * @param cliente Cliente a ser associado à venda.
     */
    public void setCliente(Cliente cliente) { 
        this.cliente = cliente; 
    }

    /**
     * Retorna o produto vendido.
     *
     * @return Produto da venda.
     */
    public Produto getProduto() { 
        return produto; 
    }

    /**
     * Define o produto vendido.
     *
     * @param produto Produto a ser definido.
     */
    public void setProduto(Produto produto) { 
        this.produto = produto; 
    }

    /**
     * Retorna a quantidade vendida.
     *
     * @return Quantidade de produtos.
     */
    public int getQuantidade() { 
        return quantidade; 
    }

    /**
     * Define a quantidade vendida.
     *
     * @param quantidade Quantidade a ser definida.
     */
    public void setQuantidade(int quantidade) { 
        this.quantidade = quantidade; 
    }

    /**
     * Retorna a data da venda.
     *
     * @return Data da venda.
     */
    public LocalDate getData() { 
        return data; 
    }

    /**
     * Define a data da venda.
     *
     * @param data Data a ser definida.
     */
    public void setData(LocalDate data) { 
        this.data = data; 
    }

    /**
     * Calcula e retorna o valor total da venda.
     * <p>
     * Multiplica o preço do produto pela quantidade vendida.
     * Retorna 0.0 se o produto for nulo.
     * </p>
     *
     * @return Valor total da venda.
     */
    public double getValorTotal() {
        if (produto == null) return 0.0;
        return produto.getPreco() * quantidade;
    }

    /**
     * Retorna uma representação em String da venda, incluindo
     * ID, cliente, produto, quantidade e data formatada.
     *
     * @return String formatada da venda.
     */
    @Override
    public String toString() {
        String dataFormatada = (data != null) ? data.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "Sem data";

        return "Venda \n" +
               "-------------------------------\n" +
               "  ID: " + id + "\n" +
               "  Cliente: " + (cliente != null ? cliente.getNome() : "Indefinido") + "\n" +
               "  Produto: " + (produto != null ? produto.getNome() : "Indefinido") + "\n" +
               "  Quantidade: " + quantidade + "\n" +
               "  Data: " + dataFormatada + "\n";
    }
}
