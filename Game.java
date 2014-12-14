import java.awt.*;
import javax.swing.JPanel;

public class Game extends JPanel implements Runnable
{
   private boolean running = true;
   private static final int WINDOW_SIZE = 500;
   private static final int WIDTH = 8;
   private static final int BORDER = 10;
   private static Tile[][] tiles;
   private final int tileSize, wallSize;
   
   public Game()
   {
      setPreferredSize(new Dimension(WINDOW_SIZE, WINDOW_SIZE));
      tiles = new Tile[WIDTH][WIDTH];
      tileSize = WINDOW_SIZE / WIDTH;
      wallSize = tileSize / 8;
      buildMaze();
   }
   
   public void paint(Graphics g)
   {
      g.setColor(Color.BLACK);
      g.fillRect(0, 0, WINDOW_SIZE, WINDOW_SIZE);
      
      g.setColor(Color.WHITE);
      g.drawString("Michael is Gay", 100, 100);
      for(int i = 0; i < tiles.length; i++){
         for(int j = 0; j < tiles[i].length; j++){
            Tile temp = tiles[i][j];
            if(temp.walls[Tile.TOP]){
               g.fillRect(tileSize * i, tileSize * j, tileSize, wallSize);
            }
            if(temp.walls[Tile.RIGHT]){
               g.fillRect(tileSize * i + tileSize - wallSize, tileSize * j, wallSize, tileSize);
            }
            if(temp.walls[Tile.BOTTOM]){
               g.fillRect(tileSize * i, tileSize * j + tileSize - wallSize, tileSize, wallSize);
            }
            if(temp.walls[Tile.LEFT]){
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
   
   
   public void buildMaze()
   {
      DisjSets ds = new DisjSets(WIDTH);
      int arrSize = WIDTH * WIDTH;
      while (ds.find(0) != ds.find(arrSize - 1)) {
         int tileNum = (int) Math.random() * arrSize;
         int wall = (int) Math.random() * 4;
         collapseWall(tileNum, wall, ds);
      }
   }
   
   public void collapseWall(int tileNum, int wallNum, DisjSets ds){
      int currTileX = tileNum % WIDTH;
      int currTileY;
      if(tileNum != 0)
         currTileY = (int) tileNum / WIDTH - 1;
      else
         currTileY = 0;
         
      int nextTileX = 0;
      int nextTileY = 0;
      
      if(wallNum == Tile.TOP){
         nextTileX = currTileX;
         nextTileY = currTileY - 1;
         tiles[currTileX][currTileY].walls[Tile.TOP] = false;
         tiles[nextTileX][nextTileY].walls[Tile.BOTTOM] = false;
      }else if(wallNum == Tile.RIGHT){
         nextTileX = currTileX + 1;
         nextTileY = currTileY;
         tiles[currTileX][currTileY].walls[Tile.RIGHT] = false;
         tiles[nextTileX][nextTileY].walls[Tile.LEFT] = false;
      }else if(wallNum == Tile.BOTTOM){
         nextTileX = currTileX;
         nextTileY = currTileY + 1;
         tiles[currTileX][currTileY].walls[Tile.BOTTOM] = false;
         tiles[nextTileX][nextTileY].walls[Tile.TOP] = false;  
      }else if(wallNum == Tile.LEFT){
         nextTileX = currTileX - 1;
         nextTileY = currTileY;
         tiles[currTileX][currTileY].walls[Tile.LEFT] = false;
         tiles[nextTileX][nextTileY].walls[Tile.RIGHT] = false;
      }
      
      if(nextTileY > -1 && nextTileY < WIDTH && nextTileX > -1 && nextTileX < WIDTH){
         ds.union(WIDTH * nextTileY + nextTileX, tileNum);
      }
   }

}