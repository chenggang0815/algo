package com.LeetCode._0231_Power_of_Two;
/*
231. Power of Two
Given an integer n, return true if it is a power of two. Otherwise, return false.
An integer n is a power of two, if there exists an integer x such that n == 2x.

Example 1:
Input: n = 1
Output: true
Explanation: 20 = 1

Example 2:
Input: n = 16
Output: true
Explanation: 24 = 16

Example 3:
Input: n = 3
Output: false
 */

/*
Follow up: Could you solve it without loops/recursion?
思路1： 迭代

思路2： 位运算
若 n = 2^x且x为自然数（即n为2的幂），则一定满足以下条件：
1. 恒有 n & (n - 1) == 0，这是因为：
    1.1 n的二进制最高位为1，其余所有位为0；
    1.2 n−1的二进制最高位为0，其余所有位为1；
2. n > 0

eg: 1 => 00000001
    2 => 00000010
    3 => 00000011
    4 => 00000100
    5 => 00000101
    6 => 00000110
    7 => 00000111
    8 => 00001000

=>  00001000 & 00000111 = 0
 */
public class Solution {
    static boolean isPowerOfTwo1(int n) {
        if (n <= 0) return false;
        if (n == 1) return true;
        while (n > 1){
            if (n % 2 != 0) return false;
            n = n / 2;
        }

        return true;
    }

    static boolean isPowerOfTwo2(int n) {
        if (n <= 0) return false;
        while (n % 2 == 0){
            n = n / 2;
        }

        return n == 1;
    }

    static boolean isPowerOfTwo3(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
    public static void main(String[] args) {
        System.out.println(isPowerOfTwo2(3));
    }
}
