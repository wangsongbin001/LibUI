package com.qukan.lib.ui.view;

import android.support.annotation.IntDef;

public class ViewOrientation {
    //背景渐变色方向
    public static final int LEFT_TO_RIGHT = 0;
    public static final int TOP_TO_BOTTOM = 1;
    public static final int LEFT_TOP_TO_RIGHT_BOTTOM = 2;
    public static final int LEFT_BOTTOM_TO_TOP_RIGHT = 3;

    //阴影方向
    public static final int ALL = 0;
    public static final int LEFT = 1;
    public static final int TOP = 2;
    public static final int RIGHT = 3;
    public static final int BOTTOM = 4;
    public static final int LEFT_TOP = 5;
    public static final int LEFT_BOTTOM = 6;
    public static final int RIGHT_TOP = 7;
    public static final int RIGHT_BOTTOM = 8;

    @IntDef({
            LEFT_TO_RIGHT,
            TOP_TO_BOTTOM,
            LEFT_TOP_TO_RIGHT_BOTTOM,
            LEFT_BOTTOM_TO_TOP_RIGHT
    })
    public @interface Orientation {

    }

    @IntDef({
            ALL,
            LEFT,
            TOP,
            RIGHT,
            BOTTOM,
            LEFT_TOP ,
            LEFT_BOTTOM ,
            RIGHT_TOP,
            RIGHT_BOTTOM
    })
    public @interface ShadowOrientation {
    }
}
