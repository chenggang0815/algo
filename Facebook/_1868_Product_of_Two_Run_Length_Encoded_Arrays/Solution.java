package Facebook._1868_Product_of_Two_Run_Length_Encoded_Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
1868. Product of Two Run-Length Encoded Arrays
Run-length encoding is a compression algorithm that allows for an integer array nums with many segments of consecutive repeated numbers to be represented by a (generally smaller) 2D array encoded. Each encoded[i] = [vali, freqi] describes the ith segment of repeated numbers in nums where vali is the value that is repeated freqi times.
    1. For example, nums = [1,1,1,2,2,2,2,2] is represented by the run-length encoded array encoded = [[1,3],[2,5]]. Another way to read this is "three 1's followed by five 2's".
The product of two run-length encoded arrays encoded1 and encoded2 can be calculated using the following steps:
    1. Expand both encoded1 and encoded2 into the full arrays nums1 and nums2 respectively.
    2. Create a new array prodNums of length nums1.length and set prodNums[i] = nums1[i] * nums2[i].
    3. Compress prodNums into a run-length encoded array and return it.
You are given two run-length encoded arrays encoded1 and encoded2 representing full arrays nums1 and nums2 respectively. Both nums1 and nums2 have the same length. Each encoded1[i] = [vali, freqi] describes the ith segment of nums1, and each encoded2[j] = [valj, freqj] describes the jth segment of nums2.
Return the product of encoded1 and encoded2.
Note: Compression should be done such that the run-length encoded array has the minimum possible length.

Example 1:
Input: encoded1 = [[1,3],[2,3]], encoded2 = [[6,3],[3,3]]
Output: [[6,6]]
Explanation: encoded1 expands to [1,1,1,2,2,2] and encoded2 expands to [6,6,6,3,3,3].
prodNums = [6,6,6,6,6,6], which is compressed into the run-length encoded array [[6,6]].

Example 2:
Input: encoded1 = [[1,3],[2,1],[3,2]], encoded2 = [[2,3],[3,3]]
Output: [[2,3],[6,1],[9,2]]
Explanation: encoded1 expands to [1,1,1,2,3,3] and encoded2 expands to [2,2,2,3,3,3].
prodNums = [2,2,2,6,9,9], which is compressed into the run-length encoded array [[2,3],[6,1],[9,2]].
*/
/*
Solution
Approach 1
1. iterate the two array
    [1,3] [2,1] [3,2]
    i=0
    [2,3] [3,3]
    j=0
    =>[1,3] * [2,3] => [2,3] =>i++;j++
    =>[2,1] * [3,3] => [6,1] =>i++, code2[1] = code2[1] - code1[1] = 3 - 1 = 2 => code2 = [3,2]
    =>[3,2] * [3,2] => [9,2] =>
*/
public class Solution {
    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        int i = 0;
        int j = 0;
        int[] code1 = encoded1[i];
        int[] code2 = encoded2[j];
        List<List<Integer>> res = new ArrayList<>();

        while(i < encoded1.length && j < encoded2.length){
            int num = code1[0] * code2[0];
            int size = res.size();
            if(code1[1] == code2[1]){
                if(size >0 && num == res.get(size - 1).get(0)){
                    int cnt = res.get(size - 1).get(1);
                    res.remove(size - 1);
                    res.add(Arrays.asList(num, cnt + code1[1]));
                }else{
                    res.add(Arrays.asList(num, code1[1]));
                }
                i++;
                j++;
                if(i < encoded1.length) code1 = encoded1[i];
                if(j < encoded2.length) code2 = encoded2[j];
            }else if(code1[1] > code2[1]){
                if(size >0 && num == res.get(size - 1).get(0)){
                    int cnt = res.get(size - 1).get(1);
                    res.remove(size - 1);
                    res.add(Arrays.asList(num, cnt + code2[1]));
                }else{
                    res.add(Arrays.asList(num, code2[1]));
                }
                code1[1] = code1[1] - code2[1];
                j++;
                if(j < encoded2.length) code2 = encoded2[j];
            }else{
                if(size >0 && num == res.get(size - 1).get(0)){
                    int cnt = res.get(size - 1).get(1);
                    res.remove(size - 1);
                    res.add(Arrays.asList(num, cnt + code1[1]));
                }else{
                    res.add(Arrays.asList(num, code1[1]));
                }
                code2[1] = code2[1] - code1[1];
                i++;
                if(i < encoded1.length) code1 = encoded1[i];
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,34};
        int i = 0;
        while (i < 5){
            //nums[i++] = 4;
            System.out.println(nums[i++]);
        }
    }
}
