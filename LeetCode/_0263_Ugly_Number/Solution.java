package LeetCode._0263_Ugly_Number;

import java.util.ArrayList;
import java.util.HashMap;

/*
263. Ugly Number

Write a program to check whether a given number is an ugly number.
Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.

Example 1:
Input: 6
Output: true
Explanation: 6 = 2 × 3

Example 2:
Input: 8
Output: true
Explanation: 8 = 2 × 2 × 2

Example 3:
Input: 14
Output: false
Explanation: 14 is not ugly since it includes another prime factor 7.

Note:
1 is typically treated as an ugly number.
Input is within the 32-bit signed integer range: [−231,  231 − 1].
 */

public class Solution {
//    static boolean isUgly(int num) {
//        int minNum = Integer.MAX_VALUE;
//        HashMap<Integer, Integer> uglyNumber = new HashMap<>();
//        int[][] dp = new int[3][3];
//        dp[0][0] = 2;
//        dp[1][0] = 3;
//        dp[2][0] = 5;
//        int[] factors = new int[]{2,3,5};
//
//        while (minNum < num){
//            for (int i = 0; i < factors.length; i++){
//                dp[0][i] = dp[0][0] * factors[i];
//
//                dp[1][i] = dp[1][0] * factors[i];
//                dp[2][i] = dp[2][0] * factors[i];
//
//            }
//
//        }
//
//    }

    // 迭代
    static boolean isUgly1(int num) {
        if(num < 1) return false;

        while (num != 1){
            if (num % 2 == 0) num = num / 2;
            else if (num % 3 == 0) num = num / 3;
            else if (num % 5 == 0) num = num / 5;
            else break;
        }

        if (num == 1) return true;
        else return false;
    }

    // 递归 - 错误版本
    static boolean isUgly2(int num) {
        if(num == 0) return false;
        if (num == 1) return true;

        if (num % 2 == 0) isUgly2(num / 2);
        if (num % 3 == 0) isUgly2(num / 3);
        if (num % 5 == 0) isUgly2(num / 5);

        return false;
    }

    static boolean isUgly2_1(int num) {
        if(num == 0) return false;
        if (num == 1) return true;

        if (num % 2 == 0) return isUgly2(num / 2);
        if (num % 3 == 0) return isUgly2(num / 3);
        if (num % 5 == 0) return isUgly2(num / 5);

        return false;
    }

    public static void main(String[] args) {
        System.out.println(isUgly2(6));
    }
}
