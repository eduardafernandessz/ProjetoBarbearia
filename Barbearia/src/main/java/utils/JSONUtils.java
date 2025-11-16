
package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Classe utilitária para funções de JSON.
 * 
 * <p>Não precisa instanciar, todos os métodos são estáticos.</p>
 * <p>Serve para converter objetos Java para JSON e vice-versa usando a biblioteca Jackson.</p>
 */
public class JSONUtils {

    /** Objeto ObjectMapper do Jackson usado para conversão entre objetos e JSON. */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Converte qualquer objeto Java em uma string JSON.
     *
     * @param objeto O objeto que você quer transformar em JSON
     * @return String representando o objeto no formato JSON
     * @throws RuntimeException Se ocorrer algum erro na conversão
     */
    public static String toJson(Object objeto) {
        try {
            return objectMapper.writeValueAsString(objeto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter para JSON", e);
        }
    }

    /**
     * Converte uma string JSON de volta para um objeto Java da classe que você escolher.
     *
     * <p>Útil para quando você lê JSON de arquivo, API ou outro lugar e quer transformar
     * em objeto para trabalhar normalmente no Java.</p>
     *
     * @param json  A string JSON
     * @param clazz A classe que você quer criar a partir do JSON
     * @param <T>   Tipo do objeto que será retornado
     * @return Objeto Java criado a partir do JSON
     * @throws RuntimeException Se o JSON estiver errado ou ocorrer erro na conversão
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler JSON", e);
        }
    }
}
