package Amazon._0547_Number_of_Provinces;

import java.util.HashMap;

public class Solution2 {
    static class UnionFind{
        private int[] parent;
        public UnionFind(int n){
            parent = new int[n + 1];
            for(int i = 0; i <= n; i++){
                parent[i] = i;
            }
        }

        void merge(int x, int y){
            int p1 = findParent(x);
            int p2 = findParent(y);
            parent[p1] = parent[p2];
        }

        int findParent(int i){
            if(i == parent[i]) return i;

            parent[i] = findParent(parent[i]);

            return parent[i];
        }

    }
    /*
    1 1 1
    1 1 1
    1 1 1
    [0,1] => p1=0 p2=1
    [1,0] => p1=1 p2=0
    */
    static public int findCircleNum(int[][] isConnected){
        int n = isConnected.length;
        UnionFind uf = new UnionFind(n);
        int count = n;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (i != j && isConnected[i][j] == 1){
                    int p1 = uf.findParent(i);
                    int p2 = uf.findParent(j);
                    if(p1 != p2){
                        uf.merge(i, j);
                        count--;
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{
                {1,1,0,0,0},
                {1,1,1,0,0},
                {0,1,1,0,0},
                {0,0,0,1,1},
                {0,0,0,1,1}
        };
        System.out.println(findCircleNum(nums));

    }
}
