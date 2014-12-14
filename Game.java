import java.awt.*;
import javax.swing.JPanel;

public class Game extends JPanel implements Runnable
{
   private boolean running = true;
   private static final int WINDOW_SIZE = 500;
   private static final int WIDTH = 2;
   private static final int BORDER = 10;
   private static Tile[][] tiles;
   private final int tileSize, wallSize;
   
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
      buildMaze();
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
               System.out.println("grid number: " + i + ", " + j + temp.walls[Tile.TOP]);
               g.fillRect(tileSize * i, tileSize * j, tileSize, wallSize);
            }
            if(temp.walls[Tile.RIGHT]){
                           System.out.println("grid number: " + i + ", " + j + temp.walls[Tile.RIGHT]);

               g.fillRect(tileSize * i + tileSize - wallSize, tileSize * j, wallSize, tileSize);
            }
            if(temp.walls[Tile.BOTTOM]){
               System.out.println("grid number: " + i + ", " + j + temp.walls[Tile.BOTTOM]);

               g.fillRect(tileSize * i, tileSize * j + tileSize - wallSize, tileSize, wallSize);
            }
            if(temp.walls[Tile.LEFT]){
                           System.out.println("grid number: " + i + ", " + j + temp.walls[Tile.LEFT]);

               g.fillRect(tileSize * i, tileSize * j, wallSize, tileSize);
            }
             
         }
      }
   }
   
   public void run()
   {
      while(running)
         try
         {
            Thread.sleep(100);
            repaint();
         } catch(InterruptedException e) {
            e.printStackTrace();
         }   
   }
   
   // builds maze using disjoint sets
   public void buildMaze()
   {
      DisjSets ds = new DisjSets(WIDTH);
      int arrSize = WIDTH * WIDTH; // size of array is dimension squared
      while (ds.find(0) != ds.find(arrSize - 1)) { // while entrance and exit are not in same set
         int tileNum = (int) (Math.random() * arrSize);
         int wall = (int) (Math.random() * 4);
         collapseWall(tileNum, wall, ds);
      }
   }
   
   public void collapseWall(int tileNum, int wallNum, DisjSets ds){   
      int tileX = (int)(tileNum / WIDTH); // finds the x coordinate
      int tileY = tileNum % WIDTH; // finds the y coordinate
      if(wallNum == Tile.TOP){
         if(tileY > -1 && tileY < WIDTH && (tileX - 1) > -1 && (tileX - 1) < WIDTH){
            tiles[tileX][tileY].walls[Tile.TOP] = false;      
            ds.union(WIDTH * (tileX - 1) + tileY, WIDTH * tileX + tileY);
            tiles[(tileX - 1)][tileY].walls[Tile.BOTTOM] = false;
         }
      //   collapseHelper(currTileX, currTileY, currTileX - 1, currTileY, Tile.TOP, Tile.BOTTOM, ds);
      }else if(wallNum == Tile.RIGHT){
         if((tileY + 1) > -1 && (tileY + 1)< WIDTH && tileX > -1 && tileX < WIDTH){
            tiles[tileX][tileY].walls[Tile.RIGHT] = false;      
            ds.union(WIDTH * tileX + (tileY + 1), WIDTH * tileX + tileY);
            tiles[tileX][(tileY + 1)].walls[Tile.LEFT] = false;
         }
         //collapseHelper(currTileX, currTileY, currTileX, currTileY +1, Tile.RIGHT, Tile.LEFT, ds);
      }else if(wallNum == Tile.BOTTOM){
         if(tileY > -1 && tileY < WIDTH && (tileX + 1)> -1 && (tileX + 1) < WIDTH){
           tiles[tileX][tileY].walls[Tile.BOTTOM] = false;          
            ds.union(WIDTH * (tileX + 1) + tileY, WIDTH * tileX + tileY);
            tiles[(tileX + 1)][tileY].walls[Tile.TOP] = false;
         }
         //collapseHelper(currTileX, currTileY, currTileX + 1, currTileY, Tile.BOTTOM, Tile.TOP, ds);  
      }else if(wallNum == Tile.LEFT){
         if((tileY - 1) > -1 && (tileY - 1) < WIDTH && tileX > -1 && tileX < WIDTH){
            tiles[tileX][tileY].walls[Tile.LEFT] = false;       
            ds.union(WIDTH * tileX + (tileY - 1), WIDTH * tileX + tileY);
            tiles[tileX][(tileY - 1)].walls[Tile.RIGHT] = false;
         }//collapseHelper(currTileX, currTileY, currTileX, currTileY - 1, Tile.LEFT, Tile.RIGHT, ds);
      }
   }      
}     