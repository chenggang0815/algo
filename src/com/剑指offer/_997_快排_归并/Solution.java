package com.剑指offer._997_快排_归并;
import java.util.Arrays;

class Solution {
    /*
    快速排序  time: nlogn  space: nlogn
    需要注意的点：
	• 为什么快排需要先从右边开始：j-- ？
	如果先出动i，显然i先到达“相遇数”，因为i只会在大于基准数的位置停下，这就意味着“相遇数”一定是大于基准数，
	那么交换后左边序列最左边的元素一定大于归位后的基准数了，与快排一趟结束后，基准数大于左边子序列元素矛盾；
	同理，如果先出动j，可以保证“相遇数”小于基准数。

    • key <= nums[j]  key >= nums[i]  等号不能忘
     */
    static public void quickSort(int[] nums, int left, int right){
        if (left > right) return;

        int i = left;
        int j = right;
        int key = nums[left];
        while (i < j){
            while (i < j && nums[j] >= key){
                j--;
            }
            while (i < j && nums[i] <= key){
                i++;
            }
            if (i < j){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }

        nums[left] = nums[i];
        nums[i] = key;

        quickSort(nums, left,i-1);
        quickSort(nums,i+1, right);
    }


    /*
    归并排序： 时间复杂度：nlogn 空间复杂度：n => 需要将temp数组放在merge函数外面，避免每次递归的时候重新新建一个
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




