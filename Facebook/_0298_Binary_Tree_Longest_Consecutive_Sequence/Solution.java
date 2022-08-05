package Facebook._0298_Binary_Tree_Longest_Consecutive_Sequence;
/*
298. Binary Tree Longest Consecutive Sequence
Given the root of a binary tree, return the length of the longest consecutive sequence path.
The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
The longest consecutive path needs to be from parent to child (cannot be the reverse).

Example 1:
    1
   / \
 -1   2
      /
     3
output=3 (1-2-3)

Example 2:
     3
   /  \
  2    4
 /
3
output=2
*/
/*
Solution
Approach 1: from top to down

Approach 2: from down to top
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

    public int longestConsecutive(TreeNode root) {
        int[] res = new int[1];
        res[0] = 1;
        dfs(root, res, null, 0);

        return res[0];
    }
    // from top to down
    void dfs(TreeNode root, int[] res, TreeNode parent, int len){
        if(root == null) return;
        if(parent != null && parent.val == root.val - 1){
            len++;
        }else{
            len = 1;
        }

        res[0] = Math.max(res[0], len);
        dfs(root.left, res, root, len);
        dfs(root.right, res, root, len);
    }

    public static void main(String[] args) {

    }
}
