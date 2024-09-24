package iftm.service;

import java.util.List;

import iftm.model.Account;
import iftm.model.Client;
import iftm.repository.AccountRepository;
import iftm.repository.ReceiptControlRepository;

public class ProcessAccountLines implements ProcessLine {

    private static final String FILETYPE = "CTA";

    private AccountRepository accountRepository;

    private ReceiptControlRepository receiptControlRepository;

    private Integer batch;

    public ProcessAccountLines() {

        accountRepository = new AccountRepository();
        receiptControlRepository = new ReceiptControlRepository();
    }

    public void process(List<String> lines) throws Exception {

        for (String line : lines) {

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

    public void processHeader(String linha) throws Exception {

        Integer batch = Integer.parseInt(linha.substring(1, 4));

        Integer bankLot = receiptControlRepository.getLastBatch(FILETYPE);
        Integer expectedBatch = bankLot + 1;

        if (!batch.equals(expectedBatch)) {

            throw new Exception("Lote recebido " + batch + " é diferente do lote esperado " + expectedBatch);
        }

        this.batch = batch;
    }

    public void processDetail(String line) throws Exception {
        // Chamando buildAccountFromLine ao invés de getAccount
        Account account = buildAccountFromLine(line);
        accountRepository.persist(account);
    }

    private Account buildAccountFromLine(String line) throws IllegalArgumentException {
        final int INCLUSION_INDEX = 1;
        final int CPF_START = 2;
        final int CPF_LENGTH = 11;
        final int LIMIT_VALUE_START = 13;
        final int LIMIT_VALUE_LENGTH = 12;
        final int DUE_DATE_START = 25;
        final int DUE_DATE_LENGTH = 2;
        final int NUMBER_START = 27;
        final int NUMBER_LENGTH = 7;

        if (line == null || line.length() < NUMBER_START + NUMBER_LENGTH) {
            throw new IllegalArgumentException("Linha inválida para processamento de conta");
        }

        String inclusionAlteration = line.substring(INCLUSION_INDEX, INCLUSION_INDEX + 1);
        String cpf = line.substring(CPF_START, CPF_START + CPF_LENGTH);
        String limitValueStr = line.substring(LIMIT_VALUE_START, LIMIT_VALUE_START + LIMIT_VALUE_LENGTH).trim();
        String dueDateStr = line.substring(DUE_DATE_START, DUE_DATE_START + DUE_DATE_LENGTH).trim();
        String number = line.substring(NUMBER_START, NUMBER_START + NUMBER_LENGTH).trim();

        Client client = new Client();
        client.setCpf(cpf);

        Account account = new Account();
        account.setInclusionAlteration(inclusionAlteration);
        account.setClient(client);
        account.setNumber(number);
        account.setDueDate(dueDateStr.contains("_") ? null : Integer.valueOf(dueDateStr));
        account.setLimitValue(limitValueStr.contains("_") ? null : Double.parseDouble(limitValueStr) / 100);

        return account;
    }

}
