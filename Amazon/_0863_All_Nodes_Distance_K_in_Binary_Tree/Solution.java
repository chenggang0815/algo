package Amazon._0863_All_Nodes_Distance_K_in_Binary_Tree;

import javafx.util.Pair;

import java.util.*;

/*
863. All Nodes Distance K in Binary Tree

Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all nodes that have a distance k from the target node.
You can return the answer in any order.

Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
Output: [7,4,1]
Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.
*/

/*
建议写个python版本，真的简洁很多 https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/solution/

Solution:
Approach 1 => dfs
1. iterate the tree, for each node, use a hashmap to store its parent node
2. use dfs iterate the tree, from top to bottom and from bottom to up
3. use hashset or previous node to avoid repeated value

Approach 2 => bfs
1. iterate the tree, for each node, use a hashmap to store its parent node
2. use bfs iterate the tree, from top to bottom and from bottom to up
3. use hashset to avoid repeated value
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

    static List<Integer> findAllNodes1(TreeNode root, TreeNode target, int k){
        HashMap<TreeNode, TreeNode> parent = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        findParent(root, parent);
        HashSet<TreeNode> visited = new HashSet<>();
        //findKDistanceNode(target, null, parent, k, 0, res);
        findKDistanceNode2(target, visited, parent, k, 0, res);

        return res;
    }

    // dfs
    static void findKDistanceNode2(TreeNode node, HashSet<TreeNode> visited, HashMap<TreeNode, TreeNode> parent, int k, int distance, List<Integer> res){
        if (node == null) return;

        if (distance == k){
            res.add(node.val);
            return;
        }
        visited.add(node);
        if (!visited.contains(node.left)){
            findKDistanceNode2(node.left, visited, parent, k, distance + 1, res);
           // visited.add(node.left);
        }
        if (!visited.contains(node.right)){
            findKDistanceNode2(node.right, visited, parent, k, distance + 1, res);
           // visited.add(node.right);
        }

        if (!visited.contains(parent.get(node))){
            findKDistanceNode2(parent.get(node), visited, parent, k, distance + 1, res);
         //   visited.add(parent.get(node));
        }
    }
    // dfs
    static void findKDistanceNode(TreeNode node, TreeNode prevNode, HashMap<TreeNode, TreeNode> parent, int k, int distance, List<Integer> res){
        if (node == null) return;

        if (distance == k){
            res.add(node.val);
            return;
        }
        if (node.left != prevNode){
            findKDistanceNode(node.left, node, parent, k, distance + 1, res);
        }
        if (node.right != prevNode) {
            findKDistanceNode(node.right, node, parent, k, distance + 1, res);
        }
        if (parent.get(node) != prevNode) findKDistanceNode(parent.get(node), node, parent, k, distance + 1, res);
    }

    // bfs
    static List<Integer> findAllNodes2(TreeNode root, TreeNode target, int k){
        HashMap<TreeNode, TreeNode> parent = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        findParent(root, parent);
        HashSet<TreeNode> visited = new HashSet<>();

        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        Pair<TreeNode, Integer> pair = new Pair<>(target, 0);
        queue.add(pair);
        while (!queue.isEmpty()){
            Pair<TreeNode, Integer> node = queue.poll();
            TreeNode currentNode = node.getKey();
            int distance = node.getValue();
            if (distance == k){
                res.add(currentNode.val);
            }
            visited.add(currentNode);
            if (!visited.contains(currentNode.left) && currentNode.left != null){
                queue.add(new Pair<>(currentNode.left, distance + 1));
            }
            if (!visited.contains(currentNode.right) && currentNode.right != null){
                queue.add(new Pair<>(currentNode.right, distance + 1));
            }
            if (!visited.contains(parent.get(currentNode)) && parent.get(currentNode) != null){
                queue.add(new Pair<>(parent.get(currentNode), distance + 1));
            }
        }

        return res;
    }

    static void findParent(TreeNode root, HashMap<TreeNode, TreeNode> parent){
        if (root == null) return;
        if (root.left != null){
            parent.put(root.left, root);
            findParent(root.left, parent);
        }
        if (root.right != null){
            parent.put(root.right, root);
            findParent(root.right, parent);
        }
    }

    public static void main(String[] args) {
    /*
        1
       / \
      2   3
     /\
    4 5
   /   \
  6    7
 */
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(7);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node3.left = node5;
        node3.right = node6;
        System.out.println(findAllNodes2(root, node1, 2));
    }
}
