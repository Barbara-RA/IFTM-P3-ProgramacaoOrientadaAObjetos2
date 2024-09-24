package iftm.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import iftm.model.Transaction;

public class TransactionRepository {

    public void saveTransaction(Transaction transaction) throws Exception {
        String querySql = "INSERT INTO tbTransacao(nrocta, nropla, vlrtra, dattra, codloj) values (?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionBd.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(querySql)) {

            preparedStatement.setString(1, transaction.getAccountNumber());
            preparedStatement.setString(2, transaction.getPlasticNumber());
            preparedStatement.setDouble(3, transaction.getTransactionValue());
            preparedStatement.setDate(4, Date.valueOf(transaction.getTransactionDate()));
            preparedStatement.setString(5, transaction.getEstablishmentCode());

            preparedStatement.execute();
        }
    }

    public void updateTransaction(Transaction transaction) throws Exception {
        String querySql = "UPDATE tbTransacao SET ";

        if (transaction.getPlasticNumber() != null && transaction.isUpdated(transaction.getPlasticNumber())) {
            querySql += "nropla = ?, ";
        }
        if (transaction.getTransactionValue() != null && transaction.isUpdated(transaction.getTransactionValue().toString())) {
            querySql += "vlrtra = ?, ";
        }
        if (transaction.getTransactionDate() != null && transaction.isUpdated(transaction.getTransactionDate().toString())) {
            querySql += "dattra = ?, ";
        }
        if (transaction.getEstablishmentCode() != null && transaction.isUpdated(transaction.getEstablishmentCode())) {
            querySql += "codloj = ?, ";
        }

        querySql = querySql.substring(0, querySql.length() - 2); // Remove a última vírgula
        querySql += " WHERE nrocta = ?";

        try (Connection connection = ConnectionBd.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(querySql)) {

            int sequence = 1;
            if (transaction.getPlasticNumber() != null && transaction.isUpdated(transaction.getPlasticNumber())) {
                preparedStatement.setString(sequence++, transaction.getPlasticNumber());
            }
            if (transaction.getTransactionValue() != null && transaction.isUpdated(transaction.getTransactionValue().toString())) {
                preparedStatement.setDouble(sequence++, transaction.getTransactionValue());
            }
            if (transaction.getTransactionDate() != null && transaction.isUpdated(transaction.getTransactionDate().toString())) {
                preparedStatement.setDate(sequence++, Date.valueOf(transaction.getTransactionDate()));
            }
            if (transaction.getEstablishmentCode() != null && transaction.isUpdated(transaction.getEstablishmentCode())) {
                preparedStatement.setString(sequence++, transaction.getEstablishmentCode());
            }

            preparedStatement.setString(sequence, transaction.getAccountNumber());
            preparedStatement.execute();
        }
    }

    public void persist(Transaction transaction) throws Exception {
        if (transaction.getAccountNumber() == null) {
            throw new IllegalArgumentException("Número da conta não pode ser nulo!");
        } else if (transaction.getPlasticNumber() != null) {
            saveTransaction(transaction);
        } else {
            updateTransaction(transaction);
        }
    }
}
