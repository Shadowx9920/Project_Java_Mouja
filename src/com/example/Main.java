package com.example;

import com.example.DataBase.DataBase;

public class Main {
    public static void main(String[] args) {
        DataBase.initDB("./src/com/example/DataBase/data.db"); 
        
        DataBase.endConnection();
    }
}