package com.LeetCode._0205_Isomorphic_Strings;
/*
205. Isomorphic Strings

给定两个字符串 s 和 t，判断它们是否是同构的。

如果s中的字符可以按某种映射关系替换得到t，那么这两个字符串是同构的。

每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。
不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。

Example 1:
Input: s = "egg", t = "add"
Output: true

Example 2:
Input: s = "foo", t = "bar"
Output: false

Example 3:
Input: s = "paper", t = "title"
Output: true

相似题目：
290. Word Pattern
*/
/*
思路：
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
