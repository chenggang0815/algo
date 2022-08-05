package Facebook._0536_Construct_Binary_Tree_from_String;

import java.util.Stack;

/*
536. Construct Binary Tree from String
You need to construct a binary tree from a string consisting of parenthesis and integers.
The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.
You always start to construct the left child node of the parent first if it exists.
Example 1:
             4
           /  \
          2    6
         / \   /
        3  1  5
Input: s = "4(2(3)(1))(6(5))"
Output: [4,2,6,3,1,5]
*/
/*
Solution
Approach 1: stack
1. when the current char is digit, we initialize a new tree node
    1.1 if the stack is empty, the tree node is the root node
        else, we make this node is a child node of the last node in the stack (first left child and right child)
2. if the current char is ')', we pop the peek node of stack
3. corner case:
    3.1 '-4'
    3.2 '-400'
for example:
     4 ( 2 ( 3 ) ( 1 ) ) ( 6 ( 5 ) )
    /*
          4
        /   \
       2     6
      / \    /
     3   1  5

     4 ( => stack=[4]
     2 ( => [4,2] 4.left=2
     3 ( => [4,2,3] 2.left=3
     ) => =>stack.pop() => stack=[4,2]
     1 ( => [4,2,1] 2.right=1
     ) [4,2]
     ) [4]
Approach 2: recursion
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

    public TreeNode str2tree(String s) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = null;
        int i = 0;
        while(i < s.length()){
            if(s.charAt(i) == '-' || Character.isDigit(s.charAt(i))){
                int j = i + 1;
                while(j < s.length() && Character.isDigit(s.charAt(j))){
                    j++;
                }
                int val = Integer.parseInt(s.substring(i, j));
                i = j;
                TreeNode node = new TreeNode(val);
                if(stack.isEmpty()){
                    root = node;
                }else{
                    if(stack.peek().left == null) stack.peek().left = node;
                    else stack.peek().right = node;
                }
                stack.push(node);
            }else{
                if(s.charAt(i) == ')')stack.pop();
                i++;
            }
        }

        return root;
    }
    public static void main(String[] args) {

    }
}
