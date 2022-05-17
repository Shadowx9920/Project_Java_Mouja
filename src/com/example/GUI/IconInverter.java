package com.example.GUI;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFrame;

import java.awt.Color;

public class IconInverter {

    private IconInverter() {}
    private static IconInverter instance = null;
    public static IconInverter getInstance() {
        if (instance == null) {
            instance = new IconInverter();
        }
        return instance;
    }

    public static BufferedImage invert(String url) {
        BufferedImage image = null;
        try {
            image = javax.imageio.ImageIO.read(new File(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int width = image.getWidth();
        int height = image.getHeight();
        System.out.println("width: " + width); 
        System.out.println("height: " + height);
        BufferedImage invertedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color c = new Color(image.getRGB(x, y));
                if(c.equals(Color.BLACK)) {
                    invertedImage.setRGB(x, y, Color.WHITE.getRGB());
                }
            }
        }
        JFrame frame = new JFrame();
        frame.setSize(width, height);
        frame.getContentPane().add(new javax.swing.JLabel(new javax.swing.ImageIcon(invertedImage)));
        frame.setVisible(true);
        return invertedImage;
    }
}
