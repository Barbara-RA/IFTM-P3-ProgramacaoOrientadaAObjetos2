package domain;

public class PessoaEstrangeira extends Pessoa {

    private String passaporte;

    @Override
    public boolean validaAbstract() {
        super.valida();

        boolean validouPassaporte = true;
        
        return validouPassaporte;        
    }

}
