package com.nowcoder._034;

import java.util.Arrays;

/*
连续子数组的最大和
思路一： 两次for循环，遍历出所有可能的连续子数组，求最大值

思路二：

思路三： 动态规划
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
        for (int i=0;i<array.length;i++){
            if (sum<0)
                sum = array[i];
            else
                sum = sum + array[i];


            if (sum>max) max = sum;
        }

        return max;

    }

    // time:o(n) space:o(n)
    static int FindGreatestSumOfSubArray3(int[] array) {
        int[] dp = new int[array.length];
        dp[0] = array[0];
        int max = array[0];
        for(int i=1;i<array.length;i++){
            dp[i] = Math.max(array[i],array[i]+dp[i-1]);
            max = Math.max(dp[i],max);
        }

        System.out.println(Arrays.toString(dp));
        return max;

    }
    public static void main(String[] args) {
        int[] nums = new int[]{1,-2,3,10,-4,7,2,-5};
        System.out.println(FindGreatestSumOfSubArray2(nums));
    }
}
