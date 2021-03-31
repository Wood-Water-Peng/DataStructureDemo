package com.example.myapplication;

import androidx.annotation.NonNull;

import java.util.List;

public class TreeMultiNode<T> {
    public List<TreeMultiNode<T>> children;
    public T data;

    public TreeMultiNode(T data) {
        this.data = data;
    }


}
