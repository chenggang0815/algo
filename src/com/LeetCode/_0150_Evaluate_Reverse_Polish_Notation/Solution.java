package com.LeetCode._0150_Evaluate_Reverse_Polish_Notation;

import java.util.Stack;

/*
Example 1:
Input: tokens = ["2","1","+","3","*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
*/

/*
“==”比较两个变量本身的值，即两个对象在内存中的首地址。
“equals()”比较字符串中所包含的内容是否相同。
比如：
String s1,s2,s3 = "abc", s4 ="abc" ;
s1 = new String("abc");
s2 = new String("abc");
那么：
s1==s2 是 false //两个变量的内存地址不一样，也就是说它们指向的对象不 一样，
故不相等。
s1.equals(s2) 是 true //两个变量的所包含的内容是abc，故相等

if (tokens[i] == "+" || tokens[i] == "-" || tokens[i] == "*" || tokens[i] =="/")
 */
public class Solution {
    static int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < tokens.length; i++){
            if (tokens[i].equals("+") || tokens.equals("-") || tokens[i].equals("*") || tokens[i].equals("/")){

                int a = Integer.parseInt(stack.pop());
                int b = Integer.parseInt(stack.pop());
                if (tokens[i].equals("+")){
                    stack.push(String.valueOf(b + a));
                }else if (tokens[i].equals("-")){
                    stack.push(String.valueOf(b - a));
                }else if (tokens[i].equals("*")){
                    stack.push(String.valueOf(b * a));
                }else{
                    stack.push(String.valueOf(b / a));
                }
            }else stack.push(tokens[i]);
        }

        return Integer.parseInt(stack.pop());
    }

    public static void main(String[] args) {
        System.out.println(evalRPN(new String[]{"2","1","+","3","*"}));
    }
}
