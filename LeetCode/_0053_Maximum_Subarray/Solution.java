package LeetCode._0053_Maximum_Subarray;
/*
53. Maximum Subarray
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
Example:
Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.

Follow up:
If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
*/
/*
Solution
Approach 1: Brute Force
time: O(n^2)
space:O(1)

Approach 2: DP
time:O(n)
space:O(n)
1. similar with approach3
2. if dp[i-1] < 0 which means => dp[i - 1] + nums[i] < nums[i] => so we don't need care about dp[i - 1] => dp[i] = nums[i]
3. else dp[i - 1] > 0 which means => dp[i-1] + nums[i] > nums[i] => dp[i] = dp[i-1] + nums[i]
=> dp[i] = Math.max(nums[i], dp[i - 1] + nums[i])

Approach 3: DP
time:O(n)
space:O(1)
1. if currentSum < 0 => which means currentSum + nums[i] < nums[i]
    1.1 so we don't need to sum nums[i] to currentSum, we just let currentSum = nums[i]
    1.2 Whenever the sum of the array is negative, we know the entire array is not worth keeping, so we'll reset it back to an empty array.
2. else => we can add nums[i] to currentSum, which may generate bigger subarray
for example nums=[-1, 4, -1, 2, 1]; maxSum = nums[0]; currentSum = nums[0];
if currentSum < 0 => currentSum = nums[i]
else currentSum += nums[i]
maxSum = max(maxSum, currentSum)

Approach 4: Divide and Conquer (Advanced)
*/
public class Solution {
    // Approach 1
    static int  maxSubArray(int[] nums){
        int n = nums.length;
        int maxSum= nums[0];
        for (int i = 0; i < n; i++){
            int sum = 0;
            for (int j = i; j < n; j++){
                sum = sum + nums[j];
                maxSum = Math.max(maxSum, sum);
            }
        }

        return maxSum;
    }

    //Approach 2
    static public int maxSubArray2(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(dp[i - 1] < 0) dp[i] = nums[i]; //if the sum of the array is negative, we know it is not worth keeping, so we'll reset it back to an empty array.
            else dp[i] = dp[i - 1] + nums[i];
            res = Math.max(res, dp[i]);
        }

        return res;
    }

    // Approach 3
    // we just use an variable cur to denote the dp[i - 1], space complexity from O(n) to O(1)
    public int maxSubArray3(int[] nums) {
        int cur = nums[0];
        int res = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(cur < 0) cur = nums[i];
            else cur += nums[i];
            res = Math.max(res, cur);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));

    }
}
