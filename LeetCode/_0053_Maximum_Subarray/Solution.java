package LeetCode._0053_Maximum_Subarray;
/*
53. Maximum Subarray - Easy
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
Approach 1: Optimized Brute Force Time: O(n^2) Space:O(1)

Approach 2: dynamic programing Time:O(n) Space:O(n)
1. similar with approach3
2. if dp[i-1] + nums[i] < nums[i] => dp[i] = nums[i]
3. if dp[i-1] + nums[i] > nums[i] => dp[i] = dp[i-1] + nums[i]
=> dp[i] = Math.max(nums[i], dp[i - 1] + nums[i])

Approach 3: dynamic programing Time:O(n) Space:O(1)
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

    //dp solution
    static public int maxSubArray2(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for(int i = 1; i < nums.length; i++){
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            max = Math.max(dp[i], max);
        }
        return max;
    }

    public int maxSubArray3(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(currentSum < 0) currentSum = nums[i];
            else currentSum += nums[i];
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));

    }
}
