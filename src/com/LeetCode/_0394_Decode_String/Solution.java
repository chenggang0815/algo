package com.LeetCode._0394_Decode_String;
/*
394. Decode String
给定一个经过编码的字符串，返回它解码后的字符串。编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。

Example 1:
Input: s = "3[a]2[bc]"
Output: "aaabcbc"

Example 2:
Input: s = "3[a2[c]]"
Output: "accaccacc"

Example 3:
Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"

Example 4:
Input: s = "abc3[cd]xyz"
Output: "abccdcdcdxyz"
*/


import java.util.Stack;

/*
思路1 ： 栈方法

本题中可能出现括号嵌套的情况，比如2[a2[bc]]，这种情况下我们可以先转化成2[abcbc]，在转化成abcbcabcbc。我们可以把字母、数字和括号看成是独立的TOKEN，并用栈来维护这些TOKEN。
具体的做法是，遍历这个栈：
1. 如果当前的字符为数位，解析出一个数字（连续的多个数位）并进栈
2. 如果当前的字符为字母或者左括号，直接进栈
3. 如果当前的字符为右括号，开始出栈，一直到左括号出栈，出栈序列反转后拼接成一个字符串，此时取出栈顶的数字（此时栈顶一定是数字，想想为什么？），就是这个字符串应该出现的次数，我们根据这个次数和字符串构造出新的字符串并进栈


 */
public class Solution {
    public String decodeString(String s) {
        Stack<String> stack = new Stack<>();

        for (char: s){

        }

    }

    public static void main(String[] args) {

    }
}
