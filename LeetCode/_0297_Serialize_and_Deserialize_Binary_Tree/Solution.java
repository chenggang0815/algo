package LeetCode._0297_Serialize_and_Deserialize_Binary_Tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
297. Serialize and Deserialize Binary Tree
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work.
You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Clarification: The input/output format is the same as how LeetCode serializes a binary tree.
You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
*/
/*
Solution:
Approach1 time:O(n) space:O(n)
1. similar to question 105/106, we construct a binary tree from inorder and preorder/postorder.
    1.1 we need two list to construct a tree, because we don't know null node, we need rootIndex to check the left side and right side.
2. but we can only a preorder/postorder to construct a binary tree because we know the null node in the array

for this question:
1. serialize : use preorder/postorder to dfs the tree, use "#" denote the null node
2. deserialize :  for preorder => root-left-right => 1,2,4,#,#,#,3,6,#,#,7,#,#

                  for postorder => left-right-root => string=#,#,4,#,2,#,#,6,#,#,7,3,1
                  1
                 / \
                2   3
               /   / \
              4   6   7
                 so we need reverse the string first, then we get root-right-left => similar to preorder

*/
public class Solution {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
        }
    }
    static public String serialize(TreeNode root){
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);

        return sb.toString();
    }

    static public void buildString(TreeNode root, StringBuilder sb){
        if (root == null) sb.append("#").append(",");
        else{
            sb.append(root.val).append(",");
            buildString(root.left, sb);
            buildString(root.right, sb);
        }
    }

    static public TreeNode deserialize(String data){
        Queue<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(",")));

        return buildTree(nodes);
    }

    static public TreeNode buildTree(Queue<String> nodes){
        String val = nodes.poll();
        if (val.equals("#")) return null;
        else {
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
    }

    static void preorder(TreeNode root){
        if (root == null) return;

        System.out.println(root.val);
        preorder(root.left);
        preorder(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(7);
        root.left = node1;
        node1.left = node3;
        root.right = node2;
        node2.left = node4;
        node2.right = node5;
        System.out.println(serialize(root));
        preorder(deserialize(serialize(root)));

    }
}
