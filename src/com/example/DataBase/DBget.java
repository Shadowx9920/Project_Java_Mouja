package com.example.DataBase;

import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import com.example.Commande;
import com.example.Fournisseur;
import com.example.Product;
import com.example.Accounts.Admin;
import com.example.Accounts.User;

public class DBget {
    public static User getUser(int ID) {
        User tmp = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sqlQuery = "SELECT * FROM Users";
        try {
            statement = DataBase.getConnection().createStatement();
            resultSet = statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (resultSet.next()) {
                if (resultSet.getInt("ID") == ID && resultSet.getBoolean("isAdmin") == false) {
                    BufferedImage image = ImageProcessing.convertToBufferedImage(resultSet.getBytes("image"));
                    tmp = new User(resultSet.getInt("ID"), resultSet.getString("username"),
                            resultSet.getString("password"), resultSet.getString("date"), image);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public static Admin getAdmin(int ID) {
        Admin tmp = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sqlQuery = "SELECT * FROM Users";
        try {
            statement = DataBase.getConnection().createStatement();
            resultSet = statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (resultSet.next()) {
                if (resultSet.getInt("ID") == ID && resultSet.getInt("isAdmin") == 1) {
                    BufferedImage image = ImageProcessing.convertToBufferedImage(resultSet.getBytes("image"));
                    tmp = new Admin(resultSet.getInt("ID"), resultSet.getString("username"),
                            resultSet.getString("password"), resultSet.getString("date"), image);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public static Commande getCommande(int ID) {
        Commande tmp = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sqlQuery = "SELECT * FROM Commandes";
        try {
            statement = DataBase.getConnection().createStatement();
            resultSet = statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (resultSet.next()) {
                if (resultSet.getInt("ID") == ID) {
                    tmp = new Commande(resultSet.getInt("ID"), getProduct(resultSet.getInt("ProductID")),
                            getUser(resultSet.getInt("UserID")), resultSet.getString("date"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public static Fournisseur getFournisseur(int ID) {
        Fournisseur tmp = null;
        Statement statement = null;
        ResultSet resultSet = null;
        LinkedList<Product> products = new LinkedList<Product>();
        String sqlQuery = "SELECT * FROM Fournisseurs;";
        String sqlPFQuery = "SELECT * FROM FournisseurProducts;";
        try {
            statement = DataBase.getConnection().createStatement();
            resultSet = statement.executeQuery(sqlPFQuery);
            while (resultSet.next()) {
                if (resultSet.getInt("FournisseurID") == ID) {
                    products.add(getProduct(resultSet.getInt("ProductID")));
                }
            }
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                if (resultSet.getInt("ID") == ID) {
                    tmp = new Fournisseur(resultSet.getInt("ID"), resultSet.getString("name"),
                            resultSet.getString("date"), products);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public static Product getProduct(int ID) {
        Product tmp = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sqlQuery = "SELECT * FROM Products";
        try {
            statement = DataBase.getConnection().createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                if (resultSet.getInt("ID") == ID) {
                    BufferedImage image = ImageProcessing.convertToBufferedImage(resultSet.getBytes("image"));
                    tmp = new Product(resultSet.getInt("ID"), resultSet.getString("name"),
                            resultSet.getString("description"), image, resultSet.getString("date"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp;
    }
}
