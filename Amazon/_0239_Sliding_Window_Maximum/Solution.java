package Amazon._0239_Sliding_Window_Maximum;
/*
239. Sliding Window Maximum

You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
You can only see the k numbers in the window. Each time the sliding window moves right by one position.
Return the max sliding window.

Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation:
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
solution 1: brute force => time:O(n*k) space:O(1)

solution 2: deque (double-ended queue) => time:O(n) space:O(n)
=> ensure the deque window only has decreasing elements => example 1
=> ensure the deque size is no more than k => example 2
=> that way, the leftmost element is always the largest

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
iterate the array,
i=0 num=1 deque=[1]
i=1 num=3 => 3 > 1 => delete 1 => deque=[3]
i=2 num=-1 => -1 < 3 => deque=[3, -1]  => if i + 1 >= k => res[i+1-k]=deque.peek()=>res[0]=3
i=3 num=-3 => -3 < -1 => deque=[3, -1, -3] => if i + 1 >= k => res[i+1-k]=deque.peek()=>res[1]=3
i=4 num=5 => 5 > -3,1,3 => deque=[5] => if i + 1 >= k => res[i+1-k]=deque.peek()=>res[2]=5
i=5 num=3 => 3 < 5 => deque=[5,3] => if i + 1 >= k => res[i+1-k]=deque.peek()=>res[3]=5
i=6 num=6 => 6 > 3,5 => deque=[6] => if i + 1 >= k => res[i+1-k]=deque.peek()=>res[4]=6
i=7 num=7 => 7 > 6 => deque=[7] => if i + 1 >= k => res[i+1-k]=deque.peek()=>res[5]=7

example 2
Input: nums = [7,6,5,4,3,2,1], k = 3
Output: [7,6,5,4,3]
i=0 num=7 deque=[7]
i=1 num=6 => 6 < 7 => deque=[7,6]
i=2 num=5 => 5 < 6 => deque=[7,6,5] => if i + 1 >= k => res[i+1-k]=deque.peek()=>res[0]=7
i=3 num=4 => 4 < 5 => deque=[7,6,5,4] => if i - deque.peek() >= k => deque.poll() => deque=[6,5,4]
                  => if i + 1 >= k => res[i+1-k]=deque.peek()=>res[1]=6
i=4 num=3 => 3 < 4 => deque=[6,5,4,3] => if i - deque.peek() >= k => deque.poll() => deque=[5,4,3]
                  => if i + 1 >= k => res[i+1-k]=deque.peek()=>res[2]=5

solution 3: dynamic programming
 */
public class Solution {
    public int[] maxSlidingWindow1(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        for(int i = 0; i < nums.length - k + 1; i++){
            int maxInWindow = nums[i];
            for(int j = i + 1; j < i + k; j++){
                maxInWindow = Math.max(nums[j], maxInWindow);
            }
            res[i] = maxInWindow;
        }

        return res;
    }

    static int[] maxSlidingWindow2(int[] nums, int k){
        int[] res = new int[nums.length - k + 1];
        LinkedList<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++){
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]){
                deque.pollLast();
            }

            deque.addLast(i);
            if (i - k >= deque.peekFirst()) deque.pollFirst();
            if (i + 1 >= k){
                res[i + 1 - k] = nums[deque.peekFirst()];
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        int k = 3;
        System.out.println(Arrays.toString(maxSlidingWindow2(nums, k)));
    }
}
