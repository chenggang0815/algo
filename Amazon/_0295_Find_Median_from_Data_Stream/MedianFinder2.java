package Amazon._0295_Find_Median_from_Data_Stream;

import java.util.PriorityQueue;
/*

input 4,2,6,1,3

maxHeap =>  store the smaller half of the numbers
      2
     /
    1
minHeap => store the larger half of the numbers
      3
    /  \
   6   4

If k = 2*n + 1 then minHeap is allowed to hold n+1 elements, while maxHeap can hold n elements
If k = 2*n   then both heaps are balanced and hold n elements each

otherwise

maxHeap =>  store the smaller half of the numbers
      3
     / \
    1   2
minHeap => store the larger half of the numbers
      4
    /
   6

time: O(log(n))
*/
public class MedianFinder2 {
    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;
    public MedianFinder2() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a, b) -> (b - a));
    }

    public void addNum(int num) {
        minHeap.add(num);
        maxHeap.add(minHeap.poll());

        if (minHeap.size() < maxHeap.size()){
            minHeap.add(maxHeap.poll()); // minHeap is allowed to hold n+1 elements, while maxHeap can hold n elements
        }

    }

    public double findMedian() {
        return minHeap.size() == maxHeap.size() ? (maxHeap.peek() + minHeap.peek()) / 2.0 : minHeap.peek();
    }

    public static void main(String[] args) {
        MedianFinder2 median = new MedianFinder2();
        median.addNum(4);
        median.addNum(2);
        median.addNum(6);
        System.out.println(median.findMedian());

    }
}
