package com.example.myapplication.search;

/**
 * 二分查找
 */
public class BinarySearch {
    /**
     * 升序数组
     * 输入: nums = [-1,0,3,5,9,12], target = 9
     * 输出: 4
     * 解释: 9 出现在 nums 中并且下标为 4
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        return _searchRecur(nums, left, right, target);
    }

    private int _searchRecur(int[] nums, int left, int right, int target) {
        if (left > right) return -1;
        int middle = left + ((right - left) >> 1);
        if (target == nums[middle]) {
            return middle;
        } else if (target > nums[middle]) {
            return _searchRecur(nums, left + 1, right, target);
        } else {
            return _searchRecur(nums, left, right - 1, target);
        }
    }


    /**
     * 输入: 4
     * 输出: 2
     * <p>
     * 输入: 8
     * 输出: 2
     * 说明: 8 的平方根是 2.82842...,
     * 由于返回类型是整数，小数部分将被舍去。
     *
     * @param x
     * @return k²=x    k为整数
     * <p>
     * 1.二分查找   0-x
     * 找到所有满足k²<x的k的最大值
     */
    public int mySqrt(int x) {
        int left = 0;
        int right = x;
        int result = -1;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (middle * middle <= x) {
                result = middle;
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return result;
    }

    /**
     * 在排序数组中查找元素的第一个和最后一个位置
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     * ps:应该叫非降序吧
     * <p>
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     * <p>
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     * [5,7,7,8,8,10]
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        int left = 0;
        int right = nums.length - 1;
        return _searchRecur2(nums, left, right, target);
    }

    private int[] _searchRecur2(int[] nums, int left, int right, int target) {
        if (left > right) return new int[]{-1, -1};
        int middle = left + ((right - left) >> 1);
        if (target == nums[middle]) {
            //向左向右找到相同的
            int start = middle;
            int end = middle;

            while (start >= left) {
                if (nums[--start] == nums[middle]) {
                } else {
                    start++;
                    break;
                }
            }
            while (end <= right) {
                if (nums[++end] == nums[middle]) {
                } else {
                    end++;
                    break;
                }
            }
            return new int[]{start, end};
        } else if (target > nums[middle]) {
            return _searchRecur2(nums, left + 1, right, target);
        } else {
            return _searchRecur2(nums, left, right - 1, target);
        }
    }
}
