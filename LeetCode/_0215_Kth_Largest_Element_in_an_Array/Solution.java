package LeetCode._0215_Kth_Largest_Element_in_an_Array;
/*
215 - kth largest element in an array
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:
Input: [3,2,1,5,6,4] and k = 2
Output: 5

Example 2:
Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
*/

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/*
Approach 1：sort time: O(n*log(n)) space:o(1)

Approach 2：heap
time: O(nlog(k))
space: O(N + k)

Approach 3：quick select time:o(n)) space:o(log(n))

*/
public class Solution {
    static Random random = new Random();

    // Approach 1
    static int findKthLargest1(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
    // Approach 2
    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int num: nums){
            pq.add(num);
            if(pq.size() > k) pq.poll();
        }

        return pq.peek();
    }

    // Approach 3
    static int findKthLargest3(int[] nums, int k) {
        partitionArray(nums, 0, nums.length - 1, nums.length - k);

        return nums[nums.length - k];
    }

    static void partitionArray(int[] nums, int left, int right, int k){
        int mid = partition(nums, left, right);
        if (mid == k) return;
        else if (mid > k) partitionArray(nums, left, mid - 1, k);
        else partitionArray(nums, mid + 1, right, k);
    }

    static int partition(int[] nums, int left, int right){
        int i = left;
        int j = right;
        //int key = nums[left];
        int index = random.nextInt(right - left + 1) + left;
        int key = nums[index];

        while (i < j){
            while (i < j && nums[j] >= key){
                j--;
            }
            while (i < j && nums[i] <= key){
                i++;
            }
            if (i < j){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        nums[left] = nums[i];
        nums[i] = key;

        return i;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{3,2,3,1,2,4,5,5,6};
        System.out.println(findKthLargest3(nums, 4));
    }

}
