package com.example.GUI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.image.BufferedImage;

import com.example.Beans.Commande;
import com.example.Beans.Fournisseur;
import com.example.Beans.Product;
import com.example.Beans.Accounts.Admin;
import com.example.Beans.Accounts.User;
import com.example.DataBase.DBget;
import com.example.DataBase.DBset;
import com.example.DataBase.DataBase;

public class DBmanagement {

    public static int searchForUsers(String login, String password) {
        int ID = -1;
        Statement statement = null;
        ResultSet resultSet = null;
        String sqlQuery = "SELECT ID,username,password FROM Users";
        try {
            statement = DataBase.getConnection().createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                if (login.equals(resultSet.getString("username"))) {
                    if (password.equals(resultSet.getString("password"))) {
                        ID = resultSet.getInt("ID");
                    } else {
                        ID = -2;
                        System.out.println("Incorrect password");
                    }
                    break;
                }
            }
            if (ID == -1) {
                System.out.println("User not Found");
            } else {
                System.out.println("User Found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ID;
    }

    public static boolean checkIfAdmin(int ID) {
        Statement statement = null;
        ResultSet resultSet = null;
        String sqlQuery = "SELECT ID,isAdmin FROM Users";
        try {
            statement = DataBase.getConnection().createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                if (resultSet.getInt("isAdmin") == 1 && resultSet.getInt("ID") == ID) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean signUp(String login, String password, BufferedImage image, String email, String phoneNumber) {
        Statement statement = null;
        ResultSet resultSet = null;
        String sqlQuery = "SELECT username FROM Users";
        try {
            statement = DataBase.getConnection().createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                if (resultSet.getString("username").equals(login)) {
                    System.out.println(resultSet.getString("username"));
                    System.out.println("User Already Exists");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBset.addUser(login, password, false, image, email, phoneNumber);
        System.out.println("User created");
        return true;
    }

    // public static LinkedList<Product> selectProducts() {
    //     LinkedList<Product> products = new LinkedList<Product>();
    //     Statement statement = null;
    //     ResultSet resultSet = null;
    //     String query = "SELECT ID FROM Products;";
    //     try {
    //         statement = DataBase.getConnection().createStatement();
    //         resultSet = statement.executeQuery(query);
    //         while (resultSet.next()) {
    //             products.add(DBget.getProduct(resultSet.getInt("ID")));
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return products;
    // }

    public static Fournisseur getFournisseur(String name){
        Fournisseur f = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT ID FROM Fournisseurs WHERE name = '"+name+"';";
        try {
            statement = DataBase.getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                f = DBget.getFournisseur(resultSet.getInt("ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }

    public static Boolean searchForFournisseur(String name){
        Statement statement = null;
        ResultSet resultSet = null;
        String sqlQuery = "SELECT name FROM Fournisseurs";
        try {
            statement = DataBase.getConnection().createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                if (resultSet.getString("name").equals(name)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void addProduct(String FournisseurName, String Productname, String description, BufferedImage image, Double price){
        if (searchForFournisseur(FournisseurName)) {
            Fournisseur tmp = getFournisseur(FournisseurName);
            DBset.addProduct(tmp.id, Productname, description, image, price);
        }else{
            DBset.addFournisseur(FournisseurName);
            Fournisseur tmp = getFournisseur(FournisseurName);
            DBset.addProduct(tmp.id, Productname, description, image, price);
        }
    }

    public static void removeProductByID(int Id) {
        DBset.delProduct(Id);
    }

    public static void removeUserByID(int Id) {
        DBset.delUser(Id);
    }

    public static void createNewCommande(LinkedList<Product> products) {
        if(!CurrentSession.checkIfLogged()){
            return;
        }
        if (CurrentSession.checkIfAdmin()) {
            return;
        }
        double totalPrice = 0;
        for (Product product : products) {
            totalPrice = product.getPrice();
        }
        Commande c = DBset.addCommande(CurrentSession.getUser().getId(), totalPrice, true );
        DBset.addCommandeUser(c.getId(), CurrentSession.getUser().getId());
    }

    public static TableModel getUsersTableModel() {
        String[] columns = new String[] {
            "Id", "Name", "Password", "Email","PhoneNumber","Creation Date"
        };
        int userCount = DBget.getUserCount();
        LinkedList<User> admins = DBget.getAllUsers();
        Object[][] data = new Object[userCount][6];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < 6; j++) {
                switch (j) {
                    case 0:
                        data[i][j] = admins.get(i).getId();
                        break;
                    case 1:
                        data[i][j] = admins.get(i).getUsername();
                        break;
                    case 2:
                        data[i][j] = admins.get(i).getPassword();
                        break;
                    case 3:
                        data[i][j] = admins.get(i).getEmail();
                        break;
                    case 4:
                        data[i][j] = admins.get(i).getPhoneNumber();
                        break;
                    case 5:
                        data[i][j] = admins.get(i).getDate();
                        break;
                    default:
                        break;
                }
            }
        }
        TableModel model = new DefaultTableModel(data,columns);
        return model;
    }

    public static TableModel getAdminsTableModel() {
        String[] columns = new String[] {
            "Id", "Name", "Password", "Email","PhoneNumber","Creation Date"
        };
        int userCount = DBget.getAdminCount();
        LinkedList<Admin> admins = DBget.getAllAdmins();
        Object[][] data = new Object[userCount][6];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < 6; j++) {
                switch (j) {
                    case 0:
                        data[i][j] = admins.get(i).getId();
                        break;
                    case 1:
                        data[i][j] = admins.get(i).getUsername();
                        break;
                    case 2:
                        data[i][j] = admins.get(i).getPassword();
                        break;
                    case 3:
                        data[i][j] = admins.get(i).getEmail();
                        break;
                    case 4:
                        data[i][j] = admins.get(i).getPhoneNumber();
                        break;
                    case 5:
                        data[i][j] = admins.get(i).getDate();
                        break;
                    default:
                        break;
                }
            }
        }
        TableModel model = new DefaultTableModel(data,columns);
        return model;
    }

    public static TableModel getProductTableModel(){
        String[] columns = new String[] {
            "Id", "Name", "Price", "image","Creation Date"
        };
        int productCount = DBget.getProductCount();
        LinkedList<Product> products = DBget.getAllProducts();
        Object[][] data = new Object[productCount][6];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < 5; j++) {
                switch (j) {
                    case 0:
                        data[i][j] = products.get(i).getId();
                        break;
                    case 1:
                        data[i][j] = products.get(i).getName();
                        break;
                    case 2:
                        data[i][j] = products.get(i).getPrice();
                        break;
                    case 3:
                        data[i][j] = new ImageIcon(products.get(i).getProductPicture());
                        break;
                    case 4:
                        data[i][j] = products.get(i).getDate();
                        break;
                    default:
                        break;
                }
            }
        }
        TableModel model = new DefaultTableModel(data,columns);
        return model;
    }

    public static void removeSelectedUserRows(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int[] rows = table.getSelectedRows();
        for (int i = rows.length - 1; i >= 0; i--) {
            DBset.delUser(Integer.parseInt(table.getModel().getValueAt(i,0).toString()));
            model.removeRow(rows[i]);
        }
    }

    public static void removeSelectedProductRows(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int[] rows = table.getSelectedRows();
        for (int i = rows.length - 1; i >= 0; i--) {
            DBset.delProduct(Integer.parseInt(table.getModel().getValueAt(i,0).toString()));
            model.removeRow(rows[i]);
        }
    }
}
