package org.project.transaction;

public class Transaction
{

    int transactionId = 0;

    private String sender;
    private String receiver;
    private double amount;

    public Transaction(int transactionId, String sender, String receiver, double amount)
     {
        this.transactionId  = transactionId;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public double getAmount() {
        return amount;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString()
    {
        return receiver+ " owes " + sender + ": " + amount;
    }
}


//        User4 owes User1: 250
//        User2 owes User1: 250
//        User3 owes User1: 250
//        User4 owes User1: 250
//        User2 owes User1: 620
//        User3 owes User1: 1130
//        User4 owes User1: 250
//        User1 owes User4: 230
//        User2 owes User1: 620
//        User3 owes User1: 1130
//        User1 owes User4: 230
//        User2 owes User1: 620
//        User2 owes User4: 240
//        User3 owes User1: 1130
//        User3 owes User4: 240