package teste;

import gerenciadores.GerenciadorAgendamentos;
import gerenciadores.GerenciadorPessoas;
import gerenciadores.GerenciadorProdutos;
import gerenciadores.GerenciadorServicos;
import gerenciadores.GerenciadorVendas;


public class QuestoesRespondidas {
    public static void main(String[] args) {
        
    GerenciadorPessoas gerenciadorPessoas = new GerenciadorPessoas();
    GerenciadorProdutos gerenciadorProdutos = new GerenciadorProdutos();
    GerenciadorAgendamentos gerenciadorAgendamentos = new GerenciadorAgendamentos();
    GerenciadorServicos gerenciadorServicos = new GerenciadorServicos();
    GerenciadorVendas gerendiadorVendas;
    
     /**
     * Questão 1: Diagrama de Classes criado no LaTeX
     */
     
    System.out.println("===========Questao 01================");
    System.out.println("      Diagrama criado no LaTeX!      ");
    
     /**
     * Questão 2: O sistema será utilizado pelos colaboradores e pelo administrador
     * 
     * O sistema é dividido em dois menus, um para Funcionarios com acoes limitadas
     * e um para Gerente com todas as acoes disponiveis
     * 
     */
    System.out.println("===========Questao 02================");
    System.out.println("O sistema e dividido em dois menus, um para Funcionarios com acoes limitadas\n" +
                       "e um para Gerente com todas as acoes disponiveis");
    System.out.println("-------------------------------------");
    System.out.println("\n----- MENU FUNCIONÁRIO -----");
            System.out.println("1 - Agendamentos");
            System.out.println("2 - Clientes");
            System.out.println("3 - Produtos");
            System.out.println("4 - Registrar Vendas");
            System.out.println("5 - Controle de Ponto");
            System.out.println("6 - Ordem de Servico"); 
            System.out.println("0 - Sair");
            System.out.println("Escolha: ");
    System.out.println("-------------------------------------");
            System.out.println("\n----- MENU GERENTE -----");
            System.out.println("1 - Agendamentos");
            System.out.println("2 - Clientes");
            System.out.println("3 - Funcionarios");
            System.out.println("4 - Servicos");
            System.out.println("5 - Produtos");
            System.out.println("6 - Despesas");
            System.out.println("7 - Registrar Vendas");
            System.out.println("8 - Controle de Ponto");
            System.out.println("9 - Ordem de Servico"); 
            System.out.println("0 - Sair");
            System.out.println("Escolha: ");

     /**
     * Questão 3: Sobrescrever o método toString() de todas as classes implementadas
     */
    System.out.println("===========Questao 03================");
    System.out.println("O metodo toString() de todas as classes presentes\n +"
                     + "na package modelo foram sobrescritas.      ");  
    
     /**
     * Questão 4: Utilizar a palavra-chave super para implementar os 
     * construtores das subclasses
     */
    System.out.println("===========Questao 04================");
    System.out.println("Nas classes Cliente, Funcionario e Gerente foi usado\n +"
                     + " a pallavra-chave super para implementar construtores");           
         
     /**
     * Questão 5: O sistema deverá armazenar de forma estática (Vetor com tamanho 
     * fixo) as informações das 3 estações de atendimento.
     */
    System.out.println("===========Questao 05================");
    System.out.println("Nas classes Cliente, Funcionario e Gerente foi usado\n +"
                     + " a pallavra-chave super para implementar construtores");     

     

    }
}
