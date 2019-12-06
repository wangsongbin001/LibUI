package com.qukan.lib.ui.view.code;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.qukan.lib.ui.R;
import com.qukan.lib.ui.utils.UIUtil;
import com.qukan.lib.ui.view.ViewOrientation;

public class ViewHelper {
    View view;
    /**
     * 背景
     */
    ViewBackground viewBackground;
    /**
     * 边框
     */
    ViewBorder viewBorder;
    /**
     * 阴影
     */
    ViewShadow viewShadow;
    /**
     * 圆角
     */
    ViewRadius viewRadius;

    /**
     * 区域，矩阵
     */
    Path path;
    RectF rectF;

    public ViewHelper(View view) {
        this.view = view;
    }

    //初始化属性
    public void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WView);
        viewRadius = new ViewRadius(typedArray);
        viewBackground = new ViewBackground(typedArray);
        viewBorder = new ViewBorder(typedArray);
        viewShadow = new ViewShadow(view, typedArray);
        typedArray.recycle();
    }

    public void refreshRegion(Canvas canvas) {
        if (view != null) {
            //测量区域
            measurePath(view);
        }
        viewShadow.refreshRegion(canvas, rectF, path);
        viewBorder.refreshRegion(canvas, rectF, path);
        viewBackground.refreshRegion(canvas, rectF, path, viewRadius.getCornerArray(), viewBorder.width);
    }

    private void measurePath(View view) {
        if (path == null) {
            path = new Path();
        }
        path.reset();
        if (rectF == null) {
            rectF = new RectF();
        }
        Log.i("ui_tag", "getMeasuredWidth " + view.getMeasuredWidth());
        rectF.set(viewShadow.shadowWidth,
                viewShadow.shadowWidth,
                view.getMeasuredWidth() - viewShadow.shadowWidth,
                view.getMeasuredHeight() - viewShadow.shadowWidth);
        path.addRoundRect(rectF, viewRadius.getCornerArray(), Path.Direction.CW);
    }

    //设置圆角
    public ViewHelper setRadius(int dpRadius) {
        dpRadius = dpRadius < 0 ? 0 : dpRadius;
        if (viewRadius != null) {
            viewRadius.setRadius(UIUtil.dip2px(view.getContext(), dpRadius));
        }
        return this;
    }

    //设置边框
    public ViewHelper setBorder(int dpBorderWidth, int borderColor) {
        dpBorderWidth = dpBorderWidth < 0 ? 0 : dpBorderWidth;
        if (viewBorder != null) {
            viewBorder.setBorderWidth(UIUtil.dip2px(view.getContext(), dpBorderWidth));
            viewBorder.setBorderColor(borderColor);
        }
        return this;
    }

    //设置阴影
    public ViewHelper setShadow(int dpShadowWidth, int shadowColor, @ViewOrientation.ShadowOrientation int shadowOrientation) {
        dpShadowWidth = dpShadowWidth < 0 ? 0 : dpShadowWidth;
        if (viewShadow != null) {
            viewShadow.setShadowWidth(view, UIUtil.dip2px(view.getContext(), dpShadowWidth));
            viewShadow.setShadowColor(shadowColor);
            viewShadow.setShadowOrientation(shadowOrientation);
        }
        return this;
    }

    //设置背景，渐变
    public ViewHelper setBgColor(int bgColor) {
        if (viewBackground != null) {
            viewBackground.setBackgroudColor(bgColor);
        }
        return this;
    }

    public ViewHelper setBgColor(int bgColorStart, int bgColorEnd, @ViewOrientation.Orientation int orientation) {
        if (viewBackground != null) {
            viewBackground.setBackgroudColor(bgColorStart, bgColorEnd);
            viewBackground.setGradientOrientation(orientation);
        }
        return this;
    }

    //设置,按压时的背景
    public ViewHelper setPressBgColor(int pressBgColor){
        if (viewBackground != null) {
            viewBackground.setPressBackgroudColor(pressBgColor);
        }
        return this;
    }

    //设置,按压时的背景
    public ViewHelper setPressBgColor(int pressBgColorStart, int pressBgColorEnd, @ViewOrientation.Orientation int orientation){
        if (viewBackground != null) {
            viewBackground.setPressBackgroudColor(pressBgColorStart, pressBgColorEnd);
            viewBackground.setPressGradientOrientation(orientation);
        }
        return this;
    }

    public void invalidate() {
        if (null != view) {
            view.invalidate();
        }
    }

    public void dispatchSetPressed(boolean pressed) {
        if (view != null && viewBackground != null) {
            if (viewBackground.dispatchSetPressed(pressed)) {
                view.invalidate();
            }
        }
    }
}
