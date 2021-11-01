package LeetCode._0128_Longest_Consecutive_Sequence;
import java.util.*;
/*
128. Longest Consecutive Sequence

Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
You must write an algorithm that runs in O(n) time.

Example 1:
Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

Example 2:
Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9

Constraints:
0 <= nums.length <= 105
-109 <= nums[i] <= 109
*/

/*
Solution
Approach 1: sort time:O(nlog(n)) space:O(1)
situation 1
example: [1,2,0,1] => sort => [0,1,1,2]
if nums[i] + 1 == nums[i + 1] => consecutive++
if nums[i] == nums[i + 1] continue
else => res => Math.max(res, consecutive)
               consecutive = 1
situation 2:
example: [1,2,0] => sort => [0,1,2]  => res = 1 consecutive = 3
return Math.max(res, consecutive)

Approach 2: HashSet time:O(n) space:O(n)
input = [100,4,200,1,3,2,101]
set = <100, 4, 200, 1, 3, 2, 101>

1. iterate the set, for each num, if not exists num-1 in the set
2. the num maybe the smallest element of the longest consecutive
3. so, start the num, check until num+1 not exists in the set

for example, set = <100, 4, 200, 1, 3, 2, 101>
1. num = 100, check the consecutive, length = 2
2. num = 4, exists 4-1=3 in the set, 4 is not the smallest number for the consecutive sequence
3. num = 200, check the consecutive, length = 1
4. num = 1, check the consecutive, length = 4

Approach 3: UnionFind
1. The union-find is implicit.
2. In the beginning we have n nodes.
3. After merging consequences nodes we have several connected components
4. and the task is to find out the biggest component.

for example, input = [100, 4, 200, 1, 3, 2, 101]
        1
       /
      2
     /
   3                   100
  /                    /
 4             200   101

 how to get the maximum size of the union?

 parents[4] = 3
 parents[2] = 2
 parents[2] = 1
 parents[1] = 1

 find(4) = 1
 find(3) = 1
 find(2) = 1
 find(1) = 1

 size = 4
*/
public class Solution {
    // Approach 1  time:O(nlog(n))
    public int longestConsecutive1(int[] nums) {
        if(nums.length == 0) return 0;

        Arrays.sort(nums);
        int cnt = 1;
        int maxCnt = 1;
        for(int i = 0; i < nums.length - 1; i++){
            if(nums[i] + 1 == nums[i + 1]){
                cnt++;
            }else if (nums[i] == nums[i + 1]){
                continue;
            }else{
                maxCnt = Math.max(cnt, maxCnt);
                cnt = 1;
            }
        }

        return Math.max(cnt, maxCnt);
    }

    // Approach 2
    public int longestConsecutive2(int[] nums) {
        //if(nums.length == 0) return 0;
        HashSet<Integer> set = new HashSet<>();
        for(int num: nums){
            set.add(num);
        }
        int maxCnt = 0;
        for(int currentNum: set){
            if(!set.contains(currentNum - 1)){
                int cnt = 1;
                while(set.contains(currentNum + 1)){
                    currentNum++;
                    cnt++;
                }
                maxCnt = Math.max(maxCnt, cnt);
            }
        }

        return maxCnt;
    }


    public static void main(String[] args) {

    }
}
