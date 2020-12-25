public class Solution33 {
    //自己做的100%，55.68%
    public int search(int[] nums, int target) {
        int i=0;
        if(target<nums[0]){
            i=nums.length-1;
            while(i>=0&&nums[i]<=nums[0]){
                if(target==nums[i])return i;
                else i--;
            }
        }else{
            while(i<=nums.length-1&&nums[i]>=nums[0]){
                if(target==nums[i])return i;
                else i++;
            }
        }
        return -1;
    }
}
