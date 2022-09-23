package LeetCode._0628_Maximum_Product_of_Three_Numbers;

import java.util.Arrays;

/*
628. Maximum Product of Three Numbers
Given an integer array nums, find three numbers whose product is maximum and return the maximum product.

Example 1:
Input: nums = [1,2,3]
Output: 6

Example 2:
Input: nums = [1,2,3,4]
Output: 24
Example 3:

Input: nums = [-1,-2,-3]
Output: -6

Constraints:
3 <= nums.length <= 104
-1000 <= nums[i] <= 1000
 */
/*
Solution
1. sort the array, then the possible maximum result can be the product of last 3 number (because the last 3 number is top 3 largest number)
2. if the the array exist negative number, for example: [-100, 1, 2, 3] or [-100, -5, 1, 2, 3]
3. the possible max result can be nums[0] * nums[1] * last number, which means we use two negative number and one positive number
4. if the array exists 0, still meet condition, for example
[-100, 0, 1, 2 ,3]
[-100, -5, 0, 0, 1]
[-100, -5, 0, 0, 0]
*/
public class Solution {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;

        return Math.max(nums[length - 1] * nums[length - 2] * nums[length - 3], nums[0] * nums[1] * nums[length - 1]);
    }
    public static void main(String[] args) {

    }
}
