package com.example.DataBase;

import java.awt.image.BufferedImage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBmodify {
    
    public static boolean modifyUser(int ID,String username, String password, BufferedImage image, String email,
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
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean modifyAdmin(int ID,String username, String password, BufferedImage image, String email,
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
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static void modifyProduct(int ID,String name, String description, BufferedImage productPicture, Double price,int quantity){
        PreparedStatement statement = null;
        String sqlQuery = "UPDATE Products SET name = ?,description = ?,image = ?,date = datetime('now'),price = ?,quantity = ? WHERE ID = ?;";
        try {
            statement = DataBase.getConnection().prepareStatement(sqlQuery);
            statement.setString(1, name);
            statement.setString(2, description);
            statement.setBytes(3, ImageProcessing.convertImgtoBytes(productPicture));
            statement.setDouble(4, price);
            statement.setInt(5, quantity);
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

    public static void substractQuantity(int ID,int quantity){
        PreparedStatement statement = null;
        String sqlQuery = "SELECT * FROM Products WHERE ID = ?;"; 
        try {
            statement = DataBase.getConnection().prepareStatement(sqlQuery);
            statement.setInt(1, ID);
            ResultSet rs = statement.executeQuery();

            int pID = rs.getInt("ID");
            String pName = rs.getString("name");
            String pDescription = rs.getString("description");
            BufferedImage pPicture = ImageProcessing.convertToBufferedImage(rs.getBytes("image"));
            Double pPrice = rs.getDouble("price");

            rs.close();
            statement.close();
            modifyProduct(pID, pName, pDescription, pPicture, pPrice, quantity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
