package org.example.Monsters;

import org.example.GamePanel;
import org.example.enty.Entyti;
import org.example.object.Currency.OBJ_Coin_Bronze;
import org.example.object.Magic.OBJ_Slime_Bool;
import org.example.object.OBJ_Heart;
import org.example.object.OBJ_ManaCrystal;

import java.util.Random;

public class MON_Bat extends Entyti {
    GamePanel gp;
    public MON_Bat(GamePanel gp) {
        super(gp);
        this.gp=gp;

        type = type_monsters;
        name = "Bat";
        defauldSpeed = 4;
        speed = defauldSpeed;
        maxLife = 7;
        life = maxLife;
        attack = 7;
        defense = 0;
        exp = 7;

        solidArea.x =   3;
        solidArea.y = 15;
        solidArea.width = 42;
        solidArea.height = 21;
        solidAreaDefaulX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();

    }

    public  void  getImage(){

        up1 = setup("/monster/bat_down_1",gp.tileSize,gp.tileSize);
        up2 = setup("/monster/bat_down_2",gp.tileSize,gp.tileSize);
        down1 = setup("/monster/bat_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/monster/bat_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("/monster/bat_down_1",gp.tileSize,gp.tileSize);
        left2 = setup("/monster/bat_down_2",gp.tileSize,gp.tileSize);
        right1 = setup("/monster/bat_down_1",gp.tileSize,gp.tileSize);
        right2 = setup("/monster/bat_down_2",gp.tileSize,gp.tileSize);


    }
    public  void  setAction(){

        if(onPath == true){

/*            //Check if it stops chasing
            checkStopChasingOrNot(gp.player,15,100);

            //Search the directory to go
            searchPath(getGoalCoal(gp.player), getGoalRow(gp.player));

            //Check if it shots a projectly
            checkShortOrNot(200,30);*/
        }
        else {

            //checkStartChasingOrNot(gp.player,5,100);
            //go monster random moved
            getRandomDirection(10);
        }
    }
    public  void  damageReaction(){
        actionLoockCounter = 0;
        //directory = gp.player.directory;
        //onPath = true;
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
