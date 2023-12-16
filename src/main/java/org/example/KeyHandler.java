package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;

    public  boolean upPressed, downPressed,leftPressed,rightPressed, enterPressed,shotKeyPressed;
    public  boolean showDebagText = false;

    public  KeyHandler(GamePanel gp){
        this.gp=gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        //tile state menu
        if(gp.gameState == gp.titelState){
            titleStacte(code);
        }

        //Play game
       else if(gp.gameState == gp.PlayState){
             playState(code);
        }
        //Pause State
        else if(gp.gameState == gp.PauseState){
            pauseState(code);

        }

        //dialoge state
       else  if(gp.gameState == gp.dialogusState){
            dialogeState(code);
        }

       //character state
        else if (gp.gameState ==gp.charactersState) {
            characterState(code);
        }


    }

    public  void  titleStacte (int code){

            if(code == KeyEvent.VK_W){
                gp.ui.comandNum--;
                if(gp.ui.comandNum < 0){
                    gp.ui.comandNum = 2;
                }
            }

            if(code == KeyEvent.VK_S){
                gp.ui.comandNum++;
                if(gp.ui.comandNum > 2){
                    gp.ui.comandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.comandNum == 0){
                gp.gameState = gp.PlayState;
                }
                if(gp.ui.comandNum == 1){
                    //add later
                }
                if(gp.ui.comandNum == 2){
                    System.exit(0);
                }

            }

    }
    public  void  playState(int code){
        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.PauseState;
        }
        if(code == KeyEvent.VK_C){
            gp.gameState = gp.charactersState;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed =true;
        }
        if(code == KeyEvent.VK_ESCAPE){
            System.exit(1);
        }
        if(code == KeyEvent.VK_F){
            shotKeyPressed = true;
        }

        //Debug
        if(code == KeyEvent.VK_T){
            if(showDebagText == false){
                showDebagText = true;
            }
            else if (showDebagText == true) {
                showDebagText = false;
            }
        }
        if(code == KeyEvent.VK_R) {
            gp.titleManeger.loadMap("/Map/worldV2.txt");
        }
    }

    public  void  pauseState(int code){
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.PlayState;
        }
    }
    public  void  dialogeState(int code){
        if(code == KeyEvent.VK_ENTER){
            gp.gameState = gp.PlayState;
        }
    }
    public  void  characterState(int code){
        if(code == KeyEvent.VK_C){
                gp.gameState = gp.PlayState;
        }
        if(code == KeyEvent.VK_W){
            if(gp.ui.slotRow != 0) {
                gp.ui.slotRow--;
                gp.playSE(9);
            }
        }
        if(code == KeyEvent.VK_A){
            if(gp.ui.slotCol !=0) {
                gp.ui.slotCol--;
                gp.playSE(9);
            }
        }
        if(code == KeyEvent.VK_S){
            if(gp.ui.slotRow != 3) {
                gp.ui.slotRow++;
                gp.playSE(9);
            }
        }
        if(code == KeyEvent.VK_D){
            if(gp.ui.slotCol != 4) {
                gp.ui.slotCol++;
                gp.playSE(9);
            }
        }
        if(code == KeyEvent.VK_ENTER){
            gp.player.selectItem();
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

    }
}
