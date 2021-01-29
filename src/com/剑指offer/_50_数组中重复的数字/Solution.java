package com.剑指offer._50_数组中重复的数字;

import java.util.Arrays;
import java.util.HashSet;

/*

ps ： solution2 无法返回数组中第一个重复的数字

1. 牛客网- 剑指offer
在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。

=> 请找出数组中第一个重复的数字

返回描述：
如果数组中有重复的数字，函数返回true，否则返回false。
如果数组中有重复的数字，把重复的数字放到参数duplication[0]中。（ps:duplication已经初始化，可以直接赋值使用。）

input {2,3,1,0,2,5,3} => output 2
input [6,3,2,0,2,5,0] => output 2

2. leetcode - 剑指 Offer 03. 数组中重复的数字
在一个长度为n的数组nums里的所有数字都在 0～n-1 的范围内。
数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。

=> 请找出数组中任意一个重复的数字

https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
示例 1：
输入：
[2, 3, 1, 0, 2, 5, 3]
输出：2 或 3

solution 2:原地交换
hash(nums[i]) => nums[nums[i] - 1]
参考leetcode 41题
 */
public class Solution {
    // time:o(n) space:o(n)
    // 1. 牛客网- 剑指offer
    static boolean duplicate1(int numbers[],int length,int [] duplication) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < length; i++){
            if (set.contains(numbers[i])){
                duplication[0] = numbers[i];
                return true;
            }

            set.add(numbers[i]);
        }

        return false;
    }
    // time:o(n) space:o(1)
    // 1. leetcode- 剑指offer
    static int duplicate2(int nums[]) {
        for (int i = 0; i < nums.length; i++){
            int temp = nums[i];
            nums[i] = nums[nums[i]];
            nums[temp] = temp;

            if (nums[i] != i && nums[i] == nums[nums[i]]){
                return nums[i];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(duplicate2(new int[]{2,3,1,0,2,5,3}));
    }
}
