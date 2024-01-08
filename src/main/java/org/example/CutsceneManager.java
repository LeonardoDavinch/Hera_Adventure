package org.example;

import org.example.Monsters.MON_SkeletonLord;
import org.example.enty.Player;
import org.example.enty.PlayerDummy;
import org.example.object.OBJ_Door;
import org.example.object.OBJ_Door_Iron;

import java.awt.*;

public class CutsceneManager {

    GamePanel gp;
    Graphics2D g2;
    public  int sceneNum;
    public  int scenePhase;

    //scene number
    public  final  int NA = 0;
    public  final  int skeletonLord = 1;

    public  CutsceneManager(GamePanel gp){
        this.gp = gp;
    }

    public  void  draw(Graphics2D g2){
        this.g2 = g2;

        switch (sceneNum){
            case skeletonLord:scene_skeletonLord();break;
        }
    }
    public  void scene_skeletonLord(){

        if(scenePhase == 0){

            gp.bossBatleOn = true;

            //shut the iron door
            for (int i = 0; i < gp.obj[1].length; i++) {

                if(gp.obj[gp.currentMap][i] == null){
                    gp.obj[gp.currentMap][i] = new OBJ_Door_Iron(gp);
                    gp.obj[gp.currentMap][i].worldX = gp.tileSize*25;
                    gp.obj[gp.currentMap][i].worldY = gp.tileSize*28;
                    gp.obj[gp.currentMap][i].temp = true;
                    gp.playSE(21);
                    break;
                }
            }

            //search a vecant slot for the dumy
            for (int i = 0; i <gp.npc[1].length; i++) {

                if(gp.npc[gp.currentMap][i] == null ){
                    gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
                    gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
                    gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
                    gp.npc[gp.currentMap][i].directory = gp.player.directory;
                    break;
                }
            }

            gp.player.drawing = false;
            scenePhase ++;
        }
        if(scenePhase == 1){

            gp.player.worldY -= 2;

            if(gp.player.worldY < gp.tileSize*16){
                scenePhase++;
            }
        }
        if(scenePhase == 2){

            for (int i = 0; i < gp.monster[1].length; i++) {

                if(gp.monster[gp.currentMap][i] != null &&
                        gp.monster[gp.currentMap][i].name == MON_SkeletonLord.monName){

                    gp.monster[gp.currentMap][i].sleep = false;
                    gp.ui.npc = gp.monster[gp.currentMap][i];
                    scenePhase++;
                    break;
                }
            }
        }
        if(scenePhase == 3){

            //The boss speak
            gp.ui.drawDialogeScreen();
        }
        if(scenePhase == 4){

            //return to the play


            for (int i = 0; i < gp.npc[1].length; i++) {

                if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)){

                    gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
                    gp.player.worldY = gp.npc[gp.currentMap][i].worldY;

                    gp.npc[gp.currentMap][i] = null;
                    break;

                }
            }

            gp.player.drawing = true;
            sceneNum = NA;
            scenePhase = 0;
            gp.gameState = gp.PlayState;

            gp.stopMusic();
            gp.playSE(22);
        }

    }
}
