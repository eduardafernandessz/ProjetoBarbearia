
package modelo;

import java.time.LocalDate;


public class Despesas {
    
    private int id;
    private String descricao;
    private double valor;
    private LocalDate data;
    private String categoria;

    public Despesas(int id, String descricao, double valor, LocalDate data, String categoria) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.categoria = categoria;
    }
    
    public Despesas(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
        @Override
    public String toString() {
        String dataFormatada = (data != null) ? data.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "Sem data";
        return "Despesa\n" +
           "-----------------------------\n" +
           "Descrição: " + descricao + "\n" +
           "Valor: R$ " + valor + "\n" +
           "Data: " + data + "\n" +
           "Categoria: " + categoria + "\n";
}


}
