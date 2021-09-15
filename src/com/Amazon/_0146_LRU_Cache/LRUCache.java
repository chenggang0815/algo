package com.Amazon._0146_LRU_Cache;

import java.util.HashMap;

public class LRUCache {
    class Node{
        int key, value;
        Node pre, next;
        Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    class DoubleList{
        Node head = new Node(0, 0);
        Node tail = new Node(0, 0);
        int size = 0;


        DoubleList(){
            head.pre = tail;
            tail.next = head;
        }

        //head -> headNext -> node2 -> tail
        void remove(Node node){

        }

        //head -> headNext -> node2 -> tail
        //head <- headNext
        void addFirst(Node node){
            Node headNext = head.next;
            node.next = headNext;
            node.pre = headNext;
            headNext.pre = node;
            head.next = node;
        }

        int size(){
            return size;
        }
    }



    private int capacity;
    HashMap<Integer, Node> map;
    DoubleList cache;
    LRUCache(int capacity){
        this.capacity = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    int get(int k){
        if (!map.containsKey(k)) return -1;
        cache.remove(map.get(k));
        cache.addFirst(map.get(k));

        return map.get(k).value;
    }

    void put(int key, int value){
        Node node = new Node(key, value);
        if (capacity == cache.size()){
            Node tail = cache.tail.pre;
            cache.remove(tail);
            cache.addFirst(node);
            map.remove(tail.key);
            map.put(key, node);
        }else {
            cache.addFirst(node);
            map.put(key, node);
        }
    }
    public static void main(String[] args) {

    }
}
