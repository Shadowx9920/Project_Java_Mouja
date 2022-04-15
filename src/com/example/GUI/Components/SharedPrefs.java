package com.example.GUI.Components;

public class SharedPrefs {
    private static SharedPrefs instance;
    private SharedPrefs() {}
    public static SharedPrefs getInstance() {
        if(instance == null) {
            instance = new SharedPrefs();
        }
        return instance;
    }

    String backgroundColor;
    String accentColor;
    String fontColor;
    String sideButtonColor;
    String sideHoverColor;
    String mainButtonColor;
    String mainHoverColor;

    public void setLightMode(){

    }

    public void setDarktMode(){
        
    }
}
