package Facebook._0680_Valid_Palindrome_II;
/*
680. Valid Palindrome II
Given a string s, return true if the s can be palindrome after deleting at most one character from it.

Example 1:
Input: s = "aba"
Output: true

Example 2:
Input: s = "abca"
Output: true
Explanation: You could delete the character 'c'.

Example 3:
Input: s = "abc"
Output: false

Constraints:
1. 1 <= s.length <= 105
2. s consists of lowercase English letters.
*/

/*
Solution
Approach 1: two pointers time:O(n) space:O(1)
1. for a string, we can use two pointers to check if it is palindrome
2. we can start check the input string, when s.charAt(left) != s.charAt(right), which means we can delete one character and continue check the remaining string
3. for example:
    // ab c xxxx a ba
    //    l      r
    //   s.charAt(l) != s.charAt(r)
    //   => cxxxx => delete 'a' => check if cxxxx is palindrome
    //   => xxxxa => delete 'c' => check if xxxxa is palindrome
*/

public class Solution {
    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while(left < right){
            if(s.charAt(left) != s.charAt(right)) return isPalindrome(s, left, right - 1) || isPalindrome(s, left + 1, right);
            left++;
            right--;
        }

        return true;
    }

    boolean isPalindrome(String s, int left, int right){
        while(left < right){
            if(s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }

        return true;
    }

    boolean isPalindrome2(String s, int left, int right){
        while(left < right && s.charAt(left) == s.charAt(right)){
            left++;
            right--;
        }

        return s.charAt(left) == s.charAt(right);
    }
    public static void main(String[] args) {

    }
}
