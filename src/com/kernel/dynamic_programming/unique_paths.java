package com.kernel.dynamic_programming;
/*
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

问总共有多少条不同的路径？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/unique-paths
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class unique_paths {

    //time:o(m*n)
    //space:o(m*n)

    static int uniquePaths(int m, int n) {
        if (m<=0||n<=0) return 1;
        int[][] nums = new int[m][n];

        //初始化
        for (int i=0;i<m;i++){
            nums[i][0] = 1;
        }

        for (int j=0;j<n;j++){
            nums[0][j] = 1;
        }

        //递推
        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                nums[i][j] = nums[i-1][j] + nums[i][j-1];
            }
        }

        return nums[m-1][n-1];
    }

    //time:o(m*n)
    //space:o(2n)
    static int uniquePaths2(int m, int n) {
        if (m<=0||n<=0) return 1;
        int[] cur = new int[n];
        int[] pre = new int[n];
        for (int i=0; i<n;i++){
            cur[i]=1;
            pre[i]=1;
        }

        for (int i=1; i<m;i++){
            for (int j=1; j<n;j++){
                cur[j] = cur[j-1] + pre[j];
                pre[j] = cur[j];
            }
        }

        return cur[n-1];
    }

    //time: o(n*m)
    //space:o(n)
    static int uniquePaths3(int m, int n) {
        if (m<=0||n<=0) return 1;

        int[] nums = new int[n];
        for (int i=0;i<n;i++){
            nums[i] =1;
        }

        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                nums[j] = nums[j-1] + nums[j];
            }
        }

        return nums[n-1];

    }

        public static void main(String[] args) {

            System.out.println(uniquePaths3(7,3));

    }
}
