package com.LeetCode._0138_Copy_List_with_Random_Pointer;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.HashMap;

public class Solution {
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node Clone(Node head)
    {
        if(head == null) return head;

        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while(cur != null){
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while(cur != null){
            map.get(cur).next = map.get(cur.next); // cur.next 可能为null，但是java中map的key不存在返回为null
            map.get(cur).random = map.get(cur.random); // python中 map中key不存在会报错
            cur = cur.next;
        }

        return map.get(head);
    }

    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1,4);
        System.out.println(map.get(1));
        System.out.println(map.get(null));
    }
}
