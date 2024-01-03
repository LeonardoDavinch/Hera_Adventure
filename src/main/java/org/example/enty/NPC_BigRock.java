package org.example.enty;

import org.example.GamePanel;
import org.example.object.OBJ_Door_Iron;
import org.example.tille_interactive.IT_MetalPlate;
import org.example.tille_interactive.InteractiveTille;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class NPC_BigRock extends Entyti{
    public  static  final  String  npcName = "Big Rock";
    public NPC_BigRock(GamePanel gp) {
        super(gp);

        name = npcName;
        directory = "down";
        speed = 1;

        solidArea = new Rectangle();
        solidArea.x = 2;
        solidArea.y = 6;
        solidAreaDefaulX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 44;
        solidArea.height = 40;

        dialogueSet = -1;
        getImage();
        setDialogues();

    }
    public  void  getImage(){
        up1 = setup("/npc/bigrock",gp.tileSize,gp.tileSize);
        up2 = setup("/npc/bigrock",gp.tileSize,gp.tileSize);
        down1 = setup("/npc/bigrock",gp.tileSize,gp.tileSize);
        down2 = setup("/npc/bigrock",gp.tileSize,gp.tileSize);
        left1 =setup("/npc/bigrock",gp.tileSize,gp.tileSize);
        left2 =setup("/npc/bigrock",gp.tileSize,gp.tileSize);
        right1 =setup("/npc/bigrock",gp.tileSize,gp.tileSize);
        right2 =setup("/npc/bigrock",gp.tileSize,gp.tileSize);


    }
    public  void  setDialogues(){
        dialogues[0][0] = "it's a giant rock";
    }
    public  void  setAction() {
    }
    public  void  update(){

    }
    public  void  speak(){

        facePlayer();
        startDialogue(this,dialogueSet);
        dialogueSet++;

        if(dialogues[dialogueSet][0] == null){
            dialogueSet -- ;
        }

    }
    public  void  move(String d){

        this.directory = d;

        checkColision();

        if(collisionOn == false){
            switch (directory){
                case "up":worldY -= speed;break;
                case "down":worldY += speed;break;
                case "left":worldX -= speed;break;
                case "right":worldX += speed;break;

            }
        }
        detectPlate();
    }
    public  void  detectPlate(){

        ArrayList<InteractiveTille> plateList = new ArrayList<>();
        ArrayList<Entyti> rockList = new ArrayList<>();

        //Create a plate List
        for (int i = 0; i < gp.iTile[1].length; i++) {

            if(gp.iTile[gp.currentMap][i] != null &&
                    gp.iTile[gp.currentMap][i].name != null &&
            gp.iTile[gp.currentMap][i].name.equals(IT_MetalPlate.itName)){
                plateList.add(gp.iTile[gp.currentMap][i]);
            }
        }

        //Create a rock List
        for (int i = 0; i < gp.npc[1].length; i++) {

            if(gp.npc[gp.currentMap][i] != null &&
                    gp.npc[gp.currentMap][i].name.equals(NPC_BigRock.npcName)){
                rockList.add(gp.npc[gp.currentMap][i]);
            }
        }
        int count = 0;

        //Scane the plate list
        for (int i = 0; i < plateList.size(); i++) {

            int xDistanse = Math.abs(worldX - plateList.get(i).worldX);
            int yDistanse = Math.abs(worldY - plateList.get(i).worldY);
            int distance = Math.max(xDistanse,yDistanse);

            if(distance < 8){

                if(linkedEntyti == null){
                    linkedEntyti = plateList.get(i);
                    gp.playSE(3);
                }

            }
            else {
                if(linkedEntyti == plateList.get(i)){
                    linkedEntyti = null;
                }
            }
        }
        //Scane the rock list
        for (int i = 0; i < rockList.size(); i++) {

            if(rockList.get(i).linkedEntyti != null){
                count++;
            }
        }
        if(count == rockList.size()){

            for (int i = 0; i < gp.obj[1].length; i++) {

                if(gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(OBJ_Door_Iron.onjName)){

                    gp.obj[gp.currentMap][i] = null;
                    gp.playSE(21);
                }
            }
        }

    }

}
