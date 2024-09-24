package iftm.model;

public class Account {

    public static final String ACCOUNT_PREFIX = "Conta_*";
    
    private String inclusionAlteration;

    private String number;

    private Double limitValue;

    private Integer dueDate;

    private Client client;

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
        return "Conta [numero = " + number + ", valorLimite = " + limitValue + ", diaVencimento = " + dueDate + ", cliente = " + client + "]";
    }

    public String getInclusionAlteration() {
        return inclusionAlteration;
    }

    public void setInclusionAlteration(String inclusionAlteration) {
        this.inclusionAlteration = inclusionAlteration;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Double getLimitValue() {
        return limitValue;
    }

    public void setLimitValue(Double limitValue) {
        this.limitValue = limitValue;
    }

    public Integer getDueDate() {
        return dueDate;
    }

    public void setDueDate(Integer dueDate) {
        this.dueDate = dueDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

   
}
