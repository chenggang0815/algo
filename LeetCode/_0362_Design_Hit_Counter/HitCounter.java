package LeetCode._0362_Design_Hit_Counter;
import java.util.TreeMap;
/*
362. Design Hit Counter

Design a hit counter which counts the number of hits received in the past 5 minutes (i.e., the past 300 seconds).
Your system should accept a timestamp parameter (in seconds granularity), and you may assume that calls are being made to the system in chronological order (i.e., timestamp is monotonically increasing). Several hits may arrive roughly at the same time.

Implement the HitCounter class:
1. HitCounter() Initializes the object of the hit counter system.
2. void hit(int timestamp) Records a hit that happened at timestamp (in seconds). Several hits may happen at the same timestamp.
3. int getHits(int timestamp) Returns the number of hits in the past 5 minutes from timestamp (i.e., the past 300 seconds).

Example 1:
Input
["HitCounter", "hit", "hit", "hit", "getHits", "hit", "getHits", "getHits"]
[[], [1], [2], [3], [4], [300], [300], [301]]
Output
[null, null, null, null, 3, null, 4, 3]
Explanation
HitCounter hitCounter = new HitCounter();
hitCounter.hit(1);       // hit at timestamp 1.
hitCounter.hit(2);       // hit at timestamp 2.
hitCounter.hit(3);       // hit at timestamp 3.
hitCounter.getHits(4);   // get hits at timestamp 4, return 3.
hitCounter.hit(300);     // hit at timestamp 300.
hitCounter.getHits(300); // get hits at timestamp 300, return 4.
hitCounter.getHits(301); // get hits at timestamp 301, return 3.

Constraints:
1. 1 <= timestamp <= 2 * 109
2. All the calls are being made to the system in chronological order (i.e., timestamp is monotonically increasing).
3. At most 300 calls will be made to hit and getHits.
 */
/*
Solution:
Approach 1: time complexity: hit()=> O(1) gitHits()=>O(k) k=300
1. hit() => TreeMap<timestamp, cnt>
2. gitHits() => delete all timestamp before (current timestamp - 300)

Approach 2: time complexity: hit()=> O(1) gitHits()=>O(k) k=300
1. hit() => Queue<timestamp> HashMap<timestamp, cnt>
2. gitHits() => delete all timestamp (in queue) before (current timestamp - 300)

Follow up:
What if the number of hits per second could be huge? Does your design scale?
*/
public class HitCounter {
    TreeMap<Integer, Integer> hitMap;

    public HitCounter() {
        hitMap = new TreeMap<>();
    }

    public void hit(int timestamp) {
        hitMap.put(timestamp, hitMap.getOrDefault(timestamp, 0) + 1);
    }

    public int getHits(int timestamp) {
        int res = 0;
        int i = timestamp - 300;

        for(int time : hitMap.keySet()){
            res += hitMap.get(time);
        }

        if(i <= 0) return res;

        for(int time : hitMap.keySet()){
            if(time <= i){
                res -= hitMap.get(time);
            }
        }

        return res;
    }
}
