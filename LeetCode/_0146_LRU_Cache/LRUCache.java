package LeetCode._0146_LRU_Cache;
/*
146. LRU Cache

Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:
1. LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
2. int get(int key) Return the value of the key if the key exists, otherwise return -1.
3. void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache.
    If the number of keys exceeds the capacity from this operation, evict the least recently used key.

Follow up:
Could you do get and put in O(1) time complexity?
 */
/*
思路：
1. 由于题目要求get和put的时间复杂度为O(1)，空间肯定不能省，存取数据时间性能最好的就是哈希表，因此底层的数据结构一定是一个哈希表；
2. 根据题目意思，访问某个数据，时间优先级得提前，还有删除末尾结点的需求，这样的数据结构得在头尾访问数据最快，这种数据结构是「双向链表」；
3. 「链表」结点需要记录：1、value，2、key（在哈希表里删除的时候用得上），3、前驱结点引用，4、后继结点引用。

We can solve this problem use a hashmap to keep track of the key and it's value in a double linked list.

Why HashMap?
- Because we want to implement get() and put() function, so the first thing comes to my mind is to use hashMap to store the key and it's value
Why double linked list?
- And we want keep the order of the key,value pair, and delete the first added key, so we can consider double linked list to store the key value pair
- One advantage of double linked list is that the node can remove itself without other reference.
- In addition, it takes constant time to add and remove nodes from the head or tail.

capacity = 3
1. 1->2 =>put(3,2) => addFirst() => 3->1->2 => put(4,3) => 1.delete last element and add (4,3) to the first

implementation:
1. for double list, we need to initialize a variable to keep track the size of double list and two dummy nodes, head node and tail node

*/

import java.util.HashMap;

class LRUCache{

    class Node{
        int key, val;
        Node next, prev;
        Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    //普通双向链表的实现
    class DoubleList{
        Node head = new Node(0,0);
        Node tail = new Node(0,0); // // find the last node in double list
        int size;

        DoubleList(){
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        // 在链表头部添加节点 x，时间 O(1)
        void addFirst(Node node){
            Node firstNode = head.next;
            head.next = node;
            firstNode.prev = node;
            node.next = firstNode;
            node.prev = head;
            size++;
        }

        // remove a node
        // 1. when call get, we need to remove this node and then add node to first
        // 2. when call put, if list contains key, we need to remove this node and then add node to first
        // 1->2->3
        void remove(Node node){
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }
    }


    HashMap<Integer, Node> map;
    DoubleList cache;
    int capacity;

    LRUCache(int capacity){
            this.capacity = capacity;
            map = new HashMap<>();
            cache = new DoubleList();
        }

    int get(int key){
            if (!map.containsKey(key)) return -1;

            cache.remove(map.get(key));
            cache.addFirst(map.get(key));

            return map.get(key).val;
        }

    void put(int key, int val){
        //        // case 1. if cache doesn't contain node
        //            // case 1.1 size < capacity => add node to first
        //            // case 1.2 size == capacity => delete last node, add node to first
        //        // case 2, if cache contains node, => remove node and then add node to first
            Node node = new Node(key, val);
            if (map.containsKey(key)){ // Update the value of the key if the key exists. 删除旧的节点，新的插到头部
                cache.remove(map.get(key));
                cache.addFirst(node);
            }else{
                if (capacity == cache.size){
                    // 删除链表最后一个数据
                    Node last = cache.tail.prev;
                    cache.remove(last);
                    map.remove(last.key); // that's why we need to store the key in the node, becuase when we delete the last Node in the double list, we need to know the key of that node.
                }
                cache.addFirst(node);
            }

            map.put(key, node);
    }


    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(1);
        lRUCache.put(2, 1);
        System.out.println(lRUCache.get(2));
        lRUCache.put(3, 2);
        System.out.println(lRUCache.get(2));
        System.out.println(lRUCache.get(3));
    }
}
