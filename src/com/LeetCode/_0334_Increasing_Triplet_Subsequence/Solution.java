package com.LeetCode._0334_Increasing_Triplet_Subsequence;
/*
334. Increasing Triplet Subsequence

Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.

Example 1:
Input: nums = [1,2,3,4,5]
Output: true
Explanation: Any triplet where i < j < k is valid.

Example 2:
Input: nums = [5,4,3,2,1]
Output: false
Explanation: No triplet exists.

Example 3:
Input: nums = [2,1,5,0,4,6]
Output: true
Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.

Constraints:
1 <= nums.length <= 105
-231 <= nums[i] <= 231 - 1

Follow up: Could you implement a solution that runs in O(n) time complexity and O(1) space complexity?
*/
/*
思路1：双指针
[20,100,10,12,5,13]
20 min=20
100 mid=100 (min=20,mid=100)
10 min=10  (min=10,mid=100)
12 mid=12 (min=10,mid=12)
5 min=5 (min=5,mid=12)
13 13>mid => return

定义min和mid，不断更新min和mid。
假设找到了两个递增的值min、mid
    1. 如果下一个值小于min
    => min指针定位到这个值上 => 尽可能的使用最小值，防止后面出现了更小的一对递增值 （参考上面遍历到数字12的时候，min、mid更新为一对更小的递增值）
                          => 而即使不出现，也不妨碍找到解，因为最终是看能否找到大于mid的值（参考上面遍历到数字15的时候，min更新了，但是num比mid大）
    2. 如果下一个值大于最小值，且小于中间值 if  min < num < mid => mid = num
    => 则使用该更小的值作为中间值(因为如果最小的中间值都得不到解，那么就是false，这样也保证了覆盖所有的情况)

    3. 最后，如果找到了大于中间值的值，则为true

思路2：动态规划
思路3：前后遍历
https://leetcode-cn.com/problems/increasing-triplet-subsequence/solution/334-di-zeng-de-san-yuan-zi-xu-lie-liang-chong-si-l/
*/
public class Solution {
    static boolean increasingTriplet(int[] nums) {
        int min = Integer.MAX_VALUE, mid = Integer.MAX_VALUE;
        for (int num: nums){
            if (num <= min) min = num;
            else if (num <= mid) mid = num;
            else return true;
        }

        return false;
    }

    public static void main(String[] args) {

        System.out.println(increasingTriplet(new int[]{20,100,10,12,5,13}));

    }
}
