package com.example.myapplication.card.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 瀑布流中默认的卡片布局
 * 定义样式--子类可以继承
 */
public class TBaseCardView extends FrameLayout {
    public TBaseCardView(@NonNull Context context) {
        super(context);
    }

    public TBaseCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TBaseCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
