import java.awt.event.*;

public class KeyHandler implements KeyListener
{
   public static boolean[] keys;
   private Game game;
   public KeyHandler(Game game)
   {
      this.game = game;
      keys = new boolean[256];
   }
   
   @Override
   public void keyPressed(KeyEvent e)
   {
      keys[e.getKeyCode()] = true;
      if(e.getKeyCode() == KeyEvent.VK_N && game.winner)
      {
         game.winner = false;
         game.createGame();
      }
   }
   
   @Override
   public void keyReleased(KeyEvent e)
   {
      keys[e.getKeyCode()] = false;
   }
   
   @Override
   public void keyTyped(KeyEvent e)
   {
   
   }
}