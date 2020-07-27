package com.kernel.dynamic_programming;

import java.util.Arrays;
import java.util.HashMap;

/*
每日复习专用
 */
public class temporary {

    static void mergeSort(int[] nums, int left, int right){
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);

        merge(nums, left, mid, right);

    }

    static void merge(int[] nums, int left, int mid, int right){
        int i = left;
        int j = mid + 1;
        int[] temp = new int[right - left + 1];
        int index = 0;
        while (i <= mid && j <= right){
            if (nums[i] <= nums[j]){
                temp[index++] = nums[i++];
            }else{
                temp[index++] = nums[j++];
            }
        }

        while (i <= mid){
            temp[index++] = nums[i++];
        }

        while (j <= right){
            temp[index++] = nums[j++];
        }

        for (int t = 0; t < temp.length; t++){
            nums[left + t] = temp[t];
        }

    }


    public static void main(String[] args) {
        int[] nums = new int[]{2,4,3,6,3,2,5,5};
        int[] num1 = new int[1];
        int[] num2 = new int[1];
        mergeSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));

    }
}
