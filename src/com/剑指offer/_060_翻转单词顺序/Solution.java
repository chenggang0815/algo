package com.剑指offer._060_翻转单词顺序;
/*
翻转单词顺序

"student. a am I" => "I am a student."

思路: 双指针遍历
1. 从末位开始，以" "为界，找到每个单词的起始位置
2. j从i开始遍历到" "，单词就是[j+1,i]
3. j继续遍历" " (如果只有一个空格，则j-1，res.append(' ')),
4. 令 i = j，开始寻找下一个单词
 */
public class Solution {

    // time: o(n) space: o(n) stringbuilder
    static String ReverseSentence(String str) {
        if(str.length() == 0) return str;

        StringBuilder res = new StringBuilder();
        int i = str.length() - 1;
        int j = i;
        while (i >= 0){
            while (j >= 0 && str.charAt(j) != ' '){
                j--;  //从末位开始遍历到第一个' '
            }
//            for (int index = j + 1; index <= i; index++){
//                res.append(str.charAt(index)); //从第一个' '的后一位开始，遍历到i就是整个单词
//            }
            res.append(str, j + 1, i + 1);
            while (j >= 0 && str.charAt(j) == ' '){
                j--;
                res.append(' '); //遍历完' ',更新i
            }
            i = j;
        }

        return res.toString();
    }

    static String ReverseSentence2(String str) {
        if(str.length() == 0) return str;

        StringBuilder res = new StringBuilder();
        int i = str.length() - 1;
        int j = i;
        while (i >= 0){
            while (j >= 0 && str.charAt(j) != ' '){
                j--;
            }
            for (int index = j+1; index <= i; index++){
                res.append(str.charAt(index));
            }
            if (j >= 0) {
                j--;
                res.append(" "); //因为此题中每个单词间只有一个' ', 所以直接j - 1就可以
            }
            i = j;
        }

        return res.toString();
    }

    public static void main(String[] args) {
        String str = "I am a student.";
                    //0123456789
        System.out.println(ReverseSentence(str));
        StringBuilder sb = new StringBuilder();
        System.out.println(sb.append("hello", 0, 2));

    }
}
