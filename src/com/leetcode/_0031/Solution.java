package com.leetcode._0031;
/*
28. Implement strStr() Easy
Implement strStr().
Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:
Input: haystack = "hello", needle = "ll"
Output: 2

Example 2:
Input: haystack = "aaaaa", needle = "bba"
Output: -1

Clarification:
What should we return when needle is an empty string? This is a great question to ask during an interview.
For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 */
public class Solution {
    // time complexity: o(n^2)
    // space complexity:o(n)
    //Runtime: 456 ms, faster than 9.35% of Java online submissions for Implement strStr().
    static public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        char[] hay = haystack.toCharArray();
        char[] nee = needle.toCharArray();
        for (int i=0;i<hay.length;i++){
            int j=0;
            int temp = i;
            while (j<nee.length&&temp<hay.length){
                if (nee[j]==hay[temp]){
                    temp++;
                    j++;
                }else j++;
            }
            if ((temp-i)==nee.length){
                return i;
            }
        }
        return -1;
    }
    //Runtime: 5 ms, faster than 17.19% of Java online submissions for Implement strStr().
    static public int strStr2(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        char[] hay = haystack.toCharArray();
        for (int i=0;i<hay.length;i++){
            StringBuffer str5 = new StringBuffer();
            int j = 0;
            while (j< needle.length()&&i+needle.length()-1<hay.length){
                str5.append(hay[i+j]);
                j++;
            }
            if (str5.toString().equals(needle)) return i;
        }
        return -1;
    }

    public static void main(String[] args) {

        String haystack = "a";
        String needle = "a";
        System.out.println(strStr2(haystack,needle));

    }
}
