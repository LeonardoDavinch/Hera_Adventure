package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed,spacePressed;
    public boolean showDebagText = false;
    public  boolean godModeON = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        //tile state menu
        if (gp.gameState == gp.titelState) {
            titleStacte(code);
        }

        //Play game
        else if (gp.gameState == gp.PlayState) {
            playState(code);
        }
        //Pause State
        else if (gp.gameState == gp.PauseState) {
            pauseState(code);

        }

        //dialoge state
        else if (gp.gameState == gp.dialogusState) {
            dialogeState(code);
        }

        //character state
        else if (gp.gameState == gp.charactersState) {
            characterState(code);
        }
        //optionals state
        else if (gp.gameState == gp.optionlaState) {
            optionsState(code);
        }
        //Game over state
        else if (gp.gameState == gp.gameOverState) {
            gameOverState(code);
        }
        //Trade state
        else if (gp.gameState == gp.tradeState) {
            tradeState(code);
        }//Map state
        else if (gp.gameState == gp.mapState) {
            mapState(code);
        }



    }

    public void titleStacte(int code) {

        if (code == KeyEvent.VK_W) {
            gp.ui.comandNum--;
            if (gp.ui.comandNum < 0) {
                gp.ui.comandNum = 2;
            }
        }

        if (code == KeyEvent.VK_S) {
            gp.ui.comandNum++;
            if (gp.ui.comandNum > 2) {
                gp.ui.comandNum = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.comandNum == 0) {
                gp.gameState = gp.PlayState;
                gp.playMusic(0);
            }
            if (gp.ui.comandNum == 1) {
                 gp.saveLoad.load();
                gp.gameState = gp.PlayState;
                gp.playMusic(0);
            }
            if (gp.ui.comandNum == 2) {
                System.exit(0);
            }

        }

    }

    public void playState(int code) {
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.PauseState;
        }
        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.charactersState;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.optionlaState;
        }
        if (code == KeyEvent.VK_M) {
            gp.gameState = gp.mapState;
        }
        if (code == KeyEvent.VK_X) {
            if(gp.map.miniMapOn == false){
                gp.map.miniMapOn = true;
            }
            else {
                gp.map.miniMapOn = false;
            }
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }

        //Debug
        if (code == KeyEvent.VK_T) {
            if (showDebagText == false) {
                showDebagText = true;
            } else if (showDebagText == true) {
                showDebagText = false;
            }
        }

        if (code == KeyEvent.VK_R) {
            switch (gp.currentMap) {
                case 0:
                    gp.titleManeger.loadMap("/maps/worldmap.txt", 0);
                    break;
                case 1:
                    gp.titleManeger.loadMap("/Map/interior01.txt", 1);
                    break;
            }

        }
        //god game
        if (code == KeyEvent.VK_G) {
            if (godModeON == false) {
                godModeON = true;
            } else if (godModeON == true) {
                godModeON = false;
            }
        }
    }
    public  void  mapState(int code){

        if(code == KeyEvent.VK_M){
            gp.gameState = gp.PlayState;

        }
    }

    public void pauseState(int code) {
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.PlayState;
        }
    }

    public void dialogeState(int code) {
        if (code == KeyEvent.VK_ENTER) {
           enterPressed = true;
        }
    }

    public void characterState(int code) {
        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.PlayState;
        }
        if (code == KeyEvent.VK_ENTER) {
            gp.player.selectItem();
        }
        playerInventory(code);
    }

    public void optionsState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.PlayState;
        }

        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        int maxComanNum = 0;
        switch (gp.ui.subState) {
            case 0:
                maxComanNum = 5;
                break;
            case 3:
                maxComanNum = 1;
                break;

        }

        if (code == KeyEvent.VK_W) {
            gp.ui.comandNum--;
            gp.playSE(9);
            if (gp.ui.comandNum < 0) {
                gp.ui.comandNum = maxComanNum;
            }
        }

        if (code == KeyEvent.VK_S) {
            gp.ui.comandNum++;
            gp.playSE(9);
            if (gp.ui.comandNum > maxComanNum) {
                gp.ui.comandNum = 0;
            }
        }

        if (code == KeyEvent.VK_A) {
            if (gp.ui.subState == 0) {
                if (gp.ui.comandNum == 1 && gp.music.volumScale > 0) {
                    gp.music.volumScale--;
                    gp.music.checkVolum();
                    gp.playSE(9);
                }
                if (gp.ui.comandNum == 2 && gp.se.volumScale > 0) {
                    gp.se.volumScale--;
                    gp.playSE(9);
                }
            }
        }

        if (code == KeyEvent.VK_D) {
            if (gp.ui.subState == 0) {
                if (gp.ui.comandNum == 1 && gp.music.volumScale < 5) {
                    gp.music.volumScale++;
                    gp.music.checkVolum();
                    gp.playSE(9);
                }
                if (gp.ui.comandNum == 2 && gp.se.volumScale < 5) {
                    gp.se.volumScale++;
                    gp.playSE(9);
                }
            }
        }

    }

    public void gameOverState(int code) {

        if (code == KeyEvent.VK_W) {
            gp.ui.comandNum--;
            if (gp.ui.comandNum < 0) {
                gp.ui.comandNum = 1;
            }
            gp.playSE(9);
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.comandNum++;
            if (gp.ui.comandNum > 1) {
                gp.ui.comandNum = 0;
            }
            gp.playSE(9);
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.comandNum == 0) {
                gp.gameState = gp.PlayState;
                gp.resetGame(false);
                gp.playMusic(0);

            } else if (gp.ui.comandNum == 1) {
                gp.gameState = gp.titelState;
                gp.resetGame(true);
            }
        }
    }

    public void tradeState(int code) {

        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (gp.ui.subState == 0) {
            if (code == KeyEvent.VK_W) {
                gp.ui.comandNum--;
                if (gp.ui.comandNum < 0) {
                    gp.ui.comandNum = 2;
                }
                gp.playSE(9);
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.comandNum++;
                if (gp.ui.comandNum > 2) {
                    gp.ui.comandNum = 0;
                }
                gp.playSE(9);
            }
        }
        if(gp.ui.subState == 1){
            npcInventory(code);
            if(code == KeyEvent.VK_ESCAPE){
                gp.ui.subState =0;
            }
        }
        if(gp.ui.subState == 2){
            playerInventory(code);
            if(code == KeyEvent.VK_ESCAPE){
                gp.ui.subState =0;
            }
        }
    }


    public  void  playerInventory(int code){

        if(code == KeyEvent.VK_W){
            if(gp.ui.playerSlotRow != 0) {
                gp.ui.playerSlotRow--;
                gp.playSE(9);
            }
        }
        if(code == KeyEvent.VK_A){
            if(gp.ui.playerSlocCol !=0) {
                gp.ui.playerSlocCol--;
                gp.playSE(9);
            }
        }
        if(code == KeyEvent.VK_S){
            if(gp.ui.playerSlotRow != 3) {
                gp.ui.playerSlotRow++;
                gp.playSE(9);
            }
        }
        if(code == KeyEvent.VK_D){
            if(gp.ui.playerSlocCol != 4) {
                gp.ui.playerSlocCol++;
                gp.playSE(9);
            }
        }
    }
    public  void  npcInventory(int code){

        if(code == KeyEvent.VK_W){
            if(gp.ui.npcSlotRow != 0) {
                gp.ui.npcSlotRow--;
                gp.playSE(9);
            }
        }
        if(code == KeyEvent.VK_A){
            if(gp.ui.npcSlotCol !=0) {
                gp.ui.npcSlotCol--;
                gp.playSE(9);
            }
        }
        if(code == KeyEvent.VK_S){
            if(gp.ui.npcSlotRow != 3) {
                gp.ui.npcSlotRow++;
                gp.playSE(9);
            }
        }
        if(code == KeyEvent.VK_D){
            if(gp.ui.npcSlotCol != 4) {
                gp.ui.npcSlotCol++;
                gp.playSE(9);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_F){
            shotKeyPressed = false;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = false;
        }
        if(code == KeyEvent.VK_SPACE){
            spacePressed = false;
        }

    }
}
