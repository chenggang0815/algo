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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
思路1： 统计每个数字的次数，根据次数排序，参考python solution  time:o(n*log(n))

思路2： 堆排序
记录每个数字出现的次数
把数字和对应的出现次数放到堆中（小顶堆）
如果堆已满（大小>=k）且当前数的次数比堆顶大，用当前元素替换堆顶元素
返回堆中的数字部分

思路3：快速排序


思路4：桶排序法 time:o(n) space:o(n)
 */

public class Solution {
    // 桶排序法
    static int[] topKFrequent(int[] nums, int k) {

        HashMap<Integer,Integer> map = new HashMap();
        for(int num : nums){
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        //桶排序
        //将频率作为数组下标，对于出现频率不同的数字集合，存入对应的数组下标
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

    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequent(new int[]{1,1,1,2,3,3}, 2)));
    }
}
