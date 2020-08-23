package com.leetcode._0051;

/*
输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)

B是A的子结构， 即 A中有出现和B相同的结构和节点值。

例如:
给定的树 A:

     3
    / \
   4   5
  / \
 1   2
给定的树 B：

   4
  /
 1
返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val=val;
        }
    }
    static boolean isSubStructure(TreeNode root1, TreeNode root2) {
        //if(root1 == null && root2 == null) return true; 输入root1,root2 = {}时，为false
        if (root1==null||root2==null) return false;

        return helper(root1,root2)||isSubStructure(root1.left,root2)||isSubStructure(root1.right,root2);
    }

    static boolean helper(TreeNode root1, TreeNode root2){
        if (root2==null) return true;
        if (root1==null) return false;

        return root1.val==root2.val&&helper(root1.left,root2.left)&&helper(root1.right,root2.right);
    }

    public static void main(String[] args) {

        TreeNode root1 = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(4);

        TreeNode root2 = new TreeNode(2);
        root1.left = node2;
        root1.right = node1;
        node2.left = node3;
        root2.left = node4;

        System.out.println(isSubStructure(root1,root2));

    }
}

