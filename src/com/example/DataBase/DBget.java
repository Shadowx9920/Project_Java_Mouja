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

    public static LinkedList<Product> getCommandeProducts(int commandeID){
        LinkedList<Product> products = new LinkedList<Product>();
        Statement commandeStatement = null;
        ResultSet resultSet = null;
        String commandeQuery = "SELECT * FROM CommandeProducts WHERE CommandeID = " + commandeID+";";
        try {
            commandeStatement = DataBase.getConnection().createStatement();
            resultSet = commandeStatement.executeQuery(commandeQuery);
            while (resultSet.next()) {
                products.add(getProduct(resultSet.getInt("ProductID")));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
    
    public static Commande getCommande(int ID) {
        Commande tmp = null;
        PreparedStatement commandeStatement = null;
        ResultSet resultSetCommande = null;
        String commandeQuery = "SELECT * FROM Commandes WHERE ID = ?;";
        try {
            commandeStatement = DataBase.getConnection().prepareStatement(commandeQuery);
            commandeStatement.setInt(1, ID);
            resultSetCommande = commandeStatement.executeQuery();
            tmp = new Commande(resultSetCommande.getInt("ID"), null,
                getUser(resultSetCommande.getInt("UserID")), resultSetCommande.getString("date"),
                0,resultSetCommande.getBoolean("isAccepted"));
            resultSetCommande.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LinkedList<Product> products = getCommandeProducts(ID);
        double totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }
        tmp.setProducts(products);
        tmp.setTotalPrice(totalPrice);
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
    
    public static Fournisseur getFournisseurByProduct(int productID){
        Fournisseur tmp = null;
        Statement Statement = null;
        ResultSet resultSet = null;

        String sqlPFQuery = "SELECT * FROM FournisseurProducts;";
        try {
            Statement = DataBase.getConnection().createStatement();
            resultSet = Statement.executeQuery(sqlPFQuery);
            while (resultSet.next()) {
                if (resultSet.getInt("ProductID") == productID) {
                    tmp = getFournisseur(resultSet.getInt("FournisseurID"));
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
        ResultSet resultSet;

        String productQuery = "SELECT * FROM Products WHERE ID = ?;";
        
        try {
            commandeStatement = DataBase.getConnection().prepareStatement(productQuery);
            commandeStatement.setInt(1, ID);

            resultSet = commandeStatement.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getInt("ID") == ID) {
                    int productID = resultSet.getInt("ID");
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    double price = resultSet.getDouble("price");
                    int quantity = resultSet.getInt("quantity");
                    String date = resultSet.getString("date");
                    BufferedImage image = ImageProcessing.convertToBufferedImage(resultSet.getBytes("image"));
                    tmp = new Product(productID, name,description, date, image,price,quantity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public static LinkedList<Fournisseur> getAllFournisseurs(){
        LinkedList<Fournisseur> tmp = new LinkedList<Fournisseur>();
        Statement commandeStatement = null;
        ResultSet resultSet = null;
        String commandeQuery = "SELECT * FROM Fournisseurs";
        try {
            commandeStatement = DataBase.getConnection().createStatement();
            resultSet = commandeStatement.executeQuery(commandeQuery);
            while (resultSet.next()) {
                tmp.add(getFournisseur(resultSet.getInt("ID")));
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
        String sqlQuery = "SELECT * FROM CommandeProducts WHERE CommandeID = ?;";
        try {
            statement = DataBase.getConnection().prepareStatement(sqlQuery);
            statement.setInt(1,CommandeID);
            rs = statement.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                System.out.println(productID);
                products.add(getProduct(productID));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Product product : products) {
            System.out.println("ProductID =" + product.getId());
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
