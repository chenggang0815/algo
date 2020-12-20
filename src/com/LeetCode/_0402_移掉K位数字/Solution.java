package com.LeetCode._0402_移掉K位数字;

import java.util.Stack;

/*
402. 移掉K位数字
给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。

注意:
num 的长度小于 10002 且 ≥ k。
num 不会包含任何前导零。
示例 1 :

输入: num = "1432219", k = 3
输出: "1219"
解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
示例 2 :

输入: num = "10200", k = 1
输出: "200"
解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
示例 3 :

输入: num = "10", k = 2
输出: "0"
解释: 从原数字移除所有的数字，剩余为空就是0。
 */
public class Solution {
    static String removeKdigits(String num, int k) {
            if(num.length() == k)       return "0";

            StringBuilder stack = new StringBuilder();
            //int remains = num.length() - k;

            for(int i = 0; i < num.length(); i++){
                char ch = num.charAt(i);
                while(k > 0 && stack.length() != 0 && stack.charAt(stack.length() - 1) > ch){
                    stack.setLength(stack.length() - 1);
                    k--;
                }
                if(ch == '0' && stack.length() == 0)    continue;
                stack.append(ch);
            }

            System.out.println("===ccc=====");

            System.out.println(stack.length());

            System.out.println("===ccc=====");

            System.out.println(k);

            System.out.println("====ccc======");

            String res = stack.substring(0, stack.length() - k).toString();

            return res.length() == 0 ? "0" : res;
        }
 // 10200 1
    static String removeKdigits2(String num, int k) {
        if(num.length() == k)       return "0";

        Stack<Character> stack = new Stack<>();
        int index = num.length() - k;

        for(int i = 0; i < num.length(); i++){
            while (k > 0 && !stack.isEmpty() && stack.peek() > num.charAt(i)){
                stack.pop();
                k--;
            }
            if (num.charAt(i) == '0' && stack.isEmpty()) continue;
            stack.push(num.charAt(i));
        }


        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }

        //System.out.println(sb);
        String res = sb.reverse().toString();
        //System.out.println(res);

        String res2 = res.substring(0, res.length() - k);
        return res2.length() == 0 ? "0": res2;
    }
    public static void main(String[] args) {

        String num = "10";
        int k = 2;


        //System.out.println(removeKdigits(num, k));
        System.out.println(removeKdigits2("112", 1));

    }

}




