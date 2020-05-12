package com.nowcoder._019;
import java.util.Arrays;

class Solution {
    //快速排序
    // time : o(nlog2(n))
    // space: o(nlog2(n))
    private static void quickSort(int[] arr, int low, int high) {
        if( low > high) {
            return;
        }
        int i = low;
        int j = high;
        int key = arr[low];
        while (i<j){
            while (key<=arr[j] && i<j){
                j--;
            }
            while (key>=arr[i] && i<j){
                i++;
            }
            if(i<j){
                int temp =arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        arr[low] = arr[i];
        arr[i] = key;

        quickSort(arr, low, i-1 );
        quickSort(arr, i+1, high);
    }
    static public void quickSort2(int[] nums, int left, int right){
        if (left>=right) return;
        int i = left;
        int j = right;
        int key = nums[left];
        while (i<j){
            while (i<j&&nums[i]<=key){
                i++;
            }
            while (i<j&&nums[j]>=key){
                j--;
            }

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
	// 归并排序
    private static void mergeSort(int[] arr, int low, int high) {

        int mid = low+(high-low)/2;
        if (low < high) {
            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);

            merge(arr, low, mid, high);
        }
    }

    private static void merge(int[] arr,int right,int mid,int left){
        int i = right;
        int j = mid+1;
        int t = 0;
        int[] temp = new int[left-right+1];
        while (i<=mid && j <= left){
            if (arr[i] <= arr[j]){
                temp[t++] = arr[i];
                i++;
            }
            else {
                temp[t++] = arr[j];
                j++;
            }
        }

        while (i<= mid ){
            temp[t++] = arr[i++];

        }
        while (j <= left){
            temp[t++] = arr[j++];
        }

        for (i =0;i<t;i++){
            arr[i+right] = temp[i];
        }
    }

    private static void  mergesort(int[] nums, int left, int right){
        if (left<right){
            int mid = left + (right-left)/2;
            mergesort(nums,left,mid);
            mergesort(nums,mid+1,right);

            merge2(nums,left,mid,right);
        }
    }

    static void merge2(int[] nums, int left, int mid, int right){
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
        int[] array = {10,7,2,4,7,62,3,4,2,1,8,9,19};
        mergesort(array,0,array.length-1);
        System.out.println(Arrays.toString(array));
        

    }

}




