package Other.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
Coding：给定一个数组 A, 统计出现频率在 start, end 这个范围之间的 item
 */
public class solution1 {
    static List<Integer> findNumber(int[] nums, int start, int end){
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int num : nums){
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        List<Integer> res = new ArrayList<>();
        for (int num : count.keySet()){
            if (count.get(num) > start && count.get(num) < end){
                res.add(num);
            }
        }

        return res;
    }

    static List<Integer> findNumber2(int[] nums, int start, int end){
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        List<Integer> res = new ArrayList<>();
        int count = 0;
        int pre = nums[0];
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == pre){
                count++;
            }else{
                if (count > start && count < end) res.add(pre);
                count = 1;
            }
            pre = nums[i];
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(findNumber2(new int[]{1,2,2,2,3,1,1,1,4,5}, 2,4));
    }
}
