package org.example.object.Armor;

import org.example.GamePanel;
import org.example.enty.Entyti;

public class OBJ_Sword_Normal extends Entyti {

    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp);

        type = type_sword;
        name = "Normal Sword";
        down1 =setup("/objects/sword_normal",gp.tileSize,gp.tileSize);
        ataccValue = 1;
        attacArea.width = 36;
        attacArea.height = 36;
        description = "["+name+"]\nAn old sword.";
        price = 20;
        knockBackPower = 2;
        motion1_duration = 2;
        motion2_duration = 10;

    }
}
