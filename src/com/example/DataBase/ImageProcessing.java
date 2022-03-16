package com.example.DataBase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageProcessing {
    public static byte[] convertImgtoBytes(BufferedImage image){
        byte[] buffer = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer = baos.toByteArray();
        return buffer;
    }
    public static BufferedImage convertToBufferedImage(byte[] buffer){
        if (buffer == null) return null;
        InputStream is = new ByteArrayInputStream(buffer);
        BufferedImage newBi = null;
        try {
            newBi = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newBi;
    }
}
