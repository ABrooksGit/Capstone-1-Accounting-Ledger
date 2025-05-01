package com.pluralsight;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class Main {
    /// //////////////////////////////////////////////////////////////////////////////////////////
    //Console Class
    private static Console console = new Console();


    //Transaction Manager Class
    //    private static Transaction format;

    //FileName as variable
    private static String fileName = "transactions.csv";


    //ArrayList
    private static ArrayList<Transaction> transactions = new ArrayList<>();


    /// ////////////////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {
        //Loads the Transactions from the csv file.
        //option 1
//        getAllTransactions();

        //option 2
        transactions = getAllTransactionsOpt2();


        //Loads the Home Screen
        homeScreen();
    }


    //EncodeTransactions
    private static Transaction transactionStringEncoded(String encodedFiles) {

        String[] parts = encodedFiles.split(Pattern.quote("|"));

        String dateString = parts[0];
        String timeString = parts[1];
        String description = parts[2];
        String vendor = parts[3];
        double amount = Double.parseDouble(parts[4]);


        LocalDate date = LocalDate.parse(dateString);
        LocalTime time = LocalTime.parse(timeString);

        return new Transaction(date, time, description, vendor, amount);

    }


    //Make An Array List of all transactions:
//    private static void getAllTransactions() {
//
//        try {
//            FileReader transactionList = new FileReader(fileName);
//            BufferedReader bufferedReader = new BufferedReader(transactionList);
//            transactions = new ArrayList<>();
//
//            String inputString;
//
//            while ((inputString = bufferedReader.readLine()) != null) {
//                //if the CSV file has A empty space, move past it and then continue
//                if (inputString.trim().isEmpty()) {
//                    continue;
//                }
//
//                transactions.add(transactionStringEncoded(inputString));
//
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }

    //Make An Array List of all transactions:
    private static ArrayList<Transaction> getAllTransactionsOpt2() {
        ArrayList<Transaction> result = new ArrayList<Transaction>();

        try {
            FileReader transactionList = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(transactionList);

            String inputString;

            while ((inputString = bufferedReader.readLine()) != null) {
                //if the CSV file has A empty space, move past it and then continue
                if (inputString.trim().isEmpty()) {
                    continue;
                }

                result.add(transactionStringEncoded(inputString));

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;

    }


    //Home Screen Method
    private static void homeScreen() {
        String home = """
                
                Welcome to the Accounting Ledger:
                D: Add A Deposit
                P: Make A Payment
                L: Go to the Ledger Screen
                X: Exit the Application
                
                What do you want to do?:\s""";

        // Using A String to type each letter and inputting it into the console class for the scanner.
        String input;
        do {
            input = console.promptForString(home);
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

                //Quits the application
                case "X":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid input....Please use the letters seen above");
            }
        } while (!input.equalsIgnoreCase("X"));
    }


    //Writing to the csv file
    private static void writeToLog() {


        try {
            FileWriter transactionLog = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(transactionLog);

            //Only writes the latest addition
            Transaction lastTransactionOnly = transactions.getLast();

            String formattedTxt = lastTransactionOnly.getFormattedTransaction();
            bufferedWriter.write("\n" + formattedTxt);

            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error: Could not save the Data");
        }
    }


    //Make A deposit or Payment Method
    private static void depositOrPaymentTransaction(boolean isPayment) {

        String description = console.promptForString("\nDescription of transaction: ").trim();
        String vendor = console.promptForString("Vendors Name?: ").trim();
        double amount = console.promptForDouble("Input the amount: ");


        if (isPayment) {
            amount = -Math.abs(amount);
            System.out.println("Your payment has been processed. Please look into ledger for your information.");
        } else {
            System.out.println("Your deposit has been completed. Please look into ledger for your information.");
        }

        Transaction format = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, amount);
        transactions.add(format);
        writeToLog();

    }


    //Create A Ledger Screen
    private static void ledgerScreen() {
        String ledger = """
                
                Here are the following options
                A: Display all entries
                D: Deposits only\s
                P: Payments only
                R: Go To Reports page
                H: Return to Home Screen
                
                Enter your choice:\s""";

        String choice;
        do {
            choice = console.promptForString(ledger);

            switch (choice.toUpperCase()) {
                case "A":
                    displayAllTransactions(); // Shows both Deposits and Payments
                    break;

                case "D": //Deposits Only;
                    displayTransactionOfChoice(false);
                    break;

                case "P"://Payments Only;
                    displayTransactionOfChoice(true);
                    break;

                case "R":// Go To Reports Page
                    reportScreen();
                    break;

                case "H": //Returns to the Home Page
                    break;
                default:
                    System.out.println("Wrong Input..please use the above");
            }
        } while (!choice.equalsIgnoreCase("H"));

    }


    //Display Deposits and Payments
    private static void displayAllTransactions() {

        for (Transaction t : transactions) {
            if (t.showSpecificValues().equalsIgnoreCase(t.showSpecificValues())) {
                System.out.println(t.getFormattedTransaction());
            }
        }

    }

    //Display Deposits or Payments
    private static void displayTransactionOfChoice(boolean isPayment) {

        if (isPayment) {
            for (Transaction t : transactions) {
                if (t.getAmount() < 0) {
                    System.out.println(t.getFormattedTransaction());
                }
            }

        } else {
            for (Transaction t : transactions) {
                if (t.getAmount() > 0) {
                    System.out.println(t.getFormattedTransaction());
                }
            }
        }

    }


    //Go To The Report Screen
    public static void reportScreen() {
        String reports = """
                
                This is the Report Screen
                1. Month To Date
                2. Previous Month
                3. Year To Date
                4. Previous Year
                5. Search by Vendor
                6. Custom Search
                0. Go back to Ledger Screen
                
                Enter 0 - 6:\s""";

        int reportChoice;
        do {
            reportChoice = console.promptForInt(reports);
            switch (reportChoice) {
                case 1:
                    searchViaDatesAndYears(1); // Month to Date
                    break;
                case 2:
                    searchViaDatesAndYears(2); // Previous Month
                    break;
                case 3:
                    searchViaDatesAndYears(3); // Year to Date
                    break;
                case 4:
                    searchViaDatesAndYears(4); // Previous Year
                    break;
                case 5:
                    findVendor(); // Type Vendor to search
                    break;
                case 6:
                    customSearch(new ArrayList<>(transactions)); // Custom Search
                case 0:
                    break;
            }
        } while (reportChoice != 0);
    }


    //Methods DatesAndYears
    public static void searchViaDatesAndYears(int dateFound) {

                LocalDate today = LocalDate.now();
                int currentMonth = today.getMonthValue();
                int startOfMonth = today.withDayOfMonth(1).getDayOfMonth();
                int firstDayOfMonth = 1;
                int lastDayOfTheYear = 31;
                int endOfTheLastMonth = today.minusMonths(1).lengthOfMonth();
                int currentYear = today.getYear();
                int thisMonthBegin = today.getDayOfMonth();
                int lastYear = currentYear - 1;
                int lastMonth = currentMonth - 1;
                if (lastMonth < 1) lastMonth = 12;
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate startOfTheMonth = LocalDate.of(currentYear, currentMonth, startOfMonth);

                LocalDate startOfTheLastMonth = LocalDate.of(currentYear, lastMonth, firstDayOfMonth);

                LocalDate endOfLastMonth = LocalDate.of(currentYear, lastMonth, endOfTheLastMonth);


                LocalDate startOfTheYear = LocalDate.of(currentYear, Month.JANUARY.getValue(), thisMonthBegin);
                //LocalDate today = LocalDate.now();


                LocalDate startOfLastYear = LocalDate.of(lastYear, Month.JANUARY.getValue(), firstDayOfMonth);
                LocalDate EndOfTheLastYear = LocalDate.of(lastYear, Month.DECEMBER.getValue(), lastDayOfTheYear);

                //Call the transaction array instead of reading it from the file.
                for(Transaction t: transactions){

                    LocalDate splitTheDates = t.getDate();

                switch (dateFound) {
                    case 1: //Start of the Month to Today
                        if (!splitTheDates.isBefore(startOfTheMonth) && !splitTheDates.isAfter(today)) {

                            System.out.println(t.getFormattedTransaction());

                        }
                        break;
                    case 2: //The start of last Month to End of Last Month
                        if (!splitTheDates.isBefore(startOfTheLastMonth) && !splitTheDates.isAfter(endOfLastMonth)) {
                            System.out.println(t.getFormattedTransaction());

                        }
                        break;
                    case 3://The start of the Year to Today
                        if (!splitTheDates.isBefore(startOfTheYear) && !splitTheDates.isAfter(today)) {
                            System.out.println(t.getFormattedTransaction());

                        }
                        break;
                    case 4: //The start of Last Year til the end of Last Year
                        if (!splitTheDates.isBefore(startOfLastYear) && !splitTheDates.isAfter(EndOfTheLastYear)) {
                            System.out.println(t.getFormattedTransaction());

                        }
                        break;


                }

            }



    }


    //Quick Search for Vendor
    private static void findVendor() {
        String findVendor = console.promptForString("Vendor: ");
        for (Transaction t : transactions) {
            if (t.getVendor().equalsIgnoreCase(findVendor)) {
                System.out.println(t.getFormattedTransaction());
            }
        }
    }


    //Creates a second ArrayList for the custom search functionality
    public static ArrayList<Transaction> filterTransactions(ArrayList<Transaction> input, LocalDate startDate, LocalDate endDate,
                                                            String description, String vendor, Double amount) {
        ArrayList<Transaction> result = new ArrayList<>();


        for (Transaction t : input) {
            boolean hasValue = true;

            // Filter by start date
            if (startDate != null && t.getDate().isBefore(startDate)) {
                hasValue = false;
            }

            // Filter by end date
            if (endDate != null && t.getDate().isAfter(endDate)) {
                hasValue = false;
            }

            // Filter by description
            if (description != null && !t.getDescription().toLowerCase().contains(description.toLowerCase())) {
                hasValue = false;
            }

            // Filter by vendor
            if (vendor != null && !t.getVendor().toLowerCase().contains(vendor.toLowerCase())) {
                hasValue = false;
            }

            // Filter by amount
            if (amount != null && t.getAmount() != amount) {
                hasValue = false;
            }


            if (hasValue) {
                result.add(t);
            }
        }

        return result;
    }


    //Uses the second ArrayList and uses the Console class to prompt the user
    public static void customSearch(ArrayList<Transaction> transactions) {

        //Prompts the user for the start date
        LocalDate startDate = null;
        while(true) try {
            String startDateInput = console.promptForString("Enter start date (YYYY-MM-DD): ");

            if (!startDateInput.isEmpty()) {
                startDate = LocalDate.parse(startDateInput);
            }
            break;

        } catch (DateTimeParseException e) {
            System.out.println("Incorrect Date format Please use correct format (YYYY-MM-DD) or skip.");
        }

        // Prompts the user for the end date
        LocalDate endDate = null;
        while(true) try {
            String endDateInput = console.promptForString("Enter end date (YYYY-MM-DD): ");

            if (!endDateInput.isEmpty()) {
                endDate = LocalDate.parse(endDateInput);
            }
            break;
        } catch (DateTimeParseException e) {
            System.out.println("Incorrect Date format Please use the correct format (YYYY-MM-DD) or skip");
        }

        // Prompts the user for description
        String description = console.promptForString("Enter description: ");
        if (description.isEmpty()) {
            description = null;
        }

        // Prompts the user for vendor
        String vendor = console.promptForString("Enter vendor: ");
        if (vendor.isEmpty()) {
            vendor = null;
        }

        // Prompts the user for amount
        String amountInput = console.promptForString("Enter amount: ");
        Double amount = null;
        if (!amountInput.isEmpty()) {
            try {
                amount = Double.parseDouble(amountInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount format. Skipping amount filter.");
            }
        }

        // Call filterTransactions with the provided user inputs
        ArrayList<Transaction> filteredTransactions = filterTransactions(transactions, startDate, endDate, description, vendor, amount);

        // Display the Custom Search
        for (Transaction t : filteredTransactions) {
            System.out.println(t.getFormattedTransaction());
        }
    }

}













// Ability to Search Via Typing in the Vendor(old version without ArrayList)

//    private static Transaction searchByVendor() {
//        String vendor = console.promptForString("Enter Vendor: ");
//        try {
//            FileReader displayDeposits = new FileReader(fileName);
//            BufferedReader bufferedReader = new BufferedReader(displayDeposits);
//
//            String deposits;
//
//            while ((deposits = bufferedReader.readLine()) != null) {
//                String[] depositSplits = deposits.split(Pattern.quote("|"));
//                String vendor = depositSplits[1].trim();
//                String description = depositSplits[2].trim();
//                float amount = Float.parseFloat(depositSplits[3].trim());
//
//                if (depositSplits[3].contains(vendor)) {
//
//                    System.out.println(deposits);
//                }
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }




//        // Prompts the user for the start date
//        String startDateInput = console.promptForString("Enter start date (YYYY-MM-DD): ");
//        LocalDate startDate = null;
//        try {
//            if (!startDateInput.isEmpty()) {
//                startDate = LocalDate.parse(startDateInput);
//            }
//
//        } catch (DateTimeParseException e) {
//            System.out.println("Incorrect Date format Please use correct format next time..Skipping");
//        }



// Prompts the user for the start date
//        String startDateInput = console.promptForString("Enter start date (YYYY-MM-DD): ");
//        LocalDate startDate = null;
//        while(true)
//        try {
//            if (!startDateInput.isEmpty()) {
//                startDate = LocalDate.parse(startDateInput);
//                break;
//            }
//
//        } catch (DateTimeParseException e) {
//            System.out.println("Incorrect Date format Please use correct format next time..Skipping");
//        }



//        // Prompts the user for the start date
//        while (true) {
//            if (startDateInput.isEmpty()) {
//                break;
//            } else {
//                try {
////                  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//                    startDate = LocalDate.parse(startDateInput/*,formatter)*/);
//                    break;
//                } catch (DateTimeParseException e) {
//                    System.out.println("Incorrect date format. Please use the correct format (YYYY-MM-DD) or skip.");
//                    startDateInput = console.promptForString("Enter start date (YYYY-MM-DD): ");
//                }
//            }
//        }