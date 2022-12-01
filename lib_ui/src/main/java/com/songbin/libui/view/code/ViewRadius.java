package com.songbin.libui.view.code;

import android.content.res.TypedArray;

import com.songbin.libui.R;


public class ViewRadius {
    private final float[] cornerArray = new float[]{0, 0, 0, 0, 0, 0, 0, 0};

    public ViewRadius(TypedArray typedArray) {
        if (typedArray != null) {
            float radius = typedArray.getDimension(R.styleable.WView_w_radius, 0);
            float radiusLT = typedArray.getDimension(R.styleable.WView_w_radius_lt, 0);
            float radiusLB = typedArray.getDimension(R.styleable.WView_w_radius_lb, 0);
            float radiusRT = typedArray.getDimension(R.styleable.WView_w_radius_rt, 0);
            float radiusRB = typedArray.getDimension(R.styleable.WView_w_radius_rb, 0);

            if (radius > 0) {
                setRadius(radius);
            } else {
                setRadius(radiusLT, radiusRT, radiusRB, radiusLB);
            }
        }
    }

    public void setRadius(float radius) {
        cornerArray[0] = radius;
        cornerArray[1] = radius;
        cornerArray[2] = radius;
        cornerArray[3] = radius;
        cornerArray[4] = radius;
        cornerArray[5] = radius;
        cornerArray[6] = radius;
        cornerArray[7] = radius;
    }

    public void setRadius(float leftTop, float rightTop, float rightBottom, float leftBottom) {
        cornerArray[0] = leftTop;
        cornerArray[1] = leftTop;
        cornerArray[2] = rightTop;
        cornerArray[3] = rightTop;
        cornerArray[4] = rightBottom;
        cornerArray[5] = rightBottom;
        cornerArray[6] = leftBottom;
        cornerArray[7] = leftBottom;
    }

    public float[] getCornerArray() {
        return cornerArray;
    }
}
