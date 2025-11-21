package teste;

import gerenciadores.GerenciadorAgendamentos;
import gerenciadores.GerenciadorOrdemDeServico;
import gerenciadores.GerenciadorPessoas;
import gerenciadores.GerenciadorProdutos;
import gerenciadores.GerenciadorServicos;
import gerenciadores.GerenciadorVendas;
import modelo.Cliente;
import modelo.Funcionario;



public class QuestoesRespondidas {
    public static void main(String[] args) {
        
    questao q = new questao();
        
        GerenciadorPessoas gp = new GerenciadorPessoas();
        GerenciadorProdutos gprod = new GerenciadorProdutos();
        GerenciadorServicos gserv = new GerenciadorServicos();
        GerenciadorVendas gv = new GerenciadorVendas(gp, gprod);
        GerenciadorAgendamentos ga = new GerenciadorAgendamentos();
        GerenciadorOrdemDeServico gos = new GerenciadorOrdemDeServico(gp, ga, gv);
        

     //Questão 1: Diagrama de Classes criado no LaTeX
     
     
    System.out.println("===========Questao 01================");
    System.out.println("      Diagrama criado no LaTeX!      ");
    

     // Questão 2: O sistema será utilizado pelos colaboradores e pelo administrador 
     // O sistema é dividido em dois menus, um para Funcionarios com acoes limitadas
     // e um para Gerente com todas as acoes disponiveis
     
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

     
     // Questão 3: Sobrescrever o método toString() de todas as classes implementadas
     
    System.out.println("===========Questao 03================");
    System.out.println("O metodo toString() de todas as classes presentes\n +"
                     + "na package modelo foram sobrescritas.      ");  
    
     
     // Questão 4: Utilizar a palavra-chave super para implementar os 
     // construtores das subclasses
     
    System.out.println("===========Questao 04================");
    System.out.println("Nas classes Cliente, Funcionario e Gerente foi usado\n +"
                     + " a pallavra-chave super para implementar construtores");           
         
     
     // Questão 5: O sistema deverá armazenar de forma estática (Vetor com tamanho 
     // fixo) as informações das 3 estações de atendimento.
     
    System.out.println("===========Questao 05================");
    System.out.println("Existe a classe Estacao em modelo\n +"
                     + "o gerenciamento acontece em GerenciadorAgendamento");     

     
     // Questão 6: Deve ser possível cadastrar os colaboradores no sistema, 
     // alterar ou editar seus atributos;
        
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
        
     
     // Questão 7: Cadastrar, alterar ou excluir clientes;
      
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
    
     
     // Questão 8: Verificar e imprimir dados das ordens de serviço de cada cliente
     
    System.out.println("===========Questao 08================");
    System.out.println("A partir do menu principal e possivel selecionar \n" +
                       "Ordem de servico. Essa opcao chama o MenuOrdemDeServico\n " +
                       "onde e possivel selecionar o cliente e fazer as operacoes la presente");
    
     
     // Questão 9: As ordens de serviço, ações do estoque, da loja e os clientes 
     // devem ser salvos de forma dinâmica no sistema.
    System.out.println("===========Questao 09================");
    System.out.println("Em todos os menus existe a opcao salvar alteracoes \n" +
                       "apenas quando selecionada as informacoes vao para o json,\n " +
                       "se nao chamar elas ficam na memoria heap");
    
    
 
    
    
     
     // Questão 14: Salve e recupere todas as informações dos Clientes, Serviços, 
     // Agendamentos, Produtos, RelatóriosdeVendas, Colaboradores, Estoque, etc… em um 
     // arquivo de texto. Utilizem classes já prontas na internet que trabalhem com 
     // o formato json. Ao manipular um arquivo utilize os conceitos aprendidos em 
     // aula para alocar e desalocar recursos com segurança. 
      
         System.out.println("===========Questao 14================");
    System.out.println("Na leitura JSON, mesmo usando Jackson, abri explicitamente o arquivo\n " +
        "com FileReader dentro de um try-with-resources. Assim garanto o \n " +
        "gerenciamento seguro do recurso (arquivo), evitando vazamentos e \n " +
        "atendendo exatamente à exigência da questão.\n "
        );
     

     // Questão 15: Instaciar um iterator para a arraylist de pessoas/funcionario/cliente (qual estiver usando)
     // Fazer testes no main em pecorrer o arraylist com chamadas usando o código: 
     // while(iterator.hasnext())
     // { 
     // imprimir(iterator.next());
     // }
     // Explicar como isso está acontecendo.
     // Qual relação do código acima com o foreach em java? 
    System.out.println("===========Questao 15================");
    System.out.println("O codigo esta na teste.questao\n "
            + "O código percorre a lista primeiro usando um Iterator, que verifica\n "
            + "se há proximos elementos com hasNext() e os retorna com next(). Essa\n "
            + "abordagem da controle direto sobre a iteração. Em seguida, a lista e\n "
            + "percorrida com um foreach, que faz a mesma coisa, porem de forma\n "
            + "simplificada. O foreach utiliza internamente um Iterator, mas esconde essa\n "
            + "logica, deixando o código mais limpo"); 
    System.out.println("\n========================================");
    System.out.println("            TESTE QUESTÃO 15");
    System.out.println("========================================\n");
    q.questao15();   // Lista clientes usando Iterator e foreach

        

     // Questão 16: Apresentar no main testes do comparator implementado.
     // Utilizar e apresentar no main a aplicação do método sort da classe collections 
     // passando o comparator criado para ordenar a lista de pessoas/usuario/cliente 
     // (qual estiver usando) com dois paramêtros diferentes. Ou seja, rodar duas vezes.
 
    System.out.println("===========Questao 16================");
    System.out.println("A Questão 16 demonstra o uso da interface Comparator para ordenar\n "
             + "uma lista de clientes carregada do JSON. O método realiza duas ordenações\n "
             + "diferentes usando Collections.sort(): primeiro por nome e depois por ID,\n "
             + "mostrando como mudar facilmente o critério de ordenação de uma mesma lista.\n");
        System.out.println("\n========================================");
        System.out.println("            TESTE QUESTÃO 16");
        System.out.println("========================================\n");
        q.questao16();   // Ordenação com Comparator (Nome e ID)


    // Questão 17: Apresentar no main testes do comparator implementado.
    // Utilizar e apresentar no main a aplicação do método sort da classe collections 
    // passando o comparator criado para ordenar a lista de pessoas/usuario/cliente 
    // (qual estiver usando) com dois paramêtros diferentes. Ou seja, rodar duas vezes.
     
    System.out.println("===========Questao 17================");   
    System.out.println("A Questão 17 demonstra duas formas de buscar clientes pelo nome:\n "
            + "uma busca personalizada usando Iterator e Comparator (método find), e outra\n "
            + "usando Collections.binarySearch() após ordenar a lista. O objetivo é comparar\n "
            + "os resultados e entender como funciona a busca linear versus a busca binária.");
        System.out.println("\n========================================");
        System.out.println("            TESTE QUESTÃO 17");
        System.out.println("========================================\n");
        q.questao17();   // Busca com find + binarySearch
        
    //Questao 18: Apresentar o funcionamento básico para o atendimento de 10 clientes da barbearia, desde o 
    //cadastro do cliente até a criação das ordens de serviço para cada atendimento, com as baixas no 
    //estoque e denotação correta dos serviços realizados, até finalizar com a emissão de nota 
    //fiscal após a conlusão de todos os processos.
        System.out.println("===========Questao 18================"); 
      
    gp.adicionarCliente(new Cliente(1, "Maria Silva", "123.456.789-01", "99999-0001", "maria.silva@email.com", "Rua A, 10"));
    gp.adicionarCliente(new Cliente(2, "João Pereira", "987.654.321-02", "99999-0002", "joao.pereira@email.com", "Rua B, 20"));
    gp.adicionarCliente(new Cliente(3, "Ana Costa", "456.789.123-03", "99999-0003", "ana.costa@email.com", "Rua C, 30"));
    gp.adicionarCliente(new Cliente(4, "Carlos Souza", "321.654.987-04", "99999-0004", "carlos.souza@email.com", "Rua D, 40"));
    gp.adicionarCliente(new Cliente(5, "Juliana Rocha", "159.753.486-05", "99999-0005", "juliana.rocha@email.com", "Rua E, 50"));
    gp.adicionarCliente(new Cliente(6, "Rafael Mendes", "753.159.842-06", "99999-0006", "rafael.mendes@email.com", "Rua F, 60"));
    gp.adicionarCliente(new Cliente(7, "Beatriz Almeida", "852.456.951-07", "99999-0007", "bia.almeida@email.com", "Rua G, 70"));
    gp.adicionarCliente(new Cliente(8, "Gabriel Santos", "951.357.258-08", "99999-0008", "gabriel.santos@email.com", "Rua H, 80"));
    gp.adicionarCliente(new Cliente(9, "Fernanda Lima", "258.147.369-09", "99999-0009", "fernanda.lima@email.com", "Rua I, 90"));
    gp.adicionarCliente(new Cliente(10, "Pedro Oliveira", "369.258.147-10", "99999-0010", "pedro.oliveira@email.com", "Rua J, 100"));

    
    gp.salvar();
    
    System.out.println("Clientes cadastrados:");
    for (Cliente c : gp.listarClientes()) {
    System.out.println(c);
    }
    
    }
}

     

    

