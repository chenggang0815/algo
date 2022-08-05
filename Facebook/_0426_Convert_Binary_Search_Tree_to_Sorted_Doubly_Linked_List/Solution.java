package Facebook._0426_Convert_Binary_Search_Tree_to_Sorted_Doubly_Linked_List;

import java.util.ArrayList;
import java.util.List;

/*
    4
   / \
  2  5
 / \
1  3
*/
/*
Solution:
Approach 1: inorder traversal time:O(n) space:O(n)

 */
public class Solution {
    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };

    static public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        List<Node> res = new ArrayList<>();

        dfs(root, res);
        for(int i = 0; i < res.size() - 1; i++){
            res.get(i).right = res.get(i + 1);
            res.get(i + 1).left = res.get(i);
        }

        res.get(0).left = res.get(res.size() - 1);
        res.get(res.size() - 1).right = res.get(0);

        return res.get(0);
    }

    static void dfs(Node root, List<Node> res){
        if (root == null) return;
        dfs(root.left, res);
        res.add(root);
        dfs(root.right, res);
    }



    public static void main(String[] args) {
        Node root = new Node(4);
        Node node1 = new Node(2);
        Node node2 = new Node(5);
        Node node3 = new Node(1);
        Node node4 = new Node(3);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        treeToDoublyList(root);
    }
}
