public class DisjSets {
   public int[] arr;
   
   public DisjSets(int dimension) {
      int arrSize = dimension * dimension;
      arr = new int[arrSize];
      for(int i = 0; i < arr.length; i++) {
         arr[i] = -1;
      }
      arr[0] = -2;
      arr[arrSize - 1] = -3;
   }
   
   public void union(int x, int y) {
      int fx = find(x);
      int fy = find(y);
      if(fx < fy)
         arr[fy] = fx;
      else
         arr[fx] = fy;
   }
   
   public int find(int x) {
      if(arr[x] < 0)  
         return x;
      else
         return find(arr[x]);
   }
}