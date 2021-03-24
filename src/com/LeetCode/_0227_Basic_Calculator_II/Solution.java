package com.LeetCode._0227_Basic_Calculator_II;
/*
227. Basic Calculator II

Given a string s which represents an expression, evaluate this expression and return its value.
The integer division should truncate toward zero.
Example 1:
Input: s = "3+2*2"
Output: 7

Example 2:
Input: s = " 3/2 "
Output: 1

Example 3:
Input: s = " 3+5 / 2 "
Output: 5

Constraints:
1 <= s.length <= 3 * 105
s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
s represents a valid expression.
All the integers in the expression are non-negative integers in the range [0, 231 - 1].
The answer is guaranteed to fit in a 32-bit integer.

*/

import java.util.Stack;

/*
Example 1:
Input: s = "3+2*2"
Output: 7

3
+ [3] pre=+
2 num=2
* pre=+ => [3,2] => pre=*
2
思路： 栈 time: o(n) space:o(n)
遍历字符串，把+和-的数组放进栈中，先计算*和/的结果，并且把结果也放入栈中，最后遍历栈，把栈中的元素全部相加得到答案
1. 遍历字符串 ss，并用变量preOp记录每个数字之前的运算符，对于第一个数字，其之前的运算符视为：+
2. 每次遍历到数字后一个位置时 => 即当前位置为操作符：+、-、*、/时，根据preSign 来决定计算方式：
    2.1 加号：将数字压入栈
    2.2 减号：将数字的相反数压入栈
    2.3 乘除号：计算数字与栈顶元素，并将栈顶元素替换为计算结果

3. 处理完该数字后，preSign更新为当前位置的操作符

4. 遍历完字符串s后，将栈中元素累加，即为该字符串表达式的值

特殊情况：最后一个位置的字符一定为数字，但是一定要遍历到
*/
public class Solution {

    static int calculate(String s) {
        char preOp = '+';
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        for (int i = 0; i < s.length(); i++){
            if (Character.isDigit(s.charAt(i))){
                num = num * 10 + s.charAt(i) - '0';
            }

            if ((!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ') || i == s.length() - 1){ // 特殊情况：最后一个位置的字符一定为数字，但是一定要遍历到，
                if (preOp == '+') stack.push(num);
                else if (preOp == '-') stack.push(-num);
                else if (preOp == '*') stack.push(stack.pop() * num);
                else if (preOp == '/') stack.push(stack.pop() / num);

                num = 0;
                preOp = s.charAt(i);
            }
        }

        int res = 0;
        while (!stack.isEmpty()){
            res += stack.pop();
        }


        return res;

    }

    public static void main(String[] args) {
        System.out.println(calculate("3+2*2"));
    }
}
