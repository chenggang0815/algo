package com.LeetCode._0662_二叉树的最大宽度;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/*
662. 二叉树最大宽度

给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。
这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。

每一层的宽度被定义为:该层最左和最右的非空节点（两端点间的null节点也计入长度）之间的长度。


思路：
一 bfs
由于需要将给定树中的每个节点都访问一遍 => 可以用深度优先搜索或者宽度优先搜索将树遍历。

主要想法是给每个节点一个position值
如果我们走向左子树，那么 position -> position * 2
如果我们走向右子树，那么 position -> positon * 2 + 1
当我们在看同一层深度的位置值 L 和 R 的时候，宽度就是 R - L + 1

宽度优先搜索顺序遍历每个节点的过程中，我们记录节点的 position 信息，
对于每一个深度，第一个遇到的节点是最左边的节点，最后一个到达的节点是最右边的节点

二 dfs
按照深度优先的顺序，记录每个节点的position
对于每一个深度，第一个到达的位置会被记录在 left[depth] 中
然后对于每一个节点，对应这一层的可能宽度是pos - left[depth] + 1
将每一层这些可能的宽度去一个最大值就是答案。

 */
public class Solution {
    class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int val){
            this.val = val;
        }
    }



    // bfs  time: o(n) space:o(n)
    class Node{
        TreeNode node;
        int depth;
        int position;
        Node(TreeNode node, int depth, int position){
            this.node = node;
            this.depth = depth;
            this.position = position;
        }
    }

    public int widthOfBinaryTree1(TreeNode root) {
        if (root == null) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(root, 0, 0));
        int curDpeth = 0, left = 0, res = 0;
        while (!queue.isEmpty()){
            Node temp = queue.poll();
            queue.add(new Node(temp.node.left, temp.depth + 1, temp.position * 2));
            queue.add(new Node(temp.node.right, temp.depth + 1, temp.position * 2 + 1));
            if (curDpeth != temp.depth){
                left = temp.position;
                curDpeth = temp.depth;
            }

            res = Math.max(res, temp.position - left + 1);
        }

        return res;
    }

    // dfs time: o(n) space:o(n)
    int res;
    HashMap<Integer, Integer> left;
    public int widthOfBinaryTree2(TreeNode root) {
        res = 0;
        left = new HashMap<>();
        dfs(root, 0, 0);

        return res;
    }

    public void dfs(TreeNode root, int depth, int position){
        if (root == null) return;
        left.computeIfAbsent(depth, x -> position);
        res = Math.max(res, position - left.get(depth) + 1);
        dfs(root.left, depth + 1, position * 2);
        dfs(root.right, depth + 1, position * 2 + 1);
    }




        public static void main(String[] args) {

    }
}
