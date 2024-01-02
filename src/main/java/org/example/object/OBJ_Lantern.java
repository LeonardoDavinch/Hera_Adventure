package org.example.object;

import org.example.GamePanel;
import org.example.enty.Entyti;

public class OBJ_Lantern  extends Entyti {
    public  static final  String onjName ="Lantern";
    public OBJ_Lantern(GamePanel gp) {
        super(gp);

        type = type_light;
        name = onjName;
        down1 = setup("/objects/lantern",gp.tileSize,gp.tileSize);
        description = "[Lantern]\nIlluminates your\nsurrodings.";
        price = 200;
        lightRadius = 250;


    }

}
