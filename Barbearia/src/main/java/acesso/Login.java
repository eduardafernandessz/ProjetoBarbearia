package acesso;

import org.json.JSONArray;
import org.json.JSONObject;
import utils.JSONUtils;

public class Login {

    private static final String CAMINHO_FUNCIONARIOS = "src/repositorio/Funcionarios.json";
    private static final String CAMINHO_GERENTES = "src/repositorio/Gerente.json";

    public static JSONObject autenticar(String login, int senha) {

        // Primeiro tenta funcionário
        JSONArray funcionarios = JSONUtils.lerArquivo(CAMINHO_FUNCIONARIOS);

        for (int i = 0; i < funcionarios.length(); i++) {
            JSONObject f = funcionarios.getJSONObject(i);

            if (f.getString("login").equals(login) && f.getInt("senha") == senha) {
                f.put("tipoUsuario", "Funcionario");
                return f;
            }
        }

        // Depois tenta gerente
        JSONArray gerentes = JSONUtils.lerArquivo(CAMINHO_GERENTES);

        for (int i = 0; i < gerentes.length(); i++) {
            JSONObject g = gerentes.getJSONObject(i);

            if (g.getString("login").equals(login) && g.getInt("senha") == senha) {
                g.put("tipoUsuario", "Gerente");
                return g;
            }
        }

        // Ninguém encontrado
        return null;
    }
}

