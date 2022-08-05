package LeetCode._0205_Isomorphic_Strings;
/*
205. Isomorphic Strings
Given two strings s and t, determine if they are isomorphic.
Two strings s and t are isomorphic if the characters in s can be replaced to get t.
All occurrences of a character must be replaced with another character while preserving the order of characters.
No two characters may map to the same character, but a character may map to itself.

Example 1:
Input: s = "egg", t = "add"
Output: true
Example 2:
Input: s = "foo", t = "bar"
Output: false
Example 3:
Input: s = "paper", t = "title"
Output: true

Constraints:
1. 1 <= s.length <= 5 * 104
2. t.length == s.length
3. s and t consist of any valid ascii character.

similar question：
290. Word Pattern
*/
/*
Solution：
需要判断s和t每个位置上的字符是否都一一对应，即s的任意一个字符被t中唯一的字符对应，同时t的任意一个字符被s中唯一的字符对应。
"paper"
(p,t) (a,i) (p,t) (e,l) (r,e)
"title"
(t,p) (i,a) (t,p) (l,e) (e,r)
对于两个map来说，

"badc"
(b,b) (a,a) (d,b) (c,a)
"baba"
(b,b) (a,a) (b,d) (a,c)
*/

import java.util.HashMap;
public class Solution {
    // "paper", t = "title"
    static boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> s2t = new HashMap<>();
        HashMap<Character, Character> t2s = new HashMap<>();

        for (int i = 0; i < s.length(); i++){
            char x = s.charAt(i), y = t.charAt(i);
            if (s2t.containsKey(x) && s2t.get(x) != y || t2s.containsKey(y) && t2s.get(y) != x) return false;
            s2t.put(x, y);
            t2s.put(y, x);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isIsomorphic("badc", "baba"));
        System.out.println(isIsomorphic("paper", "title"));
        System.out.println(isIsomorphic("bada", "eaba"));
    }
}
