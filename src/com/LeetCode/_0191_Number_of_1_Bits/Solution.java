package com.LeetCode._0191_Number_of_1_Bits;
/*
191. Number of 1 Bits

Example 1:
Input: n = 00000000000000000000000000001011
Output: 3
Explanation: The input binary string 00000000000000000000000000001011 has a total of three '1' bits.

Example 2:
Input: n = 00000000000000000000000010000000
Output: 1
Explanation: The input binary string 00000000000000000000000010000000 has a total of one '1' bit.

Example 3:
Input: n = 11111111111111111111111111111101
Output: 31
Explanation: The input binary string 11111111111111111111111111111101 has a total of thirty one '1' bits.

思考：
本题是在做完190题后，10s内写出答案，一次ac
需要理解190题和常见位运算操作

java的18种写法
https://leetcode-cn.com/problems/number-of-1-bits/solution/javade-17chong-jie-fa-by-sdwwld/
 */
public class Solution {

    public int hammingWeight(int n) {
        int res = 0;
        for(int i = 0; i < 32; i++){
            int num = n & 1;
            if(num == 1){
                res++;
            }
            n = n >> 1;
        }

        return res;
    }
}
