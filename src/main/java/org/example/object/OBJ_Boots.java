package org.example.object;

import org.example.GamePanel;
import org.example.enty.Entyti;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Boots extends Entyti {
    public  static final  String onjName ="Boots";
    public  OBJ_Boots(GamePanel gp){

        super(gp);
        name = onjName;
        down1 = setup("/objects/boots",gp.tileSize,gp.tileSize);

    }
}
