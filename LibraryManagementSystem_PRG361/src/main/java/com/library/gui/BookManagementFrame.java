package com.library.gui;

import com.library.dao.BookDAO;
import com.library.entities.Book;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class BookManagementFrame extends JFrame {
    private JTable booksTable;
    private DefaultTableModel tableModel;
    private JTextField titleField, authorField, isbnField, yearField, copiesField;
    private BookDAO bookDAO;

    public BookManagementFrame() {
        bookDAO = new BookDAO();

        setTitle("Book Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Layout components
        JPanel panel = new JPanel(new BorderLayout());

        // Table for displaying books
        tableModel = new DefaultTableModel(new String[]{"ID", "Title", "Author ID", "ISBN", "Year", "Copies"}, 0);
        booksTable = new JTable(tableModel);
        loadBooks(); // Method to load books from database
        JScrollPane tableScrollPane = new JScrollPane(booksTable);

        // Form fields
        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        formPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        formPanel.add(titleField);
        formPanel.add(new JLabel("Author ID:"));
        authorField = new JTextField();
        formPanel.add(authorField);
        formPanel.add(new JLabel("ISBN:"));
        isbnField = new JTextField();
        formPanel.add(isbnField);
        formPanel.add(new JLabel("Published Year:"));
        yearField = new JTextField();
        formPanel.add(yearField);
        formPanel.add(new JLabel("Copies Available:"));
        copiesField = new JTextField();
        formPanel.add(copiesField);

        // Action buttons
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(this::addBook); // Connect to addBook method
        buttonPanel.add(addButton);
        JButton updateButton = new JButton("Update Book");
        updateButton.addActionListener(this::updateBook); // Connect to updateBook method
        buttonPanel.add(updateButton);
        JButton deleteButton = new JButton("Delete Book");
        deleteButton.addActionListener(this::deleteBook); // Connect to deleteBook method
        buttonPanel.add(deleteButton);

        panel.add(tableScrollPane, BorderLayout.CENTER);
        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    // Method to load books from the database
    private void loadBooks() {
        List<Book> books = bookDAO.getAllBooks();
        tableModel.setRowCount(0); // Clear existing rows
        for (Book book : books) {
            tableModel.addRow(new Object[]{
                book.getBookID(),
                book.getTitle(),
                book.getAuthorID(),
                book.getIsbn(),
                book.getPublishedYear(),
                book.getCopiesAvailable()
            });
        }
    }

    // Method to clear form fields
    private void clearFields() {
        titleField.setText("");
        authorField.setText("");
        isbnField.setText("");
        yearField.setText("");
        copiesField.setText("");
    }

    // Add Book Method
    private void addBook(ActionEvent event) {
        // Collect data from input fields
        String title = titleField.getText();
        int authorID = Integer.parseInt(authorField.getText());
        String isbn = isbnField.getText();
        int publishedYear = Integer.parseInt(yearField.getText());
        int copiesAvailable = Integer.parseInt(copiesField.getText());

        // Create a new Book object and add it to the database
        Book book = new Book(title, authorID, isbn, publishedYear, copiesAvailable);
        bookDAO.addBook(book);

        // Reload the table data and clear input fields
        loadBooks();
        clearFields();
    }

    // Update Book Method
    private void updateBook(ActionEvent event) {
        int selectedRow = booksTable.getSelectedRow();
        if (selectedRow >= 0) {
            int bookID = (int) tableModel.getValueAt(selectedRow, 0);
            String title = titleField.getText();
            int authorID = Integer.parseInt(authorField.getText());
            String isbn = isbnField.getText();
            int publishedYear = Integer.parseInt(yearField.getText());
            int copiesAvailable = Integer.parseInt(copiesField.getText());

            // Update the Book object and save changes to the database
            Book book = new Book();
            book.setBookID(bookID);
            book.setTitle(title);
            book.setAuthorID(authorID);
            book.setIsbn(isbn);
            book.setPublishedYear(publishedYear);
            book.setCopiesAvailable(copiesAvailable);

            bookDAO.updateBook(book);
            loadBooks();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Select a book to update");
        }
    }

    // Delete Book Method
    private void deleteBook(ActionEvent event) {
        int selectedRow = booksTable.getSelectedRow();
        if (selectedRow >= 0) {
            int bookID = (int) tableModel.getValueAt(selectedRow, 0);
            bookDAO.deleteBook(bookID);
            loadBooks();
        } else {
            JOptionPane.showMessageDialog(this, "Select a book to delete");
        }
    }
}
