package com.songbin.libui.view.code;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;

import com.songbin.libui.R;
import com.songbin.libui.view.ViewOrientation;

import java.util.ArrayList;
import java.util.List;

/**
 * 背景绘制
 */
public class ViewBackground {
    Paint bgPaint;
    Path mPath;
    RectF mRectF;

    private int gradientOrientation;
    private int pressGradientOrientation;

    private List<Integer> bgColors;
    private List<Integer> pressedColors;

    private float pressAlpha = 0.7f;
    private boolean isPressed = false;

    public ViewBackground(TypedArray typedArray) {
        bgPaint = new Paint();
        bgPaint.setAntiAlias(true);
        bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setColor(Color.TRANSPARENT);
        bgColors = new ArrayList<>();
        pressedColors = new ArrayList<>();
        if (typedArray != null) {
            int bgColor = typedArray.getColor(R.styleable.WView_w_bg, Color.TRANSPARENT);
            int bgColorStart = typedArray.getColor(R.styleable.WView_w_bg_start, Color.TRANSPARENT);
            int bgColorEnd = typedArray.getColor(R.styleable.WView_w_bg_end, Color.TRANSPARENT);
            gradientOrientation = typedArray.getInt(R.styleable.WView_w_bg_orientation, 0);

            int pressBgColor = typedArray.getColor(R.styleable.WView_w_press_bg, Color.TRANSPARENT);
            int pressBgColorStart = typedArray.getColor(R.styleable.WView_w_press_bg_start, Color.TRANSPARENT);
            int pressBgColorEnd = typedArray.getColor(R.styleable.WView_w_press_bg_end, Color.TRANSPARENT);
            pressGradientOrientation = typedArray.getInt(R.styleable.WView_w_press_bg_orientation, 0);

            if (bgColor != Color.TRANSPARENT) {
                setBackgroudColor(bgColor);
            } else if (bgColorStart != Color.TRANSPARENT || bgColorEnd != Color.TRANSPARENT) {
                setBackgroudColor(bgColorStart, bgColorEnd);
            }

            if (pressBgColor != Color.TRANSPARENT) {
                setPressBackgroudColor(pressBgColor);
            } else if (pressBgColorStart != Color.TRANSPARENT || pressBgColorEnd != Color.TRANSPARENT) {
                setPressBackgroudColor(pressBgColorStart, pressBgColorEnd);
            }
        }
    }

    public void setBackgroudColor(int bgColor) {
        bgColors.clear();
        bgColors.add(bgColor);
    }

    public void setBackgroudColor(int... gradientColors) {
        bgColors.clear();
        if (gradientColors != null) {
            for (int color : gradientColors) {
                bgColors.add(color);
            }
        }
    }

    public void setGradientOrientation(int orientation) {
        this.gradientOrientation = orientation;
    }

    public void setPressBackgroudColor(int pressBgColor) {
        pressedColors.clear();
        pressedColors.add(pressBgColor);
    }

    public void setPressBackgroudColor(int... pressGradientColors) {
        pressedColors.clear();
        if (pressGradientColors != null) {
            for (int color : pressGradientColors) {
                pressedColors.add(color);
            }
        }
    }

    public void setPressGradientOrientation(int pressGradientOrientation) {
        this.pressGradientOrientation = pressGradientOrientation;
    }

    public void refreshRegion(Canvas canvas, RectF rectF, Path path, float[] cornerArray, float borderWidth) {
        if (canvas != null && rectF != null && path != null && cornerArray != null) {
            if (!bgColors.isEmpty()) {
                measurePath(rectF, path, borderWidth, cornerArray);
                bgPaint.reset();
                bgPaint.setAntiAlias(true);
                if (isPressed) {
                    if (pressedColors.isEmpty()) {
                        bgPaint.setAlpha((int) (255 * pressAlpha));
                    } else {
                        bgPaint.setAlpha(255);
                        if (pressedColors.size() > 1) {
                            bgPaint.setShader(getShader(mRectF, pressedColors, pressGradientOrientation));
                        } else {
                            bgPaint.setShader(null);
                            bgPaint.setColor(pressedColors.get(0));
                        }
                    }
                } else {
                    bgPaint.setAlpha(255);
                    if (bgColors.size() > 1) {
                        bgPaint.setShader(getShader(mRectF, bgColors, gradientOrientation));
                    } else {
                        bgPaint.setShader(null);
                        bgPaint.setColor(bgColors.get(0));
                    }
                }
                canvas.drawPath(mPath, bgPaint);
            }
        }
    }

    private void measurePath(RectF rectF, Path path, float borderWidth, float[] cornerArray) {
        if (borderWidth <= 0) {
            borderWidth = 0;
        }
        if (mPath == null) {
            mPath = new Path();
        }
        mPath.reset();
        if (mRectF == null) {
            mRectF = new RectF();
        }
        mRectF.set(rectF.left + borderWidth,
                rectF.top + borderWidth,
                rectF.right - borderWidth,
                rectF.bottom - borderWidth);
        mPath.addRoundRect(mRectF, cornerArray, Path.Direction.CW);
    }

    private Shader getShader(RectF rectF, List<Integer> bgColors, int gradientOrientation) {
        if (rectF != null && bgColors != null) {
            int[] colorArray = new int[bgColors.size()];
            for (int i = 0; i < bgColors.size(); i++) {
                colorArray[i] = bgColors.get(i);
            }
            Shader shader = null;
            switch (gradientOrientation) {
                case ViewOrientation.GRADIENT_TOP_TO_BOTTOM:
                    shader = new LinearGradient(rectF.width(), 0,
                            rectF.width(), rectF.height(),
                            colorArray,
                            null,
                            Shader.TileMode.CLAMP);
                    break;
                case ViewOrientation.GRADIENT_LEFT_TOP_TO_RIGHT_BOTTOM:
                    shader = new LinearGradient(0, 0,
                            rectF.width(), rectF.height(),
                            colorArray,
                            null,
                            Shader.TileMode.CLAMP);
                    break;
                case ViewOrientation.GRADIENT_LEFT_BOTTOM_TO_TOP_RIGHT:
                    shader = new LinearGradient(0, rectF.height(),
                            rectF.width(), 0,
                            colorArray,
                            null,
                            Shader.TileMode.CLAMP);
                    break;
                default:
                    shader = new LinearGradient(0, rectF.height(),
                            rectF.width(), rectF.height(),
                            colorArray,
                            null,
                            Shader.TileMode.CLAMP);
                    break;
            }
            return shader;
        }
        return null;
    }

    /**
     * @return  是否需要刷新界面
     */
    public boolean dispatchSetPressed(boolean isPressed) {
        if(this.isPressed == isPressed){
            return false;
        }
        this.isPressed = isPressed;
        return true;
    }


}
