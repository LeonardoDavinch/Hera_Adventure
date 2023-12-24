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

public class TitleManeger {
    GamePanel gp;
    public  Tile []tiles;

    public int mapTitelNumber[][][];
    public  boolean drawPath = true;
    public  TitleManeger(GamePanel gp){
        this.gp=gp;

        tiles=new Tile[50];
        mapTitelNumber = new int [gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        getTitelImage();
        loadMap("/Map/worldV2.txt",0);
        loadMap("/Map/interior01.txt",1);
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
           setup(0,"grass00",false);
           setup(1,"grass00",false);
           setup(2,"grass00",false);
           setup(3,"grass00",false);
           setup(4,"grass00",false);
           setup(5,"grass00",false);
           setup(6,"grass00",false);
           setup(7,"grass00",false);
           setup(8,"grass00",false);
           setup(9,"grass00",false);

           setup(10,"grass00",false);
           setup(11,"grass01",false);
           setup(12,"water00",true);
           setup(13,"water01",true);
           setup(14,"water02",true);
           setup(15,"water03",true);
           setup(16,"water04",true);
           setup(17,"water05",true);
           setup(18,"water06",true);
           setup(19,"water07",true);
           setup(20,"water08",true);
           setup(21,"water09",true);
           setup(22,"water10",true);
           setup(23,"water11",true);
           setup(24,"water12",true);
           setup(25,"water13",true);

           setup(26,"road00",false);
           setup(27,"road01",false);
           setup(28,"road02",false);
           setup(29,"road03",false);
           setup(30,"road04",false);
           setup(31,"road05",false);
           setup(32,"road06",false);
           setup(33,"road07",false);
           setup(34,"road08",false);
           setup(35,"road09",false);
           setup(36,"road10",false);
           setup(37,"road11",false);
           setup(38,"road12",false);

           setup(39,"earth",false);
           setup(40,"wall",true);
           setup(41,"tree",true);


            setup(42,"hut",false);
            setup(43,"floor01",false);
            setup(44,"table01",true);



    }
    public  void  setup(int index,String imagePath,boolean collision){

        UtiltyTool uTool = new UtiltyTool();
        try {
            tiles[index] = new Tile();
            tiles[index].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/"+imagePath+".png"));
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
