package utils;

import java.util.List;
import java.util.Scanner;

public class Login<T> {

    private final List<T> usuarios;

    public Login(utils.CRUDGenerico<T> crud) {
        this.usuarios = crud.listar();
    }

    public T autenticar() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite login: ");
        String login = sc.nextLine();

        System.out.print("Digite senha: ");
        int senha = sc.nextInt();
        sc.nextLine();

        for (T usuario : usuarios) {
            try {
                String userLogin = (String) usuario.getClass().getMethod("getLogin").invoke(usuario);
                int userSenha = (int) usuario.getClass().getMethod("getSenha").invoke(usuario);

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
