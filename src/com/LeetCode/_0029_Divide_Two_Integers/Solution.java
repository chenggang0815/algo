package com.LeetCode._0029_Divide_Two_Integers;

/*
29. Divide Two Integers

Given two integers dividend and divisor,
divide two integers without using multiplication, division, and mod operator.

Return the quotient after dividing dividend by divisor.
The integer division should truncate toward zero, which means losing its fractional part. For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.

Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range:
[−2^31, 2^31 − 1]. For this problem, assume that your function returns 2^31 − 1 when the division result overflows.

Example 1:
Input: dividend = 10, divisor = 3
Output: 3
Explanation: 10/3 = truncate(3.33333..) = 3.

Example 2:
Input: dividend = 7, divisor = -3
Output: -2
Explanation: 7/-3 = truncate(-2.33333..) = -2.

Example 3:
Input: dividend = 0, divisor = 1
Output: 0

Example 4:
Input: dividend = 1, divisor = 1
Output: 1
*/
public class Solution {
    static int divide(int dividend, int divisor) {
        if (dividend == 0) return 0;

        int m = Math.abs(dividend);
        int n = Math.abs(divisor);


        int res = 0;
        int sum = n;
        while (sum <= m){
            sum += n;
            res++;
        }

        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)){
            return -res;
        }

        return Math.min(res, Integer.MAX_VALUE);
    }


    public static void main(String[] args) {
        System.out.println(divide(-2147483648, -1));
        System.out.println(Math.pow(2, 31));
        System.out.println(Integer.MAX_VALUE);
    }
}
