# 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def height(self, root):
        if not root:
            return 0
        return max(self.height(root.left), self.height(root.right)) + 1

    def isBalanced(self, root):
        if not root:
            return True

        return self.isBalanced(root.left) and self.isBalanced(root.right) and (abs(self.height(root.left) - self.height(root.right)) < 2)


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
    print(solution.isBalanced(root))
