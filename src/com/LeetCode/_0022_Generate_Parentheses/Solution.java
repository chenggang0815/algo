package com.LeetCode._0022_Generate_Parentheses;

import java.util.ArrayList;
import java.util.List;
/*
22. Generate Parentheses
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

Example 1:
Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]

Example 2:
Input: n = 1
Output: ["()"]

思路1：暴力法
思路2：回溯
我们可以只在序列仍然保持有效时才添加 '(' 或者 ')'，而不是像 方法一 那样每次添加。
我们可以通过跟踪到目前为止放置的左括号和右括号的数目来做到这一点：
    2.1 如果左括号数量不大于n，我们可以放一个左括号
    2.2 如果右括号数量小于左括号的数量，我们可以放一个右括号。
 */
public class Solution {
    static void dfs(List<String> res, int n, String track, int left, int right){
        if (track.length() == n * 2){
            res.add(track);
            return;
        }
        if (left < n){
            dfs(res, n, track + "(", left + 1, right);
        }
        if (left > right){
            dfs(res, n, track + ")", left, right + 1);
        }
    }
    static List<String> generateParenthesis(int n){
        List<String> res = new ArrayList<>();
        dfs(res, n, "", 0, 0);
        return res;
    }

    public static void main(String[] args) {
       System.out.println(generateParenthesis(3));
    }
}
