package Amazon.Group_Division;
/*
Group Division
A university has admitted a group of n students with varying skill levels.
To better accommodate the students, the university has decided to create classes tailored to the skill levels.
A placement examination will return a skill level that will be used to group the students, where levels[i] represents the skill level of student i.
All students within a group must have a skill level within maxSpread, a specified range of one another.
Determine the minimum number of classes that must be formed.
Example 1:
input => levels = [1, 4, 7, 3, 4] maxSpread = 2
output => 3
The students in any group must be within maxSpread = 2 levels of each other.
In this case, one optimal grouping is (1, 3), (4, 4), and (7).
Another possible grouping is (1), (3, 4, 4), (7).
There is no way to form fewer than 3 groups.
 */

import java.util.Arrays;

/*
Solution:
[1,3,4,4,7]
1. first, sort the array
2. iterate the array
int left = 0
int res = 0
    if left + maxSpread >= nums[i] => continue
    if left + maxSpread < nums[i] =>
                                    left = i
                                    res++


1     3   4  4   7  max=2 res=1
left  i

1     3   4  4   7  max=2 res=1
left      i

1     3   4  4   7  max=2 res=2
        left i

1     3   4  4   7  max=2 res=2
        left     i

1     3   4  4   7  max=2 res=3
                left
 */
public class Solution {
    static int groupDivision(int[] nums, int maxSpread){
        if (nums.length <= 1) return nums.length;
        Arrays.sort(nums);
        int res = 1;
        int left = 0;
        for (int i = 1; i < nums.length; i++){
            if (nums[left] + maxSpread < nums[i]){
                left = i;
                res++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(groupDivision(new int[]{7, 1,7,10,4}, 5));
    }
}
