package Other._43_左旋转字符串;
/*
左旋转字符串
对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”

思路:因为存在 K > str.length() ； 所以对 index = K % str.length() => str = [index, str.length) + [0,index）
 */
public class Solution {
    
    static String LeftRotateString1(String str,int n) {
        if (str.length() == 0) return str;

        int index = n % str.length();
        return str.substring(index, str.length()) + str.substring(0, index);
    }

    static String LeftRotateString2(String str,int n) {
        if (str.length() == 0) return str;

        StringBuilder s = new StringBuilder();
        int index = n % str.length();
        for (int i = index; i < str.length(); i++){
            s.append(str.charAt(i));
        }
        for (int i = 0; i < index; i++){
            s.append(str.charAt(i));
        }

        return s.toString();
    }

    public static void main(String[] args) {
        String str= "abcdef";
        System.out.println(LeftRotateString2(str, 3));
    }
}
