package acesso;

import utils.CRUDGenerico;
import usuarios.Funcionario;
import usuarios.Gerente;

import java.util.List;

/**
 * Classe responsável pela autenticação de usuários.
 *
 * Agora usa CRUDGenerico para carregar listas de funcionários e gerentes
 * (ou seja, não depende diretamente de JSONUtils aqui).
 */
public class Login {

    // Caminhos dos arquivos usados pelo CRUDGenerico (ajuste se necessário)
    private static final String CAMINHO_FUNCIONARIOS = "src/repositorio/Funcionarios.json";
    private static final String CAMINHO_GERENTES = "src/repositorio/Gerente.json";

    /**
     * Tenta autenticar usando login e senha.
     *
     * Retorna:
     *  - um objeto Funcionario se for funcionário válido
     *  - um objeto Gerente se for gerente válido
     *  - null se credenciais inválidas
     *
     * Observação: assumo que as classes Usuarios têm getLogin() e getSenha().
     */
    public static Object autenticar(String login, int senha) {
        if (login == null) return null;
        String loginTrim = login.trim();

        // Usa CRUDGenerico para carregar a lista de funcionários
        CRUDGenerico<Funcionario> crudFunc = new CRUDGenerico<>(CAMINHO_FUNCIONARIOS, Funcionario.class);
        List<Funcionario> funcionarios = crudFunc.listar();

        if (funcionarios != null) {
            for (Funcionario f : funcionarios) {
                if (f == null) continue;
                String fLogin = f.getLogin() == null ? "" : f.getLogin().trim();
                if (fLogin.equals(loginTrim) && f.getSenha() == senha) {
                    // autenticado como funcionário
                    return f;
                }
            }
        }

        // Usa CRUDGenerico para carregar a lista de gerentes
        CRUDGenerico<Gerente> crudGer = new CRUDGenerico<>(CAMINHO_GERENTES, Gerente.class);
        List<Gerente> gerentes = crudGer.listar();

        if (gerentes != null) {
            for (Gerente g : gerentes) {
                if (g == null) continue;
                String gLogin = g.getLogin() == null ? "" : g.getLogin().trim();
                if (gLogin.equals(loginTrim) && g.getSenha() == senha) {
                    // autenticado como gerente
                    return g;
                }
            }
        }

        // não autenticado
        return null;
    }
}
