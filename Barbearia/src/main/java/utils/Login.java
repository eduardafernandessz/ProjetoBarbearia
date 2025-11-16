package utils;

import java.util.List;
import java.util.Scanner;

/**
 * Classe para autenticação de usuários.
 *
 * <p>Permite verificar login e senha de qualquer tipo de usuário que possua os métodos
 * getLogin() e getSenha().</p>
 *
 * @param <T> Tipo do usuário
 */
public class Login<T> {

    /** Lista de usuários disponíveis para autenticação. */
    private final List<T> usuarios;

    /**
     * Construtor que recebe um CRUD genérico e carrega todos os usuários cadastrados.
     *
     * @param crud CRUD genérico contendo os usuários
     */
    public Login(utils.CRUDGenerico<T> crud) {
        this.usuarios = crud.listar();
    }

    /**
    * Solicita login e senha ao usuário e verifica se existe um usuário correspondente.
    *
    * <p>O método percorre todos os usuários cadastrados e compara os valores de login
    * e senha informados com os métodos getLogin() e getSenha() de cada usuário.</p>
    *
    * @return O usuário autenticado se login e senha forem corretos, ou null se não for
    */
    public T autenticar() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite login: ");
        String login = sc.nextLine();

        System.out.print("Digite senha: ");
        int senha = sc.nextInt();
        sc.nextLine();

        // Percorre todos os usuários cadastrados
        for (T usuario : usuarios) {
            try {
                String userLogin = (String) usuario.getClass().getMethod("getLogin").invoke(usuario);
                int userSenha = (int) usuario.getClass().getMethod("getSenha").invoke(usuario);

                // Verifica se login e senha conferem
                if (userLogin.equalsIgnoreCase(login) && userSenha == senha) {
                    return usuario;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Login ou senha incorretos!");
        return null;
    }
}
