package LeetCode._0005_Longest_Palindromic_Substring;
/*
5. Longest Palindromic Substring
Given a string s, return the longest palindromic substring in s.

Example 1:
Input: s = "babad"
Output: "bab"
Note: "aba" is also a valid answer.

Example 2:
Input: s = "cbbd"
Output: "bb"

Example 3:
Input: s = "a"
Output: "a"

Example 4:
Input: s = "ac"
Output: "a"

Solution:
Approach 1: time:o(n^2) space:o(1)
1. getLen() => make the i-th character is the center of the the palindromic string, return the length of the palindromic string
2. i i
   i i+1

思路1： 暴力循环遍历 time: o(n^3) space: o(1)
思路2： 中心扩散法 time:o(n^2) space:o(1)
思路3： 动态规划

 */
public class Solution {

    public String longestPalindrome(String s) {
        int left = 0;
        int right = 0;
        //如果是是从i开始扩散，能保证回文串长度是奇数,可以直接 left = i - len/2  right = i + len/2
        //但是，如果从i和i+1开始扩展，回文串长度为偶数，此时中点在i和i+1之间，但是i的位置在中点的左边，所以计算左边的边界时，需要len-1再除以2
        // left = i - (len-1)/2 右边不受影响，还是 right = i + len/2
        //   b  a | a  b len=4 len/2=2
        //      1        (len-1)/2=1
        //   b a c a b len=5 len/2=2
        //       2
        // len=4  i=1 i+1=2
        // len=2
        // left=0
        // right=3

        for(int i = 0; i < s.length(); i++){
            int len1 = getLen(s, i, i);
            int len2 = getLen(s, i, i + 1);
            int len = Math.max(len1, len2);
            if(len > right - left + 1){
                left = i - (len - 1) / 2;
                right = i + len / 2;
                // left = i - len/2;
                //  right = i + len/2;
            }
        }

        return s.substring(left, right + 1);

    }

    // e a b a d
    // l   i   r
    // len = r - l + 1 - 2
    public int getLen(String s, int left, int right){
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }

        return right - left - 1;
    }
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
