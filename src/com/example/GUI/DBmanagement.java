package com.example.GUI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.DataBase.DataBase;

public class DBmanagement {
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
}
