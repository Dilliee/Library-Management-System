package com.library.entities;

public class Borrower {
    private int borrowerID;
    private String name;
    private String email;
    private String phone;

    // Default constructor
    public Borrower() {}

    // Constructor for creating a new borrower without ID (used when adding a borrower)
    public Borrower(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // Constructor for retrieving a borrower with ID from the database
    public Borrower(int borrowerID, String name, String email, String phone) {
        this.borrowerID = borrowerID;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // Getters and setters
    public int getBorrowerID() {
        return borrowerID;
    }

    public void setBorrowerID(int borrowerID) {
        this.borrowerID = borrowerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
