package Facebook._325_Maximum_Size_Subarray_Sum_Equals_k;

import java.util.HashMap;

/*
325. Maximum Size Subarray Sum Equals k
Given an integer array nums and an integer k, return the maximum length of a subarray that sums to k.
If there is not one, return 0 instead.

Example 1:
Input: nums = [1,-1,5,-2,3], k = 3
Output: 4
Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.

Example 2:
Input: nums = [-2,-1,2,1], k = 1
Output: 2
Explanation: The subarray [-1, 2] sums to 1 and is the longest.

Constraints:
1. 1 <= nums.length <= 2 * 10^5
2. -10^4 <= nums[i] <= 10^4
3. -10^9 <= k <= 10^9
*/
/*
Solution
Approach 1: prefix sum + hashMap time:O(n) space:O(n)

nums = 1  1  2  5  -3  k = 7
i    = 0  1  2  3   4
sum =  1  2  4  9   6
if we have sum[j] = sum[i] - k in the map, we can find a subarray which the sum equal k
=> sum[j] = sum[i] - k = 9 - 7 = 2, the length of the subarray = i - j = 3 - 1 =2

sum[i] - k = sum[j] => sum[i] - sum[j] = k => sum[i] - sum[j] denote the sum from nums[j + 1] to nums[i]
*/
public class Solution {
    public int maxSubArrayLen(int[] nums, int k) {

        HashMap<Integer, Integer> prefixSum = new HashMap<>();
        int sum = 0;
        int res = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            if(sum == k) res = i + 1;
            // we may have the multiple same key, if the map contain the sum, we don't update the (sum, index) pair, because we just need the first position to find the longest subarray.
            if(!prefixSum.containsKey(sum)){
                prefixSum.put(sum, i);
            }
            if(prefixSum.containsKey(sum - k)){
                res = Math.max(res, i - prefixSum.get(sum - k));
            }
        }

        return res;


    }
    public static void main(String[] args) {

    }
}
