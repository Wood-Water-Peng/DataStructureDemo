package com.example.myapplication;

import androidx.annotation.NonNull;

public class LinkNode<T> {
    public LinkNode<T> pNext;
    public LinkNode<T> pSibling;
    public T data;

    public LinkNode(T data) {
        this.data = data;
    }

    @NonNull
    @Override
    public String toString() {
//        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append("("+data);
//        if(pNext!=null){
//            stringBuffer.append("-"+pNext.data);
//        }
//        if(pSibling!=null){
//            stringBuffer.append("-"+pSibling.data+")");
//        }
//        return stringBuffer.toString();
        return data.toString();
    }
}
