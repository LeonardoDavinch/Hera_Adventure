package org.example.object.Armor;

import org.example.GamePanel;
import org.example.enty.Entyti;

public class OBJ_Shield_Wood extends Entyti {
    public  static final  String onjName ="Wood Shield";
    public OBJ_Shield_Wood(GamePanel gp) {
        super(gp);

        type = type_shield;
        name = onjName;
        down1 = setup("/objects/shield_wood",gp.tileSize,gp.tileSize);
        defenseValue = 1;
        description = "["+name+"]\nMade by wood.";
        price = 100;
    }
}
