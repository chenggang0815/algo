package com.nowcoder._023;
import java.lang.*;
//循环有序数组的二分查找 leetcode33

public class Solution {
/*
    Search in Rotated Sorted Array
    Suppose a sorted array is rotated at some pivot unknown to you beforehand.
            (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
    You are given a target value to search. If found in the array return its index, otherwise return -1.
    You may assume no duplicate exists in the array.
*/

    public static int search(int[] arr, int target){
        int start = 0;
        int end = arr.length-1;

        while (start <= end){
            int mid = start + (end-start)/2;
            if (arr[mid] == target) return mid;
            else if (arr[mid]>= arr[start]){
                if (target >= arr[start] && target < arr[mid]){
                    end = mid-1;
                }
                else start = mid +1;
            }
            else if (arr[mid]<= arr[end]){
                if(target > arr[mid] && target <= arr[end]){
                    start =mid+1;
                }
                else end = mid -1;

            }
        }

        return -1;

    }


    public static void main(String[] args) {

        int[] arr = new int[]{4,5,6,7,0,1,2};

        System.out.println(search(arr,7));

    }
}