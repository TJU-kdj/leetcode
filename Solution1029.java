import java.util.*;

public class Solution1029 {
    public int twoCitySchedCost(int[][] costs) {
        // Sort by a gain which company has 
        // by sending a person to city A and not to city B
        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o1[1] - (o2[0] - o2[1]);
            }
        });
  
        int total = 0;
        int n = costs.length / 2;
        // To optimize the company expenses,
        // send the first n persons to the city A
        // and the others to the city B
        for (int i = 0; i < n; ++i) total += costs[i][0] + costs[i + n][1];
        return total;
      }
  
    public static void main(String[] args) {
        System.out.println("");
        int[][] a = new int[][] { { 10, 20 }, { 30, 200 }, { 400, 50 }, { 30, 20 } };
        new Solution1029().twoCitySchedCost(a);
    }
}