package com.leetcode._0034;
/*
58. Length of Last Word Easy
Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word (last word means the last appearing word if we loop from left to right) in the string.
If the last word does not exist, return 0.
Note: A word is defined as a maximal substring consisting of non-space characters only.

Example:
Input: "Hello World"
Output: 5
 */
public class Solution {
    static public int lengthOfLastWord(String s) {
        if (s.isEmpty())return 0;
        char[] c = s.toCharArray();
        int temp=0;
        for(int j=0;j<c.length;j++){
            if(c[j]==' '){
                temp++;
            }
        }
        if(temp==c.length) return 0;

        for (int i=c.length-1;i>=0;i--){
            if (c[i]==' '&&i!=c.length-1) return c.length-1-i;
        }
        System.out.println("======");
        for (int i=0;i<c.length;i++){
            System.out.println(i);
            if (c[0]!=' '&&i!=0) return i;
        }

        return c.length;
    }
    public static void main(String[] args) {
        String s="a ";
        System.out.println(lengthOfLastWord(s));

    }
}
