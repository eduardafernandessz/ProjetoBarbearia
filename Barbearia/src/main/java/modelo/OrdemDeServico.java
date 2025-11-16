package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrdemDeServico {

    private int id;
    private Cliente cliente;
    private List<Agendamento> agendamentos;
    private List<Venda> vendas;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataFechamento;
    private double valorTotal;

    // Construtor principal
    public OrdemDeServico(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.agendamentos = new ArrayList<>();
        this.vendas = new ArrayList<>();
        this.dataAbertura = LocalDateTime.now();
        recalcularTotal();
    }

    public OrdemDeServico() {
        this.agendamentos = new ArrayList<>();
        this.vendas = new ArrayList<>();
    }

    // -------- CÁLCULO DO TOTAL --------
    private void recalcularTotal() {
        double total = 0;

        if (agendamentos != null) {
            total += agendamentos.stream()
                    .mapToDouble(Agendamento::getPrecoTotal)
                    .sum();
        }

        if (vendas != null) {
            total += vendas.stream()
                    .mapToDouble(Venda::getValorTotal)
                    .sum();
        }

        this.valorTotal = total;
    }

    // -------- MÉTODOS PARA ADICIONAR ITENS --------
    public void adicionarAgendamento(Agendamento ag) {
        if (ag != null) {
            agendamentos.add(ag);
            recalcularTotal();
        }
    }

    public void adicionarVenda(Venda v) {
        if (v != null) {
            vendas.add(v);
            recalcularTotal();
        }
    }

    // -------- GETTERS E SETTERS --------
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }


    public List<Agendamento> getAgendamentos() { return agendamentos; }
    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
        recalcularTotal();
    }

    public List<Venda> getVendas() { return vendas; }
    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
        recalcularTotal();
    }

    public LocalDateTime getDataAbertura() { return dataAbertura; }
    public void setDataAbertura(LocalDateTime dataAbertura) { this.dataAbertura = dataAbertura; }

    public LocalDateTime getDataFechamento() { return dataFechamento; }
    public void setDataFechamento(LocalDateTime dataFechamento) { this.dataFechamento = dataFechamento; }

    public double getValorTotal() { return valorTotal; }

    // -------- TO STRING COMPLETO --------
    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        String texto =
                "======= ORDEM DE SERVIÇO =======\n" +
                "ID: " + id + "\n" +
                "Cliente: " + (cliente != null ? cliente.getNome() : "N/A") + "\n" +
                "Data de Abertura: " + (dataAbertura != null ? dataAbertura.format(fmt) : "N/A") + "\n" +
                "Data de Fechamento: " + (dataFechamento != null ? dataFechamento.format(fmt) : "Ainda aberta") + "\n\n" +

                "----- Agendamentos -----\n";

        if (agendamentos.isEmpty()) {
            texto += "Nenhum agendamento vinculado.\n";
        } else {
            for (Agendamento ag : agendamentos) {
                texto += ag.toString() + "\n";
            }
        }

        texto += "\n----- Vendas -----\n";
        
        if (vendas.isEmpty()) {
            texto += "Nenhuma venda vinculada.\n";
        } else {
            for (Venda v : vendas) {
                texto += v.toString() + "\n";
            }
        }
        
        texto += "\nValor Total da OS: R$ " + valorTotal + "\n";
        return texto;
    }
}
