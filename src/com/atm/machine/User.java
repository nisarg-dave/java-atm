package com.atm.machine;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

class User {

    /**
     * The first name of the user.
     */
    private String firstName;

    /**
     * The last name of the user.
     */
    private String lastName;

    /**
     * The user ID
     */
    private String uuid;

    /**
     * The MD5 hash of the user's pin
      */
    private byte[] pinhash;

    /**
     * The list of accounts for this user.
     */
    private ArrayList<Account> accounts;

    /**
     * Create a new user.
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param pin the user's account pin
     * @param theBank the Bank object that the user is a customer of
     */
    User(String firstName, String lastName, String pin, Bank theBank) {

        // set user's name
        this.firstName = firstName;
        this.lastName = lastName;

        try {
            // store the pin's MD5 hash, rather than the original value for
            // Java doesn't know/like the string inside getInstance and so need to handle it
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Gets the bytes from the String pin and hashes them
            this.pinhash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        // get a new ID for the user
        this.uuid = theBank.getNewUserUUID();

        // Create empty list of accounts
        this.accounts = new ArrayList<Account>();

        System.out.printf(
                "New user %s, %s with ID %s created.\n",
                lastName,
                firstName,
                this.uuid
        );
    }

    /**
     * Add an account for the user
     * @param anAccount the account to add
     */
    void addAccount(Account anAccount) {
        this.accounts.add(anAccount);
    }

    /**
     * A getter method that returns the uuid
     * @return the uuid
     */
    String getUuid(){
        return this.uuid;
    }

    /**
     * Check whether a given pin matches the user's pin
     * @param aPin a pin that is entered by the user
     * @return true if they match, false if they don't
     */
     boolean validatePin(String aPin) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Uses isEqual static method to compare
            return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinhash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }
        return false;
    }

    /**
     * A getter method that returns the first name
     * @return first name of the user
     */
    String getFirstName(){
        return this.firstName;
    }

    /**
     * Print summaries for the accounts of this user.
     */
    void printAccountsSummary(){

           System.out.printf("\n\n%s's accounts summary\n", this.firstName);

           int accountIndex = 0;
           for(Account a: this.accounts){
               accountIndex += 1;
               System.out.printf("%d) %s", accountIndex, a.getSummaryLine());
           }
           System.out.println();

    }

    /**
     * A getter method that returns the number of accounts
     * @return number of accounts
     */
    int numAccounts(){
        return this.accounts.size();
    }

    /**
     * Prints the transaction history of an account
     * @param acctIdx the account index
     */
    void printAccTransHistory(int acctIdx){
        this.accounts.get(acctIdx).printTransHistory();
    }

    /**
     * Gets the account balance
     * @param accIdx the account index
     * @return the total balance
     */
    double getAcctBalance(int accIdx){
        return this.accounts.get(accIdx).getBalance();
    }

    /**
     * Gets the account id
     * @param acctIdx the account index
     * @return the account id
     */
    String getAcctUUID(int acctIdx){
        return this.accounts.get(acctIdx).getUuid();
    }

    void addAcctTransaction(int acctIdx, double amount, String memo){
        this.accounts.get(acctIdx).addTransaction(amount,memo);
    }
}

