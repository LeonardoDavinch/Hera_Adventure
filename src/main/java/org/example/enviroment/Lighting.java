package org.example.enviroment;

import org.example.GamePanel;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Lighting {

    GamePanel gp;
    BufferedImage darknesFilter;

    public  Lighting (GamePanel gp ){
        this.gp = gp;
        setLightSource();

    }
    public  void  setLightSource(){
        //create a buffer image
        darknesFilter = new BufferedImage(gp.screenWidh,gp.screenHeight,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) darknesFilter.getGraphics();

        if(gp.player.currentLight == null){
            g2.setColor(new Color(0,0,0,0.98f));

        }
        else {
            int centerX = gp.player.screenX +(gp.tileSize)/2;
            int centerY = gp.player.screenY +(gp.tileSize)/2;

            //create a gradient
            Color color[] = new Color[12];
            float fraction[] = new float[12];

            color[0] = new Color(0,0,0,0.1f);
            color[1] = new Color(0,0,0,0.42f);
            color[2] = new Color(0,0,0,0.52f);
            color[3] = new Color(0,0,0,0.61f);
            color[4] = new Color(0,0,0,0.69f);
            color[5] = new Color(0,0,0,0.76f);
            color[6] = new Color(0,0,0,0.82f);
            color[7] = new Color(0,0,0,0.87f);
            color[8] = new Color(0,0,0,0.91f);
            color[9] = new Color(0,0,0,0.94f);
            color[10] = new Color(0,0,0,0.96f);
            color[11] = new Color(0,0,0,0.98f);

            fraction[0] = 0f;
            fraction[1] = 0.4f;
            fraction[2] = 0.5f;
            fraction[3] = 0.6f;
            fraction[4] = 0.65f;
            fraction[5] = 0.7f;
            fraction[6] = 0.75f;
            fraction[7] = 0.8f;
            fraction[8] = 0.85f;
            fraction[9] = 0.9f;
            fraction[10] = 0.95f;
            fraction[11] = 1f;

            //create a gradient paint settings for the light
            RadialGradientPaint gPaint = new RadialGradientPaint(centerX,centerY,gp.player.currentLight.lightRadius,fraction,color);

            //set the gradient data on g2
            g2.setPaint(gPaint);
        }

        g2.fillRect(0,0,gp.screenWidh,gp.screenHeight);

        g2.dispose();
    }
    public  void  update(){
        if(gp.player.lightUpdate == true){
            setLightSource();
            gp.player.lightUpdate =false;
        }
    }
    public  void  draw(Graphics2D g2){

        g2.drawImage(darknesFilter,0,0,null);
    }
}
