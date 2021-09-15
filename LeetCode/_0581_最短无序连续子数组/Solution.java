package LeetCode._0581_最短无序连续子数组;

/*
581. Shortest Unsorted Continuous Subarray
Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.
You need to find the shortest such subarray and output its length.

Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.

Note:
    Then length of the input array is in range [1, 10,000].
    The input array may contain duplicates, so ascending order here means <=.
 */
public class Solution {
    // T:O(n^3) S:O(1)
    /*
    1. the subarrays nums[0:i−1] and nums[j:n−1] are correctly sorted

    2. the elements in nums[0:i−1] all need to be lesser than the min
      all the elements in nums[j:n−1] need to be larger than max
     */
    static int findUnsortedSubarray(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, prev = Integer.MIN_VALUE;

                for (int k = i; k <= j; k++) {
                    min = Math.min(min, nums[k]);
                    max = Math.max(max, nums[k]);
                }

                if ((i > 0 && nums[i - 1] > min) || (j + 1 < nums.length && nums[j + 1] < max))
                    continue;

                int k = 0;
                while (k < i && prev <= nums[k]) {
                    prev = nums[k];
                    k++;
                }
                if (k != i)
                    continue;
                k = j;
                while (k < nums.length && prev <= nums[k]) {
                    prev = nums[k];
                    k++;
                }
                if (k == nums.length) {
                    if (i==j){
                        res=0;
                    }else {
                    res = Math.min(res, j - i + 1);
                }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 2, 6, 4, 8, 10, 9, 10, 9, 15};
        //int[] nums = new int[]{1, 2, 3, 4};
        System.out.println(findUnsortedSubarray(nums));
    }
}
