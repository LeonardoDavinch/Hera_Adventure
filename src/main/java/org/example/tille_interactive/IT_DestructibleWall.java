package org.example.tille_interactive;

import org.example.GamePanel;
import org.example.enty.Entyti;
import org.example.object.Currency.OBJ_Coin_Bronze;
import org.example.object.OBJ_Heart;
import org.example.object.OBJ_ManaCrystal;

import java.awt.*;
import java.util.Random;

public class IT_DestructibleWall extends InteractiveTille{
    GamePanel gp;
    public IT_DestructibleWall(GamePanel gp,int col , int row) {
        super(gp,col,row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("/tiles interactiv/destructiblewall",gp.tileSize,gp.tileSize);
        destructible = true;
        life = 3;
    }
    public  boolean isCorrectItem(Entyti entyti){
        boolean isCorrectItem = false;

        if(entyti.currentWeapon.type == type_pickaxe){
            isCorrectItem = true;
        }
        return  isCorrectItem;
    }
    public  void  playSe(){
        gp.playSE(20);
    }
    public  InteractiveTille getDestroyedFrom(){
        InteractiveTille tille = null;
        return  tille;
    }
    public Color getParticleColor(){
        Color color = new Color(65,65,65);
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
/*    public  void  checkDrop(){
        int i = new Random().nextInt(100) + 1 ;

        if( i < 50 ){
            dropItem(new OBJ_Coin_Bronze(gp));
        }
        if(i >= 50 && i < 75){
            dropItem(new OBJ_Heart(gp));
        }
        if(i >= 75 && i < 100  ){
            dropItem(new OBJ_ManaCrystal(gp));
        }
    }*/
}
