package com.剑指offer._30_连续子数组的最大和;

import java.util.Arrays;

/*
连续子数组的最大和
思路一： 两次for循环，遍历出所有可能的连续子数组，求最大值

思路二：动态规划 time : o(n) space: o(n)

思路三：动态规划 time : o(n) space: o(1)
 */
public class Solution {

    //time: o(n^2) space:o(1)
    static int FindGreatestSumOfSubArray1(int[] array) {
    int max = Integer.MIN_VALUE;
    for(int i=0;i<array.length;i++){
        int sum = 0;
        for (int j=i;j<array.length;j++){
            sum = sum + array[j];
            if (sum>max){
                max = sum;
            }
        }
    }
    return max;
    }

    //time:o(n) space:o(1)
    static int FindGreatestSumOfSubArray2(int[] array) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < array.length; i++){
            if (sum < 0)
                sum = array[i];
            else
                sum = sum + array[i];


            if (sum>max) max = sum;
        }

        return max;
    }

    // time:o(n) space:o(n)
    static int FindGreatestSumOfSubArray3(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for(int i = 1; i < nums.length; i++){
            dp[i] = Math.max(nums[i], nums[i] + dp[i-1]);
            max = Math.max(dp[i], max);
        }

        System.out.println(Arrays.toString(dp));

        return max;

    }
    public static void main(String[] args) {
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(FindGreatestSumOfSubArray3(nums));
    }
}
