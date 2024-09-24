package iftm.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
// import java.sql.ResultSet;

import iftm.model.Account;

public class AccountRepository {

    public void saveAccount(Account account) throws Exception{

        Connection connection = ConnectionBd.getConnection();

        String querySql = "INSERT INTO tbConta(nrocta, cpfcli, vlrlim, diaven) values (?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql);

        preparedStatement.setString(1, account.getNumber());
        preparedStatement.setString(2, account.getClient().getCpf());
        preparedStatement.setDouble(3, account.getLimitValue());
        preparedStatement.setInt(4, account.getDueDate());

        preparedStatement.execute();

    }

    public void updateAccount(Account account) throws Exception {

        Connection connection = ConnectionBd.getConnection();
        String querySql = "UPDATE tbConta set ";
        int sequence = 1;
        
        if (account.getDueDate() != null && account.isUpdated(account.getDueDate().toString())) {

            querySql += " diaven = ?,";
        }
        if (account.getLimitValue() != null && account.isUpdated(account.getLimitValue().toString())) {

            querySql += " vlrlim = ?,";          
        }
        if (account.isUpdated(account.getClient().getCpf())) {

            querySql += " cpfcli = ?,";        
        }
        
        querySql = querySql.substring(0, querySql.length() - 1);
        querySql += "where nrocta = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql);

        if (account.getDueDate() != null && account.isUpdated(account.getDueDate().toString())) {

            preparedStatement.setInt(sequence, account.getDueDate());
            sequence++;
        }
        if (account.getLimitValue() != null && account.isUpdated(account.getLimitValue().toString())) {

            preparedStatement.setDouble(sequence, account.getLimitValue());
            sequence++;            
        }
        if (account.isUpdated(account.getClient().getCpf())) {

            preparedStatement.setString(sequence,account.getClient().getCpf());
            sequence++;            
        }

        preparedStatement.setString(sequence, account.getNumber());
        preparedStatement.execute();
    }

    public void persist(Account account) throws Exception {

        if(account.getInclusionAlteration().equals("I")) {

            saveAccount(account);

        } else if(account.getInclusionAlteration().equals("A")) {

            updateAccount(account);

        } else {

            throw new Exception("Opção desconhecida de inclusão alteração: " + account.getInclusionAlteration());
        }
    }

    // public Integer getLastBatch() throws Exception {

    //     Connection connection = ConnectionBd.getConnection(); 

    //     String querySql = "select MAX(numlot) as maxLote from tbControleRecebimento where tiparq = 'CTA'";  

    //     PreparedStatement preparedStatement = connection.prepareStatement(querySql);
    //     ResultSet resultSet = preparedStatement.executeQuery();    

    //     if(resultSet.next()){   

    //         return resultSet.getInt(1);
    //     }

    //     throw new Exception("Não foi encontrado o lote da conta.");
    // }

    // public void saveBatch(Integer batch) throws Exception {

    //     Connection connection = ConnectionBd.getConnection();

    //     String querySql = "INSERT INTO tbControleRecebimento(tiparq, numlot,dathraprc) values ('CTA', ?, now())";

    //     PreparedStatement preparedStatement = connection.prepareStatement(querySql);

    //     preparedStatement.setInt(1, batch);
    //     preparedStatement.execute();
    // }

    
}
