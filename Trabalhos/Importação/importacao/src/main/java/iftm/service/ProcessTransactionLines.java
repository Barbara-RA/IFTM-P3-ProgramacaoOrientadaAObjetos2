package iftm.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import iftm.model.Transaction;
import iftm.repository.ReceiptControlRepository;
import iftm.repository.TransactionRepository;

public class ProcessTransactionLines implements ProcessLine{
    
    private static final String FILETYPE = "TRA";

    private TransactionRepository transactionRepository;

    private ReceiptControlRepository receiptControlRepository;

    private Integer batch;

    public ProcessTransactionLines(){
        transactionRepository = new TransactionRepository();
        receiptControlRepository = new ReceiptControlRepository();
    }

    public void process(List<String> lines) throws Exception {
        for (String line: lines) {
            String option = line.substring(0, 1);
            if (option.equals("2")) {
                processDetail(line);
            } else if (option.equals("1")) {
                processHeader(line);
            } else {
                throw new Exception("Opção de processa linha desconhecida: " + option);
            }
        }
        receiptControlRepository.saveBatch(batch, FILETYPE);
    }

    public void processHeader(String line) throws Exception {
        Integer batch = Integer.parseInt(line.substring(1, 4));
        Integer bankLot = receiptControlRepository.getLastBatch(FILETYPE);
        Integer expectedBatch = bankLot + 1;
        if (!batch.equals(expectedBatch)) {
            throw new Exception("Lote recebido " + batch + " é diferente do lote esperado " + expectedBatch);
        }
        this.batch = batch;
    }

    public void processDetail(String line) throws Exception {
        Transaction transaction = getTransaction(line);
        transactionRepository.saveTransaction(transaction); 
    }

    private Transaction getTransaction(String line){
        Transaction transaction = new Transaction();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");

        String accountNumber = line.substring(1, 8).trim();
        String plasticNumber = line.substring(8, 15).trim();
        Double transactionValue = Double.parseDouble(line.substring(15, 27).trim()) / 100;
        String transactionDateString = line.substring(27, 35).trim();
        LocalDate transactionDate = transactionDateString.contains("_") ? null : LocalDate.parse(transactionDateString, dateFormatter);

        String transactionHourString = line.substring(35, 41).trim();
        LocalTime transactionHour = null;
        if (!transactionHourString.contains("_")) {
            try {
                transactionHour = LocalTime.parse(transactionHourString, DateTimeFormatter.ofPattern("HHmmss"));
            } catch (DateTimeParseException e) {
                System.err.println("Erro ao analisar hora: " + e.getMessage());
            }
        }

        String establishmentCode = line.substring(41, 48).trim();

        transaction.setAccountNumber(accountNumber.contains("_") ? null : accountNumber);
        transaction.setPlasticNumber(plasticNumber.contains("_") ? null : plasticNumber);
        transaction.setTransactionValue(transactionValue);
        transaction.setTransactionDate(transactionDate);
        transaction.setTransactionHour(transactionHour);
        transaction.setEstablishmentCode(establishmentCode.contains("_") ? null : establishmentCode);

        return transaction;
    }
}
