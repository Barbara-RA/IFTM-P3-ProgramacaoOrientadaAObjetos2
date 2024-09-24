package domain;

public interface Validavel {

    public boolean valida();

    default boolean validaGenerico(String nome) {
        boolean validouNome = true;

        return validouNome;
    }
    
}
