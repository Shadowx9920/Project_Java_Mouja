package com.example;

import javax.swing.UIManager;

import com.example.DataBase.DataBase;
import com.example.GUI.JForms.MainFrame;
import com.formdev.flatlaf.FlatLightLaf;

public class Main {
    public static void main(String[] args) {
        DataBase.initDB("./src/com/example/DataBase/data.db");
        try {
            UIManager.setLookAndFeel( new FlatLightLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        MainFrame.startMainFrame();
    }
}