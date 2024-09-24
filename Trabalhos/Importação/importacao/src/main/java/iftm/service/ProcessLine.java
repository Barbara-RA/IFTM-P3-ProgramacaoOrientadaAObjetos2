package iftm.service;

import java.util.List;

public interface ProcessLine {
    
    public void process(List<String> lines) throws Exception;

    public void processHeader(String linha) throws Exception;

    public void processDetail(String line) throws Exception; 
}
