package teste;

import gerenciadores.GerenciadorAgendamentos;
import gerenciadores.GerenciadorOrdemDeServico;
import gerenciadores.GerenciadorPessoas;
import gerenciadores.GerenciadorProdutos;
import gerenciadores.GerenciadorServicos;
import gerenciadores.GerenciadorVendas;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import modelo.Agendamento;
import modelo.Cliente;
import modelo.Estacao;
import modelo.Funcionario;
import modelo.OrdemDeServico;
import modelo.Produto;
import modelo.Servico;
import modelo.Venda;



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
            
        List<Cliente> testeCliente = new ArrayList<>();

        Cliente c1 = new Cliente(1, "Maria Silva", "123.456.789-01", "99999-0001", "maria.silva@email.com", "Rua A, 10");
        Cliente c2 = new Cliente(2, "João Pereira", "987.654.321-02", "99999-0002", "joao.pereira@email.com", "Rua B, 20");
        Cliente c3 = new Cliente(3, "Ana Costa", "456.789.123-03", "99999-0003", "ana.costa@email.com", "Rua C, 30");
        Cliente c4 = new Cliente(4, "Carlos Souza", "321.654.987-04", "99999-0004", "carlos.souza@email.com", "Rua D, 40");
        Cliente c5 = new Cliente(5, "Juliana Rocha", "159.753.486-05", "99999-0005", "juliana.rocha@email.com", "Rua E, 50");
        Cliente c6 = new Cliente(6, "Rafael Mendes", "753.159.842-06", "99999-0006", "rafael.mendes@email.com", "Rua F, 60");
        Cliente c7 = new Cliente(7, "Beatriz Almeida", "852.456.951-07", "99999-0007", "bia.almeida@email.com", "Rua G, 70");
        Cliente c8 = new Cliente(8, "Gabriel Santos", "951.357.258-08", "99999-0008", "gabriel.santos@email.com", "Rua H, 80");
        Cliente c9 = new Cliente(9, "Fernanda Lima", "258.147.369-09", "99999-0009", "fernanda.lima@email.com", "Rua I, 90");
        Cliente c10 = new Cliente(10, "Pedro Oliveira", "369.258.147-10", "99999-0010", "pedro.oliveira@email.com", "Rua J, 100");

        testeCliente.add(c1);
        testeCliente.add(c2);
        testeCliente.add(c3);
        testeCliente.add(c4);
        testeCliente.add(c5);
        testeCliente.add(c6);
        testeCliente.add(c7);
        testeCliente.add(c8);
        testeCliente.add(c9);
        testeCliente.add(c10);


        System.out.println("Clientes cadastrados:");
            for (Cliente c : testeCliente) {
            System.out.println(c);
        }

        System.out.print("\n");
        System.out.println("=====================================================\n");


        Funcionario f1 = gp.buscarFuncionarioPorId(1);
        Funcionario f3 = gp.buscarFuncionarioPorId(3);
        Funcionario f5 = gp.buscarFuncionarioPorId(5);

        Servico s1 = gserv.buscarPorId(1);
        Servico s2 = gserv.buscarPorId(2);
        Servico s3 = gserv.buscarPorId(3);
        Servico s4 = gserv.buscarPorId(4);
        Servico s5 = gserv.buscarPorId(5);
        Servico s6 = gserv.buscarPorId(6);
        
        List<Agendamento> testeAgendamento = new ArrayList<>();

        Agendamento a1 = new Agendamento(1, c1, f1, List.of(s1), LocalDateTime.of(2024,11,22,9,0), Estacao.CADEIRA_1);
        Agendamento a2 = new Agendamento(2, c3, f5, List.of(s3), LocalDateTime.of(2024,11,22,11,0), Estacao.CADEIRA_1);
        Agendamento a4 = new Agendamento(3, c2, f3, List.of(s2), LocalDateTime.of(2024,11,22,10,0), Estacao.CADEIRA_2);
        Agendamento a3 = new Agendamento(4, c4, f1, List.of(s4), LocalDateTime.of(2024,11,22,14,0), Estacao.CADEIRA_2);
        Agendamento a5 = new Agendamento(5, c5, f3, List.of(s5), LocalDateTime.of(2024,11,22,15,0), Estacao.LAVATORIO);
        Agendamento a6 = new Agendamento(6, c6, f5, List.of(s6), LocalDateTime.of(2024,11,22,16,0), Estacao.LAVATORIO);
        Agendamento a7 = new Agendamento(7, c7, f1, List.of(s1), LocalDateTime.of(2024,11,23,9,30), Estacao.CADEIRA_1);
        Agendamento a8 = new Agendamento(8, c8, f3, List.of(s2), LocalDateTime.of(2024,11,23,10,30), Estacao.CADEIRA_2);
        Agendamento a9 = new Agendamento(9, c9, f5, List.of(s3), LocalDateTime.of(2024,11,23,11,30), Estacao.CADEIRA_1);
        Agendamento a10 = new Agendamento(10, c10, f1, List.of(s4), LocalDateTime.of(2024,11,23,14,0), Estacao.CADEIRA_2);
        
        testeAgendamento.add(a1);
        testeAgendamento.add(a2);
        testeAgendamento.add(a3);
        testeAgendamento.add(a4);
        testeAgendamento.add(a5);
        testeAgendamento.add(a6);
        testeAgendamento.add(a7);
        testeAgendamento.add(a8);
        testeAgendamento.add(a9);
        testeAgendamento.add(a10);

        System.out.println("Agendamentos cadastrados:");
            for (Agendamento a : testeAgendamento) {
            System.out.println(a);
        }
            
        System.out.print("\n");
        System.out.println("=====================================================\n");

        List<Produto> testeProduto = new ArrayList<>();
        
        Produto p1 = gprod.buscarProdutoPorId(1);
        Produto p2 = gprod.buscarProdutoPorId(2);
        Produto p3 = gprod.buscarProdutoPorId(3);
        Produto p4 = gprod.buscarProdutoPorId(4);
        Produto p5 = gprod.buscarProdutoPorId(5);
        
        testeProduto.add(p1);
        testeProduto.add(p2);
        testeProduto.add(p3);
        testeProduto.add(p4);
        testeProduto.add(p5);
        
        System.out.println("Estoque dos Produtos:");
            for (Produto p : testeProduto) {
            System.out.println(p);
        }

        List<Venda> testeVendas = new ArrayList<>();

        Venda v1 = new Venda(1, c1, p1, 1, LocalDate.now());
        Venda v2 = new Venda(2, c2, p3, 2, LocalDate.now());
        Venda v3 = new Venda(3, c3, p2, 1, LocalDate.now());
        Venda v4 = new Venda(4, c4, p1, 3, LocalDate.now());
        Venda v5 = new Venda(5, c5, p4, 1, LocalDate.now());
        Venda v6 = new Venda(6, c6, p2, 2, LocalDate.now());
        Venda v7 = new Venda(7, c7, p3, 1, LocalDate.now());
        Venda v8 = new Venda(8, c8, p1, 1, LocalDate.now());
        Venda v9 = new Venda(9, c9, p2, 2, LocalDate.now());
        Venda v10 = new Venda(10, c10, p4, 1, LocalDate.now());
        
        testeVendas.add(v1);
        testeVendas.add(v2);
        testeVendas.add(v3);
        testeVendas.add(v4);
        testeVendas.add(v5);
        testeVendas.add(v6);
        testeVendas.add(v7);
        testeVendas.add(v8);
        testeVendas.add(v9);
        testeVendas.add(v10);

        System.out.println("Vendas cadastrados:");
            for (Venda v : testeVendas) {
            System.out.println(v);
        }
            
        System.out.print("\n");
        System.out.println("=====================================================\n");
        
        System.out.println("Estoque dos Produtos depois:");
            for (Produto p : testeProduto) {
            System.out.println(p);
        }
            
        System.out.print("\n");
        System.out.println("=====================================================\n");
    
        List<OrdemDeServico> testeOs = new ArrayList<>();
        
        OrdemDeServico os1 = new OrdemDeServico(1, c1); os1.adicionarAgendamento(a1); os1.adicionarVenda(v1);
        OrdemDeServico os2 = new OrdemDeServico(2, c2); os2.adicionarAgendamento(a2); os2.adicionarVenda(v2);
        OrdemDeServico os3 = new OrdemDeServico(3, c3); os3.adicionarAgendamento(a3); os3.adicionarVenda(v3);
        OrdemDeServico os4 = new OrdemDeServico(4, c4); os4.adicionarAgendamento(a4); os4.adicionarVenda(v4);
        OrdemDeServico os5 = new OrdemDeServico(5, c5); os5.adicionarAgendamento(a5); os5.adicionarVenda(v5);
        OrdemDeServico os6 = new OrdemDeServico(6, c6); os6.adicionarAgendamento(a6); os6.adicionarVenda(v6);
        OrdemDeServico os7 = new OrdemDeServico(7, c7); os7.adicionarAgendamento(a7); os7.adicionarVenda(v7);
        OrdemDeServico os8 = new OrdemDeServico(8, c8); os8.adicionarAgendamento(a8); os8.adicionarVenda(v8);
        OrdemDeServico os9 = new OrdemDeServico(9, c9); os9.adicionarAgendamento(a9); os9.adicionarVenda(v9);
        OrdemDeServico os10 = new OrdemDeServico(10, c10); os10.adicionarAgendamento(a10); os10.adicionarVenda(v10);
        
        testeOs.add(os1);
        testeOs.add(os2);
        testeOs.add(os3);
        testeOs.add(os4);
        testeOs.add(os5);
        testeOs.add(os6);
        testeOs.add(os7);
        testeOs.add(os8);
        testeOs.add(os9);
        testeOs.add(os10);
        
        System.out.println("Ordens de Servico cadastradas:");
            for (OrdemDeServico os: testeOs) {
            System.out.println(os);
        }
            
        System.out.print("\n");
        System.out.println("=====================================================\n");
            
    }
}

     

    

