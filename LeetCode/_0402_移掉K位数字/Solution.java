package LeetCode._0402_移掉K位数字;

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


思路：
1. 从左到右遍历每一个数字，如果当前数字比上一个数字小，则删除上一个数字，可以使数字更小；否则不能删除数字
 eg： 1432219  k = 3
      append(1)
 序列：1  1 < 4  append(4)
      1,4 4 > 3 delete(4) append(3) k=2
      1,3 3 > 2 delete(3) append(2) k=1
      1,2 2 = 2 append(2)
      1,2,2 2 > 1 delete(2) append(1) k=0
      1,2,1
      k = 0 append(9)
      1,2,1,9

2. 对于 12345 这样单调递增的数字，需要遍历完取前k个
3. 如果当前栈为空且当前数字为0时，不能加入栈中（不能有任何前导零）
 */
public class Solution {

    static String removeKdigits(String num, int k) {
        if(num.length() == k) return "0";

        StringBuilder stack = new StringBuilder();

        for(int i = 0; i < num.length(); i++){
            char ch = num.charAt(i);
            while(k > 0 && stack.length() != 0 && stack.charAt(stack.length() - 1) > ch){
                    stack.deleteCharAt(stack.length() - 1);
                    k--;
            }
            if(ch == '0' && stack.length() == 0) continue;
            stack.append(ch);
        }

        String res = stack.substring(0, stack.length() - k);

        return res.length() == 0 ? "0" : res;
    }

    public static void main(String[] args) {

        String num = "10086";
        int k = 2;

        System.out.println(removeKdigits(num, k));

    }

}




