package Amazon._0547_Number_of_Provinces;

import java.util.HashMap;

public class Solution2 {
    static class UnionFind{
        private HashMap<Integer, Integer> map;
        private int count;

        public UnionFind(){
            map = new HashMap<>();
            count = 0;
        }

        void add(int x){
            if (!map.containsKey(x)){
                map.put(x, null);
                count++;
            }
        }

        void merge(int x, int y){
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY){
                map.put(rootX, rootY);
                count--;
            }
        }

        int find(int x){
            int root = x;
            while (map.get(root) != null){
                root = map.get(root);
            }

            while (x != root){
                int original_root = map.get(x);
                map.put(x, root);
                x = original_root;
            }

            return root;
        }

    }

    static int findCircleNum(int[][] isConnected){
        UnionFind uf = new UnionFind();
        for(int i = 0;i < isConnected.length;i++){
            uf.add(i);
            for(int j = 0;j < i;j++){
                if(isConnected[i][j] == 1){
                    uf.merge(i,j);
                }
            }
        }

        return uf.count;
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
