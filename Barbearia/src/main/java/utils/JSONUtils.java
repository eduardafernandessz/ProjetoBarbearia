package utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Classe utilitaria responsavel por ler e salvar dados em arquivos JSON
 * Pode ser usada em qualuqer parte do codigo(cadastrar pessoas, agendamentos, servicos, etc)
 **/


public class JSONUtils {

    /**
     * lê o conteudo de um arquivo JSON e retorna como JSONArray
     * Se o arquivo não existir, retorna um JSONArray vazio
     **/

    public static JSONArray lerArquivo(String caminho) {
    try {
        String conteudo = new String(Files.readAllBytes(Paths.get(caminho)));
        conteudo = conteudo.trim(); // remove espaços em branco antes e depois

        if (conteudo.startsWith("[")) {
            // É um JSONArray
            return new JSONArray(conteudo);
        } else if (conteudo.startsWith("{")) {
            // É um JSONObject, então criamos um array com esse objeto
            JSONObject obj = new JSONObject(conteudo);
            JSONArray lista = new JSONArray();
            lista.put(obj);
            return lista;
        } else {
            // Arquivo vazio ou formato desconhecido
            return new JSONArray();
        }

    } catch (Exception e) {
        // Se der erro na leitura, retorna array vazio
        return new JSONArray();
    }
}


    /**
     * Salva o JSONArray em um arquivo JSON com 4 espaços
     **/
     public static void salvarArquivo(String caminho, JSONArray dados) {
         try(FileWriter file = new FileWriter(caminho)) {
             file.write(dados.toString(4));
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

    /**
     * Adiciona um JSONObject a um arquivo JSON existente.
     *
     * Se o arquivo não existir, cria um novo JSONArray e adiciona o objeto.
     *
     * @param caminho - O caminho do arquivo JSON onde será adicionado o objeto.
     * @param obj - O JSONObject que será adicionado ao arquivo.
     */
    public static void adicionarObjeto(String caminho, JSONObject obj) {
        // Lê os dados existentes do arquivo
        JSONArray lista = lerArquivo(caminho); // usa o metodo lerArquivo ja criado

        // Adiciona o novo objeto ao JSONArray
        lista.put(obj);

        // Salva o JSONArray atualizado de volta no arquivo
        salvarArquivo(caminho, lista); // usa o metodo salvarArquivo ja criado
    }


}