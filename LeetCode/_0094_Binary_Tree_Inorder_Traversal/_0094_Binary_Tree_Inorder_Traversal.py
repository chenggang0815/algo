from typing import List


class TreeNode:
    def __init__(self, val):
        self.val = val
        self.left = None
        self.right = None


class Solution:
    # time:o(n) 每个节点会被访问且只会被访问一次
    # space:o(n) 递归的栈深度
    def inorderTraversal1(self, root: TreeNode) -> List[int]:
        res = []

        def dfs(root):
            if root is None:
                return
            dfs(root.left)
            res.append(root.val)
            dfs(root.right)
        dfs(root)
        return res

    def inorderTraversal2(self, root: TreeNode) -> List[int]:
        """
    1. 从根结点开始，先往栈里面压入左结点，直到叶子结点:
    while cur:
        stack.append(cur)
        cur = cur.left

    再把叶子结点出栈，把叶子结点的右节点作为根结点再次遍历其左节点:
    cur = stack.pop()
    res.append(cur.val)
    cur = cur.right
        """
        stack = []
        res = []
        cur = root
        while stack or cur:
            while cur:
                stack.append(cur)
                cur = cur.left

            cur = stack.pop()
            res.append(cur.val)
            cur = cur.right

        return res


if __name__ == '__main__':
    root = TreeNode(1)
    node2 = TreeNode(2)
    node3 = TreeNode(3)
    node4 = TreeNode(4)
    node5 = TreeNode(5)
    root.left = node2
    root.right = node3
    node2.left = node4
    node2.right = node5
    s = Solution()
    print(s.inorderTraversal2(root))

