package com.nowcoder._019;
import java.util.Arrays;

class Solution {
    //快速排序
    // time : o(nlog2(n))
    // space: o(nlog2(n))
    static public void quickSort(int[] nums, int left, int right){
        if (left>right) return;
        int i = left;
        int j = right;
        int key = nums[left];
        while (i<j){

            while (i<j&&nums[j]>=key){
                j--;
            }

            while (i<j&&nums[i]<=key){
                i++;
            }


            System.out.println(nums[i]);
            System.out.println(nums[j]);
            System.out.println("====");
            if (i<j){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }

        nums[left] = nums[i];
        nums[i] = key;

        quickSort(nums,left,i-1);
        quickSort(nums,i+1,right);
    }

//    static void quickSort(int[] nums, int left, int right){
//        if(left>right) return;
//
//        int i = left;
//        int j = right;
//        int key = nums[left];
//
//        while(i<j){
//
//            while(i<j&&nums[j]>=key){
//                j--;
//            }
//
//            while(i<j&&nums[i]<=key){
//                i++;
//            }
//
//
//            if(i<j){
//                int temp = nums[i];
//                nums[i] = nums[j];
//                nums[j] = temp;
//            }
//        }
//
//        nums[left] = nums[i];
//        nums[i] = key;
//
//        quickSort(nums, left, i-1);
//        quickSort(nums, i+1, right);
//
//    }
	// 归并排序
    private static void  mergeSort(int[] nums, int left, int right){
        if (left>=right) return;

        int mid = left + (right-left)/2;
        mergeSort(nums,left,mid);
        mergeSort(nums,mid+1,right);

        merge(nums,left,mid,right);

    }

    static void merge(int[] nums, int left, int mid, int right){
        int i = left;
        int j = mid+1;
        int[] temp = new int[right-left+1];
        int t = 0;
        while (i<=mid&&j<=right){
            if (nums[i]<=nums[j]){
                temp[t++] = nums[i++];
            }else{
                temp[t++] = nums[j++];
            }
        }
        while (i<=mid){
            temp[t++] = nums[i++];
        }

        while (j<=right){
            temp[t++] = nums[j++];
        }

        for (i=0;i<t;i++){
            nums[i+left] = temp[i];
        }
    }

    public static void main(String[] args) {

        //int[] array = new int[] {40,2,1,311,2,1};
        int[] nums = {10,7,2,4,7,62,3,4,2,1,8,9,19};
        //mergeSort(nums,0,nums.length-1);
        quickSort(nums,0,nums.length-1);
        System.out.println(Arrays.toString(nums));
        

    }

}




