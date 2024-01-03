package org.example.tille_interactive;

import org.example.GamePanel;

public class IT_MetalPlate extends  InteractiveTille{
    GamePanel gp;
    public  static  final  String itName ="Metal plate";
    public IT_MetalPlate(GamePanel gp,int col , int row) {
        super(gp,col,row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        name = itName;
        down1 = setup("/tiles interactiv/metalplate",gp.tileSize,gp.tileSize);

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaulX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
}
