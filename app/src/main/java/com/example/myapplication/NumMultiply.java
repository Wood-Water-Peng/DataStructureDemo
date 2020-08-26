package com.example.myapplication;

public class NumMultiply {
    public String multiply(String num1, String num2) {
        //思路：将num1和num2中的每一个数字相乘，除了个位，其他位要补0
        //将计算结果相加

        String total = "0";
        int step = 0;
        for (int i = num2.length() - 1; i >= 0; i--) {
            String result = multiplyStep1(num1, num2.charAt(i));
            StringBuilder sb = new StringBuilder();
            sb.append(result);
            for (int j = 0; j < step; j++) {
                if(!sb.toString().equals("0")){
                    sb.append('0');
                }
            }
            step++;
            total = addString(total, sb.toString());
        }
        return total;
    }


    //字符串和单个字符相乘
    public String multiplyStep1(String num, char c) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int result = 0;
        for (int i = num.length() - 1; i >= 0; i--) {
            int n1 = num.charAt(i) - '0';
            int n2 = c - '0';
            int temp = (n1 * n2 + carry);
            carry = temp / 10;
            result = temp % 10;
            sb.append(result);
        }
        //
        if (carry > 0) {
            sb.append(carry);
        }
        String str = sb.reverse().toString();
        if (str.startsWith("0")) {
            return "0";
        } else {
            return str;
        }
    }

    public String addString(String num1, String num2) {
        int len1 = num1.length() - 1;
        int len2 = num2.length() - 1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (int i = len1, j = len2; (i >= 0 || j >= 0); i--, j--) {
            int n1 = 0;
            int n2 = 0;
            if (i < 0) {
                n1 = 0;
            } else {
                n1 = num1.charAt(i) - '0';
            }
            if (j < 0) {
                n2 = 0;
            } else {
                n2 = num2.charAt(j) - '0';
            }
            int temp = n1 + n2 + carry;
            carry = temp / 10;
            sb.append(temp % 10);
        }
        if (carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}
