package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
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
            input = console.promptForString(home).trim();
            switch (input.toUpperCase()) {
                case "D":
                    // Make A Deposit (payment will be false)
                    depositOrPaymentTransaction(false);
                    break;

                case "P":
                    // Make A Payment (payment will be true)
                    depositOrPaymentTransaction(true);
                    break;

                case "L":
                    // Go to the Ledger Screen
                    ledgerScreen();
                    break;

                case "EXIT":
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Not A Valid Input, Please Type the Letters Indicated");
                    break;
            }
        } while (!input.equalsIgnoreCase("Exit"));


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
    }


        private static void writeToLog () {


            try {
                FileWriter transactionLog = new FileWriter(fileName, true);
                BufferedWriter bufferedWriter = new BufferedWriter(transactionLog);

                TransactionManager lastTransactionOnly = transactions.get(transactions.size() - 1);
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
    private static void ledgerScreen() {
        String ledger = "Here are the following options\n " +
                "A: Display all entries\n" +
                "D: Deposits only \n" +
                "P: Payments only\n" +
                "R: Go To Reports page";

        String choice;
        do {

            choice = console.promptForString(ledger);
            switch (choice.toUpperCase()){
                case "A": displayAllTransactions();
                break;
                case "D": //Deposits Only;
                    break;
                case "P"://Payments Only;
                break;
                case "R":// Go To Reports Page
                break;
                case "H":

            }



        }while (choice.equalsIgnoreCase("H"));


    }


    //Display Every Entry inside of Transaction txt


    //Display All Deposits


    //Display ALl Payments


    //Go To The Report Screen
    public static void reportScreen(){
        String reports = "This is the Report Screen\n" +
                "1. Month To Date\n" +
                "2. Previous Month\n" +
                "3. Year To Date\n" +
                "4. Previous Year\n" +
                "5. Search by Vendor\n" +
                "0. Go back to Ledger Screen\n" +
                "Enter 0 - 5: ";


        int reportChoice;

        do{
            reportChoice = console.promptForInt(reports);
            


        }



    }





    //Methods DatesAndYears
    public static void searchViaDatesAndYears(int dateFound) {
        try {
            FileReader displayDeposits = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(displayDeposits);

            String search;
            while ((search = bufferedReader.readLine()) != null) {
                String[] yearAndMonthSplit = search.split(Pattern.quote("|"));


                LocalDate startOfTheMonth = LocalDate.of(2025, 4, 1);
                LocalDate today = LocalDate.now();

                LocalDate startOfTheLastMonth = LocalDate.of(2025, 3, 1);

                LocalDate endOfLastMonth = LocalDate.of(2025, 3, 31);


                LocalDate startOfTheYear = LocalDate.of(2025, 1, 1);
                //LocalDate today = LocalDate.now();


                LocalDate startOfLastYear = LocalDate.of(2024, 1, 1);
                LocalDate EndOfTheLastYear = LocalDate.of(2024, 12, 31);


                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


                LocalDate splitTheDates = LocalDate.parse(yearAndMonthSplit[0], formatter);


                switch (dateFound) {
                    case 1: //Start of the Month to Today
                        if (!splitTheDates.isBefore(startOfTheMonth) && !splitTheDates.isAfter(today)) {

                            System.out.println(search);

                        }
                        break;
                    case 2: //The start of last Month to End of Last Month
                        if (!splitTheDates.isBefore(startOfTheLastMonth) && !splitTheDates.isAfter(endOfLastMonth)) {
                            System.out.println(search);

                        }
                        break;
                    case 3://The start of the Year to Today
                        if (!splitTheDates.isBefore(startOfTheYear) && !splitTheDates.isAfter(today)) {
                            System.out.println(search);

                        }
                        break;
                    case 4: //The start of Last Year til the end of Last Year
                        if (!splitTheDates.isBefore(startOfLastYear) && !splitTheDates.isAfter(EndOfTheLastYear)) {
                            System.out.println(search);

                        }
                        break;


                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



    //Ability to Search Via Typing in the Vendor


    //Format the time and dates to the log using TransactionManager Class


    // Display The Format For The Time and Dates to the Console itself


    //Method to display only the deposits


    //Method to only display the payments


}