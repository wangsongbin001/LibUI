package com.wang.wview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qukan.lib.ui.view.ViewOrientation;
import com.qukan.lib.ui.view.WFrameLayout;

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
