package com.nowcoder._049;
/*
机器人的运动范围 : 
地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
但是不能进入行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
但是，它不能进入方格（35,38），因为3+5+3+8 = 19。
请问该机器人能够达到多少个格子？
 */

import java.util.LinkedList;
import java.util.Queue;

/*
思路:
1. dfs 深度优先搜索(递归)

递推公式 ： dp[i][j] = dp[i-1][j] + dp[i+1][j] + dp[i][j-1] + dp[i][j+1]
又因为 ：搜索方向可以缩减为向右和向下，而不必再向上和向左进行搜索， 有：
  dp[i][j] = dp[i+1][j] + dp[i][j+1]

2. bfs 广度优先搜索(队列)

初始化： 将机器人初始点 (0, 0) 加入队列 queue ；
迭代终止条件： queue 为空。代表已遍历完所有可达解。
迭代工作：
单元格出队： 将队首单元格的索引、数位和弹出，作为当前搜索单元格。
判断是否跳过： 若 :行列索引越界 或  数位和超出目标值threshold 或 当前元素已访问过时，执行 continue 。
标记当前单元格 ：将单元格索引 (i, j) 存入 flag中，代表此单元格已被访问过 。
单元格入队： 将当前元素的 下方、右方 单元格的 索引、数位和 加入 queue 。
返回值： res ，即可达解的数量。

 */
public class Solution {

    //dfs-递归解法 time: o(rows*col) space: o(rows*cols)
    static int movingCount(int threshold, int rows, int cols) {
        int[][] flag = new int[rows][cols];

        return helper(0, 0, threshold, rows, cols, flag);
    }

    static int helper(int i, int j, int threshold, int rows, int cols, int[][] flag){
        if (i < 0 || i >= rows || j < 0 || j >= cols || (intSum(i) + intSum(j)) > threshold || flag[i][j] == 1){
            return 0;
        }

        flag[i][j] = 1;
        return helper(i + 1, j, threshold, rows, cols, flag)
                //+ helper(i - 1, j, threshold, rows, cols, flag)
                //+ helper(i, j - 1, threshold, rows, cols, flag)
                + helper(i, j + 1, threshold, rows, cols, flag)
                + 1;
    }

    static int intSum(int n){
        int sum = 0;
        while (n != 0){
            sum = sum + n % 10;
            n = n / 10;
        }
        return sum;
    }

    // BFS , 广度优先遍历， 时间复杂度与dfs一样
    static int movingCount2(int threshold, int rows, int cols) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0,0,0,0});
        int res = 0;
        int[][] flag = new int[rows][cols];
        while (!queue.isEmpty()){
            int[] nums = queue.poll();
            int i = nums[0], j = nums[1], sum1 = nums[2], sum2 = nums[3];
            if (i >= rows || j >= cols || sum1 + sum2 > threshold || flag[i][j] == 1) continue;
            flag[i][j] = 1;
            res++;
            queue.offer(new int[]{i + 1, j, intSum(i + 1), intSum(j)});
            queue.offer(new int[]{i, j + 1, intSum(i), intSum(j + 1)});
        }

        return res;
    }


        public static void main(String[] args) {
        System.out.println(movingCount2(17,2, 3));
    }
}
