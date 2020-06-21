package com.nowcoder._071;
/*
字符串的排列
输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。

思路： 回溯算法
1. 假设有abc ， 依次固定 a b c， 再分别从index=[0,nums.length-1];i=[index,nums.length-1];
2. 两两交换 swap(index, i)
3. 对于当前固定的元素，如果在hashset中，则跳过，避免重复计算
 */
import java.util.*;

public class Solution {
    static ArrayList<String> array = new ArrayList<>();
    static char[] c;
    //time: O(N!) space: o(N^2) 递归深度为N，且每次递归都需要创建hashset
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
