package org.example;

import org.example.enty.Entyti;

public class CollisionChecker {
   GamePanel gp;
    public CollisionChecker(GamePanel gp) {
        this.gp=gp;
    }
    public  void  checkTile(Entyti entyti){

        int entityLeftWorldX = entyti.worldX + entyti.solidArea.x;
        int entityRightWorldX = entyti.worldX + entyti.solidArea.x + entyti.solidArea.width;
        int entityTopWorldY = entyti.worldY + entyti.solidArea.y;
        int entityBottonWorldY = entyti.worldY + entyti.solidArea.y + entyti.solidArea.height;

        int entityLeftCol =entityLeftWorldX / gp.tileSize;
        int entityRightCol =entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottonRow =entityBottonWorldY / gp.tileSize;

        int tileNum1 ,tileNum2;

        switch (entyti.directory){
            case  "up": {
                entityTopRow = (entityTopWorldY - entyti.speed) / gp.tileSize;
                tileNum1 = gp.titleManeger.mapTitelNumber[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.titleManeger.mapTitelNumber[gp.currentMap][entityRightCol][entityTopRow];
                if (gp.titleManeger.tiles[tileNum1].collision == true || gp.titleManeger.tiles[tileNum2].collision == true) {
                    entyti.collisionOn = true;
                }
                break;
            }
            case "down":
                entityBottonRow = (entityBottonWorldY - entyti.speed) / gp.tileSize;
                tileNum1  = gp.titleManeger.mapTitelNumber[gp.currentMap][entityLeftCol][entityBottonRow];
                tileNum2 = gp.titleManeger.mapTitelNumber[gp.currentMap][entityRightCol][entityBottonRow];
                if(gp.titleManeger.tiles[tileNum1].collision == true || gp.titleManeger.tiles[tileNum2].collision == true){
                    entyti.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entyti.speed) / gp.tileSize;
                tileNum1  = gp.titleManeger.mapTitelNumber[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.titleManeger.mapTitelNumber[gp.currentMap][entityLeftCol][entityBottonRow];
                if(gp.titleManeger.tiles[tileNum1].collision == true || gp.titleManeger.tiles[tileNum2].collision == true){
                    entyti.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX - entyti.speed) / gp.tileSize;
                tileNum1  = gp.titleManeger.mapTitelNumber[gp.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gp.titleManeger.mapTitelNumber[gp.currentMap][entityRightCol][entityBottonRow];
                if(gp.titleManeger.tiles[tileNum1].collision == true || gp.titleManeger.tiles[tileNum2].collision == true){
                    entyti.collisionOn = true;
                }
                break;
        }

    }
    public  int checkObhect(Entyti entyti,boolean player){

        int indx = 999;

        for (int i = 0; i < gp.obj[1].length; i++) {
            if(gp.obj[gp.currentMap][i] != null){

                entyti.solidArea.x = entyti.worldX + entyti.solidArea.x;
                entyti.solidArea.y = entyti.worldY + entyti.solidArea.y;

                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].worldX + gp.obj[gp.currentMap][i].solidArea.x;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].worldY + gp.obj[gp.currentMap][i].solidArea.y;

                switch (entyti.directory) {
                    case "up":
                        entyti.solidArea.y -= entyti.speed;
                        break;
                    case "down":
                        entyti.solidArea.y += entyti.speed;
                        break;
                    case "left":
                        entyti.solidArea.x -= entyti.speed;
                        break;
                    case "right":
                        entyti.solidArea.x += entyti.speed;
                        break;
                }
                    if(entyti.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)){
                        if(gp.obj[gp.currentMap][i].collision == true) {
                            entyti.collision = true;
                        }
                            if(player == true){
                                indx =i;
                            }
                        }

                entyti.solidArea.x = entyti.solidAreaDefaulX;
                entyti.solidArea.y = entyti.solidAreaDefaultY;
                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].solidAreaDefaulX;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].solidAreaDefaultY;
            }
        }
        return  indx;
    }
    //NPC or Monster
    public  int checkEntity(Entyti entyty, Entyti[][] target){
        int indx = 999;

        for (int i = 0; i < target[1].length; i++) {
            if(target[gp.currentMap][i] != null){

                entyty.solidArea.x = entyty.worldX + entyty.solidArea.x;
                entyty .solidArea.y = entyty.worldY + entyty.solidArea.y;

                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].worldX + target[gp.currentMap][i].solidArea.x;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].worldY + target[gp.currentMap][i].solidArea.y;

                switch (entyty.directory){
                    case "up": entyty.solidArea.y -= entyty.speed; break;
                    case "down": entyty.solidArea.y += entyty.speed; break;
                    case "left": entyty.solidArea.x -= entyty.speed; break;
                    case "right": entyty.solidArea.x += entyty.speed; break;
                }

                if(entyty.solidArea.intersects(target[gp.currentMap][i].solidArea)){
                    if(target[gp.currentMap][i] != entyty){
                        entyty.collisionOn = true;
                        indx =i;
                    }
                }


                entyty.solidArea.x = entyty.solidAreaDefaulX;
                entyty.solidArea.y = entyty.solidAreaDefaultY;
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaulX;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;
            }
        }
        return  indx;
    }
    public  boolean  checkPlayer(Entyti entyty){
        boolean contactPlayer = false;

        entyty.solidArea.x = entyty.worldX + entyty.solidArea.x;
        entyty .solidArea.y = entyty.worldY + entyty.solidArea.y;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch (entyty.directory){
            case "up": entyty.solidArea.y -= entyty.speed;break;
            case "down": entyty.solidArea.y += entyty.speed;break;
            case "left": entyty.solidArea.x -= entyty.speed;break;
            case "right": entyty.solidArea.x += entyty.speed;break;
        }

        if(entyty.solidArea.intersects(gp.player.solidArea)){
            entyty.collisionOn = true;
            contactPlayer = true;
        }

        entyty.solidArea.x = entyty.solidAreaDefaulX;
        entyty.solidArea.y = entyty.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaulX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        return  contactPlayer;
    }

}
