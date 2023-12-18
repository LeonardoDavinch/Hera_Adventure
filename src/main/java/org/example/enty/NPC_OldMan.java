package org.example.enty;

import org.example.GamePanel;

import java.util.Random;


public class NPC_OldMan extends  Entyti{


    public NPC_OldMan(GamePanel gp) {
        super(gp);

        directory = "down";
        speed = 1;

        getImage();
        setDialogues();
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
    }
    public  void  setDialogues(){

        dialogues[0] = "Hello lad";
        dialogues[1] = "So you've come to this island to \nfind the reasure ?";
        dialogues[2] = "I used to be a great warrior,but now.....\nI'm a bit too old for taking an adventure,";
        dialogues[3] = "Be careful on the island,\nthere is danger at every step";
        dialogues[4] = "But I see you are a very brave boy";
        dialogues[5] = "Well, good luck on you.";

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
    public  void  speak(){
  super.speak();

    }

}
