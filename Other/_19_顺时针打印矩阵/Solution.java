package Other._19_顺时针打印矩阵;

import java.util.ArrayList;

/*
顺时针打印矩阵

输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
例如，如果输入如下4 X 4矩阵：
1 2 3 4
5 6 7 8
9 10 11 12
13 14 15 16 则依次打印出数字 1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 */

/*
定义上下左右四个边界值： 其中 left = 0； right = matrix[0].length -1 ; top = 0; donw = martix.length - 1
0. 满足临界条件： left < right ; top < down
1. 顺时针遍历指的是依次遍历四条边，方向是从（上边）left->right,（右边） top -> down , （下边） right -> left ,（左边） down -> top
2. 每次遍历完一条边，需要更新边的边界值，并且判断是否满足临界条件

参考：
1. https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/solution/mian-shi-ti-29-shun-shi-zhen-da-yin-ju-zhen-she-di/
2. leetcode 54题
*/
public class Solution {
    static public ArrayList<Integer> printMatrix(int [][] matrix) {
        if (matrix.length == 0) return new ArrayList<>();

        int left = 0, right = matrix[0].length - 1, top = 0, down = matrix.length - 1;
        ArrayList<Integer> array = new ArrayList<>();
        while (true){
            for (int i = left; i <= right; i++){   //左到右
                array.add(matrix[top][i]);
            }
            top += 1; //上边加一
            if (top > down) break;

            for (int i = top; i <= down; i++){  //上到下
                array.add(matrix[i][right]);
            }
            right -= 1;  //右边减一
            if (left > right) break;

            for (int i = right; i >= left; i--){  //右到左
                array.add(matrix[down][i]);
            }
            down -= 1; //下边减一
            if (top > down) break;

            for (int i = down; i >= top; i--){ //下到上
                array.add(matrix[i][left]);
            }
            left += 1; //左边加一
            if (left > right) break;

        }

        return array;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3},{4,5,6}};
        System.out.println(printMatrix(matrix));

    }
}
