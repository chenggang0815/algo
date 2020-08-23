package com.剑指offer._055;

import java.util.Arrays;

/*
序列化二叉树


请实现两个函数，分别用来序列化和反序列化二叉树

二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，从而使得内存中建立起来的二叉树可以持久保存。序列化可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，序列化的结果是一个字符串，序列化时通过 某种符号表示空节点（#），以 ！ 表示一个结点值的结束（value!）。

二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。

 */

/*
思路：
链接：https://www.nowcoder.com/questionTerminal/cf7e25aa97c04cc1a68c8f040e71fb84?f=discussion
来源：牛客网

    算法思想：根据前序遍历规则完成序列化与反序列化。所谓序列化指的是遍历二叉树为字符串；所谓反序列化指的是依据字符串重新构造成二叉树。
    依据前序遍历序列来序列化二叉树，因为前序遍历序列是从根结点开始的。当在遍历二叉树时碰到Null指针时，这些Null指针被序列化为一个特殊的字符“#”。
    另外，结点之间的数值用逗号隔开。
 */
public class Solution {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    String Serialize(TreeNode root) {
        if (root == null) return "#";

        StringBuilder res = new StringBuilder();
        helper(root, res);

        return res.toString();
    }

    void helper(TreeNode root, StringBuilder res){
        if (root == null){
            res.append("#,");
            return;
        }

        res.append(root.val + ",");
        helper(root.left, res);
        helper(root.right, res);
    }

    int index = -1;
    TreeNode Deserialize(String str) {
        index++;
        String[] input = str.split(",");
        TreeNode node = null;
        if (!input[index].equals("#")){
            node = new TreeNode(Integer.valueOf(input[index]));
            node.left = Deserialize(str);
            node.right = Deserialize(str);
        }

        return node;
    }


    public static void main(String[] args) {
        String str = "3,4,5,";
        String[] s = str.split(",");
        System.out.println(Arrays.toString(s));
    }
}
