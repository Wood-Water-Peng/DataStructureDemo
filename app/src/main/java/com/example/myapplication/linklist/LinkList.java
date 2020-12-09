package com.example.myapplication.linklist;

import com.example.myapplication.LinkNode;

import java.util.Arrays;

public class LinkList<T> {
    LinkNode<T> root;
    LinkNode<T> last;

    public LinkList(T... list) {
        for (int i = 0; i < list.length; i++) {
            if (root == null) {
                root = new LinkNode<>(list[i]);
                last = root;
            } else {
                last.pNext = new LinkNode<>(list[i]);
                last = last.pNext;
            }
        }
    }

    public LinkNode<T> getRoot() {
        return root;
    }
}
