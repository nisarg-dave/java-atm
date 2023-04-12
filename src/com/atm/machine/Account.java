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

}

