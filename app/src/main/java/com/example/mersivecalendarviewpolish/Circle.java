package com.example.mersivecalendarviewpolish;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class Circle extends View {

    private static final int START_ANGLE_POINT = 270;

    private final Paint paint;
    private final RectF rect;

    public float angle;

    public Circle(Context context, AttributeSet attrs) {
        super(context, attrs);

        final int strokeWidth = 11;

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        //Circle color
        paint.setColor(Color.rgb(255, 0, 0));

        //size 200x200 example
        rect = new RectF(strokeWidth, strokeWidth, 329 + strokeWidth, 329 + strokeWidth);

        //Initial Angle (optional, it can be zero)
        //angle = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(rect, START_ANGLE_POINT, angle, false, paint);
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}