package LeetCode._0981_Time_Based_Key_Value_Store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/*
Approach 2: HashMap + BinarySearch
1. HashMap<key, List<ts>> => for a key and timestamp, we can use binary search to find max ts => meet ts <= timestamp
2. HashMap<ts+key, value> => after we find the ts, we can use the "ts+key" to a unique key to get the value
    // <key, <ts1,ts2,ts3,ts4>>
    // <key + ts, value>
    //get => (key, ts)
 */
class TimeMap2 {
    // key value ts
    // <key, <ts1,ts2,ts3,ts4>>
    // <key + ts, value>
    //get => (key, ts)
    HashMap<String, List<Integer>> map;
    HashMap<String, String> keyTovalue;

    public TimeMap2() {
        map = new HashMap<>();
        keyTovalue = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)){
            List<Integer> list = new ArrayList<>();
            list.add(timestamp);
            map.put(key, list);
        }else{
            map.get(key).add(timestamp);
        }
        String k = String.valueOf(timestamp) + key;
        keyTovalue.put(k, value);
    }

    public String get(String key, int timestamp) {
        if (map.containsKey(key)){
            int ts = binarySearch(map.get(key), timestamp);
            if (ts == -1) return "";
            System.out.println(ts);
            String k = String.valueOf(ts) + key;
            return keyTovalue.get(k);
        }else {
            return "";
        }
    }
    // 1 2 3 4 5 6  target => last nums[i] <= 4
    // binary search => find the last number => nums[i] <= target
    public int binarySearch(List<Integer> list, int target){
        int left = 0;
        int right = list.size() - 1;
        int res = -1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (list.get(mid) <= target){
                left = mid + 1;
                res = mid;
            }else{
                right = mid - 1;
            }
        }

        return res == -1 ? -1 : list.get(res);
    }

    public static void main(String[] args) {
        TimeMap2 timeMap2 = new TimeMap2();
        timeMap2.set("foo", "bar", 1);
        timeMap2.set("foo", "bar2", 4);
        System.out.println(timeMap2.get("foo", 4));

    }
}
