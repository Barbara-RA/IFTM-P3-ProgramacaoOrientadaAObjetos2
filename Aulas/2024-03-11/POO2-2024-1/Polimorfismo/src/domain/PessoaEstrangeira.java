package domain;

public class PessoaEstrangeira implements Validavel {

    private Integer codigo;

    private String nome;

    private String passaporte;

    @Override
    public boolean valida() {
        boolean validouPassaporte = true;
        
        return validaGenerico(nome) && validouPassaporte;        
    }

}
