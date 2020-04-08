package com.example.customactivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Random;

import static java.lang.Math.round;

public class CustomView extends View {

    private MotionEvent event;

    public interface onMeasureListener {
        void onSizeChanged(int width, int height);
    }

    private Paint paint;
    private int centerX;
    private int centerY;

    private final int SIZE = 50;

    private onMeasureListener listener;

    public void setListener(onMeasureListener listener) {
        this.listener = listener;
    }

    public CustomView(Context context) {
        super(context);
        initPaint();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
        if (listener != null) {
            listener.onSizeChanged(w, h);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int size = SIZE / 2;



        initPaintRandom();
        canvas.drawArc(centerX - size - 350, centerY - size - 350, centerX - size + 350, centerY - size + 350, 0, 90, true, paint);

        initPaintRandom();
        canvas.drawArc(centerX - size - 350, centerY - size - 350, centerX - size + 350, centerY - size + 350, 90, 180, true, paint);

        initPaint();
        canvas.drawArc(centerX - size - 350, centerY - size - 350, centerX - size + 350, centerY - size + 350, 180, 270, true, paint);

        initPaintRandom();
        canvas.drawArc(centerX - size - 350, centerY - size - 350, centerX - size + 350, centerY - size + 350, 270, 360, true, paint);


        initPaintRandom();
        canvas.drawCircle(centerX - size, centerY - size, SIZE * 2, paint);




        super.onDraw(canvas);
    }


    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
    }

    private void initPaintBlue() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
    }

    private void initPaintRandom() {
        Random rnd = new Random();
        paint = new Paint();
        paint.setAntiAlias(true);
        int rndcolor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        paint.setColor(rndcolor);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        this.event = event;
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
           centerX=round(event.getX());
           centerY=round(event.getY());
            invalidate();


        }
        return super.onTouchEvent(event);
    }

}