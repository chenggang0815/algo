package com.nowcoder._074;

import java.util.HashMap;

/*
字符串的最长不重复子串的长度
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
