package com.example.myapplication;

import android.nfc.Tag;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 数字
 */
public class NumberUtil {
    private static final String TAG = "NumberUtil";

    /**
     * @param nums 无重复数字
     * @return 输入一个数字，返回他的全排列
     * 思路:回溯算法，每一个节点都知道自己的可能路径
     */
    public LinkedList<List<Integer>> fullArrange(int[] nums) {

        LinkedList<List<Integer>> trackList = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();
        backTrack(nums, track, trackList);
        return trackList;
    }

    /**
     * @param nums  需要被全排列的数据
     * @param track 当前的路径
     *              递归终止条件:每一个数字都出现在了路径上
     */
    private void backTrack(@NonNull int[] nums, @NonNull LinkedList<Integer> track, @NonNull LinkedList<List<Integer>> trackList) {
        if (nums.length == track.size()) {
            //需要排列的数字已经全部出现在了路径中
            trackList.add(new LinkedList(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (track.contains(num))
                continue;
            // 做选择
            track.add(num);
            //此时已经生成了一个路径节点，继续递归，寻找下一个节点
            backTrack(nums, track, trackList);
            // 取消选择
            track.removeLast();
        }

    }

    /**
     * N皇后的问题
     * 给你一个 N×N 的棋盘，让你放置 N 个皇后，使得它们不能互相攻击。
     * PS：皇后可以攻击同一行、同一列、左上左下右上右下四个方向的任意单位。
     *
     * @param size 皇后之间不重复
     * @return 所有可能位置的链表组合
     */
    public LinkedList<int[][]> queueArrange(int size) {
        LinkedList<int[][]> list = new LinkedList<>();
        //初始化棋盘
        if (size < 4) {
            //无解
        } else {
            int[][] board = new int[size][size];
            putAllQueues(board, 0, list);
        }
        return list;
    }

    private void putAllQueues(int[][] board, int row, LinkedList<int[][]> list) {
        if (row == board.length) {
            //已经放置到最后一行，结束这次放置
            StringBuilder sb = new StringBuilder();
            sb.append("\n");
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < row; j++) {
                    sb.append(board[i][j] + " ");
                }
                sb.append("\n");
            }
            Log.i(TAG, sb.toString());
            return;
        }

        for (int i = 0; i < board.length; i++) {
            if (isQueueValidate(board, row, i)) {
                board[row][i] = 1;
                putAllQueues(board, row + 1, list);
                //移除之前的放置
                board[row][i] = 0;
            }
            //当后续的列无法满足条件时，函数在这里返回
        }
    }


    private boolean isQueueValidate(int[][] board, int row, int col) {
        //检测左上角
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        //检测右上角
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        //检测同一列
        for (int j = row - 1; j >= 0; j--) {
            if (board[j][col] == 1) {
                return false;
            }
        }
        return true;
    }


    /**
     * 凑硬币问题
     * <p>
     * 给定k种面值的硬币，分别为c1、c2...ck，每种硬币的数量无线，再给定总金额amount
     * 问至少需要几枚硬币凑出这个金额，如果不可能返回-1
     * 比如被顶
     */
    public int coinArrange(int[] coins, int target) {
        Map<Integer, Integer> memo = new HashMap<>();
        return findBest(coins, target, memo);
    }

    private int findBest(int[] coins, int target, Map<Integer, Integer> memo) {
        if (memo.containsKey(target)) return memo.get(target);
        if (target == 0) return 0;
        if (target < 0) return -1;
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int best = findBest(coins, target - coins[i], memo);
            if (best == -1) continue;
            result = Math.min(result, best + 1);

        }
        //此时已经找到了target面值所需的最小数量
        memo.put(target, result);
        return result;
    }


    /**
     * @param nums   非负整数集合
     * @param target 目标数
     *               有两个符号+、-，对于数组的任意一个数字，都可以选择其中一个
     *               返回可以使最终数组和为目标数S的所有方法数
     *               <p>
     *               思路一：回溯算法
     */
    public void findAllMethods(int[] nums, int target) {
        HashMap<String, Integer> memo = new HashMap<>();
        backTrackMethods(nums, new LinkedList<String>(), 0, target, 0, memo);
    }

    /**
     * @param nums
     * @param index
     */
    private int backTrackMethods(int[] nums, LinkedList<String> list, int index, int target, int sum, HashMap<String, Integer> memo) {
        if (nums.length == index) {
            //已经选到了最后一个
            if (target == sum) {
                //正好满足条件，等式成立
            } else {
//                list.clear();
            }
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                builder.append(list.get(i));
            }
            Log.i(TAG, builder.toString());
            return sum;
        }
        String key = index + "-" + sum;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        sum -= nums[index];
        list.add("-" + nums[index]);
        backTrackMethods(nums, list, index + 1, target, sum, memo);
        sum += nums[index];
        list.removeLast();

        sum += nums[index];
        list.add("+" + nums[index]);
        backTrackMethods(nums, list, index + 1, target, sum, memo);
        sum -= nums[index];
        list.removeLast();
        memo.put(key, sum);
        return sum;
    }

    /**
     * @param arr
     * @return 和最大的连续子数组
     * 动态规划解法
     */


    private int[] dpMaxSubArray(int[] arr, int start, int end, int index, int sum) {
        if (index == arr.length - 1) {
            //已经遍历到结尾
            int[] dp = new int[]{end - start};
            for (int i = start; i < end; i++) {
                dp[i - start] = arr[i];
            }
            return dp;
        }

        if (sum + arr[index] < sum) {
            //清除之前的数组
            start = index;
            end = start + 1;
            sum = arr[index];
        } else {
            end++;
        }
        return dpMaxSubArray(arr, start, end, index + 1, sum);
    }

    /**
     * @param arr    非负整数集合
     * @param target 目标数
     *               有两个符号+、-，对于数组的任意一个数字，都可以选择其中一个
     *               返回可以使最终数组和为目标数S的所有方法数
     *               <p>
     *               思路一：回溯算法
     *               <p>
     *               1.针对数组中的每一个数，我可以选择加或者减，做完选择之后，会有一个结果
     *               2.每次选择完的结果，
     */

    public LinkedList<String> commonBackTrack(int[] arr, int target) {
        LinkedList<String> linkedList = new LinkedList<>();
        LinkedList<String> correctPath = new LinkedList<>();
        LinkedList<String> invalidatePath = new LinkedList<>();
        commonBackTrackRecur(arr, 0, target, 0, linkedList, correctPath, invalidatePath);
        for (int i = 0; i < invalidatePath.size(); i++) {
            Log.i(TAG, invalidatePath.get(i));
        }
        return linkedList;
    }

    /**
     * @param arr
     * @param sum
     * @param target
     * @param index
     * @param list
     * @return
     */
    private void commonBackTrackRecur(int[] arr, int sum, int target, int index, LinkedList<String> list, LinkedList<String> correctPath, LinkedList<String> invalidatePath) {
        if (index == arr.length) {
            //已经到达最后一个元素
            StringBuilder builder = new StringBuilder();
            if (sum == target) {
                //满足条件
                list.add("=" + sum);
                for (int i = 0; i < list.size(); i++) {
                    builder.append(list.get(i));
                }
                correctPath.add(builder.toString());
            } else {
                list.add("-----无效");
                for (int i = 0; i < list.size(); i++) {
                    builder.append(list.get(i));
                }
                invalidatePath.add(builder.toString());
            }
            list.removeLast();
            return;
        }
        //做选择
        //选择一：  减号
        sum -= arr[index];
        list.add("-" + arr[index]);
        commonBackTrackRecur(arr, sum, target, index + 1, list, correctPath, invalidatePath);

        //回溯上次的操作
        list.removeLast();
        sum += arr[index];

        //选择二: 加号
        list.add("+" + arr[index]);
        sum += arr[index];
        commonBackTrackRecur(arr, sum, target, index + 1, list, correctPath, invalidatePath);
        list.removeLast();
    }


    /**
     * @param arr {1, 2, 1, -3,-4,-5,3,10}
     * @return 最大连续子数组  {1, 2, 1, -3,-4,-5,3,10}
     */
    public void maxContinuousSubArray(int[] arr) {
        int[] max = new int[]{0, 0};
        int[] max_sum = new int[]{0};
        int maxSum = maxContinuousSubArray(arr, 0, 0, max_sum, max);
        Log.i(TAG, "maxContinuousSubArray startIndex->" + max[0] + "---endIndex:" + max[1]);
    }

    /**
     * @param arr{1, 2, 1, -3,-4,-5,3,10}
     * @param index
     * @param sum
     * @return 当前状态的最大值
     */
    private int maxContinuousSubArray(int[] arr, int index, int sum, int[] max_sum, int[] max) {
        if (index == arr.length) {
            //超出数组范围

            return sum;
        }

        if (sum <= 0) {
            sum = arr[index];
            max[0] = index;
        } else {
            sum += arr[index];
        }
        max_sum[0] = Math.max(sum, max_sum[0]);
//        Log.i(TAG, "max_sum ->" + max_sum);
        int recurSum = maxContinuousSubArray(arr, index + 1, sum, max_sum, max);
        if (sum >= max_sum[0]) {
            max[1] = index;
        }
        return sum;
    }

    public int minCostClimbingStairs(int[] cost) {
        int[] maxCost1 = new int[]{Integer.MAX_VALUE};
        int[] maxCost2 = new int[]{Integer.MAX_VALUE};
        minCostClimbingStairsRecur(cost, 0, 0, maxCost1);
        minCostClimbingStairsRecur(cost, 1, 0, maxCost2);
        int min = Math.min(maxCost1[0], maxCost2[0]);
        Log.i(TAG, "minCostClimbingStairs miniCost->" + min);
        return min;
    }

    private void minCostClimbingStairsRecur(int[] cost, int index, int sum, int[] maxCost) {
        if (index >= cost.length) {
            //已经到达尾结点
            maxCost[0] = Math.min(sum, maxCost[0]);
            return;
        }
        sum += cost[index];
        minCostClimbingStairsRecur(cost, index + 1, sum, maxCost);
        minCostClimbingStairsRecur(cost, index + 2, sum, maxCost);
    }

    /**
     * 时间和空间复杂度最优的解法
     * dp[i]代表到节点i所需要的最小花费
     * dp[i]=min(dp[i-1]+dp[i-2])+cost[i];
     *
     * @return
     */
    public int minCostClimbingStairsBest(int[] cost) {
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }
        return Math.min(dp[dp.length - 1], dp[dp.length - 2]);
    }

    /**
     * 买卖股票问题
     *
     * @param prices {7,1,5,3,6,4}
     * @return 假设你最多只能做一笔交易(买卖)
     * 买必须在卖之前
     * <p>
     * 思路一:列出所有的可能
     * 在每一个节点，他都可以做选择，我们列出他所有的选择，求出最大的值
     * <p>
     * 思路二：dp
     * dp[i]表示第i天的最大利润
     * dp[i]=max(dp[i-1],price[i]-minPrice);
     */
    public int maxProfit(int[] prices) {
        int[] dp = new int[prices.length];
        int minPrice = prices[0];
        dp[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            minPrice = Math.min(prices[i], minPrice);
            dp[i] = Math.max(dp[i - 1], prices[i] - minPrice);
        }
        return dp[0];
    }

    /**
     * 相比maxProfit，多了一个条件，即买入之后必须休息一天才可以卖出
     *
     * @param prices
     * @return
     */
    public int maxProfitII(int[] prices) {
        int[] dp = new int[prices.length];
        int minPrice = prices[0];
        dp[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            minPrice = Math.min(prices[i], minPrice);
            dp[i] = Math.max(dp[i - 1], prices[i] - minPrice);
        }
        return dp[0];
    }


    /**
     * 非相邻的数组和最大值
     *
     * @param nums {2,7,9,3,1}
     * @return {2，9，1}
     * dp[i]表示在当前节点的最大值
     * dp[i]=dp[i-2]+num[i];
     */
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return 1;
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], dp[0]);
        int sum = 0;
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return Math.max(dp[nums.length - 1], dp[nums.length - 2]);
    }


    /**
     * @param houses
     * @return 房子数量-对应的颜色花费
     * {{1,2,3},{4,5,6}};
     * dp[i][j]  代表i个房子用颜色j粉刷需要的最小花费
     * dp[i][j]=cost[i][j]+上一次的最小花费
     * dp[i][j]=cost[i][j]+min(dp[i-1][(j+1)%3],dp[i-1][(j+2)%3])
     */
    public int paintHouse(int[][] houses) {
        if (houses.length == 0) return 0;
        if (houses.length == 1) {
            int minCost = 0;
            for (int i = 0; i < houses[0].length; i++) {
                minCost = Math.min(houses[0][i], minCost);
            }
            return minCost;
        }
        int[][] dp = new int[houses.length][3];
        dp[0] = houses[0].clone();
        for (int i = 1; i < houses.length; i++) {
            if (houses[i].length != 3) throw new IllegalArgumentException("color nums must be 3");
            for (int j = 0; j < houses[i].length; j++) {
                dp[i][j] = houses[i][j] + Math.min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3]);
            }
        }
        return Math.min(dp[dp.length - 1][2], Math.min(dp[dp.length - 1][0], dp[dp.length - 1][1]));
    }

    /**
     * @param obstacleGrid
     * @return dp[i][j]=dp[i-1][j]+dp[i][j-1]+1
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        for (int i = 0; i < obstacleGrid.length; i++) {
            for (int j = 0; j < obstacleGrid[i].length; j++) {
                if (obstacleGrid[i][j] == 1) {
                    //障碍
                    dp[i][j] = 0;
                    continue;
                } else {
                    if (i == 0 && j == 0) {
                        //起始点
                        dp[i][j] = 1;
                    } else if (i == 0) {
                        //第一排
                        dp[i][j] = dp[i][j - 1];
                    } else if (j == 0) {
                        //第一列
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                    }
                }
            }
        }
        return 0;
    }

    /**
     * @param grid
     * @return dp[i][j]=min(dp[i-1][j],dp[i][j-1])+cost[i][j]
     */
    public int minPathSum(int[][] grid) {
        if (grid.length == 0) return 0;
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[0][0];
                } else if (i == 0) {
                    //第一排
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    //第一列
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }
        return dp[grid.length - 1][dp[0].length - 1];
    }


    public int numDecodings(String s) {
        if (s.isEmpty()) return 0;
        int[] dp = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (i == 0) {
                if (s.charAt(i) == '0') return 0;
                dp[i] = 1;
            } else {
                if ((s.charAt(i) == '0')) {
                    Integer integer = Integer.valueOf(s.substring(i - 1, i));
                    if (integer == 1 || integer == 2) {
                        if (i == 1) {
                            dp[i] = dp[i - 1];
                        } else {
                            dp[i] = dp[i - 2];
                        }

                    } else {
                        return 0;
                    }
                } else {
                    dp[i] = dp[i - 1];
                    if (Integer.valueOf(s.subSequence(i - 1, i + 1).toString()) < 27) {
                        if (i == 1) {
                            dp[i]++;
                        } else {
                            if (Integer.valueOf(s.substring(i - 1, i)) == 0) {

                            } else {
                                dp[i] += dp[i - 2];
                            }
                        }
                    }
                }

            }
        }
        return dp[dp.length - 1];
    }


    /**
     * @param matrix
     * @return dp[i]表示当前节点对应的matrix的最大值
     * 1 0 1 0 0
     * 1 0 1 1 1
     * 1 1 1 1 1
     * 1 0 1 1 0
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 && j == 0) {
                        dp[i][j] = 1;
                    } else if (i == 0) {
                        dp[i][j] = 1;
                    } else if (j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;

                    }
                    max = Math.max(dp[i][j], max);
                }
            }
        }
        return max * max;
    }

    /**
     * @param nums
     * @return 最长的递增子数组的长度
     * [10,9,2,5,3,7,101,18]
     * [2,3,7,101]    4
     * dp[i] 表示以 nums[i] 结尾的「上升子序列」的长度。
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int max = 0;
        int lastMaxIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                dp[i] = 1;
            } else {
                if (nums[i] > nums[lastMaxIndex]) {
                    //大于上一个元素，直接+1
                    dp[i] = dp[lastMaxIndex] + 1;
                } else {
                    boolean flag = false;
                    for (int j = i - 1; j >= 0; j--) {
                        if (nums[i] > nums[j]) {
                            dp[i] = dp[j] + 1;
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        dp[i] = 1;
                    }
                }
            }
            if (dp[i] > max) {
                lastMaxIndex = i;
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    /**
     * 给定一定面额的硬币(假设数量不限制)，要求拼凑出一定面额的最小硬币数量
     * 如[1,2,5,10]   要求拼凑出50，则最少需要5枚硬币
     *
     * @param coins
     * @param amount
     * @return 思路:动态规划
     * 1.确定状态方程
     * f(s)=f(s-c)+1
     * f(s)表示拼凑出金额为s所需要的最少硬币数量，c表示最后一枚硬币
     */
    public int coinChange(int[] coins, int amount) {
        if (amount < 0) return -1;
        if (amount == 0) return 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int count = coinChange(coins, amount - coins[i]);
            if (count >= 0 && count < min) {
                min = count + 1;
            }
        }
        return min;
    }

    /**
     * 给你一组非负整数
     * 把数组排成最小的数,每一个单项不能拆开
     *
     * @param arr [1,3,12]
     * @return 1123
     * 1.定义一种新的大小规则 a,b  a+b<b+a,则a<b
     * 2.针对字符串重写快速排序
     */
    public String arrangeArrayToMiniNumber(int[] arr) {
        //变成字符串数组
        String[] strArr = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            strArr[i] = arr[i] + "";
        }
        fastSortStr(strArr, 0, strArr.length - 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            sb.append(strArr[i]);
        }
        return sb.toString();
    }

    private void fastSortStr(String[] str, int left, int right) {
        //选定一个锚点,确保锚点左边的比他小，锚点右边的比他大
        if (left >= right) return;
        String mid = str[right];
        int midInt = left;
        int i = left;
        int j = right;
        while (i < j) {
            //在右边找到一个小的
            while (compareStr(str[j], mid) >= 0 && i < j) {
                j--;
            }
            //在左边找到一个大的
            while (compareStr(str[i], mid) <= 0 && i < j) {
                i++;
            }
            //将左边的大的和右边的小的，交换
            String tmp = str[i];
            str[i] = str[j];
            str[j] = tmp;

        }
        str[right] = str[midInt];
        str[midInt] = mid;
        midInt = i;
        fastSortStr(str, left, midInt - 1);
        fastSortStr(str, midInt + 1, right);


    }

    private int compareStr(String a, String b) {
        Integer ab = Integer.valueOf(a + b);
        Integer ba = Integer.valueOf(b + a);
        if (ab > ba) {
            return 1;
        } else if (ab < ba) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * 找到数组中出现次数超过一半的数字
     *
     * @param arr [1,2,3,2,2,2,5]
     * @return 2
     * <p>
     * 思路一：
     * <p>
     * 先给数组排序，如果一个数出现的次数超过一半，那么，排完序后中间的数字肯定是那个数
     * 时间复杂度 NLogN
     * <p>
     * 思路二：
     * <p>
     * 根据思路一的思路，我们其实不用给数组排序，我们只需要找到数组中第k(k=n/2)大的数字就行
     * 然后再遍历统计这个数字在数组中出现的次数
     * step1:找到数组中第k大的数字
     * step2:遍历数组，计算出该数字出现的次数
     */
    public int findHalfCountNumInArray(int[] arr) {

        int partition = partition(arr, 0, arr.length - 1);
        int start = 0;
        int end = arr.length - 1;
        int num = arr.length >> 1;//中位数
        while (num != partition) {
            if (num > partition) {
                //右边找
                start = partition + 1;
                partition = partition(arr, start, end);
            } else {
                //左边找
                end = partition - 1;
                partition = partition(arr, start, end);
            }
        }
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[partition] == arr[i]) {
                count++;
            }
        }
        if (count > arr.length / 2) {
            return arr[partition];
        } else {
            return -1;
        }
    }


    private int partition(int[] arr, int start, int end) {
        //扫描，交换并返回中间数的索引
        int pivot = arr[end];
        int middle = start - 1;  //小于基准值的坑位
        for (int i = start; i < end; i++) {
            //从左到右遍历，找到小的，交换到middle的位置
            if (arr[i] <= pivot) {
                middle++;
                swap(arr, i, middle);
            }
        }

        //因为之前都是把小的交换到左边，所以middle往左的都比pivot小
        swap(arr, middle + 1, end);
        return middle + 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * @param arr
     * @return 最小的k个数
     * 思路一：
     * 先排序，找到前面的k个
     * 时间复杂度：NLogN
     * <p>
     * 思路二：
     * 不用排序，利用快排思想，但是需要改变原数组
     * <p>
     * 思路三：
     * 最小堆，需要新的空间，不改变原数组，适合处理海量数据
     */
    public int[] minKNum(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            return new int[0];
        }
        int start = 0;
        int end = arr.length - 1;
        int partition = partition(arr, start, end);
        while (partition != k) {
            if (k < partition) {
                end = partition - 1;
                partition = partition(arr, start, end);
            } else {
                start = partition + 1;
                partition = partition(arr, start, end);
            }
        }
        if (partition == k) {
            //找到了这个数
            int[] result = new int[k];
            for (int i = 0; i < k; i++) {
                result[i] = arr[i];
            }
            return result;
        } else {
            return new int[0];
        }
    }

    /**
     * @param arr
     * @param k
     * @return 最小堆思想
     * <p>
     * 当输入的数据特别大时，我们维护一个总量为k的最小堆
     */
    public int[] minKNum2(int[] arr, int k) {
        return new int[0];
    }


    /**
     * 三数之和
     * <p>
     * arr [-1, 0, 1, 2, -1, -4]，
     * <p>
     * 找到arr中和为0的三个元素的组合
     * 要求：arr中的同一个元素不能多次使用
     * <p>
     * 思路一：
     * <p>
     * 1.先通过遍历找到2个数，时间复杂度为O(n²)，
     * 2.然后使用一个哈希表去找到第三个元素，时间复杂度为O(1)，空间复杂度为O(n)
     * <p>
     * <p>
     * 思路二：
     * <p>
     * 1.先排序，时间复杂度NLogN
     * 2.遍历+双指针，找出所有符合条件的组合，注意去重 时间复杂度为O(n)
     * <p>
     * NLogN+O(n)=NLogN
     */
    public List<List<Integer>> threeNumSum(int[] arr) {
        List<List<Integer>> total = new ArrayList<>();
        if (arr.length < 3) return total;
        quickSort(arr);
        if (arr[0] > 0 || arr[arr.length - 1] < 0) return total;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) break;
            if (i > 0 && arr[i] == arr[i - 1]) continue; //去重
            int left = i + 1;
            int right = arr.length - 1;
            //以i为c位，左右两边向i靠齐
            //此时，左边的最小，右边的最大
            while (left < right) {
                int sum = arr[left] + arr[right] + arr[i];
                if (sum == 0) {
                    List<Integer> list = new ArrayList();
                    list.add(arr[left]);
                    list.add(arr[i]);
                    list.add(arr[right]);
                    total.add(list);
                    //去重
                    while (left < right && arr[left] == arr[left + 1]) left++;
                    while (left < right && arr[right] == arr[right - 1]) right--;
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return total;
    }

    private void quickSort(int[] arr) {
        doQuickSort(arr, 0, arr.length - 1);
    }

    private void doQuickSort(int[] arr, int start, int end) {
        if (start < end) {
            int partition = partition(arr, start, end);
            doQuickSort(arr, start, partition - 1);
            doQuickSort(arr, partition + 1, end);
        }
    }
}
