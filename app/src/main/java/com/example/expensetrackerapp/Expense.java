package com.example.expensetrackerapp;

public class Expense {
    private int id;
    private double amount;
    private String description;
    private String timestamp;

    public Expense(int id, double amount, String description, String timestamp) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
