public class Tile {
   public boolean[] walls;
   public int numWalls;
   public static final int TOP = 0, RIGHT = 1, BOTTOM = 2, LEFT = 3;
   
   public Tile() {
      numWalls = 4;
      walls = new boolean[4];
      for(int i = 0; i < walls.length; i++){
         walls[i] = true;
      }
   }
   
 }