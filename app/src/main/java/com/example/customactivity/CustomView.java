/*Experimental2*/

package com.example.customactivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Random;

import static java.lang.Math.round;
import static java.lang.Math.sqrt;

public class CustomView extends View {

    private MotionEvent event;

    public interface OnActionTouchListener {
        void setOnActionTouchListener(int x,int y);
    }

    private Paint paint;
    private Paint paintOne;
    private Paint paintTwo;
    private Paint paintThree;
    private Paint paintFour;
    private Paint paintCircle;
    private int centerX;
    private int centerY;


    private final int BIG_RADIUS = 350;
    private final int LITTLE_RADIUS = 100;

    private OnActionTouchListener touchListener;

    public void setOnActionTouchListener(OnActionTouchListener touchListener){this.touchListener = touchListener;}

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {

            canvas.drawArc(centerX - BIG_RADIUS, centerY - BIG_RADIUS, centerX + BIG_RADIUS, centerY + BIG_RADIUS, 0, 90, true, paintOne);
            canvas.drawArc(centerX - BIG_RADIUS, centerY - BIG_RADIUS, centerX + BIG_RADIUS, centerY + BIG_RADIUS, 90, 90, true, paintTwo);
        canvas.drawArc(centerX - BIG_RADIUS, centerY  - BIG_RADIUS, centerX + BIG_RADIUS, centerY  + BIG_RADIUS, 180, 90, true, paintThree);
        canvas.drawArc(centerX  - BIG_RADIUS, centerY  - BIG_RADIUS, centerX  + BIG_RADIUS, centerY  + BIG_RADIUS, 270, 90, true, paintFour);
        canvas.drawCircle(centerX , centerY , LITTLE_RADIUS, paintCircle);

        super.onDraw(canvas);
    }

    private void initPaintRandom() {
        Random rnd = new Random();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        int rndcolor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        paint.setColor(rndcolor);
        paint.setStyle(Paint.Style.FILL);
    }

    public void initStartPaints(){
        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
        centerX = displaymetrics.widthPixels/2;
        centerY = displaymetrics.heightPixels/2;
        initPaintRandom();
        paintOne=paint;
        initPaintRandom();
        paintTwo=paint;
        initPaintRandom();
        paintThree=paint;
        initPaintRandom();
        paintFour=paint;
        initPaintRandom();
        paintCircle=paint;
    }


    private boolean includeCircle (int x, int y) {
        int a=0,b=0;
      if ((x>centerX)&(y<centerY)) {a=x-centerX;b=centerY-y;}
      if ((x>centerX)&(y>centerY)) {a=x-centerX;b=y-centerY;}
      if ((x<centerX)&(y>centerY)) {a=centerX-x;b=y-centerY;}
      if ((x<centerX)&(y<centerY)) {a=centerX-x;b=centerY-y;}

        if (sqrt(a*a+b*b)<LITTLE_RADIUS) return true;
        else return false;
    }

    private boolean includeQuarterOne (int x,int y) {
        int a=0,b=0;
        if ((x>centerX)&(y>centerY)) {a=x-centerX;b=y-centerY;}
        if ((sqrt(a*a+b*b)>LITTLE_RADIUS)&((sqrt(a*a+b*b)<350)))
        return true; else return false;
    }

    private boolean includeQuarterTwo (int x,int y) {
        int a=0,b=0;
        if ((x<centerX)&(y>centerY)) {a=centerX-x;b=y-centerY;}
        if ((sqrt(a*a+b*b)>LITTLE_RADIUS)&((sqrt(a*a+b*b)<350)))
            return true; else return false;
    }

    private boolean includeQuarterThree(int x,int y) {
        int a=0,b=0;
        if ((x<centerX)&(y<centerY)) {a=centerX-x;b=centerY-y;}
        if ((sqrt(a*a+b*b)>LITTLE_RADIUS)&((sqrt(a*a+b*b)<350)))
            return true; else return false;
    }

    private boolean includeQuarterFour(int x,int y) {
        int a=0,b=0;
        if ((x>centerX)&(y<centerY)) {a=x-centerX;b=centerY-y;}
        if ((sqrt(a*a+b*b)>LITTLE_RADIUS)&((sqrt(a*a+b*b)<350)))
            return true; else return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        this.event = event;
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
           //centerX=round(event.getX()); experimental movement of the center of the figure
           //centerY=round(event.getY());

            if (touchListener != null) {
                touchListener.setOnActionTouchListener(round(event.getX()),round(event.getY()));
            }


           if (includeCircle(round(event.getX()),round(event.getY()))) {
                initStartPaints();
                invalidate();
            }
            if (includeQuarterOne(round(event.getX()),round(event.getY()))) {
                initPaintRandom();
                paintOne=paint;
                invalidate();
            }
            if (includeQuarterTwo(round(event.getX()),round(event.getY()))) {
                initPaintRandom();
                paintTwo=paint;
                invalidate();
            }
            if (includeQuarterThree(round(event.getX()),round(event.getY()))) {
                initPaintRandom();
                paintThree=paint;
                invalidate();
            }
            if (includeQuarterFour(round(event.getX()),round(event.getY()))) {
                initPaintRandom();
                paintFour=paint;
                invalidate();
            }



        }
        return super.onTouchEvent(event);
    }

}