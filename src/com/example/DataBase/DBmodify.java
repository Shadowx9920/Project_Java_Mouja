package com.example.DataBase;

import java.awt.image.BufferedImage;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBmodify {
    public static void modifyUser(int ID,String username, String password, BufferedImage image, String email,
    String phoneNumber){
        PreparedStatement statement = null;
        String sqlQuery = "UPDATE Users SET username = ?,password = ?,isAdmin = ?, image = ?,date = datetime('now'), email = ?, phoneNumber = ? WHERE ID = ?;";

        try {
            statement = DataBase.getConnection().prepareStatement(sqlQuery);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setBoolean(3, false);
            statement.setBytes(4, ImageProcessing.convertImgtoBytes(image));
            statement.setString(5, email);
            statement.setString(6, phoneNumber);
            statement.setInt(7, ID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void modifyAdmin(int ID,String username, String password, BufferedImage image, String email,
    String phoneNumber){
        PreparedStatement statement = null;
        String sqlQuery = "UPDATE Users SET username = ?,password = ?,isAdmin = ?, image = ?,date = datetime('now'), email = ?, phoneNumber = ? WHERE ID = ?;";

        try {
            statement = DataBase.getConnection().prepareStatement(sqlQuery);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setBoolean(3, true);
            statement.setBytes(4, ImageProcessing.convertImgtoBytes(image));
            statement.setString(5, email);
            statement.setString(6, phoneNumber);
            statement.setInt(7, ID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void modifyProduct(int ID,String name, String description, BufferedImage productPicture, Double price){
        PreparedStatement statement = null;
        String sqlQuery = "UPDATE Products SET name = ?,description = ?,image = ?,date = datetime('now'),price = ? WHERE ID = ?;";
        try {
            statement = DataBase.getConnection().prepareStatement(sqlQuery);
            statement.setString(1, name);
            statement.setString(2, description);
            statement.setBytes(3, ImageProcessing.convertImgtoBytes(productPicture));
            statement.setDouble(4, price);
            statement.setInt(6, ID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void modifyFournisseur(int ID,String name){
        PreparedStatement statement = null;
        String sqlQuery = "UPDATE Fournisseurs SET name = ? WHERE ID = ?;";
        try {
            statement = DataBase.getConnection().prepareStatement(sqlQuery);
            statement.setString(1, name);
            statement.setInt(2, ID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
