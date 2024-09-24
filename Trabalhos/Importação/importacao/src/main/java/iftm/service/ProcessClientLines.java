package iftm.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import iftm.model.Client;
import iftm.repository.ClientRepository;
import iftm.repository.ReceiptControlRepository;

public class ProcessClientLines implements ProcessLine {

    private static final String FILETYPE = "CLI";
    private static final int CPF_START = 2;
    private static final int CPF_LENGTH = 11;
    private static final int NAME_LENGTH = 30;
    private static final int ADDRESS_LENGTH = 30;
    private static final int NEIGHBORHOOD_LENGTH = 30;
    private static final int CITY_LENGTH = 30;
    private static final int STATE_LENGTH = 2;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("ddMMyyyyHHmmss");

    private ClientRepository clientRepository;
    private ReceiptControlRepository receiptControlRepository;
    private Integer batch;

    public ProcessClientLines() {
        clientRepository = new ClientRepository();
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
        Client client = buildClientFromLine(line);
        clientRepository.persist(client);
    }

    private Client buildClientFromLine(String line) throws ParseException {
        int index = 1;
        Client client = new Client();

        client.setInclusionAlteration(line.substring(index, index + 1));
        index += 1;

        client.setCpf(line.substring(index, index + CPF_LENGTH));
        index += CPF_LENGTH;

        client.setName(line.substring(index, index + NAME_LENGTH).trim());
        index += NAME_LENGTH;

        client.setAddress(line.substring(index, index + ADDRESS_LENGTH).trim());
        index += ADDRESS_LENGTH;

        client.setNeighborhood(line.substring(index, index + NEIGHBORHOOD_LENGTH).trim());
        index += NEIGHBORHOOD_LENGTH;

        client.setCity(line.substring(index, index + CITY_LENGTH).trim());
        index += CITY_LENGTH;

        client.setState(line.substring(index, index + STATE_LENGTH).trim());
        index += STATE_LENGTH;

        String dateTimeString = line.substring(index);
        if (!dateTimeString.contains("_")) {
            Date date = DATE_FORMAT.parse(dateTimeString);
            client.setDateHourRegister(date);
        }

        return client;
    }
}
