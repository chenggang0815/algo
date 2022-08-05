class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def maxDepth(self, root):
        if root is None:
            return 0
        return max(self.maxDepth(root.left), self.maxDepth(root.right)) + 1

    def isBalanced(self, root):
        if root is None:
            return True

        return self.isBalanced(root.left) and self.isBalanced(root.right) and (abs(self.maxDepth(root.left) - self.maxDepth(root.right)) < 2)


if __name__ == '__main__':
    root = TreeNode(0)
    node1 = TreeNode(0)
    node2 = TreeNode(0)
    node3 = TreeNode(0)
    node4 = TreeNode(0)
    root.left = node1
    root.right = node2
    node2.left = node3
    node2.right = node4
    Solution = Solution()
    print(Solution.isBalanced(root))
