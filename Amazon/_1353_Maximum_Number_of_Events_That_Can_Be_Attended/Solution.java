package Amazon._1353_Maximum_Number_of_Events_That_Can_Be_Attended;

import java.util.*;

/*
Solution:
Approach 1:
for example:
[1,1]
[1,2]
[1,3]
....
[1,10^5] the worst situation will run 10^5 times for last event

Approach 2:
#1. Sort the events based on starting day of the event
#2. Now once you have this sorted events, every day check what are the events that can start today
#3. for all the events that can be started today, keep their ending time in heap.

- Wait why we only need ending times ?
i) from today onwards, we already know this event started in the past and all we need to know is when this event will finish
ii) Also, another key to this algorithm is being greedy, meaning I want to pick the event which is going to end the soonest.
- So how do we find the event which is going to end the soonest?
i) brute force way would be to look at all the event's ending time and find the minimum, this is probably ok for 1 day but as we can only attend 1 event a day,
we will end up repeating this for every day and that's why we can utilize heap(min heap to be precise) to solve the problem of finding the event with earliest ending time
#4. There is one more house cleaning step, the event whose ending time is in the past, we no longer can attend those event
#5. Last but very important step, Let's attend the event if any event to attend in the heap.

for example:
[1,1]
[1,2]
[1,3]
[1,4]
use a min heap to store the end time, then we can use the log(n) time to find the small event time
        1
       / \
      2  3
          \
           4


*/
public class Solution {
    static int maxEvents1(int[][] events) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(events, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1]-b[1]);

        for(int[] event: events) {
            for(int i = event[0]; i<=event[1]; i++) {
                 if (!set.contains(i)){
                     set.add(i);
                     break;
                 }
            }
        }

        return set.size();
    }

    static int maxEvents2(int[][] events){
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int currentDay = 1;
        int res = 0;
        int i = 0;
        while (i < events.length || !pq.isEmpty()){
            while (i < events.length && events[i][0] == currentDay){
                pq.add(events[i][1]);
                i++;
            }

            while (!pq.isEmpty() && pq.peek() < currentDay){
                pq.remove();
            }

            if (!pq.isEmpty()){
                pq.remove();
                res++;
            }

            currentDay++;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(maxEvents1(new int[][]{{1,1},{1,2}}));
    }
}
