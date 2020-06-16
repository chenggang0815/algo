package com.nowcoder._059;

import java.util.HashMap;

/*
判断是否有顺子，规则: 5张牌，0可以代替任何数

思路：
1. 除0之外不能有重复值
2. 最大值 - 最小值 < 5
 */
public class Solution {
    static boolean isContinuous(int [] nums) {
        if (nums.length != 5) return false;

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            if (map.get(nums[i]) > 1 && nums[i] != 0){
                return false;
            }
            if (nums[i] > max && nums[i] != 0){
                max = nums[i];
            }
            if (nums[i] < min && nums[i] != 0){
                min = nums[i];
            }
            if (max - min >= 5) return false;
        }

        return true;
    }

    public static void main(String[] args) {

    }
}
