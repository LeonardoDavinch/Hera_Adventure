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

        int mapNum = 0;
        int i = 0;
        gp.obj[mapNum][i] = new OBJ_Axe(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 33;
        gp.obj[mapNum][i].worldY = gp.tileSize * 7;
        i++;

    }
    public  void  setNPC(){
        int mapNum = 0;
        int i =0;
        gp.npc[mapNum][i] = new NPC_OldMan(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 21;
        gp.npc[mapNum][i].worldY = gp.tileSize * 21;
        i++;

        //Map 1
        mapNum = 1;
        gp.npc[mapNum][i] = new NPC_OldMan(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 12;
        gp.npc[mapNum][i].worldY = gp.tileSize * 7;
    }
    public  void  setMonster(){
        int mapNum = 0;
                int i = 0;
                gp.monster[mapNum][i] = new MON_GreanSlime(gp);
                gp.monster[mapNum][i].worldX = gp.tileSize * 22;
                gp.monster[mapNum][i].worldY = gp.tileSize * 38;
                i++;
                gp.monster[mapNum][i] = new MON_GreanSlime(gp);
                gp.monster[mapNum][i].worldX = gp.tileSize * 24;
                gp.monster[mapNum][i].worldY = gp.tileSize * 38;
                i++;
                gp.monster[mapNum][i] = new MON_GreanSlime(gp);
                gp.monster[mapNum][i].worldX = gp.tileSize * 19;
                gp.monster[mapNum][i].worldY = gp.tileSize * 38;
                i++;
                gp.monster[mapNum][i] = new MON_GreanSlime(gp);
                gp.monster[mapNum][i].worldX = gp.tileSize * 20;
                gp.monster[mapNum][i].worldY = gp.tileSize * 38;

                mapNum = 1;

    }
    public  void  setInteractiveTile(){
        int mapNum = 0;
        int i = 0;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,27,12);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,28,12);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,29,12);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,30,12);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,31,12);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,32,12);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,33,12);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,29,21);i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp,36,30);i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp,34,28);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,33,28);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,32,28);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,31,28);i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp,10,40);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,10,41);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,11,41);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,12,41);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,13,41);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,13,40);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,14,40);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,15,40);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,16,40);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,17,40);i++;


    }
}
