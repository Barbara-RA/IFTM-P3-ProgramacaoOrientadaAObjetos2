package iftm.service;

import java.util.List;

import iftm.model.Plastic;
import iftm.repository.PlasticRepository;
import iftm.repository.ReceiptControlRepository;

public class ProcessPlasticLines implements ProcessLine {

    private static final String FILETYPE = "PLA";
    
    private PlasticRepository plasticRepository;

    private ReceiptControlRepository receiptControlRepository;

    private Integer batch;

    public ProcessPlasticLines() {

        plasticRepository = new PlasticRepository();
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

        Plastic plastic = getPlastic(line);

        plasticRepository.persist(plastic);
    }

    private Plastic getPlastic(String line) {

        Plastic plastic = new Plastic();

        String accountNumber = line.substring(1, 8).trim();
        String plasticName = line.substring(8, 38).trim();
        String cpf = line.substring(38, 49).trim();
        String numberPlastic = line.substring(49, 56).trim();

        if(accountNumber.contains("_")){

            plastic.setAccountNumber(null);
            
        } else{

            plastic.setAccountNumber(accountNumber);
        }

        if(plasticName.contains("_")){

            plastic.setPlasticName(null);

        } else{

            plastic.setPlasticName(plasticName);
        }

        if(numberPlastic.contains("_")){

            plastic.setPlasticNumber(null);
            
        } else{

            plastic.setPlasticNumber(numberPlastic);
        }

        if(cpf.contains("_")){

            plastic.setCpf(null);
            
        } else{

            plastic.setCpf(cpf);
        }

        return plastic;

    }
}
