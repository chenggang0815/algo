package LeetCode._0242_Valid_Anagram;

import java.util.Arrays;
/*
242. Valid Anagram

Given two strings s and t, return true if t is an anagram of s, and false otherwise.
An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.


Example 1:
Input: s = "anagram", t = "nagaram"
Output: true

Example 2:
Input: s = "rat", t = "car"
Output: false

Constraints:
1 <= s.length, t.length <= 5 * 104
s and t consist of lowercase English letters.
 */
/*
Approach 1: sort

Approach 2: frequency counter
* */
public class Solution {
    public boolean isAnagram1(String s, String t) {
        if(s.length() != t.length()) return false;

        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();

        Arrays.sort(sArray);
        Arrays.sort(tArray);

        for(int i = 0; i < sArray.length; i++){
            if(sArray[i] != tArray[i]) return false;
        }

        return true;
    }

    public boolean isAnagram2(String s, String t) {
        int[] charCnt = new int[26];
        if(s.length() != t.length()) return false;

        for(int i = 0; i < t.length(); i++){
            charCnt[s.charAt(i) - 'a']++;
            charCnt[t.charAt(i) - 'a']--;
        }

        for(int i = 0; i < charCnt.length; i++){
            if(charCnt[i] != 0) return false;
        }

        return true;
    }
}
