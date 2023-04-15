package com.atm.machine;

import java.util.ArrayList;


 class Account {

    /**
     * The name of the account.
     */
    private String name;

     /**
      * The account ID.
      */
    private String uuid;

     /**
      * The User object that holds this account.
      */
    private User holder;

     /**
      * The list of transactions for this account.
      */
    private ArrayList<Transaction> transactions;

     /**
      * Create a new Account
      * @param name the name of the account
      * @param holder the User object that holds this account
      * @param theBank the bank that issues the account
      */
    Account(String name, User holder, Bank theBank) {

        // set the account name and holder
        this.name = name;
        this.holder = holder;

        // get new account id
        this.uuid = theBank.getNewAccountUUID();

        // initialise transcations
        this.transactions = new ArrayList<Transaction>();
    }

     /**
      * A getter method that returns uuid
      * @return the uuid
      */
    public String getUuid(){
        return this.uuid;
    }

     /**
      * Get summary line for the account
      * @return the string summary
      */
    public String getSummaryLine(){

        // get the account balance
        double balance = this.getBalance();

        // format the summary line, depending on whether the balance is negative
        if (balance >= 0){
            // .02f means print floating point with 2 digit precision (saving memory)
            return String.format("%s : $%.02f : %s\n", this.uuid, balance, this.name);
        }
        else{
            // Accounting convetion used for negative values
            return String.format("%s : $(%.02f) : %s\n", this.uuid, balance, this.name);
        }
    }

     /**
      * Getter to get balance of account
      * @return the total balance
      */
    public double getBalance(){
        double balance = 0;
        for (Transaction t: this.transactions){
            balance += t.getAmount();
        }
        return balance;
    }

     /**
      * Print the transaction history of the account
      */
    public void printTransHistory(){

        System.out.printf("\nTransaction history for account %s\n", this.uuid);
        for(int t = this.transactions.size()-1; t>=0; t--){
            System.out.println(this.transactions.get(t).getSummaryLine());
        }
        System.out.println();
    }

     /**
      * Adds a new transaction
      * @param amount the amount of the transaction
      * @param memo the memo
      */
    public void addTransaction(double amount, String memo){
        // Create new transaction object and add it to our list
        Transaction newTrans = new Transaction(amount, memo, this);
        this.transactions.add(newTrans);
    }

}

