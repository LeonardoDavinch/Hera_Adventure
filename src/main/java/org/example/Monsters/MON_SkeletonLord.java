package org.example.Monsters;

import org.example.Data.Progress;
import org.example.GamePanel;
import org.example.enty.Entyti;
import org.example.object.Currency.OBJ_Coin_Bronze;
import org.example.object.OBJ_Door_Iron;
import org.example.object.OBJ_Heart;
import org.example.object.OBJ_ManaCrystal;

import java.util.Random;

public class MON_SkeletonLord extends Entyti {
    GamePanel gp;
    public  static  final  String monName = "Skeleton Lord";
    public MON_SkeletonLord(GamePanel gp) {
        super(gp);
        this.gp=gp;

        type = type_monsters;
        boss = true;
        name = monName;
        defauldSpeed = 1;
        speed = defauldSpeed;
        maxLife = 50;
        life = maxLife;
        attack = 10;
        defense = 2;
        exp = 50;
        knockBackPower =5;
        sleep = true;

        int size = gp.tileSize*5;
        solidArea.x = 48;
        solidArea.y = 48;
        solidArea.width =size - 48*2;
        solidArea.height =size - 48;
        solidAreaDefaulX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attacArea.width = 170;
        attacArea.height = 170;
        motion1_duration = 25;
        motion2_duration = 50;


        getImage();
        getAtaccImage();
        setDialogue();
    }

    public  void  getImage(){
        int i = 5;

        if(inRage == false){
            up1 = setup("/monster/skeletonlord_up_1",gp.tileSize*i,gp.tileSize*i);
            up2 = setup("/monster/skeletonlord_up_2",gp.tileSize*i,gp.tileSize*i);
            down1 = setup("/monster/skeletonlord_down_1",gp.tileSize*i,gp.tileSize*i);
            down2 = setup("/monster/skeletonlord_down_2",gp.tileSize*i,gp.tileSize*i);
            left1 = setup("/monster/skeletonlord_left_1",gp.tileSize*i,gp.tileSize*i);
            left2 = setup("/monster/skeletonlord_left_2",gp.tileSize*i,gp.tileSize*i);
            right1 = setup("/monster/skeletonlord_right_1",gp.tileSize*i,gp.tileSize*i);
            right2 = setup("/monster/skeletonlord_right_2",gp.tileSize*i,gp.tileSize*i);
        }
        if(inRage == true){
            up1 = setup("/monster/skeletonlord_phase2_up_1",gp.tileSize*i,gp.tileSize*i);
            up2 = setup("/monster/skeletonlord_phase2_up_2",gp.tileSize*i,gp.tileSize*i);
            down1 = setup("/monster/skeletonlord_phase2_down_1",gp.tileSize*i,gp.tileSize*i);
            down2 = setup("/monster/skeletonlord_phase2_down_2",gp.tileSize*i,gp.tileSize*i);
            left1 = setup("/monster/skeletonlord_phase2_left_1",gp.tileSize*i,gp.tileSize*i);
            left2 = setup("/monster/skeletonlord_phase2_left_2",gp.tileSize*i,gp.tileSize*i);
            right1 = setup("/monster/skeletonlord_phase2_right_1",gp.tileSize*i,gp.tileSize*i);
            right2 = setup("/monster/skeletonlord_phase2_right_2",gp.tileSize*i,gp.tileSize*i);
        }
    }
    public  void  setDialogue(){

        dialogues[0][0] = "No one can steal my treasure!";
        dialogues[0][1] = "You will die here!";
        dialogues[0][2] = "WELCOME TO YOUR DOOM!";

    }
    public  void  getAtaccImage(){
        int i = 5;

        if(inRage == false){
            attacUp1 = setup("/monster/skeletonlord_attack_up_1", gp.tileSize*i, gp.tileSize*i * 2);
            attacUp2 = setup("/monster/skeletonlord_attack_up_2", gp.tileSize*i, gp.tileSize*i * 2);
            attacDown1 = setup("/monster/skeletonlord_attack_down_1", gp.tileSize*i, gp.tileSize*i * 2);
            attactDown2 = setup("/monster/skeletonlord_attack_down_2", gp.tileSize*i, gp.tileSize*i * 2);
            attacLeft1 = setup("/monster/skeletonlord_attack_left_1", gp.tileSize*i * 2, gp.tileSize*i);
            attacLeft2 = setup("/monster/skeletonlord_attack_left_2", gp.tileSize*i * 2, gp.tileSize*i);
            attacRight1 = setup("/monster/skeletonlord_attack_right_1", gp.tileSize*i * 2, gp.tileSize*i);
            attacRight2 = setup("/monster/skeletonlord_attack_right_2", gp.tileSize*i * 2, gp.tileSize*i);
        }
        if(inRage == true){
            attacUp1 = setup("/monster/skeletonlord_phase2_attack_up_1", gp.tileSize*i, gp.tileSize*i * 2);
            attacUp2 = setup("/monster/skeletonlord_phase2_attack_up_2", gp.tileSize*i, gp.tileSize*i * 2);
            attacDown1 = setup("/monster/skeletonlord_phase2_attack_down_1", gp.tileSize*i, gp.tileSize*i * 2);
            attactDown2 = setup("/monster/skeletonlord_phase2_attack_down_2", gp.tileSize*i, gp.tileSize*i * 2);
            attacLeft1 = setup("/monster/skeletonlord_phase2_attack_left_1", gp.tileSize*i * 2, gp.tileSize*i);
            attacLeft2 = setup("/monster/skeletonlord_phase2_attack_left_2", gp.tileSize*i * 2, gp.tileSize*i);
            attacRight1 = setup("/monster/skeletonlord_phase2_attack_right_1", gp.tileSize*i * 2, gp.tileSize*i);
            attacRight2 = setup("/monster/skeletonlord_phase2_attack_right_2", gp.tileSize*i * 2, gp.tileSize*i);
        }

    }
    public  void  setAction(){

        if(inRage == false && life < maxLife/2){
            inRage = true;
            getImage();
            getAtaccImage();
            defauldSpeed++;
            speed = defauldSpeed;
            attack *= 2;
        }

        if(getTileDistance(gp.player) < 10){
            moveTowardPlayer(60);
        }
        else {
            getRandomDirection(120);
        }
        if(attacing == false){
            checkAttacOrNot(60,gp.tileSize*7,gp.tileSize*5);
        }
    }
    public  void  damageReaction(){
        actionLoockCounter = 0;
    }
    public  void  checkDrop(){

        gp.bossBatleOn = true;
        Progress.skeletonLordDefeated = true;

        gp.stopMusic();
        gp.playSE(19);

        for (int i = 0; i < gp.obj[1].length; i++) {

            if(gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(OBJ_Door_Iron.onjName)){
                gp.playSE(21);
                gp.obj[gp.currentMap][i] = null;
            }
        }

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
