package LeetCode._0099_Recover_Binary_Search_Tree;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
99. Recover Binary Search Tree
You are given the root of a binary search tree (BST),
where the values of exactly two nodes of the tree were swapped by mistake.
Recover the tree without changing its structure.

Example 1:
Input: root = [1,3,null,null,2]
Output: [3,1,null,null,2]
Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.

Example 2:
Input: root = [3,1,4,null,null,2]
Output: [2,1,4,null,null,3]
Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.
 */

/*
Solution
Approach 1
1. inorder traversal bst is a sorted array in ascending order
2. use a list to store all the nodes
3. find the two node we need to swap
4. swap the val of two node

Approach 2
1. use 3 variable to track the two node
2. TreeNode firstNode = null;
   TreeNode secondNode = null;
   TreeNode pre = null;
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

    public void recoverTree1(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        dfs(root, list);
        // 1 3 2 4
        // 6 2 3 1
        TreeNode firstNode = null;
        TreeNode secondNode = null;
        for(int i = 1; i < list.size(); i++){
            if(firstNode == null && list.get(i - 1).val > list.get(i).val) firstNode = list.get(i - 1);
            if(list.get(i - 1).val > list.get(i).val) secondNode = list.get(i);
            //if(firstNode != null && list.get(i - 1).val > list.get(i).val) secondNode = list.get(i);
        }

        int temp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = temp;
    }

    void dfs(TreeNode root, List<TreeNode> list){
        if(root == null) return;

        dfs(root.left, list);
        list.add(root);
        dfs(root.right, list);
    }

    TreeNode firstNode = null;
    TreeNode secondNode = null;
    TreeNode pre = null;
    public void recoverTree2(TreeNode root) {
        dfs(root);

        int temp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = temp;
    }

    void dfs(TreeNode cur){
        if(cur == null) return;

        dfs(cur.left);
        if(firstNode == null && pre != null && pre.val > cur.val) firstNode = pre;
        if(firstNode != null && pre.val > cur.val) secondNode = cur;
        pre = cur;

        dfs(cur.right);
    }



    public static void main(String[] args) {
        // 1 3 2 4
        // 6  2    3 4 1
        //   root
        // first = 6
        // second=2
        // 4 > 1
        // second=1
        List<Integer> list = Arrays.asList(1,3,2,4);
    }
}
