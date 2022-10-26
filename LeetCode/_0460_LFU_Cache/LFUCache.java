package LeetCode._0460_LFU_Cache;

import java.util.HashMap;
import java.util.Map;

/*
460. LFU Cache

Design and implement a data structure for a Least Frequently Used (LFU) cache.

Implement the LFUCache class:
    1. LFUCache(int capacity) Initializes the object with the capacity of the data structure.
    2. int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
    3. void put(int key, int value) Update the value of the key if present, or inserts the key if not already present.
       When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.

To determine the least frequently used key, a use counter is maintained for each key in the cache.
The key with the smallest use counter is the least frequently used key.
When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation).
The use counter for a key in the cache is incremented either a get or put operation is called on it.

The functions get and put must each run in O(1) average time complexity.


Example 1:
Input
["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, 3, null, -1, 3, 4]

Explanation
// cnt(x) = the use counter for key x
// cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
LFUCache lfu = new LFUCache(2);
lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
lfu.get(1);      // return 1
                 // cache=[1,2], cnt(2)=1, cnt(1)=2
lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
                 // cache=[3,1], cnt(3)=1, cnt(1)=2
lfu.get(2);      // return -1 (not found)
lfu.get(3);      // return 3
                 // cache=[3,1], cnt(3)=2, cnt(1)=2
lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
                 // cache=[4,3], cnt(4)=1, cnt(3)=2
lfu.get(1);      // return -1 (not found)
lfu.get(3);      // return 3
                 // cache=[3,4], cnt(4)=1, cnt(3)=3
lfu.get(4);      // return 4
                 // cache=[4,3], cnt(4)=2, cnt(3)=3

Constraints:
0 <= capacity <= 104
0 <= key <= 105
0 <= value <= 109
At most 2 * 105 calls will be made to get and put.
* */

/*
Solution:
two hashMap + double linked list
1. HashMap<key, Node>
2. HashMap<cnt, DoubleList>
3. use a variable size to record the total size in the LFU cache
4. use a variable minCnt to record the current minimum frequency of a key
 */
public class LFUCache {
    class Node {
        int key, val, cnt;
        Node prev, next;
        Node(int key, int val) {
            this.key = key;
            this.val = val;
            cnt = 1;
        }
    }

    class DoubleList {
        Node head, tail;
        int size;
        DoubleList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        void add(Node node) {
            head.next.prev = node;
            node.next = head.next;
            node.prev = head;
            head.next = node;
            size++;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        Node removeLast() {
            if (size > 0) {
                Node node = tail.prev;
                remove(node);
                return node;
            }
            else return null;
        }
    }

    int capacity, size, minCnt;
    Map<Integer, Node> nodeMap;
    Map<Integer, DoubleList> countMap;
    public LFUCache(int capacity) {
        this.capacity = capacity;
        nodeMap = new HashMap<>();
        countMap = new HashMap<>();
        size = 0;
        minCnt = 1;
    }

    public int get(int key) {
        Node node = nodeMap.get(key);
        if (node == null) return -1;
        update(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;
        Node node;
        if (nodeMap.containsKey(key)) {
            node = nodeMap.get(key);
            node.val = value;
            update(node);
        }
        else {
            node = new Node(key, value);
            nodeMap.put(key, node);
            if (size == capacity) {
                DoubleList lastList = countMap.get(minCnt);
                nodeMap.remove(lastList.removeLast().key);
                size--;
            }
            size++;
            minCnt = 1;
            DoubleList newList = countMap.getOrDefault(node.cnt, new DoubleList());
            newList.add(node);
            countMap.put(node.cnt, newList);
        }
    }

    private void update(Node node) {
        DoubleList oldList = countMap.get(node.cnt);
        oldList.remove(node);
        if (node.cnt == minCnt && oldList.size == 0){
            countMap.remove(minCnt);
            minCnt++;
        }
        node.cnt++;
        DoubleList newList = countMap.getOrDefault(node.cnt, new DoubleList());
        newList.add(node);
        countMap.put(node.cnt, newList);
    }
}
