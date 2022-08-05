package Other._982_反转一个字符串;
/*
similar to lc 344
 */
public class Solution {
    // time: O(n) space:O(1) => 反转一个字符串，不能用额外的空间
    static String reverse1(String str){
        String newStr = "";
        for (int i = str.length() - 1; i >= 0; i--){
            newStr = newStr + String.valueOf(str.charAt(i));
        }
        return newStr;
    }

    // recursion time: O(n^2) space:O(n) => 递归反转一个字符串
    static String reverse2(String str){
        if (str.length() <=1) return str;

        return reverse2(str.substring(1)) + str.charAt(0);
    }

    public static void main(String[] args) {
        System.out.println(reverse1("hello"));
    }
}
