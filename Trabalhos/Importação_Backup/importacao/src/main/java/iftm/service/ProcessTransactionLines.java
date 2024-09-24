package iftm.service;

import java.util.List;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

                throw new Exception("Opção de processa linha desconhecida: "+ option);
            }
        }
        receiptControlRepository.saveBatch(batch, FILETYPE);
    }

    public void processHeader(String line) throws Exception {

        Integer batch = Integer.parseInt(line.substring(1, 4));
        Integer bankLot = receiptControlRepository.getLastBatch(FILETYPE);
        Integer expectedBatch = bankLot + 1;

        if (!batch.equals(expectedBatch)) {

            throw new Exception("Lote recebido "+ batch + " é diferente do lote esperado "+ expectedBatch);
        }

        this.batch = batch;
    }

    public void processDetail(String line) throws Exception {

        Transaction transaction = getTransaction(line);

        transactionRepository.persist(transaction);
    }

    private Transaction getTransaction(String line){

        Transaction transaction = new Transaction();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");

        String accountNumber = line.substring(1, 8).trim();
        String plasticNumber = line.substring(8, 15).trim();
        Double transactionValue = Double.parseDouble(line.substring(15, 27).trim()) / 100;
        String transactionDateString = line.substring(27, 35).trim();
        LocalDate transactionDate = LocalDate.parse(transactionDateString, formatter);
        String transactionEstablishmentCode = line.substring(41, 48).trim();

        if(accountNumber.contains("_")){

            transaction.setAccountNumber(null);
            
        } else{

            transaction.setAccountNumber(accountNumber);
        }

        if(plasticNumber.contains("_")){

            transaction.setPlasticNumber(null);

        } else{

            transaction.setPlasticNumber(plasticNumber);
        }

        transaction.setTransactionValue(transactionValue);

        transaction.setTransactionDate(transactionDate);
        transaction.setEstablishmentCode(transactionEstablishmentCode);

        return transaction;

    }


}
