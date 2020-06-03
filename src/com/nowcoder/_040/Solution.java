package com.nowcoder._040;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/*
最小的K个数
输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。

思路1： 冒泡排序，每次遍历将最小的值放到最右边，需要遍历k*n次
思路2： 快排思想
思路3： 最大堆

二. 两种方法的优劣性比较
在面试中，另一个常常问的问题就是这两种方法有何优劣。看起来分治法的快速选择算法的时间、空间复杂度都优于使用堆的方法，
但是要注意到快速选择算法的几点局限性：

第一，算法需要修改原数组，如果原数组不能修改的话，还需要拷贝一份数组，空间复杂度就上去了。

第二，算法需要保存所有的数据。如果把数据看成输入流的话，使用堆的方法是来一个处理一个，
不需要保存数据，只需要保存 k 个元素的最大堆。而快速选择的方法需要先保存下来所有的数据，再运行算法。
当数据量非常大的时候，甚至内存都放不下的时候，就麻烦了。所以当数据量大的时候还是用基于堆的方法比较好。

 */
public class Solution {
    // time: o(k*n) 最坏情况k=n o(n^2)
    static public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        if (input.length == 0 || k > input.length) return new ArrayList<>();

        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < k; i++){
            for (int j = 0; j < input.length - i - 1; j++){
                if (input[j] < input[j + 1]){
                    int temp = input[j];
                    input[j] = input[j + 1];
                    input[j + 1] = temp;
                }
            }
            array.add(input[input.length - i - 1]);
        }

        return array;
    }

    /*
我们的目的是寻找最小的 k 个数。假设经过一次 partition 操作，枢纽元素位于下标 m，也就是说，
左侧的数组有 m个元素，是原数组中最小的 m个数。那么：

若 k-1 = m，我们就找到了最小的k（0~k-1）个数，就是左侧的数组；
若 k-1 < m ，则最小的 k个数一定都在左侧数组中，我们只需要对左侧数组递归地 parition 即可；
若 k-1 > m，则左侧数组中的 m个数都属于最小的 k个数，我们还需要在右侧数组中寻找最小的 k-m个数，对右侧数组递归地 partition 即可。

空间复杂度 O(1)，不需要额外空间。
时间复杂度的分析方法和快速排序类似。由于快速选择只需要递归一边的数组，时间复杂度小于快速排序，
期望时间复杂度为 O(n)，最坏情况下的时间复杂度为 O(n^2)
     */
    static public ArrayList<Integer> GetLeastNumbers_Solution2(int [] input, int k) {
        if (input.length == 0 || k > input.length) return new ArrayList<>();


        // 原地不断划分数组
        partitionArray(input, 0, input.length - 1, k-1);

        // 数组的前 k 个数此时就是最小的 k 个数，将其存入结果
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            array.add(input[i]);
        }

        return array;
    }

    static void partitionArray(int[] arr, int low, int high, int k) {
            // 做一次 partition 操作
        int m = partition(arr, low, high);
            // 此时数组前 m 个数，就是最小的 m 个数
        if (k == m) {
                // 正好找到最小的 k(m) 个数
            return;
        } else if (k < m) {
                // 最小的 k 个数一定在前 m 个数中，递归划分
            partitionArray(arr, low, m-1, k);
        } else {
                // 在右侧数组中寻找最小的 k-m 个数
            partitionArray(arr, m+1, high, k);
        }
    }

// partition 函数和快速排序中相同
    static int partition(int[] a, int low, int high) {
            int i = low;
            int j = high;
            int key = a[low];
            while (i<j) {
                while (i < j && a[j] >= key) {
                    j--;
                }

                while (i < j && a[i] <= key) {
                    i++;
                }

                if (i < j){
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                }
            }

            a[low] = a[j];
            a[j] = key;

            // a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
            return j;
    }

    /*
    维护一个大小为k的最大堆，每次比较堆的最大值，如果小于堆顶，则进堆，如果堆中元素大于k，则堆顶元素出堆。

    1. 入堆和出堆操作的时间复杂度均为 O(logk)，每个元素都需要进行一次入堆操作，故算法的时间复杂度为O(nlogk)。
    2. 空间复杂度： o(k)
     */
    static public ArrayList<Integer> GetLeastNumbers_Solution3(int [] input, int k) {
        if (input.length == 0 || input.length < k) return new ArrayList<>();

        Queue<Integer> heap = new PriorityQueue<>((i1, i2) -> (i2 - i1));
        for (int i = 0; i < input.length; i++){
            if (heap.isEmpty() || heap.size() < k || input[i] < heap.peek()){
                heap.offer(input[i]);
            }
            if (heap.size() > k){//维持堆中元素个数为k
                heap.poll();
            }

        }

        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < k; i++){
            array.add(heap.poll());
        }

        return array;
    }


    public static void main(String[] args) {

        int[] input = new int[]{4,1,5,1,6,2,7,3,8};
        System.out.println(GetLeastNumbers_Solution2(input,1));

    }
}
