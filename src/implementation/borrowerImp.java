package implementation;
import dto.borrower;
import interfeces.Iborrower;

import  dto.status;


import java.sql.*;
import helper.DatabaseConnection;



import helper.DatabaseConnection;

import javax.swing.*;
public class borrowerImp implements Iborrower {

    public  borrowerImp(){}


    @Override
    public borrower show(borrower b) {
        return null;
    }

    @Override
    public borrower add(borrower b) {
        java.sql.Connection connection = DatabaseConnection.getConn();
        String query = "INSERT INTO borrower (name, email, phone , age ) VALUES (?, ?, ? , ? )";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, b.getName());
            statement.setString(2, b.getEmail());
            statement.setString(3, b.getPhone());
            statement.setInt(4, b.getAge());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Borrower added successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add borrower.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to add borrower.");

        }
return b;
    }

    @Override
    public borrower update(borrower b) {
        java.sql.Connection connection = DatabaseConnection.getConn();
        String query = "UPDATE borrower SET name = ?, email = ?, phone = ?, age = ? WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, b.getName());
            statement.setString(2, b.getEmail());
            statement.setString(3, b.getPhone());
            statement.setInt(4, b.getAge());
            statement.setInt(5, b.getId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Borrower updated successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update borrower.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to update borrower.");

        }
        return b;
    }

    @Override
    public borrower delete(int id) {
java.sql.Connection connection = DatabaseConnection.getConn();
        String query = "DELETE FROM borrower WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Borrower deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete borrower.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to delete borrower.");

        }
        return null;

    }

    @Override
    public int search(String name) {
        java.sql.Connection connection = DatabaseConnection.getConn();
        String query = "SELECT * FROM borrower WHERE name = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                borrower b = borrower.getInstance();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setEmail(rs.getString("email"));
                b.setPhone(rs.getString("phone"));
                b.setAge(rs.getInt("age"));
                return b.getId();
            } else {
                JOptionPane.showMessageDialog(null, "Borrower not found.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }




}
