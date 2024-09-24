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
            if (option.equals("2")) {  // Processa apenas detalhes
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
        plasticRepository.savePlastic(plastic); // Direciona para salvar diretamente
    }

    private Plastic getPlastic(String line) {
        Plastic plastic = new Plastic();
    
        String accountNumber = line.substring(1, 8).trim();  // Extraindo número da conta
        String plasticName = line.substring(8, 38).trim();   // Extraindo nome no plástico
        String cpf = line.substring(38, 49).trim();          // Extraindo CPF
        String plasticNumber = line.substring(49, 56).trim(); // Extraindo número do plástico
    
        // Setando valores, considerando que campos com "_" significam dados não informados
        plastic.setAccountNumber(accountNumber.contains("_") ? null : accountNumber);
        plastic.setPlasticName(plasticName.contains("_") ? null : plasticName);
        plastic.setCpf(cpf.contains("_") ? null : cpf);
        plastic.setPlasticNumber(plasticNumber.contains("_") ? null : plasticNumber);
    
        return plastic;
    }
}
