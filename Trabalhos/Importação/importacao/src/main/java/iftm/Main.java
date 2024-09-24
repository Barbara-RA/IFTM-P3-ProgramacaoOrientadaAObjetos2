package iftm;


import iftm.service.ProcessAccountLines;
import iftm.service.ProcessClientLines;
import iftm.service.ProcessFile;
import iftm.service.ProcessLine;
import iftm.service.ProcessPlasticLines;
import iftm.service.ProcessTransactionLines;


public class Main {
    public static void main(String[] args) throws Exception{

        String inputDirectory = "C:\\Users\\barba\\Documents\\BARBARA\\IFTM\\2024-1\\Programação Orientada A Objetos\\Trabalhos\\Importação\\importacao\\files";
        String processedDirectory = "C:\\Users\\barba\\Documents\\BARBARA\\IFTM\\2024-1\\Programação Orientada A Objetos\\Trabalhos\\Importação\\importacao\\processed";

        // Processar diferentes tipos de arquivos
        processFiles(inputDirectory, processedDirectory, "Cliente_*", new ProcessClientLines());
        processFiles(inputDirectory, processedDirectory, "Conta_*", new ProcessAccountLines());
        processFiles(inputDirectory, processedDirectory, "Plastico_*", new ProcessPlasticLines());
        processFiles(inputDirectory, processedDirectory, "Transacao_*", new ProcessTransactionLines());
    }

    private static void processFiles(String inputDirectory, String processedDirectory, String prefix, ProcessLine processLine) throws Exception {
        ProcessFile processFile = new ProcessFile(inputDirectory, processedDirectory, prefix, processLine);
        processFile.process();
    }
}