package iftm.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
    
    public static final String TRANSACTION_PREFIX = "Transacao_*";

    private String accountNumber;
   
    private String plasticNumber;

    private Double transactionValue;

    private LocalDate transactionDate;

    private LocalTime transactionHour;

    private String establishmentCode;

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
        return "Transação [número da conta = " + accountNumber + ", número plástico = " + plasticNumber + ", valor = " + transactionValue + ", data = " + transactionDate + ", hora = " + transactionHour + ", código do estabelecimento = " + establishmentCode + "]";
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getPlasticNumber() {
        return plasticNumber;
    }

    public void setPlasticNumber(String plasticNumber) {
        this.plasticNumber = plasticNumber;
    }
    public Double getTransactionValue() {
        return transactionValue;
    }
    public void setTransactionValue(Double transactionValue) {
        this.transactionValue = transactionValue;
    }
    public LocalDate getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
    public LocalTime getTransactionHour() {
        return transactionHour;
    }
    public void setTransactionHour(LocalTime transactionHour) {
        this.transactionHour = transactionHour;
    }
    public String getEstablishmentCode() {
        return establishmentCode;
    }
    public void setEstablishmentCode(String establishmentCode) {
        this.establishmentCode = establishmentCode;
    }
}
