package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Main {

    //Console Class
    private static Console console = new Console();

    //Transaction Manager Class
    private static TransactionManager format;
    //Hash
    static HashMap<String, TransactionManager> entireList = new HashMap<String, TransactionManager>();
    //ArrayList
    private static ArrayList<TransactionManager> transactions;

    public static void main(String[] args) {


        displayAllTransactions();


    }


    //Home Screen Method

    private static void homeScreen() {
        //Beginning Prompt:
        String home = "Welcome to the Accounting Ledger\n" +
                "Here are your options: \n" +
                "D: Add A Deposit\n " +
                "P: Make A Payment\n" +
                "L: Go to the Ledger\n" +
                "Exit: Exit the Application\n" +
                "Type Any of the Above Letters or Exit: ";


        // Using A String to type each letter and inputting it into the console class for the scanner.
        String input;
        //Wrap the home screen around a Do method so that this section is always running.


        do {
            input = console.promptForString(home);
            if (input.equalsIgnoreCase("D")) {
                //Make A Deposit

            } else if (input.equalsIgnoreCase("P")) {
                //Make A Payment

            } else if (input.equalsIgnoreCase("L")) {
                //GO to the Ledger Screen

            } else if (input.equalsIgnoreCase("Exit")) {
                //Exiting...

            } else {
                System.out.println("Not A Valid Input, Please Type the Letters Indicated");
            }
        } while (!input.equalsIgnoreCase("Exit"));

    }


    //Make An Array List:
    private static ArrayList<TransactionManager> getAllTransactions() {

        try {
            FileReader transactionList = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(transactionList);
            transactions = new ArrayList<>();

            String inputString;

            while ((inputString = bufferedReader.readLine()) != null) {

                transactions.add(transactionStringEncoded(inputString));
            }

            return transactions;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    //EncodeTransactions
    private static TransactionManager transactionStringEncoded(String encodedFiles) {

        String[] parts = encodedFiles.split(Pattern.quote("|"));

        String vendor = parts[3];
        String description = parts[2];
        double amount = Double.parseDouble(parts[4]);
        boolean isPayment = Boolean.parseBoolean(parts[5]);

        TransactionManager result = new TransactionManager(vendor, description, amount, isPayment);

        return result;


    }

    //Make A deposit Method
    private static void makeADeposit() {

        for(TransactionManager manager : transactions){
            entireList.put(manager.getVendor(), manager);

        }
        String vendor = console.promptForString("Enter the Vendor's name for this deposit: ").trim();
        String description = console.promptForString("Add a description of this deposit: ").trim();
        double amount = console.promptForDouble("Input the money gained: ");

//        TransactionManager manager = entireList.get();


    }

    private static void displayAllTransactions() {
        for (TransactionManager transaction : transactions) {
            System.out.println(transaction.paymentCheck());
        }
    }






    //Make A Payment Method


    //Create A Ledger Screen


    //Display Every Entry inside of Transaction txt


    //Display All Deposits


    //Display ALl Payments


    //GO TO The Report Screen


    //Methods for Month To Day


    //Methods for Previous Month


    //Methods for Year To Date


    //Methods for the Previous Year


    //Ability to Search Via Typing in the Vendor


    //Format the time and dates to the log using TransactionManager Class



    // Display The Format For The Time and Dates to the Console itself


    //Method to display only the deposits


    //Method to only display the payments


}