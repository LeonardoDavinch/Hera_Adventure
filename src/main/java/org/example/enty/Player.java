package org.example.enty;

import org.example.GamePanel;
import org.example.KeyHandler;
import org.example.Monsters.MON_GreanSlime;
import org.example.Monsters.MON_Orc;
import org.example.Monsters.MON_RedSlime;
import org.example.object.Armor.OBJ_Pickaxe;
import org.example.object.Magic.OBJ_Fireball;
import org.example.object.OBJ_Axe;
import org.example.object.OBJ_Key;
import org.example.object.Armor.OBJ_Shield_Wood;
import org.example.object.Armor.OBJ_Sword_Normal;
import org.example.object.OBJ_Potion_Red;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends  Entyti{
    KeyHandler keyH;
    public final  int screenX;
    public  final  int screenY;
    public  int standCounter = 0;
    public  boolean attacCanceled = false;
    public  boolean lightUpdate = false;

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

    }

    public  void  setDefaultVale(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;

    /*    //Dungeon b2
        gp.currentMap = 3;
        worldX = gp.tileSize * 25;
        worldY = gp.tileSize * 14;*/

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
        coin = 5000;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShiled = new OBJ_Shield_Wood(gp);
        currentLight = null;
        projectile = new OBJ_Fireball(gp);
        attack = getAttack();
        defense = getDefense();

        getImage();
        getAttacImage();
        getGuardImage();
        setItems();
        setDialogues();
    }
    public  void  setDefaultPostions(){
        gp.currentMap = 0;
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        directory = "down";
    }
    public  void  setDialogues(){

        dialogues[0][0] = "You are level " + level + " now!\n"
                +"You feel stornger! ";

    }
    public  void  restoreStatus(){

        life = maxLife;
        mana = maxMana;
        speed = defauldSpeed;
        invicible = false;
        transparent = false;
        attacing = false;
        guarding = false;
        knockBack = false;
        lightUpdate = true;

    }
    public  void  setItems(){

        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShiled);
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Potion_Red(gp));
        inventory.add(new OBJ_Axe(gp));
        inventory.add(new OBJ_Pickaxe(gp));



    }
    public  int  getAttack(){
        attacArea = currentWeapon.attacArea;
        motion1_duration  = currentWeapon.motion1_duration;
        motion2_duration  = currentWeapon.motion2_duration;
        return  attack = strength * currentWeapon.ataccValue;
    }
    public  int getCurrentWeaponSlot(){
        int curentWeaponSlot = 0;
        for (int i = 0; i < inventory.size(); i++) {
            if(inventory.get(i) == currentWeapon){
                curentWeaponSlot = i;
            }
        }
        return curentWeaponSlot;
    }
    public  int getCurrentSheldSlot(){
        int curentSheldSlot = 0;
        for (int i = 0; i < inventory.size(); i++) {
            if(inventory.get(i) == currentShiled){
                curentSheldSlot = i;
            }
        }
        return curentSheldSlot;
    }
    public  int getDefense(){
        return  defense = dexsterity * currentShiled.defenseValue;
    }
    public  void  getImage(){
        up1 = setup("/player/Walking sprites/boy_up_1",gp.tileSize,gp.tileSize);
        up2 = setup("/player/Walking sprites/boy_up_2",gp.tileSize,gp.tileSize);
        down1 = setup("/player/Walking sprites/boy_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/player/Walking sprites/boy_down_2",gp.tileSize,gp.tileSize);
        left1 =setup("/player/Walking sprites/boy_left_1",gp.tileSize,gp.tileSize);
        left2 =setup("/player/Walking sprites/boy_left_2",gp.tileSize,gp.tileSize);
        right1 =setup("/player/Walking sprites/boy_right_1",gp.tileSize,gp.tileSize);
        right2 =setup("/player/Walking sprites/boy_right_2",gp.tileSize,gp.tileSize);

    }
    public  void  getSleepingImage(BufferedImage image){
        up1  = image;
        up2  = image;
        down1 = image;
        down2 = image;
        left1 = image;
        left2 = image;
        right1= image;
        right2= image;
    }
    public  void getAttacImage(){

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
        if(currentWeapon.type == type_pickaxe) {
            attacUp1 = setup("/player/Attacking sprites/boy_pick_up_1", gp.tileSize, gp.tileSize * 2);
            attacUp2 = setup("/player/Attacking sprites/boy_pick_up_2", gp.tileSize, gp.tileSize * 2);
            attacDown1 = setup("/player/Attacking sprites/boy_pick_down_1", gp.tileSize, gp.tileSize * 2);
            attactDown2 = setup("/player/Attacking sprites/boy_pick_down_2", gp.tileSize, gp.tileSize * 2);
            attacLeft1 = setup("/player/Attacking sprites/boy_pick_left_1", gp.tileSize * 2, gp.tileSize);
            attacLeft2 = setup("/player/Attacking sprites/boy_pick_left_2", gp.tileSize * 2, gp.tileSize);
            attacRight1 = setup("/player/Attacking sprites/boy_pick_right_1", gp.tileSize * 2, gp.tileSize);
            attacRight2 = setup("/player/Attacking sprites/boy_pick_right_2", gp.tileSize * 2, gp.tileSize);
        }
    }
    public  void  getGuardImage(){
        guardUp = setup("/player/Guarding sprites/boy_guard_up",gp.tileSize,gp.tileSize);
        guardDown = setup("/player/Guarding sprites/boy_guard_down",gp.tileSize,gp.tileSize);
        guardLeft = setup("/player/Guarding sprites/boy_guard_left",gp.tileSize,gp.tileSize);
        guardRight = setup("/player/Guarding sprites/boy_guard_right",gp.tileSize,gp.tileSize);
    }
    public  void  update() {

        if(knockBack == true) {


            //Check tile collision
            collisionOn = false;
            gp.oChecker.checkTile(this);
            gp.oChecker.checkObhect(this,true);
            gp.oChecker.checkEntity(this, gp.npc);
            gp.oChecker.checkEntity(this,gp.monster);
            gp.oChecker.checkEntity(this,gp.iTile);

            if (collisionOn == true) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defauldSpeed;
            }
            else if (collisionOn == false  ) {
                switch (knockBackDirectory) {
                    case "up": worldY -= speed;break;
                    case "down":worldY += speed;break;
                    case "left": worldX -= speed;break;
                    case "right":worldX += speed;break;
                }
            }

            knockBackCounter++;
            if (knockBackCounter == 10) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defauldSpeed;
            }
        }
        else if(attacing == true){
            attacing();
        }
        else if(keyH.spacePressed == true){
            guarding = true;
            guardCounter++;
        }

       else if (keyH.upPressed == true || keyH.downPressed == true
                || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {

            if (keyH.upPressed == true) {directory = "up";}
            else if (keyH.downPressed == true) {directory = "down";}
            else if (keyH.leftPressed == true) {directory = "left";}
            else if (keyH.rightPressed == true) {directory = "right";}

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

                currentWeapon.durablility --;
            }

            attacCanceled = false;
            gp.keyH.enterPressed = false;
            guarding = false;
            guardCounter = 0;

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
       else {
           standCounter++;
           if(standCounter == 20){
               sprintNum =1;
               standCounter = 0;
           }
           guarding = false;
           guardCounter = 0;
        }

       if (gp.keyH.shotKeyPressed == true && projectile.alive == false && shotAvaliableCounter == 30
       && projectile.haveResourse(this) == true) {

            projectile.set(worldX,worldY,directory,true,this);

            projectile.subtracktResourse(this);

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
                transparent = false;
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
        if(keyH.godModeON == false){
            if(life < 0 ){
                gp.gameState = gp.gameOverState;
                gp.ui.comandNum -= 1 ;
                gp.stopMusic();
                gp.playSE(12);
            }
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
                    if(canObtainItem(gp.obj[gp.currentMap][i]) == true){
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

            if(i !=999){
                if(gp.keyH.enterPressed == true){
                     attacCanceled = true;
                    gp.npc[gp.currentMap][i].speak();
            }

                gp.npc[gp.currentMap][i].move(directory);
        }
    }
    public  void  contactMonster(int i){

        if( i != 999){
            if(invicible == false && gp.monster[gp.currentMap][i].dyling == false){
                gp.playSE(6);

                int damage = gp.monster[gp.currentMap][i].attack - defense;
                if(damage < 1 ){
                    damage = 1;
                }
                life -= damage;
                invicible = true;
                transparent = true;
            }
        }
    }
    public  void  damageMonster(int i ,Entyti attaker, int attack,int knockBackPower){
        if( i != 999) {
            if (gp.monster[gp.currentMap][i].invicible == false) {

                gp.playSE(5);

                if(knockBackPower > 0){
                    setknockBack(gp.monster[gp.currentMap][i],attaker, knockBackPower);
                }
                if(gp.monster[gp.currentMap][i].offBalance == true){
                    attack *= 5;
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
                //gp.iTile[gp.currentMap][i].checkDrop();

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
         /*   dialogues[0][0] = "You are level " + level + " now!\n"
                    +"You feel stornger! ";*/
            setDialogues();
            startDialogue(this,0);
        }
    }
    public  void  selectItem(){

        int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlocCol,gp.ui.playerSlotRow);

        if(itemIndex < inventory.size()){

            Entyti selectItem = inventory.get(itemIndex);

            if(selectItem.type == type_sword || selectItem.type == type_axe || selectItem.type == type_pickaxe){

                currentWeapon =selectItem;
                attack = getAttack();
                getAttacImage();
            }
            if(selectItem.type == type_shield){

                currentShiled = selectItem;
                defense = getDefense();
            }
            if(selectItem.type == type_light){

                if(currentLight == selectItem){
                    currentLight = null;
                }
                else {
                    currentLight  = selectItem;
                }
                lightUpdate = true;
            }
            if(selectItem.type == type_consumable){

                if(selectItem.use(this) == true){
                    if(selectItem.amout>1){
                        selectItem.amout --;
                    }
                   else {
                        inventory.remove(itemIndex);
                    }
                }
            }
        }
    }
    public  int searchItemInInventory(String itemName) {

        int itemIndex = 999 ;

        for (int i = 0; i < inventory.size(); i++) {
            if(inventory.get(i).name.equals(itemName)){
                itemIndex = i;
                break;
            }
        }
        return  itemIndex;

    }
    public  boolean canObtainItem (Entyti item){

        boolean canObtain  = false;

        Entyti newItem = gp.eGenerator.getObject(item.name);
        //check if stakeble
        if(newItem.stackbale == true){
            int index = searchItemInInventory(newItem.name);

            if(index != 999){
                inventory.get(index).amout++;
                canObtain = true;
            }
            else {
                if(item.inventory.size() != maxInventorySize){
                    inventory.add(newItem);
                    canObtain = true;
                }
            }
        }
        else {
            if(item.inventory.size() != maxInventorySize){
                inventory.add(newItem);
                canObtain = true;
            }
        }
        return  canObtain;
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
                if(guarding == true){
                    image = guardUp;
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
                if(guarding == true){
                    image = guardDown;
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
                if(guarding == true){
                    image = guardLeft;
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
                if(guarding == true){
                    image = guardRight;
                }
                break;
        }

        if(transparent == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.3f));
        }
        if(drawing == true){
            g2.drawImage(image,tempScreenX,tempScreenY,null);
        }

        //Reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));




    }
}
