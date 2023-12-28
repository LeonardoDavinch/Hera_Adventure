package org.example.object;

import org.example.GamePanel;
import org.example.enty.Entyti;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends Entyti {

GamePanel gp;

    public  OBJ_Chest(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "Chest";
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
    }
    public  void  interact(){

        gp.gameState = gp.dialogusState;

        if(opened == false){
            gp.playSE(3);

            StringBuilder sb = new StringBuilder();
            sb.append("You open the chest and find a"+loot.name+"!");

            if(gp.player.canObtainItem(loot) == false){
                sb.append("\n...But you can't carry any more!");

            }
            else {
                sb.append("\nYou obtain the"+loot.name+"!");
                down1 = image2;
                opened = true;
            }
            gp.ui.currentdialogue = sb.toString();
        }
        else {
            gp.ui.currentdialogue = "It's empty";
        }

    }
}
