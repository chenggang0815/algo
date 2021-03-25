package com.LeetCode._0371_Sum_of_Two_Integers;
/*
371. Sum of Two Integers

Given two integers a and b, return the sum of the two integers without using the operators + and -.

Example 1:
Input: a = 1, b = 2
Output: 3

Example 2:
Input: a = 2, b = 3
Output: 5

Constraints:
-1000 <= a, b <= 1000
*/
/*
参考：剑指offer 48题

eg 13 + 9 = 2
13的二进制：1101 9的二进制：1001
第一轮
1. 计算不进位的和 => 1101 ^ 1001 => 0100
2. 计算进位 => 1101 & 1001 => 1001 <<1 => 10010
第二轮
1. 计算不进位的和 00100 ^ 10010 => 10110
2. 计算进位 => 00100 & 10010 => 00000 <<1 => 000000

进位为0，return 10110 => 2^4+2^2+2^1=16+4+2=22

https://leetcode.com/problems/sum-of-two-integers/discuss/84278/A-summary%3A-how-to-use-bit-manipulation-to-solve-problems-easily-and-efficiently

//时间复杂度 O(1)：最差情况下,需循环31次 => int32
//空间复杂度 O(1)
*/
public class Solution {
    static int getSum(int a, int b){
        while (b != 0){
            int temp = a ^ b;
            b = (a & b) << 1;
            a = temp;
        }

        return a;
    }

    public static void main(String[] args) {
        System.out.println(getSum(3,6));
    }
}
