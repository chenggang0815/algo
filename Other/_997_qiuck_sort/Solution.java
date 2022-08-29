package Other._997_qiuck_sort;
import java.util.Arrays;
import java.util.Random;
/*
快速排序
思路： time: o(n*log(n))  space: o(n*log(n))
1. 在nums中选择一个pivot（一般为nums[0]），通过一趟排序将数组A分为两部分 => 其中以pivot为中心，pivot右边都比pivot大，pivot左边都比pivot小
2. 然后对这两部分[left， i-1]和[i+1, right]分别递归执行这个过程
3. 递归结束条件 => 数组内只有一个元素 => i = left => [left, i - 1] => left > right

4. 一趟排序的方法：
nums = [5, 2, 8, 9, 2, 3, 4, 9]
        0  1  2  3  4  5  6  7
pivot = nums[0] = 5
i = 0
j = nums.length - 1 = 7
    4.1 从nums[i]从左往右找，找到第一个小于pivot的数，即为Ai
    4.2 从nums[j]从右往左找，找到第一个大于pivot的数，即为Aj
    4.3 交换Ai和Aj
    4.4 重复1，2，3步，直到i=j
    4.5 调整pivot的位置 => 交换Ai和pivot

nums = [5, 2, 8, 9, 2, 3, 4, 9]
        0  1  2  3  4  5  6  7
step 1
assume pivot = nums[0] = 5
left index=2
right index=6
swap(nums[2],nums[6]) => swap(8, 4) => nums=[5, 2, 4, 9, 2, 3, 8, 9]
                                             0  1  2  3  4  5  6  7
                                                   i           j
step 2
left index = 3
right index = 5
swap(nums[3],nums[5]) => swap(9, 3) => nums=[5, 2, 4, 3, 2, 9, 8, 9]
                                             0  1  2  3  4  5  6  7
                                                         j
                                                         i

step 3
swap nums[i] and nums[0] =>  nums=[2, 2, 4, 3, 5, 9, 8, 9]
                                             pivot
一趟排序之后 => pivot的左边都是小于等于pivot的数，pivot的右边都是大于等于pivot的数


需要注意的点：
• 为什么快排需要先从右边开始：j-- ？
	如果先出动i，显然i先到达"相遇数"，因为i只会在大于key的位置停下，这就意味着"相遇数"一定是大于key，
	那么交换后左边序列最左边的元素一定大于归位后的基准数了，与快排一趟结束后，key大于等于左边子序列元素矛盾；
	同理，如果先出动j，可以保证"相遇数"小于基准数

• key <= nums[j]  key >= nums[i]  等号不能忘
*/

class Solution {
    static Random random = new Random();
    static  void quickSort(int[] nums, int left, int right){
        if (left > right) return;

        int i = left;
        int j = right;
        int pivot = nums[left];

        // randomly choose a number and then switch with the first number
//        int index = left + random.nextInt(right - left + 1);
//        int pivot = nums[index];
//        nums[index] = nums[left];
//        nums[left] = pivot;

        while (i < j){
            while (i < j && nums[j] >= pivot){
                j--;
            }
            while (i < j && nums[i] <= pivot){
                i++;
            }
            if (i < j){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }

        nums[left] = nums[i];
        nums[i] = pivot;

        quickSort(nums, left,i-1);
        quickSort(nums,i+1, right);
    }


    public static void main(String[] args) {
        int[] nums = {10,7,2,4,7,62,3,4,2,1,8,9,19};
        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

}




