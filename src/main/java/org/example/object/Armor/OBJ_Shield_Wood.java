package org.example.object.Armor;

import org.example.GamePanel;
import org.example.enty.Entyti;

public class OBJ_Shield_Wood extends Entyti {
    public OBJ_Shield_Wood(GamePanel gp) {
        super(gp);

        type = type_shield;
        name = "Wood Shield";
        down1 = setup("/objects/shield_wood",gp.tileSize,gp.tileSize);
        defenseValue = 1;
        description = "["+name+"]\nMade by wood.";
    }
}
