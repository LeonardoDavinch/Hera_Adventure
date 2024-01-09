package org.example;

import org.example.Monsters.MON_SkeletonLord;
import org.example.enty.Player;
import org.example.enty.PlayerDummy;
import org.example.object.OBJ_BlueHeart;
import org.example.object.OBJ_Door;
import org.example.object.OBJ_Door_Iron;

import java.awt.*;

public class CutsceneManager {

    GamePanel gp;
    Graphics2D g2;
    public  int sceneNum;
    public  int scenePhase;
    public  int counter = 0;
    public  float alpha = 0f;
    public  int y;
    public  String endCredit;

    //scene number
    public  final  int NA = 0;
    public  final  int skeletonLord = 1;
    public  final  int ending = 2;
    public  CutsceneManager(GamePanel gp){
        this.gp = gp;

        endCredit = "Program/Music/Art\n"
        +"RyiShow"
        +"\n\n\n\n\n\n\n\n\n\n\n\n\n"
        +"Special Thanks\n"
        +"Erly Demo version\n"
        +"Watabou\n"
        +"Leonardo\n"
        +"Someone\n\n\n\n\n\n"
        +"Thank you for playing!";
    }

    public  void  draw(Graphics2D g2){
        this.g2 = g2;

        switch (sceneNum){
            case skeletonLord:scene_skeletonLord();break;
            case ending: scene_ending();break;
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
    public  void  scene_ending(){

        if(scenePhase == 0){

            gp.stopMusic();
            gp.ui.npc = new OBJ_BlueHeart(gp);
            scenePhase++;

        }
        if(scenePhase == 1){
            gp.ui.drawDialogeScreen();
        }
        if(scenePhase == 2){

            gp.playSE(4);
            scenePhase++;
        }
        if(scenePhase == 3){

            if(counterReacted(300) == true ){
                scenePhase++;
            }

        }
        if(scenePhase == 4){
            alpha += 0.005f;
            if(alpha>1f){
                alpha = 1f;
            }
            drawBlackBakground(alpha);

            if(alpha == 1f){
                alpha = 0;
                scenePhase++;

            }
        }
        if(scenePhase == 5){

            drawBlackBakground(1f);
            alpha += 0.005f;
            if(alpha>1f){
                alpha = 1f;
            }
            String text = "After the fierce battle with the Skeleton Lord,\n" +
                    "the Gera boy finally found the legendary treasure.\n"+
                    "But this is not the end of his journey.\n"+
                    "The Gera boy's Adventure has just begun.";
            drawString(alpha,38f,200,text,70);

            if(counterReacted(600) == true){
                gp.playMusic(0);
                scenePhase++;
            }
        }
        if(scenePhase == 6){

            drawBlackBakground(1f);

            drawString(1f, 120f, gp.screenHeight/2,"Gera Adventure", 40 );

            if(counterReacted(480) == true){
                scenePhase++;
            }
        }
        if(scenePhase == 7 ){

            y = gp.screenHeight/2;
            drawBlackBakground(1f);

            drawString(1f, 38f,y,endCredit,40);

            if(counterReacted(480) == true){
                scenePhase++;
            }
        }
        if(scenePhase == 8){

            drawBlackBakground(1f);

            y--;
            drawString(1f,38f,y,endCredit,40);
        }
    }
    public  boolean counterReacted(int target){

        boolean counterReacted = false;

        counter++;
        if(counter > target){
            counterReacted = true;
            counter = 0 ;
        }
        return  counterReacted ;

    }
    public  void drawBlackBakground(float alpha){

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,gp.screenWidh ,gp.screenHeight);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));

    }
    public  void  drawString(float alpha,float foantSize,int y,String text , int lineHeight){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(foantSize));

        for(String line : text.split("\n")){
            int x = gp.ui.getXforCenterText(line);
            g2.drawString(line ,x, y );
            y += lineHeight ;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
    }
}
