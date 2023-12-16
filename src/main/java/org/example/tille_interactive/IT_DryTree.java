package org.example.tille_interactive;

import org.example.GamePanel;
import org.example.enty.Entyti;

import java.awt.*;

public class IT_DryTree  extends InteractiveTille {
    GamePanel gp;
    public IT_DryTree(GamePanel gp,int col , int row) {
        super(gp,col,row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("/tiles interactiv/drytree",gp.tileSize,gp.tileSize);
        destructible = true;
        life = 3;
    }
    public  boolean isCorrectItem(Entyti entyti){
        boolean isCorrectItem = false;

        if(entyti.currentWeapon.type == type_axe){
            isCorrectItem = true;
        }
        return  isCorrectItem;
    }
    public  void  playSe(){
        gp.playSE(11);
    }
    public  InteractiveTille getDestroyedFrom(){
        InteractiveTille tille = new IT_Trunk(gp,worldX/gp.tileSize,worldY/gp.tileSize);
        return  tille;
    }
    public Color getParticleColor(){
        Color color = new Color(65,50,30);
        return  color;
    }
    public  int getParticleSize(){
        int size = 6;
        return  size;
    }
    public  int getParticleSpead(){
        int spead = 1;
        return  spead;
    }
    public  int getParticleMaxLife(){
        int maxLife = 20;
        return  maxLife;
    }
}
