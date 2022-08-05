package Facebook._1382_Balance_a_Binary_Search_Tree;

import java.util.ArrayList;
import java.util.List;

/*
1382. Balance a Binary Search Tree
Given the root of a binary search tree, return a balanced binary search tree with the same node values.
If there is more than one answer, return any of them.
A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.

Example 1:
Input: root = [1,null,2,null,3,null,4,null,null]
    1                        2
     \                      / \
      2                    1   3
       \        =>              \
        3                        4
         \
          4
Output: [2,1,3,null,null,null,4]
Explanation: This is not the only correct answer, [3,1,4,null,2] is also correct.


Example 1:
*/
/*
Solution
Approach 1: time:O(n) space:O(n)
similar to question 108
Approach 2: time:O(n) space:O(1)
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

    public TreeNode balanceBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(list, root);

        root = buildTree(list, 0, list.size() - 1);

        return root;
    }

    TreeNode buildTree(List<Integer> list, int start, int end){
        if (start > end) return null;

        int mid = (start + end) / 2;

        TreeNode root = new TreeNode(list.get(mid));
        root.left = buildTree(list, start, mid - 1);
        root.right = buildTree(list, mid + 1, end);

        return root;
    }

    // left root right
    void dfs(List<Integer> list, TreeNode root){
        if(root == null) return;
        dfs(list, root.left);
        list.add(root.val);
        dfs(list, root.right);
    }
    public static void main(String[] args) {

    }
}
