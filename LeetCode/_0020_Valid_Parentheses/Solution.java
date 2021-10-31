package LeetCode._0020_Valid_Parentheses;
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
        Stack<Character> stack = new Stack<>();
        for(Character c : s.toCharArray()){
            if(c.equals(')') || c.equals(']') || c.equals('}')){
                if(stack.isEmpty()) return false;
                if(c.equals(')') && stack.peek().equals('(')){
                    stack.pop();
                }else if(c.equals(']') && stack.peek().equals('[')){
                    stack.pop();
                }else if(c.equals('}') && stack.peek().equals('{')){
                    stack.pop();
                }else return false;
            }else{
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s1 = "()[]{}";
        String s2 = "([} }]}";
        System.out.println(isValid(s2));
    }
}