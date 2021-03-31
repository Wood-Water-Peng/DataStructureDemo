package com.example.myapplication.stack;

import android.text.TextUtils;

import java.util.Stack;

/**
 * @Author jacky.peng
 * @Date 2021/3/27 4:32 PM
 * @Version 1.0
 */
public class StackDemo {
    /**
     * 基本计算器的实现
     * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
     * 输入：s = "1 + 1"
     * 输出：2
     */
    public int calculate(String s) {
        if (TextUtils.isEmpty(s)) return 0;
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int res = 0;//左边已经算出的值
        int sign = 1; //运算符
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                //将已经计算出的值压栈
                stack.push(res);
                stack.push(sign);
                res = 0;
            } else if (Character.isDigit(c)) {
                if (sign > 0) {
                    res += (c - 48);
                } else {
                    res -= (c - 48);
                }
            } else if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1;
            } else if (c == ')') {
                sign = stack.pop();
                Integer pop = stack.pop();
                if (sign > 0) {
                    res = pop + res;
                } else {
                    res = pop - res;
                }
            }
        }
        return res;
    }

    private int fetchOperationNum(String s, int start) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < s.length(); i++) {
            char c = sb.charAt(i);
            if (c >= 80 && c <= 89) {
                sb.append(c);
            }
        }
        return Integer.parseInt(sb.toString());
    }

}
