package com.leetcode._0058_最后一个单词的长度;
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
    // time complexity: o(n)
    //space complexity:(1)
    //先去掉字符串最右边的" "，再找出第一个出现" "的位置
    static public int lengthOfLastWord(String s) {
        if (s.isEmpty())return 0;
        for (int i=s.length()-1;i>=0;i--){
            if (s.charAt(i)!=' '){
                s = s.substring(0,i+1);
                break;
            }
        }
        //System.out.println("s2="+s);
        for (int i=s.length()-1;i>=0;i--){
            if (s.charAt(i)==' '){
                return s.length()-1-i;
            }
        }
        return s.length();
    }

    //每次" "之后都是一个新的单词，从左到右统计每个单词的长度，最后返回的是最后一个单词的长度
    //o(n)
    //o(1)
    static public int lengthOfLastWord2(String s){
        int res = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != ' ') {
                if (i != 0 && s.charAt(i - 1) == ' ') res = 1;
                else ++res;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s=" a aa ";
        //String s = "hello world";
        System.out.println(lengthOfLastWord(s));

    }
}
