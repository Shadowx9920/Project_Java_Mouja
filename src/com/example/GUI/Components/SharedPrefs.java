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

    public static String backgroundColor;
    public static String accentColor;
    public static String fontColor;
    public static String sideButtonColor;
    public static String sideHoverColor;
    public static String mainButtonColor;
    public static String mainHoverColor;

    public static void setLightMode(){
        backgroundColor = "#FFFFFF";
        accentColor = "#000000";
        fontColor = "#000000";
        sideButtonColor = "#000000";
        sideHoverColor = "#000000";
        mainButtonColor = "#000000";
        mainHoverColor = "#000000";
    }

    public static void setDarktMode(){
        backgroundColor = "#FFFFFF";
        accentColor = "#000000";
        fontColor = "#FFFFFF";
        sideButtonColor = "#FFFFFF";
        sideHoverColor = "#ea66c6";
        mainButtonColor = "#FFFFFF";
        mainHoverColor = "#ea66c6";
    }
}
