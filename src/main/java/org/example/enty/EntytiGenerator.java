package org.example.enty;

import org.example.GamePanel;
import org.example.object.*;
import org.example.object.Armor.OBJ_Shield_Blue;
import org.example.object.Armor.OBJ_Shield_Wood;
import org.example.object.Armor.OBJ_Sword_Normal;
import org.example.object.Currency.OBJ_Coin_Bronze;
import org.example.object.Magic.OBJ_Fireball;
import org.example.object.Magic.OBJ_Slime_Bool;

public class EntytiGenerator {

    GamePanel gp;

    public  EntytiGenerator(GamePanel gp){
        this.gp = gp;

    }
    public Entyti getObject (String itemName){
        Entyti obj = null ;

        switch (itemName){
            case OBJ_Axe.onjName:obj = new OBJ_Axe(gp);break;
            case OBJ_Boots.onjName:obj = new OBJ_Boots(gp);break;
            case OBJ_Chest.onjName:obj = new OBJ_Chest(gp);break;
            case OBJ_Coin_Bronze.onjName:obj =new OBJ_Coin_Bronze(gp);break;
            case OBJ_Door.onjName:obj = new OBJ_Door(gp);break;
            case OBJ_Fireball.onjName:obj =new OBJ_Fireball(gp);break;
            case OBJ_Heart.onjName:obj =new OBJ_Heart(gp);break;
            case OBJ_Key.onjName:obj = new OBJ_Key(gp);break;
            case OBJ_Lantern.onjName:obj =new OBJ_Lantern(gp);break;
            case OBJ_ManaCrystal.onjName:obj = new OBJ_ManaCrystal(gp);break;
            case OBJ_Potion_Red.onjName:obj = new OBJ_Potion_Red(gp);break;
            case OBJ_Slime_Bool.onjName:obj =new OBJ_Slime_Bool(gp);break;
            case OBJ_Shield_Blue.onjName:obj = new OBJ_Shield_Blue(gp);break;
            case OBJ_Shield_Wood.onjName:obj = new OBJ_Shield_Wood(gp);break;
            case OBJ_Sword_Normal.onjName:obj = new OBJ_Sword_Normal(gp);break;
            case OBJ_Tent.onjName:obj = new OBJ_Tent(gp);break;
        }
        return obj;
    }
}
