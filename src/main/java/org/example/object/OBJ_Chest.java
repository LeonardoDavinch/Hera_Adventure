package org.example.object;

import org.example.GamePanel;
import org.example.enty.Entyti;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends Entyti {
    public  static final  String onjName ="Chest";
GamePanel gp;

    public  OBJ_Chest(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = onjName;
        image = setup("/objects/chest",gp.tileSize,gp.tileSize);
        image2 = setup("/objects/chest_opened",gp.tileSize,gp.tileSize);
        down1 = image;
        collision = true;

        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 32;
        solidAreaDefaulX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    public  void  setLoot(Entyti loot){
        this.loot =loot;
        setDialogues();
    }
    public  void  setDialogues(){
    dialogues[0][0]="You open the chest and find a"+loot.name+"!"+"\n...But you can't carry any more!";
    dialogues[1][0]="You open the chest and find a"+loot.name+"!"+"\nYou obtain the"+loot.name+"!";
    dialogues[2][0]="It's empty";
    }
    public  void  interact(){


        if(opened == false){
            gp.playSE(3);


            if(gp.player.canObtainItem(loot) == false){
                startDialogue(this,0);
            }
            else {
               startDialogue(this,1);
                down1 = image2;
                opened = true;
            }
        }
        else {
            startDialogue(this,2);
        }

    }
}
