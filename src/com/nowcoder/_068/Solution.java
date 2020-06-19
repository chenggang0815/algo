package com.nowcoder._068;
/*
数组中的逆序对

在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。 i > j => (i, j)
输入一个数组，求出这个数组中的逆序对的总数。 并将总数p对1000000007取模的结果输出。 即输出P%1000000007
输入: [7,5,6,4]
输出: 5

思路1：暴力遍历，两层for循环

思路2：归并排序
 */
public class Solution {
    static int res;
     static int InversePairs(int [] nums) {
        if (nums.length == 0) return 0;
        mergesort(nums, 0, nums.length - 1);

        return res;
    }

     static void mergesort(int[] nums, int left, int right){
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        mergesort(nums, left, mid);
        mergesort(nums, mid + 1, right);

        merge(nums, left, mid, right);
    }

     static void merge(int[] nums, int left, int mid, int right){
        int i = left;
        int j = mid + 1;
        int index = 0;
        int[] temp = new int[right - left + 1];

        while (i <= mid && j <= right){
            if (nums[i] <= nums[j]){
                temp[index++] = nums[i++];
            }else {
                //res = (res + mid - i + 1) % 1000000007;
                res = (res + mid - i + 1);
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
        int[] nums = new int[]{5,4,3};
        System.out.println(InversePairs(nums));

    }
}
