package org.example.object.Armor;

import org.example.GamePanel;
import org.example.enty.Entyti;

public class OBJ_Pickaxe extends Entyti {
    public  static final  String onjName ="Pickaxe";

    public OBJ_Pickaxe(GamePanel gp) {
        super(gp);

        type = type_pickaxe;
        name = onjName;
        down1 = setup("/objects/pickaxe",gp.tileSize,gp.tileSize);
        ataccValue = 2;
        attacArea.width = 30;
        attacArea.height = 30;
        description = "[Pickaxe]]nYou will dig it!";
        price  = 75;
        knockBackPower = 10;
        motion1_duration = 10;
        motion2_duration = 20;
    }
}
