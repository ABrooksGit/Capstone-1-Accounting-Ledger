package com.pluralsight;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.regex.Pattern;

public class Main {

    //Console Class
    private static Console console = new Console();

    //Transaction Manager Class
    private static TransactionManager format;
    //FileName as variable
    private static String fileName = "transactions.csv";
   
    //ArrayList
    private static ArrayList<TransactionManager> transactions = new ArrayList<>();

    public static void main(String[] args) {


        homeScreen();


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
                //Make A Deposit(payment will be false)
                depositOrPaymentTransaction(false);

            } else if (input.equalsIgnoreCase("P")) {
                //Make A Payment(payment will be true)
                depositOrPaymentTransaction(true);

            } else if (input.equalsIgnoreCase("L")) {
                //GO to the Ledger Screen

            } else if (input.equalsIgnoreCase("Exit")) {
                //Exiting...

            } else {
                System.out.println("Not A Valid Input, Please Type the Letters Indicated");
            }
        } while (!input.equalsIgnoreCase("Exit"));

    }


//    //Make An Array List:
//    private static ArrayList<TransactionManager> getAllTransactions() {
//
//        try {
//            FileReader transactionList = new FileReader("fileName");
//            BufferedReader bufferedReader = new BufferedReader(transactionList);
//            transactions = new ArrayList<>();
//
//            String inputString;
//
//            while ((inputString = bufferedReader.readLine()) != null) {
//
//                transactions.add(transactionStringEncoded(inputString));
//            }
//
//            return transactions;
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }


    private static void writeToLog() {


        try {
            FileWriter transactionLog = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(transactionLog);

                TransactionManager lastTransactionOnly = transactions.get(transactions.size()-1);
                LocalDateTime today = LocalDateTime.now();
                DateTimeFormatter iso =
                        DateTimeFormatter.ofPattern("\nyyyy-MM-dd|HH:mm:ss a|");
                String printedDate = today.format(iso);
                String formattedTxt = printedDate + lastTransactionOnly.paymentCheck();
                bufferedWriter.write(formattedTxt);
            
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error: Could not save the Data");
        }
    }


//    //EncodeTransactions
//    private static TransactionManager transactionStringEncoded(String encodedFiles) {
//
//        String[] parts = encodedFiles.split(Pattern.quote("|"));
//
//        String vendor = parts[3];
//        String description = parts[2];
//        double amount = Double.parseDouble(parts[4]);
//        boolean isPayment = Boolean.parseBoolean(parts[5]);
//
//        TransactionManager result = new TransactionManager(vendor, description, amount, isPayment);
//
//        return result;
//
//
//    }

    //Make A deposit or Payment Method
    private static void depositOrPaymentTransaction(boolean isPayment) {
        String vendor = console.promptForString("Vendors Name?: ").trim();
        String description = console.promptForString("Description of transaction: ").trim();
        double amount = console.promptForDouble("Input the amount: ");

        TransactionManager format = new TransactionManager(vendor, description, amount, isPayment);
        transactions.add(format);
        writeToLog();


    }

    private static void displayAllTransactions() {
        try {
            FileReader displayAll = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(displayAll);
            String next;
            while ((next = bufferedReader.readLine()) != null) {
                System.out.println(next);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
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