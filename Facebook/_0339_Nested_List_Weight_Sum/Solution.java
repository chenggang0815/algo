package Facebook._0339_Nested_List_Weight_Sum;
/*
339. Nested List Weight Sum
You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists.
The depth of an integer is the number of lists that it is inside of. For example, the nested list [1,[2,2],[[3],2],1] has each integer's value set to its depth.
Return the sum of each integer in nestedList multiplied by its depth.

Example 1:
Input: nestedList = [[1,1],2,[1,1]]
Output: 10
Explanation: Four 1's at depth 2, one 2 at depth 1. 1*2 + 1*2 + 2*1 + 1*2 + 1*2 = 10.

Example 2:
Input: nestedList = [1,[4,[6]]]
Output: 27
Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3. 1*1 + 4*2 + 6*3 = 27.
*/
/*
Solution
Approach 1: DFS

Approach 2: BFS

*/
public class Solution {
    // dfs
    public int depthSum1(List<NestedInteger> nestedList) {
        int[] res = new int[1];

        dfs(nestedList, res, 1);

        return res[0];
    }

    void dfs(List<NestedInteger> nestedList, int[] res, int depth){
        for(NestedInteger list : nestedList){
            if(list.isInteger()){
                res[0] += list.getInteger() * depth;
            }else{
                dfs(list.getList(), res, depth + 1);
            }
        }
    }

    // bfs
    public int depthSum2(List<NestedInteger> nestedList) {
        int res = 0;
        int depth = 1;
        Queue<List<NestedInteger>> queue = new LinkedList<>();
        queue.add(nestedList);
        while(!queue.isEmpty()){
            int cnt = queue.size();
            while(cnt > 0){
                List<NestedInteger> lists = queue.poll();
                for(NestedInteger list : lists){
                    if(list.isInteger()) res += depth * list.getInteger();
                    else{
                        queue.add(list.getList());
                    }
                }
                cnt--;
            }
            depth++;
        }

        return res;
    }

    public static void main(String[] args) {

    }
}
