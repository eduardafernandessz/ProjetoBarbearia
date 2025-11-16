package modelo;

/**
 * Enumeração que representa as estações de atendimento em uma barbearia ou salão.
 *
 * <p>Define as opções de estações disponíveis, cada uma com um nome descritivo:
 * CADEIRA_1 representa a primeira cadeira de atendimento,
 * CADEIRA_2 representa a segunda cadeira de atendimento,
 * e LAVATORIO representa o local para lavagem de cabelo.</p>
 */
public enum Estacao {

    /** Primeira cadeira de atendimento */
    CADEIRA_1("Cadeira 1"),

    /** Segunda cadeira de atendimento */
    CADEIRA_2("Cadeira 2"),

    /** Lavatorio para lavagem de cabelo */
    LAVATORIO("Lavatório");

    /** Nome descritivo da estação */
    private final String nome;

    /**
     * Construtor do enum que recebe o nome da estação.
     *
     * @param nome Nome descritivo da estação
     */
    Estacao(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o nome descritivo da estação.
     *
     * @return Nome da estação
     */
    public String getNome() {
        return nome;
    }
}
