package 剑指Offer._982_反转一个字符串;
/*
反转一个字符串，不能用额外的空间
 */
public class Solution {
    static private String reverse(String str){
        String newStr = "";
        for (int i=str.length()-1;i>=0;i--){
            newStr = newStr + String.valueOf(str.charAt(i));
        }
        return newStr;
    }
    public static void main(String[] args) {
        System.out.println(reverse("hello"));
    }
}
