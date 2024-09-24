package iftm.model;

public class Plastic {
    
    public static final String PLASTIC_PREFIX = "Plastico_*";

    private String plasticNumber; 

    private String accountNumber;

    private String plasticName;

    private String cpf;

    public boolean isUpdated(String valor) {

        for (char character: valor.toCharArray()) {

            if (character != '_') {

                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Plástico [numero = " + plasticNumber + ", nome = " + plasticName + ", conta = " + accountNumber + ", CPF cliente = " + cpf + "]";
    }
    
    public String getPlasticName() {
        return plasticName;
    }

    public void setPlasticName(String plasticName) {
        this.plasticName = plasticName;
    }

    public String getPlasticNumber() {
        return plasticNumber;
    }

    public void setPlasticNumber(String numberPlastic) {
        this.plasticNumber = numberPlastic;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
