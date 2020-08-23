package com.剑指offer._041;

import java.util.ArrayList;
import java.util.Arrays;

/*
输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。

ps : 如果不要求位置不变，可以使用双指针法
 */
public class Solution {

    //time : o(n) space:o(n)
    static public void reOrderArray(int [] array) {
        ArrayList<Integer> array1 = new ArrayList<>();
        ArrayList<Integer> array2 = new ArrayList<>();
        for(int i = 0; i < array.length; i++){
            if(array[i] % 2 == 1){
                array1.add(array[i]);
                System.out.println(array[i]);
            }else{
                array2.add(array[i]);
            }
        }

        for(int i = 0; i < array1.size(); i++){
            array[i] = array1.get(i);
        }
        int index = array1.size();
        for(int i = 0; i < array2.size(); i++){
            array[index++] = array2.get(i);
        }

        System.out.println(Arrays.toString(array));

    }

    // time: o(n^2) space:(1)
    static public void reOrderArray2(int [] array) {
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array.length - i - 1; j++){
                if (array[j] % 2 == 0 && array[j + 1] % 2 == 1){
                    int temp  = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(array));
    }

        public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7};
        reOrderArray2(nums);

    }
}
