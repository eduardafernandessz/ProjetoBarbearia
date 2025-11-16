package modelo;

import modelo.Estacao;
import java.util.List;

public class Servico {
    private int id;
    private String nome;
    private double preco;
    private int duracaoMinutos;
    private List<Estacao> estacoesPossiveis; // AGORA É UMA LISTA!

    public Servico(int id, String nome, double preco, int duracaoMinutos, List<Estacao> estacoesPossiveis) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.duracaoMinutos = duracaoMinutos;
        this.estacoesPossiveis = estacoesPossiveis;
    }

    public Servico() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }
    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    public List<Estacao> getEstacoesPossiveis() {
        return estacoesPossiveis;
    }
    public void setEstacoesPossiveis(List<Estacao> estacoesPossiveis) {
        this.estacoesPossiveis = estacoesPossiveis;
    }
    
        @Override
    public String toString() {
        String estacoes = (estacoesPossiveis == null || estacoesPossiveis.isEmpty())
                ? "Nenhuma"
                : estacoesPossiveis.stream().map(Enum::name).reduce((a, b) -> a + ", " + b).orElse("Nenhuma");

        return "Servico {\n" +
                "  ID: " + id + "\n" +
                "  Nome: " + nome + "\n" +
                "  Preço: R$ " + String.format("%.2f", preco) + "\n" +
                "  Duração: " + duracaoMinutos + " minutos\n" +
                "  Estações possíveis: " + estacoes + "\n" +
                "}";
    }

}
