from typing import List


class Solution:
    class TreeNode:
        def __init__(self, val):
            self.val = val
            self.left = None
            self.right = None

    def preorderTraversal(self, root: TreeNode) -> List[int]:
        res = []
        if root == None:
            return res
        stack = [root]

        while stack:
            node = stack.pop()
            res.append(node.val)
            if node.left:
                stack.append(node.left)
            if node.right:
                stack.append(node.right)