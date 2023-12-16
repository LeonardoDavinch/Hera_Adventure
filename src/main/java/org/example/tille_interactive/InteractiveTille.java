package org.example.tille_interactive;

import org.example.GamePanel;
import org.example.enty.Entyti;

import java.awt.*;
import java.awt.image.BufferedImage;

public class InteractiveTille extends Entyti {
    GamePanel gp;
    public  boolean destructible = false;

    public InteractiveTille(GamePanel gp,int col,int row) {
        super(gp);
        this.gp = gp;
    }
    public  boolean isCorrectItem(Entyti entyti){
        boolean isCorrectItem = false;
        return  isCorrectItem;
    }
    public  void  playSe(){
    }
    public  InteractiveTille getDestroyedFrom(){
        InteractiveTille tille = null;
        return  tille;
    }
    public  void  update(){

        if(invicible == true){
            invicibleCounter++;
                if(invicibleCounter > 20 ){
                    invicible = false;
                    invicibleCounter = 0;

            }
        }
    }
    public  void draw(Graphics2D g2){

        int screenX =worldX - gp.player.worldX +gp.player.screenX;
        int screenY = worldY -gp.player.worldY +gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){


            g2.drawImage(down1,screenX,screenY,gp.tileSize,gp.tileSize,null);
        }
    }
}
