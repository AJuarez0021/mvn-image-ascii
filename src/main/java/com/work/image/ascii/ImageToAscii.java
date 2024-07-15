package com.work.image.ascii;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;

/**
 *
 * @author linux
 */
public class ImageToAscii {

    private static final String ASCII_CHARS = "@%#*+=-:. ";

    public BufferedImage loadImage(String fileName) throws URISyntaxException, IOException {
        URI uri = ImageToAscii.class.getClassLoader().getResource(fileName).toURI();
        BufferedImage image = ImageIO.read(new File(uri));
        return image;
    }

    public BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        resizedImage.getGraphics().drawImage(originalImage, 0, 0, width, height, null);
        return resizedImage;
    }

    public String convertToAscii(BufferedImage image) {
        StringBuilder asciiArt = new StringBuilder();
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y));
                double brightness = 0.2126 * color.getRed() + 0.7152 * color.getGreen() + 0.0722 * color.getBlue();
                int index = (int) ((brightness * (ASCII_CHARS.length() - 1)) / 255);
                asciiArt.append(ASCII_CHARS.charAt(index));
            }
            asciiArt.append("\n");
        }
        return asciiArt.toString();
    }

    public void writeConsole(String ascii) {
        System.out.println(ascii);
    }

    public void writeFile(String ascii) throws IOException {
        try (FileWriter writer = new FileWriter("ascii-art.txt")) {
            writer.write(ascii);
        }
    }
}
