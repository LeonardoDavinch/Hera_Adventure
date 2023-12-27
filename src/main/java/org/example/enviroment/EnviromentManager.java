package org.example.enviroment;

import org.example.GamePanel;

import java.awt.*;

public class EnviromentManager {

    GamePanel gp;
    Lighting lighting;

    public  EnviromentManager(GamePanel gp){
        this.gp=gp;

    }

    public  void  setup(){
        lighting = new Lighting(gp);
    }
    public  void  update(){
        lighting.update();
    }
    public  void  draw(Graphics2D g2){
        lighting.draw(g2);
    }

}
