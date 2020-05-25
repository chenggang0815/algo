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

    /*
    利用位运算中的异或运算 ：两个相同的数异或为0，n^n=0，n^0=n
    */
    static void FindNumsAppearOnce2(int [] array,int num1[] , int num2[]) {
        int xor1 = 0;
        for (int i = 0; i < array.length; i++)
            xor1 = xor1 ^ array[i];
        //在xor1中找到第一个不同的位对数据进行分类，分类为两个队列对数据进行异或求和找到我们想要的结果

        int index = 1;
        while ((index & xor1) == 0)
            index = index << 1;//因为可能有多个位为1所以需要求一下位置
        int result1 = 0;
        int result2 = 0;
        for(int i=0; i < array.length; i++){
            if((index & array[i]) == 0)
                result1 = result1^array[i];
            else
                result2 = result2^array[i];
        }
        num1[0] = result1;
        num2[0] = result2;
        //System.out.println(num1[0]);
        //System.out.println(num2[0]);

    }
        public static void main(String[] args) {
        int[] array = new int[]{1,2,2,3,3,5,6,6};
        int[] num1 = new int[1];
        int[] num2 = new int[1];
        //FindNumsAppearOnce2(array, num1, num2);
            System.out.println(1^2);
        }
}
