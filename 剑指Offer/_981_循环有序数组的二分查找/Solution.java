package 剑指offer._981_循环有序数组的二分查找;
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
        int right = 0;
        int left = arr.length-1;

        while (right <= left){
            int mid = left + (right-left)/2;
            if (arr[mid] == target) return mid;
            else if (arr[mid]>= arr[left]){
                if (target >= arr[left] && target < arr[mid]){
                    right = mid-1;
                }
                else left = mid +1;
            }
            else if (arr[mid]<= arr[right]){
                if(target > arr[mid] && target <= arr[right]){
                    left =mid+1;
                }
                else right = mid -1;

            }
        }

        return -1;

    }


    public static void main(String[] args) {

        int[] arr = new int[]{4,5,6,7,0,1,2};

        System.out.println(search(arr,7));

    }
}