package Facebook._1216_Valid_Palindrome_III;
/*
1216. Valid Palindrome III

Given a string s and an integer k, return true if s is a k-palindrome.
A string is k-palindrome if it can be transformed into a palindrome by removing at most k characters from it.

Example 1:
Input: s = "abcdeca", k = 2
Output: true
Explanation: Remove 'b' and 'e' characters.

Example 2:
Input: s = "abbababa", k = 1
Output: true

Constraints:
1 <= s.length <= 1000
s consists of only lowercase English letters.
1 <= k <= s.length
*/
/*
Solution
Approach 1: dynamic programing, similar to question: 516. Longest Palindromic Subsequence
1. get the length of the longest palindromic subsequence of s => lps
2. lps + k should >= s.length(), if s is a k-palindrome.
for example:
    1. One of the lps of string "abcdeca" is "acdca".
    2. Characters not contributing to lps of the string should be removed in order to make the string palindrome.
    3. So on removing b and e from "abcdeca".
*/
public class Solution {
    public boolean isValidPalindrome(String s, int k) {
        int lps = longestPalindromeSubseq(s);
        //System.out.print(lps);

        return lps + k >= s.length();

    }

    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];

        for(int i = s.length() - 1; i >= 0; i--){
            dp[i][i] = 1;
            for(int j = i + 1; j < s.length(); j++){
                //System.out.print(Arrays.toString(new int[]{i, j}));
                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }else{
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][s.length() - 1];
    }
    public static void main(String[] args) {

    }
}
