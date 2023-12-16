package org.example;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    EventRect eventRect[][];

    public  int previusEventX,previusEventY;
    public  boolean canTouchEvent = true;

    public  EventHandler(GamePanel gp){
        this.gp=gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow){

            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDeafultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;

            }
        }


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
            if(hit(27,16,"right") == true){teleport(gp.dialogusState);}
            if(hit(23,12,"up") == true){healingPool(23,12,gp.dialogusState);}
        }

    }

    public  boolean  hit(int col, int row,String reqDirection){

        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row  * gp.tileSize + eventRect[col][row].y;

        if(gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false){
            if(gp.player.directory.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
            hit = true;

            previusEventX =gp.player.worldX;
            previusEventY = gp.player.worldY;
            }
        }
        gp.player.solidArea.x = gp.player.solidAreaDefaulX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x =eventRect[col][row].eventRectDeafultX;
        eventRect[col][row].y =eventRect[col][row].eventRectDefaultY;


        return  hit;
    }
    public  void  teleport(int gameState){
        gp.gameState = gameState;
        gp.ui.currentdialogue = "Teleport!";
        gp.player.worldX = gp.tileSize *37;
        gp.player.worldY = gp.tileSize *10;

    }

    public  void  damagePit(int col, int row, int gameState){
     gp.gameState = gameState;
     gp.playSE(6);
     gp.ui.currentdialogue = "You faill into a pit!";
     gp.player.life -= 1;
    // eventRect[col][row].eventDone =true;
        canTouchEvent = false;
    }

    public  void  healingPool(int col, int row,int gameState){
        if(gp.keyH.enterPressed == true){
            gp.gameState = gameState;
            gp.player.attacCanceled = true;
            gp.playSE(2);
            gp.ui.currentdialogue = "You drink the wather.\nYour life and mana have been recovered";
            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;
            gp.aSetter.setMonster();
        }
    }
}
