package Facebook._0523_Continuous_Subarray_Sum;

import java.util.HashMap;

/*
523. Continuous Subarray Sum

Given an integer array nums and an integer k, return true if nums has a continuous subarray of size at least two whose elements sum up to a multiple of k, or false otherwise.
An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.

Example 1:
Input: nums = [23,2,4,6,7], k = 6
Output: true
Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.

Example 2:
Input: nums = [23,2,6,4,7], k = 6
Output: true
Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.

Example 3:
Input: nums = [23,2,6,4,7], k = 13
Output: false

Constraints:
1 <= nums.length <= 105
0 <= nums[i] <= 109
0 <= sum(nums[i]) <= 231 - 1
1 <= k <= 231 - 1
*/
/*
Solution

1. Running sum from first element to index i : sum_i. If we mod k, it will be some format like : sum_i = k * x + mod1
2. Running sum from first element to index j : sum_j. If we mod k, it will be some format like : sum_j = k * y + mod2
3. If they have the same mod, which is mod1 == mod2,
   subtracting these two running sum we get the difference sum_i - sum_j = (x - y) * k = constant * k,
   and the difference is the sum of elements between index i and j, and the value is a multiple of k.
*/
public class Solution {

    public boolean checkSubarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            sum = sum % k;
            if(map.containsKey(sum)){
                if (i - map.get(sum) > 1) return true;
            }else{
                map.put(sum, i);
            }
        }

        return false;
    }
    public static void main(String[] args) {

    }
}
