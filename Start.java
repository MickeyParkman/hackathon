import javax.swing.JFrame;

import maze.*;

public class Start extends JFrame
{

   public Start()
   {
      Game game = new Game();
      setContentPane(game);
      addKeyListener(new KeyHandler(game));
      pack();
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
   }

   public static void main(String[] args)
   {
      new Start();
   }
}