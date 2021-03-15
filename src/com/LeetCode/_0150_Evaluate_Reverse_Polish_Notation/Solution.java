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

/*
@beOkWithAnything
String实例是对象，判断String相等就应该用equals()而不是简单的==。
== 是判断两个引用指向的是不是一个对象。至于 为什么ide能用 == 判断，肯定是因为你们用了
String[] s = {"1"};
的形式创建的字符串数组，此时"1"被放在常量池，在evalRPN函数再用"1"会直接指向常量池里之前创建的"1"，
即两个引用指向的是同一个对象，所以能相等。
而leetcode上tokens里的字符串都是通过 new String("1");方式创建的对象，所以==不相等必须要equals，至于jdk版本，，，，依我看不是jdk的问题
 */
public class Solution {
    // Runtime: 4 ms, faster than 92.22% of Java online submissions for Evaluate Reverse Polish Notation.
    static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < tokens.length; i++){
            String s = tokens[i];
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")){
                int a = stack.pop();
                int b = stack.pop();
                if (s.equals("+")){
                    stack.push(b + a);
                }else if (s.equals("-")){
                    stack.push(b - a);
                }else if (s.equals("*")){
                    stack.push(b * a);
                }else{
                    stack.push(b / a);
                }
            }else stack.push(Integer.valueOf(s));
        }

        return stack.pop();
    }

    /*
        public int evalRPN(String[] tokens) {
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
     */

    public static void main(String[] args) {
        System.out.println(evalRPN(new String[]{"2","1","+","3","*"}));
    }
}
