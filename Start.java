import javax.swing.JFrame;

public class Start extends JFrame
{

   public Start()
   {
      setContentPane(new Game());
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