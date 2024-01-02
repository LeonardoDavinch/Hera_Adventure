package org.example.object;

import org.example.GamePanel;
import org.example.enty.Entyti;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends Entyti {
    GamePanel gp;
    public  static final  String onjName ="Key";
    public  OBJ_Key(GamePanel gp){
        super(gp);
        this.gp = gp;


        type =type_consumable;
        name = onjName;
        down1 = setup("/objects/key",gp.tileSize,gp.tileSize);
        description = "["+name+"]\nIts opens the door.";
        price = 100;
        stackbale = true;
        setDialogues();
    }
    public  void  setDialogues(){
        dialogues[0][0]="You use the "+ name +"an open the door";
        dialogues[1][0] = "What are you doing?";
    }
    public  boolean  use(Entyti entyti){


        int objIndex = getDetected(entyti,gp.obj,"Door");

        if(objIndex  != 999){
            startDialogue(this,0);
            gp.playSE(3);
            gp.obj[gp.currentMap][objIndex] = null;
            return  true;
        }
        else {
            startDialogue(this,1);
            return  false;
        }


    }


}
