package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

    /**
     * Representa uma Ordem de Serviço (OS) na barbearia.
     * <p>
     * Cada Ordem de Serviço está associada a um cliente, pode conter vários agendamentos
     * e vendas, possui datas de abertura e fechamento, e calcula automaticamente o valor total.
     * </p>
     */
public class OrdemDeServico {

    /** Identificador único da Ordem de Serviço. */
    private int id;

    /** Cliente associado à Ordem de Serviço. */
    private Cliente cliente;

    /** Lista de agendamentos vinculados à Ordem de Serviço. */
    private List<Agendamento> agendamentos;

    /** Lista de vendas vinculadas à Ordem de Serviço. */
    private List<Venda> vendas;

    /** Data e hora de abertura da Ordem de Serviço. */
    private LocalDateTime dataAbertura;

    /** Data e hora de fechamento da Ordem de Serviço. */
    private LocalDateTime dataFechamento;

    /** Valor total da Ordem de Serviço, considerando agendamentos e vendas. */
    private double valorTotal;

    /**
     * Construtor principal da Ordem de Serviço.
     * <p>
     * Inicializa a OS com um cliente e um identificador, além de listas vazias
     * para agendamentos e vendas. Define a data de abertura como o momento atual
     * e calcula o valor total inicial.
     * </p>
     *
     * @param id Identificador único da Ordem de Serviço.
     * @param cliente Cliente associado à Ordem de Serviço.
     */
    public OrdemDeServico(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.agendamentos = new ArrayList<>();
        this.vendas = new ArrayList<>();
        this.dataAbertura = LocalDateTime.now();
        recalcularTotal();
    }

    /**
     * Construtor vazio.
     * <p>
     * Inicializa as listas de agendamentos e vendas como vazias.
     * </p>
     */
    public OrdemDeServico() {
        this.agendamentos = new ArrayList<>();
        this.vendas = new ArrayList<>();
    }

    /**
     * Recalcula o valor total da Ordem de Serviço.
     * <p>
     * Soma o valor de todos os agendamentos e vendas associados.
     * </p>
     */
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

    /**
     * Adiciona um agendamento à Ordem de Serviço e atualiza o valor total.
     *
     * @param ag Agendamento a ser adicionado.
     */
    public void adicionarAgendamento(Agendamento ag) {
        if (ag != null) {
            agendamentos.add(ag);
            recalcularTotal();
        }
    }

    /**
     * Adiciona uma venda à Ordem de Serviço e atualiza o valor total.
     *
     * @param v Venda a ser adicionada.
     */
    public void adicionarVenda(Venda v) {
        if (v != null) {
            vendas.add(v);
            recalcularTotal();
        }
    }

    /**
     * Retorna o identificador da Ordem de Serviço.
     *
     * @return id da OS.
     */
    public int getId() { 
        return id; 
    }

    /**
     * Define o identificador da Ordem de Serviço.
     *
     * @param id Identificador a ser definido.
     */
    public void setId(int id) { 
        this.id = id; 
    }

    /**
     * Retorna o cliente associado à Ordem de Serviço.
     *
     * @return Cliente da OS.
     */
    public Cliente getCliente() { 
        return cliente; 
    }

    /**
     * Define o cliente associado à Ordem de Serviço.
     *
     * @param cliente Cliente a ser associado.
     */
    public void setCliente(Cliente cliente) { 
        this.cliente = cliente; 
    }

    /**
     * Retorna a lista de agendamentos da Ordem de Serviço.
     *
     * @return Lista de agendamentos.
     */
    public List<Agendamento> getAgendamentos() { 
        return agendamentos; 
    }

    /**
     * Define a lista de agendamentos da Ordem de Serviço e atualiza o valor total.
     *
     * @param agendamentos Lista de agendamentos a ser definida.
     */
    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
        recalcularTotal();
    }

    /**
     * Retorna a lista de vendas da Ordem de Serviço.
     *
     * @return Lista de vendas.
     */
    public List<Venda> getVendas() { 
        return vendas; 
    }

    /**
     * Define a lista de vendas da Ordem de Serviço e atualiza o valor total.
     *
     * @param vendas Lista de vendas a ser definida.
     */
    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
        recalcularTotal();
    }

    /**
     * Retorna a data de abertura da Ordem de Serviço.
     *
     * @return Data de abertura.
     */
    public LocalDateTime getDataAbertura() { 
        return dataAbertura; 
    }

    /**
     * Define a data de abertura da Ordem de Serviço.
     *
     * @param dataAbertura Data de abertura a ser definida.
     */
    public void setDataAbertura(LocalDateTime dataAbertura) { 
        this.dataAbertura = dataAbertura; 
    }

    /**
     * Retorna a data de fechamento da Ordem de Serviço.
     *
     * @return Data de fechamento.
     */
    public LocalDateTime getDataFechamento() { 
        return dataFechamento; 
    }

    /**
     * Define a data de fechamento da Ordem de Serviço.
     *
     * @param dataFechamento Data de fechamento a ser definida.
     */
    public void setDataFechamento(LocalDateTime dataFechamento) { 
        this.dataFechamento = dataFechamento; 
    }

    /**
     * Retorna o valor total da Ordem de Serviço.
     *
     * @return Valor total.
     */
    public double getValorTotal() { 
        return valorTotal; 
    }

    /**
     * Retorna uma representação em String da Ordem de Serviço, incluindo
     * informações do cliente, datas, agendamentos, vendas e valor total.
     *
     * @return String formatada da OS.
     */
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
