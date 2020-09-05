package com.LeetCode._0020_有效的括号;
import java.util.Stack;
/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

    Open brackets must be closed by the same type of brackets.
    Open brackets must be closed in the correct order.

Note that an empty string is also considered valid.

Example 1:

Input: "()"
Output: true

Example 2:

Input: "()[]{}"
Output: true

Example 3:

Input: "(]"
Output: false

Example 4:

Input: "([)]"
Output: false

Example 5:

Input: "{[]}"
Output: true

 */
public class Solution {
    // Time Complexity:O(n)
    // Space Complexity:O(1)
    public static boolean isValid(String s){
        char[] chars = s.toCharArray();
        if(chars.length == 0) return true;
        if(chars.length%2 == 1 || chars[0] == ')' || chars[0] == ']' || chars[0]== '}') return false;
        Stack<Character> stack=new Stack<Character>();
        for(int i=0;i<chars.length;i++){
            if(chars[i] == '{' || chars[i] == '(' || chars[i] == '['){
                stack.push(chars[i]);
            }else if (chars[i] == ')'){
                if (stack.pop() == '('){
                    continue;
                }
                else return false;
            }else if (chars[i] == ']'){
                if (stack.pop() == '['){
                    continue;
                }
                else return false;
            }else if (chars[i] == '}'){
                if (stack.pop() == '{'){
                    continue;
                }
                else return false;
            } else {
                //System.out.println(stack.pop().toString());
                return false;
            }
        }
        if (stack.size() == 0) {
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        String s1 = "()[]{}";
        String s2 = "([} }]}";
        System.out.println(isValid(s2));
    }
}