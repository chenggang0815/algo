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
solution 1: sort time:O(nlog(n)) space:O(1)
situation 1
example: [1,2,0,1] => sort => [0,1,1,2]
if nums[i] + 1 == nums[i + 1] => consecutive++
if nums[i] == nums[i + 1] continue
else => res => Math.max(res, consecutive)
               consecutive = 1
situation 2:
example: [1,2,0] => sort => [0,1,2]  => res = 1 consecutive = 3
return Math.max(res, consecutive)

solution 2: HashSet time:O(n) space:O(n)
input = [100,4,200,1,3,2,101]
set = <100, 4, 200, 1, 3, 2, 101>

=> iterate the set, for every num, if not exists num-1 in the set
=> the num maybe the smallest element of the longest consecutive
=> so, start the num, check until num+1 not exists in the set

for example, set = <100, 4, 200, 1, 3, 2, 101>
num = 100, check the consecutive, length = 2
num = 200, check the consecutive, length = 1
num = 1, check the consecutive, length = 4

solution 3: Union - find
The union-find is implicit.
In the beginning we have n nodes.
After merging consequences nodes we have several connected components and the task is to find out the biggest component.

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
    // time:O(nlog(n))
    public int longestConsecutive1(int[] nums) {
        if(nums.length == 0) return 0;
        Arrays.sort(nums);
        int cnt = 1;
        int res = 1;
        for(int i = 0; i < nums.length - 1; i++){

            if(nums[i] == nums[i + 1] - 1){
                cnt++;
            }else if (nums[i] == nums[i + 1]){
                continue;
            }else{
                res = Math.max(cnt, res);
                cnt = 1;
            }
        }

        return Math.max(cnt, res);
    }

    public int longestConsecutive2(int[] nums) {
        //if(nums.length == 0) return 0;
        HashSet<Integer> set = new HashSet<>();
        for(int num: nums){
                set.add(num);
        }
        int res = 0;
        for(int num: set){
            if(!set.contains(num - 1)){
                int cnt = 1;

                while(set.contains(num + 1)){
                    num++;
                    cnt++;
                }

                res = Math.max(res, cnt);
            }
        }

        return res;
    }


    public static void main(String[] args) {

    }
}
