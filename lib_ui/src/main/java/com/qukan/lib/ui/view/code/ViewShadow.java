package com.qukan.lib.ui.view.code;

import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

import com.qukan.lib.ui.R;
import com.qukan.lib.ui.view.ViewOrientation;

import static android.view.View.LAYER_TYPE_SOFTWARE;

public class ViewShadow {

    Paint shadowPaint;
    float shadowWidth;
    int shadowOrientation;
    int shadowColor;

    public ViewShadow(View view, TypedArray typedArray){
        shadowColor = Color.TRANSPARENT;
        if(typedArray != null){
            shadowColor = typedArray.getColor(R.styleable.WView_w_shadow_color, Color.TRANSPARENT);
            shadowWidth = typedArray.getDimension(R.styleable.WView_w_shadow_width, 0);
            shadowOrientation = typedArray.getInt(R.styleable.WView_w_shadow_orientation, 0);
        }
        shadowPaint = new Paint();
        shadowPaint.setStyle(Paint.Style.FILL);
        shadowPaint.setColor(shadowColor);
        checkIfCloseHardwareAcceleration(view);
    }

    public void refreshRegion(Canvas canvas, RectF rectF, Path path){
        if(shadowWidth <= 0){
            return;
        }
        if(canvas != null && rectF != null && path != null){
            if(shadowOrientation == ViewOrientation.SHADOW_ALL){
                shadowPaint.setMaskFilter(new BlurMaskFilter(shadowWidth, BlurMaskFilter.Blur.OUTER));
            }else{
                float shadow = shadowWidth/2;
                switch (shadowOrientation){
                    case ViewOrientation.SHADOW_LEFT:
                        shadowPaint.setShadowLayer(shadow, -shadow, 0, shadowColor);
                        break;
                    case ViewOrientation.SHADOW_TOP:
                        shadowPaint.setShadowLayer(shadow, 0, -shadow, shadowColor);
                        break;
                    case ViewOrientation.SHADOW_RIGHT:
                        shadowPaint.setShadowLayer(shadow, shadow, 0, shadowColor);
                        break;
                    case ViewOrientation.SHADOW_BOTTOM:
                        shadowPaint.setShadowLayer(shadow, 0, shadow, shadowColor);
                        break;
                    case ViewOrientation.SHADOW_LEFT_TOP:
                        shadowPaint.setShadowLayer(shadow, -shadow, -shadow, shadowColor);
                        break;
                    case ViewOrientation.SHADOW_LEFT_BOTTOM:
                        shadowPaint.setShadowLayer(shadow, -shadow, shadow, shadowColor);
                        break;
                    case ViewOrientation.SHADOW_RIGHT_TOP:
                        shadowPaint.setShadowLayer(shadow, shadow, -shadow, shadowColor);
                        break;
                    case ViewOrientation.SHADOW_RIGHT_BOTTOM:
                        shadowPaint.setShadowLayer(shadow, shadow, shadow, shadowColor);
                        break;
                }
            }
            canvas.drawPath(path, shadowPaint);
        }
    }

    public void setShadowWidth(View view, float shadowWidth){
        this.shadowWidth = shadowWidth;
        checkIfCloseHardwareAcceleration(view);
    }

    public void setShadowColor(int color){
        shadowPaint.reset();
        shadowPaint.setStyle(Paint.Style.FILL);
        shadowPaint.setColor(color);
        this.shadowColor = color;
    }

    public void setShadowOrientation(int shadowOrientation){
        this.shadowOrientation = shadowOrientation;
    }

    private void checkIfCloseHardwareAcceleration(View view){
        if(shadowWidth > 0 && view != null){
            //对单独的View在运行时阶段禁用硬件加速
            view.setLayerType(LAYER_TYPE_SOFTWARE, null);
        }
    }

}
