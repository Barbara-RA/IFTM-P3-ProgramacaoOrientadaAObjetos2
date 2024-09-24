package iftm.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import iftm.model.Client;
import iftm.repository.ClientRepository;
import iftm.repository.ReceiptControlRepository;

public class ProcessClientLines implements ProcessLine{

    private static final String FILETYPE = "CLI";

    private ClientRepository clientRepository;

    private ReceiptControlRepository receiptControlRepository;

    private Integer batch;

    public ProcessClientLines(){
        clientRepository = new ClientRepository();
        receiptControlRepository = new ReceiptControlRepository();
    }

    public void process(List<String> lines) throws Exception{

        for(String line: lines){

            String option = line.substring(0, 1);

            if(option.equals("2")){

                processDetail(line);

            } else if(option.equals("1")){

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
        
        if(!batch.equals(expectedBatch)){

            throw new Exception("Lote recebido " + batch + "é diferente do lote esperado " + expectedBatch);
        }

        this.batch = batch;

    }

    public void processDetail(String line) throws Exception{

        Client client = getClient(line);

        clientRepository.persist(client);
    }
    
    private Client getClient(String line) throws ParseException {

        Client client = new Client();

        client.setInclusionAlteration(line.substring(1,2));
        client.setCpf(line.substring(2,13));
        client.setName(line.substring(13,43));
        client.setAddress(line.substring(43,73));
        client.setNeighborhood(line.substring(73,103));
        client.setCity(line.substring(103,133));
        client.setState(line.substring(133, 135));

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
        String dateRegister = line.substring(135, 143);
        String hourRegister = line.substring(143, 149);
        Date dataHoraCadastro = sdf.parse(dateRegister+hourRegister);

        client.setDateHourRegister(dataHoraCadastro);

        return client;
    }
}
