package com.leetcode._0066_加一;
import java.util.Arrays;
/*
66. Plus One Easy
Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
You may assume the integer does not contain any leading zero, except the number 0 itself.
Example 1:
Input: [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.

Example 2:
Input: [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.

 */
public class Solution {
    // 除了需要进位的情况，其他情况都是在当前+1，如果当前=9，则在前一位+1
    // time: o(n)
    //space: o(n) or o(1)
    static public int[] plusOne(int[] digits) {
    for (int i=digits.length-1;i>=0;i--){
        if (digits[i]<9){
            digits[i]=digits[i]+1;
            return digits;
        }
        digits[i] =0;
    }

    int[] res = new int[digits.length+1]; // 需要进位的情况
    res[0] = 1;
    return res;
    }

    public static void main(String[] args) {
        int[] digits = new int[]{9,9,9,9};
        System.out.println(Arrays.toString(plusOne(digits)));
    }
}
