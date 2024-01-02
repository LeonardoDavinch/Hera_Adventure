package org.example.enty;

import org.example.GamePanel;
import org.example.object.Armor.OBJ_Shield_Blue;
import org.example.object.Armor.OBJ_Shield_Wood;
import org.example.object.Armor.OBJ_Sword_Normal;
import org.example.object.OBJ_Axe;
import org.example.object.OBJ_Key;
import org.example.object.OBJ_Potion_Red;

import java.awt.*;

public class NPC_Merchant extends  Entyti {


    public NPC_Merchant(GamePanel gp) {
        super(gp);

        directory = "down";
        speed = 1;

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 16;
        solidAreaDefaulX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        getImage();
        setDialogues();
        setItem();
    }
    public  void  setDialogues(){

        dialogues[0][0] = "He he, so you found me.\nI have some good stuff.\nDo you want to trade?";
        dialogues[1][0] = "Come again, hehe!";
        dialogues[2][0] = "You nead more coin to buy that!";
        dialogues[3][0] = "You cannot carry any more!";
        dialogues[4][0] = "You cannot sell an quiped item!";



    }
    public  void  getImage(){
        up1 = setup("/npc/merchant_down_1",gp.tileSize,gp.tileSize);
        up2 = setup("/npc/merchant_down_2",gp.tileSize,gp.tileSize);
        down1 = setup("/npc/merchant_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/npc/merchant_down_2",gp.tileSize,gp.tileSize);
        left1 =setup("/npc/merchant_down_1",gp.tileSize,gp.tileSize);
        left2 =setup("/npc/merchant_down_2",gp.tileSize,gp.tileSize);
        right1 =setup("/npc/merchant_down_1",gp.tileSize,gp.tileSize);
        right2 =setup("/npc/merchant_down_2",gp.tileSize,gp.tileSize);

    }

    public  void  setItem(){
        inventory.add(new OBJ_Potion_Red(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Sword_Normal(gp));
        inventory.add(new OBJ_Axe(gp));
        inventory.add(new OBJ_Shield_Wood(gp));
        inventory.add(new OBJ_Shield_Blue(gp));

    }
    public  void  speak(){
        facePlayer();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;

    }

}