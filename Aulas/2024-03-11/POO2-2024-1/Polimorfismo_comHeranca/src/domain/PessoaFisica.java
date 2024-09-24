package domain;

public class PessoaFisica extends Pessoa implements PessoaRegular {

    private String cpf;

    @Override
    public boolean validaAbstract() {
        super.valida();

        boolean validouCpf = true;
        return validouCpf;        
    }

    @Override
    public boolean isPessoaRegular() {
        boolean validaSerasa = true;
        return validaSerasa;
    }
    
}
