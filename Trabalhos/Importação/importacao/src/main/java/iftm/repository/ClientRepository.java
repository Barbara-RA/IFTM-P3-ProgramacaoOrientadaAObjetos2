package iftm.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import iftm.model.Client;

public class ClientRepository {

    public void saveClient(Client client) throws Exception {
        String querySql = "INSERT INTO tbCliente (cpfcli, nomcli, endcli, baicli, cidcli, sigest, datcad) values (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionBd.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(querySql)) {

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
    }

    public void updateClient(Client client) throws Exception {
        String querySql = "UPDATE tbCliente SET ";
        
        if (client.isUpdated(client.getName())) querySql += "nomcli = ?, ";
        if (client.isUpdated(client.getAddress())) querySql += "endcli = ?, ";
        if (client.isUpdated(client.getNeighborhood())) querySql += "baicli = ?, ";
        if (client.isUpdated(client.getCity())) querySql += "cidcli = ?, ";
        if (client.isUpdated(client.getState())) querySql += "sigest = ?, ";

        querySql = querySql.substring(0, querySql.length() - 2); // Remove a última vírgula
        querySql += " WHERE cpfcli = ?";

        try (Connection connection = ConnectionBd.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(querySql)) {

            int sequence = 1;
            if (client.isUpdated(client.getName())) preparedStatement.setString(sequence++, client.getName());
            if (client.isUpdated(client.getAddress())) preparedStatement.setString(sequence++, client.getAddress());
            if (client.isUpdated(client.getNeighborhood())) preparedStatement.setString(sequence++, client.getNeighborhood());
            if (client.isUpdated(client.getCity())) preparedStatement.setString(sequence++, client.getCity());
            if (client.isUpdated(client.getState())) preparedStatement.setString(sequence++, client.getState());

            preparedStatement.setString(sequence, client.getCpf());
            preparedStatement.execute();
        }
    }

    public void persist(Client client) throws Exception {
        if (client.getInclusionAlteration().equals("I")) {
            saveClient(client);
        } else if (client.getInclusionAlteration().equals("A")) {
            updateClient(client);
        } else {
            throw new IllegalArgumentException("Opção desconhecida de inclusão/alteração: " + client.getInclusionAlteration());
        }
    }
}
