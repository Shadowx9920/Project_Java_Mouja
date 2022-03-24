package com.example;

import java.util.LinkedList;

import com.example.Accounts.Admin;
import com.example.Accounts.User;
import com.example.DataBase.DBget;
import com.example.DataBase.DataBase;
import com.example.GUI.JForms.UserScreen;

public class Main {
    public static void main(String[] args) {
        DataBase.initDB("./src/com/example/DataBase/data.db"); 
        UserScreen.startUserScreen();
    }
}