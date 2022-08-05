package LeetCode._0145_Binary_Tree_Postorder_Traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
145. Binary Tree Postorder Traversal
Given the root of a binary tree, return the postorder traversal of its nodes' values.
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
        3
       / \
      9  4
        / \
       5   7
    left -> right -> root
    9 -> 5 -> 7 -> 4 -> 3
    迭代过程：
    1. 3入栈，9入栈
    2. 9.left == null
    3. 9出栈
    4. 9.right == null => 9入res, root = null pre = 9
    5. 3出栈
    6. 3.right != null => 3再入栈, root = 3.right = 4
    7. 4入栈, 5入栈, 5出栈
    8. 5.right == null => 5入res, root = null pre = 5
    9. 4出栈
    10. 4.right != null => 4再入栈, root = 4.right = 7
    11. 7入栈, 7出栈
    12. 7.right == null => 7入res, root = null pre = 7
    13. 4出栈
    14. 4.right = pre => 4入res, root = null pre = 4
    15. 3出栈
    16. 3.right = pre => 3入res, root = null pre = 3
    return res
*/

    static  List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == pre){
                res.add(root.val);
                pre = root; // 右子树出栈的时候，每次出栈root不断上移，下一次循环时还会判断是否有右子树；如果不加prev就会陷入死循环~，加个prev做个判断，只有没有遍历过的右子树才能入栈
                root = null;
            }else {
                stack.push(root);
                root = root.right;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(7);
        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;
        System.out.println(postorderTraversal(root));

    }
}
