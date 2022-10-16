package LeetCode._1498_Number_of_Subsequences_That_Satisfy_the_Given_Sum_Condition;

import java.util.Arrays;

/*
1498. Number of Subsequences That Satisfy the Given Sum Condition
You are given an array of integers nums and an integer target.
Return the number of non-empty subsequences of nums such that the sum of the minimum and maximum element on it is less or equal to target. Since the answer may be too large, return it modulo 109 + 7.


Example 1:
Input: nums = [3,5,6,7], target = 9
Output: 4
Explanation: There are 4 subsequences that satisfy the condition.
[3] -> Min value + max value <= target (3 + 3 <= 9)
[3,5] -> (3 + 5 <= 9)
[3,5,6] -> (3 + 6 <= 9)
[3,6] -> (3 + 6 <= 9)
*/
/*
Approach 1: two sum
1. Sort input nums first,
2. For each nums[i], find out the maximum nums[j] => that nums[i] + nums[j] <= target.
3. For each elements in the subarray nums[i+1] ~ nums[j], we can pick or not pick, so there are 2 ^ (j - i) subsequences in total.
   => So we can update res = (res + 2 ^ (j - i)) % mod.

We don't care the original elements order,
we only want to know the count of sub sequence.
So we can sort the original nums, and the result won't change.
*/
public class Solution {
    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        int res = 0;
        int[] pows = new int[nums.length];
        pows[0] = 1;
        for(int i = 1; i < nums.length; i++) pows[i] = pows[i - 1] * 2 % 1000000007;
        while(left <= right){
            if(nums[left] + nums[right] <= target){
                res = (res + pows[right - left]) % 1000000007;
                left++;
            }else{
                right--;
            }
        }

        return res;
    }
    public static void main(String[] args) {

    }
}
