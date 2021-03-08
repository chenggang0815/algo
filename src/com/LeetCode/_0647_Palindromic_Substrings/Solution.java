package com.LeetCode._0647_Palindromic_Substrings;
/*
647. Palindromic Substrings

给定一个字符串，计算这个字符串中有多少个回文子串

Given a string, your task is to count how many palindromic substrings in this string.
The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".

Example 2:
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
*/

/*

思路1：枚举出所有的子串，然后再判断这些子串是否是回文 time:o(n^3)

思路2：枚举每一个可能的回文中心，然后用中心扩展法判断当前的回文中心有多少个回文子串 time:o(n^2)
 */
public class Solution {
    // Runtime: 2 ms, faster than 97.24% of Java online submissions for Palindromic Substrings.
    static int countSubstrings(String s){
        int res = 0;
        for (int i = 0; i < s.length(); i++){
            // 回文串可能是奇数也可能是偶数
            // eg: Input: "aaa"
            //Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
            res = res + helper(s, i, i) + helper(s, i, i + 1);
        }

        return res;
    }

    // 中心扩展判断有多少个回文子串
    static int helper(String s, int left, int right){
        int res = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
            res++;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(countSubstrings("aaa"));
    }
}
