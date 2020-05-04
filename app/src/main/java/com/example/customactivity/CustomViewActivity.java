package com.example.customactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CustomViewActivity extends AppCompatActivity {
    public static Intent newIntent(Context context) {
        return new Intent(context, CustomViewActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);

        CustomView customView = findViewById(R.id.customView);
        customView.initStartPaints();


        customView.setOnActionTouchListener(new CustomView.OnActionTouchListener() {
            @Override
            public void setOnActionTouchListener(int x, int y) {
                String message = String.format("X: %d Y: %d", x, y);
                Toast.makeText(CustomViewActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

    }


}
