package modelo;

import java.time.LocalDate;

public class Venda {

    private int id;
    private Cliente cliente;
    private Produto produto;
    private int quantidade;
    private LocalDate data;

    public Venda(int id, Cliente cliente, Produto produto, int quantidade, LocalDate data) {
        this.id = id;
        this.cliente = cliente;
        this.produto = produto;
        this.quantidade = quantidade;
        this.data = data;
    }

    public Venda() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
}
