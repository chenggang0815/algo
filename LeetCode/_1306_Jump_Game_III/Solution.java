package LeetCode._1306_Jump_Game_III;
/*
1306. Jump Game III
Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.
Notice that you can not jump outside of the array at any time.

Example 1:
Input: arr = [4,2,3,0,3,1,2], start = 5
Output: true
Explanation:
All possible ways to reach at index 3 with value 0 are:
index 5 -> index 4 -> index 1 -> index 3
index 5 -> index 6 -> index 4 -> index 1 -> index 3

Example 2:
Input: arr = [4,2,3,0,3,1,2], start = 0
Output: true
Explanation:
One possible way to reach at index 3 with value 0 is:
index 0 -> index 4 -> index 1 -> index 3

Example 3:
Input: arr = [3,0,2,1,2], start = 2
Output: false
Explanation: There is no way to reach at index 1 with value 0.

Constraints:
1 <= arr.length <= 5 * 104
0 <= arr[i] < arr.length
0 <= start < arr.length
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/*
Approach 1: dfs

Approach 2: bfs
*/
public class Solution {
    // approach 1.1
    public boolean canReach(int[] arr, int start) {
        int[] visited = new int[arr.length];

        return helper(arr, start, visited);
    }

    boolean helper(int[] nums, int index, int[] visited){
        if(index < 0 || index >= nums.length) return false;

        if(visited[index] >= 2) return false;

        if(nums[index] == 0) return true;


        if(index - nums[index] >= 0){
            visited[index - nums[index]]++;
            if(helper(nums, index - nums[index], visited)) return true;
        }

        if(index + nums[index] <= nums.length - 1){
            visited[index + nums[index]]++;
            if(helper(nums, index + nums[index], visited)) return true;
        }

        return false;
    }

    boolean res = false;
    public boolean canReach2(int[] arr, int start) {
        int[] visited = new int[arr.length];
        helper2(arr, start, visited);

        return res;
    }

    void helper2(int[] nums, int index, int[] visited){
        if(index < 0 || index >= nums.length) return;

        if(visited[index] >= 2) return;

        if(nums[index] == 0){
            res = true;
            return;
        }

        if(index - nums[index] >= 0){
            visited[index - nums[index]]++;
            helper2(nums, index - nums[index], visited);
        }

        if(index + nums[index] <= nums.length - 1){
            visited[index + nums[index]]++;
            helper2(nums, index + nums[index], visited);
        }
    }

    // approach 2 - 1
    public boolean canReach3(int[] arr, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        HashSet<Integer> set = new HashSet<>();
        set.add(start);
        while(!queue.isEmpty()){
            int index = queue.poll();
            if(arr[index] == 0) return true;
            if(index - arr[index] >= 0 && !set.contains(index - arr[index])){
                queue.add(index - arr[index]);
                set.add(index - arr[index]);
            }

            if(index + arr[index] < arr.length && !set.contains(index + arr[index])){
                queue.add(index + arr[index]);
                set.add(index + arr[index]);
            }
        }

        return false;
    }

    public boolean canReach4(int[] arr, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while(!queue.isEmpty()){
            int index = queue.poll();
            if(arr[index] == 0) return true;
            if(arr[index] < 0) continue;
            if(index - arr[index] >= 0){
                queue.add(index - arr[index]);
            }

            if(index + arr[index] < arr.length){
                queue.add(index + arr[index]);
            }

            arr[index] = -arr[index];
        }

        return false;
    }

    public static void main(String[] args) {

    }
}
