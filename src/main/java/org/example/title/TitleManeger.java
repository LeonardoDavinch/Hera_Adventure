package org.example.title;

import org.example.GamePanel;
import org.example.UtiltyTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TitleManeger {
    GamePanel gp;
    public  Tile []tiles;

    public int mapTitelNumber[][][];
    public  boolean drawPath = true;
    ArrayList<String> fileNames = new ArrayList<>();
    ArrayList<String>collisionStatus = new ArrayList<>();
    public  TitleManeger(GamePanel gp){
        this.gp=gp;

        //read tile data file
        InputStream is = getClass().getResourceAsStream("/maps/tiledata.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;

        try {
            while ((line = br.readLine()) != null) {
                fileNames.add(line);
                collisionStatus.add(br.readLine());

            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        tiles=new Tile[fileNames.size()];
        getTitelImage();

        is = getClass().getResourceAsStream("/maps/worldmap.txt");
        br = new BufferedReader(new InputStreamReader(is));

        try{
            String line2 = br.readLine();
            String maxTile[]=line2.split(" ");

            gp.maxWorldCol = maxTile.length;
            gp.maxWorldRow = maxTile.length;
            mapTitelNumber = new int [gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

            br.close();
        }catch (IOException o){
            o.printStackTrace();
        }
        loadMap("/maps/worldmap.txt",0);
        loadMap("/maps/indoor01.txt",1);
        loadMap("/maps/dungeon01.txt",2);
        loadMap("/maps/dungeon02.txt",3);


    }

    public  void  loadMap(String filePath , int map){
        try{
            InputStream is=getClass().getResourceAsStream(filePath);
            BufferedReader br=new BufferedReader(new InputStreamReader(is));

            int cool=0;
            int row=0;

            while (cool < gp.maxWorldCol && row < gp.maxWorldRow){

                String line = br.readLine();

                while (cool < gp.maxWorldCol){

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[cool]);

                    mapTitelNumber[map][cool][row] = num;
                    cool++;
                }
                if(cool == gp.maxWorldCol){
                    cool = 0;
                    row ++;
                }
            }
            br.close();

        }catch (Exception o){
            o.printStackTrace();
        }
    }
    public  void  getTitelImage(){
        for (int i = 0; i < fileNames.size() ; i++) {

            String fileName;
            boolean collisions;

            fileName = fileNames.get(i);

            if(collisionStatus.get(i).equals("true")){
                collisions = true;
            }
            else {
                collisions = false;
            }
            setup(i,fileName,collisions);
        }



    }
    public  void  setup(int index,String ImageName,boolean collision){

        UtiltyTool uTool = new UtiltyTool();
        try {
            tiles[index] = new Tile();
            tiles[index].image = ImageIO.read(getClass().getResourceAsStream("/tile/"+ImageName));
            tiles[index].image = uTool.scaleImage(tiles[index].image,gp.tileSize,gp.tileSize);
            tiles[index].collision = collision;

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public  void  draw(Graphics2D g2){

       int WorldCool = 0;
       int WorldRow = 0;


       while (WorldCool < gp.maxWorldCol && WorldRow < gp.maxWorldRow){

           int titelNum = mapTitelNumber[gp.currentMap][WorldCool][WorldRow];

           int worldX = WorldCool * gp.tileSize;
           int worldY =  WorldRow * gp.tileSize;
           int screenX =worldX - gp.player.worldX +gp.player.screenX;
           int screenY = worldY -gp.player.worldY +gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                    && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                    && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                    && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

                g2.drawImage(tiles[titelNum].image,screenX,screenY,gp.tileSize,gp.tileSize,null);

            }
           WorldCool++;

           if(WorldCool == gp.maxWorldCol){
               WorldCool = 0;
               WorldRow ++;
           }
       }
       if(drawPath == true){
           g2.setColor(new Color(255,0,0,70));

           for (int i = 0; i < gp.pFinder.pathList.size(); i++) {
               int worldX = gp.pFinder.pathList.get(i).col * gp.tileSize;
               int worldY = gp.pFinder.pathList.get(i).row * gp.tileSize;;
               int screenX =worldX - gp.player.worldX +gp.player.screenX;
               int screenY = worldY -gp.player.worldY +gp.player.screenY;

               g2.fillRect(screenX,screenY,gp.tileSize,gp.tileSize);
           }
       }

    }

}
