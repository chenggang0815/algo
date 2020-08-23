package com.剑指offer._058;
/*
正则表达式匹配
请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配

思路1：递归
1. 边界条件：
  当str和pattern都为空的时候，返回true；当pattern为空，str不为空的时候，返回false

2. 从 patternIndex = 0，开始比较，分两种情况,
     pattern[patternIndex + 1] = '*' =>  判断当前位置的两个字符是否相等，若相等 (str, pattern+2)或者(str+1, pattern) ，若不等 (str, pattern+2);
     pattern[patternIndex + 1] != '*' => 判断当前位置的两个字符是否相等，若相等(str+1, pattern+1)判断下一位，不相等return false;


思路2：动态规划
 */
public class Solution {

    static boolean match(char[] str, char[] pattern) {
        if (str.length == 0 && pattern.length == 0) return true;
        else if (str.length != 0 && pattern.length == 0) return false;

        int strIndex = 0;
        int patternIndex = 0;
        return helper(str, strIndex, pattern, patternIndex);
    }

    static boolean helper(char[] str, int strIndex, char[] pattern, int patternIndex) {

        if (patternIndex == pattern.length) return strIndex == str.length;

        boolean conditon = (strIndex != str.length && str[strIndex] == pattern[patternIndex]) || (strIndex != str.length && pattern[patternIndex] == '.');
        if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
            if (conditon) {
                return helper(str, strIndex + 1, pattern, patternIndex) || helper(str, strIndex, pattern, patternIndex + 2);
            } else {
                return helper(str, strIndex, pattern, patternIndex + 2);
            }

        } else{
            if (conditon) {
                return helper(str, strIndex + 1, pattern, patternIndex + 1);
            } else {
                return false;
            }
        }
    }


    public static void main(String[] args) {
        char[] str = new char[]{'a', 'a', 'a'};
        char[] pattern = new char[]{'a', '.', 'a', '*', 'a'};
       // System.out.println(match1(str, 0, pattern, 0));
        System.out.println(match(str, pattern));
    }
}
