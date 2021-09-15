class TreeNode:
    def __init__(self, val):
        self.val = val
        self.left = None
        self.right = None


class Solution:
    def isValidBST1(self, root: TreeNode) -> bool:
        stack = []
        cur = root
        pre = float('-inf')
        while cur or stack:
            while cur:
                stack.append(cur)
                cur = cur.left
            cur = stack.pop()
            if pre >= cur.val:
                return False
            pre = cur.val
            cur = cur.right

        return True

