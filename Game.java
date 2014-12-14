public class Game extends JPanel implements Runnable
{
   private boolean running = true;
   private static final int WINDOW_WIDTH = 500;
   private static final int WIDTH = 50;
   private static final int BORDER = 
   
   public void paint(Graphics g)
   {
      g.setBackground(Color.BLACK);
      
      
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
}