package Facebook._1650_Lowest_Common_Ancestor_of_a_Binary_Tree_III;

import java.util.HashSet;

/*
1650. Lowest Common Ancestor of a Binary Tree III
Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).
Each node will have a reference to its parent node. The definition for Node is below:
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}
According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)."

example:
            3
          /   \
         5     1
        / \   / \
       6  2  0  8
         / \
        7  4
p = 5
q = 4
return: 5
*/
/*
Solution
Approach 1: HashSet time:O(H) space:O(H)
1. because we have parent pointer, so we can store the path of one of the node until it reach the root node.
2. iterate another node, from it to root node, the first node exists in the step 1 is the lowest common ancestor

Approach 2: time:O(H) space:O(1)
1. traverse from p and q, if p or q reach the root node, we assign q = p and p = q
2. for example,
    // p: 5 3
    // q: 4 2 5 3
    // p-path: node1 = p = 5 => 3, reach root, node1 = q => 4  => 2 => 5

    // q-path: node2 = q = 4 => 2 => 5 => 3, reach root, node2 = p => 5
=> p-path = 5,3,4,2,5
=> q-path = 4,2,5,3,5
q equal p, when they both reach node 5
*/
public class Solution {
    class Node{
        Node left;
        Node right;
        Node parent;
        int val;
        Node(int val){
            this.val = val;
        }
    }

    public Node lowestCommonAncestor1(Node p, Node q) {
        HashSet<Node> set = new HashSet<>();
        while(p != null){
            set.add(p);
            p = p.parent;
        }

        while(q != null){
            if(set.contains(q)) return q;
            q = q.parent;
        }

        return null;
    }

    // p: 5 3
    // q: 4 2 5 3
    // p-path: 5 3 4 2 5
    // q-path: 4 2 5 3 5
    public Node lowestCommonAncestor2(Node p, Node q) {
        Node node1 = p;
        Node node2 = q;
        while(node1 != node2){
            if(node1.parent == null) node1 = q;
            else node1 = node1.parent;
            if(node2.parent == null) node2 = p;
            else node2 = node2.parent;
        }

        return node1;
    }
    public static void main(String[] args) {

    }
}
