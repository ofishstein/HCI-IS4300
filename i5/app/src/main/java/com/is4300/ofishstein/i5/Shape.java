package com.is4300.ofishstein.i5;

import android.graphics.Paint;
import android.widget.FrameLayout;

/**
 * Created by ofishstein on 2/19/18.
 */

public class Shape {
    public String shape;
    public Float left;
    public Float top;
    public Float right;
    public Float bottom;
    public Float startX;
    public Float startY;
    public Float endX;
    public Float endY;
    public Paint paint;

    Shape(String shape, Float startX, Float startY, Float endX, Float endY, Paint paint) {
        this.endX = endX;
        this.endY = endY;
        this.startX = startX;
        this.startY = startY;
        this.left = startX < endX ? startX : endX;
        this.top = startY < endY ? startY : endY;
        this.right = startX > endX ? startX : endX;
        this.bottom = startY > endY ? startY : endY;
        this.shape = shape;
        this.paint = paint;
    }
}
