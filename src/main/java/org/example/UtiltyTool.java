package org.example;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtiltyTool {

    public BufferedImage scaleImage (BufferedImage original, int widh , int height){

        BufferedImage scaledImage = new BufferedImage(widh , height,original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, widh,height,null);
        g2.dispose();

        return  scaledImage;
    }
}
