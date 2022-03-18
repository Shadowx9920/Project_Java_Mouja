package com.example;

import com.example.DataBase.DataBase;
import com.example.GUI.Authenticate;

public class Main {
    public static void main(String[] args) {
        DataBase.initDB("./src/com/example/DataBase/data.db"); 
        Authenticate.startAuthentification();
        //DataBase.endConnection();
    }
}