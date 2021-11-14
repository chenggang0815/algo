package LeetCode._0107_Binary_Tree_Level_Order_Traversal_II;

import java.util.*;

/*
107. Binary Tree Level Order Traversal II
Given the root of a binary tree,
return the bottom-up level order traversal of its nodes' values. (i.e., from left to right, level by level from leaf to root).

Example 1:
            3
           / \
          9  20
            / \
           15 7
Input: root = [3,9,20,null,null,15,7]
Output: [[15,7],[9,20],[3]]

similar question with 102
*/
public class Solution {
    // dfs
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
        }
    }
    public List<List<Integer>> levelOrderBottom1(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
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
            res.addFirst(temp);
        }

        return res;
    }

    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;

        dfs(root, 0, res);
        Collections.reverse(res);

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
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp1 = Arrays.asList(1,2,3);
        List<Integer> temp2 = Arrays.asList(4,5,6);
        res.add(temp1);
        res.add(temp2);
        System.out.println(res);
        Collections.reverse(res);
        System.out.println(res);


    }
}
