package LeetCode._0454_4Sum_II;
import java.util.HashMap;
/*
454. 4Sum II

Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.
To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.

Example:
Input:
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]

Output:
2

Explanation:
The two tuples are:
1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
*/

/*
思路：分组 + hashmap  time:o(n^2) space:o(n^2) 在最坏的情况下，A[i]+B[j]的值均不相同，因此值的个数为n^2
1. 将四个数组分成两部分，A和B为一组，C和D为另外一组; ans = 0;
2. 对于A和B，使用两层for循环对它们进行遍历，得到所有A[i]+B[j]的值并存入hashmap中 => key:A[i]+B[j] value:A[i]+B[j]出现的次数
3. 对于C和D，同样使用两层for循环对它们进行遍历，当遍历到C[i]+D[j]时 => 如果-(C[i]+D[j])出现在hashmap中，那么将-(C[k]+D[l])对应的值累加进答案中 => res += map.get(-(C[i]+D[j]))
4. return ans;

*/
public class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int u: A){
            for (int v: B){
                map.put(u + v, map.getOrDefault(u + v, 0) + 1);
            }
        }

        int ans = 0;
        for (int u: C){
            for (int v: D){
                if (map.containsKey(-(u + v))){
                    ans += map.get(-(u + v));
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {

    }
}
