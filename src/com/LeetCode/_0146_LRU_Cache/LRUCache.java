package com.LeetCode._0146_LRU_Cache;
/*
146. LRU Cache

Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

1. LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
2. int get(int key) Return the value of the key if the key exists, otherwise return -1.
3. void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.

Follow up:
Could you do get and put in O(1) time complexity?
 */
/*
思路：
1. 由于题目要求get和put的时间复杂度为O(1)，空间肯定不能省，存取数据时间性能最好的就是哈希表，因此底层的数据结构一定是一个哈希表；
2. 根据题目意思，访问某个数据，时间优先级得提前，还有删除末尾结点的需求，这样的数据结构得在头尾访问数据最快，这种数据结构是「双向链表」；
3. 「链表」结点需要记录：1、value，2、key（在哈希表里删除的时候用得上），3、前驱结点引用，4、后继结点引用。

*/

import java.util.HashMap;

class LRUCache{

    class Node{
        int key, val;
        Node next, prev;
        Node(int k, int v){
            this.key = k;
            this.val = v;
        }
    }

    //普通双向链表的实现
    class DoubleList{
        Node head = new Node(0,0);
        Node tail = new Node(0,0);
        int size;

        private DoubleList(){
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        // 在链表头部添加节点 x，时间 O(1)
        void addFirst(Node x){
            Node headNext = head.next;
            head.next = x;
            headNext.prev = x;
            x.next = headNext;
            x.prev = head;
            size++;
        }

        // 删除链表中的 x 节点（x 一定存在）
        // 由于是双链表且给的是目标 Node 节点，时间 O(1)
        void remove(Node x){
            x.prev.next = x.next;
            x.next.prev = x.prev;
            size--;
        }

        // 删除链表中最后一个节点，并返回该节点，时间 O(1)
        Node removeLast(){
            Node last = tail.prev;
            remove(last);
            return last;
        };

        // 返回链表长度，时间 O(1)
        public int size(){
            return size;
        };
    }


    private HashMap<Integer, Node> map;
    private DoubleList cache;
    private int capacity;

    LRUCache(int capacity){
            this.capacity = capacity;
            map = new HashMap<>();
            cache = new DoubleList();
        }

    int get(int key){
            if (!map.containsKey(key)) return -1;
            int val = map.get(key).val;
            // 利用 put 方法把该数据提前
            put(key, val);
            return val;
        }

    void put(int key, int val){
            // 先把新节点 x 做出来
            Node x = new Node(key, val);
            if (map.containsKey(key)){
                // 删除旧的节点，新的插到头部
                cache.remove(map.get(key));
                cache.addFirst(x);
                // 更新 map 中对应的数据
                map.put(key, x);
            }else{
                if (capacity == cache.size()){
                    // 删除链表最后一个数据
                    Node last = cache.removeLast();
                    map.remove(last.key); // 为什么要在链表中同时存储 key 和 val，而不是只存储 val
                }
                // 直接添加到头部
                cache.addFirst(x);
                map.put(key, x);
            }
    }


    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1,1);
        cache.put(2,2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
}
