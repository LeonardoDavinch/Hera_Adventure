package org.example.object;

import org.example.GamePanel;
import org.example.enty.Entyti;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends Entyti {
    GamePanel gp;
    public  static final  String onjName ="Heart";
    public  OBJ_Heart(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = onjName;
        value = 2;
        down1 = setup("/objects/heart_full",gp.tileSize,gp.tileSize);
        image = setup("/objects/heart_full",gp.tileSize,gp.tileSize);
        image2 = setup("/objects/heart_half",gp.tileSize,gp.tileSize);
        image3 = setup("/objects/heart_blank",gp.tileSize,gp.tileSize);
    }
    public  boolean  use(Entyti entyti){

        gp.playSE(2);
        gp.ui.addMesage("Life "+ value);
        entyti.life += value;
        return  true;
    }
}
