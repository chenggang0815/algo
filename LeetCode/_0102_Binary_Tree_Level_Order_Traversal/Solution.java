package LeetCode._0102_Binary_Tree_Level_Order_Traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
102. Binary Tree Level Order Traversal
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7

return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
 */
/*
solution:
Approach 1: bfs
Approach 2: dfs (pre-order) we use pre-order to make sure we create all the level list first, can we get use res.get(level)

similar question with 107
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

    //bfs
    static public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int count = queue.size();
            List<Integer> temp = new ArrayList<>();
            while (count > 0){
                TreeNode node = queue.poll();
                temp.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
                count--;
            }
            res.add(temp);
        }

        return res;
    }

    // dfs pre-order, we use pre-order to make sure we create all the level list first, can we get use res.get(level)
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;

        dfs(root, 0, res);

        return res;
    }
    // pre-order => node -> left -> right
    void dfs(TreeNode root, int level, List<List<Integer>> res){
        if(root == null) return;

        if(level == res.size()) res.add(new ArrayList<>());
        res.get(level).add(root.val);
        dfs(root.left, level + 1, res);
        dfs(root.right, level + 1, res);
    }

    public static void main(String[] args) {
   /*
    3
   / \
  9  20
    /  \
   15   7
    */
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);


        root.left = node1;
        root.right = node2;;
        node2.right = node3;
        node3.left = node4;

        System.out.println(levelOrder1(root));


    }
}
