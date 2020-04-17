package com.leetcode._0028;
/*
14. Longest Common Prefix Easy

Write a function to find the longest common prefix string amongst an array of strings.
If there is no common prefix, return an empty string "".
Example 1:  Input: ["flower","flow","flight"]   Output: "fl"
Example 2:  Input: ["dog","racecar","car"]      Output: ""
Explanation: There is no common prefix among the input strings.

Note:   All given inputs are in lowercase letters a-z.
 */
public class Solution {

    /*
Time complexity : O(S), where S is the sum of all characters in all strings.
In the worst case all n strings are the same. The algorithm compares the string S1 with the other strings [S2…Sn]
There are S character comparisons, where S is the sum of all characters in the input array.
Space complexity : O(1). We only used constant extra space.
     */
    static String longestCommonPrefix(String[] strs) {
        if(strs.length==0) return "";
        String prefix = strs[0];
        for (int i=1;i<strs.length;i++){
            while (strs[i].indexOf(prefix)!=0){
                prefix = prefix.substring(0,prefix.length()-1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        //String[] strs = new String[]{"flower","flow","flight"};
        String[] strs = new String[]{"dog","racecar","car"};
        System.out.println(longestCommonPrefix(strs));
    }
}
