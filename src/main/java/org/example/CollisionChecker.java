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
                tileNum1 = gp.titleManeger.mapTitelNumber[entityLeftCol][entityTopRow];
                tileNum2 = gp.titleManeger.mapTitelNumber[entityRightCol][entityTopRow];
                if (gp.titleManeger.tiles[tileNum1].collision == true || gp.titleManeger.tiles[tileNum2].collision == true) {
                    entyti.collisionOn = true;
                }
                break;
            }
            case "down":
                entityBottonRow = (entityBottonWorldY - entyti.speed) / gp.tileSize;
                tileNum1  = gp.titleManeger.mapTitelNumber[entityLeftCol][entityBottonRow];
                tileNum2 = gp.titleManeger.mapTitelNumber[entityRightCol][entityBottonRow];
                if(gp.titleManeger.tiles[tileNum1].collision == true || gp.titleManeger.tiles[tileNum2].collision == true){
                    entyti.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entyti.speed) / gp.tileSize;
                tileNum1  = gp.titleManeger.mapTitelNumber[entityLeftCol][entityTopRow];
                tileNum2 = gp.titleManeger.mapTitelNumber[entityLeftCol][entityBottonRow];
                if(gp.titleManeger.tiles[tileNum1].collision == true || gp.titleManeger.tiles[tileNum2].collision == true){
                    entyti.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX - entyti.speed) / gp.tileSize;
                tileNum1  = gp.titleManeger.mapTitelNumber[entityRightCol][entityTopRow];
                tileNum2 = gp.titleManeger.mapTitelNumber[entityRightCol][entityBottonRow];
                if(gp.titleManeger.tiles[tileNum1].collision == true || gp.titleManeger.tiles[tileNum2].collision == true){
                    entyti.collisionOn = true;
                }
                break;
        }

    }
    public  int checkObhect(Entyti entyti,boolean player){
        int indx = 999;

        for (int i = 0; i < gp.obj.length; i++) {
            if(gp.obj[i] != null){

                entyti.solidArea.x = entyti.worldX + entyti.solidArea.x;
                entyti.solidArea.y = entyti.worldY + entyti.solidArea.y;

                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

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
                    if(entyti.solidArea.intersects(gp.obj[i].solidArea)){
                        if(gp.obj[i].collision == true) {
                            entyti.collision = true;
                        }
                            if(player == true){
                                indx =i;
                            }
                        }

                entyti.solidArea.x = entyti.solidAreaDefaulX;
                entyti.solidArea.y = entyti.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaulX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }
        return  indx;
    }
    //NPC or Monster
    public  int checkEntity(Entyti entyty, Entyti[] target){
        int indx = 999;

        for (int i = 0; i < target.length; i++) {
            if(target[i] != null){

                entyty.solidArea.x = entyty.worldX + entyty.solidArea.x;
                entyty .solidArea.y = entyty.worldY + entyty.solidArea.y;

                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (entyty.directory){
                    case "up": entyty.solidArea.y -= entyty.speed; break;
                    case "down": entyty.solidArea.y += entyty.speed; break;
                    case "left": entyty.solidArea.x -= entyty.speed; break;
                    case "right": entyty.solidArea.x += entyty.speed; break;
                }

                if(entyty.solidArea.intersects(target[i].solidArea)){
                    if(target[i] != entyty){
                        entyty.collisionOn = true;
                        indx =i;
                    }
                }


                entyty.solidArea.x = entyty.solidAreaDefaulX;
                entyty.solidArea.y = entyty.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaulX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
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
