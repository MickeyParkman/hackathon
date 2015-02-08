import java.awt.*;
import javax.swing.JPanel;

public class Game extends JPanel implements Runnable
{
   private boolean running = true;
   private static final int WINDOW_WIDTH = 1000;
   private static final int WINDOW_HEIGHT = 600;
   public boolean winner;
   
   Map map;
   Player player;
   
   public Game()
   {
      //Player starts at null so it isn't displayed while the map is being drawn
      player = null;      
      setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));            
      createGame();      
   }
   
   public void createGame()
   {
      //initialize a new map
      map = new Map(WINDOW_WIDTH, WINDOW_HEIGHT);
      //create a new game thread for painting the game and moving the player
      Thread game = new Thread(this);
      game.start();
      //create a new thread to handle the creation of the map
      Thread mapThread = new Thread(map);
      mapThread.start();      
   }
   
   @Override
   public void run()
   {
      //While the map is being created, only draw the map
      while(map.isCreating())
      {
         try{
            repaint();
            Thread.sleep(100);
         }
         catch(InterruptedException e)
         {
            System.err.println("Error Sleeping First Loop");
         }
      }
      //since the map is done, create the player
      player = new Player(map.getTiles(), map.getTileSize());
      //as long as the player hasn't reached the finish line, keep updating
      while(!player.atFinish())
      {
         try{
            //move the player if the correct keys are being pressed
            player.move();
            repaint();
            Thread.sleep(100);
         }
         catch(InterruptedException e)
         {
            System.err.println("Error Sleeping First Loop");
         }
      }
      //the player has reached the end and is a winner
      winner = true;
      try{
         //draw the end screen
         Thread.sleep(10);
         repaint();   
      }
      catch(InterruptedException e)
      {
         System.err.println("Error Sleeping At End");
      }
   }
   
   public void paint(Graphics g)
   {
      g.setColor(Color.BLACK);
      //fill a rectangle the size of the screen to remove what was being displayed before
      g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
      //paint the map
      map.paint(g);
      //if there is a player, draw them
      if(player != null)
      {
         player.paint(g);
      }
      if(winner)
      {
         //if there is a winner, clear the screen
         g.setColor(Color.BLACK);
         g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
         g.setColor(Color.WHITE);
         //paint "You won" and how to start a new game
         g.setFont(new Font("Times New Roman", Font.BOLD, 50));
         g.drawString("YOU WON!", WINDOW_WIDTH / 2 - 175, WINDOW_HEIGHT / 2 - 10);
         g.setFont(new Font("Times New Roman", Font.BOLD, 25));
         g.drawString("PRESS N FOR NEW GAME!", WINDOW_WIDTH / 2 - 210, WINDOW_HEIGHT / 2 + 30);
         player = null;
      }
   }
   
   
   
         
}     