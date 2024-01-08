package org.example;

import org.example.enty.Entyti;
import org.example.object.Currency.OBJ_Coin_Bronze;
import org.example.object.OBJ_Heart;
import org.example.object.OBJ_ManaCrystal;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font maruMonica,puriasaB;
    public  BufferedImage heart_full,heart_half,heart_blank,crystal_full,crystal_blanck,coin;
    public  boolean messageON = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public  boolean gameFinished = false;
    public  String currentdialogue = "";

    public  int comandNum = 0;
    public  int titelScreanState = 0;
    public   int slotCol = 0;
    public  int playerSlotRow = 0;
    public  int playerSlocCol = 0;
    public  int npcSlotCol = 0;
    public  int npcSlotRow = 0;
    public  int subState = 0;
    public  int counter = 0;
    public  Entyti npc;
    public  int charIndex = 0 ;
    public  String combinedText = "";


    public UI(GamePanel gp){
        this.gp=gp;


        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
            is  = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
            puriasaB = Font.createFont(Font.TRUETYPE_FONT,is);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Create hud object
        Entyti heart=new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
        Entyti crystal = new OBJ_ManaCrystal(gp);
        crystal_full = crystal.image;
        crystal_blanck = crystal.image2;
        Entyti bronzecoin = new OBJ_Coin_Bronze(gp);
        coin = bronzecoin.down1;




    }
    public void addMesage(String text) {
        message.add(text);
        messageCounter.add(0);
    }

    public  void  draw(Graphics2D g2){
        this.g2 = g2;
        //g2.setFont(maruMonica);
        g2.setFont(maruMonica);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);
        //tile state
        if(gp.gameState == gp.titelState){
            drawTileScrean();
        }
        //Play state
        if(gp.gameState == gp.PlayState){
                 drawPlayerLife();
                 drawMonsterLife();
                 drawMesage();
        }
        //pause state
        if(gp.gameState == gp.PauseState){
            drawPlayerLife();
            drawPauseScrean();
        }
        //Dialoge state
        if(gp.gameState == gp.dialogusState){

            drawDialogeScreen();
        }
        //character state
        if(gp.gameState == gp.charactersState){
            drawCharacterScrean();
            drawinventory(gp.player,true);
        }
        //options state
        if(gp.gameState == gp.optionlaState){
            drawOptionsScrean();
        }
        //Game over state
        if(gp.gameState == gp.gameOverState){
            drawGameOverScreen();
        }
        //Transition state
        if(gp.gameState == gp.transitionState){
            drawTransition();
        }
        //Trade state
        if(gp.gameState == gp.tradeState){
            drawTradeScreen();
        }
        //Sleep state
        if(gp.gameState == gp.sleepState){
            drawSleepScreen();
        }


    }

    public  void  drawPlayerLife(){

        int x = gp.tileSize /2;
        int y = gp.tileSize /2;
        int i = 0;
        int iconSize = 32;
        int manaStartX = (gp.tileSize/2)-5;
        int manaStartY =0;

        //Draw max hearth
        while (i < gp.player.maxLife/2){
            g2.drawImage(heart_blank,x,y,iconSize,iconSize,null);
            i++;
            x += iconSize;
            manaStartY = y + 32;

            if( i % 8 == 0){
                x = gp.tileSize/2;
                y+=iconSize;
            }
        }
        //Reset
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        //Draw current Life
        while (i < gp.player.life){
            g2.drawImage(heart_half,x,y,iconSize,iconSize,null);
            i++;
            if (i < gp.player.life){
                g2.drawImage(heart_full,x,y,iconSize,iconSize,null);
            }
            i++;
            x += iconSize;
        }

        //draw max mana
        x = manaStartX;
        y = manaStartY;
        i = 0;
        while (i < gp.player.maxMana){
            g2.drawImage(crystal_blanck,x,y,iconSize,iconSize,null);
            i++;
            x += 20;

            if(i % 10 == 0){
                x = manaStartX;
                y +=iconSize;
            }

        }
        //draw mana
        x = manaStartX;
        y = manaStartY;
        i = 0;
        while (i < gp.player.mana){
            g2.drawImage(crystal_full,x,y,iconSize,iconSize,null);
            i++;
            x += 20;

            if( i % 10 == 0){
                x =manaStartX;
                y +=iconSize;
            }

        }

    }
    public  void  drawMonsterLife(){

        for (int i = 0; i < gp.monster[1].length; i++) {

            Entyti monster  = gp.monster[gp.currentMap][i];

            if(monster != null && monster.inCamera() == true){

                //Monster hp bar
                if(monster.hpBarOn == true && monster.boss == false){

                    double onScale = (double)gp.tileSize/monster.maxLife;
                    double hpBarValue =onScale * monster.life;

                    g2.setColor(new Color(69, 75, 98));
                    g2.fillRect(monster.getScreenX()-1,monster.getScreenY()-16,gp.tileSize+2,12);

                    g2.setColor(new Color(220, 15, 15));
                    g2.fillRect(monster.getScreenX(),monster.getScreenY() - 15,(int) hpBarValue,10);

                    monster.hpBarCounter ++;

                    if(monster.hpBarCounter > 600){
                        monster.hpBarCounter = 0;
                        monster.hpBarOn = false;
                    }
                }
                else if(monster.boss == true){

                    double onScale = (double)gp.tileSize * 8 /monster.maxLife;
                    double hpBarValue =onScale * monster.life;

                    int x  = gp.screenWidh/2 - gp.tileSize*4;
                    int y = gp.tileSize*10;

                    g2.setColor(new Color(35, 35, 35));
                    g2.fillRect(x-1,y-1,gp.tileSize* 8 + 2,22);

                    g2.setColor(new Color(255, 0, 30));
                    g2.fillRect(x,y,(int) hpBarValue, 20);

                    g2.setFont(g2.getFont().deriveFont(Font.BOLD,24f));
                    g2.setColor(Color.white);
                    g2.drawString(monster.name,x + 4,y - 10);
                }
            }
        }
    }

    public  void  drawMesage(){

        int mesageX = gp.tileSize;
        int mesageY = gp.tileSize*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,32F));

        for (int i = 0; i < message.size(); i++) {

            if(message.get(i) !=  null){

                g2.setColor(Color.black);
                g2.drawString(message.get(i),mesageX+2,mesageY+2);
                g2.setColor(Color.white);
                g2.drawString(message.get(i),mesageX,mesageY);

                int counter = messageCounter.get(i)+1;
                messageCounter.set(i,counter);
                mesageY += 50;

                if(messageCounter.get(i) > 180){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
            
        }
    }

    public  void  drawTileScrean(){

        if (titelScreanState == 0) {



            Entyti entyti =new Entyti(gp);
            BufferedImage backgroundImage = entyti.setup("/backgraund/BackMenu", gp.screenWidh, gp.screenHeight);
            g2.drawImage(backgroundImage, 0, 0, gp.screenWidh, gp.screenHeight, null);


            //tile name
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
            String text = " Hera's Adventure";
            int x = getXforCenterText(text);
            int y = gp.tileSize* 3;

            //Shadow
            g2.setColor(Color.black);
            g2.drawString(text,x+5,y+5);

            //Main color
            g2.setColor(Color.WHITE);
            g2.drawString(text,x,y);

            //Red boy image
            x = gp.screenWidh/2 -(gp.tileSize*2)/2;
            y +=gp.tileSize*2;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null );

            //Menu
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));

            text = "New game";
            x = getXforCenterText(text);
            y += gp.tileSize*3.5;
            g2.drawString(text, x,y);
            if(comandNum == 0 ){
                g2.drawString(">",x-gp.tileSize,y);
            }

            text = "Load game";
            x = getXforCenterText(text);
            y += gp.tileSize;
            g2.drawString(text, x,y);
            if(comandNum == 1 ){
                g2.drawString(">",x-gp.tileSize,y);
            }

            text = "Quit";
            x = getXforCenterText(text);
            y += gp.tileSize;
            g2.drawString(text, x,y);
            if(comandNum == 2 ){
                g2.drawString(">",x-gp.tileSize,y);
            }

        }
        else if(titelScreanState == 1){


            //Class selection screan
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));

            String text ="Select your class!";
            int x = getXforCenterText(text);
            int y = gp.tileSize*3;
            g2.drawString(text,x,y);


             text = "Fighter";
             x = getXforCenterText(text);
             y += gp.tileSize*3;
            g2.drawString(text,x,y);
             if(comandNum == 0 ){
                 g2.drawString(">",x-gp.tileSize,y);
             }

            text = "Thief";
            x = getXforCenterText(text);
            y += gp.tileSize;
            g2.drawString(text,x,y);
            if(comandNum == 1 ){
                g2.drawString(">",x-gp.tileSize,y);
            }

            text = "Sorcerer";
            x = getXforCenterText(text);
            y += gp.tileSize;
            g2.drawString(text,x,y);
            if(comandNum == 2 ){
                g2.drawString(">",x-gp.tileSize,y);
            }

            text = "Back";
            x = getXforCenterText(text);
            y += gp.tileSize*2;
            g2.drawString(text,x,y);
            if(comandNum == 3){
                g2.drawString(">",x-gp.tileSize,y);
            }
        }

    }

    public  void drawPauseScrean(){
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
        String text = "PAUSED";
        int x = getXforCenterText(text);

        int y = gp.screenHeight/2;

        g2.drawString(text,x,y);
    }

    public  void  drawDialogeScreen() {

        //window
        int x = gp.tileSize * 3;
        int y = gp.tileSize / 2;
        int width = gp.screenWidh - (gp.tileSize * 6);
        int heidht = gp.tileSize * 4;

        drawSubWindow(x, y, width, heidht);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));//marumonik
        x += gp.tileSize;
        y += gp.tileSize;

        if(npc.dialogues[npc.dialogueSet][npc.dialogIndex] != null){

            /*currentdialogue = npc.dialogues[npc.dialogueSet][npc.dialogIndex];*/

            char character [] = npc.dialogues[npc.dialogueSet][npc.dialogIndex].toCharArray();

            if(charIndex < character.length){

                gp.playSE(17);
                String s = String.valueOf(character[charIndex]);
                combinedText = combinedText + s;
                currentdialogue =combinedText;
                charIndex++;
            }

            if(gp.keyH.enterPressed == true){
                charIndex = 0;
                combinedText = "";

                if(gp.gameState == gp.dialogusState || gp.gameState == gp.cutsceneState){
                    npc.dialogIndex++;
                    gp.keyH.enterPressed = false;
                }
            }
        }
        else {//if not text
            npc.dialogIndex = 0;

            if(gp.gameState == gp.dialogusState ){
                gp.gameState = gp.PlayState;
            }
            if(gp.gameState == gp.cutsceneState){
                gp.csManager.scenePhase++;
            }
        }

        for (String line : currentdialogue.split("\n")) {
        g2.drawString(line, x, y);
        y += 40;
    }
    }

    public  void  drawCharacterScrean(){
        //Create a frame
        final  int frameX = gp.tileSize ;
        final  int frameY = gp.tileSize;
        final  int frameWidh = gp.tileSize * 5;
        final  int frameHeight = gp.tileSize *10;
        drawSubWindow(frameX,frameY,frameWidh,frameHeight);

        //text
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX +20;
        int textY = frameY + gp.tileSize;
        final  int lineHight = 35;

        //Names
        g2.drawString("Level",textX,textY);
        textY +=lineHight;
        g2.drawString("Life",textX,textY);
        textY +=lineHight;
        g2.drawString("Mana",textX,textY);
        textY +=lineHight;
        g2.drawString("Strenght",textX,textY);
        textY +=lineHight;
        g2.drawString("Dextyrity",textX,textY);
        textY +=lineHight;
        g2.drawString("Attack",textX,textY);
        textY +=lineHight;
        g2.drawString("Defence",textX,textY);
        textY +=lineHight;
        g2.drawString("Exp",textX,textY);
        textY +=lineHight;
        g2.drawString("Next Level",textX,textY);
        textY +=lineHight;
        g2.drawString("Coin",textX,textY);
        textY +=lineHight + 20;
        g2.drawString("Weapon",textX,textY);
        textY +=lineHight + 15;
        g2.drawString("Shield",textX,textY);
        textY +=lineHight;

        //Values
        int tailX = (frameX + frameWidh) - 30;
        //rest testY
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHight;

        value = String.valueOf(gp.player.life + "/"+gp.player.maxLife);
        textX = getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHight;

        value = String.valueOf(gp.player.mana + "/"+gp.player.maxMana);
        textX = getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHight;

        value = String.valueOf(gp.player.strength);
        textX = getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHight;

        value = String.valueOf(gp.player.dexsterity);
        textX = getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHight;

        value = String.valueOf(gp.player.attack);
        textX = getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHight;

        value = String.valueOf(gp.player.defense);
        textX = getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHight;

        value = String.valueOf(gp.player.exp);
        textX = getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHight;

        value = String.valueOf(gp.player.coin);
        textX = getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHight;

        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY-24, null);
        textY +=gp.tileSize;
        g2.drawImage(gp.player.currentShiled.down1, tailX - gp.tileSize, textY-24, null);

    }
    public  void  drawinventory(Entyti entyti,boolean cursor){

        //Frame
        int frameX  =0 ;
        int frameY  =0 ;
        int frameWeight  =0 ;
        int frameHeight  =0 ;
        int slotCol  =0 ;
        int slotRow =0 ;

        if(entyti == gp.player){
            //Frame
            frameX = gp.tileSize * 12;
            frameY = gp.tileSize;
            frameWeight = gp.tileSize * 6;
            frameHeight = gp.tileSize * 5;
            slotCol = playerSlocCol;
            slotRow = playerSlotRow;
        }
        else {
            frameX = gp.tileSize * 2;
            frameY = gp.tileSize;
            frameWeight = gp.tileSize * 6;
            frameHeight = gp.tileSize * 5;
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;
        }
        drawSubWindow(frameX,frameY,frameWeight,frameHeight);

        //slot
        final  int slotXstart = frameX + 20;
        final  int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;


        //draw players items
        for (int i = 0; i < entyti.inventory.size(); i++) {

            //Equip cursor
            if(entyti.inventory.get(i) == entyti.currentWeapon||
            entyti.inventory.get(i) == entyti.currentShiled || entyti.inventory.get(i) == entyti.currentLight){
                g2.setColor(new Color(121, 95, 95));
                g2.fillRoundRect(slotX,slotY,gp.tileSize,gp.tileSize,10,10 );
            }

            g2.drawImage(entyti.inventory.get(i).down1,slotX,slotY,null);

            //Display amout
            if(entyti == gp.player && entyti.inventory.get(i).amout > 1){

                g2.setFont(g2.getFont().deriveFont(32f));
                int amoutX;
                int amoutY;

                String s = ""+entyti.inventory.get(i).amout;
                amoutX = getXforAlignToRightText(s,slotX+44);
                amoutY = slotY + gp.tileSize;

                //Shadow
                g2.setColor(new Color(60,60,60));
                g2.drawString(s,amoutX,amoutY);
                //Number
                g2.setColor(Color.white);
                g2.drawString(s,amoutX - 3,amoutY - 3);
            }

            slotX +=slotSize    ;

            if( i == 4 || i == 9 || i == 14){
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        //Cursor
        if(cursor == true){
            int cursorX = slotXstart +(slotSize * slotCol);
            int cursotY = slotYstart +(slotSize * slotRow);
            int cursotWeight = gp.tileSize;
            int cursotHeight = gp.tileSize;

            //Draw cursor
            g2.setColor(Color.red);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX,cursotY,cursotWeight,cursotHeight,10,10);

            //descriptions frame
            int dFrameX = frameX;
            int dFrameY = frameY + frameHeight;
            int dFrameWeight = frameWeight;
            int dFrameHeight = gp.tileSize * 3;


            //draw descripton text
            int textX = dFrameX + 20;
            int textY = dFrameY + gp.tileSize;
            g2.setFont(g2.getFont().deriveFont(28F));

            int itemIndex  = getItemIndexOnSlot(slotCol,slotRow);

            if(itemIndex <entyti.inventory.size() ){
                drawSubWindow(dFrameX,dFrameY,dFrameWeight,dFrameHeight);

                for(String line : entyti.inventory.get(itemIndex).description.split("\n")){

                    g2.drawString(line,textX,textY );
                    textY += 32;
                }
                g2.drawString("Durability:"+entyti.inventory.get(itemIndex).durablility,textX,textY+100);

               if(entyti.inventory.get(itemIndex).durablility == 0){
                   gp.player.inventory.remove(itemIndex);
               }

            }
        }

    }
    public  void  drawGameOverScreen(){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gp.screenWidh,gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,110f));

        text = "Game over";
        //Shadow
        g2.setColor(Color.black);
        x = getXforCenterText(text);
        y = gp.tileSize*4;
        g2.drawString(text,x,y);
        //Main
        g2.setColor(Color.white);
        g2.drawString(text,x-4,y-4);

        //Retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXforCenterText(text);
        y += gp.tileSize*4;
        g2.drawString(text,x,y);
        if(comandNum == 0){
            g2.drawString(">",x -40,y);
        }


        //Back to the title screen
        text = "Quit";
        x = getXforCenterText(text);
        y += 55;
        g2.drawString(text,x,y);
        if(comandNum == 1){
            g2.drawString(">",x -40,y);
        }

    }
    public  void  drawOptionsScrean(){

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        //Sub window
        int frameX = gp.tileSize *6;
        int frameY = gp.tileSize;
        int frameWeight = gp.tileSize * 8;
        int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX,frameY,frameWeight,frameHeight);

        switch (subState){
            case  0:options_top(frameX,frameY);break;
            case  1:options_fullScreanNotification(frameX,frameY);break;
            case  2:options_control(frameX,frameY); break;
            case  3:options_endGameConfirmation(frameX,frameY);break;
        }
        gp.keyH.enterPressed =false;
    }
    public  void  options_top(int frameX,int frameY){
        int textX;
        int textY;

        //Titel
        String text = "Options";
        textX = getXforCenterText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text ,textX,textY);

        //Full screan on/off
        textX = frameX + gp.tileSize;
        textY += gp.tileSize * 2;
        g2.drawString("Full Screan",textX,textY);

        if(comandNum == 0){
            g2.drawString("> ",textX-25,textY);
            if(gp.keyH.enterPressed == true){
                if(gp.fullScreanOn == false){
                    gp.fullScreanOn = true;
                }
                else if (gp.fullScreanOn == true) {
                    gp.fullScreanOn = false;
                }
                subState = 1;
            }
        }

        //Music
        textY += gp.tileSize;
        g2.drawString("Music",textX,textY);
        if(comandNum == 1){
            g2.drawString("> ",textX-25,textY);
        }

        //Se
        textY += gp.tileSize;
        g2.drawString("Se",textX,textY);
        if(comandNum == 2){
            g2.drawString("> ",textX-25,textY);
        }

        //Control
        textY += gp.tileSize;
        g2.drawString("Control",textX,textY);
        if(comandNum == 3){
            g2.drawString("> ",textX-25,textY);
            if(gp.keyH.enterPressed == true){
                subState = 2;
                comandNum = 0 ;
            }
        }

        //End Game
        textY += gp.tileSize;
        g2.drawString("End Game",textX,textY);
        if(comandNum == 4){
            g2.drawString("> ",textX-25,textY);
            if(gp.keyH.enterPressed == true){
                subState = 3;
                comandNum = 0;
            }
        }

        //Back
        textY += gp.tileSize * 2;
        g2.drawString("Back",textX,textY);
        if(comandNum == 5){
            g2.drawString("> ",textX-25,textY);
            if(gp.keyH.enterPressed == true){
                gp.gameState = gp.PlayState;
                comandNum = 0;
            }
        }

        //Full screan check box
        textX = frameX + (int) (gp.tileSize * 4.5);
        textY = frameY + gp.tileSize*2+24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX,textY,24,24);
        if(gp.fullScreanOn == true){
            g2.fillRect(textX,textY,24,24);
        }

        //musick volume
        textY += gp.tileSize;
        g2.drawRect(textX,textY,120,24);
        int volumWidh = 24 * gp.music.volumScale;
        g2.fillRect(textX,textY,volumWidh,24);


        //Se volume
        textY += gp.tileSize;
        g2.drawRect(textX,textY,120,24);
         volumWidh = 24 * gp.se.volumScale;
        g2.fillRect(textX,textY,volumWidh,24);

        gp.config.saveConfig();



    }
    public  void  options_fullScreanNotification(int frameX,int frameY){

        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;

        currentdialogue ="The chenge will take \neffect after restarting \nthe game.";

        for(String line : currentdialogue.split("\n")){
            g2.drawString(line,textX,textY);
            textY += 40;
        }

        //Back
        textY = frameY + gp.tileSize * 9;
        g2.drawString("Back",textX,textY);
        if(comandNum == 0 ){
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
            }

        }

    }
    public  void  options_control(int frameX , int frameY){
        int textX;
        int textY;

        //Title
        String text = "Control";
        textX = getXforCenterText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text,textX,textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Move",textX,textY);textY+=gp.tileSize;
        g2.drawString("Confirm/Attack",textX,textY);textY+=gp.tileSize;
        g2.drawString("Shoot/Cast",textX,textY);textY+=gp.tileSize;
        g2.drawString("Character Screen",textX,textY);textY+=gp.tileSize;
        g2.drawString("Pause",textX,textY);textY+=gp.tileSize;
        g2.drawString("Options",textX,textY);textY+=gp.tileSize;

        textX = frameX + gp.tileSize*6;
        textY = frameY + gp.tileSize*2;
        g2.drawString("Wasd",textX,textY);textY+=gp.tileSize;
        g2.drawString("Enter",textX,textY);textY+=gp.tileSize;
        g2.drawString("F",textX,textY);textY+=gp.tileSize;
        g2.drawString("C",textX,textY);textY+=gp.tileSize;
        g2.drawString("P",textX,textY);textY+=gp.tileSize;
        g2.drawString("ESC",textX,textY);textY+=gp.tileSize;

        //back
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize*9;
        g2.drawString("Back",textX,textY);
        if(comandNum == 0){
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
                comandNum = 3;
            }
        }

    }
    public  void  options_endGameConfirmation(int frameX,int frameY){
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;

        currentdialogue = "Quit the game and \nretutn to the title screen?";

        for(String line : currentdialogue.split("\n")){
            g2.drawString(line,textX,textY);
            textY += 40;
        }

        //Yes
        String text = "Yes";
        textX = getXforCenterText(text);
        textY +=gp.tileSize*3;
        g2.drawString(text,textX,textY);
        if(comandNum == 0){
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
                gp.gameState = gp.titelState;
                gp.resetGame(true);
            }
        }

        //No
         text = "No";
        textX = getXforCenterText(text);
        textY +=gp.tileSize;
        g2.drawString(text,textX,textY);
        if(comandNum == 1){
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.enterPressed == true){
                subState =0;
                comandNum = 4;
            }
        }
    }
    public  void  drawTransition(){

        counter++;
        g2.setColor(new Color(0,0,0,counter*5));
        g2.fillRect(0,0,gp.screenWidh,gp.screenHeight);

        if(counter == 50){
            counter = 0;
            gp.gameState = gp.PlayState;
            gp.currentMap = gp.eHandler.tempMap;
            gp.player.worldX = gp.tileSize * gp.eHandler.tempCol;
            gp.player.worldY = gp.tileSize * gp.eHandler.tempRow;
            gp.eHandler.previusEventX = gp.player.worldX;
            gp.eHandler.previusEventY = gp.player.worldY;
            gp.changeArea();
        }
    }
    public  void  drawTradeScreen(){

        switch (subState){
            case 0 :trade_select();break;
            case 1: trade_buy();break;
            case 2: trade_selle();break;
        }
        gp.keyH.enterPressed = false;

    }
    public  void  trade_select(){

        npc.dialogueSet = 0;
        drawDialogeScreen();

        //Draw window
        int x = gp.tileSize * 15;
        int y = gp.tileSize * 4;
        int width = gp.tileSize * 3;
        int height = (int) (gp.tileSize * 3.5);
        drawSubWindow(x,y,width,height);

        //draw text
        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString("Buy",x,y);
        if(comandNum == 0){
            g2.drawString(">",x-24,y);
            if(gp.keyH.enterPressed == true){
                subState = 1;
            }
        }

        y += gp.tileSize;
        g2.drawString("Sell",x,y);
        if(comandNum == 1){
            g2.drawString(">",x-24,y);
            if(gp.keyH.enterPressed == true){
                subState = 2;
            }
        }
        y += gp.tileSize;

        g2.drawString("Leave",x,y);
        if(comandNum == 2){
            g2.drawString(">",x-24,y);
            if(gp.keyH.enterPressed == true){
                subState = 0;
                npc.startDialogue(npc,1);
            }
        }

    }
    public  void  drawSleepScreen(){
        counter++;

        if(counter < 120 ){
            gp.eMeneger.lighting.filterAlpha += 0.01f;
            if(gp.eMeneger.lighting.filterAlpha > 1f){
                gp.eMeneger.lighting.filterAlpha = 1f;
            }
        }
        if(counter >= 120){
            gp.eMeneger.lighting.filterAlpha -= 0.01f;
            if(gp.eMeneger.lighting.filterAlpha <= 0) {
                gp.eMeneger.lighting.filterAlpha = 0f;
                counter = 0;
                gp.eMeneger.lighting.dayState = gp.eMeneger.lighting.day;
                gp.eMeneger.lighting.dayCounter = 0;
                gp.gameState = gp.PlayState;
                gp.player.getImage();

            }
        }

    }
    public  void  trade_buy(){
        //draw player inventory
        drawinventory(gp.player,false);
        //draw npc inventory
        drawinventory(npc,true);

        //Draw hit window
        int x = gp.tileSize *2;
        int y = gp.tileSize *9;
        int width = gp.tileSize * 6;
        int height = gp.tileSize * 2;
        drawSubWindow(x,y,width,height);
        g2.drawString("[ESC] Back",x+24,y+60);

        //Draw player coin window
        x = gp.tileSize *12;
        y = gp.tileSize *9;
        width = gp.tileSize * 6;
        height = gp.tileSize * 2;
        drawSubWindow(x,y,width,height);
        g2.drawString("Your Coint: "+gp.player.coin,x+24,y+60);

        //draw prick window
        int itemIndex = getItemIndexOnSlot(npcSlotCol ,npcSlotRow);
        if(itemIndex < npc.inventory.size()){
             x =(int) (gp.tileSize * 5.5);
             y =(int) (gp.tileSize * 5.5);
             width =(int) (gp.tileSize * 2.5);
             height = gp.tileSize ;
             drawSubWindow(x,y,width,height);
             g2.drawImage(coin,x+10,y+8,32,32,null);

             int price  = npc.inventory.get(itemIndex).price;
             String text = ""+price;
             x = getXforAlignToRightText(text,gp.tileSize*8-20);
             g2.drawString(text,x,y+34);

             //Buy an item
            if(gp.keyH.enterPressed == true){
                if(npc.inventory.get(itemIndex).price > gp.player.coin){
                    subState = 0;
                    npc.startDialogue(npc,2);
                }
                else {
                    if(gp.player.canObtainItem(npc.inventory.get(itemIndex)) == true){
                        gp.player.coin -= npc.inventory.get(itemIndex).price;
                    }
                    else {
                        subState = 0;
                        npc.startDialogue(npc,3);
                    }
                }
            }
        }


    }
    public  void  trade_selle(){
        //draw player inventory
        drawinventory(gp.player,true);

        int x;
        int y;
        int width;
        int height;

        //Draw hit window
         x = gp.tileSize *2;
         y = gp.tileSize *9;
         width = gp.tileSize * 6;
         height = gp.tileSize * 2;
        drawSubWindow(x,y,width,height);
        g2.drawString("[ESC] Back",x+24,y+60);

        //Draw player coin window
        x = gp.tileSize *12;
        y = gp.tileSize *9;
        width = gp.tileSize * 6;
        height = gp.tileSize * 2;
        drawSubWindow(x,y,width,height);
        g2.drawString("Your Coin: "+gp.player.coin,x+24,y+60);

        //draw prick window
        int itemIndex = getItemIndexOnSlot(playerSlocCol ,playerSlotRow);
        if(itemIndex < gp.player.inventory.size()){
            x =(int) (gp.tileSize * 15.5);
            y =(int) (gp.tileSize * 5.5);
            width =(int) (gp.tileSize * 2.5);
            height = gp.tileSize ;
            drawSubWindow(x,y,width,height);
            g2.drawImage(coin,x+10,y+8,32,32,null);

            int price  = gp.player.inventory.get(itemIndex).price/2;
            String text = ""+price;
            x = getXforAlignToRightText(text,gp.tileSize*18-20);
            g2.drawString(text,x,y+34);

            //selle an item
            if(gp.keyH.enterPressed == true){

                if(gp.player.inventory.get(itemIndex) == gp.player.currentWeapon ||
                        gp.player.inventory.get(itemIndex) == gp.player.currentShiled){
                    comandNum =0;
                    subState = 0;
                    npc.startDialogue(npc,4);


                }
                else {
                    if(gp.player.inventory.get(itemIndex).amout > 1){
                        gp.player.inventory.get(itemIndex).amout--;
                    }
                    else {
                        gp.player.inventory.remove(itemIndex);
                    }
                    gp.player.coin +=price;
                }

            }
        }


    }
    public  int getItemIndexOnSlot(int slocCol,int slotRow){
        int itemIndex = slocCol + (slotRow * 5);
        return  itemIndex;
    }
    public void drawSubWindow(int x,int y,int width,int height){

        Color c = new Color(0,0,0,150);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);


        c = new Color(227, 227, 227, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10,25,25 );


    }

    public  int  getXforCenterText(String text){
        int lenght =(int) g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidh/2 - lenght/2;
        return  x;
    }
    public  int  getXforAlignToRightText(String text,int tailX){

        int lenght = (int) g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = tailX - lenght;
        return  x;
    }


}
