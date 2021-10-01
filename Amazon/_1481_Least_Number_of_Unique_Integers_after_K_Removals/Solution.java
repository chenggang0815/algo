package Amazon._1481_Least_Number_of_Unique_Integers_after_K_Removals;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/*
1481. Least Number of Unique Integers after K Removals

Given an array of integers arr and an integer k. Find the least number of unique integers after removing exactly k elements.

Example 1:
Input: arr = [5,5,4], k = 1
Output: 1
Explanation: Remove the single 4, only 5 is left.

Example 2:
Input: arr = [4,3,1,1,3,3,2], k = 3
Output: 2
Explanation: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3 will be left.

Constraints:
1 <= arr.length <= 10^5
1 <= arr[i] <= 10^9
0 <= k <= arr.length
*/

/*
solution 1 : hashmap + sort (sort + greedy) time: O(nlogn) space: O(n)

solution 2: hashmap + priority queue time: O(nlogn) space: O(n)
*/
public class Solution {
    static int findUniqueNumber1(int[] arr, int k){
        HashMap<Integer, Integer> count = new HashMap<>();
        for(int num: arr){
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        List<Integer> key = new ArrayList<>(count.keySet());
        key.sort((a, b) -> (count.get(a) - count.get(b)));

        for(int num: key){
            if(k > 0){
                k -= count.get(num);
                count.remove(num);
            }
            else break;
        }

        return k == 0 ? count.size() : count.size() + 1;
    }
    // sort by values of map, don't need to use map.get()
    static int findUniqueNumber2(int[] arr, int k){
        HashMap<Integer, Integer> count = new HashMap<>();
        for(int num: arr){
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        List<Integer> key = new ArrayList<>(count.values());
        key.sort((a, b) -> (a - b));

        int n = 0;
        for(int num: key){
            if(k > 0){
                k -= num;
                n++;
            }
            else break;
        }

        return k == 0 ? key.size() - n : key.size() - n + 1;
    }

    static int findUniqueNumber3(int[] arr, int k){
        HashMap<Integer, Integer> countMap = new HashMap<>();
        for(int num: arr){
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (countMap.get(a) - countMap.get(b)));
        pq.addAll(countMap.keySet());
        while (k > 0){
            k -= countMap.get(pq.poll());
        }

        return k == 0 ? pq.size() : pq.size() + 1;
    }

    public static void main(String[] args) {
        HashMap<Integer, Integer> count = new HashMap<>();
        count.put(2,3);
        count.put(10,20);
        System.out.println(count.keySet());
        for (int i : count.values()){
            System.out.println(i);
        }
    }
}
