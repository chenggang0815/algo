package com.LeetCode._0290_Word_Pattern;

import java.util.HashMap;

/*
290. Word Pattern
Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.

Example 1:
Input: pattern = "abba", s = "dog cat cat dog"
Output: true

Example 2:
Input: pattern = "abba", s = "dog cat cat fish"

Output: false

Example 3:
Input: pattern = "aaaa", s = "dog cat cat dog"
Output: false

Example 4:
Input: pattern = "abba", s = "dog dog dog dog"
Output: false

Constraints:
1 <= pattern.length <= 300
pattern contains only lower-case English letters.
1 <= s.length <= 3000
s contains only lower-case English letters and spaces ' '.
s does not contain any leading or trailing spaces.
All the words in s are separated by a single space.
 */
/*
思路：利用两个hashmap，分别映射s2p和p2s，依次判断是否相匹配
time:o(m+n) m和n分别是pattern和s的长度，其中对s按照空格切分需要o(n)的时间复杂度
space:o(m+n)

相似题目：
205. Isomorphic Strings
 */
public class Solution {

    static boolean wordPattern(String pattern, String s) {
        String[] str = s.split(" ");
        if (pattern.length() != str.length) return false;


        HashMap<Character, String> p2s = new HashMap<>();
        HashMap<String, Character> s2p = new HashMap<>();

        for (int i = 0; i < str.length; i++){
            char p = pattern.charAt(i);
            if (!p2s.containsKey(p)){
                p2s.put(p, str[i]);
            }else {
                if (!p2s.get(p).equals(str[i])) return false;
            }

            if (!s2p.containsKey(str[i])){
                s2p.put(str[i], p);
            }else {
                if (s2p.get(str[i]) != p) return false;
            }
        }

        return true;
    }


    public static void main(String[] args) {
        System.out.println(wordPattern("abba", "dog cat cat dog"));
        System.out.println(wordPattern("abba", "dog dog dog dog"));

    }
}
