package com.songbin.libui.utils;

import android.content.Context;

public class UIUtil {

    public static float DENSITY;

    public static int dip2px(Context context, float dpValue) {
        float scale = 3.0F;
        if (DENSITY <= 0.0F && context != null) {
            scale = context.getResources().getDisplayMetrics().density;
        } else if (DENSITY > 0.0F) {
            scale = DENSITY;
        }

        return (int)(dpValue * scale + 0.5F);
    }

    public static int px2dip(Context context, float pxValue) {
        float scale = 3.0F;
        if (DENSITY <= 0.0F && context != null) {
            scale = context.getResources().getDisplayMetrics().density;
        } else if (DENSITY > 0.0F) {
            scale = DENSITY;
        }

        return (int)(pxValue / scale + 0.5F);
    }
}
