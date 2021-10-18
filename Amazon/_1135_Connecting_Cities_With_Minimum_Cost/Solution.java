package Amazon._1135_Connecting_Cities_With_Minimum_Cost;
import java.util.Arrays;

/*
1135. Connecting Cities With Minimum Cost
There are n cities labeled from 1 to n. You are given the integer n and an array connections where connections[i] = [xi, yi, costi] indicates that the cost of connecting city xi and city yi (bidirectional connection) is costi.
Return the minimum cost to connect all the n cities such that there is at least one path between each pair of cities. If it is impossible to connect all the n cities, return -1,
The cost is the sum of the connections' costs used.
Example 1:
Input: n = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
Output: 6
Explanation: Choosing any 2 edges will connect all cities so we choose the minimum 2.
*/

/*
Solution 1: Kruskal's algorithm
1. first sort the array by the weight
2. use union find, iterate the array
    2.1 if two node not be connected, which means two don't have same parent, merge two edges, res += weight
        2.1.1 count--, which means there two node be merged
    2.2 if two node already be connected, which means two have same parent, ignore, because the weight is more bigger
3. if count > 1, which means there are more than 1 component in the graph => return -1
*/
public class Solution {
    static class UnionFind{
        int[] parent;

        public UnionFind(int n){
            parent = new int[n + 1];
            for (int i = 1; i <= n; i++){
                parent[i] = i;
            }
        }

        // without path compression  57 ms
//        public int findParent(int i){
//            while (i != parent[i]){
//                i = parent[i];
//            }
//
//            return i;
//        }

        /*
          4
         /
        3
       /
      2
     /
    1
    find(1) -> find(2) -> find(3) -> find(4) => parent[1] = 4  return parent[1]
    38 ms
         */
        public int findParent(int i){
            if (i == parent[i]) return i;

            parent[i] = findParent(parent[i]);

            return parent[i];
        }

        public void merge(int i, int j){
            int p1 = findParent(i);
            int p2 = findParent(j);
            parent[p1] = p2;
        }
    }

    static int minimumCost(int n, int[][] connections){
        Arrays.sort(connections, (a, b) -> a[2] - b[2]);

        int res = 0;
        int count = n;

        UnionFind uf = new UnionFind(n);
        for (int [] connection: connections){
            int p1 = uf.findParent(connection[0]);
            int p2 = uf.findParent(connection[1]);
            if (uf.parent[p1] != uf.parent[p2]){
                res += connection[2];
                uf.merge(connection[0], connection[1]);
                count--;
            }
        }


        return count == 1 ? res: -1;
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] connections = new int[][]{{1,2,5},{1,3,6},{2,3,1}};
        System.out.println(minimumCost(n, connections));
    }
}
