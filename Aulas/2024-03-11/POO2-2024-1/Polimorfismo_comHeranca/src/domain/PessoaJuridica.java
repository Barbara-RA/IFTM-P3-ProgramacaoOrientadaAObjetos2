package domain;

public class PessoaJuridica extends Pessoa {

    private String cnpj;

    @Override
    public boolean validaAbstract() {
        super.valida();
        
        boolean validouCnpj = true;
        return validouCnpj;
    }
    
}
