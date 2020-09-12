package com.example.myapplication;

//回溯算法经典例题


import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BacktrackingDemo {
    private static final String TAG = "BacktrackingDemo";
    //47 全排列

    /**
     * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
     * 输入: [1,1,2]
     * 输出:
     * [
     * [1,1,2],
     * [1,2,1],
     * [2,1,1]
     * ]
     */
    Map<Integer, Boolean> topUsed = new HashMap();

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> total = new ArrayList<>();
        //每一层数据的使用情况
        Map<Integer, Boolean> usedVertical = new HashMap();
        if (nums == null || nums.length < 1) {
            return total;
        }
        Map<Integer, Map<Integer, Boolean>> used = new HashMap<>();
        permuteUniqueRecur(nums, 0, path, total, used, usedVertical);
        printList(total);
        return total;
    }


    private void permuteUniqueRecur(int[] nums, int depth, List<Integer> path, List<List<Integer>> total, Map<Integer, Map<Integer, Boolean>> used, Map<Integer, Boolean> usedVertical) {
        if (depth >= nums.length) {
            total.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used.get(depth + i) == null) {
                used.put(depth + i, new HashMap<Integer, Boolean>());
            }
            Boolean aBoolean = usedVertical.get(i);
            if (!path.isEmpty()) {
                if (aBoolean != null && aBoolean) continue;
            }
            Map<Integer, Boolean> booleanMap = used.get((depth + i));
            if (booleanMap.get(i) != null && booleanMap.get(i)) continue;
//            if (i > 0 && (nums[i] == nums[i - 1])&&!used[i - 1]) continue;
            path.add(nums[i]);
            if (depth == 0) {
                Boolean top = topUsed.get(nums[i]);
                if (top != null && top) {
                    continue;
                } else {
                    topUsed.put(nums[i], true);
                }
            }
            booleanMap.put(nums[i], true);
            usedVertical.put(i, true);
            permuteUniqueRecur(nums, depth + 1, path, total, used, usedVertical);
            booleanMap.put(nums[i], false);
            usedVertical.put(i, false);
            path.remove(path.size() - 1);
        }
    }

    public void printList(List<List<Integer>> list) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
        for (int i = 0; i < list.size(); i++) {
            buffer.append("[");
            for (int j = 0; j < list.get(i).size(); j++) {
                buffer.append(list.get(i).get(j));
                buffer.append(",");
            }
            buffer.append("]");
        }
        buffer.append("}");
        Log.i(TAG, "printList ->" + buffer.toString());
    }
}
