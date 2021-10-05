package LeetCode._0128_Longest_Consecutive_Sequence;

import java.util.HashMap;
import java.util.Map;

public class Solution2 {
    public int longestConsecutive(int[] nums) {
        UF uf = new UF(nums.length);
        Map<Integer,Integer> map = new HashMap<Integer,Integer>(); // <value,index>
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i])){
                continue;
            }
            map.put(nums[i], i);
            if(map.containsKey(nums[i] + 1)){
                uf.union(i, map.get(nums[i] + 1));
            }
            if(map.containsKey(nums[i] - 1)){
                uf.union(i, map.get(nums[i] - 1));
            }
        }
        return uf.maxUnion();
    }

class UF{
    private int[] parents;
    public UF(int n){
        parents = new int[n];
        for(int i = 0; i < n; i++){
            parents[i] = i;
        }
    }

    private int find(int i){
        while(i != parents[i]){
            parents[i] = parents[parents[i]];
            i = parents[i];
        }
        return i;
    }

    public boolean connected(int i, int j){
        return find(i) == find(j);
    }

    public void union(int p, int q){
        int i = find(p);
        int j = find(q);
        parents[i] = j;
    }

    // returns the maximum size of union
    public int maxUnion(){ // O(n)
        int[] count = new int[parents.length];
        int max = 0;
        for(int i = 0; i < parents.length; i++){
            count[find(i)] ++;
            max = Math.max(max, count[find(i)]);
        }
        return max;
    }

    }

    public static void main(String[] args) {

    }
}
