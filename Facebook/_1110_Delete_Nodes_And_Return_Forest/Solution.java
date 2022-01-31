package Facebook._1110_Delete_Nodes_And_Return_Forest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*
1110. Delete Nodes And Return Forest
Given the root of a binary tree, each node in the tree has a distinct value.
After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
Return the roots of the trees in the remaining forest. You may return the result in any order.
Example 1:
                     1
                   /  \
                  2    3
                 / \  / \
                4  5 6  7
Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]
*/
/*
Solution
Approach 1: preorder traverse

Approach 2: postorder traverse
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
    // preorder
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        HashSet<Integer> set = new HashSet<>();
        for(int num : to_delete) set.add(num);
        List<TreeNode> res = new ArrayList<>();
        if(!set.contains(root.val)) res.add(root);
        dfs(root, set, res);

        return res;
    }

    void dfs(TreeNode root, HashSet<Integer> set, List<TreeNode> res){
        if(root == null) return;

        if(set.contains(root.val) && root.left != null && !set.contains(root.left.val)){
            res.add(root.left);
        }

        if(set.contains(root.val) && root.right != null && !set.contains(root.right.val)){
            res.add(root.right);
        }
        if(root.left != null){
            dfs(root.left, set, res);
            if(set.contains(root.left.val)) root.left = null;
        }

        if(root.right != null){
            dfs(root.right, set, res);
            if(set.contains(root.right.val)) root.right = null;
        }
    }
    public static void main(String[] args) {

    }
}
