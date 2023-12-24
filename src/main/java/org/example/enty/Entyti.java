package org.example.enty;

import org.example.GamePanel;
import org.example.Monsters.MON_GreanSlime;
import org.example.UtiltyTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Entyti {
    GamePanel gp;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public  BufferedImage attacUp1, attacUp2,attacDown1,attactDown2,
    attacLeft1,attacLeft2,attacRight1,attacRight2;
    public Rectangle solidArea = new Rectangle(0, 0, 48 ,48);
    public  Rectangle attacArea = new Rectangle(0,0,0,0);
    public  int solidAreaDefaulX , solidAreaDefaultY;
    public  String  dialogues[] = new String[20];
    public  boolean collision = false;
    public BufferedImage image ,image2, image3;


    //State
    public  int worldX, worldY;
    public  String directory = "down";
    public  int sprintNum =1;
    public  int dialogIndex = 0;
    public  boolean invicible = false;
    public  boolean collisionOn = false;
    public  boolean attacing = false;
    public  boolean alive = true;
    public  boolean dyling = false;
    public  boolean hpBarOn = false;
    public  boolean onPath = false;
    public  boolean knockBack = false;


    //Counter
    public  int spritCounter =0;
    public  int actionLoockCounter = 0 ;
    public  int invicibleCounter = 0 ;
    public  int dyingCounter = 0;
    public  int hpBarCounter = 0;
    public  int shotAvaliableCounter = 0;
    public  int knockBackCounter = 0;


    //Character Atributs
    public  int speed;
    public  String name;
    public  int defauldSpeed ;
    public  int maxLife;
    public  int life;
    public  int maxMana;
    public  int mana;
    public  int ammo;
    public  int level ;
    public  int strength;
    public  int dexsterity;
    public  int attack;
    public  int defense;
    public  int exp;
    public  int nextLevelExp;
    public  int coin;
    public  Entyti currentWeapon;
    public  Entyti currentShiled;
    public  Projectile projectile ;

    //Items atributes
    public ArrayList<Entyti> inventory = new ArrayList<>();
    public final  int maxInventorySize = 20;
    public  int value;
    public  int ataccValue;
    public  int defenseValue;
    public  String description = "";
    public  int useCost;
    public  int price;
    public  int knockBackPower = 0;

    //Type
    public  int type;// 0 player , 1 npc , 2 monster

    public  final  int type_player = 0;
    public  final  int  type_nps = 1;
    public  final  int type_monsters = 2;
    public  final  int type_sword = 3;
    public  final  int type_axe = 4;
    public  final  int type_shield = 5;
    public  final  int type_consumable = 6;
    public  final  int type_pickupOnly = 7;



    public  Entyti (GamePanel gp){
        this.gp=gp;
    }

   public  void  setAction(){
    }

    public  void  damageReaction(){

    }
    public  void  speak(){
        if(dialogues[dialogIndex] == null){
            dialogIndex = 0;
        }
        gp.ui.currentdialogue = dialogues[dialogIndex];
        dialogIndex++;

        switch (gp.player.directory){
            case "up":
                directory = "down";
                break;
            case "down":
                directory ="up";
                break;
            case "left":
                directory = "right";
                break;
            case "right":
                directory = "left";
                break;
        }

    }
    public void  use(Entyti entyti ){}
    public  void  checkDrop (){}
    public  void  dropItem(Entyti dropedItem){
        for (int i = 0; i < gp.obj[1].length ; i++) {
            if(gp.obj[gp.currentMap][i] == null){
                gp.obj[gp.currentMap][i] = dropedItem;
                gp.obj[gp.currentMap][i].worldX = worldX;
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }
    public Color getParticleColor(){
        Color color = null;
        return  color;
    }
    public  int getParticleSize(){
        int size = 0;
        return  size;
    }
    public  int getParticleSpead(){
        int spead = 0;
        return  spead;
    }
    public  int getParticleMaxLife(){
        int maxLife = 0 ;
        return  maxLife;
    }
    public  void  generateParticle(Entyti generator,Entyti target){

        Color color =generator.getParticleColor();
        int size = generator.getParticleSize();
        int spead = generator.getParticleSpead();
        int maxLife =generator.getParticleMaxLife();

        Particle p1 = new Particle(gp,target,color,size,spead,maxLife,-2,-1);
        Particle p2 = new Particle(gp,target,color,size,spead,maxLife,2,-1);
        Particle p3 = new Particle(gp,target,color,size,spead,maxLife,-2,1);
        Particle p4 = new Particle(gp,target,color,size,spead,maxLife,2,1);
        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);
    }
    public  void  checkColision(){
        collisionOn = false;
        gp.oChecker.checkTile(this);
        gp.oChecker.checkObhect(this,false);
        gp.oChecker.checkEntity(this,gp.npc);
        gp.oChecker.checkEntity(this,gp.monster);
        gp.oChecker.checkEntity(this,gp.iTile);
        boolean contactPlayer = gp.oChecker.checkPlayer(this);


        if(this.type == type_monsters && contactPlayer == true){
            damagePlayer(attack);
        }

    }
    public  void  update(){

        if(knockBack == true){
            checkColision();

            if(collisionOn == true){
                knockBackCounter = 0;
                knockBack = false;
                speed = defauldSpeed;
            } else if (collisionOn == false) {
                switch (gp.player.directory){
                    case "up": worldY -= speed;break;
                    case "down": worldY += speed;break;
                    case "left": worldX -= speed;break;
                    case "right": worldX += speed;break;
                }
            }
            knockBackCounter++;
            if(knockBackCounter == 10){
                knockBackCounter = 0;
                knockBack = false;
                speed =defauldSpeed;
            }

        }
        else {
            setAction();
            checkColision();

            //if colision is false. plater can move
            if(collisionOn == false){
                switch (directory ){
                    case "up": worldY -= speed;break;
                    case "down": worldY += speed;break;
                    case "left": worldX -= speed;break;
                    case "right": worldX += speed;break;
                }
            }
        }



        spritCounter++;
        if (spritCounter > 24) {
            if (sprintNum == 1) {
                sprintNum = 2;
            } else if (sprintNum == 2) {
                sprintNum = 1;
            }
            spritCounter = 0;
        }

        if(invicible == true){
            invicibleCounter ++;
            if(invicibleCounter > 40 ){
                invicible = false;
                invicibleCounter = 0;
            }
        }
        if(shotAvaliableCounter < 30) {
            shotAvaliableCounter++;
        }
    }
    public  void damagePlayer(int attack){

        if(gp.player.invicible == false){
            gp.playSE(6);

            int damage = attack - gp.player.defense;
            if(damage < 0 ){
                damage = 0;
            }
            gp.player.life -= damage;
            gp.player.invicible =true;
        }
    }

    public  void  draw(Graphics2D g2){
        BufferedImage image = null;
        int screenX =worldX - gp.player.worldX +gp.player.screenX;
        int screenY = worldY -gp.player.worldY +gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

            switch (directory) {
                case "up":
                    if(sprintNum == 1) {image = up1;}
                    if(sprintNum == 2){image = up2;}
                    break;
                case "down":
                    if(sprintNum == 1) {image = down1;}
                    if(sprintNum == 2){image = down2;}
                    break;
                case "left":
                    if(sprintNum == 1) {image = left1;}
                    if (sprintNum == 2){image = left2;}
                    break;
                case "right":
                    if(sprintNum == 1) {image = right1;}
                    if(sprintNum == 2){image =right2;}
                    break;
            }

            //Monster hp bar
            if(type == 2 && hpBarOn == true){
                double onScale = (double)gp.tileSize/maxLife;
                double hpBarValue =onScale *life;

                g2.setColor(new Color(69, 75, 98));
                g2.fillRect(screenX-1,screenY-16,gp.tileSize+2,12);

                g2.setColor(new Color(220, 15, 15));
                g2.fillRect(screenX,screenY - 15,(int) hpBarValue,10);

                hpBarCounter ++;
                if(hpBarCounter > 600){
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }


            if(invicible == true){
                hpBarOn = true;
                hpBarCounter =0;
                changAlpha(g2,0.4f);
            }
            if(dyling == true){
                dyingAnimation(g2);
            }

            g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);

            changAlpha(g2,1f);
    }
    }
    public  void  dyingAnimation(Graphics2D g2){

        dyingCounter ++;

        int i = 5;

        if(dyingCounter <= i){changAlpha(g2,0f);}
        if(dyingCounter > i && dyingCounter <= i*2){changAlpha(g2,1f);}
        if(dyingCounter > i*2 && dyingCounter <= i*3){changAlpha(g2,0f);}
        if(dyingCounter > i*3 && dyingCounter <= i*4){changAlpha(g2,1f);}
        if(dyingCounter > i*4 && dyingCounter <= i*5){changAlpha(g2,0f);}
        if(dyingCounter > i*5 && dyingCounter <= i*6){changAlpha(g2,1f);}
        if(dyingCounter > i*6 && dyingCounter <= i*7){changAlpha(g2,0f);}
        if(dyingCounter > i*7 && dyingCounter <= i*8){changAlpha(g2,1f);}
        if(dyingCounter > i*8 ){
            alive = false;
        }
    }
    public  void  changAlpha(Graphics2D g2,float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alphaValue));
    }
    public  BufferedImage setup(String ImagePath ,int widh, int height){
        UtiltyTool uTool =  new UtiltyTool();
        BufferedImage image = null;
        try{
            image =  ImageIO.read(getClass().getResourceAsStream(ImagePath+".png"));
            image = uTool.scaleImage(image,widh,height);
        }catch (IOException e){
            e.printStackTrace();
        }
        return  image;
    }

    //Good+
    public  void  searchPath(int goalCol ,int goalRow) {

        int startCol = (worldX + solidArea.x) / gp.tileSize;
        int startRow = (worldY + solidArea.y) / gp.tileSize;

        gp.pFinder.setNode(startCol, startRow, goalCol, goalRow, this);


        if (gp.pFinder.search() == true) {
            //next worldX and worldY
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;


            //Entyty solia area postion
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;


            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                directory = "up";
            }
            else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                directory = "down";
            }
            else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
                //left on right
                if (enLeftX > nextX) {
                    directory = "left";
                }
                if (enLeftX < nextX) {
                    directory = "right";
                }
            }
            else if (enTopY > nextY && enLeftX > nextX) {
                    directory = "up";
                    checkColision();
                    if (collisionOn == true ) {
                        directory = "left";
                    }
                }

            else if (enTopY > nextY && enLeftX < nextX) {
                    directory = "up";
                    checkColision();
                    if (collisionOn == true ) {
                        directory = "right";
                    }
                }

            else if (enTopY < nextY && enLeftX > nextX) {
                    directory = "down";
                    checkColision();
                    if (collisionOn == true ) {
                        directory = "left";
                    }
                }

            else if (enTopY < nextY && enLeftX < nextX) {
                    directory = "down";
                    checkColision();
                    if (collisionOn == true ) {
                        directory = "right";
                    }
                }
        }
    }
}
