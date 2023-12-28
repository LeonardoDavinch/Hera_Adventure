package org.example.Monsters;

import org.example.GamePanel;
import org.example.enty.Entyti;
import org.example.object.Currency.OBJ_Coin_Bronze;
import org.example.object.Magic.OBJ_Slime_Bool;
import org.example.object.OBJ_Heart;
import org.example.object.OBJ_ManaCrystal;

import java.util.Random;

public class MON_Orc  extends Entyti {
    GamePanel gp;
    public MON_Orc(GamePanel gp) {
        super(gp);
        this.gp=gp;

        type = type_monsters;
        name = "Orc";
        defauldSpeed = 1;
        speed = defauldSpeed;
        maxLife = 11;
        life = maxLife;
        attack = 8;
        defense = 2;
        exp = 10;
        knockBackPower =5;

        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaulX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attacArea.width = 48;
        attacArea.height = 48;
        motion1_duration = 40;
        motion2_duration = 85;


        getImage();
        getAtaccImage();

    }

    public  void  getImage(){

        up1 = setup("/monster/orc_up_1",gp.tileSize,gp.tileSize);
        up2 = setup("/monster/orc_up_2",gp.tileSize,gp.tileSize);
        down1 = setup("/monster/orc_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/monster/orc_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("/monster/orc_left_1",gp.tileSize,gp.tileSize);
        left2 = setup("/monster/orc_left_2",gp.tileSize,gp.tileSize);
        right1 = setup("/monster/orc_right_1",gp.tileSize,gp.tileSize);
        right2 = setup("/monster/orc_right_2",gp.tileSize,gp.tileSize);

    }
    public  void  getAtaccImage(){
        attacUp1 = setup("/monster/orc_attack_up_1", gp.tileSize, gp.tileSize * 2);
        attacUp2 = setup("/monster/orc_attack_up_2", gp.tileSize, gp.tileSize * 2);
        attacDown1 = setup("/monster/orc_attack_down_1", gp.tileSize, gp.tileSize * 2);
        attactDown2 = setup("/monster/orc_attack_down_2", gp.tileSize, gp.tileSize * 2);
        attacLeft1 = setup("/monster/orc_attack_left_1", gp.tileSize * 2, gp.tileSize);
        attacLeft2 = setup("/monster/orc_attack_left_2", gp.tileSize * 2, gp.tileSize);
        attacRight1 = setup("/monster/orc_attack_right_1", gp.tileSize * 2, gp.tileSize);
        attacRight2 = setup("/monster/orc_attack_right_2", gp.tileSize * 2, gp.tileSize);
    }
    public  void  setAction(){

        if(onPath == true){

            //Check if it stops chasing
            checkStopChasingOrNot(gp.player,15,100);

            //Search the directory to go
            searchPath(getGoalCoal(gp.player), getGoalRow(gp.player));
        }
        else {

            checkStartChasingOrNot(gp.player,5,100);
            //go monster random moved
            getRandomDirection();
        }
        if(attacing == false){
            checkAttacOrNot(30,gp.tileSize*4,gp.tileSize);
        }
    }
    public  void  damageReaction(){
        actionLoockCounter = 0;
        directory = gp.player.directory;
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
