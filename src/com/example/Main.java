package com.example;

import java.awt.image.BufferedImage;

import com.example.DataBase.DBset;
import com.example.DataBase.DataBase;
import com.example.GUI.JForms.MainFrame;
import com.example.GUI.TestUI.AdminScreen;
import com.example.GUI.TestUI.Authenticate;
import com.example.GUI.TestUI.UserScreen;

public class Main {
    public static void main(String[] args) {
        DataBase.initDB("./src/com/example/DataBase/data.db"); 
        MainFrame.startMainFrame();
        //AdminScreen.startAdminScreen();
        //Authenticate.startAuthentification();
    }
}