package com.library.gui;

import com.library.dao.BorrowerDAO;
import com.library.entities.Borrower;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class BorrowerManagementFrame extends JFrame {
    private JTable borrowersTable;
    private DefaultTableModel tableModel;
    private JTextField nameField, emailField, phoneField;
    private BorrowerDAO borrowerDAO;

    public BorrowerManagementFrame() {
        borrowerDAO = new BorrowerDAO();

        setTitle("Borrower Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Layout components
        JPanel panel = new JPanel(new BorderLayout());

        // Table for displaying borrowers
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Email", "Phone"}, 0);
        borrowersTable = new JTable(tableModel);
        loadBorrowers(); // Method to load borrowers from database
        JScrollPane tableScrollPane = new JScrollPane(borrowersTable);

        // Form fields
        JPanel formPanel = new JPanel(new GridLayout(3, 2));
        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);
        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);
        formPanel.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        formPanel.add(phoneField);

        // Action buttons
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Borrower");
        addButton.addActionListener(this::addBorrower); // Connect to addBorrower method
        buttonPanel.add(addButton);
        JButton updateButton = new JButton("Update Borrower");
        updateButton.addActionListener(this::updateBorrower); // Connect to updateBorrower method
        buttonPanel.add(updateButton);
        JButton deleteButton = new JButton("Delete Borrower");
        deleteButton.addActionListener(this::deleteBorrower); // Connect to deleteBorrower method
        buttonPanel.add(deleteButton);

        panel.add(tableScrollPane, BorderLayout.CENTER);
        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    // Method to load borrowers from the database
    private void loadBorrowers() {
        List<Borrower> borrowers = borrowerDAO.getAllBorrowers();
        tableModel.setRowCount(0); // Clear existing rows
        for (Borrower borrower : borrowers) {
            tableModel.addRow(new Object[]{
                borrower.getBorrowerID(),
                borrower.getName(),
                borrower.getEmail(),
                borrower.getPhone()
            });
        }
    }

    // Method to clear form fields
    private void clearFields() {
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
    }

    // Add Borrower Method
    private void addBorrower(ActionEvent event) {
        // Collect data from input fields
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();

        // Create a new Borrower object and add it to the database
        Borrower borrower = new Borrower(name, email, phone);
        borrowerDAO.addBorrower(borrower);

        // Reload the table data and clear input fields
        loadBorrowers();
        clearFields();
    }

    // Update Borrower Method
    private void updateBorrower(ActionEvent event) {
        int selectedRow = borrowersTable.getSelectedRow();
        if (selectedRow >= 0) {
            int borrowerID = (int) tableModel.getValueAt(selectedRow, 0);
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();

            // Update the Borrower object and save changes to the database
            Borrower borrower = new Borrower();
            borrower.setBorrowerID(borrowerID);
            borrower.setName(name);
            borrower.setEmail(email);
            borrower.setPhone(phone);

            borrowerDAO.updateBorrower(borrower);
            loadBorrowers();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Select a borrower to update");
        }
    }

    // Delete Borrower Method
    private void deleteBorrower(ActionEvent event) {
        int selectedRow = borrowersTable.getSelectedRow();
        if (selectedRow >= 0) {
            int borrowerID = (int) tableModel.getValueAt(selectedRow, 0);
            borrowerDAO.deleteBorrower(borrowerID);
            loadBorrowers();
        } else {
            JOptionPane.showMessageDialog(this, "Select a borrower to delete");
        }
    }
}
