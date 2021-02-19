package com.剑指offer._996_冒泡排序_选择排序_插入排序;

//冒泡排序，选择排序，插入排序。
public class Solution {
    //冒泡排序
    public static void bubbleSort(int[] arr){
        int length = arr.length;
        for(int i = 0; i < length - 1; i++){
            for(int j = 0; j < length - i - 1; j++){
                if (arr[j] >= arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
	
	//插入排序
	public static void insertSort(int[] arr){
        int length =arr.length;
        int target;
        for (int i = 1;i<length;i++){
            target =arr[i];
            int j =i;

            while (j>0 && target<arr[j-1]){
                arr[j] = arr[j-1];
                j--;
            }
            arr[j] = target;
        }
    }
	
	   //选择排序
    public static void selectionSort(int[] arr){
        int length = arr.length;
        for (int i =0;i<length;i++){
            int k =i;
            for (int j = i;j<length;j++){
                if (arr[j] > arr[k]){
                    k=j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[k];
            arr[k] = temp;
        }
    }


    public static void main(String[] args) {
        int[] array = new int[] {4,2,1,4,1,1,1,100};
        bubbleSort(array);
        for(int i=0;i<array.length;i++){
            System.out.println(array[i] + "");
        }
    }

}




