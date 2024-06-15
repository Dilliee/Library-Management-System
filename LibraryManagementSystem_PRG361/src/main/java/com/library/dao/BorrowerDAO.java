package com.library.dao;

import com.library.db.DatabaseConnection;
import com.library.entities.Borrower;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowerDAO {
    private Connection connection;

    public BorrowerDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // Add a new borrower to the database
    public void addBorrower(Borrower borrower) {
        String query = "INSERT INTO Borrowers (name, email, phone) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, borrower.getName());
            stmt.setString(2, borrower.getEmail());
            stmt.setString(3, borrower.getPhone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve all borrowers from the database
    public List<Borrower> getAllBorrowers() {
        List<Borrower> borrowers = new ArrayList<>();
        String query = "SELECT * FROM Borrowers";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Borrower borrower = new Borrower(
                    rs.getInt("borrowerID"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone")
                );
                borrowers.add(borrower);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrowers;
    }

    // Update an existing borrower in the database
    public void updateBorrower(Borrower borrower) {
        String query = "UPDATE Borrowers SET name = ?, email = ?, phone = ? WHERE borrowerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, borrower.getName());
            stmt.setString(2, borrower.getEmail());
            stmt.setString(3, borrower.getPhone());
            stmt.setInt(4, borrower.getBorrowerID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a borrower from the database
    public void deleteBorrower(int borrowerID) {
        String query = "DELETE FROM Borrowers WHERE borrowerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, borrowerID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
