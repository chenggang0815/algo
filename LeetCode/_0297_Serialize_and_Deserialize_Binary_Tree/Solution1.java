package LeetCode._0297_Serialize_and_Deserialize_Binary_Tree;

import java.util.Arrays;
import java.util.Stack;

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
public class Solution1 {
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
            buildString(root.left, sb);
            buildString(root.right, sb);
            sb.append(root.val).append(",");
        }
    }

    static public TreeNode deserialize(String data){
        Stack<String> nodes = new Stack<>();
        nodes.addAll(Arrays.asList(data.split(",")));

        return buildTree(nodes);
    }

    static public TreeNode buildTree(Stack<String> nodes){
        String val = nodes.pop();
        if (val.equals("#")) return null;
        else {
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.right = buildTree(nodes);
            node.left = buildTree(nodes);
            return node;
        }
    }

    static void postorder(TreeNode root){
        if (root == null) return;

        postorder(root.left);
        postorder(root.right);
        System.out.println(root.val);
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
        postorder(deserialize(serialize(root)));

    }
}

