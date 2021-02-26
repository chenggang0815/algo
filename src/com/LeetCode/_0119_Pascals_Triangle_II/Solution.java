package com.LeetCode._0119_Pascals_Triangle_II;
/*
119. Pascal's Triangle II

Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.

Example 1:
Input: rowIndex = 3
Output: [1,3,3,1]

Example 2:
Input: rowIndex = 0
Output: [1]

Example 3:
Input: rowIndex = 1
Output: [1,1]
*/

import java.util.ArrayList;
import java.util.List;

/*
    [[1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]]

 dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
 */

public class Solution {

    // time: o(rowIndex^2) space:o(rowIndex*(rowIndex+1)/2)
    static List<Integer> getRow1(int rowIndex) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++){
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < i + 1; j++){ //第i行，有i+1个数字
                if (j == 0 || i == j){
                    temp.add(1);
                }else {
                    temp.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
                }
            }

            res.add(temp);
        }

        return res.get(rowIndex);
    }
/*
    只存两行，除了二维数组，也可以单独用两个list
    static List<Integer> getRow2(int rowIndex) {
        int[][] dp = new int[2][rowIndex + 1];

        for (int i = 0; i <= rowIndex; i++){
            for (int j = 0; j < i + 1; j++){ //第i行，有i+1个数字
                if (j == 0 || i == j){
                    dp[1][j] = 1;
                }else {
                    dp[1][j] = dp[0][j - 1] + dp[0][j];
                }
            }
            dp[0] = dp[1];
        }

        return dp[1];
    }
 */
   // time: o(rowIndex^2) space:o(rowIndex+1)
    static List<Integer> getRow2(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++){
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < i + 1; j++){ // 第i行，有i+1个数字
                if (j == 0 || i == j){
                    temp.add(1);
                }else {
                    temp.add(res.get(j - 1) + res.get(j));
                }
            }
            res = temp;
        }

        return res;
    }

    // time: o(rowIndex) space:o(1)
    /*
    能否只用一个数组呢？
    当前行第i项的计算只与上一行第i−1项及第i项有关 => 因此可以倒着计算当前行，这样计算到第i项时，第i−1项仍然是上一行的值
     */
    static List<Integer> getRow3(int rowIndex) {
        List<Integer> row = new ArrayList<Integer>();
        row.add(1);
        for (int i = 1; i <= rowIndex; ++i) {
            row.add(0);
            for (int j = i; j > 0; --j) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
        }
        return row;
    }

        public static void main(String[] args) {
        System.out.println(getRow3(3));
    }
}
