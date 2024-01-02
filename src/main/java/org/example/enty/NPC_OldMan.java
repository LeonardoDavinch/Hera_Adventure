package org.example.enty;

import org.example.GamePanel;

import java.awt.*;
import java.util.Random;


public class NPC_OldMan extends  Entyti{


    public NPC_OldMan(GamePanel gp) {
        super(gp);

        directory = "down";
        speed = 1;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaulX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 30;
        solidArea.height = 30;

        dialogueSet = -1;
        getImage();
        setDialogues();

    }
    public  void  getImage(){
        up1 = setup("/npc/oldman_up_1",gp.tileSize,gp.tileSize);
        up2 = setup("/npc/oldman_up_2",gp.tileSize,gp.tileSize);
        down1 = setup("/npc/oldman_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/npc/oldman_down_2",gp.tileSize,gp.tileSize);
        left1 =setup("/npc/oldman_left_1",gp.tileSize,gp.tileSize);
        left2 =setup("/npc/oldman_left_2",gp.tileSize,gp.tileSize);
        right1 =setup("/npc/oldman_right_1",gp.tileSize,gp.tileSize);
        right2 =setup("/npc/oldman_right_2",gp.tileSize,gp.tileSize);


    }
    public  void  setDialogues(){

        dialogues[0][0] = "Hello lad";
        dialogues[0][1] = "So you've come to this island to \nfind the reasure ?";
        dialogues[0][2] = "I used to be a great warrior,but now.....\nI'm a bit too old for taking an adventure,";
        dialogues[0][3] = "Be careful on the island,\nthere is danger at every step";
        dialogues[0][4] = "But I see you are a very brave boy";
        dialogues[0][5] = "Well, good luck on you.";

        dialogues[1][0] = "If you become tired, rest at the wather";
        dialogues[1][1] = "However, the mpnsters reappear if you rest\nI dont't know why but that's how it works";
        dialogues[1][2] = "In any case,don't push yourself too hard";

        dialogues[2][0] ="I wonder how open that door..";

    }

    public  void  setAction() {
       if(onPath == true){
           int goalCol =(gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
           int goalRow =(gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;

            searchPath(goalCol,goalRow);

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
    public  void  speak(){

        facePlayer();
        startDialogue(this,dialogueSet);
        dialogueSet++;

        if(dialogues[dialogueSet][0] == null){
            dialogueSet -- ;
        }

    }

}
