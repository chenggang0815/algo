package LeetCode._0981_Time_Based_Key_Value_Store;
/*
981. Time Based Key-Value Store
Design a time-based key-value data structure that can store multiple values for the same key at different time stamps and retrieve the key's value at a certain timestamp.
Implement the TimeMap class:
TimeMap() Initializes the object of the data structure.
void set(String key, String value, int timestamp) Stores the key key with the value value at the given time timestamp.
String get(String key, int timestamp) Returns a value such that set was called previously, with timestamp_prev <= timestamp.
If there are multiple such values, it returns the value associated with the largest timestamp_prev. If there are no values, it returns "".

Example 1:
Input
["TimeMap", "set", "get", "get", "set", "get", "get"]
[[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]
Output
[null, null, "bar", "bar", null, "bar2", "bar2"]

Explanation
TimeMap timeMap = new TimeMap();
timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.
timeMap.get("foo", 1);         // return "bar"
timeMap.get("foo", 3);         // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
timeMap.get("foo", 4);         // return "bar2"
timeMap.get("foo", 5);         // return "bar2"
*/
/*
Solution:
Approach 1: TreeMap + HashMap
1. HashMap<key, TreeMap<ts, value>>
2. for get operate => for loop the TreeMap.keySet(), find the max ts <= timestamp
                   => use TreeMap.floorKey(timestamp) API find the max key <= timestamp
    // key => (value1, ts1)
    // key => (value2, ts2)
    // key => get max ts_prev <= ts => get (ts, value)
    // HashMap<key, TreeMap<ts, value>>

Approach 2: HashMap + BinarySearch
1. HashMap<key, List<ts>> => for a key and timestamp, we can use binary search to find max ts => meet ts <= timestamp
2. HashMap<ts+key, value> => after we find the ts, we can use the "ts+key" to a unique key to get the value
    // <key, <ts1,ts2,ts3,ts4>>
    // <key + ts, value>
    //get => (key, ts)
*/
import java.util.HashMap;
import java.util.TreeMap;

public class TimeMap {
    // key => (value1, ts1)
    // key => (value2, ts2)
    // key => get max ts_prev <= ts => get (ts, value)
    // HashMap<key, TreeMap<ts, value>>
    //
    HashMap<String, TreeMap<Integer, String>> map;
    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if(map.containsKey(key)){
            map.get(key).put(timestamp, value);
        }else{
            TreeMap<Integer, String> treeMap = new TreeMap<>();
            treeMap.put(timestamp, value);
            map.put(key, treeMap);
        }
    }

    public String get(String key, int timestamp) {
        int maxPrevTs = 0;
        // for(int ts: map.get(key).keySet()){
        //     if (ts <= timestamp) maxPrevTs = ts;
        //     else break;
        // }
        if(!map.containsKey(key)) return "";

        if(map.get(key).floorKey(timestamp) == null) return "";
        else return map.get(key).get(map.get(key).floorKey(timestamp));

        //return maxPrevTs > 0 ? map.get(key).get(maxPrevTs) : "";
    }
}

