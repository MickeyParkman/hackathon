package maze;

import java.awt.Graphics;
import java.awt.Color;

import maze.tiles.*;

public class Map implements Runnable
{
   private static final int WIDTH = 20;
   private static int height = 0;
   private static Tile[][] tiles;
   private static final int MIN_WALLS = 2;
   private boolean creating;
   
   public Map(int windowWidth, int windowHeight)
   {
      //the tile size depends on the number of tiles desired width wise and the width of the screen
      int tileSize = windowWidth / WIDTH;
      Tile.setTileSize(tileSize);
      //the number of tiles height wise depends on the size of the tiles and the height of the window
      height = windowHeight / tileSize;
      //initialize all the tiles in the window
      tiles = new Tile[WIDTH][height];
      for(int i = 0; i < tiles.length; i++){
         for(int j = 0; j < tiles[i].length; j++){
            tiles[i][j] = new Tile();
         }
      }
      creating = true;
   }
   
   //the creation happens in the run method so that it can "sleep" between collapsing walls
   //so that the user can see the walls being collapsed
   public void run()
   {
      DisjSets ds = new DisjSets(WIDTH, height);
      int arrSize = WIDTH * height; // size of array is dimension squared
      try
      {
         while (ds.find(0) != ds.find(arrSize - 1)) { // while entrance and exit are not in same set
            int tileNum = (int) (Math.random() * arrSize);
            int wall = (int) (Math.random() * 4); 
            //try to collapse the randomly selected wall
            collapseWall(tileNum, wall, ds);            
            Thread.sleep(2);
         } 
         //the map is now finished and ready for play
         creating = false; 
      } catch(InterruptedException e) {
         e.printStackTrace();
      }   
   }
   
   public boolean isCreating() { return creating; }  
   public Tile[][] getTiles(){ return tiles; } 
   
   public void collapseWall(int tileNum, int wallNum, DisjSets ds){   
      int tileY = (int)(tileNum / WIDTH); // finds the x coordinate
      int tileX = tileNum % WIDTH; // finds the y coordinate
      if(wallNum == Tile.TOP){
         //if the wall to be collapsed is the top wall we need to union the tile above the selected tile
         if(tileY - 1 > -1 && tileY < height && (tileX) > -1 && (tileX) < WIDTH){
            //the union returns false if the two tiles are of the same set            
            if(ds.union(WIDTH * (tileY - 1) + tileX, tileNum)){
               //collapse the top wall of this tile
               tiles[tileX][tileY].walls[Tile.TOP] = false;   
               //collapse the bottom wall of the tile above
               tiles[tileX][tileY-1].walls[Tile.BOTTOM] = false;
            }
         }
      }else if(wallNum == Tile.RIGHT){
         if(tileY > -1 && (tileY)< height && tileX > -1 && tileX + 1 < WIDTH){
            if(ds.union(WIDTH * tileY + tileX + 1, tileNum)){
               tiles[tileX][tileY].walls[Tile.RIGHT] = false; 
               tiles[tileX + 1][(tileY)].walls[Tile.LEFT] = false;
            }
         }            
      }else if(wallNum == Tile.BOTTOM){
         if(tileY > -1 && tileY + 1 < height && (tileX) > -1 && (tileX) < WIDTH){
            if(ds.union(WIDTH * (tileY + 1) + tileX, tileNum)){
               tiles[tileX][tileY].walls[Tile.BOTTOM] = false;      
               tiles[tileX][tileY + 1].walls[Tile.TOP] = false;
            }
         }
      }else if(wallNum == Tile.LEFT){
         if((tileY) > -1 && (tileY) < height && tileX - 1 > -1 && tileX - 1 < WIDTH){
            if(ds.union(WIDTH * tileY + tileX - 1, tileNum)){
               tiles[tileX][tileY].walls[Tile.LEFT] = false;      
               tiles[tileX - 1][(tileY)].walls[Tile.RIGHT] = false;
            }
         }
      }   
   }
   
   public void paint(Graphics g)
   {     
      //set the color of the walls
      g.setColor(Color.WHITE);
      for(int i = 0; i < tiles.length; i++){
         for(int j = 0; j < tiles[i].length; j++){
            tiles[i][j].paint(g, i, j);
            //draw all of the walls of the current tile                         
         }
      }
   }
}