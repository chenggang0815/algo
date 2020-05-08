package com.kernel.basic_java;

public class string {
    public static void main(String[] args) {
        String str = "this is a string";
        //1. int length()  获取字符串的长度
        System.out.println(str.length());
        //2. char charAt(int index) 获取特定位置的字符 (注意角标越界问题)
        System.out.println(str.charAt(0));
        //3. boolean isEmpty()是否长度为0
        System.out.println(str.isEmpty());
        //4. 将字符数组转换为字符串
        String s = String.valueOf(new char[]{'a','b'});
        System.out.println(s);
        //5. 将字符串转为字符(char)数组
        System.out.println(str.toCharArray()[0]);

    }
}
