package Facebook._1762_Buildings_With_an_Ocean_View;

import java.util.ArrayList;
import java.util.List;

/*
1762. Buildings With an Ocean View
There are n buildings in a line. You are given an integer array heights of size n that represents the heights of the buildings in the line.
The ocean is to the right of the buildings. A building has an ocean view if the building can see the ocean without obstructions. Formally, a building has an ocean view if all the buildings to its right have a smaller height.
Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing order.

Example 1:
Input: heights = [4,2,3,1]
Output: [0,2,3]
Explanation: Building 1 (0-indexed) does not have an ocean view because building 2 is taller.

Example 2:
Input: heights = [4,3,2,1]
Output: [0,1,2,3]
Explanation: All the buildings have an ocean view.

Example 3:
Input: heights = [1,3,2,4]
Output: [3]
Explanation: Only building 3 has an ocean view.

Constraints:
1 <= heights.length <= 105
1 <= heights[i] <= 109
*/
/*
Solution
Approach 1: time:O(n) space:O(1)
example 1:
    4 3 2 1
    *   * *
example 2:
    1 3 2 4
          *
1. we can iterate from the left to right, and maintain the max height,
if the current height > max height, the current building can see the ocean, and then we update the max height
2. because we don't know how many buildings can see the ocean, so we need to use a list (dynamic array) to store the index
3. put the index in final array reversely
*/
public class Solution {
    public int[] findBuildings(int[] heights) {
        int maxHeight = Integer.MIN_VALUE;
        List<Integer> list = new ArrayList<>();
        for(int i = heights.length - 1; i >= 0; i--){
            if(heights[i] > maxHeight) list.add(i);
            maxHeight = Math.max(maxHeight, heights[i]);
        }
        int[] res = new int[list.size()];
        int index = 0;
        for(int i = list.size() - 1; i >= 0; i--){
            res[index++] = list.get(i);
        }

        return res;
    }

    public static void main(String[] args) {

    }
}
