package org.example.enty;

import org.example.GamePanel;

public class Projectile extends  Entyti{
    Entyti user;
    public Projectile(GamePanel gp) {
        super(gp);
    }

    public  void  set(int worldX, int worldY,String direction,boolean alive,Entyti user){
            this.worldX = worldX;
            this.worldY = worldY;
            this.directory = direction;
            this.alive =alive;
            this.user = user;
            this.life =  this.maxLife;
    }
    public  void  update(){

        if(user == gp.player){
            int monsterIndex = gp.oChecker.checkEntity(this,gp.monster);
            if(monsterIndex != 999){
                gp.player.damageMonster(monsterIndex,attack);
                generateParticle(user.projectile,gp.monster[gp.currentMap][monsterIndex]);
                alive=false;
            }

        }
        if(user != gp.player){
            boolean contactPlayer = gp.oChecker.checkPlayer(this);

            if(gp.player.invicible == false && contactPlayer == true){
                damagePlayer(attack);
                generateParticle(user.projectile,gp.player);
                alive = false;
            }
        }
        switch (directory){
            case "up":worldY -= speed;break;
            case "down":worldY += speed;break;
            case "left":worldX -= speed;break;
            case "right":worldX += speed;break;
        }
        life--;
        if(life <= 0 ){
            alive = false;
        }

        spritCounter++;
        if(spritCounter > 12){
            if(sprintNum == 1){
                sprintNum = 2;
            }
            else if (sprintNum == 2) {
                sprintNum = 1;
            }
            spritCounter = 0;
        }

    }
    public  boolean haveResourse(Entyti user){

        boolean haveResourse = false;
        return haveResourse;
    }
    public  void  subtracktResourse(Entyti user){
    }
}
