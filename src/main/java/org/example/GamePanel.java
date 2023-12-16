package org.example;

import org.example.enty.Entyti;
import org.example.enty.Player;
import org.example.tille_interactive.InteractiveTille;
import org.example.title.TitleManeger;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static org.example.App.window;

public class GamePanel extends JPanel implements  Runnable{
    //Screan Setings
      final  int originalTitleSize=16;
     final  int scale = 3;

     public final int tileSize=originalTitleSize * scale;// 48x48 title
     public final  int maxScreenCol =20;
     public final  int maxScreenRow =12;
     public final  int screenWidh =tileSize * maxScreenCol; // 768 pixel
     public final  int screenHeight =tileSize * maxScreenRow;// 576 pixel
    //World setting
    public  final  int maxWorldCol = 50;
    public  final  int maxWorldRow =50;
    //for full screan
      int screanWidh2 = screenWidh;
      int screanHeight2 = screenHeight;
     BufferedImage tempScreen;

     Graphics2D g2;

    //FPS
    int FPS =60;

    //System
    TitleManeger titleManeger=new TitleManeger(this);
    public KeyHandler keyH = new KeyHandler(this);
    public  Sound music =new Sound();
    public  Sound se =new Sound();
    public  UI ui =new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread  gameThread;
    public  CollisionChecker oChecker = new CollisionChecker(this);
    public  AsserSetter aSetter = new AsserSetter(this);

    //Entitty object
    public Player player = new Player(this,keyH);
    public Entyti obj[] = new Entyti[20];
    public Entyti npc[] = new Entyti[10];
    public  Entyti monster[] = new Entyti[20];
    public InteractiveTille iTile[] = new InteractiveTille[50];
    public ArrayList<Entyti> projectList = new ArrayList<>();
    public  ArrayList<Entyti> particleList = new ArrayList<>();
    public ArrayList<Entyti> entytiList = new ArrayList<>();




    //Game State
    public  int gameState;
    public  final  int  titelState = 0;
    public  final  int PlayState = 1 ;
    public  final  int PauseState = 2;
    public  final  int dialogusState = 3;
    public  final  int charactersState =4;



    public  GamePanel (){
        this.setPreferredSize(new Dimension(screenWidh,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public  void  setupGame(){

       aSetter.setObject_();
       aSetter.setNPC();
       aSetter.setMonster();
       aSetter.setInteractiveTile();
        playMusic(0);
       gameState = titelState;

        tempScreen = new BufferedImage(screenWidh, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();


    }
    public  void  setFullScreen(){
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gd = ge.getDefaultScreenDevice();
            gd.setFullScreenWindow(window);
        screanWidh2 = window.getWidth();
        screanHeight2 = window.getHeight();
    }

    public  void  startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();

    }

    public  void run(){

        double drawInterval =  1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null ){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                drawToTempScreen();
                drawToScreen();
                delta --;
                drawCount++;
            }
            if(timer >= 1000000000){
                drawCount =0;
                timer =0;
            }
        }
    }

    public  void  update(){

        if(gameState == PlayState){
            //Player
            player.update();
            //Npc
            for (int i = 0; i < npc.length; i++) {
                if(npc[i] != null){
                    npc[i].update();
                }
            }
            //monster
            for (int i = 0; i <monster.length ; i++) {
                if( monster[i] != null){
                    if(monster[i].alive == true && monster[i].dyling == false){
                        monster[i].update();
                    }
                    if(monster[i].alive == false){
                        monster[i].checkDrop();
                        monster[i] = null;
                    }

                }
            }
            for (int i = 0; i <projectList.size() ; i++) {
                if( projectList.get(i) != null){
                    if(projectList.get(i).alive == true ){
                        projectList.get(i).update();
                    }
                    if(projectList.get(i).alive == false){
                        projectList.remove(i);
                    }
                }
            }
            for (int i = 0; i <particleList.size() ; i++) {
                if( particleList.get(i) != null){
                    if(particleList.get(i).alive == true ){
                        particleList.get(i).update();
                    }
                    if(particleList.get(i).alive == false){
                        particleList.remove(i);
                    }
                }
            }
            //tree list
            for(int  i= 0; i < iTile.length ; i++) {
                if(iTile[i] != null){
                    iTile[i].update();
                }
            }
        }
        if(gameState == PauseState){
         //nothing
        }
    }

    public  void drawToTempScreen(){

        //Debug
        long drawStart = 0;
        if(keyH.showDebagText == true){
            drawStart = System.nanoTime();
        }

        //Titel screan
        if (gameState == titelState) {
            ui.draw(g2);
        }
        //Otheer
        else {

            //Tile
            titleManeger.draw(g2);

            //interactive tille
            for (int i = 0; i < iTile.length; i++) {
                if(iTile[i] != null){
                    iTile[i].draw(g2);
                }
            }

            //add entity to list
            entytiList.add(player);

            for (int i = 0; i <npc.length ; i++) {
                if(npc[i] != null){
                    entytiList.add(npc[i]);
                }
            }

            for (int i = 0; i <obj.length ; i++) {
                if(obj[i] !=null){
                    entytiList.add(obj[i]);
                }
            }

            for (int i = 0; i <monster.length ; i++) {
                if(monster[i] != null){
                    entytiList.add(monster[i]);
                }
            }
            for (int i = 0; i <projectList.size() ; i++) {
                if(projectList.get(i) != null){
                    entytiList.add(projectList.get(i));
                }
            }
            for (int i = 0; i < particleList.size() ; i++) {
                if(particleList.get(i) != null){
                    entytiList.add(particleList.get(i));
                }
            }

            //Sort
            Collections.sort(entytiList, new Comparator<Entyti>() {
                @Override
                public int compare(Entyti e1, Entyti e2) {

                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });

            //Draw Entyti
            for (int i = 0; i <entytiList.size() ; i++) {
                entytiList.get(i).draw(g2);
            }
            //Empty list
            entytiList.clear();

            //Ui
            ui.draw(g2);
        }

        //Debug
        if(keyH.showDebagText == true){
            long drawEnd = System.nanoTime();
            long  passed = drawEnd - drawStart;

            g2.setFont(new Font("Arial",Font.PLAIN,20));
            g2.setColor(Color.white);
            int x = 10;
            int y = 400;
            int lineHeight = 20;

            g2.drawString("WorldX"+player.worldX,x,y);y += lineHeight;
            g2.drawString("WorldY"+player.worldY,x,y);y += lineHeight;
            g2.drawString("Col"+(player.worldX + player.solidArea.x)/tileSize,x,y);y += lineHeight;
            g2.drawString("Row"+(player.worldY + player.solidArea.y)/tileSize,x,y);y += lineHeight;
            g2.drawString("Draw Time: "+passed,x,y);

        }

    }
    public  void drawToScreen(){

        Graphics g = getGraphics();
        if (g != null) {
            g.drawImage(tempScreen, 0, 0, screanWidh2, screanHeight2, null);
            Toolkit.getDefaultToolkit().sync(); // Для уникнення проблеми з подвійним буфером
            g.dispose();
        }
    }

    public  void  playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public  void  stopMusic(){
        music.stop();
    }
    public  void  playSE(int i){
        se.setFile(i);
        se.play();
    }
}
