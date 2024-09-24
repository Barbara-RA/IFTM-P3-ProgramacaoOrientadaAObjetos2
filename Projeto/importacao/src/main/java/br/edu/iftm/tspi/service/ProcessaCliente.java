package br.edu.iftm.tspi.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Spring;

import br.edu.iftm.tspi.dao.ClienteDao;
import br.edu.iftm.tspi.domain.Cliente;

public class ProcessaCliente {
    private String path;

    private ClienteDao clienteDao;

    public ProcessaCliente (String path){
        this.path=path;
    }

    public void processa() throws Exception{
        BufferedReader br= new BufferedReader(new FileReader(path));
        while (br.ready()) {
            String linha =br.readLine();
            if(linha.substring(1, 1).equals("2")){
                Cliente cliente = getCliente(linha);
                clienteDao.salvarClient(cliente);
            }   
        }
        br.close();
    }

    private Cliente getCliente(String linha) throws ParseException {
        Cliente cliente = new Cliente();
        cliente.setInclusaoAlteracao(linha.substring(2,2));
        cliente.setCpf(linha.substring(3,13));
        cliente.setNome(linha.substring(14, 43));
        cliente.setEndereco(linha.substring(44, 73));
        cliente.setBairro(linha.substring(70,103));
        cliente.setCidade(linha.substring(104, 133 ));
        cliente.setEstado(linha.substring(134,135));
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
        String dataCadastro = linha.substring(136,143);
        String horaCadastro = linha.substring(144,149);
        Date dataHoraCadastro = sdf.parse(dataCadastro + horaCadastro);
        cliente.setDataHoraCadastro(dataHoraCadastro);
        return cliente;

    }
}
