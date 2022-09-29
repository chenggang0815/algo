package LeetCode._0017_Letter_Combinations_of_a_Phone_Number;

import java.util.ArrayList;
import java.util.List;
/*
17. Letter Combinations of a Phone Number

Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
Return the answer in any order.

输入：digits = "23"
输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]

思路1： dfs（回溯）
对于回溯来说，需要画图理解每次搜索的路径，在哪撤销回退，在哪里达到搜索的终点

对于这道题来说，需要注意的点：
1. 输入的数字，但是遍历的是数字对应的多个字符，所以可以用一个数组把数字和字符串对应起来
2. 对于一个字符串，每次都是从下一个字符串的最左边开始遍历，所以for循环从i=0开始，并且下次遍历从下一个数字开始

思考s.deleteCharAt(index)和s.deleteCharAt(s.length() - 1)为什么相同

思路2：bfs（队列）
 */
public class Solution {

    static List<String> letterCombinations(String digits){
        String[] letterMap = new String[]{"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        List<String> res = new ArrayList<>();
        if (digits.length() == 0) return res;
        backtrack(digits, letterMap, res, new StringBuilder(), 0);

        return res;
    }

    static void backtrack(String digits, String[] letterMap, List<String> res, StringBuilder s, int index){
        if (s.length() == digits.length()){
            res.add(s.toString());
            return;
        }

        char digit = digits.charAt(index);
        String letters = letterMap[digit - '0'];
        for(int i = 0; i < letters.length(); i++){
            s.append(letters.charAt(i));
            backtrack(digits, letterMap, res, s, index + 1);
           //s.deleteCharAt(index);
            s.deleteCharAt(s.length() - 1);
        }
    }

    //思路2 bfs
    // digits = "23" => '2' = "abc"  '3' = "def"
    static List<String> letterCombinations2(String digits){
        String[] letterMap = new String[]{"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        List<String> res = new ArrayList<>();
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
