package org.example.object;

import org.example.GamePanel;
import org.example.enty.Entyti;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends Entyti {
    GamePanel gp;
    public  OBJ_Key(GamePanel gp){
        super(gp);
        this.gp = gp;


        type =type_consumable;
        name = "Key";
        down1 = setup("/objects/key",gp.tileSize,gp.tileSize);
        description = "["+name+"]\nIts opens the door.";
        price = 100;
        stackbale = true;

    }
    public  boolean  use(Entyti entyti){

        gp.gameState = gp.dialogusState;

        int objIndex = getDetected(entyti,gp.obj,"Door");

        if(objIndex  != 999){
            gp.ui.currentdialogue = "You use the "+ name +"an open the door";
            gp.playSE(3);
            gp.obj[gp.currentMap][objIndex] = null;
            return  true;
        }
        else {
            gp.ui.currentdialogue = "What are you doing?";
            return  false;
        }


    }


}
