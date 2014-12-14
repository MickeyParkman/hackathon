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
         int tileNum = (int) (Math.random() * arrSize);
         int wall = (int) (Math.random() * 4);
         collapseWall(tileNum, wall, ds);
      }
   }
   
   public void collapseWall(int tileNum, int wallNum, DisjSets ds){   
      int currTileX = tileNum % WIDTH;
      int currTileY = (int)(tileNum / WIDTH);
              
      if(wallNum == Tile.TOP){
         collapseHelper(currTileX, currTileY, currTileX, currTileY - 1, Tile.TOP, Tile.BOTTOM, ds);
      }else if(wallNum == Tile.RIGHT){
         collapseHelper(currTileX, currTileY, currTileX + 1, currTileY, Tile.RIGHT, Tile.LEFT, ds);
      }else if(wallNum == Tile.BOTTOM){
         collapseHelper(currTileX, currTileY, currTileX, currTileY + 1, Tile.BOTTOM, Tile.TOP, ds);  
      }else if(wallNum == Tile.LEFT){
         collapseHelper(currTileX, currTileY, currTileX - 1, currTileY, Tile.LEFT, Tile.RIGHT, ds);
      }
   }
   
   private void collapseHelper(int currTileX, int currTileY, int nextTileX, int nextTileY, 
                              int currWallSide, int nextWallSide, DisjSets ds){
      tiles[currTileX][currTileY].walls[currWallSide] = false;      
      if(nextTileY > -1 && nextTileY < WIDTH && nextTileX > -1 && nextTileX < WIDTH){
         ds.union(WIDTH * nextTileY + nextTileX, WIDTH * currTileY + currTileX);
         tiles[nextTileX][nextTileY].walls[nextWallSide] = false;
      }
   }

}