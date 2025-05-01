//Constructor
package com.pluralsight;
import java.text.NumberFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;


public class Transaction {

    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;


    //output format for arraylist
    public Transaction(LocalDate date, LocalTime time,String description, String vendor,double amount) {
        this.date = date;
        this.time = time;
        this.vendor = vendor;
        this.description = description;
        this.amount = amount;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Boolean getPayment() {
        return (getAmount() < 0);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }





    public String getFormattedToLog(){
        String dateString = this.date.toString();


        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = time.format(timeFormatter);

        String result = String.format("%s|%s|%s|%s|%.2f",
                dateString,
                formattedTime,
                this.getDescription(),
                this.getVendor(),
                this.amount);

        return result;
    }




    public String getFormatted(){
        String dateString = this.date.toString();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = time.format(timeFormatter);

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        String formattedAmount = currencyFormatter.format(amount);

        return String.format("%-12s|%-10s|%-30s|%-20s|%s",
                dateString, formattedTime, this.description, this.vendor, formattedAmount);
        }




public static String getFormattedLedgerTextHeader() {
    return    "\nDATE         TIME       DESCRIPTION                    VENDOR               AMOUNT ($)\n"
            + "------------|----------|------------------------------|--------------------|----------";
}

}



