package org.example.enty;

import org.example.AsserSetter;
import org.example.GamePanel;
import org.example.KeyHandler;
import org.example.Monsters.MON_GreanSlime;
import org.example.object.Magic.OBJ_Fireball;
import org.example.object.OBJ_Key;
import org.example.object.Armor.OBJ_Shield_Wood;
import org.example.object.Armor.OBJ_Sword_Normal;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;

public class Player extends  Entyti{
    KeyHandler keyH;
    public final  int screenX;
    public  final  int screenY;
    public  int standCounter = 0;
    public  boolean attacCanceled = false;



    public  Player(GamePanel gp, KeyHandler keyH){
        super(gp);

        this.keyH =keyH;

        screenX = gp.screenWidh/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 -(gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaulX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;


        setDefaultVale();
        getPlayerImage();
        getPlayerAttac();
        setItems();
    }

    public  void  setDefaultVale(){

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        defauldSpeed = 4;
        speed = defauldSpeed;
        directory = "down";

        //player status
        level =1;
        maxLife = 6;
        life = maxLife;
        maxMana = 4;
        mana = maxMana;
        ammo = 10;
        strength =1;
        dexsterity =1;
        exp = 0;
        nextLevelExp =5;
        coin = 25;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShiled = new OBJ_Shield_Wood(gp);
        projectile = new OBJ_Fireball(gp);
        attack = getAttack();
        defense = getDefense();
    }
    public  void  setDefaultPostions(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        directory = "down";
    }
    public  void  restoreLifeAndMan(){

        life = maxLife;
        mana = maxMana;
        invicible = false;

    }
    public  void  setItems(){

        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShiled);
        inventory.add(new OBJ_Key(gp));

    }
    public  int  getAttack(){
        attacArea = currentWeapon.attacArea;

        return  attack = strength * currentWeapon.ataccValue;
    }
    public  int getDefense(){
        return  defense = dexsterity * currentShiled.defenseValue;
    }
    public  void  getPlayerImage(){
        up1 = setup("/player/Walking sprites/boy_up_1",gp.tileSize,gp.tileSize);
        up2 = setup("/player/Walking sprites/boy_up_2",gp.tileSize,gp.tileSize);
        down1 = setup("/player/Walking sprites/boy_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/player/Walking sprites/boy_down_2",gp.tileSize,gp.tileSize);
        left1 =setup("/player/Walking sprites/boy_left_1",gp.tileSize,gp.tileSize);
        left2 =setup("/player/Walking sprites/boy_left_2",gp.tileSize,gp.tileSize);
        right1 =setup("/player/Walking sprites/boy_right_1",gp.tileSize,gp.tileSize);
        right2 =setup("/player/Walking sprites/boy_right_2",gp.tileSize,gp.tileSize);

    }
    public  void  getPlayerAttac(){

        if(currentWeapon.type == type_sword) {
            attacUp1 = setup("/player/Attacking sprites/boy_attack_up_1", gp.tileSize, gp.tileSize * 2);
            attacUp2 = setup("/player/Attacking sprites/boy_attack_up_2", gp.tileSize, gp.tileSize * 2);
            attacDown1 = setup("/player/Attacking sprites/boy_attack_down_1", gp.tileSize, gp.tileSize * 2);
            attactDown2 = setup("/player/Attacking sprites/boy_attack_down_2", gp.tileSize, gp.tileSize * 2);
            attacLeft1 = setup("/player/Attacking sprites/boy_attack_left_1", gp.tileSize * 2, gp.tileSize);
            attacLeft2 = setup("/player/Attacking sprites/boy_attack_left_2", gp.tileSize * 2, gp.tileSize);
            attacRight1 = setup("/player/Attacking sprites/boy_attack_right_1", gp.tileSize * 2, gp.tileSize);
            attacRight2 = setup("/player/Attacking sprites/boy_attack_right_2", gp.tileSize * 2, gp.tileSize);
        }
        if(currentWeapon.type == type_axe) {
            attacUp1 = setup("/player/Attacking sprites/boy_axe_up_1", gp.tileSize, gp.tileSize * 2);
            attacUp2 = setup("/player/Attacking sprites/boy_axe_up_2", gp.tileSize, gp.tileSize * 2);
            attacDown1 = setup("/player/Attacking sprites/boy_axe_down_1", gp.tileSize, gp.tileSize * 2);
            attactDown2 = setup("/player/Attacking sprites/boy_axe_down_2", gp.tileSize, gp.tileSize * 2);
            attacLeft1 = setup("/player/Attacking sprites/boy_axe_left_1", gp.tileSize * 2, gp.tileSize);
            attacLeft2 = setup("/player/Attacking sprites/boy_axe_left_2", gp.tileSize * 2, gp.tileSize);
            attacRight1 = setup("/player/Attacking sprites/boy_axe_right_1", gp.tileSize * 2, gp.tileSize);
            attacRight2 = setup("/player/Attacking sprites/boy_axe_right_2", gp.tileSize * 2, gp.tileSize);
        }
    }

    public  void  update() {

        if(attacing == true){
        attacing();

        }
       else if (keyH.upPressed == true || keyH.downPressed == true
                || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {

            if (keyH.upPressed == true) {
                directory = "up";
            } else if (keyH.downPressed == true) {
                directory = "down";
            } else if (keyH.leftPressed == true) {
                directory = "left";
            } else if (keyH.rightPressed == true) {
                directory = "right";
            }

            //Check tile collision
            collisionOn = false;
            gp.oChecker.checkTile(this);

            //check object collision
            int objIndex = gp.oChecker.checkObhect(this,true);
            pickUpObject(objIndex);

            //check NPC
            int npcIndex = gp.oChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //check monster colision
            int monsterIndex = gp.oChecker.checkEntity(this,gp.monster);
            contactMonster(monsterIndex);

            //check interactive tile collision
            int iTTileIndex = gp.oChecker.checkEntity(this,gp.iTile);

            //Check event
            gp.eHandler.checkEvent();


            //if colision is false. plater can move
            if(collisionOn == false && keyH.enterPressed == false){
                switch (directory ){
                    case "up":{ worldY -= speed;break;}
                    case "down":{ worldY += speed;break;}
                    case "left": {worldX -= speed;break;}
                    case "right":{ worldX += speed;break;}
                }
            }
            if(keyH.enterPressed == true && attacCanceled == false){
                gp.playSE(7);
                attacing = true;
                spritCounter = 0;
            }

            attacCanceled = false;

            gp.keyH.enterPressed = false;

            spritCounter++;
            if (spritCounter > 12) {
                if (sprintNum == 1) {
                    sprintNum = 2;
                } else if (sprintNum == 2) {
                    sprintNum = 1;
                }
                spritCounter = 0;
            }
        }
       if (gp.keyH.shotKeyPressed == true && projectile.alive == false && shotAvaliableCounter == 30
       && projectile.haveResourse(this) == true) {

            projectile.set(worldX,worldY,directory,true,this);

            projectile.subtracktResourse(this);

           /* gp.projectList.add(projectile);*/

           //check vacancy
           for (int i = 0; i < gp.projectile[1].length; i++) {
               if(gp.projectile[gp.currentMap][i] == null){
                   gp.projectile[gp.currentMap][i] = projectile;
                   break;
               }
           }

           shotAvaliableCounter = 0;

            gp.playSE(10);
        }
        //this needs outside
        if(invicible == true){
            invicibleCounter ++;
            if(invicibleCounter > 60 ){
                invicible = false;
                invicibleCounter = 0;
            }
        }
        if(shotAvaliableCounter < 30){
            shotAvaliableCounter ++;
        }
        if(life > maxLife){
            life = maxLife;
        }
        if(mana > maxMana){
            mana = maxMana;
        }
        if(life < 0 ){
            gp.gameState = gp.gameOverState;
            gp.ui.comandNum -= 1 ;
            gp.stopMusic();
            gp.playSE(12);
        }
    }
    public  void  attacing(){
        spritCounter++;

        if(spritCounter <= 5){
            sprintNum =1;
        }
        if(spritCounter > 5 && spritCounter <=25){
            sprintNum = 2;

            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidh = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch (directory){
                case "up": worldY -= attacArea.height;break;
                case "down": worldY += attacArea.width;break;
                case "left": worldX -= attacArea.height;break;
                case "roght": worldX += attacArea.width;break;
            }
            solidArea.width = attacArea.width;
            solidArea.height = attacArea.height;

            //check monster colision
            int monsterIndex = gp.oChecker.checkEntity(this,gp.monster);
            damageMonster(monsterIndex,attack,currentWeapon.knockBackPower);

            int iTileIndex = gp.oChecker.checkEntity(this,gp.iTile);
            damageInteractiveTille(iTileIndex);

            int projectileIndex = gp.oChecker.checkEntity(this,gp.projectile);
            damageProjectile(projectileIndex);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidh;
            solidArea.height = solidAreaHeight;

        }
        if(spritCounter > 25){
            sprintNum = 1;
            spritCounter = 0;
            attacing = false;
        }
    }
    public  void  pickUpObject(int i){
        if(i != 999){
            //pickup only items
                if(gp.obj[gp.currentMap][i].type == type_pickupOnly){

                gp.obj[gp.currentMap][i].use(this);
                gp.obj[gp.currentMap][i] = null;

                }
                //Obstacle
                else if (gp.obj[gp.currentMap][i].type == type_obstacle) {
                    if(keyH.enterPressed == true){
                        attacCanceled = true;
                        gp.obj[gp.currentMap][i].interact();
                    }
                }

                //inventory items
            else {
                    String  text ;
                    if(inventory.size() != maxInventorySize){

                        inventory.add(gp.obj[gp.currentMap][i]);
                        gp.playSE(1);
                        text = "Got a "+ gp.obj[gp.currentMap][i].name+"!";

                    }else {
                        text = "You cannot carry any more!";
                    }
                    gp.ui.addMesage(text);
                    gp.obj[gp.currentMap][i]=null;
                }

        }
    }
    public  void  interactNPC(int i ){

        if(gp.keyH.enterPressed == true){

            if(i !=999){
                     attacCanceled = true;
                    gp.gameState = gp.dialogusState;
                    gp.npc[gp.currentMap][i].speak();
            }
        }
    }
    public  void  contactMonster(int i){

        if( i != 999){
            if(invicible == false && gp.monster[gp.currentMap][i].dyling == false){
                gp.playSE(6);

                int damage = gp.monster[gp.currentMap][i].attack - defense;
                if(damage < 0 ){
                    damage = 0;
                }
                life -= damage;

                invicible = true;
            }
        }
    }
    public  void  damageMonster(int i ,int attack,int knockBackPower){
        if( i != 999) {
            if (gp.monster[gp.currentMap][i].invicible == false) {

                gp.playSE(5);
                if(knockBackPower > 0){
                    knockBack(gp.monster[gp.currentMap][i],knockBackPower);
                }


                int damage = attack - gp.monster[gp.currentMap][i].defense;
                if (damage < 0) {
                    damage = 0;
                }
                gp.monster[gp.currentMap][i].life -= damage;
                gp.ui.addMesage(damage + "damage!");

                gp.monster[gp.currentMap][i].invicible = true;
                gp.monster[gp.currentMap][i].damageReaction();

                if (gp.monster[gp.currentMap][i].life <= 0) {
                    gp.monster[gp.currentMap][i].life = 0;
                    gp.monster[gp.currentMap][i].dyling = true;
                    gp.ui.addMesage("Kiled the " + gp.monster[gp.currentMap][i].name + "!");
                    gp.ui.addMesage("Exp " + gp.monster[gp.currentMap][i].exp);
                    exp +=gp.monster[gp.currentMap][i].exp;
                    checkLevelUp();
                    respawnMonster(i,10000);

                }
            }
        }
    }
    private void respawnMonster(final int monsterIndex, long respawnDelay) {
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        // Respawn the monster at the same location
                        gp.monster[gp.currentMap][monsterIndex] = new MON_GreanSlime(gp);
                        gp.monster[gp.currentMap][monsterIndex].worldX = gp.tileSize * 22; // Change to the appropriate respawn location
                        gp.monster[gp.currentMap][monsterIndex].worldY = gp.tileSize * 38; // Change to the appropriate respawn location
                    }
                },
                respawnDelay
        );
    }
    public  void  knockBack (Entyti entyti,int knockBackPower){
        entyti.directory = directory;
        entyti.speed += knockBackPower;
        entyti.knockBack = true;

    }

    public  void  damageInteractiveTille(int i){

        if(i != 999 && gp.iTile[gp.currentMap][i].destructible == true
                && gp.iTile[gp.currentMap][i].isCorrectItem(this)== true
                && gp.iTile[gp.currentMap][i].invicible == false){

            gp.iTile[gp.currentMap][i].playSe();
            gp.iTile[gp.currentMap][i].life--;
            gp.iTile[gp.currentMap][i].invicible = true;

            //generator destroy tree
            generateParticle(gp.iTile[gp.currentMap][i],gp.iTile[gp.currentMap][i]);

            if(gp.iTile[gp.currentMap][i].life == 0 ) {
                gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedFrom();
            }
        }
    }
    public  void  damageProjectile(int i ){

        if(i != 999){
            Entyti projectile = gp.projectile[gp.currentMap][i];
            projectile.alive = false;
            generateParticle(projectile,projectile);
        }
    }
    public  void  checkLevelUp(){

        if(exp >= nextLevelExp) {

            level++;
            nextLevelExp = nextLevelExp * 2;
            maxLife += 2;
            strength++;
            dexsterity++;
            attack = getAttack();
            defense = getDefense();

            gp.playSE(8);
            gp.gameState = gp.dialogusState;
            gp.ui.currentdialogue = "You are level " + level + " now!\n"
        +"You feel stornger! ";
        }
    }
    public  void  selectItem(){

        int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlocCol,gp.ui.playerSlotRow);

        if(itemIndex < inventory.size()){

            Entyti selectItem = inventory.get(itemIndex);

            if(selectItem.type == type_sword || selectItem.type == type_axe){

                currentWeapon =selectItem;
                attack = getAttack();
                getPlayerAttac();
            }
            if(selectItem.type == type_shield){

                currentShiled = selectItem;
                defense = getDefense();
            }
            if(selectItem.type == type_consumable){

                if(selectItem.use(this) == true){
                    inventory.remove(itemIndex);
                }

            }
        }
    }
    public  void  draw(Graphics2D g2){

        BufferedImage image =null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (directory) {
            case "up":
                if(attacing == false){
                    if(sprintNum == 1) {image = up1;}
                    if(sprintNum == 2){image = up2;}
                }
                if(attacing == true){
                    tempScreenY = screenY - gp.tileSize;
                    if(sprintNum == 1) {image = attacUp1;}
                    if(sprintNum == 2){image = attacUp2;}
                }
                break;
            case "down":
                if(attacing == false){
                    if(sprintNum == 1) {image = down1;}
                    if(sprintNum == 2){image = down2;}
                }
                if( attacing == true){
                    if(sprintNum == 1) {image = attacDown1;}
                    if(sprintNum == 2){image = attactDown2;}
                }
                break;
            case "left":
                if(attacing == false){
                    if(sprintNum == 1) {image = left1;}
                    if (sprintNum == 2){image = left2;}
                }
                if(attacing == true){
                    tempScreenX = screenX - gp.tileSize;
                    if(sprintNum == 1) {image = attacLeft1;}
                    if (sprintNum == 2){image = attacLeft2;}
                }
                break;
            case "right":
                if(attacing == false){
                    if(sprintNum == 1) {image = right1;}
                    if(sprintNum == 2){image =right2;}
                }
                if(attacing == true){
                    if(sprintNum == 1) {image = attacRight1;}
                    if(sprintNum == 2){image =attacRight2;}
                }
                break;
        }

        if(invicible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.3f));
        }
       g2.drawImage(image,tempScreenX,tempScreenY,null);

        //Reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));




    }
}
