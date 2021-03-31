package com.example.myapplication.array;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/3sum/
 * 数组相关
 * 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * [0,-1,1,-2,2,2,2]
 * 思路:
 * 难点在于如何去重。
 * 比如在取第三个数的时候，可能会取到多个2，那么最终返回多个[0,-2,2]
 * 解决办法：可以通过排序解决重复选择的问题
 * <p>
 * 排序+双指针
 */
public class ArrayTest1 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }
        SortUtil sortUtil = new SortUtil();
        sortUtil.quickSort(nums);


        for (int k = 0; k < nums.length; k++) {
            if (nums[k] > 0) {
                break;
            }
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            int i = k + 1;
            int j = nums.length - 1;
            while (i < j) {
                int sum = nums[k] + nums[i] + nums[j];
                if (sum == 0) {
                    //判断i和j的相邻元素
                    while (nums[i] == nums[i + 1]) {
                        i++;
                    }

                    while (nums[j] == nums[j - 1]) {
                        j--;
                    }
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[k]);
                    list.add(nums[i]);
                    list.add(nums[j]);
                    result.add(list);
                    j--;
                    i++;
                } else if (sum > 0) {
                    j--;
                } else {
                    i++;
                }
            }

        }
        return result;
    }

}
