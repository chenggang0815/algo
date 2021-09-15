package LeetCode._0125_Valid_Palindrome;
/*
125. Valid Palindrome

Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:
Input: "A man, a plan, a canal: Panama"
Output: true

Example 2:
Input: "race a car"
Output: false
 */
/*
1. 设置左、右双指针，向中间判断；
2. 跳过非数字字母的字符；
3. 将字母全部转化为小写体，之后判断。
4. java用了库函数，python纯自己实现（运行时间不太理想）
 */
public class Solution {
    static boolean isPalindrome(String s){
        int left = 0, right = s.length() - 1;
        while (left < right){
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) left++;
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) right--;
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))){
                return false;
            }
            left++; right--;
        }

        return true;
    }
    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
    }
}
