package com.nowcoder._075;
/*
去掉字符串中多余的空格

input： "  hello   world"

output: "hello world"
 */
public class Solution {

    //time: o(n) space: o(1)

    static String deleteSpace(String s){
        if (s.length() == 0) return "";

        s = s.trim();
        StringBuilder res = new StringBuilder();
        int left = 0;
        int right = 0;
        while (left < s.length()) {
            while (right < s.length() && s.charAt(right) != ' ') right++;
            res.append(s, left, right).append(" ");
            while (right < s.length() && s.charAt(right) == ' ') right++;
            left = right;
        }

        return res.toString().trim();
    }


    public static void main(String[] args) {
        String s = " I  am     chenggang        ";
        System.out.println(deleteSpace(s));
    }
}
