package LeetCode._2476_Closest_Nodes_Queries_in_a_Binary_Search_Tree;

import java.util.ArrayList;
import java.util.List;

/*
2476. Closest Nodes Queries in a Binary Search Tree

You are given the root of a binary search tree and an array queries of size n consisting of positive integers.
Find a 2D array answer of size n where answer[i] = [mini, maxi]:
    1. min_i is the largest value in the tree that is smaller than or equal to queries[i]. If a such value does not exist, add -1 instead.
    2. max_i is the smallest value in the tree that is greater than or equal to queries[i]. If a such value does not exist, add -1 instead.
Return the array answer.

Example 1:
        6
       / \
      2   13
    / \   / \
   1  4  9  15
            /
           14
Input: root = [6,2,13,1,4,9,15,null,null,null,null,null,null,14], queries = [2,5,16]
Output: [[2,2],[4,6],[15,-1]]
* */
/*
Solution: inorder-dfs + binary search
1. user inorder traversal to convert the binary search tree to a sorted array
2. use binary search to find the first one >= target and the last one <= target
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

    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<Integer> nums = new ArrayList<>();
        dfs(root, nums);

        List<List<Integer>> res = new ArrayList<>();
        for(int target: queries){
            List<Integer> temp = new ArrayList<>();
            temp.add(findLast(nums, target));
            temp.add(findFirst(nums, target));

            res.add(new ArrayList<>(temp));
        }

        return res;
    }

    int findFirst(List<Integer> nums, int target){
        int res = -1;
        int left = 0;
        int right = nums.size() - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums.get(mid) >= target){
                res = mid;
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }

        return res == -1 ? -1 : nums.get(res);
    }
    // 1 2 4 6 9 13 14 15
    // 5 => [4, 6]
    // find the last one <= 5 => 4

    int findLast(List<Integer> nums, int target){
        int res = -1;
        int left = 0;
        int right = nums.size() - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums.get(mid) > target){
                right = mid - 1;
            }else{
                res = mid;
                left = mid + 1;
            }
        }

        return res == -1 ? -1 : nums.get(res);
    }

    void dfs(TreeNode root, List<Integer> nums){
        if(root == null) return;

        // inorder => left - root - right
        dfs(root.left, nums);
        nums.add(root.val);
        dfs(root.right, nums);
    }

    public static void main(String[] args) {

    }
}
