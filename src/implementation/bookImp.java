package implementation;

import  dto.status;


import java.sql.*;
import helper.DatabaseConnection;

import dto.book;
import interfeces.Ibook;
import helper.DatabaseConnection;


import javax.swing.*;

public class bookImp implements Ibook {


    public bookImp() {
    }


    @Override
    public book show(book b) {
        java.sql.Connection connection = DatabaseConnection.getConn();
        String query = "SELECT * FROM books";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                System.out.println("Books:");
                System.out.println("Title: " + rs.getString("title"));
                System.out.println("Author: " + rs.getString("author"));
                System.out.println("ISBN: " + rs.getInt("ISBN"));
                System.out.println("Status: " + rs.getString("status"));
                System.out.println("**************************");


            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to retrieve books.");
        }
    return b;
    }


    @Override
    public book add(book book) {
        java.sql.Connection connection = DatabaseConnection.getConn();
        String query = "INSERT INTO books (title, author, isbn) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getISBN());
            //statement.setObject(4, book.getStatus(), Types.OTHER); // Set the enum value

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Book added successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add the book.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to add the book.");
        }

        return book;
    }


    @Override
    public book update(book b) {
        java.sql.Connection connection = DatabaseConnection.getConn();
        String query = "UPDATE books SET title = ?, author = ?, status = ? WHERE isbn = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, b.getTitle());
            statement.setString(2, b.getAuthor());
            statement.setObject(3, b.getStatus(), Types.OTHER); // Set the enum value
            statement.setInt(4, b.getISBN());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Book updated successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update the book.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return b;
    }

    @Override
    public book delete(int bookId) {
        java.sql.Connection connection = DatabaseConnection.getConn();
        String query = "DELETE FROM books WHERE isbn = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, bookId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Book deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete the book.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null; // You might want to reconsider the return type or implementation for the delete operation
    }

    @Override
    public book search(String title) {
        java.sql.Connection connection = DatabaseConnection.getConn();

        String query = "SELECT * FROM books WHERE title LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            String likeTerm = "%" + title + "%";
            statement.setString(1, likeTerm);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String author = rs.getString("author");
                int isbn = rs.getInt("isbn");
                String statusString = rs.getString("status");
                dto.status status = dto.status.valueOf(statusString); // Convert the string to dto.status enum
                book foundBook = book.getInstance();
                foundBook.setISBN(isbn);
                foundBook.setTitle(title);
                foundBook.setAuthor(author);
                foundBook.setStatus(status);

                // Display the book data
                System.out.println("Book found:");
                System.out.println("Title: " + foundBook.getTitle());
                System.out.println("Author: " + foundBook.getAuthor());
                System.out.println("ISBN: " + foundBook.getISBN());
                System.out.println("Status: " + foundBook.getStatus());

                return foundBook;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("No matching book found");
        return null;
    }

    @Override
    public book searchByIsbn(int ISBN) {
        System.out.println("Searching for book with ISBN " + ISBN);
        java.sql.Connection connection = DatabaseConnection.getConn();
        String query = "SELECT * FROM books WHERE isbn = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, ISBN);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("title"));
                JOptionPane.showMessageDialog(null, "The book is " + rs.getString("title"));

                // Create and return a book object
                book foundBook = book.getInstance();
                foundBook.setISBN(ISBN);
                foundBook.setTitle(rs.getString("title"));
                foundBook.setAuthor(rs.getString("author"));
                foundBook.setStatus(dto.status.valueOf(rs.getString("status")));
                return foundBook;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // No matching book found, return null
        return null;
    }

    @Override
    public book borrow(int isbn) {
        System.out.println("Borrowing book with ISBN " + isbn);
        return null;
    }

    @Override
    public book returnBook(int isbn) {
        return null;
    }


}
