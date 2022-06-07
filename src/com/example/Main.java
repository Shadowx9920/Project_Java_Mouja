package com.example;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

import com.example.DataBase.DataBase;
import com.example.DataBase.GeneratePDF;
import com.example.GUI.Components.SplashScreen.SplashScreen;
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
        new SplashScreen(null, true).setVisible(true);
        MainFrame.startMainFrame(MainFrame.color);

        // JFileChooser chooser = new JFileChooser(); 
        // chooser.setCurrentDirectory(new java.io.File("."));
        // chooser.setDialogTitle("Select Folder");
        // chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        // //
        // // disable the "All files" option.
        // //
        // chooser.setAcceptAllFileFilterUsed(false);
        // //    
        // if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
        //     System.out.println("getCurrentDirectory(): "  +  chooser.getCurrentDirectory());
        //     System.out.println("getSelectedFile() : "  +  chooser.getSelectedFile());
        // }
        // else {
        //     System.out.println("No Selection ");
        // }
        // GeneratePDF.generatePDF("./src/com/example/DataBase/GeneratePDF.pdf");
    }
}