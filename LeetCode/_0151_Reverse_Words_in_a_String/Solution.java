package LeetCode._0151_Reverse_Words_in_a_String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
151. Reverse Words in a String
Given an input string s, reverse the order of the words.
A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
Return a string of the words in reverse order concatenated by a single space.
Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.

Example 1:
Input: s = "the sky is blue"
Output: "blue is sky the"

Example 2:
Input: s = "  hello world  "
Output: "world hello"
Explanation: Your reversed string should not contain leading or trailing spaces.
 */
/*
Solution:
Approach 1 :
1. trim()
2. split()
3. from left to right, if ch[i].length() != 0 => append in StringBuilder => edge case
4. sb.append(ch[i]).append(" ")
5. sb.delete(sb.length() - 1).toString() // delete the last " "
 */
public class Solution {
    static String reverseWords(String s) {
        s = s.trim();
        String[] str = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = str.length - 1; i >= 0; i--){
            if (str[i].length() == 0) continue;
            sb.append(str[i]).append(" ");
        }

        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public String reverseWords2(String s) {
        List<String> list = new ArrayList<>();
        int left = 0;
        int right = s.length() - 1;
        while(s.charAt(left) == ' ' && left < s.length()) left++;
        while(s.charAt(right) == ' ' && right >= 0) right--;

        int i = left;
        while(i <= right){
            if(s.charAt(i) == ' '){
                list.add(s.substring(left, i));
                while(i <= right && s.charAt(i) == ' ') i++;
                left = i;
            }else{
                i++;
            }
        }
        list.add(s.substring(left, i));

        StringBuilder res = new StringBuilder();
        for(int j = list.size() - 1; j >= 0; j--) res.append(list.get(j)).append(" ");

        return res.deleteCharAt(res.length() - 1).toString();
}

    public static void main(String[] args) {
        //System.out.println(reverseWords("a good   example"));

        System.out.println(Arrays.toString("a good    example a   b".split(" ")));


    }
}
