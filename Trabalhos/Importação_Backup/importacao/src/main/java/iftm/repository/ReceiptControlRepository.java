package iftm.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReceiptControlRepository {

    
    public void saveBatch(Integer batch, String typeFile) throws Exception{

        Connection connection = ConnectionBd.getConnection();

        String querySql = "INSERT INTO tbControleRecebimento(tiparq, numlot, dathraprc) values (?, ?, now())";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql);
        preparedStatement.setString(1, typeFile);
        preparedStatement.setInt(2, batch);
        preparedStatement.execute();
    }

    public Integer getLastBatch(String fileType) throws Exception{

        Connection connection = ConnectionBd.getConnection();

        String querySql = "select MAX(numlot) as maxLote from tbControleRecebimento where tiparq = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql);
        preparedStatement.setString(1, fileType);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {

           return resultSet.getInt(1);
        }

        throw new Exception("Não foi encontrado o último lote solicitado.");

    }

}
