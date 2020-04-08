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

public class CustomView extends View {

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
        canvas.drawCircle(centerX - size, centerY - size, SIZE*4, paint);
        initPaintBlue();
        canvas.drawCircle(centerX - size, centerY - size, SIZE*2, paint);
        initPaintRandom();
        canvas.drawArc(centerX-size-100,centerY-size-100,centerX-size+100,centerY-size+100, 0,90,true,paint);

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
        int rndcolor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        paint.setColor(rndcolor);
    }

    public void onTouch(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
initPaintRandom();

        }
    }

    }
