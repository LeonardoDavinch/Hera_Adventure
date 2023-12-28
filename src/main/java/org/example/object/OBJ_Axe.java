package org.example.object;

import org.example.GamePanel;
import org.example.enty.Entyti;

public class OBJ_Axe extends Entyti {

    public OBJ_Axe(GamePanel gp) {
        super(gp);

        type = type_axe;
        name = "Woodcutter's Axe";
        down1 = setup("/objects/axe",gp.tileSize,gp.tileSize);
        ataccValue = 2;
        attacArea.width = 30;
        attacArea.height = 30;
        description = "[Woodcutter's Axe]\nA bit rusty but still\ncan cut some trees";
        price  = 75;
        knockBackPower = 10;
        motion1_duration = 20;
        motion2_duration = 40;
    }
}
