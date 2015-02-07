import java.awt.*;
import javax.swing.JPanel;

public class Game extends JPanel implements Runnable
{
   private boolean running = true;
   private static final int WINDOW_SIZE = 500;
   private static final int WIDTH = 20;
   private static final int BORDER = 2;
   private static Tile[][] tiles;
   private final int tileSize, wallSize;
   private static final int MAX_WALLS = 1;
   
   public Game()
   {
      setPreferredSize(new Dimension(WINDOW_SIZE, WINDOW_SIZE));
      tiles = new Tile[WIDTH][WIDTH];
      for(int i = 0; i < tiles.length; i++){
         for(int j = 0; j < tiles[i].length; j++){
            tiles[i][j] = new Tile();
         }
      }
      tileSize = WINDOW_SIZE / WIDTH;
      wallSize = tileSize / 8;
      new Thread(this).start();
   }
   
   public void paint(Graphics g)
   {
      g.setColor(Color.BLACK);
      g.fillRect(0, 0, WINDOW_SIZE, WINDOW_SIZE);
      
      g.setColor(Color.WHITE);
      for(int i = 0; i < tiles.length; i++){
         for(int j = 0; j < tiles[i].length; j++){
            Tile temp = tiles[i][j];
            if(temp.walls[Tile.TOP]){
               //System.out.println("grid number: " + i + ", " + j + temp.walls[Tile.TOP]);
               g.fillRect(tileSize * i, tileSize * j, tileSize, wallSize);
            }
            if(temp.walls[Tile.RIGHT]){
                           //System.out.println("grid number: " + i + ", " + j + temp.walls[Tile.RIGHT]);

               g.fillRect(tileSize * i + tileSize - wallSize, tileSize * j, wallSize, tileSize);
            }
            if(temp.walls[Tile.BOTTOM]){
               //System.out.println("grid number: " + i + ", " + j + temp.walls[Tile.BOTTOM]);

               g.fillRect(tileSize * i, tileSize * j + tileSize - wallSize, tileSize, wallSize);
            }
            if(temp.walls[Tile.LEFT]){
                           //System.out.println("grid number: " + i + ", " + j + temp.walls[Tile.LEFT]);

               g.fillRect(tileSize * i, tileSize * j, wallSize, tileSize);
            }
             
         }
      }
   }
   
   public void run()
   {
      DisjSets ds = new DisjSets(WIDTH);
      int arrSize = WIDTH * WIDTH; // size of array is dimension squared
      try
      {
         while (ds.find(0) != ds.find(arrSize - 1)) { // while entrance and exit are not in same set
            int tileNum = (int) (Math.random() * arrSize);
            int wall = (int) (Math.random() * 4); 
            collapseWall(tileNum, wall, ds);            
            repaint();
            Thread.sleep(100);
         }    
      } catch(InterruptedException e) {
         e.printStackTrace();
      }   
   }
   
   public void collapseWall(int tileNum, int wallNum, DisjSets ds){   
      int tileX = (int)(tileNum / WIDTH); // finds the x coordinate
      int tileY = tileNum % WIDTH; // finds the y coordinate
      if(tiles[tileX][tileY].walls.length > MAX_WALLS){
         if(wallNum == Tile.TOP){
            if(tileY - 1 > -1 && tileY < WIDTH && (tileX) > -1 && (tileX) < WIDTH){
               if(tiles[tileX][tileY - 1].numWalls > MAX_WALLS){
                  tiles[tileX][tileY].walls[Tile.TOP] = false;  
                  tiles[tileX][tileY].numWalls--;    
                  ds.union(WIDTH * (tileY - 1) + tileX, WIDTH * tileY + tileX);
                  tiles[tileX][tileY-1].walls[Tile.BOTTOM] = false;
                  tiles[tileX][tileY-1].numWalls--;
               }
            }
         //   collapseHelper(currTileX, currTileY, currTileX - 1, currTileY, Tile.TOP, Tile.BOTTOM, ds);
         }else if(wallNum == Tile.RIGHT){
            if(tileY > -1 && (tileY)< WIDTH && tileX > -1 && tileX + 1 < WIDTH){
               if(tiles[tileX + 1][tileY].numWalls > MAX_WALLS){
                  tiles[tileX][tileY].walls[Tile.RIGHT] = false; 
                  tiles[tileX][tileY].numWalls--;     
                  ds.union(WIDTH * tileY + (tileX + 1), WIDTH * tileY + tileX);
                  tiles[tileX + 1][(tileY)].walls[Tile.LEFT] = false;
                  tiles[tileX + 1][tileY].numWalls--;
               }
            }
            //collapseHelper(currTileX, currTileY, currTileX, currTileY +1, Tile.RIGHT, Tile.LEFT, ds);
         }else if(wallNum == Tile.BOTTOM){
            if(tileY > -1 && tileY + 1 < WIDTH && (tileX) > -1 && (tileX) < WIDTH){
               if(tiles[tileX][tileY + 1].numWalls > MAX_WALLS){
                  tiles[tileX][tileY].walls[Tile.BOTTOM] = false;
                  tiles[tileX][tileY].numWalls--;         
                  ds.union(WIDTH * (tileY + 1) + tileX, WIDTH * tileY + tileX);
                  tiles[tileX][tileY + 1].walls[Tile.TOP] = false;
                  tiles[tileX][tileY + 1].numWalls--;
               }
            }
            //collapseHelper(currTileX, currTileY, currTileX + 1, currTileY, Tile.BOTTOM, Tile.TOP, ds);  
         }else if(wallNum == Tile.LEFT){
            if((tileY) > -1 && (tileY) < WIDTH && tileX - 1 > -1 && tileX - 1 < WIDTH){
               if(tiles[tileX - 1][tileY].numWalls > MAX_WALLS){
                  tiles[tileX][tileY].walls[Tile.LEFT] = false;  
                  tiles[tileX][tileY].numWalls--;     
                  ds.union(WIDTH * tileY + (tileX - 1), WIDTH * tileY + tileX);
                  tiles[tileX - 1][(tileY)].walls[Tile.RIGHT] = false;
                  tiles[tileX - 1][tileY].numWalls--;
               }
            }//collapseHelper(currTileX, currTileY, currTileX, currTileY - 1, Tile.LEFT, Tile.RIGHT, ds);
         }
      }
   }      
}     