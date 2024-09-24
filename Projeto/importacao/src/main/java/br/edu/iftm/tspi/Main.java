package br.edu.iftm.tspi;

import br.edu.iftm.tspi.service.ProcessaCliente;

public class Main {
    public static void main(String[] args) throws Exception {
        String path = "arquivos/Cliente_20140220.txt";
        ProcessaCliente processaCliente = new ProcessaCliente(path);
        processaCliente.processa();
    }
}