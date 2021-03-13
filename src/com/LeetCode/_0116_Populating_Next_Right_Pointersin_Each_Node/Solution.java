package com.LeetCode._0116_Populating_Next_Right_Pointersin_Each_Node;

import java.util.LinkedList;
import java.util.Queue;

/*
116. Populating Next Right Pointers in Each Node
将二叉树的每一层节点都连接起来形成一个链表
You are given a perfect binary tree where all leaves are on the same level, and every parent has two children.
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL

Follow up:
1. You may only use constant extra space.
2. Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.

思路1：层次遍历 time: o(n) space:o(n)
1. 对二叉树进行层次遍历，在层次遍历的过程中将我们将二叉树每一层的节点拿出来遍历并构建链表
2. 如果当前层还有两个以及上的结点，将当前节点指向它右侧的结点 => if (size > 1) node.next = queue.peek();

思路2：迭代：利用已经建立的next指针，建立递推关系 time: o(n) space:o(1)
    1
   / \
  2   3
 / \  / \
4  5 6   7

2. head节点为父节点
    2.1 第一种 同一层两个串联的节点都有一个共同的父节点（4和5） => head.left.next = head.right; // 4.next = 5

    2.1 第二种 同一层两个串联的节点的父节点不同（5和6）利用上层父节点的next指针
if (head.next != null){ // 如果当前节点不是当前层最右边的结点，则 => 5.next = 6
    head.right.next = head.next.left;
}

思路3：递归
*/
public class Solution {
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect1(Node root) {
        if (root == null) return root;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            while (size > 0){
                Node node = queue.poll();
                if (size > 1) node.next = queue.peek(); //如果当前层还有两个以及上的结点，将当前节点指向它右侧的结点
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
                size--;
            }
        }

        return root;
    }
    /*
    1
   / \
  2   3
 / \  / \
4  5 6   7

    第一层
    root = leftmost
    leftmost = head
    head = left
    head.left.next = head.right => 2.next = 3
    第二层
    leftmost = leftmost.left => leftmost = 1.left = 2
    head = leftmost
    head.left.next = head.right => 4.next = 5
    head.right.next = head.next.left => 5.next = 2.next.left = 3.left = 6 => 5.next = 6
     */
    static Node connect2(Node root){
        if (root == null) return root;

        Node leftmost = root;

        while (leftmost.left != null){ //如果当前层为最后一层，则结束循环
            Node head = leftmost;
            while (head != null){ //当前节点为空，本层遍历结束
                head.left.next = head.right; // 4.next = 5

                if (head.next != null){ // 如果当前节点不是当前层最右边的结点，则 => 5.next = 6
                    head.right.next = head.next.left;
                }
                head = head.next; // 当前节点向本层的右边移动一位
            }

            leftmost = leftmost.left; //leftmost这一层遍历结束，从下一层的最左边的结点开始遍历
        }

        return root;
    }

    static Node connect3(Node root){
        dfs(root);
        return root;
    }
/*
                              1
                          /       \
                         2         3
                       /  \      /    \
                      4    5    6      7
                     / \ / \   /  \   /  \
                    8  9 10 11 12 13 14 15
                    第一轮循环
                    root = 1
                    left = 2
                    right = 3
                    2.next = 3
                    left = 5
                    right = 6
                    5.next = 6
                    left = 11
                    right = 12
                    11.next = 12

                    第二轮循环
                    root = 2
                    left = 2.left = 4
                    right = 2.right = 5
                    4.next = 5
                    9.next = 10
                    ...
 */
    static void dfs(Node root){
        if (root == null) return;

        Node left = root.left;
        Node right = root.right;

        while (left != null){
            left.next = right;
            left = left.right;
            right = right.left;
        }

        dfs(root.left);
        dfs(root.right);
    }

    public static void main(String[] args) {


    }
}
