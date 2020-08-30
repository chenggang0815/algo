package com.剑指offer._63_数据流中的中位数;

import java.util.PriorityQueue;

/*
数据流中的中位数

如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。

思路一： 最大堆+最小堆（优先队列）
1. 本质上是保证堆顶是数据流中间的数： 比如 1 2 2 3
 a. 最大堆（堆顶最大，那肯定要存放较小的一半数）里存放较小的一半数据 => 2 1
 b. 最小堆（堆顶最小，那肯定要存放较大的一半数）里存放较大的一半数据 => 2 3

2. 依次交替向最大堆和最小堆插入数据，又因为要维持 a, b两点：
  2-1. 每次向最大堆插入数据的时候，需要先向最小堆插入数据，再把最小堆的堆顶元素插入最大堆，

3. 如果先向最小堆插入数据，那么奇数的时候，中位数就是最小堆的堆顶数据。反正则是最大堆的堆顶元素，偶数则是两个堆顶元素的平均值

4. 可以通过两个堆的数据量来判断奇偶，若相等则为偶数，不相等则为奇数。
思路二: 平衡二叉搜索树
 */
public class Solution {
    /*

时间复杂度：
1. 查找中位数 O(1) ： 获取堆顶元素使用 O(1) 时间；
2. 添加数字 O(logN) ： 堆的插入和弹出操作使用 O(logN) 时间。

空间复杂度 O(N)： 其中 N 为数据流中的元素数量，小顶堆 A 和大顶堆 B 最多同时保存 N 个元素。
     */
    static PriorityQueue<Integer> max = new PriorityQueue<>((i1, i2) -> (i2 - i1));
    static PriorityQueue<Integer> min = new PriorityQueue<>();

    static void Insert(Integer num) {
        if (max.size() == min.size()){
            max.add(num);
            min.add(max.poll());
        }else{
            min.add(num);
            max.add(min.poll());
        }
    }

    static Double GetMedian() {
        if (max.size() == min.size()){
            return (max.peek() + min.peek()) / 2.0;
        }else{
            return min.peek() / 1.0;
        }

    }
    public static void main(String[] args) {

        Insert(1);
        Insert(3);
        Insert(4);
        System.out.println(GetMedian());

    }
}
