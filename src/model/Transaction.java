package model;

import java.util.Calendar;

public class Transaction {
    
    private String id;
    private Calendar date;
    private double totalToPay;
    
    public Transaction(String id, Calendar date, double totalToPay) {
        this.id = id;
        this.date = date;
        this.totalToPay = totalToPay;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public double getTotalToPay() {
        return totalToPay;
    }

    public void setTotalToPay(double totalToPay) {
        this.totalToPay = totalToPay;
    }
}
