package Amazon._0253_Meeting_Rooms_II;
/*
253. Meeting Rooms II
Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.
Example 1:
Input: intervals = [[0,30],[5,10],[15,20]]
Output: 2

Example 2:
Input: intervals = [[7,10],[2,4]]
Output: 1

Constraints:
1 <= intervals.length <= 104
0 <= start_i < end_i <= 106
*/

import java.util.Arrays;
import java.util.PriorityQueue;

/*
Solution
Approach 1: PriorityQueue  time: O(nlog(n))
for example [[4,9], [4,17],[9,10]]
1. first, sort the array by the start time => [[4,9], [4,17],[9,10]]
2. similar to question 252, compare intervals[i][1] and intervals[i+1][0]
3. initialize a new min-heap, sort by the end time
    3.1 add intervals[0] in the heap, heap.size() represent the number of the meeting room
    3.2 compare heap.peek() and intervals[i] => [4,9] [4,17] => 4 < 9 => need a meeting room, add [4,17] in the queue
    3.3 heap.peek() => [4,9]
    3.4 compare [4,9] [9,10] => don't need another room, pop [4,9] add [9,10]
    3.5 return heap.size() = 2
    Every time the new interval start is larger than the minimum end, pop the interval in the queue


3. iterate the array

for example
1. cur=[0,30]
   pq=[]
2. cur=[5,10]
   pq=>  (0,30) =>  0<5 always meet(because array is sort)
                => consider the start time of current meet is 5
                => the end time of previous meet is 30
                => 5<30, so we need a new room, add (5,10) in the heap, and (5,10) is the new peek

3. cur=[15,20]
   pq=> (5,10)  =>   5<15 (because array is sort)
        /
      (0,30)    => consider the start time of current meet is 15
                => the end time of previous meet is 10
                => 10<15, so don't need a new room, use (15,20) replace the (5,10)


4. pq=> (15,20)
       /
     (0,30)

return pq.size()

Approach 2: Ordering time: O(nlog(n))
for example: [0,30] [15,20] [5,10]
start => [0, 5, 15]
end => [10, 20, 30]
start      0     5      15
end       10     20     30

start < end


*/
public class Solution {

    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        for(int[] interval: intervals){ //[7,10] [2,4]
            if(pq.isEmpty() || interval[0] < pq.peek()[1]){
                pq.add(interval);
            }else{
                pq.poll();
                pq.add(interval);
            }
        }

        return pq.size();
    }

    public int minMeetingRooms2(int[][] intervals) {
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];
        for(int i = 0; i < intervals.length; i++){
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        Arrays.sort(start);
        Arrays.sort(end);
        int count = 0;
        int endIndex = 0;
        for(int i = 0; i < intervals.length; i++){
            if(start[i] < end[endIndex]){
                count++;
            }else{
                endIndex++;
            }
        }

        return count;
    }

    public static void main(String[] args) {

    }
}
