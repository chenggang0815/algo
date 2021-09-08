package com.Amazon._0200_Number_of_Islands;

import java.util.Arrays;

public class test {
    static void printArray(int[][] nums){

        System.out.println(Arrays.toString(nums[0]));
        changArray(nums);
        System.out.println(Arrays.toString(nums[0]));
    }

    static void changArray(int[][] nums){
        nums[0][0] = 100;
    }

    public static void main(String[] args) {
        int[][] nums = {{1,2,3,4}, {2,3,4,5}};
        printArray(nums);
    }
}
