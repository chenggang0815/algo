package 剑指Offer._999_归并排序;

import java.util.Arrays;

public class Solution {
    /*
   归并排序：时间复杂度：o(n*log(n)) 空间复杂度：o(n) => 需要将temp数组放在merge函数外面，避免每次递归的时候重新新建一个
    */
    static void  mergeSort(int[] nums, int left, int right, int[] temp){
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid, temp);
        mergeSort(nums,mid+1, right, temp);

        merge(nums, left, mid, right, temp);

    }

    static void merge(int[] nums, int left, int mid, int right, int[] temp){
        int i = left;
        int j = mid + 1;
        //int[] temp = new int[right - left + 1];
        int t = i;
        while (i <= mid && j <= right){
            if (nums[i] <= nums[j]){
                temp[t++] = nums[i++];
            }else{
                temp[t++] = nums[j++];
            }
        }
        while (i <= mid){
            temp[t++] = nums[i++];
        }
        while ( j<= right){
            temp[t++] = nums[j++];
        }

        for (i = left; i < t; i++){
            nums[i] = temp[i];
        }
    }

    public static void main(String[] args) {

        int[] nums = {10,7,2,4,7,62,3,4,2,1,8,9,19};
        int[] temp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1, temp);
        System.out.println(Arrays.toString(nums));
    }
}
