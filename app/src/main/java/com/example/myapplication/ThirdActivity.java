package com.example.myapplication;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ThirdActivity extends AppCompatActivity {
    private static final String TAG = "ThirdActivity";
    LinearLayout linearLayout;
    private ObjectAnimator objectAnimatorOut;
    private TextView btn_01;
    private View btn_02;
    private View relative_layout;
    private Scroller mScroller;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        mScroller = new Scroller(this);
        linearLayout = findViewById(R.id.linear_layout);
        btn_01 = findViewById(R.id.btn_01);
        linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int measuredHeight = linearLayout.getMeasuredHeight();
                int top = btn_01.getTop();
            }
        });
        btn_01.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int top = btn_01.getTop();
                Log.i(TAG, "btn_01----top:" + top);
                Log.i(TAG, "btn_01----scrollY:" + btn_01.getScrollY());
            }
        });

        btn_01.setMovementMethod(new ScrollingMovementMethod());
        btn_01.postDelayed(new Runnable() {
            @Override
            public void run() {
//                btn_01.setTranslationY(-100);
                ScaleAnimation scaleAnimation = new ScaleAnimation(1, 3, 1, 3, btn_02.getWidth() / 2, btn_02.getHeight() / 2);
                btn_02.setAnimation(scaleAnimation);
                scaleAnimation.setDuration(2000);
                scaleAnimation.start();
//                ObjectAnimator animator = ObjectAnimator.ofFloat(btn_01, "alpha", 1f, 0.5f);
//                animator.setDuration(2000);
//                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator animation) {
//                        Object animatedValue = animation.getAnimatedValue();
//                    }
//                });
//                animator.start();
            }
        }, 1000);
        btn_02 = findViewById(R.id.btn_02);
        relative_layout = findViewById(R.id.relative_layout);
//        relative_layout.scrollBy(0,-50);
        relative_layout.setTranslationY(-50);
        int end = 100;
        //上滑收起
        if (objectAnimatorOut == null) {
            float[] x = {0f, end};
            objectAnimatorOut = ObjectAnimator.ofFloat(btn_02, "translationY", x);
            objectAnimatorOut.setDuration(2000);
        }
//        objectAnimatorOut.start();
        btn_02.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                float btn_02X = btn_02.getX();
                int btn02Left = btn_02.getLeft();
                Log.i(TAG, "btn02Left:" + btn02Left);
//                Log.i(TAG,"btn02Left:"+btn02Left);

            }
        });

        btn_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ThirdActivity.this, "btn_02", Toast.LENGTH_SHORT).show();
            }
        });
        btn_02.postDelayed(new Runnable() {
            @Override
            public void run() {
//                btn_02.offsetLeftAndRight(50);
            }
        }, 1000);
        btn_02.postDelayed(new Runnable() {
            @Override
            public void run() {
                btn_02.requestLayout();
            }
        }, 3000);

        float[] x = {0f, end};
        ObjectAnimator translationX = ObjectAnimator.ofFloat(relative_layout, "translationX", x);
        translationX.setDuration(1000);
    }
}
