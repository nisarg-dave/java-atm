package com.atm.machine;

import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {

        // initialise Scanner
        Scanner sc = new Scanner(System.in);

        // init Bank
        Bank theBank = new Bank("Bank of Perth");

        // add a user, which also creates a savings account
        User aUser = theBank.addUser("John", "Wick", "123");

        // add a check account for our user
        Account newAccount = new Account("Checking", aUser, theBank);
        // Add account to user's and bank's lists
        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);

        User curUser;
        while(true){

            // stay in the login prompt until successful login
            curUser = ATM.mainMenuPrompt(theBank,sc);

            // stay in main menu until user quits
            ATM.printUserMenu(curUser, sc);
        }
    }

    /**
     * Print the ATM's login menu
     * @param theBank the Bank object
     * @param sc the Scanner object for user inputs
     * @return authenticated user
     */
    public static User mainMenuPrompt(Bank theBank, Scanner sc){
        // inits
        String userID;
        String pin;
        User authUser;

        // prompt the user for ID/pin combo until a correct one is reached
        do {
            System.out.printf("\n\nWelcome to %s\n\n", theBank.getName());
            System.out.printf("Enter userID: ");
            userID = sc.nextLine();
            System.out.printf("Enter pin: ");
            pin = sc.nextLine();

            // try to get the user object corresponding to the ID and pin combo
            authUser = theBank.userLogin(userID, pin);
            if(authUser == null){
                System.out.println("Incorrect user ID/pin combination. " + "Please try again.");
            }

        } while(authUser == null); // continue looping until successful login

        return authUser;
    }

    public static void printUserMenu(User curUser, Scanner sc) {}
}
