package iftm.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import iftm.model.Plastic;

public class PlasticRepository {

    public void savePlastic(Plastic plastic) throws Exception {
        String querySql = "INSERT INTO tbPlastico(nropla, nrocta, nompla, cpfpla) values (?, ?, ?, ?)";

        try (Connection connection = ConnectionBd.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(querySql)) {

            preparedStatement.setString(1, plastic.getPlasticNumber());
            preparedStatement.setString(2, plastic.getAccountNumber());
            preparedStatement.setString(3, plastic.getPlasticName());
            preparedStatement.setString(4, plastic.getCpf());

            preparedStatement.execute();
        }
    }

    public void updatePlastic(Plastic plastic) throws Exception {
        String querySql = "UPDATE tbPlastico SET ";

        if (plastic.getPlasticNumber() != null && plastic.isUpdated(plastic.getPlasticNumber())) {
            querySql += "nropla = ?, ";
        }
        if (plastic.getPlasticName() != null && plastic.isUpdated(plastic.getPlasticName())) {
            querySql += "nompla = ?, ";
        }
        if (plastic.getCpf() != null && plastic.isUpdated(plastic.getCpf())) {
            querySql += "cpfpla = ?, ";
        }

        querySql = querySql.substring(0, querySql.length() - 2); // Remove a última vírgula
        querySql += " WHERE nrocta = ?";

        try (Connection connection = ConnectionBd.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(querySql)) {

            int sequence = 1;
            if (plastic.getPlasticNumber() != null && plastic.isUpdated(plastic.getPlasticNumber())) {
                preparedStatement.setString(sequence++, plastic.getPlasticNumber());
            }
            if (plastic.getPlasticName() != null && plastic.isUpdated(plastic.getPlasticName())) {
                preparedStatement.setString(sequence++, plastic.getPlasticName());
            }
            if (plastic.getCpf() != null && plastic.isUpdated(plastic.getCpf())) {
                preparedStatement.setString(sequence++, plastic.getCpf());
            }

            preparedStatement.setString(sequence, plastic.getAccountNumber());
            preparedStatement.execute();
        }
    }

    public void persist(Plastic plastic) throws Exception {
        if (plastic.getPlasticNumber() == null) {
            throw new IllegalArgumentException("Número da conta não pode ser nulo!");
        } else if (plastic.getPlasticNumber() != null) {
            savePlastic(plastic);
        } else {
            updatePlastic(plastic);
        }
    }
}
