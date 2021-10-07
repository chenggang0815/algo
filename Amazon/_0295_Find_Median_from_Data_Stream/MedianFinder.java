package Amazon._0295_Find_Median_from_Data_Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
/*
295. Find Median from Data Stream

The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value and the median is the mean of the two middle values.
For example, for arr = [2,3,4], the median is 3.
For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.

Implement the MedianFinder class:
MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.

Constraints:
-105 <= num <= 105
There will be at least one element in the data structure before calling findMedian.
At most 5 * 104 calls will be made to addNum and findMedian.

Follow up:
1. If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
2. If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 */

/*
solution 1: use a list to store number, can use 3 ways to maintain the order of list,
1. every time add a number, sort the list
   time => add number => O(1)
           sort list => O(nlog(n))
           overall time => O(1) + O(log(n)) => O(log(n))
           space: O(n)

2. every time add a number, iterate the list, insert the number to the right index
   time => iterate the list => O(n)
        => insert the number to the right index => O(n)
        => overall time => O(n) + O(n) => O(n)
        => space: O(n)

3. every time add a number, use binary search to find the index to insert the number
    time =>  binary search takes O(log(n)) time to find correct insertion position
        => Insertion can take up to O(n) time since elements have to be shifted inside the container to make room for the new element
        => overall time => O(n) + O(log(n)) => O(n)
        => space: O(n)
*/
public class MedianFinder {
    List<Integer> list;
    public MedianFinder() {

        list = new ArrayList<>();

    }

    public void addNum(int num) {
        if(list.size() == 0){
            list.add(num);
            return;
        }
        if(num <= list.get(0)) {
            list.add(0, num);
            return;
        }
        /*
       for(int i = 0; i < list.size(); i++){
            if(list.get(i) >= num){
                list.add(i, num);
                return;
            }
        }
         */
        int index = binarySearch(list, num);
        list.add(index, num);
    }

    public int binarySearch(List<Integer> list, int num){
        int left = 0;
        int right = list.size() - 1;
        int index = list.size();
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(list.get(mid) >= num){
                right = mid - 1;
                index = mid;
            }else{
                left = mid + 1;
            }
        }

        return index;
    }

    public double findMedian() {
        //System.out.println(list);
        int size = list.size();
        if(size == 1) return list.get(0);
        if(size % 2 == 0){
            return (list.get(size/2) + list.get(size/2 - 1))/2.0;
        }else{
            return list.get(size/2);
        }
    }

    public static void main(String[] args) {
        MedianFinder median = new MedianFinder();
        median.addNum(1);
        median.addNum(2);
        median.addNum(3);
        System.out.println(median.findMedian());

        PriorityQueue<Integer> heap = new PriorityQueue<>((a,b) -> b - a);
        heap.add(2);
        heap.add(5);
        heap.add(3);
        System.out.println(heap.peek());
    }
}
