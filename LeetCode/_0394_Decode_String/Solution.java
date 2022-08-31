package LeetCode._0394_Decode_String;
/*
394. Decode String

Given an encoded string, return its decoded string.
The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc.
Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k.
For example, there will not be input like 3a or 2[4].

The test cases are generated so that the length of the output will never exceed 10^5.

Example 1:
Input: s = "3[a]2[bc]"
Output: "aaabcbc"

Example 2:
Input: s = "3[a2[c]]"
Output: "accaccacc"

Example 3:
Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"

Example 4:
Input: s = "abc3[cd]xyz"
Output: "abccdcdcdxyz"
*/


import java.util.Stack;

/*
思路1 ： 栈方法

本题中可能出现括号嵌套的情况，比如2[a2[bc]]，这种情况下我们可以先转化成2[abcbc]，在转化成abcbcabcbc。
我们可以把字母、数字和括号看成是独立的TOKEN，并用栈来维护这些TOKEN。
具体的做法是，遍历这个栈：
1. 如果当前的字符为数位，解析出一个数字（连续的多个数位）并进栈
2. 如果当前的字符为字母或者左括号，直接进栈
3. 如果当前的字符为右括号，开始出栈，一直到左括号出栈，出栈序列反转后拼接成一个字符串，此时取出栈顶的数字（此时栈顶一定是数字，想想为什么？），就是这个字符串应该出现的次数，我们根据这个次数和字符串构造出新的字符串并进栈

=> 数字存放在数字栈，字符串存放在字符串栈，遇到右括号时候弹出一个数字栈，字母栈弹到左括号为止。就是逆波兰式那种题。

 */
public class Solution {
    // 数字存放在数字栈，字符串存放在字符串栈，遇到右括号时候弹出一个数字栈，字母栈弹到左括号为止
    // 2[a2[bc]]
    static String decodeString(String s) {
        Stack<StringBuilder> strStack = new Stack<>();
        Stack<Integer> intStack = new Stack<>();
        StringBuilder cur = new StringBuilder();
        int digits = 0;
        for (char ch: s.toCharArray()){
            if (Character.isDigit(ch)){
                digits = digits * 10 + (ch - '0');
            }else if(ch == '['){ // "3[a2[c]]" 在当前字符=='['时，需要把前面的字符串和重复次数添加到栈里，并且重置字符串和重复次数
                intStack.push(digits);
                strStack.push(cur);
                cur = new StringBuilder();
                digits = 0;
            }else if (ch == ']'){ // 在当前字符==']'时，需要从栈中取出已有的字符串，拼接多次当前的字符串；然后更新当前字符串
                StringBuilder temp = strStack.pop();
                int repeatTimes = intStack.pop();
                for (int i = 0; i < repeatTimes; i++){
                    temp.append(cur);
                }
                cur = temp;
            }else {
                cur.append(ch);
            }
        }

        return cur.toString();
    }

    public static void main(String[] args) {

        System.out.println(decodeString("3[a]2[bc]"));
        String temp = "hello";
        for (int i = 0; i < 2; i++){
            temp += temp;
        }
        System.out.println(temp);
    }
}
