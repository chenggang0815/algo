package com.leetcode._0003_无重复字符的最长子串;
import java.util.HashMap;

/*
请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。

输入: "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

intput : "abcdbgh"
output: 5 ("cdbgh")
 */
public class Solution {

    //time: o(n) space: o(string中的字符集)
    static int longestSubString(String s){
        if (s.length() == 0) return 0;

        int res = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0;

        for (int i = 0; i < s.length(); i++){
            if (map.containsKey(s.charAt(i))){
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            res = Math.max(res, i - left + 1);
        }

        return res;
    }
    public static void main(String[] args) {
        System.out.println(longestSubString("abcdbef"));
    }
}

