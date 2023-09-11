package implementation;
import dto.borrowed_books;

import  dto.status;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import helper.DatabaseConnection;

import dto.book;
import interfeces.Ibook;
import helper.DatabaseConnection;
import interfeces.Iborrower;


import javax.swing.*;

import java.util.Date;
public class bookImp implements Ibook {


    public bookImp() {
    }

    //good practice to use private connection
    private Connection conx = DatabaseConnection.getConn();

    @Override
    public book show(book b) {
        String query = "SELECT * FROM books";

        try (PreparedStatement statement = conx.prepareStatement(query)) {
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
        String query = "INSERT INTO books (title, author, isbn) VALUES (?, ?, ?)";

        try (PreparedStatement statement = conx.prepareStatement(query)) {
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
        String query = "UPDATE books SET title = ?, author = ?, status = ? WHERE isbn = ?";
        try (PreparedStatement statement = conx.prepareStatement(query)) {
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
        String query = "DELETE FROM books WHERE isbn = ?";
        try (PreparedStatement statement = conx.prepareStatement(query)) {
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

        return null;
    }

    @Override
    public book search(String title) {
        String query = "SELECT * FROM books WHERE title LIKE ?";
        try (PreparedStatement statement = conx.prepareStatement(query)) {
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
        String query = "SELECT * FROM books WHERE isbn = ?";
        try (PreparedStatement statement = conx.prepareStatement(query)) {
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
        String name = JOptionPane.showInputDialog("Insert the name of the borrower for borrow");
        //call the searchName method to get the id of the borrower
        Iborrower IMp = new borrowerImp();
        int foundBorrower1= IMp.search(name);
        //insert into borrowed_books table
        //call the borrow method from borrowed_booksImp
        borrowed_booksImp borrowed_booksImp = new borrowed_booksImp();
        //set the date to the current date
        java.sql.Date date = new java.sql.Date(new Date().getTime());
        borrowed_booksImp.borrow(isbn,foundBorrower1, date );



        return null;
    }


    @Override
    public book returnBook(int isbn) {

        //insert into borrowed_books table
        //call the borrow method from borrowed_booksImp
        borrowed_booksImp borrowed_booksImp = new borrowed_booksImp();
        //set the date to the current date
        java.sql.Date date = new java.sql.Date(new Date().getTime());
        borrowed_booksImp.returned(isbn, date );



return null;
    }


// all down need updates
    @Override
    public book statistics() {
        System.out.println("Statistics:");
        System.out.println("list of books borrowed: ");
        //call the showBorrowed method
        showBorrowed();
      System.out.println("list of books available: ");
        //call the showAvailable method
        showAvailable();
        System.out.println("list of books lost: ");
        //call the showLost method
        showLost();
        return null;
    }


    public  book showBorrowed(){

        String query = "SELECT * FROM books WHERE status = 'borrowed'";
        try (PreparedStatement statement = conx.prepareStatement(query)) {
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
        return null;


    }
    public  book showAvailable(){

        String query = "SELECT * FROM books WHERE status = 'available'";
        try (PreparedStatement statement = conx.prepareStatement(query)) {
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
        return null;


    }
    public  book showLost(){

        String query = "SELECT * FROM books WHERE status = 'lost'";
        try (PreparedStatement statement = conx.prepareStatement(query)) {
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
        return null;


    }
    @Override
    public boolean isBorrowed(int isbn) {
        String query = "SELECT * FROM books WHERE isbn = ? AND status = 'borrowed'";
        try (PreparedStatement statement = conx.prepareStatement(query)) {
            statement.setInt(1, isbn);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    @Override
    public book updateStatus(int isbn) {
        String query = "UPDATE books SET status = 'available' WHERE isbn = ?";
        try (PreparedStatement statement = conx.prepareStatement(query)) {
            statement.setInt(1, isbn);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Book status updated successfully.");
                System.out.println("Book status updated successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update the book status.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public book lostStatus(int isbn) {
        String query = "UPDATE books SET status = 'lost' WHERE isbn = ?";
        try (PreparedStatement statement = conx.prepareStatement(query)) {
            statement.setInt(1, isbn);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Book status updated successfully.");
                System.out.println("Book status updated successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update the book status.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    }
