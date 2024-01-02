package org.example.object;

import org.example.GamePanel;
import org.example.enty.Entyti;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends Entyti {
    GamePanel gp;
    public  OBJ_Door(GamePanel gp){
        super(gp);
        this.gp = gp;
        type =type_obstacle;
        name = "Door";
        down1 = setup("/objects/door",gp.tileSize,gp.tileSize);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaulX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialogues();
    }
    public  void  setDialogues(){
        dialogues[0][0] = "You need a key to open this";
    }
    public  void  interact(){

   startDialogue(this,0);
    }
}
