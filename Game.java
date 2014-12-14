import java.awt.*;
import javax.swing.JPanel;

public class Game extends JPanel implements Runnable
{
   private boolean running = true;
   private static final int WINDOW_SIZE = 500;
   private static final int WIDTH = 8;
   private static final int BORDER = 10;
   
   public Game()
   {
      setPreferredSize(new Dimension(WINDOW_SIZE, WINDOW_SIZE));
      
   }
   
   public void paint(Graphics g)
   {
      g.setColor(Color.BLACK);
      g.fillRect(0, 0, WINDOW_SIZE, WINDOW_SIZE);
      
      g.setColor(Color.WHITE);
      g.drawString("Michael is Gay", 100, 100);
      for(int i = 0; i < tiles.length; i++)
      {
         for(Tile t : tiles[i])
         {
            t.paint();
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
   
   
   public void buildMaze(
   
   
   }
}