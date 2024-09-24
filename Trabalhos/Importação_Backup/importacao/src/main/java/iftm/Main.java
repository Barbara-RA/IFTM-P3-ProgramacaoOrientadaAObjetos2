package iftm;

import iftm.model.Account;
import iftm.model.Client;
import iftm.model.Plastic;
import iftm.model.Transaction;
import iftm.service.ProcessAccountLines;
import iftm.service.ProcessClientLines;
import iftm.service.ProcessFile;
import iftm.service.ProcessPlasticLines;
import iftm.service.ProcessTransactionLines;

public class Main {
    public static void main(String[] args) throws Exception{

        ProcessFile processClient = new ProcessFile(Client.CLIENT_PREFIX, new ProcessClientLines());
        processClient.process();

        ProcessFile processAccount = new ProcessFile(Account.ACCOUNT_PREFIX, new ProcessAccountLines());
        processAccount.process();
        
        ProcessFile processPlastic = new ProcessFile(Plastic.PLASTIC_PREFIX, new ProcessPlasticLines());
        processPlastic.process();
        
        ProcessFile processTransaction = new ProcessFile(Transaction.TRANSACTION_PREFIX, new ProcessTransactionLines());
        processTransaction.process();
    
    }
}