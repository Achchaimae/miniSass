package implementation;

import dto.book;
import helper.DatabaseConnection;
import interfeces.Iborrower;
import interfeces.Iborrowed;


import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.sql.Connection;

public class borrowed_booksImp {
    private Connection conx = DatabaseConnection.getConn();
    public book borrow(int ISBN, int id, java.util.Date date_borrowed) {
        book b  = book.getInstance();
        bookImp bookImp = new bookImp();
        //condition if the book is borrowed or not
        if (bookImp.isBorrowed(ISBN)) {
            JOptionPane.showMessageDialog(null, "Book is already borrowed");
            return null;
        }
        else {
            // insert the borrowed book into the borrowed_books table
            String query = "INSERT INTO borrowed_books (book_id, borrower_id, borrow_date) VALUES (?, ?, ?)";
            // set a trigger to update the status of the book to borrowed
            try (PreparedStatement statement = conx.prepareStatement(query)) {
                statement.setInt(1, ISBN);  // Set book_id instead of ISBN
                statement.setInt(2, id);  // Set borrower_id instead of id
                statement.setDate(3, new java.sql.Date(date_borrowed.getTime()));
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Book borrowed successfully");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        }
    }

    public book returned(int ISBN ,java.util.Date date_borrowed ){
        // update the borrowed book in the borrowed_books table
        book b = book.getInstance();
        bookImp bookImp = new bookImp();
        bookImp.updateStatus(ISBN);
        String query = "UPDATE borrowed_books SET return_date = ? WHERE book_id = ?";
        //update the status of the book

        try (PreparedStatement statement = conx.prepareStatement(query)) {
            statement.setDate(1, new java.sql.Date(date_borrowed.getTime()));
            statement.setInt(2, ISBN);  // Set book_id instead of ISBN
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Book returned successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


}

