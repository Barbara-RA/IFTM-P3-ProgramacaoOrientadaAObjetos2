package domain;

public abstract class Pessoa {

    private Integer codigo;

    private String nome;

    public abstract boolean validaAbstract();

    public boolean valida() {
        boolean validouNome = true;

        return validouNome && validaAbstract();
    }
    
}
