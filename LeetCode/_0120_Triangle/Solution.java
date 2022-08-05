package LeetCode._0120_Triangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
120. Triangle

Given a triangle array, return the minimum path sum from top to bottom.
For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.

Example 1:
Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
Output: 11
Explanation: The triangle looks like:
   2
  3 4
 6 5 7
4 1 8 3
The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).

Example 2:
Input: triangle = [[-10]]
Output: -10
*/
/*
Solution
Approach 1: Dynamic Programing
   2             2
  3 4     =>    5 6 => 5=3+2 6=3+2
 6 5 7        11 10 13 => 10 = 5 + min(5,6)
4 1 8 3      15 11 18 16 => min(15, 11, 18, 16) = 11

for number at nums[i][j] => if j = 0 => sum = num + nums[i - 1][0]
                         => if j = col => sum = num + nums[i - 1][col]
                         => else sum = num + min(nums[i - 1][j], nums[i - 1][j + 1])
*/
public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        for(int i = 1; i < triangle.size(); i++){
            for(int j = 0; j < triangle.get(i).size(); j++){
                int temp = Integer.MAX_VALUE;
                if(j == 0){
                    temp = triangle.get(i - 1).get(0);
                }else if(j == triangle.get(i).size() - 1){
                    temp = triangle.get(i - 1).get(j - 1);
                }
                else{
                    temp = Math.min(triangle.get(i - 1).get(j - 1), triangle.get(i - 1).get(j));
                }
                triangle.get(i).set(j, triangle.get(i).get(j) + temp);
            }
        }

        // int minTotal = Integer.MAX_VALUE;
        // for(int i = 0; i < triangle.get(triangle.size() - 1).size(); i ++){
        //     minTotal = Math.min(minTotal, triangle.get(triangle.size() - 1).get(i));
        // }

        return Collections.min(triangle.get(triangle.size() - 1));
    }

    public static void main(String[] args) {
        List<Integer> res = new ArrayList<>();
        res.add(5);
        System.out.println(res);
        res.set(0, res.get(0) + 2);
        System.out.println(res);
    }
}
