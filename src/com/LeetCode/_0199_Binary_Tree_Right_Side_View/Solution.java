package com.LeetCode._0199_Binary_Tree_Right_Side_View;

import java.util.List;
/*
199. Binary Tree Right Side View

Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example 1:
Input: root = [1,2,3,null,5,null,4]
Output: [1,3,4]
 */

/*
思路1： bfs 每层最后一个元素

思路2： dfs root -> right -> left 顺序，每层都是最右边
 */
public class Solution {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
        }
    }

    public List<Integer> rightSideView(TreeNode root) {

    }

    public static void main(String[] args) {
        System.out.println();
    }
}
