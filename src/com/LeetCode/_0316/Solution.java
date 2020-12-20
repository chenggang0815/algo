package com.LeetCode._0316;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/*
316. 去除重复字母
给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。

注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同

示例 1：
输入：s = "bcabc"
输出："abc"
示例 2：

输入：s = "cbacdcbc"
输出："acdb"

提示：
1 <= s.length <= 104
s 由小写英文字母组成
 */
public class Solution {
    static void removeDuplicateLetters(String s) {
         HashMap<Character, Integer> map = new HashMap<>();
         for(int i = 0; i < s.length(); i++){
             int temp = map.getOrDefault(s.charAt(i), 0) + 1;
             map.put(s.charAt(i), temp);
         }
        System.out.println(map);
        Stack<Character> stack = new Stack<>();
         for(int i = 0; i < s.length(); i++){
             Character ch = s.charAt(i);
             if (!stack.contains(ch)){
                 if(!stack.isEmpty() && stack.peek() > ch &&  map.get(stack.peek()) > 0){
                     stack.pop();
                     stack.push(ch);
                 }else{
                     stack.push(ch);
                 }
                 map.put(ch, map.get(ch) - 1);
             }
         }

        System.out.println(stack.toString());
    }
    public static void main(String[] args) {
        removeDuplicateLetters("bcabc");
    }
}
