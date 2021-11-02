package Other._39_平衡二叉树;

import java.util.LinkedList;
import java.util.Queue;

/*
输入一棵二叉树，判断该二叉树是否是平衡二叉树。

在这里，我们只需要考虑其平衡性，不需要考虑其是不是排序二叉树


思路：
平衡二叉树（Balanced Binary Tree）又被称为AVL树（有别于AVL算法），且具有以下性质：
它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树

思路1：从根结点开始，对每个结点，遍历求其左右子树的高度是否 > 1

思路2：从根结点开始，需要对每个结点从上到下遍历判断，会有重复遍历；因此，可以从叶子结点开始往上遍历，如果不满足条件直接退出
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

    //bfs 求高度
    static boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) return true;

        return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right) && (Math.abs(helper(root.left) - helper(root.right)) < 2);
    }

    static int helper(TreeNode root){
        if (root == null) return 0;

        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int count = queue.size();
            while (count > 0){
                TreeNode temp = queue.poll();
                if (temp.left != null) queue.offer(temp.left);
                if (temp.right != null) queue.offer(temp.right);
                count--;
            }
            depth++;
        }

        return depth;
    }

    //dfs求高度
    static boolean IsBalanced2(TreeNode root) {
        if (root == null) return true;

        return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right) && (Math.abs(depth(root.left) - depth(root.right)) < 2);
    }

    static int depth(TreeNode root){
        return (root == null) ? 0 : Math.max(depth(root.right), depth(root.left)) + 1;
    }


    // 思路2 自底向上的递归
    static boolean IsBalanced3(TreeNode root) {
        return getDepth(root) != -1;
    }

    static int getDepth(TreeNode root) {
        if (root == null) return 0;

        int left = getDepth(root.left);
        if (left == -1) return -1;
        int right = getDepth(root.right);
        if (right == -1) return -1;

        return Math.abs(left - right) > 1 ? -1 : 1 + Math.max(left, right);
    }

    public static void main(String[] args) {
        System.out.println("====");
    }
}
