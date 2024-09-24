package iftm.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
// import java.sql.ResultSet;

import iftm.model.Client;

public class ClientRepository{

    public void saveClient(Client client) throws Exception{

        Connection connection = ConnectionBd.getConnection();

        String querySql = "INSERT INTO tbCliente (cpfcli, nomcli, endcli, baicli, cidcli, sigest, datcad) values (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql);

        preparedStatement.setString(1, client.getCpf());
        preparedStatement.setString(2, client.getName());
        preparedStatement.setString(3, client.getAddress());
        preparedStatement.setString(4, client.getNeighborhood());
        preparedStatement.setString(5, client.getCity());
        preparedStatement.setString(6, client.getState());
        java.sql.Date sqlDate = new java.sql.Date(client.getDateHourRegister().getTime());
        preparedStatement.setDate(7, sqlDate);

        preparedStatement.execute();
        
    }

    public void updateClient(Client client) throws Exception{

        Connection connection = ConnectionBd.getConnection();

        String querySql = "UPDATE tbCliente set ";

        int sequence = 1;

        if(client.isUpdated(client.getName())){
            querySql += " nomecli = ?";
        }
        if(client.isUpdated(client.getAddress())){
            querySql += " endcli = ?,";
        }
        if(client.isUpdated(client.getNeighborhood())){
            querySql += " baicli = ?,";
        }
        if(client.isUpdated(client.getCity())){
            querySql += " cidcli = ?,";
        }
        if(client.isUpdated(client.getState())){
            querySql += " sigest = ?,";
        }

        querySql = querySql.substring(0 , querySql.length() - 1);
        querySql += " WHERE cpfcli = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql);

        if(client.isUpdated(client.getName())){
            preparedStatement.setString(sequence, client.getName());
            sequence++;
        }
        if(client.isUpdated(client.getAddress())){
            preparedStatement.setString(sequence, client.getAddress());
            sequence++;
        }
        if(client.isUpdated(client.getNeighborhood())){
            preparedStatement.setString(sequence, client.getNeighborhood());
            sequence++;
        }
        if(client.isUpdated(client.getCity())){
            preparedStatement.setString(sequence, client.getCity());
            sequence++;
        }
        if(client.isUpdated(client.getState())){
            preparedStatement.setString(sequence, client.getState());
            sequence++;
        }

        preparedStatement.setString(sequence, client.getCpf());
        preparedStatement.execute();

    }

    public void persist(Client client) throws Exception{

        if(client.getInclusionAlteration().equals("I")){

            saveClient(client);

        } else if(client.getInclusionAlteration().equals("A")){

            updateClient(client);

        } else {
            throw new Exception("Opção de inclusão alteração desconhecida: " + client.getInclusionAlteration());
        }
    }

    // public Integer getLastBatch() throws Exception{

    //     Connection connection = ConnectionBd.getConnection();

    //     String querySql = "select MAX(numlot) as maxLote from tbControleRecebimento where tiparq = 'CLI'";

    //     PreparedStatement preparedStatement = connection.prepareStatement(querySql);

    //     ResultSet resultSet = preparedStatement.executeQuery();

    //     if (resultSet.next()) {

    //        return resultSet.getInt(1);
    //     }

    //     throw new Exception("Não foi encontrado o último lote de cliente");

    // }

    // public void saveBatch(Integer batch) throws Exception{

    //     Connection connection = ConnectionBd.getConnection();

    //     String querySql = "INSERT INTO tbControleRecebimento(tiparq, numlot, dathraprc) values ('CLI', ?, now())";

    //     PreparedStatement preparedStatement = connection.prepareStatement(querySql);
    //     preparedStatement.setInt(1, batch);
    //     preparedStatement.execute();
    // }
}