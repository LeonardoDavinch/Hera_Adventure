package org.example.object.Magic;

import org.example.GamePanel;
import org.example.enty.Entyti;
import org.example.enty.Projectile;

import java.awt.*;

public class OBJ_Fireball extends Projectile {

    GamePanel gp;
    public OBJ_Fireball(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Fireball";
        speed = 10;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        knockBackPower = 5;
        useCost = 1;
        alive = false;
        getImage();

    }
    public  void  getImage (){
    up1 = setup("/projectile/fireball_up_1",gp.tileSize,gp.tileSize);
    up2 = setup("/projectile/fireball_up_2",gp.tileSize,gp.tileSize);
    down1 = setup("/projectile/fireball_down_1",gp.tileSize,gp.tileSize);
    down2 = setup("/projectile/fireball_down_2",gp.tileSize,gp.tileSize);
    left1 = setup("/projectile/fireball_left_1",gp.tileSize,gp.tileSize);
    left2 = setup("/projectile/fireball_left_2",gp.tileSize,gp.tileSize);
    right1 = setup("/projectile/fireball_right_1",gp.tileSize,gp.tileSize);
    right2 = setup("/projectile/fireball_right_2",gp.tileSize,gp.tileSize);
    }
    public  boolean haveResourse(Entyti user){

        boolean haveResourse = false;
        if(user.mana >= useCost){
            haveResourse = true;
        }
        return haveResourse;
    }
    public  void  subtracktResourse(Entyti user){
        user.mana -= useCost;
    }
    public Color getParticleColor(){
        Color color = new Color(240,50,30);
        return  color;
    }
    public  int getParticleSize(){
        int size = 10;
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
