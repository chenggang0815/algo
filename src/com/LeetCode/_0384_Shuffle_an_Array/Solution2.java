package com.LeetCode._0384_Shuffle_an_Array;

import java.util.Random;
/*
时间复杂度 ： O(n)
Fisher-Yates 洗牌算法时间复杂度是线性的，因为算法中生成随机序列，交换两个元素这两种操作都是常数时间复杂度的。

空间复杂度： O(n)
因为要实现重置功能，原始数组必须得保存一份，因此空间复杂度并没有优化
*/
public class Solution2 {
    private int[] array;
    private int[] original;

    Random rand = new Random();

    private int getRandomIndex(int left, int right){
        return rand.nextInt(right - left) + left;
    }

    private void swap(int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public Solution2(int[] nums){
        array = nums;
        original = nums.clone();
    }

    public int[] reset(){
        array = original;
        original = original.clone();
        return array;
    }

    public int[] shuffle(){
        for (int i = 0; i < array.length; i++){
            swap(i,  getRandomIndex(i, array.length));
        }
        return array;
    }

}
