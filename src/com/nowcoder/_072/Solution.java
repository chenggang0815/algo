package com.nowcoder._072;
/*
1. 最长公共子序列
如果X[i-1] = Y[j-1] => dp[i][j] = dp[i-1][j-1] + 1
如果X[i-1] != Y[j-1] => Math.max(dp[i][j - 1], dp[i - 1][j])

2. 最长公共子串（求连续的公共最长子序列）

如果X[i-1] = Y[j-1] => dp[i][j] = dp[i-1][j-1] + 1
如果X[i-1] != Y[j-1] => dp[i][j] = 0，就相当于在这里重新开始了。
 */
public class Solution {
/*
1. dp[i][j] 的含义是：对于 s1[1..i] 和 s2[1..j]，它们的 LCS 长度是 dp[i][j]
2. dp[0][..]和dp[..][0]都应该初始化为 0，这就是 base case
3. 递推关系：
    s1[i - 1] == s[j - 1] => dp[i][j] = dp[i -1][j - 1] + 1
    s1[i - 1] != s[j - 1] => dp[i][j] = Math.max(dp[i -1][j], dp[i][j - 1])
 */
    static int lcs(String s1, String s2){
        int[][] dp = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++){
            dp[i][0] = 0;
        }
        for (int j = 0; j < s2.length(); j++){
            dp[0][j] = 0;
        }

        for(int i = 1; i < s1.length(); i++){
            for (int j = 1; j < s2.length(); j++){
                if (s1.charAt(i -1) == s2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return dp[s1.length() - 1][s2.length() - 1];
    }

    static int lcsString(String s1, String s2){
        int[][] dp = new int[s1.length()][s2.length()];
        int result = 0;
        for (int i = 0; i < s1.length(); i++){
            dp[i][0] = 0;
        }
        for (int j = 0; j < s2.length(); j++){
            dp[0][j] = 0;
        }

        for(int i = 1; i < s1.length(); i++){
            for (int j = 1; j < s2.length(); j++){
                if (s1.charAt(i -1) == s2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    result = Math.max(result, dp[i][j]);
                }else{
                    dp[i][j] = 0;
                }
            }
        }

        return result;
    }
    public static void main(String[] args) {
        System.out.println(lcsString("helloworld", "loop"));
    }
}
