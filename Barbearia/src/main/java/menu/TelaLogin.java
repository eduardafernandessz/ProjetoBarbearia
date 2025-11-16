package menu;

import utils.Login;
import modelo.Funcionario;
import modelo.Gerente;

import java.util.Scanner;

public class TelaLogin {

    public static Object iniciar(Login<Funcionario> loginFunc, Login<Gerente> loginGer) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===================================");
        System.out.println("            BARBEARIA");
        System.out.println("===================================");
        System.out.println("             LOGIN");
        System.out.println("===================================\n");

        System.out.println("1 - Funcionário");
        System.out.println("2 - Gerente");
        System.out.print("Escolha: ");

        int opc = sc.nextInt();
        sc.nextLine();

        return switch (opc) {
            case 1 -> {
                Funcionario f = loginFunc.autenticar();
                if (f != null) {
                    System.out.println("\n✔ Bem-vindo, " + f.getNome() + "!");
                    yield f;
                }
                yield null;
            }

            case 2 -> {
                Gerente g = loginGer.autenticar();
                if (g != null) {
                    System.out.println("\n✔ Bem-vindo, " + g.getNome() + "!");
                    yield g;
                }
                yield null;
            }

            default -> {
                System.out.println("Opção inválida!");
                yield null;
            }
        };
    }
}
