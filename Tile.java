public class Tile {
   public boolean[] walls;
   public static final int TOP = 0, RIGHT = 1, BOTTOM = 2, LEFT = 3;
   
   public Tile() {
      walls = new boolean[4];
      for(int i = 0; i < walls.length; i++){
         walls[i] = true;
      }
   }
   
   public void paint(Graphics g)
   {
      for(int i = 0; i < tiles.length; i++){
         for(int j = 0; j < tiles[i].length; j++){
            Tile temp = tiles[i][j];
            if(temp.walls[Tiles.TOP]){
               g.fillRect(tileSize * i, tileSize * j, tileSize, wallSize);
            }
            if(temp.walls[Tiles.RIGHT]){
               g.fillRect(tileSize * i + tileSize - wallSize, tileSize * j, wallSize, tileSize);
            }
            if(temp.walls[Tiles.BOTTOM]){
               g.fillRect(tileSize * i, tileSize * j + tileSize - wallSize, tileSize, wallSize);
            }
            if(temp.walls[Tiles.LEFT]){
               g.fillRect(tileSize * i, tileSize * j, wallSize, tileSize);
            }
             
         }
      }
   }
   
   public void collapseWall(int tileNum, int wallNum, DisjSets ds){
      int currTileX = tileNum % WIDTH;
      int currTileY = (int) tileNum / WIDTH;
      int nextTileX = 0;
      int nextTileY = 0;
      
      if(wallNum == Tile.TOP){
         nextTileX = currTileX;
         nextTileY = currTileY - 1;
         tiles[currTileX][currTileY].walls[Tile.TOP] = false;
         tiles[nextTileX][nextTileY].walls[Tile.BOTTOM] = false;
      }else if(wallNum == Tile.RIGHT){
         nextTileX = currTileX + 1;
         nextTileY = currTileY;
         tiles[currTileX][currTileY].walls[Tile.RIGHT] = false;
         tiles[nextTileX][nextTileY].walls[Tile.LEFT] = false;
      }else if(wallNum == Tile.DOWN){
         nextTileX = currTileX;
         nextTileY = currTileY + 1;
         tiles[currTileX][currTileY].walls[Tile.BOTTOM] = false;
         tiles[nextTileX][nextTileY].walls[Tile.TOP] = false;  
      }else if(wallNum == Tile.LEFT){
         nextTileX = currTileX - 1;
         nextTileY = currTileY;
         tiles[currTileX][currTileY].walls[Tile.LEFT] = false;
         tiles[nextTileX][nextTileY].walls[Tile.RIGHT] = false;
      }
      
      if(nextTileY > -1 && nextTileY < WIDTH && nextTileX > -1 && nextTileX < WIDTH){
         ds.union(WIDTH * nextTileY + nextTileX, tileNum);
      }
   }
}