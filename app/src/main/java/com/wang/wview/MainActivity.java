package com.wang.wview;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.songbin.libui.view.ViewOrientation;
import com.songbin.libui.view.WFrameLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WFrameLayout fl = findViewById(R.id.fl);
        fl.getViewHelper()
                .setRadius(60, 60, 0, 60)
                .setBgColor(Color.parseColor("#ffffff"))
                .setBorder(6, Color.parseColor("#E91E63"))
                .setShadow(10, Color.parseColor("#FFEB3B"), ViewOrientation.SHADOW_RIGHT_BOTTOM)
                .invalidate();
    }

    public void onClick(View view){

    }
}
