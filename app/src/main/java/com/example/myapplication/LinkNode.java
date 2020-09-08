package com.example.myapplication;

import androidx.annotation.NonNull;

public class LinkNode<T> {
    LinkNode<T> pNext;
    LinkNode<T> pSibling;
    T data;

    public LinkNode(T data) {
        this.data = data;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("("+data);
        if(pNext!=null){
            stringBuffer.append("-"+pNext.data);
        }
        if(pSibling!=null){
            stringBuffer.append("-"+pSibling.data+")");
        }
        return stringBuffer.toString();
    }
}
