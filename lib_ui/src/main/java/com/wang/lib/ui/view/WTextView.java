package com.wang.lib.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.wang.lib.ui.view.code.ViewHelper;

/**
 * 1，添加属性
 * 2，获取属性
 * 3，使用属性
 */
public class WTextView extends AppCompatTextView {

    ViewHelper viewHelper;

    public WTextView(Context context) {
        super(context);
        init(context, null);
    }

    public WTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public WTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        viewHelper = new ViewHelper(this);
        viewHelper.init(context, attrs);
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

    public ViewHelper getViewHelper(){
        return viewHelper;
    }

    @Override
    protected void dispatchSetPressed(boolean pressed) {
        Log.i("ui_tag", "WTextView dispatchSetPressed: pressed" + pressed);
        super.dispatchSetPressed(pressed);
        if (viewHelper != null) {
            viewHelper.dispatchSetPressed(pressed);
        }
    }
}
