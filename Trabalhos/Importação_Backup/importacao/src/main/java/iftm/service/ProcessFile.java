package iftm.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcessFile {
    
    private static final String PATH = "C:\\Users\\milab\\OneDrive\\Documentos - piatã\\Repos\\Projeto importacao POO2\\importacao_codigo_repetido\\poo_2_importacao\\files\\";
    private static final String PROCESSED = "C:\\Users\\milab\\OneDrive\\Documentos - piatã\\Repos\\Projeto importacao POO2\\importacao_codigo_repetido\\poo_2_importacao\\processed\\";

    private String prefix;

    private ProcessLine processLine;

    public ProcessFile(String prefix, ProcessLine processLine){
        this.prefix = prefix;
        this.processLine = processLine;
    }
    
    public void process() throws Exception{

        List<Path> filesToProcess = getFilesToProcces(prefix);
        
        for (Path fileToProcess: filesToProcess){

            processFile(fileToProcess);
            moveProcessedFiles(fileToProcess);
        }
    }

    public List<Path> getFilesToProcces(String prefix) throws Exception{

        Path directory = Paths.get(PATH);

        List<Path> fileList = new ArrayList<>();

        Files.newDirectoryStream(directory, prefix).forEach(fileList::add);

        Collections.sort(fileList);

        return fileList;
    }


    private void processFile(Path fileToProcess) throws Exception{

        processLine.process(
            Files.readAllLines(fileToProcess)
        );

    }

    private void moveProcessedFiles(Path fileToProcess) throws Exception{

        Path directory = Paths.get(
            PROCESSED+fileToProcess.getFileName()
        );

        Files.move(fileToProcess, directory);
    }
}
