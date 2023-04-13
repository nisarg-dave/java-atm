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
     * Creates a new bank
     * @param name the name of the bank
     */
    public Bank(String name){
        this.name = name;
        // init empty lists
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }

     /**
      * Generate a new id for the user.
      * @return the uuid
      */
    public String getNewUserUUID() {

        // initialise
        String uuid = null;
        Random rng = new Random();
        int len = 6;
        boolean nonUnique;

        do {
            uuid = "";
            // generate the number
            for (int c= 0; c < len; c++){
                // Gets an integer from 0 to 10 inclusive and wraps it around the Integer class and then calls toString method.
                uuid += ((Integer)rng.nextInt(10)).toString();
            }

            nonUnique = false;
            // Check the number to make sure it's unique
            for (User u : this.users){
                if(uuid.compareTo(u.getUuid()) == 0){
                    nonUnique = true;
                }
            }
        }
        while(nonUnique);

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
        boolean nonUnique;

        do {
            uuid = "";

            // generate the number
            for (int c= 0; c < len; c++){
                // Gets an integer from 0 to 10 inclusive and wraps it around the Integer class and then calls toString method.
                uuid += ((Integer)rng.nextInt(10)).toString();
            }

            nonUnique = false;
            // Check the number to make sure it's unique
            for (Account a : this.accounts){
                if(uuid.compareTo(a.getUuid()) == 0){
                    nonUnique = true;
                }
            }
        }
        while(nonUnique);



        return uuid;

    }

    /**
     * Add an account
     * @param anAccount the account to add
     */
    public void addAccount(Account anAccount) {
        this.accounts.add(anAccount);
    }


    /**
     * Create a new user of the bank
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param pin the user's pin
     * @return the new User Object
     */
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

    /**
     * Ger the User object associated with a particular userID and pin, if they are valid
     * @param userID the UIDD of the user to login
     * @param pin the pin of the user
     * @return the User Object if successful or nul if not
     */
    public User userLogin(String userID, String pin){

        // search through list of users
        for (User u : this.users){
            if(u.getUuid().compareTo(userID) == 0 && u.validatePin(pin)){
                return u;
            }
        }

        // if we haven't found the user then return null
        return null;
    }

    /**
     * A getter method that returns the name of the bank
     * @return the name of the bank
     */
    public String getName(){
        return this.name;
    }

}

