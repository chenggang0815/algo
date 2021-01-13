package com.LeetCode._0017_Letter_Combinations_of_a_Phone_Number;

import java.util.ArrayList;
import java.util.List;
/*
17. Letter Combinations of a Phone Number

Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
Return the answer in any order.

思路1： dfs（回溯）
对于回溯来说，需要画图理解每次搜索的路径，在哪撤销回退，在哪里达到搜索的终点
思考s.deleteCharAt(index)和s.deleteCharAt(s.length() - 1)为什么相同

思路2：bfs（队列）
 */
public class Solution {
    static String[] letterMap = new String[]{"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    static List<String> res = new ArrayList<>();
    static List<String> letterCombinations(String digits){
        if (digits.length() == 0) return res;
        backtrack(digits, new StringBuilder(), 0);
        return res;
    }

    static void backtrack(String digits, StringBuilder s, int index){
        if (index == digits.length()){
            res.add(s.toString());
            return;
        }
        char digit = digits.charAt(index);
        String letters = letterMap[digit - '0'];
        for(int i = 0; i < letters.length(); i++){
            s.append(letters.charAt(i));
           backtrack(digits, s, index + 1);
           //s.deleteCharAt(index);
            s.deleteCharAt(s.length() - 1);
        }
    }

    //思路2 bfs
    // digits = "23" => '2' = "abc"  '3' = "def"
    static List<String> letterCombinations2(String digits){
        res.add("");
        for (int i = 0; i < digits.length(); i++){
            int size = res.size();//需要固定好队列的大小
            for (int j = 0; j < size; j++){
                String temp = res.get(0);
                res.remove(0); //队列第一个元素出队列
                String letters = letterMap[digits.charAt(i) - '0'];
                for (int index = 0; index < letters.length(); index++){
                    res.add(temp + letters.charAt(index));
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations2("23"));
    }
}
