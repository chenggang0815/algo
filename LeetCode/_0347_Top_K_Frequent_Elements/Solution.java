package LeetCode._0347_Top_K_Frequent_Elements;
/*
347. Top K Frequent Elements

Given a non-empty array of integers, return the k most frequent elements.

Example 1:
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]

Example 2:
Input: nums = [1], k = 1
Output: [1]

Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
You can return the answer in any order.
 */

import java.util.*;

/*
Approach 1： 统计每个数字的次数，根据次数排序，参考python solution  time:o(n*log(n))

Approach 2： min-heap
记录每个数字出现的次数
把数字和对应的出现次数放到堆中（小顶堆）
如果堆已满（大小>=k）且当前数的次数比堆顶大，用当前元素替换堆顶元素
返回堆中的数字部分

思路3：QuickSelect
for example = [4,5,5,2,1,1,1,5,2,3,3,5,2]
1. build hashmap<element, frequency>, and a array of unique element
   (because the elements are unique but their frequency are not)
2. HashMap<>() => (4, cnt=1) (5, cnt=4) (2, cnt=3) (1, cnt=3) (3, cnt=2)
   unique array => nums = [3,5,2,1,4]
3. select a pivot = nums[left] = 3
   after a partition => [4,    3,     1,      2,       5]
                       cnt=1  cnt=2  cnt=3   cnt=3   cnt=4
                      all the number on the left of pivot have small frequency
                      all the number on the right of pivot have bigger frequency

4. partitionArray(nums, 0, nums.length, nums.length - k)

5. index m = partition(nums, left, right)
   if k == m return
   else if k < m => partitionArray(nums, left, m - 1, k)
   else partitionArray(nums, m + 1, right, k)

思路4：桶排序法 time:o(n) space:o(n)
https://leetcode.com/problems/top-k-frequent-elements/discuss/81602/Java-O(n)-Solution-Bucket-Sort
 */

public class Solution {
    //Approach 1
    //https://leetcode.com/problems/top-k-frequent-elements/discuss/81602/Java-O(n)-Solution-Bucket-Sort
    static int[] topKFrequent(int[] nums, int k) {

        HashMap<Integer,Integer> map = new HashMap();
        for(int num : nums){
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }


        List<Integer>[] list = new List[nums.length + 1];
        for(int key : map.keySet()){
            //key => 数字
            //i => 数字对应的频数作为下标
            int i = map.get(key);
            if(list[i] == null){
                list[i] = new ArrayList();
            }
            list[i].add(key);
        }

        // 倒序遍历数组获取出现顺序从大到小的排列
        int[] result = new int[k];
        int j=0;
        for(int i = list.length - 1; i >= 0 && j < k; i--){  //从后往前遍历，拿了k个值就终止
            if(list[i] == null) continue;
            for(Integer number : list[i]){
                result[j++] = number;
            }
        }

        return result;
    }

    //Approach 2

    static int[] topKFrequent2(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num: nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // min heap
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));

        for(int num: map.keySet()){
            pq.add(num);
            if(pq.size() > k ){
                pq.poll();
            }
        }

        int[] res = new int[k];
        for(int i = 0; i < k; i++){
            res[i] = pq.poll();
        }

        return res;
    }

    //Approach 3

    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequent2(new int[]{1,1,1,2,3,3}, 2)));
    }
}
