package com.atm.machine;

import java.util.ArrayList;
import java.util.Random;

class Bank {

    /**
     * Name of the bank.
     */
    private String name;

    /**
     * List of customers.
     */
    private ArrayList<User> users;

    /**
     * List of accounts
     */
    private ArrayList<Account> accounts;

     /**
      * Generate a new id for the user.
      * @return the uuid
      */
    public String getNewUserUUID() {

        // initialise
        String uuid = null;
        Random rng = new Random();
        int len = 6;
        boolean nonUnique = false;

        while(!nonUnique) {
            uuid = "";

            // generate the number
            for (int c= 0; c < len; c++){
                // Gets an integer from 0 to 10 inclusive and wraps it around the Integer class and then calls toString method.
                uuid += ((Integer)rng.nextInt(10)).toString();
            }

            // Check the number to make sure it's unique
            for (User u : this.users){
                if(uuid.compareTo(u.getUuid()) == 0){
                    nonUnique = true;
                }
            }
        }

        return uuid;
    }


    /**
     * Generate a new id for an account
     * @return the uuid
     */
    public String getNewAccountUUID() {
        // initialise
        String uuid = null;
        Random rng = new Random();
        int len = 10;
        boolean nonUnique = false;

        while(!nonUnique) {
            uuid = "";

            // generate the number
            for (int c= 0; c < len; c++){
                // Gets an integer from 0 to 10 inclusive and wraps it around the Integer class and then calls toString method.
                uuid += ((Integer)rng.nextInt(10)).toString();
            }

            // Check the number to make sure it's unique
            for (Account a : this.accounts){
                if(uuid.compareTo(a.getUuid()) == 0){
                    nonUnique = true;
                }
            }
        }

        return uuid;

    }

    /**
     * Add an account
     * @param anAccount the account to add
     */
    public void addAccount(Account anAccount) {
        this.accounts.add(anAccount);
    }


    public User addUser(String firstName, String lastName, String pin) {

        // create a new User object and add it oto our list
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);

        // create a savings account for the user
        Account newAccount = new Account("Savings", newUser, this);
        newUser.addAccount(newAccount);
        this.addAccount(newAccount);

        return newUser;
    }
}

