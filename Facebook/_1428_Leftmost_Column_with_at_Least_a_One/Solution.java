package Facebook._1428_Leftmost_Column_with_at_Least_a_One;
/*
1428. Leftmost Column with at Least a One
A row-sorted binary matrix means that all elements are 0 or 1 and each row of the matrix is sorted in non-decreasing order.
Given a row-sorted binary matrix binaryMatrix, return the index (0-indexed) of the leftmost column with a 1 in it. If such an index does not exist, return -1.
You can't access the Binary Matrix directly. You may only access the matrix using a BinaryMatrix interface:
    1. BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
    2. BinaryMatrix.dimensions() returns the dimensions of the matrix as a list of 2 elements [rows, cols], which means the matrix is rows x cols.
Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.

For custom testing purposes, the input will be the entire binary matrix mat. You will not have access to the binary matrix directly.
Example 1:
Input: mat = [[0,1,0],
              [0,0,1]]
Output: 1
*/
/*
Solution
Approach 1: binary search => time:O(n*log(m)) space:O(1)
1. for each row we can use binary search to find the first '1'
2. tip: for binary search
Approach 2: Start at Top Right, Move Only Left and Down => time:O(m+n) space:O(1)
*/
public class Solution {
//    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
//        List<Integer> list = binaryMatrix.dimensions();
//        int rows = list.get(0);
//        int cols = list.get(1);
//        int res = 100;
//        for(int row = 0; row < rows; row++){
//            res = Math.min(res, binarySearch(row, cols, binaryMatrix));
//        }
//
//        //System.out.print(binarySearch(0, cols, binaryMatrix));
//
//        return res == 100 ? -1 : res;
//    }
//
//
//    int binarySearch1(int row, int cols, BinaryMatrix binaryMatrix){
//        int left = 0;
//        int right = cols - 1;
//        //int res = Integer.MAX_VALUE;
//        while(left < right){
//            int mid = left + (right - left) / 2;
//            //System.out.print("mid:" + mid + "\n");
//            //System.out.print(binaryMatrix.get(row, mid) + "\n");
//            if(binaryMatrix.get(row, mid) >= 1){
//                right = mid;
//                //res = mid;
//            }else{
//                left = mid + 1;
//            }
//        }
//
//        //System.out.print("right:" + right + "\n");
//
//        return binaryMatrix.get(row, right) == 1 ? right : 100 ;
//    }
//
//    int binarySearch2(int row, int cols, BinaryMatrix binaryMatrix){
//        int left = 0;
//        int right = cols - 1;
//        int res = Integer.MAX_VALUE;
//        while(left <= right){
//            int mid = left + (right - left) / 2;
//            //System.out.print("mid:" + mid + "\n");
//            //System.out.print(binaryMatrix.get(row, mid) + "\n");
//            if(binaryMatrix.get(row, mid) >= 1){
//                right = mid - 1;
//                res = mid;
//            }else{
//                left = mid + 1;
//            }
//        }
//
//        return res;
//    }
    public static void main(String[] args) {

    }
}
