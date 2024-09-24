package domain;

public class PessoaJuridica implements Validavel {

    private Integer codigo;

    private String nome;

    private String cnpj;

    @Override
    public boolean valida() {
        boolean validouCnpj = true;
        return validaGenerico(nome) && validouCnpj && isPessoaRegular();
    }

    public boolean isPessoaRegular() {
        boolean validouReceitaFederal = true;

        return validouReceitaFederal;
    }
    
}
