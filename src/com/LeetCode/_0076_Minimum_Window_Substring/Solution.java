package com.LeetCode._0076_Minimum_Window_Substring;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Example 1:
Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"

String s = "ADOBEC ODE BANC", t = "ABC";
map = {A:1, B:1, C:1}
left = 0 right = 0 count = 3

https://leetcode.com/problems/minimum-window-substring/discuss/26808/Here-is-a-10-line-template-that-can-solve-most-'substring'-problems
 */
public class Solution {
    static String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for(Character c: t.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0, count = t.length();
        int minStart = 0, minLen = Integer.MAX_VALUE;

        while (right < s.length()) {
            char c = s.charAt(right);
            if(map.containsKey(c) && map.get(c) > 0 ) count--;
            map.put(c, map.getOrDefault(c, 0) - 1);
            right++;

            while (count == 0) {
                if (right - left < minLen) {
                    minStart = left;
                    minLen = right - left;
                }

                map.put(s.charAt(left), map.getOrDefault(s.charAt(left), 0) + 1);
                if (map.get(s.charAt(left)) > 0) count++;
                left++;
            }
        }

        if (minLen != Integer.MAX_VALUE) return s.substring(minStart, minStart + minLen);

        return "";
    }

    public String minWindow2(String s, String t) {
        int[] nums = new int[128];
        for(char c: t.toCharArray()){
            nums[c - '0']++;
        }

        int left = 0, right = 0, count = t.length();
        int minLen = Integer.MAX_VALUE, minStart = 0;
        while(right < s.length()){
            if(nums[s.charAt(right) - '0'] > 0) count--;
            nums[s.charAt(right) - '0']--;
            right++;

            while(count == 0){
                if(right - left < minLen){
                    minStart = left;
                    minLen = right - left;
                }

                nums[s.charAt(left) - '0']++;
                if(nums[s.charAt(left) - '0'] > 0) count++;
                left++;
            }
        }

        if(minLen != Integer.MAX_VALUE) return s.substring(minStart, minStart + minLen);

        return "";
    }


    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";
        System.out.println(minWindow(s, t));
    }
}
