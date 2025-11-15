package modelo;

public enum Estacao {
    CADEIRA_1("Cadeira 1"),
    CADEIRA_2("Cadeira 2"),
    LAVATORIO("Lavatório");

    private final String nome;

    Estacao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
