package com.LeetCode._0005_最长回文子串;
/*
leetcode 5 - 最长回文子串
给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：
输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。

示例 2：
输入: "cbbd"
输出: "bb"

思路1： 暴力循环遍历 time: o(n^3) space: o(1)
思路2： 中心扩散 time:o(n^2) space:o(1)
 */
public class Solution {
    //time: o(n^2) space:o(1)
    static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = helper(s, i, i);
            int len2 = helper(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    static int helper(String s, int left, int right) {
        //int L = left, R = right;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1; // 回文串的长度是right-left+1-2 = right-left-1
    }

    public static void main(String[] args) {

        System.out.println(longestPalindrome("beaaebc"));
    }
}
