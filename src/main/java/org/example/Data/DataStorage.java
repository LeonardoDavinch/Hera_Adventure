package org.example.Data;

import org.example.enty.Entyti;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable {

    //Player state
    int level;
    int maxLife;
    int life;
    int maxMana;
    int mana;
    int strenght;
    int dexterity;
    int exp;
    int nextLevelExp;
    int coin;

    //Player inventory
    ArrayList<String> itemName = new ArrayList<>();
    ArrayList<Integer> itemAmout = new ArrayList<>();
    int currentWaponSlot ;
    int currentShildSlot;

    String mapObjecName[][];
    int mapObjectWorldX[][];
    int mapObjectWorldY[][];
    String mapOnjectLootName [][];
    boolean mapObjectOpened[][];




}
