package org.example.Data;

import org.example.GamePanel;
import org.example.enty.Entyti;
import org.example.object.*;
import org.example.object.Armor.OBJ_Shield_Blue;
import org.example.object.Armor.OBJ_Shield_Wood;
import org.example.object.Armor.OBJ_Sword_Normal;

import java.io.*;

public class SaveLoad {

    GamePanel gp;
    public  SaveLoad (GamePanel gp){
        this.gp = gp;

    }

    public  void  save(){

        try {

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));

            DataStorage ds = new DataStorage();

            ds.level = gp.player.level;
            ds.maxLife = gp.player.maxLife;
            ds.life = gp.player.life;
            ds.maxMana = gp.player.maxMana;
            ds.mana = gp.player.mana;
            ds.strenght = gp.player.strength;
            ds.dexterity = gp.player.dexsterity;
            ds.exp = gp.player.exp;
            ds.nextLevelExp = gp.player.nextLevelExp;
            ds.coin = gp.player.coin;

            //Player inventory
            for (int i = 0; i < gp.player.inventory.size(); i++) {
                ds.itemName.add(gp.player.inventory.get(i).name);
                ds.itemAmout.add(gp.player.inventory.get(i).amout);
            }
            //Player Equpment
            ds.currentWaponSlot = gp.player.getCurrentWeaponSlot();
            ds.currentShildSlot = gp.player.getCurrentSheldSlot();

            //Object on Map
            ds.mapObjecName = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldX =new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldY =new int[gp.maxMap][gp.obj[1].length];
            ds.mapOnjectLootName = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectOpened = new boolean[gp.maxMap][gp.obj[1].length];

            for(int mapNum = 0 ; mapNum < gp.maxMap;mapNum++){
                for (int i = 0; i < gp.obj[1].length; i++) {

                    if(gp.obj[mapNum][i] == null){
                        ds.mapObjecName[mapNum][i] = "NA";

                    }
                    else {
                        ds.mapObjecName[mapNum][i] = gp.obj[mapNum][i].name;
                        ds.mapObjectWorldX[mapNum][i] = gp.obj[mapNum][i].worldX;
                        ds.mapObjectWorldY[mapNum][i] = gp.obj[mapNum][i].worldY;

                        if(gp.obj[mapNum][i].loot != null){
                            ds.mapOnjectLootName[mapNum][i] = gp.obj[mapNum][i].loot.name;
                        }
                        ds.mapObjectOpened[mapNum][i] = gp.obj[mapNum][i].opened;

                    }
                }
            }


            //Write the dataStroge object
            oos.writeObject(ds);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public  void  load(){
        try {

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

            //Read the data Storeg
            DataStorage ds = (DataStorage) ois.readObject();

            gp.player.level = ds.level;
            gp.player.maxLife = ds.maxLife;
            gp.player.life = ds.life;
            gp.player.maxMana = ds.maxMana;
            gp.player.mana = ds.mana;
            gp.player.strength = ds.strenght;
            gp.player.dexsterity = ds.dexterity;
            gp.player.exp = ds.exp;
            gp.player.nextLevelExp = ds.nextLevelExp;
            gp.player.coin = ds.coin;

            //Player inventory load
            gp.player.inventory.clear();
            for (int i = 0; i < ds.itemName.size(); i++) {
                gp.player.inventory.add(gp.eGenerator.getObject(ds.itemName.get(i)));
                gp.player.inventory.get(i).amout = ds.itemAmout.get(i);
            }
            //Player Equpment
           gp.player.currentWeapon = gp.player.inventory.get(ds.currentWaponSlot);
            gp.player.currentShiled = gp.player.inventory.get(ds.currentShildSlot);
            gp.player.getAttack();
            gp.player.getDefense();
            gp.player.getAttacImage();

            //Object on Map
            for(int mapNum = 0; mapNum < gp.maxMap;mapNum++){

                for (int i = 0; i < gp.obj[1].length; i++) {

                    if(ds.mapObjecName[mapNum][i].equals("NA") ){
                        gp.obj[mapNum][i] = null;
                    }
                    else {
                        gp.obj[mapNum][i] =gp.eGenerator. getObject(ds.mapObjecName[mapNum][i]);
                        gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
                        gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
                        if(ds.mapOnjectLootName[mapNum][i] != null){
                            gp.obj[mapNum][i].loot = gp.eGenerator.getObject(ds.mapObjecName[mapNum][i]);
                        }
                        gp.obj[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];
                        if(gp.obj[mapNum][i].opened == true){
                            gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image2;
                        }

                    }
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
