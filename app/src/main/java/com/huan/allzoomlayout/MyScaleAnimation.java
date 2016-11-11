package com.huan.allzoomlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.ScaleAnimation;

/**
 * -----------------------------------------------------------------------------------Author Info---
 * Company Name:          xjyy.
 * Author:                Liu Huan.
 * Email:                 771383629@qq.com.
 * Date:                  2016/11/11 22:59.
 * -----------------------------------------------------------------------------------Message-------
 * If the following code to run properly, it is coding by Liu Huan.
 * otherwise I don't know.
 * -----------------------------------------------------------------------------------Class Info----
 * ClassName:             MyScaleAnimation.
 * -----------------------------------------------------------------------------------Describe------
 * Function:  只是为了方便克隆ScaleAnimation(该动画类内部没有实现克隆接口)
 * -----------------------------------------------------------------------------------Modify--------
 * 2016/11/11 22:59     Modified By liuhuan.
 * -----------------------------------------------------------------------------------End-----------
 */
public class MyScaleAnimation extends ScaleAnimation implements Cloneable {
    public MyScaleAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScaleAnimation(float fromX, float toX, float fromY, float toY) {
        super(fromX, toX, fromY, toY);
    }

    public MyScaleAnimation(float fromX, float toX, float fromY, float toY, float pivotX, float pivotY) {
        super(fromX, toX, fromY, toY, pivotX, pivotY);
    }

    public MyScaleAnimation(float fromX, float toX, float fromY, float toY, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue) {
        super(fromX, toX, fromY, toY, pivotXType, pivotXValue, pivotYType, pivotYValue);
    }

    public MyScaleAnimation clone() throws CloneNotSupportedException {
        return (MyScaleAnimation) super.clone();
    }

}
