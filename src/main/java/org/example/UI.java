package org.example;

import org.example.enty.Entyti;
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
    public  BufferedImage heart_full,heart_half,heart_blank,crystal_full,crystal_blanck;
    public  boolean messageON = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public  boolean gameFinished = false;
    public  String currentdialogue = "";

    public  int comandNum = 0;
    public  int titelScreanState = 0;
    public   int slotCol = 0;
    public  int slotRow = 0;
    public  int subState = 0;




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
                 drawMesage();
        }
        //pause state
        if(gp.gameState == gp.PauseState){
            drawPlayerLife();
            drawPauseScrean();
        }
        //Dialoge state
        if(gp.gameState == gp.dialogusState){
            drawPlayerLife();
            drawDialogeScreen();
        }
        //character state
        if(gp.gameState == gp.charactersState){
            drawCharacterScrean();
            drawinventory();
        }
        //options state
        if(gp.gameState == gp.optionlaState){
            drawOptionsScrean();
        }


    }

    public  void  drawPlayerLife(){

        int x = gp.tileSize /2 ;
        int y = gp.tileSize /2;
        int i = 0;

        //Draw max hearth
        while (i < gp.player.maxLife/2){
            g2.drawImage(heart_blank,x,y,null);
            i++;
            x += gp.tileSize;
        }

        //Reset
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        //Draw current Life
        while (i < gp.player.life){
            g2.drawImage(heart_half,x,y,null);
            i++;
            if (i < gp.player.life){
                g2.drawImage(heart_full,x,y,null);
            }
            i++;
            x += gp.tileSize;
        }

        //draw max mana
        x = (gp.tileSize/2)-5;
        y = (int) (gp.tileSize * 1.5);
        i = 0;
        while (i < gp.player.maxMana){
            g2.drawImage(crystal_blanck,x,y,null);
            i++;
            x +=35;

        }
        //draw mana
        x = (gp.tileSize/2)-5;
        y = (int) (gp.tileSize * 1.5);
        i = 0;
        while (i < gp.player.mana){
            g2.drawImage(crystal_full,x,y,null);
            i++;
            x += 35;

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


            //Backgraund
        /*    g2.setColor(new Color(5, 5, 5));
            g2.fillRect(0, 0, gp.screenWidh, gp.screenHeight);*/
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
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidh - (gp.tileSize * 4);
        int heidht = gp.tileSize * 4;

        drawSubWindow(x, y, width, heidht);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));//marumonik
        x += gp.tileSize;
        y += gp.tileSize;

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
    public  void  drawinventory(){
        int frameX = gp.tileSize *13;
        int frameY = gp.tileSize;
        int frameWeight = gp.tileSize * 6;
        int frameHeight = gp.tileSize * 5;
        drawSubWindow(frameX,frameY,frameWeight,frameHeight);

        //slot
        final  int slotXstart = frameX + 20;
        final  int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;


        //draw players items
        for (int i = 0; i < gp.player.inventory.size(); i++) {

            //Equip cursor
            if(gp.player.inventory.get(i) == gp.player.currentWeapon||
            gp.player.inventory.get(i) == gp.player.currentShiled){
                g2.setColor(new Color(140, 131, 131));
                g2.fillRoundRect(slotX,slotY,gp.tileSize,gp.tileSize,10,10 );
            }

            g2.drawImage(gp.player.inventory.get(i).down1,slotX,slotY,null);

            slotX +=slotSize    ;

            if( i == 4 || i == 9 || i == 14){
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        //Cursor
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
        /*drawSubWindow(dFrameX,dFrameY,dFrameWeight,dFrameHeight);*/

        //draw descripton text
        int textX = dFrameX + 20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(28F));


        int itemIndex  = getItemIndexOnSlot();

        if(itemIndex <gp.player.inventory.size() ){
            drawSubWindow(dFrameX,dFrameY,dFrameWeight,dFrameHeight);

            for(String line : gp.player.inventory.get(itemIndex).description.split("\n")){

                g2.drawString(line,textX,textY );
                textY += 32;
            }

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
                subState =0;
                gp.gameState = gp.titelState;
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

    public  int getItemIndexOnSlot(){
        int itemIndex = slotCol + (slotRow * 5);
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
