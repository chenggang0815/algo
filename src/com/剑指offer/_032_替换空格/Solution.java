package com.剑指offer._032_替换空格;
/*
替换空格

请实现一个函数，将一个字符串中的每个空格替换成“%20”。
例如，当字符串为We Are Happy.
则经过替换之后的字符串为We%20Are%20Happy。
 */
public class Solution {
    /*
   递归思路：
   1. 如果当前两个节点的val相等，那么递归判断checkSubTree(t1.left,t2.left) && checkSubTree(t1.right,t2.right)
   2. 如果当前两个节点的val不相等，那么递归判断checkSubTree(t1.left,t2) || checkSubTree(t1.right,t2)
   3. 最后处理边界条件
     */
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val=val;
        }
    }

    static boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if (t1==null&&t2==null) return true;  //当输入t1 t2 都为null时，t2还是t1的子树
        if (t1==null||t2==null) return false;

        return (t1.val==t2.val&&checkSubTree(t1.left,t2.left)&&checkSubTree(t1.right,t2.right))||checkSubTree(t1.right,t2)||checkSubTree(t1.left,t2);
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode root2 = new TreeNode(2);
        root1.left = node2;
        root1.right = node1;

        System.out.println(checkSubTree(root1,root2));


    }
}
