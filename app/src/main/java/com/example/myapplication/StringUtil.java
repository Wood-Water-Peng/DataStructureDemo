package com.example.myapplication;

import android.util.Log;

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
}
