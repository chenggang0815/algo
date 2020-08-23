package com.剑指offer._022_二分查找以及变种;
import java.lang.*;
//二分查找以及变种

public class Solution {
//二分查找
    public  static int binarySearch(int[] arr, int k){
        int right = arr.length-1;
        int left = 0;
        while (left <= right){
            int mid = left + (right-left)/2;
            if(arr[mid] == k) return mid;
            else if (arr[mid] < k){
                left = mid+1;
            }
            else {
                right = mid-1;
            }
        }
        return -1;
        }

//二分查找的递归实现
    public static int bsearch(int[] arr,int k){
        int n = arr.length;
        return bsearchInternally(arr,0,n-1,k);
    }

    public static int bsearchInternally(int[] arr,int right, int left,int k){
        if(right > left) return -1;
        int mid = (right+left)/2;
        if(arr[mid]==k){
            return mid;
        }
        else if(arr[mid] > k){
            return  bsearchInternally(arr,right,mid-1,k);
        }
        else {
            return bsearchInternally(arr,mid+1,left,k);
        }
    }

    //二分查找求平方根
    public static double sqrt(double t){
        double low = 0;
        double high = t;
        double mid = low + (high-low)/2;

        while (high-low > 1e-7){
            if(mid * mid > t){
                high = mid;
            }
            else if(mid * mid < t ){
                low = mid;
            }
            else if(Math.abs(high-mid*mid)<1e-7) return mid;

            mid = low + (high-low)/2;
        }
        return -1;
    }

    //牛顿迭代分求平方根
    public static int mySqrt(int x)
    {
        if (x <= 1) return x;
        double x1 = 0, x2 = 1;
        while (Math.abs(x1 - x2) > 0.000001)
        {
            x1 = x2;
            x2 = x1 / 2 + (double)x / (2 * x1);
        }
        return (int)x1;
    }


    //查找第一个等于给定值的元素
    public  static int searchFirst(int[] arr, int k){
        int low = 0;
        int high = arr.length;
        while (low <= high){
            int mid = low + (high-low)/2;
            if (arr[mid] > k){
                high = mid-1;
            }
            else if (arr[mid] < k ){
                low = mid +1;
            }
            else{
                if (mid == 0 || arr[mid-1] !=k ) return mid;
                else high =mid-1;
            }
        }
        return -1;
    }

    //查找最后一个等于给定值的元素
    public static int searchLast(int[] arr, int k){
        int low = 0;
        int high = arr.length-1;
        while (low <= high){
            int mid = low + (high-low)/2;

            if (arr[mid] > k){
                high = mid-1;
            }
            else if (arr[mid] < k ){
                low = mid+1;
            }
            else {
               if (mid ==high-1 || arr[mid+1]!=k) return mid;
               else low=mid+1;
            }
        }
        return -1;
    }

    //查找第一个大于等于给定值的元素
    public static int searchFirstBig(int[] arr, int k){
        int low = 0;
        int high = arr.length-1;
        while (low <= high){
            int mid = low + (high-low)/2;
            if (arr[mid]>= k){
                if (mid==0 || arr[mid-1] <k){
                    return  mid;
                }
                else  high= mid-1;
            }
            else low=mid+1;
        }

        return -1;
    }

    //查找最后一个小于等于给定值的元素
    public static int searchLastLow(int[] arr, int k){
        int low =0;
        int high = arr.length-1;
        while (low <= high){
            int mid = low +(high-low)/2;
            if (arr[mid] <= k){
                if (mid == arr.length-1 || arr[mid+1] > k){
                    return mid;
                }
                else low = mid+1;
            }
            else high = mid-1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2,3,4,8,8,8,10,10};
        System.out.println(searchLastLow(arr,9));
    }
}
