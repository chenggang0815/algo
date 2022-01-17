package Facebook._0266_Palindrome_Permutation;
/*
266. Palindrome Permutation

Given a string s, return true if a permutation of the string could form a palindrome.

Example 1:
Input: s = "code"
Output: false

Example 2:
Input: s = "aab"
Output: true

Example 3:
Input: s = "carerac"
Output: true

Constraints:
1. 1 <= s.length <= 5000
2. s consists of only lowercase English letters.
*/
/*
Solution
1. if a string can form a palindrome, it must meet the requirement that at most have one odd number character
2. for example:
    "aab" a:2 b:1 only have one odd number character is b
    "aaab" a:3 b:1 have two odd number character, can't form a palindrome
Approach 1: two pass

Approach 2: one pass
*/
public class Solution {
    // two pass
    public boolean canPermutePalindrome1(String s) {
        int[] map = new int[26];
        for(int i = 0; i < s.length(); i++){
            map[s.charAt(i) - 'a']++;
        }
        int num = 0;
        for(int i = 0; i < map.length; i++){
            if(map[i] % 2 == 1) num++;
        }

        return num < 2;
    }
    // one pass
    public boolean canPermutePalindrome2(String s) {
        int[] map = new int[26];
        int num = 0;
        for(int i = 0; i < s.length(); i++){
            map[s.charAt(i) - 'a']++;
            if(map[s.charAt(i) - 'a'] % 2 == 1) num++;
            else num--;
        }

        return num < 2;
    }

    public static void main(String[] args) {

    }
}
