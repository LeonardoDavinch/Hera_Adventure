package org.example.object;

import org.example.GamePanel;
import org.example.enty.Entyti;

public class OBJ_Door_Iron extends Entyti {
    GamePanel gp;
    public  static final  String onjName ="Iron Door";
    public  OBJ_Door_Iron(GamePanel gp){
        super(gp);
        this.gp = gp;
        type =type_obstacle;
        name = onjName;
        down1 = setup("/objects/door_iron",gp.tileSize,gp.tileSize);
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
        dialogues[0][0] = "it won't budge";
    }
    public  void  interact(){

        startDialogue(this,0);
    }
}
