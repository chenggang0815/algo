/*求1+2+3+...+n，
要求不能使用乘除法、for、while、if、else、switch、case
等关键字及条件判断语句 */


class Solution {
public:
    int Sum_Solution(int n) {
        int ans = n;
        ans && (ans += Sum_Solution(n - 1));
        return ans;
    }
};