package org.example.object.Currency;

import org.example.GamePanel;
import org.example.enty.Entyti;

public class OBJ_Coin_Bronze extends Entyti {
    GamePanel gp;
    public OBJ_Coin_Bronze(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = "Bronze coin";
        value = 1;
        down1 = setup("/objects/coin_bronze",gp.tileSize,gp.tileSize);
    }
    public  void  use(Entyti entyti){

        gp.playSE(1);
        gp.ui.addMesage("Coin "+value);
        gp.player.coin += value;

    }
}
