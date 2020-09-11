package com.example.myapplication;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
    private static final String TAG = "StringUtil";

    //打印出字符组成的所有单词集合
    //例如输入a、b、c，则输出有abc,acb,bac,bca,cab,cba
    //思路：递归  将单词的可能性看做第一个字符和剩余字符的组成
    public static void printAllCharWords(char[] sequences, int startPosition) {
        if (sequences == null) {
            return;
        }

        if (startPosition == sequences.length - 1) {
            //递归终止条件
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < sequences.length; i++) {
                sb.append(sequences[i]);
            }
            Log.i(TAG, "printAllCharWords->" + sb.toString());
        } else {
            //将startPosition处的char和后面的一一交换
            for (int i = startPosition; i < sequences.length; i++) {
                char temp = sequences[startPosition];
                sequences[startPosition] = sequences[i];
                sequences[i] = temp;

                printAllCharWords(sequences, startPosition + 1);
                //打印完之后再将字符交换
                temp = sequences[startPosition];
                sequences[startPosition] = sequences[i];
                sequences[i] = temp;
            }
        }
    }

    /**
     * 打印出该字符串的所有组合
     *
     * @param str "abc"
     *            <p>
     *            输出 [a,b,c] [a,c,b] [b,a,c] [b,c,a] [c,a,b] [c,b,a]
     *            <p>
     *            思路:递归
     */
    public void printStringCombinations(String str) {
        char[] chars = new char[str.length()];
        List list = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            chars[i] = str.charAt(i);
        }
        printStringCombinationsRecur(chars, list, 0);
        int size = list.size();
    }

    /**
     * @param str
     * @param list
     * @param index 需要固定的index
     */
    private void printStringCombinationsRecur(char[] str, List list, int index) {
        //递归的退出条件
        if (index == str.length - 1) {
            //组成了一个string
            list.add(generateString(str));
            return;
        }
        //处理重复元素
        List set = new ArrayList<>();
        for (int i = index; i < str.length; i++) {
            if (set.contains(str[i])) continue;
            set.add(str[i]);
            char temp = str[index];
            str[index] = str[i];
            str[i] = temp;

            printStringCombinationsRecur(str, list, index + 1);
            //恢复之前的交换
            temp = str[index];
            str[index] = str[i];
            str[i] = temp;
        }
    }

    private String generateString(char[] str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length; i++) {
            sb.append(str[i]);
        }
        return sb.toString();
    }

    /**
     * 将字符串数组中的空格替换为##
     *
     * @param data
     */
    public String replaceStringSpace(String data) {
        char[] str = new char[data.length()];
        for (int i = 0; i < data.length(); i++) {
            str[i] = data.charAt(i);
        }
        int spaceCount = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == ' ') {
                spaceCount++;
            }
        }
        char[] newChar = new char[str.length + spaceCount];
        int j = newChar.length - 1;
        for (int i = str.length - 1; i >= 0; i--) {
            if (str[i] == ' ') {
                newChar[j] = '#';
                j--;
                newChar[j] = '#';
                j--;
            } else {
                newChar[j] = str[i];
                j--;
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < newChar.length; i++) {
            builder.append(newChar[i]);
        }
        return builder.toString();
    }
}
