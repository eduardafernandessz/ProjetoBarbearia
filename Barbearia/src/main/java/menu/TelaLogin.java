package menu;

import utils.Login;
import modelo.Funcionario;
import modelo.Gerente;

import java.util.Scanner;

    /**
     * Classe responsável por exibir e controlar o fluxo da tela inicial de login.
     *
     * <p>Esta tela permite que o usuário escolha entre logar como
     * Funcionário ou como Gerente. Após a seleção,
     * o método chama o sistema genérico de autenticação {@link Login}
     * para validar usuário e senha.</p>
     *
     * <p>O método {@link #iniciar(Login, Login)} retorna o objeto autenticado,
     * podendo ser um {@link Funcionario} ou um {@link Gerente}.
     * Caso a autenticação falhe, retorna {@code null}.</p>
     */
public class TelaLogin {

    /**
     * Inicia o fluxo de login da aplicação.
     *
     * <p>
     * O usuário escolhe o tipo de acesso:
     * 1 - Funcionário
     * 2 - Gerente
     * </p>
     *
     * <p>
     * Após a escolha, o sistema chama o método {@code autenticar()}
     * da instância de {@link Login} correspondente, que solicita
     * CPF/e-mail e senha, valida e retorna o objeto autenticado.
     * </p>
     *
     * <p>
     * Retorno:
     * Um objeto {@link Funcionario} autenticado;
     * Ou um objeto {@link Gerente} autenticado;
     * Ou {@code null} caso falhe.
     * </p>
     *
     * @param loginFunc sistema de autenticação para Funcionários
     * @param loginGer sistema de autenticação para Gerentes
     * @return o usuário autenticado (Funcionario ou Gerente), ou {@code null} se falhar
     */
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
                    System.out.println("\n Bem-vindo, " + f.getNome() + "!");
                    yield f;
                }
                yield null;
            }

            case 2 -> {
                Gerente g = loginGer.autenticar();
                if (g != null) {
                    System.out.println("\n Bem-vindo, " + g.getNome() + "!");
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
