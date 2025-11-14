package menu;

import java.util.Scanner;
import controle.GerenciarPessoas;

public class MenuFuncionario {
        public void exibir() {

        Scanner scanner = new Scanner(System.in);
        GerenciarPessoas gp = new GerenciarPessoas(true);

        int opcao;

        do {
            System.out.println("\n------ MENU FUNCIONÁRIO ------");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Listar Clientes");
            System.out.println("3 - Editar Cliente");
            System.out.println("4 - Excluir Cliente");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> gp.cadastrarPessoa(scanner);
                case 2 -> gp.listarPessoas(scanner);
                case 3 -> gp.editarPessoa(scanner);
                case 4 -> gp.excluirPessoa(scanner);
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }
}
