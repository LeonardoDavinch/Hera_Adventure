package org.example.object.Armor;

import org.example.GamePanel;
import org.example.enty.Entyti;

public class OBJ_Shield_Blue extends Entyti {
    public  static final  String onjName ="Blue Shield";
    public OBJ_Shield_Blue(GamePanel gp) {
        super(gp);

        type = type_shield;
        name =  onjName;
        down1 = setup("/objects/shield_blue",gp.tileSize,gp.tileSize);
        defenseValue = 2;
        description = "["+name+"]\nA shiny blue shield.";
        price = 250;
    }
}
