package com.example.Beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import com.example.DataBase.DBget;
import com.example.DataBase.DataBase;

public class Fournisseur {
    public  int id;
    public String name;
    public String date; 
    public LinkedList<Product> products;

    public Fournisseur(int id,String name,String date,LinkedList<Product> products) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LinkedList<Product> getProducts() {
        return products;
    }

    public void setProducts(LinkedList<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Fournisseur [date=" + date + ", id=" + id + ", name=" + name + ", products=" + products + "]";
    }
    
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
}
