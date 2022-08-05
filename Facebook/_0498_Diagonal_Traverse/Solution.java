package Facebook._0498_Diagonal_Traverse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
498. Diagonal Traverse
Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.
Example 1:
Input: mat = [[1,2,3],
              [4,5,6],
              [7,8,9]]
         [1][2,4][7,5,3][6,8][9]
Output: [1,2,4,7,5,3,6,8,9]
Example 2:

Input: mat = [[1,2],[3,4]]
Output: [1,2,3,4]
Constraints:
1. m == mat.length
2. n == mat[i].length
3. 1 <= m, n <= 104
4. 1 <= m * n <= 104
5. -105 <= mat[i][j] <= 105
*/
/*
Solution:
Approach 1: HashMap
1. for each diagonal, we can find the col + row is equal, for example [0,2] [1,1] [2,0]
2. so we can iterate the matrix, and use a hashmap<col+row, list<Integer>> to store each diagonal
Input: mat = [[1,2,3],
              [4,5,6],
              [7,8,9]]
    [0,0]
    [0,1] [1,0]
    [0,2] [1,1] [2,0]
    [1,2] [2,1]
    [2,2]
*/
public class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        int[] res = new int[rows * cols];
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                int sum = i + j;
                if(!map.containsKey(sum)){
                    map.put(sum, new ArrayList<>());
                }
                map.get(sum).add(mat[i][j]);
            }
        }

        //System.out.print(map);
        int index = 0;
        for(int i = 0; i <= rows + cols - 2; i++){
            List<Integer> list = map.get(i);
            if(i % 2 == 0){
                for(int j = list.size() - 1; j >= 0; j--) res[index++] = list.get(j);
            }else{
                for(int j = 0; j < list.size(); j++) res[index++] = list.get(j);
            }
        }

        return res;
    }
    public static void main(String[] args) {

    }
}
