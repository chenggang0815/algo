package com.剑指offer._050;
/*
请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子


思路： DFS + 剪枝
DFS： 可以理解为暴力法遍历矩阵中所有字符串可能性。DFS通过递归，先朝一个方向搜到底，再回溯至上个节点，沿另一个方向搜索，以此类推。
剪枝： 在搜索中，遇到这条路不可能和目标字符串匹配成功的情况（例如：此矩阵元素和目标字符不同、此元素已被访问），则应立即返回。

1. 递归参数： 当前元素在矩阵 matrix 中的行列索引 i 和 j ，当前目标字符在str中的索引 k 。
2. 终止条件：
   a. 返回 false： ① 行或列索引越界 或 ② 当前矩阵元素与目标字符不同 或 ③ 当前矩阵元素已访问过
   b. 返回 true： 字符串 str 已全部匹配，即 k = len(str) - 1 。

3-1 . 递推工作(空间复杂度：o(k*m*n)):
   a. 标记当前矩阵位置 ：flag[i][j] == 1 ，代表此元素已访问过，防止之后搜索时重复访问。
   b. 搜索下一单元格： 朝当前元素的 上、下、左、右 四个方向开启下层递归，使用 或 连接 （代表只需一条可行路径） ，并记录结果至 res 。
   c. 还原当前位置元素， flag[i][j] == 0 ， 代表 i，j变化后，开启新一轮的搜索。
   d. 回溯返回值： 返回 res ，代表是否搜索到目标字符串。

3-2 . 递推工作(空间复杂度:o(k))：
   a. 标记当前矩阵元素： 将 matrix[i][j] 值暂存于变量 tmp ，并修改为字符 '/' ，代表此元素已访问过，防止之后搜索时重复访问。
   b. 搜索下一单元格： 朝当前元素的 上、下、左、右 四个方向开启下层递归，使用 或 连接 （代表只需一条可行路径） ，并记录结果至 res 。
   c. 还原当前矩阵元素： 将 tmp 暂存值还原至 board[i][j] 元素。
   d. 回溯返回值： 返回 res ，代表是否搜索到目标字符串。

https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/solution/mian-shi-ti-12-ju-zhen-zhong-de-lu-jing-shen-du-yo/

 */
public class Solution {

    // time: o(k^3*rows*cols) 对于每个str长度为k，遍历三个方向 space: o(rows*cols)
    static boolean hasPath(char[] matrix, int rows, int cols, char[] str){
        int k = 0;
        int[][] flag = new int[rows][cols];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j< cols; j++){
                if (helper(matrix, rows, cols, str, i, j, k, flag)){
                    return true;
                }
            }
        }

        return false;
    }

    static boolean helper(char[] matrix, int rows, int cols, char[] str, int i, int j, int k, int[][] flag){
        int index = i * cols + j;
        if (i < 0 || i >= rows || j < 0 || j >= cols || matrix[index] != str[k] || flag[i][j] == 1){
            return false;
        }
        if (k == str.length-1) return true; // 已经遍历完所有str的字符，返回true
        flag[i][j] = 1; //将当前位置标记，防止走回头路
        boolean res = helper(matrix, rows, cols, str, i - 1, j, k + 1, flag)
                || helper(matrix, rows, cols, str, i + 1, j, k + 1, flag)
                || helper(matrix, rows, cols, str, i, j - 1, k + 1, flag)
                || helper(matrix, rows, cols, str, i, j + 1, k + 1, flag);
        flag[i][j] = 0; // i,j变化后，开启新一轮的搜索, 所以将状态恢复

        return res;
    }


    public static void main(String[] args) {

    }
}
