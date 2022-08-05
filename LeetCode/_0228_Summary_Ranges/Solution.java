package LeetCode._0228_Summary_Ranges;
import java.util.ArrayList;
import java.util.List;

/*
228. Summary Ranges

You are given a sorted unique integer array nums.
Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.
Each range [a,b] in the list should be output as:
"a->b" if a != b
"a" if a == b

Example 1:
Input: nums = [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]
Explanation: The ranges are:
[0,2] --> "0->2"
[4,5] --> "4->5"
[7,7] --> "7"

Example 2:
Input: nums = [0,2,3,4,6,8,9]
Output: ["0","2->4","6","8->9"]
Explanation: The ranges are:
[0,0] --> "0"
[2,4] --> "2->4"
[6,6] --> "6"
[8,9] --> "8->9"
 */

/*
思路：
双指针，从right = left = 0开始:
                              if nums[left + 1] - nums[left] == 1 => left++
                              else [nums[right] -> nums[left]]
                              => right = left + 1
需要考虑整数溢出问题
 */
public class Solution {
    static List<String> summaryRanges1(int[] nums) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i < nums.length){
            int j = i;
            while (j < nums.length - 1){
                if (nums[j + 1] - nums[j] == 1) j++;
                else break;
            }
            if (j > i) {
                StringBuilder sb = new StringBuilder();
                String s = "->";
                sb.append(nums[i]);
                sb.append(s);
                sb.append(nums[j]);
                res.add(sb.toString());
            }else{
                res.add(String.valueOf(nums[i]));
            }
            i = j + 1;
        }

        return res;
    }

    static List<String> summaryRanges2(int[] nums) {
        List<String> res = new ArrayList<>();
        int right = 0;
        while (right < nums.length){
            int left = right;
            while (left < nums.length - 1){
                if (nums[left + 1] - nums[left] == 1) left++;
                else break;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(nums[right]);
            if (left > right) {
                sb.append("->");
                sb.append(nums[left]);
            }
            res.add(sb.toString());
            right = left + 1;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(summaryRanges2(new int[]{0,1,2,4,5,7}));
        System.out.println(summaryRanges2(new int[]{0,2,3,4,6,8,9}));
        System.out.println(summaryRanges2(new int[]{-5,-4,1}));
    }
}
