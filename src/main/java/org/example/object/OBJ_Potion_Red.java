package org.example.object;

import org.example.GamePanel;
import org.example.enty.Entyti;

public class OBJ_Potion_Red extends Entyti {
    GamePanel gp;

    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);

        this.gp=gp;

        type =type_consumable;
        name = "Red Potion";
        value = 5;
        down1 = setup("/objects/potion_red",gp.tileSize,gp.tileSize);
        description = "[Red potion]\nHeals your life by"+value+".";
    }
    public  void  use(Entyti entyti){
        gp.gameState = gp.dialogusState;
        gp.ui.currentdialogue ="You drink the"+name+"!\n"
                +"Your life has been recovered by "+value+".";
        entyti.life +=value;
        gp.playSE(2);

    }
}
