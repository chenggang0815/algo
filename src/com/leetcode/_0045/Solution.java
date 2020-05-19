package com.leetcode._0045;
/*
91. Decode Ways Medium
A message containing letters from A-Z is being encoded to numbers using the following mapping:
'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:
Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).

Example 2:
Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).

 */

/*
假设数字为xxxxij，ij为最后两位，共有以下几种情况:
一. j!='0'
    1. i='0' => dp[i] = dp[i-1]
    2-1. i!='0'&&ij>26 => dp[i] = dp[i-1]
    2-2. i!='0'&&ij>=26 => dp[i] = d[i-1]+dp[i-2]

二. j=='0'
    1. i=='0' => return 0
    2-1. i!='0'&&ij<=26 => dp[i] = dp[i-2]
    2-2. i!='0'&&ij>26 => return 0

    时间复杂度：o(n)
    空间复杂度:o(n) 可优化至o(1)
 */
public class Solution {
    static public int numDecodings(String s) {
        if (s.length()=='0'||s.charAt(0)=='0') return 0;
        if (s.length()==1) return 1;
        char[] c = s.toCharArray();
        int[] dp = new int[c.length];
        dp[0]=1;
        int m = (c[0]-'0')*10+c[1]-'0';
        if (c[1]!='0'){
            if (m>26) dp[1] = 1;
            else dp[1] = 2;
        }else{
            if (m>26) dp[1] = 0;
            else dp[1] = 1;
        }

        for (int i=2;i<c.length;i++){
            int n = (c[i-1]-'0')*10+c[i]-'0';

            if (c[i]!='0'){
                if (c[i-1]=='0') dp[i] = dp[i-1];
                else if (c[i-1]!='0'&&n>26) dp[i] = dp[i-1];
                else if (c[i-1]!='0'&&n<=26) dp[i] = dp[i-1]+dp[i-2];
            }else {
                if (c[i-1]=='0') return 0;
                else if (c[i-1]!='0'&&n>26) return 0;
                else if (c[i-1]!='0'&&n<=26) dp[i] = dp[i-2];
            }
        }

        return dp[c.length-1];

    }

    public static void main(String[] args) {
        String s= "27";
        System.out.println(numDecodings(s));

    }
}
