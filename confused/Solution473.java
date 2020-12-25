package confused;  

import java.util.*;
import java.util.Collections;
import java.util.stream.Collectors;

public class Solution473 {
    // 官方解法1,43ms
    public List<Integer> nums;
    public int[] sums = new int[4];
    public int possibleSquareSide;

    // Depth First Search function.
    public boolean dfs(int index) {

        // If we have exhausted all our matchsticks, check if all sides of the square
        // are of equal length
        if (index == this.nums.size()) {
            return sums[0] == sums[1] && sums[1] == sums[2] && sums[2] == sums[3];
        }

        // Get current matchstick.
        int element = this.nums.get(index);

        // Try adding it to each of the 4 sides (if possible)
        for (int i = 0; i < 4; i++) {
            if (this.sums[i] + element <= this.possibleSquareSide) {
                this.sums[i] += element;
                if (this.dfs(index + 1)) {
                    return true;
                }
                this.sums[i] -= element;
            }
        }

        return false;
    }

    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int L = nums.length;
        int perimeter = 0;
        for (int i = 0; i < L; i++) {
            perimeter += nums[i];
        }

        this.possibleSquareSide = perimeter / 4;
        if (this.possibleSquareSide * 4 != perimeter) {
            return false;
        }

        // 将数组转换为列表不用遍历的方法
        this.nums = Arrays.stream(nums).boxed().collect(Collectors.toList());
        Collections.sort(this.nums, Collections.reverseOrder());
        return this.dfs(0);
    }

    // 官方解答二，动态规划+状态

    
    // 最快答案99.83%,68.32%,1ms
    boolean[] flag;

    public boolean makesquare1(int[] nums) {
        // 计算每条边的边长
        int sum = 0;
        for (int num : nums) {
            sum = sum + num;
        }

        if (sum == 0 || sum % 4 != 0) {
            return false;
        }
        int len = sum / 4;
        flag = new boolean[nums.length];
        // 降序排列，大的先上，这样剪枝可以剪多点
        // 升序降序都是1ms
        Arrays.sort(nums);
        int L = 0, R = nums.length - 1;
        while (L <= R) {
            int t = nums[L];
            nums[L] = nums[R];
            nums[R] = t;
            L++;
            R--;
        }
        return dfs(0, len, 0, 0, nums);
    }

    // edge 表示当前是第几条边，总共四条边
    // len 表示每条边应该的长度
    // u 表示当前边已经到多少长度了
    // start 认为规定一个遍历的顺序，防止重复
    // nums[] 木棍的数组
    boolean dfs(int edge, int len, int u, int start, int[] nums) {
        if (edge == 4) {
            return true;
        }
        // u 到达len，就可以换条边摆了
        if (u == len) {
            return dfs(edge + 1, len, 0, 0, nums);
        }

        for (int i = start; i < nums.length; i++) {
            if (!flag[i] && u + nums[i] <= len) {
                flag[i] = true;
                if (dfs(edge, len, u + nums[i], i + 1, nums)) {
                    return true;
                }
                flag[i] = false;

                // 能够走到这一步，说明这根火柴不行，否则已经return了
                // 相等的火柴也不行
                while (i + 1 < nums.length && nums[i + 1] == nums[i]) {
                    i++;
                }

                // 如果这条火柴是边的第一条，那说明在这条边的任意一个位置都不行，那说明这一整个方案也不行，
                if (u == 0) {
                    return false;
                }
                // 如果是最后一条，同理
                if (u + nums[i] == len) {
                    return false;
                }
            }
        }
        return false;
    }
}