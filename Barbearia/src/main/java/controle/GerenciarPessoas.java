package controle;

import menu.MenuFuncionario;
import menu.MenuGerente;

import java.util.Scanner;


public class GerenciarPessoas {
        Scanner scanner = new Scanner(System.in);
        MenuFuncionario menufunc = new MenuFuncionario();
        MenuGerente menuger = new MenuGerente();
        
        public void exibirMenu(){
            System.out.println("\n====== SISTEMA BARBEARIA ======");
            System.out.println("1 - Entrar como Gerente");
            System.out.println("2 - Entrar como Funcionário");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {

                case 1 -> {
                    System.out.println("\n>>> MODO GERENTE <<<");
                    menuger.menuGerente();
                }

                case 2 -> {
                    System.out.println("\n>>> MODO FUNCIONÁRIO <<<");
                    menufunc.menuFuncionario();
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
            scanner.close();
        }
}