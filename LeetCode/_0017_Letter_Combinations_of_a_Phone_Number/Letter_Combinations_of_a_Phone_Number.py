class Solution:


    def letterCombinations(self, digits: str):
        res = []
        lettersmap = ["", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"]
        if len(digits) == 0:
            return res

        def backtrack(digits, s, index):
            if index == len(digits):
                res.append(s)
                return
            digit = digits[index]
            letters = lettersmap[int(digit)]
            for letter in letters:
                backtrack(digits, s + letter, index + 1)

        backtrack(digits, "", 0)

        return res


if __name__ == '__main__':
    s = Solution()
    res = s.letterCombinations("23")
    print(res)
