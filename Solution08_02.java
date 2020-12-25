import java.util.*;
public class Solution08_02 {
    //别人的答案
    /* List<List<Integer>> path = new LinkedList<>();  // 记录路径
    int r = 0;  // 行数
    int c = 0;  // 列数
    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        r = obstacleGrid.length;
        if (r == 0) {       // 空网格
            return path;
        }
        c = obstacleGrid[0].length;
        if (obstacleGrid[r - 1][c - 1] == 1) {  // 终点有障碍
            return path;
        }
        boolean[][] visit = new boolean[r][c];  // 记录数组
        backtrack(obstacleGrid, 0, 0, visit);
        return path;
    }

    public boolean backtrack(int[][] obstacleGrid, int x, int y, boolean[][] visit) {
        // 越界，有障碍，已访问
        if (x >= r || y >= c || obstacleGrid[x][y] == 1 || visit[x][y]) {
            return false;
        }
        // 如果不是以上情况，说明这个格子值得探索，做出选择
        path.add(Arrays.asList(x, y));
        visit[x][y] = true;
        // 选择后是否到达终点
        if (x == r - 1 && y == c - 1) {
            return true;
        }
        // 选择后没到终点，先尝试向下，再尝试向右，神奇的或运算
        if (backtrack(obstacleGrid, x + 1, y, visit) || backtrack(obstacleGrid, x, y + 1, visit)) {
            return true;
        }
        // 既不能向下也不能向右，是个死胡同，撤销选择
        path.remove(path.size() - 1);
        return false;
    }*/
    Stack<int[]> stack=new Stack<>();
    int i=0,j=0;
    int[][] input;
    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        input=obstacleGrid;
        method(0,0);
        if(stack.empty()){
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> ans=new ArrayList<>();
        while(!stack.empty()){
            int[] temp=stack.pop();
            List<Integer> t=new ArrayList<>();
            for(int k=0;k<temp.length;k++){
                t.add(temp[k]);
            }
            ans.add(t);
        }
        Integer[] finsh=ans.get(0).toArray(new Integer[0]);
        if(finsh[0].intValue()!=input.length-1||finsh[1].intValue()!=input[0].length-1){
            return new ArrayList<List<Integer>>();
        }
        Collections.reverse(ans);
        return ans;
    }

    void method(int down,int right){
        if(i+down>=input.length||j+right>=input[0].length){
            return;
        }
        else if(input[i+down][j+right]==0){
            i+=down;j+=right;
            stack.add(new int[]{i,j});
            method(0, 1);
            method(1, 0);
            if(i==input.length-1&&j==input[0].length-1){
                return;
            }   
            i-=down;j-=right;
            input[i+down][j+right]=1;
            stack.pop();
        }else{
            return;
        }
    }
}
