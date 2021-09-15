package 剑指offer._01_二维数组中的查找;

/*
二维数组中的查找

在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

思路1： 暴力遍历 time:o(m*n)

思路2 : time:o(m+n)
从左下角的值开始遍历，如果target > nums[i][j]， j++ ;如果target < nums[i][j], i--
 */
public class Solution {
    static boolean findTarget(int[][] nums, int target){
        if (nums.length == 0 || nums[0].length == 0) return false;

        int row = nums.length;
        int col = nums[0].length;
        int i = row - 1;
        int j = 0;
        while (i >= 0 && j < col){
            if (nums[i][j] == target){
                return true;
            }else if (nums[i][j] > target){
                i--;
            }else {
                j++;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{{1, 2, 3}, {2, 3, 4}, {5, 8, 10}};
        System.out.println(findTarget(nums, 8));

    }
}
