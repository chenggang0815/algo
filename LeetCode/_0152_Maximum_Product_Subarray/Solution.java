package LeetCode._0152_Maximum_Product_Subarray;
/*
152. Maximum Product Subarray
Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product, and return the product.
It is guaranteed that the answer will fit in a 32-bit integer.
A subarray is a contiguous subsequence of the array.

Example 1:
Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
*/
/*
Solution:
1. We need to take care of negative number, a negative product times a negative number may have the maximum product
2. so we use two int array to save the maximum product and the minimum product
3. for max array, we need to compare => Math.max(nums[i], Math.max(max[i - 1] * nums[i], Math.max(min[i - 1] * nums[i]))

similar with question 53
*/
public class Solution {
    public int maxProduct1(int[] nums) {
        int[] dpMin = new int[nums.length];
        int[] dpMax = new int[nums.length];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length - 1; i++){
            dpMax[i] = Math.max(nums[i], Math.max(dpMax[i - 1] * nums[i], dpMin[i - 1] * nums[i]));
            dpMin[i] = Math.min(nums[i], Math.min(dpMax[i - 1] * nums[i], dpMin[i - 1] * nums[i]));
            res = Math.max(res, dpMax[i]);
        }

        return res;
    }

    // approach 2, time: O(n), space: O(1)
    // similar to question 53, we use to variable the max and min
    // and use one temporary variable the max value, before we update max value
    public int maxProduct2(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++){
            // we need this temporary variable to store the max value, before we update max value
            int tempMax = Math.max(nums[i], Math.max(max * nums[i], min * nums[i]));
            min = Math.min(nums[i], Math.min(max * nums[i], min * nums[i]));
            max = tempMax;
            res = Math.max(res, max);
        }

        return res;
    }
    public static void main(String[] args) {

    }

}
