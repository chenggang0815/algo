package com.LeetCode._0258_Add_Digits;
/*
258. Add Digits

Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

Example:
Input: 38
Output: 2
Explanation: The process is like: 3 + 8 = 11, 1 + 1 = 2.
             Since 2 has only one digit, return it.
Follow up:
Could you do it without any loop/recursion in O(1) runtime?
*/
/*

*/
public class Solution {
    //
    static int addDigits1(int num) {
        int res = num;
        while (res > 9){
            String s = String.valueOf(res);
            int temp = 0;
            for (int i = 0; i < s.length(); i++){
                temp = temp + (s.charAt(i) - '0');
            }
            res = temp;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(addDigits1(38));
    }
}
