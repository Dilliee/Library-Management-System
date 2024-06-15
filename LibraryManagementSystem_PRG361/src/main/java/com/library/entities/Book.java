package com.library.entities;

public class Book {
    private int bookID;
    private String title;
    private int authorID;
    private String isbn;
    private int publishedYear;
    private int copiesAvailable;

    // Default constructor
    public Book() {}

    // Constructor for creating a new book without ID (used when adding a book)
    public Book(String title, int authorID, String isbn, int publishedYear, int copiesAvailable) {
        this.title = title;
        this.authorID = authorID;
        this.isbn = isbn;
        this.publishedYear = publishedYear;
        this.copiesAvailable = copiesAvailable;
    }

    // Constructor for retrieving a book with ID from the database
    public Book(int bookID, String title, int authorID, String isbn, int publishedYear, int copiesAvailable) {
        this.bookID = bookID;
        this.title = title;
        this.authorID = authorID;
        this.isbn = isbn;
        this.publishedYear = publishedYear;
        this.copiesAvailable = copiesAvailable;
    }

    // Getters and setters
    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }
}
