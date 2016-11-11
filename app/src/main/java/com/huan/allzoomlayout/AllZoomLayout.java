package com.huan.allzoomlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.widget.LinearLayout;


import java.util.ArrayList;
import java.util.List;

/**
 * -----------------------------------------------------------------------------------Author Info---
 * Company Name:          xjyy.
 * Author:                Liu Huan.
 * Email:                 771383629@qq.com.
 * Date:                  2016/11/11 21:15.
 * -----------------------------------------------------------------------------------Message-------
 * If the following code to run properly, it is coding by Liu Huan.
 * otherwise I don't know.
 * -----------------------------------------------------------------------------------Class Info----
 * ClassName:             com.lh.dcassistant.widgets.
 * -----------------------------------------------------------------------------------Describe------
 * Function: (一个简单的自定义View,LinearLayout的子类)转载请注明出处谢谢
 * -----------------------------------------------------------------------------------Modify--------
 * 2016/11/11 21:15     Modified By liuhuan.
 * -----------------------------------------------------------------------------------End-----------
 */
public class AllZoomLayout extends LinearLayout {
    Context context;
    //按压动画合集
    private List<MyScaleAnimation> starts;
    //松开动画合集
    private List<MyScaleAnimation> ends;
    private float begin = 1.0f;
    private float end = 0.8f;
    /**
     * 收缩动画
     **/
    private MyScaleAnimation beginAnimation = new MyScaleAnimation(begin, end, begin, end,
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
            0.5f);
    /**
     * 伸展动画
     **/
    private MyScaleAnimation finishAnimation = new MyScaleAnimation(end, begin, end, begin,
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
            0.5f);
    /**
     * 点击事件
     */
    private OnClickListener onClickListener;

    public AllZoomLayout(Context context) {
        this(context, null);
    }

    public AllZoomLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AllZoomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initAllZoomLayout();
    }

    /**
     * 初始化动画合集
     */
    private void initAllZoomLayout() {
        /** 设置动画持续时间和保留动画结果 **/
        beginAnimation.setDuration(200);
        /**设置动画停留在最后一个的状态**/
        beginAnimation.setFillAfter(true);
        /** 设置动画持续时间和保留动画结果 **/
        finishAnimation.setDuration(200);
        /**设置动画停留在最后一个的状态**/
        finishAnimation.setFillAfter(true);

        starts = new ArrayList<>();
        ends = new ArrayList<>();
    }

    /**
     * 当View显示到窗口的时候生成动画合集，因为此时的getChildCount()的值才不为0
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (starts.size() == 0 || ends.size() == 0) {
            for (int i = 0; i < getChildCount(); i++) {
                try {
                    starts.add(beginAnimation.clone());
                    ends.add(finishAnimation.clone());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int childCount = getChildCount();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://手指按下时
                for (int i = 0; i < getChildCount(); i++) {
                    getChildAt(i).startAnimation(starts.get(i));
                }
                invalidate();//刷新
                if (onClickListener != null) {//监听
                    onClickListener.onClick(this);
                }
                break;
            case MotionEvent.ACTION_UP:
                for (int i = 0; i < getChildCount(); i++) {
                    getChildAt(i).startAnimation(ends.get(i));
                }
                invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                for (int i = 0; i < getChildCount(); i++) {
                    getChildAt(i).startAnimation(ends.get(i));
                }
                invalidate();
                break;
        }


        return true;
    }

    /**
     * 拦截点击事件不再向下传递
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
