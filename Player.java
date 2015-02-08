import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
public class Player
{
   
   int xCoordinate, yCoordinate;
   int xFinish, yFinish;
   private Tile[][] tiles;
   private int tileSize;
   
   public Player(Tile[][] tiles, int tileSize)
   {
      this.tiles = tiles;
      this.tileSize = tileSize;
      xCoordinate = 0;
      yCoordinate = 0;
      xFinish = tiles.length - 1;
      yFinish = tiles[0].length - 1;
   }
   
   public void move()
   {
      Tile currentTile = tiles[xCoordinate][yCoordinate];
      if(KeyHandler.keys[KeyEvent.VK_W] || KeyHandler.keys[KeyEvent.VK_UP])
      {
         if(!currentTile.walls[Tile.TOP])
         {
            yCoordinate -= 1;
         }  
      }
      if(KeyHandler.keys[KeyEvent.VK_D] || KeyHandler.keys[KeyEvent.VK_RIGHT])
      {
         if(!currentTile.walls[Tile.RIGHT])
         {
            xCoordinate += 1;
         }  
      }
      if(KeyHandler.keys[KeyEvent.VK_S] || KeyHandler.keys[KeyEvent.VK_DOWN])
      {
         if(!currentTile.walls[Tile.BOTTOM])
         {
            yCoordinate += 1;
         }  
      }
      if(KeyHandler.keys[KeyEvent.VK_A] || KeyHandler.keys[KeyEvent.VK_LEFT])
      {
         if(!currentTile.walls[Tile.LEFT])
         {
            xCoordinate -= 1;
         }  
      }
   }
   
   public boolean atFinish()
   {
      return xCoordinate == xFinish && yCoordinate == yFinish;
   }
   
   public void paint(Graphics g)
   {
      g.setColor(Color.RED);
      g.fillRect(xCoordinate * tileSize + 2, yCoordinate * tileSize + 2, tileSize - 4, tileSize - 4);
   }
   
}