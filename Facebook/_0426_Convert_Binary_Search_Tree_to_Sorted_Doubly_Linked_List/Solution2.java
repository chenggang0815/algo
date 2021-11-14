package Facebook._0426_Convert_Binary_Search_Tree_to_Sorted_Doubly_Linked_List;
/*
similar question to 98
*/
public class Solution2 {
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };

    // use a global variable to track the previous node
    Node prev = null;
    public Node treeToDoublyList(Node root) {
        if(root == null) return null;

        Node dummy = new Node(0, null, null);
        prev = dummy;

        dfs(root);
        // connect head and tail
        // head = dummy.right
        // tail = prev
        dummy.right.left = prev;
        prev.right = dummy.right;

        return dummy.right;
    }

    void dfs(Node root){
        if(root == null) return;
        dfs(root.left);
        // connect current node and previous node
        // and update prev
        root.left = prev;
        prev.right = root;
        prev = root;

        dfs(root.right);
    }
}
