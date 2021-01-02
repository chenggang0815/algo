# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution(object):
    def maxDepth(self, root):
        if root is None:
            return 0
        return max(self.maxDepth(root.left), self.maxDepth(root.right)) + 1

    def minDepth(self, root):
        if root is None:
            return 0
        if root.left is None or root.right is None:
            return self.minDepth(root.left) + self.minDepth(root.right) + 1
        return min(self.minDepth(root.left), self.minDepth(root.right)) + 1

    def isBalanced(self, root):
        """
        :type root: TreeNode
        :rtype: bool
        """
        # a = self.maxDepth(root)
        # b = self.minDepth(root)

        # if a - b > 1:
        #    return False
        # else:
        #    return True

        return self.isBalanced(root.left) and self.isBalanced(root.right) and (
                self.maxDepth(root) - self.minDepth(root) < 2)


if __name__ == '__main__':
    root = TreeNode()
    node1 = TreeNode()
    node2 = TreeNode()
    node3 = TreeNode()
    node4 = TreeNode()
    root.left = node1
    root.right = node2
    node1.left = node3
    node1.right = node4

    solution = Solution()
    print solution.maxDepth(root)
