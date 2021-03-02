package com.LeetCode._0257_Binary_Tree_Paths;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
257. Binary Tree Paths

Given a binary tree, return all root-to-leaf paths.
Note: A leaf is a node with no children.

Example:
Input:

   1
 /   \
2     3
 \
  5
Output: ["1->2->5", "1->3"]
Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 */
/*
思路1：dfs

思路2：bfs
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
    /*
    LeetCode在每次提交代码的时候不会对全局变量进行重新初始化
	所以会导致全局变量在第二次运行的时候还是上次的值 => 不是空的所以在第二次输入的时候自然答案就错了，最后无法提交
    解决方法就是每次手动初始化一下全局变量
    */
    static List<String> res = new ArrayList<>(); // 使用全局变量，好像会影响leetcode的答案

    static void dfs1(TreeNode root, StringBuilder sb){
        if (root == null) {
            //sb.delete(sb.length() - 2, sb.length());
            return;
        }
        sb.append(root.val);
        if (root.left == null && root.right == null){
            res.add(sb.toString());
            return;
        }
        //dfs(root.left, sb.append("->"));
        //dfs(root.right, sb.append("->"));
        /*为什么错误？  => 因为对于stringbuilder来说，每次append之后的值不会再有变化，只能追加
                      => 对于一般的数字变量 eg int temp = 1 每次值改变之后 temp = 2  都是不同的值
                      => 对于stringbuilder 除非手动删除 append之后，上层递归也是最后append之后的值
                      => 这也是为什么要写42行的原因
         */

        dfs1(root.left, new StringBuilder(sb).append("->"));
        dfs1(root.right, new StringBuilder(sb).append("->"));

    }



    static List<String> binaryTreePaths1(TreeNode root) {
        res.clear(); // 解决方法就是每次手动初始化一下全局变量
        StringBuilder sb = new StringBuilder();
        if (root == null) return res;
        dfs1(root, sb);
        return res;
    }


    static void dfs2(TreeNode root, List<String> res, String s){
        if (root == null) {
            //s.substring(0, s.length() - 2); 这一行不需要 s在每层递归都不一样
            return;
        }
        s += root.val;
       //s += String.valueOf(root.val);

        if (root.left == null && root.right == null){
            res.add(s);
            return;
        }

        dfs2(root.left, res,s +"->");
        dfs2(root.right, res,s + "->");

    }

    static List<String> binaryTreePaths2(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;

        dfs2(root, res, "");

        return res;
    }

    // bfs Runtime: 9 ms, faster than 31.34% of Java online submissions for Binary Tree Paths.
    static List<String> binaryTreePaths3(TreeNode root) {
        List<String> res = new ArrayList<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<String> pathQueue = new LinkedList<>();

        if (root == null) return res;

        nodeQueue.offer(root);
        pathQueue.offer(String.valueOf(root.val));
        while (!nodeQueue.isEmpty()){
            TreeNode node = nodeQueue.poll();
            String path = pathQueue.poll();
            if (node.right == null && node.left == null){
                res.add(path);
            }else{
                if (node.left != null){
                    nodeQueue.offer(node.left);
                    pathQueue.offer(path + "->" + String.valueOf(node.left.val));
                }

                if (node.right != null){
                    nodeQueue.offer(node.right);
                    pathQueue.offer(path + "->" + node.right.val);
                }
            }
        }

        return res;
    }

/*
   1
 /   \
2     3
 \
  5
 */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(5);
        root.left = node1;
        root.right = node2;
        node1.right = node3;
        System.out.println(binaryTreePaths3(root));
    }
}
