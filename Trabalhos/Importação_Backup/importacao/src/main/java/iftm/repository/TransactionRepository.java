package iftm.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
// import java.sql.ResultSet;
import java.sql.Date;

import iftm.model.Transaction;

public class TransactionRepository {
    
    public void saveTransaction(Transaction transaction) throws Exception{

        Connection connection = ConnectionBd.getConnection();

        String querySql = "INSERT INTO tbTransacao(nrocta, nropla, vlrtra, dattra, codloj) values (?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql);

        preparedStatement.setString(1, transaction.getAccountNumber());
        preparedStatement.setString(2, transaction.getPlasticNumber());
        preparedStatement.setDouble(3, transaction.getTransactionValue());
        preparedStatement.setDate(4, Date.valueOf(transaction.getTransactionDate()));
        preparedStatement.setString(5, transaction.getEstablishmentCode());

        preparedStatement.execute();

    }

    public void updateTransaction(Transaction transaction) throws Exception {

        Connection connection = ConnectionBd.getConnection();
        
        String querySql = "UPDATE tbTransacao SET ";
        int sequence = 1;

        if (transaction.getPlasticNumber() != null && transaction.isUpdated(transaction.getPlasticNumber().toString())) {

            querySql += " nropla = ?,";
        }
        if (transaction.getPlasticNumber() != null  && transaction.isUpdated(transaction.getTransactionValue().toString())) {

            querySql += " vlrtra = ?,";
        }
        if (transaction.getTransactionDate()!= null  && transaction.isUpdated(transaction.getTransactionDate().toString())) {

            querySql += " dattra = ?,";
        }
        if (transaction.getEstablishmentCode() != null && transaction.isUpdated(transaction.getEstablishmentCode().toString())) {

            querySql += " codloj = ?,";
        }

        querySql = querySql.substring(0, querySql.length() - 1); 
        querySql += " WHERE nrocta = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql);

        if (transaction.getPlasticNumber() != null && transaction.getPlasticNumber().length() > 0) {

            preparedStatement.setString(sequence, transaction.getPlasticNumber());
            sequence++;
        }
        if (transaction.getPlasticNumber() != null && transaction.isUpdated(transaction.getTransactionValue().toString())) {

            preparedStatement.setString(sequence, transaction.getPlasticNumber());
            sequence++;
        }
        if (transaction.getTransactionDate() != null && transaction.isUpdated(transaction.getTransactionDate().toString())) {

            preparedStatement.setDate(sequence, Date.valueOf(transaction.getTransactionDate()));
            sequence++;
        }
        if (transaction.getEstablishmentCode() != null && transaction.isUpdated(transaction.getEstablishmentCode().toString())) {

            preparedStatement.setString(sequence, transaction.getEstablishmentCode());
            sequence++;
        }

        preparedStatement.setString(sequence, transaction.getAccountNumber());
        preparedStatement.execute();
    }

    public void persist(Transaction transaction) throws Exception {
        
        if (transaction.getAccountNumber() == null) {

            throw new Exception("Número da conta não pode ser nulo!");

        } else if(transaction.getPlasticNumber() != null) {

            saveTransaction(transaction);

        } else {

            updateTransaction(transaction);
        }
    }

    // public Integer getLastBatch() throws Exception {

    //     Connection connection = ConnectionBd.getConnection(); 

    //     String querySql = "select MAX(numlot) as maxLote from tbControleRecebimento where tiparq = 'TRA'";  

    //     PreparedStatement preparedStatement = connection.prepareStatement(querySql);
    //     ResultSet resultSet = preparedStatement.executeQuery();    

    //     if(resultSet.next()){   

    //         return resultSet.getInt(1);
    //     }

    //     throw new Exception("Não foi encontrado o lote da conta.");
    // }

    // public void saveBatch(Integer batch) throws Exception {

    //     Connection connection = ConnectionBd.getConnection();

    //     String querySql = "INSERT INTO tbControleRecebimento(tiparq, numlot,dathraprc) values ('TRA', ?, now())";

    //     PreparedStatement preparedStatement = connection.prepareStatement(querySql);

    //     preparedStatement.setInt(1, batch);
    //     preparedStatement.execute();
    // }
}
