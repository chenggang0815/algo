package LeetCode._0103_Binary_Tree_Zigzag_Level_Order_Traversal;
/*
103. Binary Tree Zigzag Level Order Traversal
Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
(i.e., from left to right, then right to left for the next level and alternate between).

Example 1:
        3
       / \
      9  20
         / \
        15  7
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]
*/

import java.util.*;

/*
思路1： time:o(n) space:o(n)
1. 在层次遍历的基础上，每次遍历完一层，判断是否要反转当前层的元素
    1.1 偶数高度正序 => if(depth % 2 == 0)
    1.2 奇数高度逆序 => if(depth % 2 == 1)

思路2：双端队列  time:o(n) space:o(n)
LinkedList<Integer> temp = new LinkedList<>();

1. 如果正序 => 从左至右，每次将被遍历到的元素插入至双端队列的末尾 LinkedList.addLast(node.val)
2. 如果逆序 => 从右至左，每次将被遍历到的元素插入至双端队列的头部 LinkedList.addFirst(node.val)
参考剑指offer 按之字形顺序打印二叉树
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

    // Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Zigzag Level Order Traversal.
    // 偶数高度正序；奇数高度反序
    static List<List<Integer>> zigzagLevelOrder1(TreeNode root){
        if (root == null) return new ArrayList<>();

        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()){
            int count = queue.size();
            List<Integer> temp1 = new ArrayList<>();
            while (count > 0) {
                TreeNode node = queue.poll();
                temp1.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
                count--;
            }
            List<Integer> temp2 = new ArrayList<>();
            if (depth % 2 == 1){
                for (int i = temp1.size() - 1; i >= 0; i--){
                    temp2.add(temp1.get(i));
                }
                res.add(temp2);
            }else {
                res.add(temp1);
            }
            depth++;
        }
        return res;
    }


    static List<List<Integer>> zigzagLevelOrder2(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int index = 0;

        while (!queue.isEmpty()){
            int count = queue.size();
            Deque<Integer> levelList = new LinkedList<Integer>();
            while (count > 0) {
                TreeNode node = queue.poll();
                if (index % 2 == 0) levelList.addLast(node.val); // 如果正序 => 从左至右，每次将被遍历到的元素插入至双端队列的末尾 LinkedList.addLast(node.val)
                else levelList.addFirst(node.val); //  如果逆序 => 从右至左，每次将被遍历到的元素插入至双端队列的头部 LinkedList.addFirst(node.val)
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
                count--;
            }
            index++;
            res.add(new LinkedList<>(levelList));
        }
        return res;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;

        System.out.println(zigzagLevelOrder2(root));
    }
}
