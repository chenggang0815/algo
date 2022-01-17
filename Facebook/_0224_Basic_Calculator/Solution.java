package Facebook._0224_Basic_Calculator;

import java.util.Stack;

/*
224. Basic Calculator
Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().

Example 1:
Input: s = "1 + 1"
Output: 2

Example 2:
Input: s = " 2-1 + 2 "
Output: 3

Example 3:
Input: s = "(1+(4+5+2)-3)+(6+8)"
Output: 23
*/
/*
Solution
Approach 1: stack
*/
public class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int sign = 1;
        int result = 0;
        int number = 0;
        for(int i = 0; i < s.length(); i++){
            if (Character.isDigit(s.charAt(i))) {
                int sum = s.charAt(i) - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    sum = sum * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                result += sum * sign;
            }else if(s.charAt(i) == '+') sign = 1;
            else if (s.charAt(i) == '-') sign = -1;
            else if(s.charAt(i) == '('){
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            }else if(s.charAt(i) == ')'){
                result = result * stack.pop() + stack.pop();
            }
        }

        return result;
    }
    public static void main(String[] args) {

    }
}
