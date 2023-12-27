package org.example.object;

import org.example.GamePanel;
import org.example.enty.Entyti;

public class OBJ_Tent extends Entyti {

    GamePanel gp;
    public OBJ_Tent(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Tent";
        down1 = setup("/objects/tent",gp.tileSize,gp.tileSize);
        description = "[Tent]\nYou cal sleep until\nnext moring";
        price = 300;
        stackbale = true;
    }
    public  boolean use(Entyti entyti){

        gp.gameState = gp.sleepState;
        gp.playSE(14);
        gp.player.life = gp.player.maxLife;
        gp.player.mana = gp.player.maxMana;
        gp.player.getSleepingImage(down1);
        return true;
    }
}
