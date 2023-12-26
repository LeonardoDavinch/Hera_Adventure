package org.example.object;

import org.example.GamePanel;
import org.example.enty.Entyti;

public class OBJ_ManaCrystal  extends Entyti {

    GamePanel gp;
    public OBJ_ManaCrystal(GamePanel gp) {
        super(gp);
        this.gp=gp;

        type = type_pickupOnly;
        name ="Mana Crystal";
        value = 1;
        down1 = setup("/objects/manacrystal_full",gp.tileSize,gp.tileSize);
        image = setup("/objects/manacrystal_full",gp.tileSize,gp.tileSize);
        image2 = setup("/objects/manacrystal_blank",gp.tileSize,gp.tileSize);
    }
    public  boolean  use(Entyti entyti){

        gp.playSE(2);
        gp.ui.addMesage("Mana "+ value);
        entyti.mana += value;
        return  true;
    }
}
