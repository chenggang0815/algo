package Amazon._00146_LRU_Cache;

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
        int size;

        DoubleList(){
            // head -> tail
            // head <- tail
            head.next = tail;
            tail.pre = head;
        }

        //head -> headNext -> node2 -> tail
        void remove(Node node){
             Node preNode = node.pre;
             Node nextNode = node.next;
             preNode.next = nextNode;
             nextNode.pre = preNode;
             size--;
        }

        //head -> headNext -> node2 -> tail
        //head <- headNext
        void addFirst(Node node){
            Node headNext = head.next;
            node.next = headNext;
            //node.pre = headNext;
            node.pre = head;
            headNext.pre = node;
            head.next = node;
            size++;
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
        if (map.containsKey(key)){
            cache.remove(map.get(key));
            cache.addFirst(node);
        }
        else{
            if (capacity == cache.size){
                Node tail = cache.tail.pre;
                cache.remove(tail);
                map.remove(tail.key);
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
