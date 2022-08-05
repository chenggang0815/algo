package LeetCode._0227_Basic_Calculator_II;
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

Solution: time: O(n) space:O(n)
-- Intuition
   3+2*3
   we should first consider * or / operation


1. iterate the string, when current char is digit, we use cur = cur * 10 + s[i] - '0' to calculate current number
   for example 123+1 when i belong to [0, 2], the number is 123
2. when we meet a sign, we need to calculate previous value, but we care about the previous sign,
   if previous sign is '+', we just push the current number, which is after the '+' into stack => 1 + 2 * 3
                                                                                                  0 1 2 3 4  i=3 current sign is '*'
                                                                                                             previous sign is '+', we push current 2 into stack
   if previous sign is '-' , we can simply think this sign is a minus '+', so we push the -cur into stack

   if previous sign is '/' or '*', we need to calculate stack.pop() * current, then push the value into stack.
   for example => 2 * 3 + 1
                  0 1 2 3 4 when i=3 current sign is '+'
                           previous sign is '*', current number is =3
                           we take stack.pop() * 3 = 2 * 3 = 6, then push 6 into stack
3. corner case,
   3.1 the initially sign is '+', we always need to add the first number into stack
   3.2 when current char is ' ', ignore it
   3.3 when current char is the last digit, we also need to calculate previous value

遍历字符串，把+和-的数组放进栈中，先计算*和/的结果，并且把结果也放入栈中，最后遍历栈，把栈中的元素全部相加得到答案
1. 遍历字符串s，并用变量preOp记录每个数字之前的运算符，对于第一个数字，其之前的运算符视为：+
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
        char preSign = '+';
        Stack<Integer> stack = new Stack<>();
        int currentNUmber = 0;
        for (int i = 0; i < s.length(); i++){
            if (Character.isDigit(s.charAt(i))){
                currentNUmber = currentNUmber * 10 + s.charAt(i) - '0';
            }

            if ((!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ') || i == s.length() - 1){ // 特殊情况：最后一个位置的字符一定为数字，但是一定要遍历到，
                if (preSign == '+') stack.push(currentNUmber);
                else if (preSign == '-') stack.push(-currentNUmber);
                else if (preSign == '*') stack.push(stack.pop() * currentNUmber);
                else if (preSign == '/') stack.push(stack.pop() / currentNUmber);

                currentNUmber = 0;
                preSign = s.charAt(i);
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
