package com.example.myapplication;

import androidx.annotation.NonNull;

public class TreeNode<T> {
    public TreeNode<T> left;
    public TreeNode<T> right;
    public T data;

    public TreeNode(T data) {
        this.data = data;
    }


    @NonNull
    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("value:"+data);
        if(left!=null){
            stringBuffer.append("-left:"+left.data);
        }
        if(right!=null){
            stringBuffer.append("-right:"+right.data);
        }
        return stringBuffer.toString();
    }
}
