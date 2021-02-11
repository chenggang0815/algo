from collections import Counter
class Solution:
    def minWindow1(self, s: str, t: str) -> str:
        if len(t) > len(s):
            return ""
        minLength = len(s)
        inedx = (0, 0)
        c = Counter(t)
        for i in range(len(s)):
            for j in range(i, len(s)):
                c1 = Counter(s[i : j + 1])
                count = 0
                for char in c:
                    if c[char] <= c1[char]:
                        count += 1
                    else:
                        break
                if count == len(c) and len(t) <= (j + 1 - i) <= minLength:
                    minLength = j + 1 - i
                    inedx = (i, j + 1)
                    print("===")
                    print(inedx)

        #print(inedx)
        return s[inedx[0]:inedx[1]]


    def minWindow2(self, s: str, t: str) -> str:
        left = 0
        right = 0
        c = Counter(t)
        inedx = (left, right)
        minLength = len(s)
        while right < len(s):
            c1 = Counter(s[left: right + 1])
            count = 0
            for char in c:
                if c[char] <= c1[char]:
                    count += 1
                else:
                    break
            if count == len(c):
                if len(t) <= (right + 1 - left) <= minLength:
                    minLength = right + 1 - left
                    inedx = (left, right + 1)
                left += 1
            else:
                right += 1


        return s[inedx[0]:inedx[1]]


    def minWindow3(self, s: str, t: str) -> str:
        left = 0
        right = 0
        c = Counter(t)
        inedx = (left, right)
        minLength = len(s)
        while right < len(s):
            c1 = Counter(s[left: right + 1])
            count = 0
            for char in c:
                if c[char] <= c1[char]:
                    count += 1
                    if count == len(c):
                        if len(t) <= (right + 1 - left) <= minLength:
                            minLength = right + 1 - left
                            inedx = (left, right + 1)
                            print(inedx)
                            while True:
                                print("===")
                                left += 1
                                if s[left] in t:
                                    break
                else:
                    right += 1
                    break

        return s[inedx[0]:inedx[1]]


if __name__ == '__main__':
    s = Solution()
    print(s.minWindow3("ADOBECODEBANC", "ABC"))
