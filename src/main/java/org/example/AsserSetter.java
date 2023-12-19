package org.example;


import org.example.Monsters.MON_GreanSlime;
import org.example.enty.NPC_OldMan;
import org.example.object.*;
import org.example.object.Armor.OBJ_Shield_Blue;
import org.example.object.Currency.OBJ_Coin_Bronze;
import org.example.tille_interactive.IT_DryTree;

import java.util.Random;

public class AsserSetter {
    GamePanel gp;

    public  AsserSetter(GamePanel gp){
        this.gp=gp;
    }
    public  void setObject_(){
        int i = 0;
        gp.obj[i] = new OBJ_Coin_Bronze(gp);
        gp.obj[i].worldX = gp.tileSize * 25;
        gp.obj[i].worldY = gp.tileSize * 23;
        i++;
        gp.obj[i] = new OBJ_Coin_Bronze(gp);
        gp.obj[i].worldX = gp.tileSize * 21;
        gp.obj[i].worldY = gp.tileSize * 19;
        i++;
        gp.obj[i] = new OBJ_Coin_Bronze(gp);
        gp.obj[i].worldX = gp.tileSize * 26;
        gp.obj[i].worldY = gp.tileSize * 21;
        i++;
        gp.obj[i] = new OBJ_Axe(gp);
        gp.obj[i].worldX = gp.tileSize * 33;
        gp.obj[i].worldY = gp.tileSize * 7;
        i++;
        gp.obj[i] = new OBJ_Shield_Blue(gp);
        gp.obj[i].worldX = gp.tileSize * 30;
        gp.obj[i].worldY = gp.tileSize * 29;
        i++;
        gp.obj[i] = new OBJ_Potion_Red(gp);
        gp.obj[i].worldX = gp.tileSize * 22;
        gp.obj[i].worldY = gp.tileSize * 27;
        i++;
        gp.obj[i] = new OBJ_Heart(gp);
        gp.obj[i].worldX = gp.tileSize * 22;
        gp.obj[i].worldY = gp.tileSize * 29;
        i++;
        gp.obj[i] = new OBJ_ManaCrystal(gp);
        gp.obj[i].worldX = gp.tileSize * 22;
        gp.obj[i].worldY = gp.tileSize * 31;
        i++;
    }
    public  void  setNPC(){

        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 21;

    }
    public  void  setMonster(){

                int i = 0;
                gp.monster[i] = new MON_GreanSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 22;
                gp.monster[i].worldY = gp.tileSize * 38;
                i++;
                gp.monster[i] = new MON_GreanSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 24;
                gp.monster[i].worldY = gp.tileSize * 38;
                i++;
                gp.monster[i] = new MON_GreanSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 19;
                gp.monster[i].worldY = gp.tileSize * 38;

    }
    public  void  setInteractiveTile(){
        int i = 0;
        gp.iTile[i] = new IT_DryTree(gp,27,12);i++;
        gp.iTile[i] = new IT_DryTree(gp,28,12);i++;
        gp.iTile[i] = new IT_DryTree(gp,29,12);i++;
        gp.iTile[i] = new IT_DryTree(gp,30,12);i++;
        gp.iTile[i] = new IT_DryTree(gp,31,12);i++;
        gp.iTile[i] = new IT_DryTree(gp,32,12);i++;
        gp.iTile[i] = new IT_DryTree(gp,33,12);i++;
        gp.iTile[i] = new IT_DryTree(gp,29,21);i++;
        gp.iTile[i] = new IT_DryTree(gp,36,30);i++;
        gp.iTile[i] = new IT_DryTree(gp,34,28);i++;
        gp.iTile[i] = new IT_DryTree(gp,33,28);i++;
        gp.iTile[i] = new IT_DryTree(gp,32,28);i++;
        gp.iTile[i] = new IT_DryTree(gp,31,28);i++;



    }
}
