package com.LeetCode._0005_Longest_Palindromic_Substring;
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
思路2： 中心扩散法 time:o(n^2) space:o(1)
思路3： 动态规划

 */
public class Solution {
    //time: o(n^2) space:o(1)
    static String longestPalindrome1(String s) {
        if (s == null || s.length() < 1) return "";

        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = helper(s, i, i);
            int len2 = helper(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start + 1) {
                //   c a b a c
                //   l   i   r  len = r - left + 1 - 2
                //   0 1 2 3 4
                //   len = 4 - 0 - 1 = 3
                //   start = 1 => i -  (len - 1) / 2 = 2-1
                //   end = 3 => i + len / 2 = 2 + 1
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
        // d a b a e
        // 0 1 2 3 4
        // l       r =>  len = right - left + 1 - 2 = right - left - 1
        return right - left - 1; // 回文串的长度是right-left+1-2 = right-left-1
    }

    //动态规划
    /*
    如果 dp[i + 1][j - 1]为回文串，那么向两边扩散，if s[i] == s[j] => dp[i][j] = true
    初始化：if s[i] == s[j] 并且，if j - i < 3 =>  dp[i][j] = true eg:"a", "aa", "aba"
    对于 s.length() <= 2 => return s
        s.length() > 2 => maxLength >= 1

     */
    static String longestPalindrome2(String s){
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int maxLength = 1;
        int start = 0;
        for(int i = 0; i < n; i++){
            dp[i][i] = true;
        }

        for(int j = 1; j < n; j++){
            for(int i = 0; i < j; i++){
                if (s.charAt(i) != s.charAt(j)){
                    dp[i][j] = false;
                }else{
                    if (j - i < 3){
                        dp[i][j] = true;
                    }else{
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                if (dp[i][j] && j - i + 1 > maxLength){
                    maxLength = j - i + 1;
                    start = i;
                }
            }
        }

        return s.substring(start, start + maxLength);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome2("a"));
    }
}
