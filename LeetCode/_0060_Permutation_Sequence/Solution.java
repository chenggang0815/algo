package LeetCode._0060_Permutation_Sequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
60. Permutation Sequence
The set [1, 2, 3, ..., n] contains a total of n! unique permutations.
By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Example 1:
Input: n = 3, k = 3
Output: "213"

Example 2:
Input: n = 4, k = 9
Output: "2314"

Example 3:
Input: n = 3, k = 1
Output: "123"
*/

/*
Solution
https://leetcode-cn.com/problems/permutation-sequence/solution/hui-su-jian-zhi-python-dai-ma-java-dai-ma-by-liwei/
Approach 1

*/
public class Solution {
    // n = 3 k = 4
    /*
     123
     132
     213
     231 => k = 4
     312
     321
    */
    // []
    // index = k / f(n - i)
    // index =
    public String getPermutation(int n, int k) {
        List<Integer> numbers = new ArrayList<>();
        int[] factorial = new int[n + 1];
        StringBuilder sb = new StringBuilder();

        // create an array of factorial lookup
        int sum = 1;
        factorial[0] = 1;
        for(int i = 1; i <= n; i++){
            sum *= i;
            factorial[i] = sum;
        }
        // factorial[] = {1, 1, 2, 6, 24, ... n!}

        for(int i = 1; i <= n; i++){
            numbers.add(i);
        }
        // numbers = {1, 2, 3, 4}

        k--;
        for(int i = 1; i <= n; i++){
            int index = k / factorial[n - i];
            sb.append(String.valueOf(numbers.get(index)));
            numbers.remove(index);
            k -= index * factorial[n - i];
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4));
        System.out.println(new ArrayList<>(list));
    }
}
