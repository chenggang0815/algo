package LeetCode._0692_Top_K_Frequent_Words;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
/*
692. Top K Frequent Words
Given an array of strings words and an integer k, return the k most frequent strings.
Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.

Example 1:
Input: words = ["i","love","leetcode","i","love","coding"], k = 2
Output: ["i","love"]
Explanation: "i" and "love" are the two most frequent words.
Note that "i" comes before "love" due to a lower alphabetical order.

Example 2:
Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
Output: ["the","is","sunny","day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.
*/
/*
Solution 1
HashMap + minHeap
1. PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> (map.get(a) != map.get(b) ? map.get(a) - map.get(b) : b.compareTo(a)));
    map.get(a) != map.get(b) ? map.get(a) - map.get(b) => if a < b, a will be the peek()
    b.compareTo(a) => b="aaa", a="aa", b.compareTo(a) > 0 => b will the peek()
*/
public class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        for(String word : words){
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> (map.get(a) != map.get(b) ? map.get(a) - map.get(b) : b.compareTo(a)));
        for(String word : map.keySet()){
            if(pq.size() < k ){
                pq.add(word);
            }
            else if(map.get(pq.peek()) < map.get(word) || (map.get(pq.peek()).equals(map.get(word)) && (word.compareTo(pq.peek()) < 0))
            ){
                pq.poll();
                pq.add(word);
            }
        }
        List<String> res = new ArrayList<>();
        while(!pq.isEmpty()) res.add(pq.poll());
        res.sort((a, b) -> !map.get(b).equals(map.get(a)) ? (map.get(b) - map.get(a)) : a.compareTo(b));

        return res;
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 3);
        map.put("b",4);
        map.put("c",1);
        map.put("aa", 3);
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> (map.get(a) != map.get(b) ? map.get(a) - map.get(b) : a.compareTo(b)));
        pq.add("a");
        pq.add("aa");

        System.out.println(pq.peek());
        pq.poll();
        System.out.println(pq.peek());

        System.out.println(pq);

        System.out.println("aa".compareTo("aaa"));
        System.out.println("b".compareTo("a"));
    }
}
