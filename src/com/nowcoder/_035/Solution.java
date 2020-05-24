package com.nowcoder._035;

import java.util.HashMap;

/*
一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 */
public class Solution {
    /*
    map记录每个数字出现的次数
     */
    //time: o(n) space: o(n)
    static void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<array.length; i++){
            if (map.containsKey(array[i])){
                map.put(array[i], map.get(array[i]) + 1);
            }else {
                map.put(array[i], 1);
            }
        }
        int index = 0;
        int[] nums = new int[2];
        for (int j=0; j<array.length; j++){
            if (map.get(array[j]) == 1){
                nums[index] = array[j];
                index++;
            }
        }

        num1[0] = nums[0];
        num2[0] = nums[1];
        System.out.println(num1[0]);
        System.out.println(num2[0]);
    }
        public static void main(String[] args) {
        int[] array = new int[]{1,2,2,3,3,5,6,6};
        int[] num1 = new int[1];
        int[] num2 = new int[1];
        FindNumsAppearOnce(array, num1, num2);


        }
}
