package com.nowcoder._020;

import java.util.Arrays;

public class Solution {

// 计数排序
    public static int[] countSort(int[] arr, int k){
        int[] c = new int[k+1];
        int length = arr.length;
        int[] b = new int[length];

        for (int i=0;i<length;i++){
            c[arr[i]] +=1;
        }
        int sum=0;
        for (int i=0; i<k+1;i++){
            sum += c[i];
            c[i] = sum;
        }
        for (int i=length-1;i>=0;i--){
            b[c[arr[i]]-1] = arr[i];
            c[arr[i]]--;
        }

        return b;

    }

    public static void main(String[] args) {

        int[] array = {1,4,1,4,5,6};
        System.out.println(Arrays.toString(countSort(array,6)));



    }

}




