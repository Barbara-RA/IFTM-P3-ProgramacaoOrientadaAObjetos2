package iftm.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcessFile {

    private String inputDirectory;
    private String processedDirectory;
    private String prefix;
    private ProcessLine processLine;

    // Construtor para passar os diretórios como parâmetros
    public ProcessFile(String inputDirectory, String processedDirectory, String prefix, ProcessLine processLine) {
        this.inputDirectory = inputDirectory;
        this.processedDirectory = processedDirectory;
        this.prefix = prefix;
        this.processLine = processLine;
    }

    // Método principal que processa os arquivos
    public void process() throws Exception {
        List<Path> filesToProcess = getFilesToProcess();
        for (Path fileToProcess : filesToProcess) {
            processFile(fileToProcess);
            moveProcessedFile(fileToProcess);
        }
    }

    // Obtém a lista de arquivos a serem processados
    private List<Path> getFilesToProcess() throws Exception {
        Path directory = Paths.get(inputDirectory);
        List<Path> fileList = new ArrayList<>();
        
        // Filtrando arquivos com o prefixo
        Files.newDirectoryStream(directory, prefix + "*").forEach(fileList::add);
        Collections.sort(fileList);

        return fileList;
    }

    // Processa o arquivo individualmente
    private void processFile(Path fileToProcess) throws Exception {
        List<String> lines = Files.readAllLines(fileToProcess);
        processLine.process(lines); // Processa as linhas do arquivo
    }

    // Move o arquivo para o diretório de processados
    private void moveProcessedFile(Path fileToProcess) throws Exception {
        Path targetPath = Paths.get(processedDirectory, fileToProcess.getFileName().toString());
        Files.move(fileToProcess, targetPath); // Movendo o arquivo processado
    }
}
