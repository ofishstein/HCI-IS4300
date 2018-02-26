package com.is4300.ofishstein.i5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by ofishstein on 2/19/18.
 */

public class SketchView extends View {

    Canvas canvas;
    Bitmap bitmap;
    Paint paint;
    String shape;

    Float startX;
    Float startY;
    Float endX;
    Float endY;

    ArrayList<Shape> shapes;

    public SketchView(Context context) {
        super(context);

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setDither(true);

        shapes = new ArrayList<>();
        bitmap = Bitmap.createBitmap(820, 480, Bitmap.Config.ARGB_4444);
        canvas = new Canvas(bitmap);

        this.setBackgroundColor(getResources().getColor(android.R.color.white));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startX = event.getX();
            startY = event.getY();
            Log.d("CANVAS", String.format("DOWN\tx - %f, y - %f", startX, startY));
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            endX = event.getX();
            endY = event.getY();
            shapes.add(new Shape(shape, startX, startY, endX, endY, new Paint(paint)));
            Log.d("CANVAS", String.format("UP\tx - %f, y - %f", endX, endY));
            invalidate();
        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Shape shape : shapes) {
            switch (shape.shape) {
                case "Line":
                    canvas.drawLine(shape.startX, shape.startY, shape.endX, shape.endY, shape.paint);
                    break;
                case "Rectangle":
                    canvas.drawRect(shape.left, shape.top, shape.right, shape.bottom, shape.paint);
                    break;
                case "Oval":
                    canvas.drawOval(shape.left, shape.top, shape.right, shape.bottom, shape.paint);
                    break;
                case "Curve":
                    canvas.drawArc(shape.left, shape.top, shape.right, shape.bottom, 180, 180, false, shape.paint);
            }
        }

        // clear the canvas
        if (shapes.size() < 1) {
            canvas.drawColor(getResources().getColor(android.R.color.transparent));
        }
    }



    public void setPaintColor(String color) {
        switch(color) {
            case "Black":
                paint.setColor(getResources().getColor(R.color.black));
                break;
            case "Yellow":
                paint.setColor(getResources().getColor(R.color.yellow));
                break;
            case "Green":
                paint.setColor(getResources().getColor(R.color.green));
                break;
            case "Blue":
                paint.setColor(getResources().getColor(R.color.blue));
                break;
            case "Red":
                paint.setColor(getResources().getColor(R.color.red));
                break;
        }
    }

    public void setPaintThickness(String thickness) {
        switch (thickness) {
            case "1":
                paint.setStrokeWidth(1 * 5);
                break;
            case "2":
                paint.setStrokeWidth(2 * 5);
                break;
            case "3":
                paint.setStrokeWidth(3 * 5);
                break;
            case "4":
                paint.setStrokeWidth(4 * 5);
                break;
            case "5":
                paint.setStrokeWidth(5 * 5);
                break;
        }
    }

    public void setShape(String shape) {
        this.shape = shape;
        if (shape.equals("Rectangle") || shape.equals("Oval")) {
            paint.setStyle(Paint.Style.FILL);
        } else {
            paint.setStyle(Paint.Style.STROKE);
        }
    }

    public void clearCanvas() {
        Log.d("CLEAR BUTTON", "clearing");
        shapes.clear();
        invalidate();
    }
}
