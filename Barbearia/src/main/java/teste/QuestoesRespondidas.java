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
    System.out.println("Existe a classe Estacao em modelo\n +"
                     + "o gerenciamento acontece em GerenciadorAtendimento");     

     /**
     * Questão 6: Deve ser possível cadastrar os colaboradores no sistema, 
     * alterar ou editar seus atributos;
     */    
    System.out.println("===========Questao 06================");
    System.out.println("No menu do Gerente, tem a opcao 3 - Funcionarios,\n" +
                       "nela é chamado o menu onde todas essas acoes pode ser realizadas\n " +
                       "Este menu esta em MenuPessoa");
    System.out.println("\n--- MENU GERENCIAR FUNCIONÁRIOS ---");
    System.out.println("1 - Listar Funcionários");
    System.out.println("2 - Adicionar Funcionário");
    System.out.println("3 - Buscar Funcionário por ID");
    System.out.println("4 - Editar Funcionário");
    System.out.println("5 - Remover Funcionário");
    System.out.println("6 - Salvar Alterações");
    System.out.println("0 - Voltar");
    System.out.print("Escolha: ");
    System.out.println("-------------------------------------");
    System.out.println("Cada opcao dessa chama o metodo correspondente\n" +
                           "no GerenciadorPessoas");
        
     /**
     * Questão 7: Cadastrar, alterar ou excluir clientes;
     */ 
    System.out.println("===========Questao 07================");
    System.out.println("No menu do Funcionario e do Gerente tem a opcao de\n " + 
                       "manipular clientes, que chama o MenuPessoa e exibe o menu\n " +
                       "para gerenciar os clientes");
    System.out.println("\n----- MENU GERENCIAR CLIENTES -----");
    System.out.println("1 - Listar Clientes");
    System.out.println("2 - Adicionar Cliente");
    System.out.println("3 - Verificar existencia do Cliente(Buscar)");
    System.out.println("4 - Editar Cliente");
    System.out.println("5 - Remover Cliente");
    System.out.println("6 - Salvar Alterações");
    System.out.println("0 - Voltar");
    System.out.print("Escolha: ");
    System.out.println("-------------------------------------");
    System.out.println("Cada opcao dessa chama o metodo correspondente\n" +
                       "no GerenciadorPessoas");
    
     /**
     * Questão 8: Verificar e imprimir dados das ordens de serviço de cada cliente
     */ 
    System.out.println("===========Questao 08================");
    System.out.println("A partir do menu principal e possivel selecionar \n" +
                       "Ordem de servico. Essa opcao chama o MenuOrdemDeServico\n " +
                       "onde e possivel selecionar o cliente e fazer as operacoes la presente");
    
     /**
     * Questão 9: As ordens de serviço, ações do estoque, da loja e os clientes 
     * devem ser salvos de forma dinâmica no sistema.
     */ 
    System.out.println("===========Questao 09================");
    System.out.println("Em todos os menus existe a opcao salvar alteracoes \n" +
                       "apenas quando selecionada as informacoes vao para o json,\n " +
                       "se nao chamar elas ficam na memoria heap");
    
    
    
    
    
    
    
     /**
     * Questão 14: Salve e recupere todas as informações dos Clientes, Serviços, 
     * Agendamentos, Produtos, RelatóriosdeVendas, Colaboradores, Estoque, etc… em um 
     * arquivo de texto. Utilizem classes já prontas na internet que trabalhem com 
     * o formato json. Ao manipular um arquivo utilize os conceitos aprendidos em 
     * aula para alocar e desalocar recursos com segurança. 
     */ 
         System.out.println("===========Questao 14================");
    System.out.println("A classe JSONUtils e responsavel por converte qualquer\n " +
                       "objeto Java em uma string JSON, e vise versqa'");
     
    
     


    

     

    }
}
