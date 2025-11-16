package modelo;

import modelo.Estacao;
import java.util.List;

    /**
     * Representa um serviço oferecido pela barbearia.
     * <p>
     * Cada serviço possui um identificador, nome, preço, duração em minutos
     * e uma lista de estações possíveis onde pode ser realizado.
     * </p>
     */
public class Servico {

    /** Identificador único do serviço. */
    private int id;

    /** Nome do serviço. */
    private String nome;

    /** Preço do serviço em reais. */
    private double preco;

    /** Duração do serviço em minutos. */
    private int duracaoMinutos;

    /** Lista de estações possíveis para realizar o serviço. */
    private List<Estacao> estacoesPossiveis;

    /**
     * Construtor principal do serviço.
     *
     * @param id Identificador único do serviço.
     * @param nome Nome do serviço.
     * @param preco Preço do serviço.
     * @param duracaoMinutos Duração do serviço em minutos.
     * @param estacoesPossiveis Lista de estações possíveis para realizar o serviço.
     */
    public Servico(int id, String nome, double preco, int duracaoMinutos, List<Estacao> estacoesPossiveis) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.duracaoMinutos = duracaoMinutos;
        this.estacoesPossiveis = estacoesPossiveis;
    }

    /**
     * Construtor vazio.
     * <p>
     * Inicializa o serviço sem atributos definidos.
     * </p>
     */
    public Servico() {
    }

    /**
     * Retorna o identificador do serviço.
     *
     * @return id do serviço.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador do serviço.
     *
     * @param id Identificador a ser definido.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o nome do serviço.
     *
     * @return Nome do serviço.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do serviço.
     *
     * @param nome Nome a ser definido.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o preço do serviço.
     *
     * @return Preço em reais.
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Define o preço do serviço.
     *
     * @param preco Preço a ser definido.
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * Retorna a duração do serviço em minutos.
     *
     * @return Duração em minutos.
     */
    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    /**
     * Define a duração do serviço em minutos.
     *
     * @param duracaoMinutos Duração a ser definida.
     */
    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    /**
     * Retorna a lista de estações possíveis para realizar o serviço.
     *
     * @return Lista de estações.
     */
    public List<Estacao> getEstacoesPossiveis() {
        return estacoesPossiveis;
    }

    /**
     * Define a lista de estações possíveis para realizar o serviço.
     *
     * @param estacoesPossiveis Lista de estações.
     */
    public void setEstacoesPossiveis(List<Estacao> estacoesPossiveis) {
        this.estacoesPossiveis = estacoesPossiveis;
    }

    /**
     * Retorna uma representação em String do serviço, incluindo
     * ID, nome, preço, duração e estações possíveis.
     *
     * @return String formatada do serviço.
     */
    @Override
    public String toString() {
        String estacoes = (estacoesPossiveis == null || estacoesPossiveis.isEmpty())
                ? "Nenhuma"
                : estacoesPossiveis.stream().map(Enum::name).reduce((a, b) -> a + ", " + b).orElse("Nenhuma");

        return "Servico \n" +
                "-------------------------------\n" +
                "  ID: " + id + "\n" +
                "  Nome: " + nome + "\n" +
                "  Preço: R$ " + String.format("%.2f", preco) + "\n" +
                "  Duração: " + duracaoMinutos + " minutos\n" +
                "  Estações possíveis: " + estacoes + "\n";
    }

}
