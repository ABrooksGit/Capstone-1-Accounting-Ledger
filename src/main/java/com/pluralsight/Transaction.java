package com.pluralsight;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Transaction {

    LocalDate date;
    LocalTime time;
    String vendor;
    String description;
    double amount;
    //Boolean isPayment;


    public Transaction(LocalDate date, LocalTime time, String vendor, String description, double amount) {
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

    //    public void setPayment(Boolean payment) {
//        isPayment = payment;
//    }


    public String getFormattedTransaction(){
        String dateStr = this.date.toString();
        String timeStr = this.time.toString();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = time.format(timeFormatter);

        String result = String.format("%s|%s|%s|%s|%.2f",
                dateStr,
                formattedTime,
                this.getDescription(),
                this.getVendor(),
                amount);
//        if(this.isPayment){
//            result = String.format("%s|%s|-%.2f", this.getDescription(), this.getVendor(), this.getAmount());
//        } else {
//
//        }
        return result;
    }


}
