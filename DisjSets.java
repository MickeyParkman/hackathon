public class DisjSets {
   public int[] arr;
   
   public DisjSets(int dimension) {
      int arrSize = dimension * dimension;
      arr = new int[arrSize];
      for(int i : arr) {
         arr[i] = -1;
      }
      arr[0] = -2;
      arr[arrSize - 1] = -3;
   }
   
   public void union(int x, int y) {
      if(arr[x] < arr[y])
         arr[y] = x;
      else
         arr[x] = y;
   }
   
   public int find(int x) {
      if(arr[x] < 0)  
         return x;
      else
         return find(arr[x]);
   }
}