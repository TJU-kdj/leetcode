//自己写的，100%，74.26%
public class Solution598 {
    public int maxCount(int m, int n, int[][] ops) {
        if(ops.length==0){
            return m*n;
        }
        int minX=Integer.MAX_VALUE;
        int minY=Integer.MAX_VALUE;
        for (int i = 0; i < ops.length; i++) {
            int[] temp = ops[i];
            if(temp[0]<minX)minX=temp[0];
            if(temp[1]<minY)minY=temp[1];
        }
        return minX*minY;
    }
}