package com.pluralsight;

public class TransactionManager {
    String vendor;
    String description;
    double amount;
    Boolean isPayment;


    public TransactionManager(String vendor, String description, double amount, Boolean isPayment) {
        this.vendor = vendor;
        this.description = description;
        this.amount = amount;
        this.isPayment = isPayment;
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
        return isPayment;
    }

    public void setPayment(Boolean payment) {
        isPayment = payment;
    }


    public String paymentCheck(){
        String result;

        if(this.isPayment){
            result = String.format("%s|%s|-%s", this.getDescription(), this.getVendor(), this.getAmount());
        } else {
            result = String.format("%s|%s|%s", this.getDescription(), this.getVendor(), this.getAmount());
        }
        return result;
    }


}
