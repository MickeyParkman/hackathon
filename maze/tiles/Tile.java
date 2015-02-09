package maze.tiles;

import java.awt.*;

public class Tile
{
   public boolean[] walls;
   public int numWalls;
   public static int tileSize;
   private static int wallSize;
   public static final int TOP = 0, RIGHT = 1, BOTTOM = 2, LEFT = 3;
   
   public Tile() {
      numWalls = 4;
      walls = new boolean[4];
      for(int i = 0; i < walls.length; i++){
         walls[i] = true;
      }
   }
   
   public static void setTileSize(int size)
   {
      tileSize = size;
      wallSize = size / 8;
   }
   
   public void paint(Graphics g, int x, int y)
   {
      if(walls[Tile.TOP]){
         g.fillRect(tileSize * x, tileSize * y, tileSize, wallSize);
      }
      if(walls[Tile.RIGHT]){
         g.fillRect(tileSize * x + tileSize - wallSize, tileSize * y, wallSize, tileSize);
      }
      if(walls[Tile.BOTTOM]){
         g.fillRect(tileSize * x, tileSize * y + tileSize - wallSize, tileSize, wallSize);
      }
      if(walls[Tile.LEFT]){
         g.fillRect(tileSize * x, tileSize * y, wallSize, tileSize);
      }
   }
}