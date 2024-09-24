package iftm.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
// import java.sql.ResultSet;

import iftm.model.Plastic;

public class PlasticRepository {
    
    public void savePlastic(Plastic plastic) throws Exception{

        Connection connection = ConnectionBd.getConnection();

        String querySql = "INSERT INTO tbPlastico(nropla, nrocta, nompla, cpfpla) values (?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql);

        preparedStatement.setString(1, plastic.getPlasticNumber());
        preparedStatement.setString(2, plastic.getAccountNumber());
        preparedStatement.setString(3, plastic.getPlasticName());
        preparedStatement.setString(4, plastic.getCpf());

        preparedStatement.execute();
    }

    public void updatePlastic(Plastic plastic) throws Exception {

        Connection connection = ConnectionBd.getConnection();
        
        String querySql = "UPDATE tbPlastico set ";
        int sequence = 1;
        
        if (plastic.getPlasticNumber() != null && plastic.isUpdated(plastic.getPlasticName().toString())) {

            querySql += " nropla = ?,";
        }
        if (plastic.getPlasticName() != null && plastic.isUpdated(plastic.getPlasticName().toString())) {

            querySql += " nompla = ?,";
        }
        if (plastic.getCpf() != null && plastic.isUpdated(plastic.getPlasticName().toString())) {

            querySql += " cpfpla = ?,";
        }
        
        querySql = querySql.substring(0, querySql.length() - 1);
        querySql += "where nrocta = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql);

        if (plastic.getPlasticNumber() != null && plastic.isUpdated(plastic.getPlasticNumber().toString())) {

            preparedStatement.setString(sequence, plastic.getPlasticNumber());
            sequence++;
        }
        if (plastic.getPlasticName() != null && plastic.isUpdated(plastic.getPlasticName().toString())) {

            preparedStatement.setString(sequence, plastic.getPlasticName());
            sequence++;
        }
        if (plastic.getCpf() != null && plastic.isUpdated(plastic.getCpf())) {

            preparedStatement.setString(sequence,plastic.getCpf());
            sequence++;            
        }

        preparedStatement.setString(sequence, plastic.getAccountNumber());
        preparedStatement.execute();
    }

    public void persist(Plastic plastic) throws Exception {
        
        if (plastic.getAccountNumber() == null) {

            throw new Exception("Número da conta não pode ser nulo!");

        } else if(plastic.getPlasticNumber() != null) {

            savePlastic(plastic);

        } else {

            updatePlastic(plastic);;
        }
    }

    // public void persist(Plastic plastic) throws Exception {

    //     if(plastic.getInclusionAlteration().equals("I")) {

    //         savePlastic(plastic);

    //     } else if(plastic.getInclusionAlteration().equals("A")) {

    //         updatePlastic(plastic);
            
    //     } else {

    //         throw new Exception("Opção desconhecida de inclusão alteração: " + plastic.getInclusionAlteration());
    //     }
    // }

    // public Integer getLastBatch() throws Exception {

    //     Connection connection = ConnectionBd.getConnection(); 

    //     String querySql = "select MAX(numlot) as maxLote from tbControleRecebimento where tiparq = 'PLA'";  

    //     PreparedStatement preparedStatement = connection.prepareStatement(querySql);
    //     ResultSet resultSet = preparedStatement.executeQuery();    

    //     if(resultSet.next()){   

    //         return resultSet.getInt(1);
    //     }

    //     throw new Exception("Não foi encontrado o lote do plástico.");
    // }

    // public void saveBatch(Integer batch) throws Exception {

    //     Connection connection = ConnectionBd.getConnection();

    //     String querySql = "INSERT INTO tbControleRecebimento(tiparq, numlot,dathraprc) values ('PLA', ?, now())";

    //     PreparedStatement preparedStatement = connection.prepareStatement(querySql);

    //     preparedStatement.setInt(1, batch);
    //     preparedStatement.execute();
    // }

}
