package LeetCode._1007_Minimum_Domino_Rotations_For_Equal_Row;
/*
谷歌2019年北美的校招OA题目

在一排多米诺骨牌中，A[i] 和 B[i] 分别代表第 i 个多米诺骨牌的上半部分和下半部分。（一个多米诺是两个从 1 到 6 的数字同列平铺形成的 —— 该平铺的每一半上都有一个数字。）

我们可以旋转第 i 张多米诺，使得 A[i] 和 B[i] 的值交换。

返回能使 A 中所有值或者 B 中所有值都相同的最小旋转次数。

如果无法做到，返回 -1.

示例 1：


输入：A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
输出：2
解释：
图一表示：在我们旋转之前， A 和 B 给出的多米诺牌。
如果我们旋转第二个和第四个多米诺骨牌，我们可以使上面一行中的每个值都等于 2，如图二所示。

示例 2：

输入：A = [3,5,1,2,3], B = [3,6,3,3,4]
输出：-1
解释：
在这种情况下，不可能旋转多米诺牌使一行的值相等。
 */

/*
思路：
1. 如果要是A中或者B中所有的值都相等，则A[0]或者B[0]一定满足出现在所有的（A[i], B[i]）中
2. 反之，若对于A[0] 和 B[0]在某个（A[i],B[i]）中都没出现，则return -1
3. 对于满足1的情况，则需要计算交换A或者交换B的次数（计算A/B中不等于x的数的个数），返回最小的值。
eg：
        int[] a = new int[]{1,2,1,1,1,2,2,2};
        int[] b = new int[]{2,1,2,2,2,2,2,2};
x=1,不满足条件
x=2,a中不等于2的值有4个，b中不等于2的值有1个

时间复杂度：o(n) space: o(1)
 */
public class Solution {
    static int check(int x, int[] A, int[] B){
        int cnt_a = 0;
        int cnt_b = 0;
        for(int i = 0; i < A.length; i++){
            if(x != A[i] && x != B[i]){
                return -1;
            }else if (x != A[i]){
                cnt_a++;
            }else if(x != B[i]){
                cnt_b++;
            }
        }

        return Math.min(cnt_a, cnt_b);
    }

    static int minDominoRotations(int[] A, int[] B){
        int res = check(A[0], A, B);

        if(res != -1 ){
            return res;
        }else{
            if (check(B[0], A, B) != -1){
                return check(B[0], A, B);
            }
        }

        return -1;
    }
    public static void main(String[] args) {
        int[] a = new int[]{1,2,1,1,1,2,2,2};
        int[] b = new int[]{2,1,2,2,2,2,2,2};
        //int[] A = new int[]{2,1,2,4,2,2}, B = {5,2,6,2,3,2};
        //System.out.println(minDominoRotations(a, b));
        System.out.println(check(2,a,b));

    }
}
