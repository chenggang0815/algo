package Amazon._1429_First_Unique_Number;
import java.util.HashMap;
import java.util.TreeMap;

/*
1429. First Unique Number
You have a queue of integers, you need to retrieve the first unique integer in the queue.

Implement the FirstUnique class:
1. FirstUnique(int[] nums) Initializes the object with the numbers in the queue.
2. int showFirstUnique() returns the value of the first unique integer of the queue, and returns -1 if there is no such integer.
3. void add(int value) insert value to the queue.

Example 1:

Input:
["FirstUnique","showFirstUnique","add","showFirstUnique","add","showFirstUnique","add","showFirstUnique"]
[[[2,3,5]],[],[5],[],[2],[],[3],[]]
Output:
[null,2,null,2,null,3,null,-1]
Explanation:
FirstUnique firstUnique = new FirstUnique([2,3,5]);
firstUnique.showFirstUnique(); // return 2
firstUnique.add(5);            // the queue is now [2,3,5,5]
firstUnique.showFirstUnique(); // return 2
firstUnique.add(2);            // the queue is now [2,3,5,5,2]
firstUnique.showFirstUnique(); // return 3
firstUnique.add(3);            // the queue is now [2,3,5,5,2,3]
firstUnique.showFirstUnique(); // return -1
*/
/*
Solution:
time: initialize: O(k)
      showFirstUnique: O(1)
      add: O(1)
space: O(n)

1. if we need O(1) time to get the first unique value
   O(1) => Map
   first => order => TreeMap
2. TreeMap<time, value> map => we return map.get(map.firstKey())

3. if we add the same value more than one time, we need the delete the <time, value> from treeMap

4. but how can we know we already have the same value in the treeMap, and the key(time) of this value?

5. so we can use another map to store the <value, time>, if the new value already in the map, then we can remove it from treeMap
   => if(map.containsKey(value)) => treeMap.remove(map.get(value))
*/
public class FirstUnique {
    TreeMap<Integer, Integer> orderMap;
    HashMap<Integer, Integer> map;
    int time;
    public FirstUnique(int[] nums) {
        orderMap = new TreeMap<>();
        map = new HashMap<>();
        time = 0;
        for(int num : nums){
            if(map.containsKey(num)){
                orderMap.remove(map.get(num));
            }else{
                orderMap.put(time, num);
                map.put(num, time);
                time++;
            }
        }

    }

    public int showFirstUnique() {
        if(orderMap.size() != 0) return orderMap.get(orderMap.firstKey());

        return -1;
    }

    public void add(int value) {
        if(map.containsKey(value)){
            orderMap.remove(map.get(value));
        }else{
            orderMap.put(time, value);
            map.put(value, time);
            time++;
        }
    }
    public static void main(String[] args) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(6,6);
        map.put(1,1);
        map.put(9,9);
        System.out.println(map.firstKey());
        //map.remove(3);
        System.out.println(map.firstKey());
        //map.remove(1);
    }
}
