package LeetCode._0044_Wildcard_Matching;
/*
44. Wildcard Matching

Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Example 1:
Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:
Input: s = "aa", p = "*"
Output: true
Explanation: '*' matches any sequence.

Example 3:
Input: s = "cb", p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.

Example 4:
Input: s = "adceb", p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".

Example 5:
Input: s = "acdcb", p = "a*c?b"
Output: false
*/

/*
Approach 1: 动态规划 time:O(m*n) space:O(m*n)
dp[i][j] 表示 p 的前 i 个字符和 s 的前 j 个字符是否匹配
1. 如果 p[i - 1] == s[j - 1] 或 p[i - 1] == '?'，表示当前的字符串是匹配的，dp[i][j] 可以从 dp[i - 1][j - 1] 转移而来
2. 如果 p[i - 1] == '*'，这个位置可以匹配0到若干个字符。
   那么 dp[i][j] 可以从 dp[i - 1][j] 转移而来（表示当前星号没有匹配字符）
   也可以从 dp[i][j - 1] 转移而来（表示当前星号匹配了当前的位置的字符）。
   因为只要任意一种匹配即可，所以这里是逻辑或的关系。
3. 初始条件 => dp[0][0] = true 表示空串是匹配的。处理一下匹配串 p 以若干个星号开头的情况。因为星号是可以匹配空串的


https://leetcode-cn.com/problems/wildcard-matching/solution/zi-fu-chuan-dong-tai-gui-hua-bi-xu-miao-dong-by-sw/
 */
public class Solution {
    static boolean isMatch(String s, String p){
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= n; i++){
            if (p.charAt(i - 1) == '*'){
                dp[0][i] = true;
            }else break;
        }

        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if (p.charAt(j - 1) == '*'){
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {

    }
}
