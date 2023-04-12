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
    public void addAccount(Account anAccount) {
        this.accounts.add(anAccount);
    }

    /**
     * A getter method that returns the uuid
     * @return the uuid
     */
    public String getUuid(){
        return this.uuid;
    }
}

