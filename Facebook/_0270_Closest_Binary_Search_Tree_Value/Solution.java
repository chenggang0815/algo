package Facebook._0270_Closest_Binary_Search_Tree_Value;
/*
270. Closest Binary Search Tree Value
Given the root of a binary search tree and a target value, return the value in the BST that is closest to the target.

Example 1:
            4
           / \
          2   5
         / \
        1  3
Input: root = [4,2,5,1,3], target = 3.714286
Output: 4

Example 2:
Input: root = [1], target = 4.428571
Output: 1
 */
/*
Solution
Approach 1: traverse the binary tree

Approach 2: binary search
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
    public int closestValue1(TreeNode root, double target) {

        double[] distance = new double[1];
        int[] res = new int[1];
        res[0] = root.val;
        distance[0] = Math.abs(root.val - target);
        dfs(root, target, res, distance);

        System.out.print(distance[0]);

        return res[0];
    }

    void dfs(TreeNode root, double target, int[] res, double[] distance){
        if(root == null) return;

        double d = root.val - target;
        //if(root.val > target && d > distance[0]) return;
        //(root.val < target && Math.abs(d) > distance[0]) return;
        if(distance[0] > Math.abs(d)){
            res[0] = root.val;
            distance[0] = Math.abs(d);
        }

        dfs(root.left, target, res, distance);
        dfs(root.right, target, res, distance);
    }

    public int closestValue2(TreeNode root, double target){
        int res = root.val;
        while(root != null){
            res = Math.abs(root.val - target) < Math.abs(res - target) ? root.val : res;
            root = target < root.val ? root.left : root.right;
        }

        return res;
    }

    public static void main(String[] args) {

    }
}
