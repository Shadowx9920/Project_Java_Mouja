package com.example.GUI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.awt.image.BufferedImage;

import com.example.Accounts.Admin;
import com.example.Accounts.User;
import com.example.DataBase.DBget;
import com.example.DataBase.DBset;
import com.example.DataBase.DataBase;

public class DBmanagement {
    public static boolean signIn(String login,String password){
        int ID = DBmanagement.searchForUsers(login,password);
                if(ID == -1) return false;
                if(ID == -2) return false;
                DBmanagement.checkIfAdmin(ID);
                if(DBmanagement.checkIfAdmin(ID)){
                    Admin admin = DBget.getAdmin(ID);
                    return true;
                }else{
                    User user = DBget.getUser(ID);
                    UserScreen.startUserScreen();
                    return true;
                }
    }
    public static int searchForUsers(String login, String password){
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
                    }else{
                        ID = -2;
                        System.out.println("Incorrect password");
                    }
                    break;
                }
            }
            if(ID == -1){
                System.out.println("User not Found");
            }else{
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
                if (resultSet.getInt("isAdmin") == 1 && resultSet.getInt("ID") == ID){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    } 
    public static boolean signUp(String login,String password,BufferedImage image){
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
        DBset.addUser(login, password, false, image);
        System.out.println("User created");
        return true;
    }
    public static void selectProducts(){
        Statement statement = null;
        ResultSet resultSet = null;
        String Query = "SELECT * FROM Products;";
        try {
            statement = DataBase.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
