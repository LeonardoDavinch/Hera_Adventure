package org.example.object.Magic;

import org.example.GamePanel;
import org.example.enty.Entyti;
import org.example.enty.Projectile;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class OBJ_Slime_Bool extends Projectile {

    GamePanel gp;
    public OBJ_Slime_Bool(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Slime Bool";
        speed = 8;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();

    }
    public  void  getImage (){
        up1 = setup("/projectile/slime_up_1",gp.tileSize,gp.tileSize);
        up2 = setup("/projectile/slime_up_2",gp.tileSize,gp.tileSize);
        down1 = setup("/projectile/slime_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/projectile/slime_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("/projectile/slime_left_1",gp.tileSize,gp.tileSize);
        left2 = setup("/projectile/slime_left_2",gp.tileSize,gp.tileSize);
        right1 = setup("/projectile/slime_right_1",gp.tileSize,gp.tileSize);
        right2 = setup("/projectile/slime_right_2",gp.tileSize,gp.tileSize);
    }
    public  boolean haveResourse(Entyti user){

        boolean haveResourse = false;
        if(user.ammo >= useCost){
            haveResourse = true;
        }
        return haveResourse;
    }
    public  void  subtracktResourse(Entyti user){
        user.ammo -= useCost;
    }
    public Color getParticleColor(){
        Color color = new Color(72, 117, 51);
        //speed of player becos damagge
        Timer timer = new Timer();
        gp.player.speed = Math.max(gp.player.speed - 2, 1);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                    gp.player.speed = 4;
            }
        }, 5000);
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
