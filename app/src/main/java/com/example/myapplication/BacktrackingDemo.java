package com.example.myapplication;

//回溯算法经典例题


import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BacktrackingDemo<T> {
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
        CollectionUtil collectionUtil = new CollectionUtil();
        collectionUtil.fastSort(nums);
        Boolean[] used = new Boolean[nums.length];
        permuteUniqueRecur(nums, 0, path, total, used, usedVertical);
        printList(total);
        return total;
    }


    private void permuteUniqueRecur(int[] nums, int depth, List<Integer> path, List<List<Integer>> total, Boolean[] used, Map<Integer, Boolean> usedVertical) {
        if (depth >= nums.length) {
            total.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            Boolean aBoolean = usedVertical.get(i);
            if (!path.isEmpty()) {
                if (aBoolean != null && aBoolean) continue;
            }
            //横向剪枝，
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            path.add(nums[i]);
            if (depth == 0) {
                Boolean top = topUsed.get(nums[i]);
                if (top != null && top) {
                    continue;
                } else {
                    topUsed.put(nums[i], true);
                }
            }
            used[i] = true;
            usedVertical.put(i, true);
            permuteUniqueRecur(nums, depth + 1, path, total, used, usedVertical);
            used[i] = false;
            usedVertical.put(i, false);
            path.remove(path.size() - 1);
        }
    }


    /**
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * <p>
     * candidates 中的数字可以无限制重复被选取。
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> total = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return total;
        }
        CollectionUtil collectionUtil = new CollectionUtil();
        collectionUtil.fastSort(nums);
        combinationSumRecur(nums, target, 0, new ArrayList<Integer>(), total);
        printList(total);
        return total;
    }

    private void combinationSumRecur(int[] candidates, int rest, int begin, List<Integer> path, List<List<Integer>> total) {
        if (rest == 0) {
            //满足条件
            total.add(new ArrayList<Integer>(path));
            return;
        } else if (rest < 0) {
            //超过了和
            return;
        }

        for (int i = begin; i < candidates.length; i++) {
            //同一层剪枝
//            if (i > begin && candidates[i - 1] == candidates[i]) continue;
            path.add(candidates[i]);
            combinationSumRecur(candidates, rest - candidates[i], begin + 1, path, total);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * <p>
     * candidates 中的每个数字在每个组合中只能使用一次。
     * <p>
     * 说明：
     * <p>
     * 所有数字（包括目标数）都是正整数。
     * 解集不能包含重复的组合。 
     *
     * @param nums
     * @param target
     * @return 示例 1:
     * *
     * * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
     * * 所求解集为:
     * * [
     * *   [1, 7],
     * *   [1, 2, 5],
     * *   [2, 6],
     * *   [1, 1, 6]
     * * ]
     * <p>
     * 输入: candidates = [2,5,2,1,2], target = 5,
     * 所求解集为:
     * [
     * [1,2,2],
     * [5]
     * ]
     */

    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> total = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return total;
        }
        CollectionUtil collectionUtil = new CollectionUtil();
        collectionUtil.fastSort(nums);
        combinationSum2Recur(nums, target, 0, new ArrayList<Integer>(), total);
        printList(total);
        return total;
    }


    private void combinationSum2Recur(int[] candidates, int rest, int begin, List<Integer> path, List<List<Integer>> total) {
        if (rest == 0) {
            //满足条件
            total.add(new ArrayList<Integer>(path));
            return;
        } else if (rest < 0) {
            //超过了和
            return;
        }

        for (int i = begin; i < candidates.length; i++) {
            //同一层剪枝
            if (i > begin && candidates[i - 1] == candidates[i]) continue;
            path.add(candidates[i]);
            combinationSumRecur(candidates, rest - candidates[i], i + 1, path, total);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     *
     * @param n
     * @param k
     * @return 输入: n = 4, k = 2
     * 输出:
     * [
     * [2,4],
     * [3,4],
     * [2,3],
     * [1,2],
     * [1,3],
     * [1,4],
     * ]
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> total = new ArrayList<>();
        if (k > n || n < 0) {
            return total;
        }

        combineRecur(n, k, 1, new ArrayList<Integer>(), total);
        printList(total);
        return total;
    }

    private void combineRecur(int n, int rest, int begin, List<Integer> path, List<List<Integer>> total) {
        if (rest == 0) {
            //满足条件
            total.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i <= n; i++) {
            path.add(i);
            combineRecur(n, rest - 1, i + 1, path, total);
            path.remove(path.size() - 1);
        }


    }

    /**
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     * <p>
     * 说明：解集不能包含重复的子集。
     * <p>
     * 输入: nums = [1,2,3]
     * 输出:
     * [
     * [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> total = new ArrayList<>();
        if (nums.length == 0) {
            return total;
        }

        subsetsRecur(nums, 0, new ArrayList<Integer>(), total);
        printList(total);
        return total;
    }

    private void subsetsRecur(int[] nums, int begin, List<Integer> path, List<List<Integer>> total) {
        if (begin == nums.length) {
            //满足条件
            total.add(new ArrayList<>(path));
            return;
        }
        total.add(new ArrayList<>(path));
        for (int i = begin; i < nums.length; i++) {
            path.add(nums[i]);
            subsetsRecur(nums, i + 1, path, total);
            path.remove(path.size() - 1);
        }


    }


    /**
     * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     * <p>
     * 说明：解集不能包含重复的子集。
     * <p>
     * 示例:
     * <p>
     * 输入: [1,2,2]
     * 输出:
     * [
     * [2],
     * [1],
     * [1,2,2],
     * [2,2],
     * [1,2],
     * []
     * ]
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> total = new ArrayList<>();
        if (nums.length == 0) {
            return total;
        }
        CollectionUtil collectionUtil = new CollectionUtil();
        collectionUtil.fastSort(nums);
        subsets2Recur(nums, 0, new ArrayList<Integer>(), total);
        printList(total);
        return total;
    }

    private void subsets2Recur(int[] nums, int begin, List<Integer> path, List<List<Integer>> total) {
        if (begin == nums.length) {
            //满足条件
            total.add(new ArrayList<>(path));
            return;
        }
        total.add(new ArrayList<>(path));
        for (int i = begin; i < nums.length; i++) {
            if (i > begin && nums[i] == nums[i - 1]) continue;
            path.add(nums[i]);
            subsetsRecur(nums, i + 1, path, total);
            path.remove(path.size() - 1);
        }


    }

    /**
     * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
     * <p>
     * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
     * <p>
     * "123"
     * "132"
     * "213"
     * "231"
     * "312"
     * "321"
     * 给定 n 和 k，返回第 k 个排列。
     * <p>
     * 说明：
     * <p>
     * 给定 n 的范围是 [1, 9]。
     * 给定 k 的范围是[1,  n!]。
     * 示例 1:
     * <p>
     * 输入: n = 3, k = 3
     * 输出: "213"
     *
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
        if (n < 0) return "";
        if (k > getFactorial(n)) return "";

        List<String> total = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        Boolean[] used = new Boolean[n];
        getPermutationRecur(n, k, builder, used, total);
//        printString(result);
        printArray(total);
        return total.get(0);
    }

    int m = 0;
    String result;

    private void getPermutationRecur(int n, int k, StringBuilder builder, Boolean[] used, List<String> total) {
        if (builder.toString().length() == n) {
            total.add(new String(builder.toString()));
            m++;
            if (k == m) {
                result = builder.toString();
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            if (used[i] != null && used[i].booleanValue()) continue;
            builder.append((i + 1));
            used[i] = true;
            getPermutationRecur(n, k, builder, used, total);
            if (k == m) {
                return;
            }
            builder.deleteCharAt(builder.length() - 1);
            used[i] = false;
        }
    }

    /**
     * 获取n的阶乘
     *
     * @param n
     * @return
     */
    private int getFactorial(int n) {
        int m = 0;
        int index = 0;
        int total = 0;
        while (index <= n) {
            if (index == 0) {
                m = 0;
            } else if (index == 1) {
                m = 1;
            } else {
                m = total;
            }
            total = m * index;
            index++;
        }
        return total;
    }

    /**
     * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
     * <p>
     * 有效的 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
     * <p>
     * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效的 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效的 IP 地址。
     *
     * @param s
     * @return <p>
     * 输入：s = "25525511135"
     * 输出：["255.255.11.135","255.255.111.35"]
     * <p>
     * 思路
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> total = new ArrayList<>();
        LinkedList<String> list = new LinkedList<>();
        if (TextUtils.isEmpty(s)) {
            return total;
        }
        if (s.length() < 4 || s.length() > 12) return total;
        restoreIpAddressesRecur(s, 0, 1, list, total);
        printArray(total);
        return total;
    }

    private void restoreIpAddressesRecur(String s, int startPosition, int count, LinkedList<String> list, List<String> total) {

        if (count == 4) {
            String ipSegment = getIpSegment(s, startPosition, s.length());
            if (isIpSegmentValidate(ipSegment)) {
                list.add(ipSegment);
                total.add(convertIpFromList(list));
                list.removeLast();
            }
            return;
        }
        for (int i = 1; i < 4; i++) {
            String ipSegment;
            if (startPosition + i > s.length()) return;
            if (count == 4) {
                ipSegment = getIpSegment(s, startPosition, s.length());
            } else {
                ipSegment = getIpSegment(s, startPosition, startPosition + i);

            }
            if (isIpSegmentValidate(ipSegment)) {
                list.add(ipSegment);
                restoreIpAddressesRecur(s, startPosition + i, count + 1, list, total);
                list.removeLast();
                //此时如果第四段,那么退出，因为第四段必须取剩下所有的
            }
        }


    }

    private String convertIpFromList(LinkedList<String> linkedList) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < linkedList.size(); i++) {
            builder.append(linkedList.get(i));
            if (i != linkedList.size() - 1) {
                builder.append("-");
            }
        }
        return builder.toString();
    }

    private String getIpSegment(String s, int begin, int end) {
        return s.substring(begin, end);
    }

    private boolean isIpSegmentValidate(String s) {
        if (TextUtils.isEmpty(s) || s.length() > 3) return false;
        if (s.length() > 1 && s.startsWith("0")) return false;
        if (Integer.valueOf(s) > 255) return false;
        return true;
    }


    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
     * <p>
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     *
     * @param digits
     * @return <p/>
     * 输入："23"
     * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     */
    public List<String> letterCombinations(String digits) {
        List<String> total = new ArrayList<>();
        if (digits.equals("")) return new ArrayList<>(0);
        for (int i = 0; i < digits.length(); i++) {
            if (digits.charAt(i) < 50 || digits.charAt(i) > 57) {
                return new ArrayList<>(0);
            }
        }
        StringBuilder builder = new StringBuilder();
        letterCombinationsRecur(digits, 0, builder, total);
        printArray(total);
        return total;
    }

    private void letterCombinationsRecur(String digits, int startIndex, StringBuilder path, List<String> total) {
        if (startIndex == digits.length()) {
            total.add(path.toString());
            return;
        }
        int max = 3;
        if (digits.charAt(startIndex) == '7' || digits.charAt(startIndex) == '9') {
            max = 4;
        }
        for (int i = 0; i < max; i++) {
            char c = digits.charAt(startIndex);
            char item = getCharFromDigit(c - 48, i);
            path.append(item);
            letterCombinationsRecur(digits, startIndex + 1, path, total);
            path.deleteCharAt(path.length() - 1);
        }
    }

    //将数字映射到首字符
    private char getCharFromDigit(int digit, int index) {
        if (digit >= 8) {
            return (char) ((digit - 2) * 3 + 98 + index);
        } else {
            return (char) ((digit - 2) * 3 + 97 + index);
        }
    }

    /**
     * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
     *
     * @param str
     * @return </p>
     * </p>
     * 示例：
     * 输入：S = "a1b2"
     * 输出：["a1b2", "a1B2", "A1b2", "A1B2"]
     */
    public List<String> letterCasePermutation(String str) {
        List<String> total = new ArrayList<>();
        if (str.equals("")) return new ArrayList<>(0);
        letterCasePermutationRecur(string2Char(str), 0, total);
        printArray(total);
        return total;
    }

    private void letterCasePermutationRecur(char[] str, int startIndex, List<String> total) {
        if (startIndex == str.length) {
            total.add(char2String(str));
            return;
        }
        char charAt = str[startIndex];
        //判断charAt是否属于字母
        if (charAt >= 65 && charAt <= 90) {
            //大写
            //选择一:直接跳过
            letterCasePermutationRecur(str, startIndex + 1, total);
            //选择二：变小写
            str[startIndex] = (char) (charAt + 32);
            letterCasePermutationRecur(str, startIndex + 1, total);
        } else if (charAt >= 97 && charAt <= 122) {
            //小写
            //选择一:直接跳过
            letterCasePermutationRecur(str, startIndex + 1, total);
            //选择二：变大写
            str[startIndex] = (char) (charAt - 32);
            letterCasePermutationRecur(str, startIndex + 1, total);
        } else {
            //其他字符
            letterCasePermutationRecur(str, startIndex + 1, total);
        }


    }

    private String char2String(char[] list) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.length; i++) {
            builder.append(list[i]);
        }
        return builder.toString();
    }

    private char[] string2Char(String str) {
        char[] list = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            list[i] = str.charAt(i);
        }
        return list;
    }

    /**
     * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     *
     * @param n
     * @return 输入：4
     * 输出：[
     * [".Q..",  // 解法 1
     * "...Q",
     * "Q...",
     * "..Q."],
     * <p>
     * ["..Q.",  // 解法 2
     * "Q...",
     * "...Q",
     * ".Q.."]
     * ]
     * <p>
     * 思路：
     * <p>
     * 回溯法，以行为单位，再以列为单位，逐行放置
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> total = new ArrayList<>();
        List<String> path = new ArrayList<>();
        if (n == 1) {
            path.add("Q");
            total.add(path);
            return total;
        }
        if (n < 3) return total;

        columnFlag = new HashMap<>(n);
        TopLeftFlag = new HashMap<>(n);
        TopRightFlag = new HashMap<>(n);
        //生成一个n*n的棋盘
        int[][] board = new int[n][n];
        solveNQueensRecur(board, 0, path, total);
        printList(total);
        return total;
    }

    //列
    HashMap<Integer, Integer> columnFlag;
    //左上角   行列的差相同
    HashMap<Integer, Integer> TopLeftFlag;
    //右上角   行列和相同
    HashMap<Integer, Integer> TopRightFlag;

    private void solveNQueensRecur(int[][] board, int row, List<String> path, List<List<String>> total) {
        if (row >= board.length) {
            //已经放置完毕,如果在之前行都不满足条件，则已经过滤
            total.add(new ArrayList<String>(path));
            return;
        }
        for (int i = 0; i < board.length; i++) {
            //判断当前列是否有
            if (columnFlag.containsKey(i)) {
                continue;
            } else if (TopLeftFlag.containsKey(row - i)) {
                continue;
            } else if (TopRightFlag.containsKey(row + i)) {
                continue;
            } else {
                //可以放置
                columnFlag.put(i, 1);
                TopLeftFlag.put(row - i, 1);
                TopRightFlag.put(row + i, 1);
                board[row][i] = 1;
                path.add(getStringFromInt(board[row]));
                solveNQueensRecur(board, row + 1, path, total);
                columnFlag.remove(i);
                TopLeftFlag.remove(row - i);
                TopRightFlag.remove(row + i);
                board[row][i] = 0;
                path.remove(path.size() - 1);

            }

        }
    }


    private String getStringFromInt(int[] list) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < list.length; i++) {
            if (list[i] == 1) {
                buffer.append("Q");
            } else {
                buffer.append(".");
            }
        }
        return buffer.toString();
    }

    public <T> void printList(List<List<T>> list) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
        for (int i = 0; i < list.size(); i++) {
            buffer.append("[");
            for (int j = 0; j < list.get(i).size(); j++) {
                buffer.append(list.get(i).get(j));
                buffer.append(",");
            }
            buffer.append("]");
            buffer.append("\n");
        }
        buffer.append("}");
        Log.i(TAG, "printList ->" + buffer.toString());
    }

    public void printArray(List<String> list) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
        for (int i = 0; i < list.size(); i++) {
            buffer.append(list.get(i));
            buffer.append(",");
        }
        buffer.append("}");
        Log.i(TAG, "printArray ->" + buffer.toString());
    }

    public void printString(String str) {
        Log.i(TAG, "printString ->" + str);
    }
}
