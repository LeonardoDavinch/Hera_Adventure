package org.example.object;

import org.example.GamePanel;
import org.example.enty.Entyti;

public class OBJ_BlueHeart extends Entyti {
    GamePanel gp;
    public  static final  String objName= "Blue Heart";
    public OBJ_BlueHeart(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type =type_pickupOnly;
        name = objName;
        down1 = setup("/objects/blueheart",gp.tileSize,gp.tileSize);

        setDialogues();

    }
    public  void setDialogues(){

        dialogues[0][0] = "You pick up a beautiful blue gem";
        dialogues[0][1] = "You find the Blue Heart, the legendar";
    }
    public  boolean use(Entyti entyti){

        gp.gameState = gp.cutsceneState;
        gp.csManager.sceneNum = gp.csManager.ending;

        return  true;
    }
}
