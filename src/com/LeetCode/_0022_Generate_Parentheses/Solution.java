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

https://leetcode-cn.com/problems/generate-parentheses/solution/hui-su-suan-fa-by-liweiwei1419/

思路1：暴力法
思路2：dfs + 剪枝 + 做减法 => 由于字符串的特殊性，产生一次拼接都生成新的对象，因此无需回溯
                        => 如果用stringbuilder呢？
    2.1 做减法 left和right分别表示左右括号剩余的数量
            "" n=2 left=right=2
           /  \
          (    ) left=2 right=1 => left > right => 剪枝
         /  \  x
       ((   ()
       /   /   \
     (()  ()(  ()) left=1 right=0 => left > right => 剪枝
     /    /     x
    (()) ()()

    1. 当左括号剩余数量大于右括号剩余数量时，已经不能生成有效的括号，直接剪枝 => if(left > right) return;
    2. 当左右括号剩余数量大于0时，可以产生分支
    3. 当左右括号剩余数量都等于0时，递归停止，把字符加入res

思路3：dfs + 剪枝 + 做加法

思路4：bfs => 创建结点对象，使用队列完成广度优先遍历

 */
public class Solution {
    // 思路2 dfs + 剪枝 + 做减法
    static List<String> generateParenthesis1(int n){
        List<String> res = new ArrayList<>();
        if (n == 0) return res; // 边界条件

        dfs1(res, n, "", n, n);
        return res;
    }

    static void dfs1(List<String> res, int n, String s, int left, int right){
        if (left == 0 && right == 0){
            res.add(s);
            return;
        }
        // 剪枝
        if (left > right) return;

        if (left > 0){
            dfs1(res, n, s + "(", left - 1, right);
        }
        if (right > 0){
            dfs1(res, n, s + ")", left, right - 1);
        }
    }

    //思路3 dfs + 剪枝 + 做加法
    static List<String> generateParenthesis2(int n){
        List<String> res = new ArrayList<>();
        if (n == 0) return res; // 边界条件

        dfs2(res, n, "", 0, 0);
        return res;
    }
    static void dfs2(List<String> res, int n, String s, int left, int right){
        if (left == n && right == n){
            res.add(s);
            return;
        }
        if (left < right) return;

        if (left < n){
            dfs2(res, n, s + "(", left + 1, right);
        }
        if (right < n){
            dfs2(res, n, s + ")", left, right + 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis2(2));
    }
}
