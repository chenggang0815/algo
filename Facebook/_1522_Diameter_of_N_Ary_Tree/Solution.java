package Facebook._1522_Diameter_of_N_Ary_Tree;

import java.util.ArrayList;
import java.util.List;

/*
1522. Diameter of N-Ary Tree
Given a root of an N-ary tree, you need to compute the length of the diameter of the tree.
The diameter of an N-ary tree is the length of the longest path between any two nodes in the tree. This path may or may not pass through the root.
(Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value.)

Example 1:
                     1
                   / | \
                  3  2  4
                 / \
                 5 6
Input: root = [1,null,3,2,4,null,5,6]
Output: 3
Explanation: Diameter is shown in red color.

Constraints:
The depth of the n-ary tree is less than or equal to 1000.
The total number of nodes is between [1, 104].
*/
/*
Solution
Approach 1: recursion
1. for each node, we calculate the longest path (s1) and the second longest path (s2) start from the current node.
2. the entire longest path is the maximum s1 + s2

similar question:
#543. Diameter of Binary Tree
#1522. Diameter of N-Ary Tree
#1245. Tree Diameter
#124. Binary Tree Maximum Path Sum
*/
public class Solution {
    class Node {
        public int val;
        public List<Node> children;


        public Node() {
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val,ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    public int diameter(Node root) {
        int[] res = new int[1];
        dfs(root, res);

        return res[0];
    }

    int dfs(Node root, int[] res){
        if(root == null) return 0;
        int longest = 0;
        int secondLongest = 0;
        for(Node node : root.children){
            int path = dfs(node, res);
            if(path > longest){
                secondLongest = longest;
                longest = path;
            }else if(path > secondLongest){
                secondLongest = path;
            }
        }
        res[0] = Math.max(res[0], longest + secondLongest);

        return longest + 1;

    }

    public static void main(String[] args) {

    }
}
