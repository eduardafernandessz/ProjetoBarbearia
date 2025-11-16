package modelo;

import java.time.LocalDate;

/**
 * Classe que representa uma despesa registrada.
 *
 * <p>Contém informações como ID, descrição, valor, data e categoria da despesa.
 * Permite acessar e modificar esses dados por meio de getters e setters.</p>
 */
public class Despesas {

    /** Identificador único da despesa */
    private int id;

    /** Descrição detalhada da despesa */
    private String descricao;

    /** Valor monetário da despesa */
    private double valor;

    /** Data em que a despesa foi realizada */
    private LocalDate data;

    /** Categoria da despesa */
    private String categoria;

    /**
     * Construtor completo da despesa.
     *
     * @param id Identificador único da despesa
     * @param descricao Descrição detalhada
     * @param valor Valor da despesa
     * @param data Data da despesa
     * @param categoria Categoria da despesa
     */
    public Despesas(int id, String descricao, double valor, LocalDate data, String categoria) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.categoria = categoria;
    }

    /** Construtor vazio para facilitar criação de objetos sem inicialização imediata */
    public Despesas() {}

    /** Retorna o ID da despesa */
    public int getId() { return id; }

    /** Define o ID da despesa */
    public void setId(int id) { this.id = id; }

    /** Retorna a descrição da despesa */
    public String getDescricao() { return descricao; }

    /** Define a descrição da despesa */
    public void setDescricao(String descricao) { this.descricao = descricao; }

    /** Retorna o valor da despesa */
    public double getValor() { return valor; }

    /** Define o valor da despesa */
    public void setValor(double valor) { this.valor = valor; }

    /** Retorna a data da despesa */
    public LocalDate getData() { return data; }

    /** Define a data da despesa */
    public void setData(LocalDate data) { this.data = data; }

    /** Retorna a categoria da despesa */
    public String getCategoria() { return categoria; }

    /** Define a categoria da despesa */
    public void setCategoria(String categoria) { this.categoria = categoria; }

    /**
     * Retorna uma representação textual da despesa.
     *
     * <p>Inclui descrição, valor, data formatada e categoria. Se a data for nula, exibe "Sem data".</p>
     *
     * @return String com os detalhes da despesa
     */
    @Override
    public String toString() {
        String dataFormatada = (data != null) ? data.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "Sem data";
        return "Despesa\n" +
               "-----------------------------\n" +
               "Descrição: " + descricao + "\n" +
               "Valor: R$ " + valor + "\n" +
               "Data: " + dataFormatada + "\n" +
               "Categoria: " + categoria + "\n";
    }
}
