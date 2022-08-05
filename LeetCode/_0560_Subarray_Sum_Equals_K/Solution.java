package LeetCode._0560_Subarray_Sum_Equals_K;

import java.util.HashMap;
import java.util.Map;

/*
560. Subarray Sum Equals K

Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.

Example 1:
Input: nums = [1,1,1], k = 2
Output: 2

Example 2:
Input: nums = [1,2,3], k = 3
Output: 2
 */

/*
思路1： 暴力求解 time:o(n^3) space:o(1)

思路2： 暴力求解-优化 time:o(n^n) space:o(1)
根据[i, j]的和 => [i, j+1]的和

思路3：前缀和 + 哈希表优化 time:o(n) space:o(n)
Solution 1. Brute force. We just need two loops (i, j) and test if SUM[i, j] = k. Time complexity O(n^2), Space complexity O(1). I bet this solution will TLE.

Solution 2. From solution 1, we know the key to solve this problem is SUM[i, j]. So if we know SUM[0, i - 1] and SUM[0, j], then we can easily get SUM[i, j].
To achieve this, we just need to go through the array, calculate the current sum and save number of all seen PreSum to a HashMap. Time complexity O(n), Space complexity O(n).

// Sliding window -- No, contains negative number
// hashmap + preSum

   1. Hashmap<sum[0,i - 1], frequency>
   2. sum[i, j] = sum[0, j] - sum[0, i - 1]    --> sum[0, i - 1] = sum[0, j] - sum[i, j]
          k           sum      hashmap-key     -->  hashmap-key  =  sum - k
   3. now, we have k and sum.
      As long as we can find a sum[0, i - 1], we then get a valid subarray
      which is as long as we have the hashmap-key,  we then get a valid subarray
   4. Why don't map.put(sum[0, i - 1], 1) every time ?
      if all numbers are positive, this is fine
      if there exists negative number, there could be preSum frequency > 1
*/
public class Solution {
    //Time Limit Exceeded
    //time:o(n^3) space:o(1)
    static int subarraySum1(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i++){
            for (int j = i; j < nums.length; j++){
                int sum = 0;
                for (int t = i; t <= j; t++){
                    sum += nums[t];
                }
                if (sum == k) res++;
            }
        }

        return res;
    }
    //Runtime: 913 ms, faster than 24.00% of Java online submissions for Subarray Sum Equals K.
    // time:o(n^2) space:o(1)
    static int subarraySum2(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i++){
            int sum = 0;
            for (int j = i; j < nums.length; j++){
                sum += nums[j];
                if (sum == k) res++;
            }
        }

        return res;
    }
    /*
Solution 1. Brute force. We just need two loops (i, j) and test if SUM[i, j] = k. Time complexity O(n^2), Space complexity O(1). I bet this solution will TLE.

Solution 2. From solution 1, we know the key to solve this problem is SUM[i, j]. So if we know SUM[0, i - 1] and SUM[0, j], then we can easily get SUM[i, j].
To achieve this, we just need to go through the array, calculate the current sum and save number of all seen PreSum to a HashMap. Time complexity O(n), Space complexity O(n).
     */
    //17ms time:o(n) space:o(n)
    static int subarraySum3(int[] nums, int k) {
        // Edge cases
        if(nums.length == 0)    return 0;

        int sum = 0, result = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);

        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
            if (preSum.containsKey(sum - k)){
                result += preSum.get(sum - k);
            }

            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }

        return result;
    }
    public static void main(String[] args) {

        System.out.println(subarraySum2(new int[]{-1, -1, 1}, 0));
    }
}
