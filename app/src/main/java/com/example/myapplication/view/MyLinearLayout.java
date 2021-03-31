package com.example.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Scroller;

import androidx.annotation.Nullable;

public class MyLinearLayout extends LinearLayout {
    private Scroller mScroller;
    public MyLinearLayout(Context context) {
        this(context,null);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller=new Scroller(context);
//        mScroller.startScroll(0, 0, 0, -100, 2000);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void computeScroll() {
        if (!mScroller.isFinished() && mScroller.computeScrollOffset()) {
            final int oldX = getScrollX();
            final int oldY = getScrollY();
            final int x = mScroller.getCurrX();
            final int y = mScroller.getCurrY();

            if (oldX != x || oldY != y) {
                scrollTo(x, y);

            }

            // Keep on drawing until the animation has finished.
            postInvalidateOnAnimation();
            return;
        }

        // Done with scroll, clean up state.
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
