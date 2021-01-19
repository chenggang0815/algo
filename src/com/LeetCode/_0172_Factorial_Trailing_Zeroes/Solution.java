package com.LeetCode._0172_Factorial_Trailing_Zeroes;
/*
172. Factorial Trailing Zeroes

Given an integer n, return the number of trailing zeroes in n!.
Follow up: Could you write a solution that works in logarithmic time complexity?

Example 1:
Input: n = 3
Output: 0
Explanation: 3! = 6, no trailing zero.

Example 2:
Input: n = 5
Output: 1
Explanation: 5! = 120, one trailing zero.

Example 3:
Input: n = 0
Output: 0
 */
public class Solution {
    static int numfive(int i){
        int res = 0;
        while (i > 0){
            if (i % 5 == 0) res++;
            i = i / 5;
        }

        return res;
    }

    static int numfive2(int i){
        int res = 0;
        while (i % 5 == 0){
            res++;
            i = i / 5;
        }

        return res;
    }
    static int trailingZeroes(int n) {
        int res = 0;
        for (int i = n; i > 0; i--){
            int temp = numfive2(i);
            if (temp > 0) System.out.println(i);
            res = res + temp;

        }
        return res;
    }

    static int trailingZeroes2(int n) {

        int zeroCount = 0;
        for (int i = 5; i <= n; i += 5) {
            int currentFactor = i;
            while (currentFactor % 5 == 0) {
                zeroCount++;
                currentFactor /= 5;
            }
        }
        return zeroCount;
    }

    public static void main(String[] args) {
    System.out.println(trailingZeroes(60));
       System.out.println(trailingZeroes2(60));
        System.out.println(numfive(54));
        System.out.println(54 / 5);
    }
}
