package Amazon._0323_Number_of_Connected_Components_in_an_Undirected_Graph;

public class Solution2 {
    class UnionFind{
        int[] parent;
        UnionFind(int n){
            parent = new int[n + 1];
            for (int i = 0; i <= n; i++){
                parent[i] = i;
            }
        }
        // disjoint-set union -  Without Path Compression
        int findParent(int i){
            while (i != parent[i]){
                i = parent[i];
            }
            return i;
        }

        // Path compression
        //edges = [0,1],[1,2],[2,3]
    /*  parents = new int[] {0,1,2,3}
    parents[0]=0 parents[1]=1 parents[2]=2 parents[3]=3
           3
          /
         2
        /
       1
      /
     0  parents[0]=1 parents[1]=2 parents[2]=3 parents[3]=3
     after path compression for node 0 => parents[0]=3 parents[1]=2 parents[2]=3 parents[3]=3
         3
        / \
       2   0
      /
     1
   */
        int findParent2(int i) {
            if (i == parent[i]) return i;
            //return parent[i] = findParent(parent, parent[i]); // Path compression
            parent[i] = findParent(parent[i]);

            return parent[i];
        }

        void merge(int i, int j){
            int p1 = findParent(i);
            int p2 = findParent(j);
            parent[p1] = p2;
        }
    }

    int countComponents2(int n, int[][] edges){
        UnionFind uf = new UnionFind(n);
        int res = n;
        for (int[] edge : edges){
            int p1 = uf.findParent(edge[0]);
            int p2 = uf.findParent(edge[1]);
            if (p2 != p1){
                uf.merge(p1, p2);
                res--;
            }
        }

        return res;
    }

}
