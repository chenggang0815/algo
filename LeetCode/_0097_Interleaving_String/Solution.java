package LeetCode._0097_Interleaving_String;
/*
97. Interleaving String

Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:
1. s = s1 + s2 + ... + sn
2. t = t1 + t2 + ... + tm
3. |n - m| <= 1
4. The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
Note: a + b is the concatenation of strings a and b.
*/
/*
Solution
two pointer is not work
Approach 1: 2d Dynamic Programing
1. let dp[i][j] denote if the first i char of s1 and first j char of s2 can concat the first (i+j) char of s3
2. there are two case:
    2.1 case 1 => dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)
    2.2 case 2 => dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1)

time: O(m*n) space:O(m*n)
Approach 2: 1d Dynamic Programing
*/
public class Solution {

    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        if(len1 + len2 != len3) return false;

        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;
        for(int i = 1; i < len1 + 1; i++) dp[i][0] = dp[i - 1][0] && (s1.charAt(i - 1) == s3.charAt(i - 1));
        for(int i = 1; i < len2 + 1; i++) dp[0][i] = dp[0][i - 1] && (s2.charAt(i - 1) == s3.charAt(i - 1));
        for(int i = 1; i < len1 + 1; i++){
            for(int j = 1; j < len2 + 1; j++){
                dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1)) ;
            }
        }

        return dp[len1][len2];
    }
    public static void main(String[] args) {

    }
}
