package LeetCode._1143_Longest_Common_Subsequence;
/*
1143. 最长公共子序列

最长公共子序列：由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串

eg:
"abcdf", "acdfmf"
最长公共子序列："acdf"
最长公共子串： "cdf"
 */

public class Solution {
    /*
    1. dp[i][j] 的含义是：对于 s1[1..i-1] 和 s2[1..j-1]，它们的 LCS 长度是 dp[i][j]
    2. dp[0][..]和dp[..][0]都应该初始化为 0，这就是 base case
    3. 递推关系：
        s1[i - 1] == s[j - 1] => dp[i][j] = dp[i -1][j - 1] + 1
        s1[i - 1] != s[j - 1] => dp[i][j] = Math.max(dp[i -1][j], dp[i][j - 1])
     */
    //https://leetcode.com/problems/longest-common-subsequence/solution/
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length() + 1;
        int n = text2.length() + 1;

        int[][] dp = new int[m][n];
        //        s1 = "hello"  s2="world"
        //index         12345       12345
        //dp[5][5] => s1.subString(0, 5) s2.subString(0, 5)
        // dp[i][j] => s1[0]:s1[i-1] vs s2[0]:s2[j-1]
        // dp[0][0] = 0
        // dp[1][1] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;
        // if text1.charAt(i) == text2.charAt(j) => dp[i][j] = dp[i-1][j-1] + 1
        // else => two case
        // the last letter of "hello" is not matched or the last letter of "world" is not matched
        //        1. "hell" vs "world"
        //        2. "hello" vs "worl"
        //        dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j])
        //
        for(int i = 0; i < m; i++){
            dp[i][0] = 0;
        }
        for(int i = 0; i < n; i++){
            dp[0][i] = 0;
        }

        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(text1.charAt(i - 1) == text2.charAt(j - 1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }

        return dp[text1.length()][text2.length()];
    }

    static int lcs(String s1, String s2){
        if (s1.length() == 0 || s2.length() == 0) return 0;

        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++){
            dp[i][0] = 0;
        }
        for (int j = 0; j <= n; j++){
            dp[0][j] = 0;
        }
        //m = s1.length() n = s2.length()
        //dp[m][n]表示从s1[0,,,m-1] 到 s2[0,...n-1]的结果
        //所以dp[m][n]需要计算s1和s2的最后一个字符是否匹配，即s1.charAt(m - 1) == s2.charAt(n - 1)
        for(int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if (s1.charAt(i - 1) == s2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return dp[m][n];
    }

    static int lcsString(String s1, String s2){
        if (s1.length() == 0 || s2.length() == 0) return 0;

        int result = 0;
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++){
            dp[i][0] = 0;
        }
        for (int j = 0; j <= s2.length(); j++){
            dp[0][j] = 0;
        }

        for(int i = 1; i <= s1.length(); i++){
            for (int j = 1; j <= s2.length(); j++){
                if (s1.charAt(i - 1) == s2.charAt(j - 1)){
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

        System.out.println(lcsString("abcdf", "acdfmf"));
    }
}
