package LeetCode._0128_Longest_Consecutive_Sequence;

import java.util.HashMap;
import java.util.Map;

public class Solution2 {
    class UnionFind{
        int[] parents;
        UnionFind(int n){
            parents = new int[n];
            for(int i = 0; i < n; i++){
                parents[i] = i;
            }
        }

        int findParent(int i){
            if(i == parents[i]) return i;

            parents[i] = findParent(parents[i]);

            return parents[i];
        }

        boolean connected(int i, int j){
            return findParent(i) == findParent(j);
        }

        void merge(int i, int j){
            int p1 = findParent(i);
            int p2 = findParent(j);
            parents[p1] = p2;
        }

        /*
returns the maximum size of a component =>
for example, input = [100, 4, 200, 1, 3, 2, 101]
        1
       /
      2
     /
    3                  100
   /                   /
  4             200   101
 size=4

how to get the maximum size of the union?
=> parents[4]=3, parents[2]=2, parents[2]=1, parents[1]=1
=> findParent(4)=1, findParent(3)=1, findParent(2)=1, findParent(1)=1
=> size = 4
         */
        public int maxUnion(){ // O(n)
            int[] parentCnt = new int[parents.length];
            int size = 0;
            for(int i = 0; i < parents.length; i++){
                int parent = findParent(i);
                parentCnt[parent]++;
                size = Math.max(size, parentCnt[parent]);
            }
            return size;
        }

    }

    public int longestConsecutive(int[] nums) {
        UnionFind uf = new UnionFind(nums.length);
        Map<Integer,Integer> map = new HashMap<Integer,Integer>(); // <value,index>
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i])){
                continue;
            }
            map.put(nums[i], i);
            if(map.containsKey(nums[i] + 1)){
                uf.merge(i, map.get(nums[i] + 1));
            }
            if(map.containsKey(nums[i] - 1)){
                uf.merge(i, map.get(nums[i] - 1));
            }
        }
        return uf.maxUnion();
    }


    public static void main(String[] args) {

    }
}
