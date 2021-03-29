package com.LeetCode._0378_Kth_Smallest_Element_in_a_Sorted_Matrix;
import java.util.Arrays;
/*
378. Kth Smallest Element in a Sorted Matrix

Given an n x n matrix where each of the rows and columns are sorted in ascending order, return the kth smallest element in the matrix.
Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example 1:
Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
Output: 13
Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13

Example 2:
Input: matrix = [[-5]], k = 1
Output: -5

Constraints:
1. n == matrix.length
2. n == matrix[i].length
3. 1 <= n <= 300
4. -109 <= matrix[i][j] <= -109
5. All the rows and columns of matrix are guaranteed to be sorted in non-degreasing order.
*/
/*
思路1： 排序 time：o(n^2log(n)) space:o(n^2)

思路2：二分查找
1. 找出二维矩阵中最小的数left，最大的数right，那么第k小的数必定在left~right之间
2. mid=(left+right) / 2 => 在二维矩阵中寻找小于等于mid的元素个数count
    2.1 若count小于k，表明第k小的数在右半部分且不包含mid，即left=mid+1, => 保证了第 k 小的数在left ~ right 之间
    eg k=5 表示寻找第5小的数， [left，mid]之间的数为[0,1,2] count= 3 < 5 ，答案在mid和right之间，left=mid+1

    2.2 因为count为小于等于k的数的个数，若count大于k => 表明第k小的数在左半部分且可能包含mid，即right=mid => 保证了第 k 小的数在left ~ right 之间
        eg k=5 表示寻找第5小的数， [left，mid]之间的数为[0,1,2,2,3,4,5,5] count= 8 > 5 ，答案在left和mid之间，right=mid
3. 因为每次循环中都保证了第k小的数在left ~ right 之间，当left==right 时，第k小的数即被找出，等于right

注意：这里的left、mid、right是数值，不是索引位置

参考：
https://www.cnblogs.com/grandyang/p/5727892.html

ps: 不必保证循环时每一个left或right都在矩阵中，但可以保证当left==right时，right一定在矩阵中，因为第k小的那个数始终在区间[left,right]中，只需要缩小区间的范围即可，当范围缩小到只有一个元素时,第k小的元素就等于right了。
 */
public class Solution {
    static int kthSmallest1(int[][] matrix, int k) {
        int[] nums = new int[matrix.length * matrix[0].length];
        int index = 0;
        for (int i = 0; i < matrix.length; i++){
            for (int num: matrix[i]){
                nums[index++] = num;
            }
        }

        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));

        return nums[k - 1];
    }

    static int kthSmallest2(int[][] matrix, int k) {
        int row = matrix.length;
        int col = matrix[0].length;
        int left = matrix[0][0];
        int right = matrix[row - 1][col - 1];
        while (left < right){
            // 每次循环都保证第K小的数在start~end之间，当start==end，第k小的数就是start
            int mid = (left + right) / 2;
            // 找二维矩阵中<=mid的元素总个数
            int count = findNotBiggerThanMid(matrix, mid, row, col);
            if (count < k){
                // 第k小的数在右半部分，且不包含mid
                left = mid + 1;
            }else {
                // 第k小的数在左半部分，可能包含mid
                right = mid;
            }
        }

        return right;
    }

    static int findNotBiggerThanMid(int[][] matrix, int mid, int row, int col){
        // 以列为单位找，找到每一列最后一个<=mid的数即知道每一列有多少个数<=mid
        int i = row - 1;
        int j = 0;
        int count = 0;
        while (i >= 0 && j < col){
            if (matrix[i][j] <= mid){
                // 第j列有i+1个元素<=mid
                count += i + 1;
                j++;
            }else{
                // 第j列目前的数大于mid，需要继续在当前列往上找
                i--;
            }
        }

        return count;
    }

    public static void main(String[] args) {

        int[][] matrix = new int[][]{{1,5,9},{10,11,13},{12,13,15}};
        int k = 8;
        System.out.println(kthSmallest2(matrix, k));
}
}
