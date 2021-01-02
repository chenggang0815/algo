"""111. Minimum Depth of Binary Tree
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.
"""


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def minDepth(self, root: TreeNode):
        if root is None:
            return 0
        if root.left is None and root.right is None:
            return 1
        if root.left is None or root.right is None:
            return self.minDepth(root.left) + self.minDepth(root.right) + 1
        return min(self.minDepth(root.left), self.minDepth(root.right)) + 1


if __name__ == '__main__':
    root = TreeNode()
    node1 = TreeNode()
    node2 = TreeNode()
    node3 = TreeNode()
    node4 = TreeNode()
    # root.left = node1
    root.right = node2
    # node1.left = node3
    node2.right = node4

    solution = Solution()
    print(solution.minDepth(root))
