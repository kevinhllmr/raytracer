package com.raytracer.canvas;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Canvas {  

    private String name = "";
    private final int width;
    private final int height;
    private final BufferedImage image;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public Canvas(int width, int height, String name) {
        this.width = width;
        this.height = height;
        this.name = name;    
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getR(int x, int y) {
        int rgb = image.getRGB(x, y);
        int red = (rgb >> 16) & 0xFF; 
        return red;
    }

     public int getG(int x, int y) {
        int rgb = image.getRGB(x, y);
        int green = (rgb >> 8) & 0xFF; 
        return green;
    }

     public int getB(int x, int y) {
        int rgb = image.getRGB(x, y);
        int blue = rgb & 0xFF; 
        return blue;
    }

    public void setPixel(int x, int y, Color c) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            image.setRGB(x, y, c.getRGB());

        } else {
            throw new IllegalArgumentException("Coordinates (x, y) are outside the image boundaries.");
        }
    }

    public int getPixel(int x, int y) {
        return image.getRGB(x, y);
    }

    public void writeToFile() throws IOException {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        Files.createDirectories(Paths.get("C:\\Users\\maxim\\Documents\\GitHub\\raytracer\\gpu-raytracer\\src\\img\\"));
        File outputfile = new File("C:\\Users\\maxim\\Documents\\GitHub\\raytracer\\gpu-raytracer\\src\\img\\" + timestamp + " " + name + ".png");
        ImageIO.write(image, "png", outputfile);
    }  
}