package com.qukan.lib.ui.view;

import android.support.annotation.IntDef;

public class ViewOrientation {
    //背景渐变色方向
    public static final int GRADIENT_LEFT_TO_RIGHT = 0;
    public static final int GRADIENT_TOP_TO_BOTTOM = 1;
    public static final int GRADIENT_LEFT_TOP_TO_RIGHT_BOTTOM = 2;
    public static final int GRADIENT_LEFT_BOTTOM_TO_TOP_RIGHT = 3;

    //阴影方向
    public static final int SHADOW_ALL = 0;
    public static final int SHADOW_LEFT = 1;
    public static final int SHADOW_TOP = 2;
    public static final int SHADOW_RIGHT = 3;
    public static final int SHADOW_BOTTOM = 4;
    public static final int SHADOW_LEFT_TOP = 5;
    public static final int SHADOW_LEFT_BOTTOM = 6;
    public static final int SHADOW_RIGHT_TOP = 7;
    public static final int SHADOW_RIGHT_BOTTOM = 8;

    @IntDef({
            GRADIENT_LEFT_TO_RIGHT,
            GRADIENT_TOP_TO_BOTTOM,
            GRADIENT_LEFT_TOP_TO_RIGHT_BOTTOM,
            GRADIENT_LEFT_BOTTOM_TO_TOP_RIGHT
    })
    public @interface Orientation {

    }

    @IntDef({
            SHADOW_ALL,
            SHADOW_LEFT,
            SHADOW_TOP,
            SHADOW_RIGHT,
            SHADOW_BOTTOM,
            SHADOW_LEFT_TOP,
            SHADOW_LEFT_BOTTOM,
            SHADOW_RIGHT_TOP,
            SHADOW_RIGHT_BOTTOM
    })
    public @interface ShadowOrientation {
    }
}
