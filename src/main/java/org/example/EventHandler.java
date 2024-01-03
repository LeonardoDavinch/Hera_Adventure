package org.example;
import org.example.enty.Entyti;

import  java.awt.*;


public class EventHandler {
    GamePanel gp;
    EventRect[][][] eventRect;
    Entyti eventMaster;

    public  int previusEventX,previusEventY;
    public  boolean canTouchEvent = true;
    public int tempMap,tempCol,tempRow;

    public  EventHandler(GamePanel gp){
        this.gp=gp;

        eventMaster = new Entyti(gp);

        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map = 0;
        int col = 0;
        int row = 0;
        while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow){

            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDeafultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;

                if(row == gp.maxWorldRow){
                    row = 0;
                    map++;
                }

            }
        }

        setDialogues();
    }
    public  void  setDialogues(){

        eventMaster.dialogues[0][0] = "Teleport!";
        eventMaster.dialogues[1][0] = "You faill into a pit!";
        eventMaster.dialogues[2][0] = "You drink the wather.\nYour life and mana have been recovered.\n" +
                "(The progres has been saved)";
        eventMaster.dialogues[2][1] = "Damn, this is good water";
    }

    public  void  checkEvent(){
        //chek player
        int xDistance = Math.abs(gp.player.worldX - previusEventX);
        int yDistance = Math.abs(gp.player.worldY - previusEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gp.tileSize){
            canTouchEvent =true;
        }

        if(canTouchEvent == true){
            if(hit(0,27,16,"right") == true){teleport(gp.dialogusState);}//teleport
            else if (hit(0,23,12,"up") == true){healingPool(gp.dialogusState);}//heling poll
            else if(hit(0,10,39,"any") == true){teleportMap(1,12,13,gp.indoor);}//to the merchast bos
            else if(hit(1,12,13,"any") == true){teleportMap(0,10,39,gp.outside);}//to outside
            else if(hit(1,12,9,"up") == true){speak(gp.npc[1][0]);}
            else if(hit(0,12,9,"any") == true){teleportMap(2,9,41,gp.dungeon);}//to the dungeon
            else if(hit(2,9,41,"any") == true){teleportMap(0,12,9,gp.outside);}//to outside
            else if(hit(2,8,7,"any") == true){teleportMap(3,26,41,gp.dungeon);}//to b2
            else if(hit(3,26,41,"any") == true){teleportMap(2,8,7,gp.dungeon);}//to b1
        }

    }

    public  boolean  hit(int map ,int col, int row,String reqDirection){

        boolean hit = false;

        if(map == gp.currentMap){
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row  * gp.tileSize + eventRect[map][col][row].y;

            if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false){
                if(gp.player.directory.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                    hit = true;

                    previusEventX =gp.player.worldX;
                    previusEventY = gp.player.worldY;
                }
            }
            gp.player.solidArea.x = gp.player.solidAreaDefaulX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x =eventRect[map][col][row].eventRectDeafultX;
            eventRect[map][col][row].y =eventRect[map][col][row].eventRectDefaultY;

        }


        return  hit;
    }
    public  void  teleportMap(int map ,int col,int row,int area){

            gp.gameState = gp.transitionState;
            gp.nextArea  = area;
            tempMap = map;
            tempCol = col;
            tempRow = row;
            canTouchEvent = false;
            gp.playSE(13);


    }
    public  void  teleport(int gameState){
        gp.gameState = gameState;
        eventMaster.startDialogue(eventMaster,0);
        gp.player.worldX = gp.tileSize *37;
        gp.player.worldY = gp.tileSize *10;

    }

    public  void  damagePit( int gameState){
     gp.gameState = gameState;
     gp.playSE(6);
     eventMaster.startDialogue(eventMaster,1);
     gp.player.life -= 1;
        canTouchEvent = false;
    }

    public  void  healingPool(int gameState){

        if(gp.keyH.enterPressed == true){
            gp.gameState = gameState;
            gp.player.attacCanceled = true;
            gp.playSE(2);
            eventMaster.startDialogue(eventMaster,2);
            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;
            gp.aSetter.setMonster();
            gp.saveLoad.save();
        }
    }
    public  void  speak(Entyti entyti){

        if(gp.keyH.enterPressed == true){
            gp.gameState = gp.dialogusState;
            gp.player.attacCanceled = true;
            entyti.speak();
        }
    }
}
