package com.example.myapplication.dp;

/**
 * 动态规划相关
 */
public class Dp01 {
    /**
     * https://leetcode-cn.com/problems/longest-palindromic-substring/
     * 最长回文串
     *
     * @param s
     * @return 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     * 思路：动态规划
     * <p>
     * dp[i][j]=dp[i+1][j-1]&&(s[i]==s[j])
     * 注意：考虑到特殊情况
     * s=1
     * s=2  s[i]==s[i+1]
     * <p>
     * 从小到大扩展，当达到字符串的长度时，返回结果
     */
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String result = "";
        //len表示回文的长度
        for (int len = 0; len < n; len++) {
            //i和j分别是回文的左右边界
            for (int i = 0; i + len < n; i++) {
                int j = i + len;

                //处理特殊情况
                if (len == 0) {
                    dp[i][i] = true;
                } else if (len == 1) {
                    dp[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
                } else {
                    dp[i][j] = dp[i + 1][j - 1] && ((s.charAt(i) == s.charAt(j)));
                }
                if (dp[i][j] && (len + 1) > result.length()) {
                    result = s.substring(i, j + 1);
                }
            }
        }
        return result;
    }
}
