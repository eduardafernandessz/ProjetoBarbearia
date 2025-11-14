/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servico;

/**
 *
 * @author KEL
 */
public class Servico {
    private int id;
    private String nome;
    private double preco;
    private int duracaoMinutos;
    private Estacao estacaoNecessaria;

    public Servico(int id, String nome, double preco, int duracaoMinutos, Estacao estacaoNecessaria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.duracaoMinutos = duracaoMinutos;
        this.estacaoNecessaria = estacaoNecessaria;
    }

    public Estacao getEstacaoNecessaria() {
        return estacaoNecessaria;
    }
}
