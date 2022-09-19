package LeetCode._0055_Jump_Game;
/*
55. Jump Game

Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
Each element in the array represents your maximum jump length at that position.
Determine if you are able to reach the last index.

Example 1:
Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:
Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 */
/*
贪心算法： time: o(n) space:o(1)
1. 如果某一个作为起跳点的格子可以跳跃的距离是3，那么表示后面3个格子都可以作为起跳点
2. 可以对每一个能作为起跳点的格子都尝试跳一次，把能跳到最远的距离不断更新
3. 如果可以一直跳到最后，就成功了

 */
public class Solution {
    static boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++){
            if (i > max) return false;
            max = Math.max(nums[i] + i, max);
            if (max >= nums.length) return true;
        }

        return true;
    }

    // 09/10/2022
    public boolean canJump2(int[] nums) {
        int max = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(max < i) return false;
            max = Math.max(max, i + nums[i]);
            if(max >= nums.length - 1) return true;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(canJump(new int[]{3,2,1,0,4}));
    }
}
