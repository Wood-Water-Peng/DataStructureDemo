package com.example.myapplication.string;

/**
 * @Author jacky.peng
 * @Date 2021/3/29 2:01 PM
 * @Version 1.0
 */


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 返回 s 中涵盖 t 所有字符的最小子串
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * <p>
 * ab
 * <p>
 * b
 * <p>
 * 滑动窗口
 * <p>
 * 1.确定初始的最小窗口
 * 2.不满足则扩大窗口    直到满足条件并记录当前窗口的值
 * 3.尝试一直缩小窗口，直到不满足，
 */
public class StringDemo {
    public String minWindow(String s, String t) {
        if (s == null || t == null) return "";
        if (s.length() < t.length()) return "";
        HashMap<Character, Integer> subMap = new HashMap<>();
        HashMap<Character, Integer> windowMap = new HashMap<>();
        String result = "";
        //记录子窗口
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            Integer integer = subMap.get(c);
            if (integer == null || integer == 0) {
                subMap.put(c, 1);
            } else {
                subMap.put(c, integer + 1);
            }
        }

        //[)  闭区间
        int start = 0;
        int end = 0;

        //将右边的字符加入窗口
        while (end < s.length()) {
            //更新窗口数据
            char c = s.charAt(end);
            if (windowMap.containsKey(c)) {
                windowMap.put(c, windowMap.get(c) + 1);
            } else {
                windowMap.put(c, 1);
            }
            end++;
            //找到一个可行解
            if (isWindowAvailable(windowMap, subMap)) {
                //更新结果
                String substring = s.substring(start, end);
                if (result.equals("")) {
                    result = substring;
                } else if (result.length() > substring.length()) {
                    result = substring;

                }
                if (result.length() == t.length()) {
                    return result;
                }

                //尝试优化这个可行解
                while (end - start > t.length()) {
                    char charAt = s.charAt(start);
                    Integer integer = windowMap.get(charAt);
                    if (integer > 0) {
                        windowMap.put(charAt, integer - 1);
                    }
                    start++;
                    //优化成功，刷新可行解
                    if (isWindowAvailable(windowMap, subMap)) {
                        substring = s.substring(start, end);
                        if (result.equals("")) {
                            result = substring;
                        } else if (result.length() > substring.length()) {
                            result = substring;

                        }
                        if (result.length() == t.length()) {
                            return result;
                        }
                    } else {
                        //退出优化过程
                        break;
                    }
                }
            }

        }
        return result;

    }

    /**
     * subMap中的每一项都能在windowMap中命中
     *
     * @param windowMap
     * @param subMap
     * @return
     */
    private boolean isWindowAvailable(HashMap<Character, Integer> windowMap, HashMap<Character, Integer> subMap) {
        Iterator<Map.Entry<Character, Integer>> iterator = subMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Character, Integer> next = iterator.next();
            Integer integer = windowMap.get(next.getKey());
            if (integer == null || integer < subMap.get(next.getKey())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
     * 输入: s1 = "ab" s2 = "eidbaooo"
     * 输出: True
     * 解释: s2 包含 s1 的排列之一 ("ba").
     *
     * @param s1
     * @param s2
     * @return 关键:怎么判断该元素是否应该纳入窗口？
     * 纳入窗口中的元素存在于目标总，且数量不超过
     * 在什么时候返回？
     * <p>
     * 窗口中的元素值数量和目标相同
     * <p>
     * 窗口
     */
    public boolean checkInclusion(String s1, String s2) {
        HashMap<Character, Integer> subMap = new HashMap<>();
        HashMap<Character, Integer> windowMap = new HashMap<>();
        String result = "";
        //记录子窗口
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            Integer integer = subMap.get(c);
            if (integer == null || integer == 0) {
                subMap.put(c, 1);
            } else {
                subMap.put(c, integer + 1);
            }
        }
        int start = 0, end = 0;
        while (start < s2.length() && end < s2.length()) {
            //尝试着找到符合条件的序列
            //更新窗口数据
            char c = s2.charAt(start);
            if (s1.contains(c + "")) {
                //找到了窗口的起点
                end = start + 1;
                while (end < s2.length()) {
                    c = s2.charAt(end);
                    if (s1.contains(c + "")) {
                        end++;
                    } else {

                    }
                }
            } else {
                start++;
            }
            if (isWindowAvailable(windowMap, subMap)) {
                return true;
            }
        }
        return false;
    }

}
