package com.LeetCode._0118_Pascals_Triangle;

import java.util.ArrayList;
import java.util.List;
/*
118. Pascal's Triangle
Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
 */
/*
思路：
1. 每一行首尾都是1
2. 除开首尾，dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
 */
public class Solution {
    static List<List<Integer>> generate(int numRows){
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < numRows; i++){
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < i + 1; j++){
                if (j == 0 || j == i){
                    temp.add(1);
                }else{
                    temp.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
                }
            }
            res.add(temp);
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(generate(5));
    }
}
