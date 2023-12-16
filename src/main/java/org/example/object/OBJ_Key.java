package org.example.object;

import org.example.GamePanel;
import org.example.enty.Entyti;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends Entyti {
    public  OBJ_Key(GamePanel gp){
        super(gp);

        name = "Key";
        down1 = setup("/objects/key",gp.tileSize,gp.tileSize);
        description = "["+name+"]\nIts opens the door.";

    }

}
