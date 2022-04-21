package com.example;

import com.example.DataBase.DataBase;
import com.example.GUI.Components.SharedPrefs;
import com.example.GUI.JForms.MainFrame;

public class Main {
    public static void main(String[] args) {
        DataBase.initDB("./src/com/example/DataBase/data.db");
        SharedPrefs.setDarktMode(); 
        MainFrame.startMainFrame();
    }
}