package maze;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;

import maze.tiles.Tile;

public class Player
{
   
   double xpos, ypos;
   double xSpeed = 1, ySpeed = 1;
   int xFinish, yFinish;
   private Tile[][] tiles;
   private int tileSize;
   private int playerSize;
   
   public Player(Tile[][] tiles, int tileSize, int xStart, int yStart)
   {
      this.tiles = tiles;
      this.tileSize = tileSize;
      this.playerSize = tileSize / 2;
      xpos = xStart;
      ypos = yStart;
      xFinish = tiles.length - 1;
      yFinish = tiles[0].length - 1;
   }
   
   public void move()
   {
      Tile currentTile = tiles[(int)((xpos + playerSize / 2) / tileSize)][(int)((ypos + playerSize / 2) / tileSize)];
      if(KeyHandler.keys[KeyEvent.VK_W] || KeyHandler.keys[KeyEvent.VK_UP])
      {
         if(!currentTile.walls[Tile.TOP] || ((int) ypos) % tileSize + 1 > tileSize / 8)
         {
            ypos -= ySpeed;
         }  
      }
      if(KeyHandler.keys[KeyEvent.VK_D] || KeyHandler.keys[KeyEvent.VK_RIGHT])
      {
         if(!currentTile.walls[Tile.RIGHT] || ((int) xpos + playerSize) % tileSize  + 1< tileSize - tileSize / 8)
         {
            xpos += xSpeed;
         }  
      }
      if(KeyHandler.keys[KeyEvent.VK_S] || KeyHandler.keys[KeyEvent.VK_DOWN])
      {
         if(!currentTile.walls[Tile.BOTTOM] || ((int) ypos + playerSize) % tileSize  + 1< tileSize - tileSize / 8)
         {
            ypos += ySpeed;
         }  
      }
      if(KeyHandler.keys[KeyEvent.VK_A] || KeyHandler.keys[KeyEvent.VK_LEFT])
      {
         if(!currentTile.walls[Tile.LEFT] || ((int) xpos) % tileSize + 1 > tileSize / 8)
         {
            xSpeed -= xSpeed;
         }  
      }
   }
   
   public boolean atFinish()
   {
      return (int)(xpos / tileSize) == xFinish && (int)(ypos / tileSize) == yFinish;
   }
   
   public void paint(Graphics g)
   {
      g.setColor(Color.RED);
      g.fillOval((int)xpos - playerSize / 2, (int)ypos - playerSize / 2, playerSize, playerSize);
   }
   
}