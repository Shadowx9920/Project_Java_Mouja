package com.example.DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class IdManager {

    public static int generateNewID(Connection connection){
        Random r = new Random();
        int newID = 0;
        while (checkIfIDAlreadyExists(newID, connection)) {
            newID = r.nextInt(10000);
        }
        return  newID;
    }

    public static boolean checkIfIDAlreadyExists(int Id,Connection connection){
        Statement statement = null;
        ResultSet query = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String selectQuery = "select ID from USERS;";
        try {
            query = statement.executeQuery(selectQuery);
            while (query.next()) {
                if (query.getInt("ID") == Id) {
                    statement.close();
                    return true;
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        selectQuery = "select ID from Fournisseurs;";
        try {
            query = statement.executeQuery(selectQuery);
            while (query.next()) {
                if (query.getInt("ID") == Id) {
                    statement.close();
                    return true;
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        selectQuery = "select ID from Products;";
        try {
            query = statement.executeQuery(selectQuery);
            while (query.next()) {
                if (query.getInt("ID") == Id) {
                    statement.close();
                    return true;
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        selectQuery = "select ID from Commandes;";
        try {
            query = statement.executeQuery(selectQuery);
            while (query.next()) {
                if (query.getInt("ID") == Id) {
                    statement.close();
                    return true;
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
