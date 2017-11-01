package com.example.bauyrzhan.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

public class UmbrellaDrops extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_umbrella_drops);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    public static boolean startBtnClickedForTheFirstTime = false;
    public static boolean activated = false;

    public void startUmbrellaDrops(View view) {
        if(!startBtnClickedForTheFirstTime) {
            startBtnClickedForTheFirstTime = true;
            (findViewById(R.id.umbrellaView)).postInvalidate();
        }
        activated = true;
    }

    public void stopUmbrellaDrops(View view) {
        activated = false;
    }
}
