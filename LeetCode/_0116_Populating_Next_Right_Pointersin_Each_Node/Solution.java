package LeetCode._0116_Populating_Next_Right_Pointersin_Each_Node;

import java.util.LinkedList;
import java.util.Queue;

/*
116. Populating Next Right Pointers in Each Node

You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

example:
      input     =>        output
         1               1->null
       /  \            /  \
      2    3    =>    2 -> 3->null
    / \  /  \        / \  /  \
   4  5 6   7       4->5->6->7->null

Follow up:
1. You may only use constant extra space.
2. Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
*/

/*
Solution:
Approach 1: time:O(n) space:O(n)
1. use level order traversal, for each level, we traverse the tree from left to right
2. for each level, if there exits at least one node in the queue, we have curNode.next = queue.peek()
3. for example, when we traverse level = 2, we have queue = [2,3] => curNode = 2, queue= [3] => curNode.next = 3

Approach 2: time:O(N) space:O(1)
example:
      input     =>        output
         1               1->null
       /  \            /  \
      2    3    =>    2 -> 3->null
    / \  /  \        / \  /  \
   4  5 6   7       4->5->6->7->null
1. we can observe the above binary tree, and we can find two patterns
2. pattern 1:
   for node 2 and node 3 => we can connect node 2 and node 3 => 1.left.next = 1.right => because node 2 and node 3 have the similar parent node
   for node 4 and node 5 => we can connect node 4 and node 5 => 2.left.next = 2.right => because node 4 and node 5 have the similar parent node
3. pattern 2:
   for node 5 and node 6 => these two nodes don't have similar parent node, but if we already connect the node 2 and the node 3
   => we can use 2.right.next = 2.next.left => 5.next = 6
*/
public class Solution {
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

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
                size--;
                if (size > 0) node.next = queue.peek(); // if current level exists node, current_node.next = queue.peek()
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }

        return root;
    }

    static Node connect2(Node root){
        Node leftMost = root;

        while (leftMost != null){
            Node cur = leftMost;
            while(cur != null){
                // case 1 => similar parent node
                if(cur.left != null) cur.left.next = cur.right;
                // case 2
                if(cur.next != null && cur.left != null) cur.right.next = cur.next.left;
                cur = cur.next;
            }
            leftMost = leftMost.left;
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
