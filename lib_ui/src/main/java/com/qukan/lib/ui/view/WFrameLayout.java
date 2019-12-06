package com.qukan.lib.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.qukan.lib.ui.view.code.ViewHelper;

public class WFrameLayout extends FrameLayout {
    ViewHelper viewHelper;

    public WFrameLayout(Context context) {
        super(context);
        init(context, null);
    }

    public WFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public WFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        viewHelper = new ViewHelper(this);
        viewHelper.init(context, attrs);
        //处于性能的考虑，viewgroup会被设置成true，即不会调用onDraw方法
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i("ui_tag", " onDraw ");
        if (this.getVisibility() == View.GONE || this.getVisibility() == View.INVISIBLE) {
            super.onDraw(canvas);
            return;
        }
        if (this.getVisibility() == View.VISIBLE) {
            viewHelper.refreshRegion(canvas);
            canvas.save();
            canvas.restore();
        }
        super.onDraw(canvas);
    }

    @Override
    protected void dispatchSetPressed(boolean pressed) {
        Log.i("ui_tag", "WFrameLayout dispatchSetPressed: pressed" + pressed);
        super.dispatchSetPressed(pressed);
        if (viewHelper != null) {
            viewHelper.dispatchSetPressed(pressed);
        }
    }
}
