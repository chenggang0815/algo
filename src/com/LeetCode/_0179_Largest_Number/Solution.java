package com.LeetCode._0179_Largest_Number;
/*
179. Largest Number

Given a list of non-negative integers nums, arrange them such that they form the largest number.
Note: The result may be very large, so you need to return a string instead of an integer.

Example 1:
Input: nums = [10,2]
Output: "210"

Example 2:
Input: nums = [3,30,34,5,9]
Output: "9534330"

Example 3:
Input: nums = [1]
Output: "1"

Example 4:
Input: nums = [10]
Output: "10"
*/

import java.util.Arrays;

/*
思路：
需要证明：Let a, b, and c be non-negative integers. If a.b > b.a and b.c > c.b , we have a.c > c.a .
然后再用排序算法，保证排序完后的数组，任意两个相邻的元素满足 => str(a)+str(b) > str(b)+str(a)
*/
public class Solution {
    //Runtime: 20 ms, faster than 5.02% of Java online submissions for Largest Number.
    static String largestNumber(int[] nums) {
        int len = nums.length;
        String[] str = new String[len];
        for (int i = 0; i < len; i++){
            str[i] = String.valueOf(nums[i]);
        }

        for (int i = 0; i < str.length; i++){
            for (int j  = 0; j < str.length - i - 1; j++){
                String a = str[j];
                String b = str[j + 1];

                if (Long.parseLong(a+b) < Long.parseLong(b+a)){ // 由于a+b可能会溢出，所以用long表示，更好的办法是改写字符串的compareTo()方法来直接对拼接后的字符串进行比较
                    str[j + 1] = a;
                    str[j] = b;
                }
            }
        }

        if (str[0].equals("0")) return "0"; // 如果为0，提前返回"0"

        StringBuilder res = new StringBuilder();
        for (String s : str) {
            res.append(s);
        }

        return res.toString();
    }

    public static void main(String[] args) {
        //System.out.println(largestNumber(new int[]{999999991,9}));
        System.out.println(largestNumber(new int[]{0,0}));
    }
}
