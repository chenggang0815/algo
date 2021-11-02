package Other._04_重建二叉树;
/*
输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，
则重建二叉树并返回。
 */

/*
假设 前序遍历的数组 pre = {1,2,5,7,3,4,8};
     中序遍历的数组 in = {2,5,7,1,3,4,8};

可知： 1.前序遍历的pre[0]就是当前的root结点；然后依次是left子节点，right子节点
      2.中序遍历的root结点前面全部是left子结点，后面全是right子结点
      3.并且前序数组的长度总是等于中序数组

利用递归的思想，找出每个中间结点（root结点），再分别找出root结点的left、right子结点

递归的出口为：pre.length==0，表示已经遍历到叶子结点，没有左右子节点

eg：
root结点pre[0] = 1，在in数组中的index = 3
则在in数组中{2,5,7}全部是pre[0]的left子结点，{3,4,8}全部是pre[0]的right子结点

在分别递归root结点的左右子节点的前序，中序遍历数组，直到叶子结点。
 */

import java.util.Arrays;

public class Solution {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
        }
    }

    static TreeNode reConstructBinaryTree(int[] pre, int[] in){
        if (pre.length==0) return null;
        TreeNode root = new TreeNode(pre[0]);
        int rootIndex = 0;
        for (int i=0;i<in.length;i++){
            if (pre[0]==in[i]){
                rootIndex = i;
                break;
            }
        }

        root.left = reConstructBinaryTree(Arrays.copyOfRange(pre,1,rootIndex+1),Arrays.copyOfRange(in,0,rootIndex));
        root.right = reConstructBinaryTree(Arrays.copyOfRange(pre,rootIndex+1,pre.length),Arrays.copyOfRange(in,rootIndex+1,in.length));

        return root;
    }

    static void preOrder(TreeNode root){
        if (root==null) return;
        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    static void inOrder(TreeNode root){
        if (root==null) return;
        preOrder(root.left);
        System.out.println(root.val);
        preOrder(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(7);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(8);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;

        int[] pre = new int[]{1,2,5,7,3,4,8};
        int[] in = new int[]{2,5,7,1,3,4,8};

        TreeNode newroot = reConstructBinaryTree(pre,in);


        preOrder(root);
        System.out.println("======");
        inOrder(root);

        System.out.println("====== after recon ===========");
        preOrder(newroot);
        System.out.println("======");
        inOrder(newroot);
    }
}
