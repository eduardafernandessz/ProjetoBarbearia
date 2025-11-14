import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Classe utilitária para funções de JSON.
 * 
 * - Não precisa instanciar (todos os métodos são estáticos)
 * - Serve para converter objetos Java para JSON usando Jackson
 */
public class JSONUtils {

    // Objeto do Jackson que faz toda a conversão entre objetos e JSON
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Converte qualquer objeto Java para uma string JSON.
     * 
     * @param objeto O objeto que você quer transformar em JSON
     * @return String em formato JSON
     * @throws RuntimeException Se acontecer algum erro na conversão
     */
    public static String toJson(Object objeto) {
        try {
            // Jackson transforma o objeto Java em JSON
            return objectMapper.writeValueAsString(objeto);
        } catch (JsonProcessingException e) {
            // Lança RuntimeException para não precisar tratar checagem de exceção
            throw new RuntimeException("Erro ao converter para JSON", e);
        }
    }

    /**
     * Converte uma string JSON de volta para um objeto Java da classe que você escolher.
     * 
     * Útil para quando você lê JSON de arquivo, API ou outro lugar, e quer transformar
     * em objeto para trabalhar normalmente no Java.
     *
     * @param json  A string JSON
     * @param clazz A classe que você quer criar a partir do JSON
     * @param <T>   Tipo genérico da classe
     * @return Objeto Java criado a partir do JSON
     * @throws RuntimeException Se o JSON estiver errado ou ocorrer erro na conversão
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            // Jackson lê o JSON e cria o objeto do tipo especificado
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler JSON", e);
        }
    }
}
