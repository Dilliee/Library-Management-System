package com.library.dao;

import com.library.db.DatabaseConnection;
import com.library.entities.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private Connection connection;

    public BookDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // Add a new book to the database
    public void addBook(Book book) {
    String query = "INSERT INTO Books (title, authorID, isbn, publishedYear, copiesAvailable) VALUES (?, ?, ?, ?, ?)";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setString(1, book.getTitle());
        stmt.setInt(2, book.getAuthorID());
        stmt.setString(3, book.getIsbn());
        stmt.setInt(4, book.getPublishedYear());
        stmt.setInt(5, book.getCopiesAvailable());
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    // Retrieve all books from the database
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM Books";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Book book = new Book(
                    rs.getInt("bookID"),
                    rs.getString("title"),
                    rs.getInt("authorID"),
                    rs.getString("isbn"),
                    rs.getInt("publishedYear"),
                    rs.getInt("copiesAvailable")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    // Update an existing book in the database
    public void updateBook(Book book) {
        String query = "UPDATE Books SET title = ?, authorID = ?, isbn = ?, publishedYear = ?, copiesAvailable = ? WHERE bookID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, book.getTitle());
            stmt.setInt(2, book.getAuthorID());
            stmt.setString(3, book.getIsbn());
            stmt.setInt(4, book.getPublishedYear());
            stmt.setInt(5, book.getCopiesAvailable());
            stmt.setInt(6, book.getBookID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a book from the database
    public void deleteBook(int bookID) {
        String query = "DELETE FROM Books WHERE bookID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, bookID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
