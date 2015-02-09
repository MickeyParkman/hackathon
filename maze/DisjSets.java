package maze;

public class DisjSets {
   public int[] arr;
   
   public DisjSets(int width, int height) {
      int arrSize = width * height;
      arr = new int[arrSize];
      for(int i = 0; i < arr.length; i++) {
         arr[i] = -1;
      }
      arr[0] = -2;
      arr[arrSize - 1] = -3;
   }
   
   //tries to union two sets
   //if the two indexes are of the same set, return false
   //otherwise union the two sets and return true 
   public boolean union(int x, int y) {
      int fx = find(x);
      int fy = find(y);
      if(fx < fy)
      { 
         arr[fy] = fx;
         return true;
      }
      else if(fx > fy)
      {
         arr[fx] = fy;
         return true;
      }
      else
      {
         return false;
      }
   }
   
   public int find(int x) {
      if(arr[x] < 0){
         return x;
      }
      else{
         return find(arr[x]);
      }
   }
}