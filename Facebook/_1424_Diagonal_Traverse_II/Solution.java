package Facebook._1424_Diagonal_Traverse_II;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
1424. Diagonal Traverse II
Given a 2D integer array nums, return all elements of nums in diagonal order as shown in the below images.

Example 1:
Input: nums = [[1,2,3],
               [4,5,6],
               [7,8,9]]
Output: [1,4,2,7,5,3,8,6,9]

Example 2:
Input: nums = [[1,2,3,4,5],
               [6,7],
               [8],
               [9,10,11],
               [12,13,14,15,16]]
Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
*/
/*
Solution
Approach 1: similar to questions 498
*/
public class Solution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        List<Integer> list = new ArrayList<>();
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int i = 0;
        int maxIndex = 0;
        int size = 0;
        for(List<Integer> temp : nums){
            for(int j = 0; j < temp.size(); j++){
                size++;
                int index = i + j;
                maxIndex = Math.max(index, maxIndex);
                if(!map.containsKey(index)) map.put(index, new ArrayList<>());
                map.get(index).add(temp.get(j));
            }
            i++;
        }

        int[] res = new int[size];
        int index = 0;
        for(i = 0; i <= maxIndex; i++){
            List<Integer> temp = map.get(i);
            for(int j = temp.size() - 1; j >= 0; j--){
                res[index++] = temp.get(j);
            }
        }

        return res;
    }

    public static void main(String[] args) {

    }
}
