package 剑指Offer._1000_实现heap;
// implement a min heap, for a max heap,
// just change the compare relation in the heapifyUp and heapifyDown
// for a min heap, => insert: move the last element from bottom to up, swap with the smaller parent
//                 => extract: set the last element to first position, move from top to bottom
// for a max heap => insert: move the last element from bottom to up, swap with the bigger parent
//                => extract: set the last element to first position, move from top to bottom
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    static int[] arr = new int[5];
    static int size = 0;
    static void insert(int val){
        arr[size] = val;
        size++;
        heapifyUp(arr, size, size - 1);
    }

    static int extractMin(){
        int res = arr[0];
        arr[0] = arr[size - 1];
        size--;
        heapifyDown(arr, size, 0);
        return res;
    }

    static void heapifyDown(int[] arr, int size, int parent){
        int childL = 2 * parent;
        int childR = 2 * parent + 1;
        while (2 * parent < size){
            System.out.println(Arrays.toString(arr));
            System.out.println(childL);
            System.out.println(childR);
            if (arr[parent] > Math.min(arr[childR], arr[childL])){
                if (arr[childL] < arr[childR]){
                    int temp = arr[childL];
                    arr[childL] = arr[parent];
                    arr[parent] = temp;
                    parent = childL;
                }else{
                    int temp = arr[childR];
                    arr[childR] = arr[parent];
                    arr[parent] = temp;
                    parent = childR;
                }
                 childL = 2 * parent;
                 childR = 2 * parent + 1;
            }else break;
        }
    }

    static void heapifyUp(int[] arr, int size, int child){
        int parent = child / 2;
        while (parent >= 0){
            if (arr[parent] > arr[child]){
                int temp = arr[parent];
                arr[parent] = arr[child];
                arr[child] = temp;
                child = parent;
                parent = child / 2;
            }else{
                break;
            }
        }
    }


    public static void main(String[] args) {
        insert(300);
        System.out.println(extractMin());
        insert(200);
        insert(600);
        insert(500);
        System.out.println(extractMin());
        insert(100);
        System.out.println(extractMin());
        System.out.println(extractMin());
        System.out.println(extractMin());

    }
}
