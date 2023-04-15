package com.atm.machine;

import java.util.Date;

class Transaction {

    /**
     * The amount of this transaction.
     */
    private double amount;

    /**
     * The time and date of this transaction.
     */
    private Date timestamp;

    /**
     * A memo for this transaction.
     */
    private String memo;

    /**
     * The account in which the transaction was performed.
     */
    private Account account;

    //Overloading constructors

    /**
     * Create a new transaction
     * @param amount the amount transacted.
     * @param account the account the transaction belongs to.
     */
    Transaction(double amount, Account account){
        this.amount = amount;
        this.account = account;
        this.timestamp = new Date();
        this.memo = "";
    }

    /**
     * Create a new transaction
     * @param amount the amount transacted.
     * @param memo the memo for the transaction.
     * @param account the account the transaction belongs to.
     */
    Transaction(double amount, String memo, Account account){

        // call the two-arg constructor first
        this(amount, account);

        // set the memo
        this.memo = memo;
    }

    /**
     * Get the amount of the transaction
     * @return the amount
     */
    double getAmount(){
        return this.amount;
    }

    String getSummaryLine(){
        if(this.amount >= 0){
            return String.format("%s : $%.02f : %s", this.timestamp.toString(), this.amount, this.memo);
        }
        else{
            // Withdrawal hence negative
            return String.format("%s : $%.02f : %s", this.timestamp.toString(), -this.amount, this.memo);
        }
    }
}
