package org.example.ai;

import java.util.Objects;

public class Node {
    Node parent;
    public  int col;
    public  int row;
      int gCost;
      int hCost;
      int fCost;
      boolean solid;
      boolean open;
      boolean cheked;

      public  Node (int col, int row){
          this.col = col;
          this.row = row;


      }

}
