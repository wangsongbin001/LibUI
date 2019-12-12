package com.wang.lib.ui.view.code;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

import com.wang.lib.ui.R;


/**
 * 边框绘制
 */
public class ViewBorder {
    Paint borderPaint;
    float width = 0f;

    public ViewBorder(TypedArray typedArray) {
        int color = Color.TRANSPARENT;
        if (typedArray != null) {
            color = typedArray.getColor(R.styleable.WView_w_border_color, Color.TRANSPARENT);
            width = typedArray.getDimension(R.styleable.WView_w_border_width, 0);
        }
        borderPaint = new Paint();
        borderPaint.setStyle(Paint.Style.FILL);
        borderPaint.setAntiAlias(true);
        borderPaint.setColor(color);
    }

    /**
     * 刷新区域
     *
     * @param canvas
     * @param path
     */
    public void refreshRegion(Canvas canvas, RectF rectF, Path path) {
        if (width <= 0) {
            return;
        }
        if (canvas != null && rectF != null && path != null) {
            canvas.drawPath(path, borderPaint);
        }
    }

    public void setBorderWidth(float borderWidth){
        this.width = borderWidth;
    }

    public void setBorderColor(int borderColor){
        borderPaint.reset();
        borderPaint.setColor(borderColor);
    }
}
