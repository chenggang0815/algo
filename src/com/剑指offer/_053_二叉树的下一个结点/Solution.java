package com.剑指offer._053_二叉树的下一个结点;
/*
二叉树的下一个结点

给定一个二叉树其中的一个结点，请找出此结点中序遍历顺序的下一个结点并且返回。
注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。

思路:
分三种情况：

1. 当前结点的右结点不为空，从右子结点开始向左子结点遍历，直到叶子结点，叶子结点为下一个结点。

2. 当前结点的右结点为空：
   2-1. 当前结点是父结点的左子结点 ： 父结点为下一个结点。
   2-2. 当前结点是父结点的右子结点 ： 向上遍历父结点，直到当前父节点是左子节点

 */
public class Solution {
    static public class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode parent = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}

    public TreeLinkNode GetNext(TreeLinkNode pNode)
    {
        if (pNode == null) return pNode;

        if (pNode.right != null){
            TreeLinkNode temp = pNode.right;
            while (temp.left != null){
                temp = temp.left;
            }

            return temp;
        }else if (pNode.parent != null && pNode.parent.left == pNode){
            return pNode.parent;
        }else if (pNode.parent != null && pNode.parent.right == pNode){
            TreeLinkNode temp = pNode.parent;
            while (temp.parent != null && temp.parent.left == temp){
                temp = temp.parent;
            }

            return temp.parent;
        }else {
            return pNode.parent;
        }
    }
    public static void main(String[] args) {

    }
}
