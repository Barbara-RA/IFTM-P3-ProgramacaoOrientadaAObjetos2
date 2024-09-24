package domain;

public class PessoaFisica implements Validavel {

    private Integer codigo;

    private String nome; 

    private String cpf;

    @Override
    public boolean valida() {
        boolean validouCpf = true;
        return validaGenerico(nome) && validouCpf && isPessoaRegular();        
    }

    public boolean isPessoaRegular() {
        boolean validaSerasa = true;

        return validaSerasa;
    }
    
}
