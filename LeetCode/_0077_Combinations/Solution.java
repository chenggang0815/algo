package LeetCode._0077_Combinations;
import java.util.ArrayList;
import java.util.List;

/*
77. Combinations
Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].
You may return the answer in any order.
Example 1:
Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
Example 2:
Input: n = 1, k = 1
Output: [[1]]

Constraints:
1 <= n <= 20
1 <= k <= n
*/

/*
Solution:
BackTracking time: K*C_n_k
1. stop condition => temp.size()==2
2. iterate from left to right, no repeat value => for(int i = index; i <= n; i++) dfs(i+1)
*/
public class Solution {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backTracking(n, k, res, new ArrayList<>(), 1);

        return res;
    }

    void backTracking(int n, int k, List<List<Integer>> res, ArrayList<Integer> temp, int index){
        if(temp.size() == k){
            res.add(new ArrayList<>(temp));
        }

        for(int i = index; i <= n; i++){
            temp.add(i);
            backTracking(n, k, res, temp, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {

    }
}
