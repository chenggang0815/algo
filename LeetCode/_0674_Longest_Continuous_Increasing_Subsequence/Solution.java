package LeetCode._0674_Longest_Continuous_Increasing_Subsequence;
/*
674. Longest Continuous Increasing Subsequence

Given an unsorted array of integers nums, return the length of the longest continuous increasing subsequence (i.e. subarray). The subsequence must be strictly increasing.

A continuous increasing subsequence is defined by two indices l and r (l < r) such that it is [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] and for each l <= i < r, nums[i] < nums[i + 1].

Example 1:
Input: nums = [1,3,5,4,7]
Output: 3
Explanation: The longest continuous increasing subsequence is [1,3,5] with length 3.
Even though [1,3,5,7] is an increasing subsequence, it is not continuous as elements 5 and 7 are separated by element 4.

Example 2:
Input: nums = [2,2,2,2,2]
Output: 1
Explanation: The longest continuous increasing subsequence is [2] with length 1. Note that it must be strictly increasing.
 */

/*
ps:此题是2021-01-24的每日一题，并且是32题，解法2中的一个子问题；
再写出这个子问题的时候，我想去找类似的问题（其实是53题），结果发现了当天的每日一题，也就是674题基本完美和我解出的子问题对上了，
只需要修改一个判断条件，就ac了。

算是一个惊喜，所以纪念一下
 */
public class Solution {
    static int findLengthOfLCIS(int[] nums) {

        if (nums.length <= 1) return nums.length;
        int[] dp = new int[nums.length];
        int res = 0;
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++){
            if (nums[i] > nums[i - 1]){
                dp[i] = dp[i - 1] + 1;
            }else dp[i] = 1;

            res = Math.max(res, dp[i]);
        }

        return res;
    }
    public static void main(String[] args) {
        System.out.println(findLengthOfLCIS(new int[]{1,3,5,4,7}));
    }
}
