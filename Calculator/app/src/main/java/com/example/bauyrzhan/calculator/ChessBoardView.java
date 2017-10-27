package com.example.bauyrzhan.calculator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class ChessBoardView extends View {
    public ChessBoardView(Context context) {
        super(context);
    }

    public ChessBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        boolean white = false;
        int counter = 0;
        int square = canvas.getWidth()/6;

        for(int j=square;j<=(canvas.getHeight()/2+canvas.getHeight()/4);j+=square) {

            if(white) {
                counter = 1;
                white = false;
            }
            else {
                counter = 0;
                white = true;
            }

            for (int i=square;i<=canvas.getWidth();i+=square) {

                if(counter%2 == 0) {
                    Paint p = new Paint();
                    p.setStyle(Paint.Style.FILL);
                    p.setARGB(255, 0, 0, 0);
                    canvas.drawRect(i-square,j-square,i,j,p);
                }

                counter++;

            }

        }

    }
}
