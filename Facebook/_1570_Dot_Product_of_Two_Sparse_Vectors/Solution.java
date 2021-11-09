package Facebook._1570_Dot_Product_of_Two_Sparse_Vectors;
/*
1570. Dot Product of Two Sparse Vectors

Given two sparse vectors, compute their dot product.
Implement class SparseVector:
1. SparseVector(nums) Initializes the object with the vector nums
2. dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot product between two SparseVector.

Follow up: What if only one of the vectors is sparse?

Example 1:
Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
Output: 8
Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
*/

import java.util.HashMap;

/*
Solution
Approach 1: sparse vector *  sparse vector
SparseVector(numa) => o(n) built array
dotProduct(vec) =>  o(n)

Approach 2: hashMap
SparseVector(numa) => o(n) built array
dotProduct(vec) =>  o(Math.min(map1.size(), map2.size())) => built once in O(n) complexity and get dot product in o(Math.min(map1.size(), map2.size()))
 */
public class Solution {
//    class SparseVector {
//
//        int[] array;
//        SparseVector(int[] nums) {
//            array = nums;
//        }
//
//        // Return the dotProduct of two sparse vectors
//        public int dotProduct(SparseVector vec) {
//            int res = 0;
//            for(int i = 0; i < vec.array.length; i++){
//                res += vec.array[i] * array[i];
//            }
//
//            return res;
//        }
//    }
    class SparseVector {
        HashMap<Integer, Integer> map;
        SparseVector(int[] nums) {
            map = new HashMap<>();
            for (int i = 0; i < nums.length; i++){
                if (nums[i] != 0) map.put(i, nums[i]);
            }
        }

    // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector vec) {
            HashMap<Integer, Integer> map1 = vec.map;
            int res = 0;
            if (map.size() < map1.size()){
                for (int num : map.keySet()){
                    res += map.get(num) * map1.getOrDefault(num, 0);
                }
            }else{
                for (int num : map1.keySet()){
                    res += map1.get(num) * map.getOrDefault(num, 0);
                }
            }

            return res;
        }
    }

    public static void main(String[] args) {

    }
}
