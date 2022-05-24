package com.example.DataBase;

import java.awt.image.BufferedImage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import com.example.Beans.Commande;
import com.example.Beans.Fournisseur;
import com.example.Beans.Product;
import com.example.Beans.Accounts.Admin;
import com.example.Beans.Accounts.User;

public class DBget {
    public static User getUser(int ID) {
        User tmp = null;
        Statement commandeStatement = null;
        ResultSet resultSet = null;
        String commandeQuery = "SELECT * FROM Users";
        try {
            commandeStatement = DataBase.getConnection().createStatement();
            resultSet = commandeStatement.executeQuery(commandeQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (resultSet.next()) {
                if (resultSet.getInt("ID") == ID && resultSet.getBoolean("isAdmin") == false) {
                    BufferedImage image = ImageProcessing.convertToBufferedImage(resultSet.getBytes("image"));
                    tmp = new User(resultSet.getInt("ID"), resultSet.getString("username"),
                            resultSet.getString("password"), resultSet.getString("date"), image,
                            resultSet.getString("email"), resultSet.getString("phoneNumber"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public static Admin getAdmin(int ID) {
        Admin tmp = null;
        Statement commandeStatement = null;
        ResultSet resultSet = null;
        String commandeQuery = "SELECT * FROM Users";
        try {
            commandeStatement = DataBase.getConnection().createStatement();
            resultSet = commandeStatement.executeQuery(commandeQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (resultSet.next()) {
                if (resultSet.getInt("ID") == ID && resultSet.getInt("isAdmin") == 1) {
                    BufferedImage image = ImageProcessing.convertToBufferedImage(resultSet.getBytes("image"));
                    tmp = new Admin(resultSet.getInt("ID"), resultSet.getString("username"),
                            resultSet.getString("password"), resultSet.getString("date"), image,
                            resultSet.getString("email"), resultSet.getString("phoneNumber"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public static Commande getCommande(int ID) {
        Commande tmp = null;
        LinkedList<Product> products = new LinkedList<Product>();
        PreparedStatement commandeStatement = null;
        PreparedStatement productsStatement = null;
        ResultSet resultSetCommande = null;
        ResultSet resultSetProducts = null;

        String commandeQuery = "SELECT * FROM Commandes WHERE ID = ?;";
        String productsQuery = "SELECT * FROM CommandeProducts WHERE CommandeID = ?;";
        try {
            commandeStatement = DataBase.getConnection().prepareStatement(commandeQuery);
            commandeStatement.setInt(1, ID);

            productsStatement = DataBase.getConnection().prepareStatement(productsQuery);
            productsStatement.setInt(1, ID);

            resultSetCommande = commandeStatement.executeQuery();
            resultSetProducts = productsStatement.executeQuery();
            
            double totalPrice = 0;
            
            if (resultSetCommande.next()) {
                while (resultSetProducts.next()) {
                    products.add(getProduct(resultSetProducts.getInt("ProductID")));
                    totalPrice += getProduct(resultSetProducts.getInt("ProductID")).getPrice();
                }

                tmp = new Commande(resultSetCommande.getInt("ID"), products,
                        getUser(resultSetCommande.getInt("UserID")), resultSetCommande.getString("date"),
                        totalPrice,resultSetCommande.getBoolean("isAccepted"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public static Fournisseur getFournisseur(int ID) {
        Fournisseur tmp = null;
        Statement commandeStatement = null;
        ResultSet resultSet = null;
        LinkedList<Product> products = new LinkedList<Product>();
        String commandeQuery = "SELECT * FROM Fournisseurs;";
        String sqlPFQuery = "SELECT * FROM FournisseurProducts;";
        try {
            commandeStatement = DataBase.getConnection().createStatement();
            resultSet = commandeStatement.executeQuery(sqlPFQuery);
            while (resultSet.next()) {
                if (resultSet.getInt("FournisseurID") == ID) {
                    products.add(getProduct(resultSet.getInt("ProductID")));
                }
            }
            resultSet = commandeStatement.executeQuery(commandeQuery);
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
        PreparedStatement commandeStatement = null;
        ResultSet resultSet = null;
        String commandeQuery = "SELECT * FROM Products WHERE ID = ?;";
        try {
            commandeStatement = DataBase.getConnection().prepareStatement(commandeQuery);
            commandeStatement.setInt(1, ID);
            resultSet = commandeStatement.executeQuery();
            if (resultSet.getInt("ID") == ID) {
                BufferedImage image = ImageProcessing.convertToBufferedImage(resultSet.getBytes("image"));
                tmp = new Product(resultSet.getInt("ID"), resultSet.getString("name"),
                        resultSet.getString("description"), resultSet.getString("date"), image,
                        resultSet.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public static LinkedList<User> getAllUsers() {
        LinkedList<User> users = new LinkedList<User>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sqlQuery = "SELECT ID,isAdmin FROM Users;";
        try {
            statement = DataBase.getConnection().prepareStatement(sqlQuery);
            rs = statement.executeQuery();
            while (rs.next()) {
                if (rs.getInt("isAdmin") == 0) {
                    users.add(getUser(rs.getInt("ID")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static LinkedList<Admin> getAllAdmins() {
        LinkedList<Admin> users = new LinkedList<Admin>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sqlQuery = "SELECT ID,isAdmin FROM Users;";
        try {
            statement = DataBase.getConnection().prepareStatement(sqlQuery);
            rs = statement.executeQuery();
            while (rs.next()) {
                if (rs.getInt("isAdmin") == 1) {
                    users.add(getAdmin(rs.getInt("ID")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static LinkedList<Product> getAllProducts(){
        LinkedList<Product> products = new LinkedList<Product>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sqlQuery = "SELECT ID FROM Products;";
        try {
            statement = DataBase.getConnection().prepareStatement(sqlQuery);
            rs = statement.executeQuery();
            while (rs.next()) {
                products.add(getProduct(rs.getInt("ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static LinkedList<Commande> getAllCommandes(){
        LinkedList<Commande> commandes = new LinkedList<Commande>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sqlQuery = "SELECT ID FROM Commandes;";
        try {
            statement = DataBase.getConnection().prepareStatement(sqlQuery);
            rs = statement.executeQuery();
            while (rs.next()) {
                commandes.add(getCommande(rs.getInt("ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandes;
    }

    public static LinkedList<Commande> getAllUserCommandes(int UserID){
        LinkedList<Commande> commandes = new LinkedList<Commande>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sqlQuery = "SELECT ID FROM Commandes WHERE UserID = ?;";
        try {
            statement = DataBase.getConnection().prepareStatement(sqlQuery);
            statement.setInt(1,UserID);
            rs = statement.executeQuery();
            while (rs.next()) {
                commandes.add(getCommande(rs.getInt("ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandes;
    }

    public static LinkedList<Product> getAllCommandeProducts(int CommandeID){
        LinkedList<Product> products = new LinkedList<Product>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sqlQuery = "SELECT ProductID FROM CommandeProducts WHERE CommandeID = ?;";
        try {
            statement = DataBase.getConnection().prepareStatement(sqlQuery);
            statement.setInt(1,CommandeID);
            rs = statement.executeQuery();
            while (rs.next()) {
                products.add(getProduct(rs.getInt("ProductID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }	

    public static int getUserCount() {
        int nmbr = 0;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sqlQuery = "SELECT COUNT(*) FROM Users WHERE isAdmin = 0;";
        try {
            statement = DataBase.getConnection().prepareStatement(sqlQuery);
            rs = statement.executeQuery();
            nmbr = rs.getInt("COUNT(*)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nmbr;
    }

    public static int getAdminCount() {
        int nmbr = 0;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sqlQuery = "SELECT COUNT(*) FROM Users WHERE isAdmin = 1;";
        try {
            statement = DataBase.getConnection().prepareStatement(sqlQuery);
            rs = statement.executeQuery();
            nmbr = rs.getInt("COUNT(*)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nmbr;
    }

    public static int getProductCount() {
        int nmbr = 0;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sqlQuery = "SELECT COUNT(*) FROM Products";
        try {
            statement = DataBase.getConnection().prepareStatement(sqlQuery);
            rs = statement.executeQuery();
            nmbr = rs.getInt("COUNT(*)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nmbr;
    }

    public static int getProductCount(int fournisseurID) {
        int nmbr = 0;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sqlQuery = "SELECT COUNT(*) FROM FournisseurProducts WHERE FournisseurID = ?";
        try {
            statement = DataBase.getConnection().prepareStatement(sqlQuery);
            statement.setInt(1, fournisseurID);
            rs = statement.executeQuery();
            nmbr = rs.getInt("COUNT(*)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nmbr;
    }

    public static int getFournisseurCount() {
        int nmbr = 0;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sqlQuery = "SELECT COUNT(*) FROM Fournisseurs";
        try {
            statement = DataBase.getConnection().prepareStatement(sqlQuery);
            rs = statement.executeQuery();
            nmbr = rs.getInt("COUNT(*)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nmbr;
    }

    public static int getCommandeCount() {
        int nmbr = 0;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sqlQuery = "SELECT COUNT(*) FROM Commandes";
        try {
            statement = DataBase.getConnection().prepareStatement(sqlQuery);
            rs = statement.executeQuery();
            nmbr = rs.getInt("COUNT(*)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nmbr;
    }

    public static int getCommandeCount(int userID) {
        int nmbr = 0;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sqlQuery = "SELECT COUNT(*) FROM Commandes WHERE userID = ?";
        try {
            statement = DataBase.getConnection().prepareStatement(sqlQuery);
            statement.setInt(1, userID);
            rs = statement.executeQuery();
            nmbr = rs.getInt("COUNT(*)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nmbr;
    }
}
