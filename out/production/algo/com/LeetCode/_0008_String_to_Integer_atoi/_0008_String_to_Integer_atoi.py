class Solution:

    # 10次才通过
    def myAtoi(self, s: str) -> int:
        string = s.strip()
        if len(string) == 0:
            return 0

        res = 0
        index = 0
        neg = False

        l = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"]

        if s[0] not in l and s[0] not in ["-", "+"]:
            return res
        else:
            if s[0] in ["-", "+"]:
                index += 1
                if s[0] == "-":
                    neg = True

        ll = []
        print("index:", index)
        print("s:", s)
        for i in range(index, len(s)):
            if s[i] in l:
                ll.append(s[i])
            else:
                break

        print(ll)

        if len(ll) == 0:
            return res
        else:
            res = int("".join(ll))

        res = -int(res) if neg else int(res)

        if res > 2 ** 31 - 1:
            res = 2 ** 31 - 1
        if res < -2 ** 31:
            res = -2 ** 31

        return res

    def myAtoi1(self, s: str) -> int:
        """
        1. 先去掉首尾的空格，如果剩下的字符串长度为0，直接返回0

        2. 再判断首位，
            2.1 如果不是数字或正负号 => return res
            2.2 如果是正负号 => index += 1 指针右移一位
                2.2.1 如果是负号 => neg = true

        3. 从指针的位置开始遍历字符串
            3.1 如果当前位置的字符是数字，加入结果
            3.2 如果当前位置的字符不是数字，结束遍历

        4. 结果集 => 为空 => return res
            结果集 => 不为空 => res = int(''.join(res_list))

        5. 判断是否加上负号

        6. 判断是否超出界限

        """
        string = s.strip()
        if len(string) == 0:
            return 0

        res = 0
        index = 0
        neg = False

        digits = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"]
        signs = ["-", "+"]

        if string[0] not in digits and string[0] not in signs:
            return res
        else:
            if string[0] in signs:
                index += 1
                if string[0] == "-":
                    neg = True

        res_list = []

        for i in range(index, len(string)):
            if string[i] in digits:
                res_list.append(string[i])
            else:
                break

        if len(res_list) == 0:
            return res
        else:
            res = int("".join(res_list))

        res = -int(res) if neg else int(res)

        if res > 2 ** 31 - 1:
            res = 2 ** 31 - 1
        if res < -2 ** 31:
            res = -2 ** 31

        return res

    def myAtoi2(self, s: str) -> int:
        string = s.strip()
        if len(string) == 0:
            return 0

        res = 0
        index = 0
        neg = False

        signs = ["-", "+"]

        if not string[0].isdigit() and string[0] not in signs:
            return res
        else:
            if string[0] in signs:
                index += 1
                if string[0] == "-":
                    neg = True

        res_list = []

        for i in range(index, len(string)):
            if string[i].isdigit():
                res_list.append(string[i])
            else:
                break

        if len(res_list) == 0:
            return res
        else:
            res = int("".join(res_list))

        if neg:
            res = -2 ** 31 if -res < -2 ** 31 else -res
        else:
            res = 2 ** 31 - 1 if res > 2 ** 31 - 1 else res

        return res


if __name__ == '__main__':
    s = Solution()
    print(s.myAtoi2("  -42"))
