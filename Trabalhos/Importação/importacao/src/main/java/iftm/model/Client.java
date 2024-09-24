package iftm.model;

import java.util.Date;

public class Client {

    public static final String CLIENT_PREFIX = "Cliente_*";
    
    private String inclusionAlteration;
    
    private String cpf;
    
    private String name;
    
    private String address;
    
    private String neighborhood;
    
    private String city;
    
    private String state;
    
    private Date dateHourRegister;

    public boolean isUpdated(String value){

        for(char character: value.toCharArray()){
            
            if( character != '_'){

                return true;
            }
        }

        return false;
    }
    
    public String getInclusionAlteration() {
        return inclusionAlteration;
    }
    public void setInclusionAlteration(String inclusionAlteration) {
        this.inclusionAlteration = inclusionAlteration;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getNeighborhood() {
        return neighborhood;
    }
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public Date getDateHourRegister() {
        return dateHourRegister;
    }
    public void setDateHourRegister(Date dateHourRegister) {
        this.dateHourRegister = dateHourRegister;
    }

    @Override
    public String toString(){
        return "Cliente [inclusão/alteração = " + inclusionAlteration + ", cpf = " + cpf + ", nome = " + name + ", endereco = " + address + ", bairro = " + neighborhood + ", cidade = " + city + ", estado = " + state + ", data-hora cadastro = " + dateHourRegister + "]";
    }
}
