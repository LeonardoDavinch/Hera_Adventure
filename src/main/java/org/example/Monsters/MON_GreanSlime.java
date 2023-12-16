package org.example.Monsters;

import org.example.GamePanel;
import org.example.enty.Entyti;
import org.example.object.Currency.OBJ_Coin_Bronze;
import org.example.object.Magic.OBJ_Slime_Bool;
import org.example.object.OBJ_Heart;
import org.example.object.OBJ_ManaCrystal;

import java.util.Random;

public class MON_GreanSlime extends Entyti {
   GamePanel gp;
    public MON_GreanSlime(GamePanel gp) {
        super(gp);
        this.gp=gp;

        type = type_monsters;
        name = "Grean Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        attack = 5;
        defense = 0;
        exp = 2;
        projectile = new OBJ_Slime_Bool(gp);

        solidArea.x =   3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaulX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();

    }
    public  void  getImage(){

        up1 = setup("/monster/greenslime_down_1",gp.tileSize,gp.tileSize);
        up2 = setup("/monster/greenslime_down_2",gp.tileSize,gp.tileSize);
        down1 = setup("/monster/greenslime_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/monster/greenslime_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("/monster/greenslime_down_1",gp.tileSize,gp.tileSize);
        left2 = setup("/monster/greenslime_down_2",gp.tileSize,gp.tileSize);
        right1 = setup("/monster/greenslime_down_1",gp.tileSize,gp.tileSize);
        right2 = setup("/monster/greenslime_down_2",gp.tileSize,gp.tileSize);


    }
    public  void  setAction(){
        actionLoockCounter++;

        if (actionLoockCounter == 120) {

            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                directory = "up";
            }  if (i > 25 && i <= 50) {
                directory = "down";
            }  if (i > 50 && i <= 75) {
                directory = "left";
            }  if (i > 75 && i <= 100) {
                directory = "right";
            }

            actionLoockCounter = 0;

        }

        int i = new Random().nextInt(100) + 1;
        if (i > 99 && projectile.alive == false && shotAvaliableCounter == 30) {

            projectile.set(worldX, worldY, directory, true, this);
            gp.projectList.add(projectile);
            shotAvaliableCounter = 0;
        }
    }
    public  void  damageReaction(){
        actionLoockCounter = 0;
        directory = gp.player.directory;
    }
    public  void  checkDrop(){
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
    }

}
