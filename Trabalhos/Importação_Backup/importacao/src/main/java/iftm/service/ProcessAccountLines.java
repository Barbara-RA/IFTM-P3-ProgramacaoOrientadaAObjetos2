package iftm.service;

import java.util.List;

import iftm.model.Account;
import iftm.model.Client;
import iftm.repository.AccountRepository;
import iftm.repository.ReceiptControlRepository;

public class ProcessAccountLines implements ProcessLine{
    
    private static final String FILETYPE = "CTA";
    
    private AccountRepository accountRepository;

    private ReceiptControlRepository receiptControlRepository;

    private Integer batch;

    public ProcessAccountLines() {

        accountRepository = new AccountRepository();
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

    public void processHeader(String linha) throws Exception {

        Integer batch = Integer.parseInt(linha.substring( 1, 4));

        Integer bankLot = receiptControlRepository.getLastBatch(FILETYPE);
        Integer expectedBatch = bankLot + 1;

        if (!batch.equals(expectedBatch)) {

            throw new Exception("Lote recebido "+ batch + " é diferente do lote esperado "+ expectedBatch);
        }

        this.batch = batch;
    }

    public void processDetail(String line) throws Exception {

        Account account = getAccount(line);

        accountRepository.persist(account);
    }

    private Account getAccount(String line){

        Account account = new Account();

        account.setInclusionAlteration(line.substring(1,2));
        
        String cpf = line.substring(2,13);
        String limitValue = line.substring(13,25).trim();
        String dueDate = line.substring(25,27).trim();
        String number = line.substring(27,34).trim();
        
        Client client = new Client();

        client.setCpf(cpf);
        account.setClient(client);
        account.setNumber(number);

        if (dueDate.contains("_")) {

            account.setDueDate(null);  

        } else {

            account.setDueDate(Integer.valueOf(dueDate));

        }

        if (limitValue.contains("_")) {
            account.setLimitValue(null);    
        } else {

            Double limitValueDouble = Double.parseDouble(limitValue) / 100;

            account.setLimitValue(limitValueDouble);
        }
        
        return account;
    }
}
