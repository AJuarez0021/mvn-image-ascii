package com.work.image.ascii;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author linux
 */
public class Main {

    public static void main(String[] args) {
        try {
            ImageToAscii img = new ImageToAscii();
            String fileName = "image.jpeg";
            int width;
            int height;

            BufferedImage image = img.loadImage(fileName);
            width = 100;
            height = (image.getHeight() * width) / image.getWidth();

            image = img.resizeImage(image, width, height);
            String asciiArt = img.convertToAscii(image);
                      
            img.writeConsole(asciiArt);
            img.writeFile(asciiArt);

        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
