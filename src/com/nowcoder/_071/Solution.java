package com.nowcoder._071;
/*
字符串的排列

 */
import java.util.*;

public class Solution {
    static ArrayList<String> array = new ArrayList<>();
    static char[] c;

    static ArrayList<String> Permutation(String str) {
        if (str.length() == 0) return new ArrayList<>();

        c = str.toCharArray();
        helper(0);

        return array;
    }

    static void helper(int index) {
        if (index == c.length - 1) {
            array.add(String.valueOf(c));
            return;
        }
        HashSet<Character> set = new HashSet<>();
        for (int i = index; i < c.length; i++) {
            if (set.contains(c[i])) continue;
            set.add(c[i]);
            swap(index, i);
            helper(index + 1);
            swap(i, index);
        }
    }

    static void swap(int i, int j){
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(Permutation("abc").toString());
        //System.out.println(String.valueOf(new char[]{'a', 'b'}));
    }
}
