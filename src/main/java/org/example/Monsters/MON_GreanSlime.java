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
        defauldSpeed = 1;
        speed = defauldSpeed;
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
    public  void  update(){

        super.update();

        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance =(xDistance + yDistance)/gp.tileSize;

        if(onPath == false && tileDistance < 5){

            int i = new Random().nextInt(100)+1;
            if(i > 50){
                onPath = true;
            }
        }
   /*     if(onPath == true && tileDistance > 20){
            onPath = true;
        }*/
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
        if(onPath == true){

            int goalCol =(gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow =(gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;

            searchPath(goalCol,goalRow);

            int i = new Random().nextInt(100) + 1;
            if (i > 80 && projectile.alive == false && shotAvaliableCounter == 30) {

                projectile.set(worldX, worldY, directory, true, this);
                /*gp.projectList.add(projectile);*/

             /*   //check Vacancy
                for (int j = 0; j < gp.projectile[1].length; j++) {
                    if(gp.projectile[gp.currentMap][j] == null){
                        gp.projectile[gp.currentMap][j] = projectile;
                        break;
                    }
                }*/

                shotAvaliableCounter = 0;
            }
        }
        else{
            actionLoockCounter++;

            if (actionLoockCounter == 120) {
                Random random = new Random();
                int i = random.nextInt(100) + 1;

                if (i <= 25) {
                    directory = "up";
                }
                if (i > 25 && i <= 50) {
                    directory = "down";
                }
                if (i > 50 && i <= 75) {
                    directory = "left";
                }
                if (i > 75 && i <= 100) {
                    directory = "right";
                }
                actionLoockCounter = 0;
            }
        }

    }
    public  void  damageReaction(){
        actionLoockCounter = 0;
        //directory = gp.player.directory;
        onPath = true;
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
