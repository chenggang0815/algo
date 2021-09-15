class Solution:

    def generateParenthesis(self, n: int):
        res = []

        def dfs(track, left, right):
            if len(track) == n * 2:
                res.append(track)
                return

            if left < n:
                # path = path + "("
                dfs(track + '(', left + 1, right)
            if left > right:
                # path = path + ")"
                dfs(track + ')', left, right + 1)

        dfs("", 0, 0)

        return res


if __name__ == '__main__':
    s = Solution()
    print(s.generateParenthesis(3))
