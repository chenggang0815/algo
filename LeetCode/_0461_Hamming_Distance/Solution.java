package LeetCode._0461_Hamming_Distance;
/*
461. Hamming Distance
The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
Given two integers x and y, return the Hamming distance between them.

Example 1:
Input: x = 1, y = 4
Output: 2
Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑
The above arrows point to positions where the corresponding bits are different.

Example 2:
Input: x = 3, y = 1
Output: 1

Constraints:
0 <= x, y <= 231 - 1
*/
/*
Approach 1:
1. z = x ^ y
2. count the number of 1 in z

Approach 2
1. because the max length of x and y is 32
2. compare each digit one by one
3. (x & 1) => 比较x的最后一位是不是1
4. (y & 1) => 比较y的最后一位是不是1

tip (x | 0) != (y | 0) 就不行
*/
public class Solution {
    public int hammingDistance1(int x, int y) {
        int res = 0;
        int z = x ^ y;
        while (z > 0){
            if ((z & 1) == 1) res++;
            z = z >> 1; // right shift, 1010 >> 1 => 101
        }

        return res;
    }

    public int hammingDistance2(int x, int y) {
        int res = 0;
        for (int i = 0; i < 32; i++){
            if ((x & 1) != (y & 1)) res++;
            x = x >> 1;
            y = y >> 1;
        }

        return res;
    }

    public static void main(String[] args) {

    }
}
