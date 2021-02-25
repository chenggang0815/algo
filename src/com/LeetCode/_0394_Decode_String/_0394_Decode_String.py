class Solution:
    def decodeString(self, s: str) -> str:
        int_stack = []
        str_stack = []
        cur = ""
        digits = 0
        for char in s:
            if char.isdigit():
                digits = 10 * digits + int(char)
            elif char == '[':  # "3[a2[c]]" 在当前字符=='['时，需要把前面的字符串和重复次数添加到栈里，并且重置字符串和重复次数
                int_stack.append(digits)
                str_stack.append(cur)
                cur = ""
                digits = 0
            elif char == ']':  # 在当前字符==']'时，需要从栈中取出已有的字符串，拼接多次当前的字符串；然后更新当前字符串
                temp = str_stack.pop()
                repeat_times = int_stack.pop()
                for i in range(repeat_times):
                    temp += cur
                cur = temp
            else:
                cur += char

        return cur


if __name__ == '__main__':
    s = Solution()
    print(s.decodeString("3[a]2[bc]"))
    print(s.decodeString("3[a2[c]]"))
