package com.剑指offer._068;
/*
数组中的逆序对

在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。 i > j => (i, j)
输入一个数组，求出这个数组中的逆序对的总数。 并将总数p对1000000007取模的结果输出。 即输出P%1000000007
输入: [7,5,6,4]
输出: 5

思路1：暴力遍历，两层for循环

思路2：归并排序
1. 对归并排序中的两个数组[1,4,5,6]和[2, 3] 从 1和2开始比较，当left=4，right=2时，有left > right ， 得知(4,2)是一个逆序对，
由于两边都是排序好的数据，所有可以直接算出逆序对的个数，mid（6的位置） - i（4的位置） + 1 => 因为4后面的数据都比2大

2. 根据第一点的思想，将数组二分到left >= right，即两个长度都为 1， [1] [4]时 ， 就判断了1和4的情况，因此只需在归并排序的基础上
统计就能判断所有情况。
 */
public class Solution {

    // time: o(n*log(n)) space:o(n)
    static int res;
     static int InversePairs(int [] nums) {
        if (nums.length == 0) return 0;

        int[] temp = new int[nums.length];
        mergesort(nums, 0, nums.length - 1, temp);

        return res;
    }

     static void mergesort(int[] nums, int left, int right, int[] temp){
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        mergesort(nums, left, mid, temp);
        mergesort(nums, mid + 1, right, temp);

        merge(nums, left, mid, right, temp);
    }

     static void merge(int[] nums, int left, int mid, int right, int[] temp){
        int i = left;
        int j = mid + 1;
        int index = i;
        //int[] temp = new int[right - left + 1];

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

        for (i = left; i < index; i++){
            nums[i] = temp[i];
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5,4,3};
        System.out.println(InversePairs(nums));

    }
}
