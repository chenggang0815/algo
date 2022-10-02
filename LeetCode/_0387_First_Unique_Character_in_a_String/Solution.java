package LeetCode._0387_First_Unique_Character_in_a_String;
/*
387. First Unique Character in a String

Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.

Example 1:
Input: s = "leetcode"
Output: 0

Example 2:
Input: s = "loveleetcode"
Output: 2

Example 3:
Input: s = "aabb"
Output: -1

Constraints:
1 <= s.length <= 105
s consists of only lowercase English letters.
*/
public class Solution {
    public int firstUniqChar(String s) {
        int[] cnt = new int[26];
        for(char ch: s.toCharArray()){
            cnt[ch - 'a']++;
        }
        for(int i = 0; i < s.length(); i++){
            if(cnt[s.charAt(i) - 'a'] == 1) return i;
        }

        return -1;
    }

    public static void main(String[] args) {

    }
}
