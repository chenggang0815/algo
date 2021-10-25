package LeetCode._0080_Remove_Duplicates_from_Sorted_Array_II;
/*
80. Remove Duplicates from Sorted Array II

Example 1:
Input: nums = [1,1,1,2,2,3]
Output: 5, nums = [1,1,2,2,3,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
*/

/*
Solution:
if k=2, for example
[1,1,1,1,1,1,2,2,2,2,2,2,3]
1. when index=0 and index=1 we can remain the element
2. iterate the array from index=3
3.
4. if current number nums[i] == nums[i - k] => i++

首先我们先让前 2 位直接保留，得到 1,1
对后面的每一位进行继续遍历，能够保留的前提是与当前位置的前面 k 个元素不同（答案中的第一个 1），因此我们会跳过剩余的 1，将第一个 2 追加，得到 1,1,2
继续这个过程，这时候是和答案中的第 2 个 1 进行对比，因此可以得到 1,1,2,2
这时候和答案中的第 1 个 2 比较，只有与其不同的元素能追加到答案，因此剩余的 2 被跳过，3 被追加到答案：1,1,2,2,3

作者：AC_OIer
链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/solution/gong-shui-san-xie-guan-yu-shan-chu-you-x-glnq/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Solution {
    public int removeDuplicates(int[] nums) {
        return process(nums, 2);
    }

    int process(int[] nums, int k) {
        int index = 0;
        for (int num : nums) {
            if (index < k || num != nums[index - k]){
                nums[index] = num;
                index++;
            }
        }
        return index;
    }

    public static void main(String[] args) {

    }
}
