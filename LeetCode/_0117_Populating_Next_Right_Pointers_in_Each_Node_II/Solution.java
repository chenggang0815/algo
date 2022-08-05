package LeetCode._0117_Populating_Next_Right_Pointers_in_Each_Node_II;

import java.util.LinkedList;
import java.util.Queue;

/*
117. Populating Next Right Pointers in Each Node II
Given a binary tree
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

for example:
      1               1 -> null
     / \    =>      /  \
    2   3          2 ->3 -> null
    \   \          \   \
    5   7          5 ->7 -> null

*/

/*
Solution:
Approach 1: level order traversal, same to question 116

Approach 2: time:O(n) space:O(1)
1. we use 3 variables and the next pointer to reduce the space
2. we use cur denote the current node in the current level, we move cur by cur = cur.next
3. we use leftmost to denote the left most node in the next level, it is the start node in the next level
4. we use the nextLevelCur to traverse the next level and build the next relationship
*/
public class Solution {
    class Node{
        Node left;
        Node right;
        Node next;
        int val;

        Node(int val){
            this.val =val;
        }
    }

    public Node connect1(Node root) {
        if(root == null) return root;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size > 0){
                Node cur = queue.poll();
                size--;
                if(size > 0) cur.next = queue.peek();
                if(cur.left != null) queue.add(cur.left);
                if(cur.right != null) queue.add(cur.right);
            }
        }

        return root;
    }

    public Node connect2(Node root){
        Node leftMost = root;
        Node cur = null;
        Node nextCur = null;
        while (leftMost != null){
            cur = leftMost;
            leftMost = null;
            nextCur = null;
            while (cur != null){
                if (cur.left != null){
                    if (nextCur != null){
                        nextCur.next = cur.left;
                    }else{
                        leftMost = cur.left;
                    }
                    nextCur = cur.left;
                }
                if (cur.right != null){
                    if (nextCur != null){
                        nextCur.next = cur.right;
                    }else{
                        leftMost = cur.right;
                    }
                    nextCur = cur.right;
                }
                cur = cur.next;
            }
        }

        return root;
    }

    public static void main(String[] args) {

    }
}
