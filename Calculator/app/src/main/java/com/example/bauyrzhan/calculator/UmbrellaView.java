package com.example.bauyrzhan.calculator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.content.Context;

import java.util.ArrayList;
import java.util.Random;

public class UmbrellaView extends View { // required constructors

    int frames = 0;
    private int xMax, yMax; // This view’s bounds
    private int umbrellaX = 0;
    private float fallY = 30;
    private ArrayList<Bitmap> umbrellas;
    private ArrayList<Point> umbrellaLocations;
    public static Thread umbrellaTh;

    public UmbrellaView(Context context) {
        super(context);
    }

    public UmbrellaView(Context context , AttributeSet attrs) {
        super(context, attrs);
        umbrellas = new ArrayList<>();
        umbrellaLocations = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        xMax = canvas.getWidth();
        yMax = canvas.getHeight();
        canvas.drawColor(Color.rgb(38, 223, 211));
        umbrellaDrop(canvas);
    }

    private void umbrellaDrop(Canvas canvas) {
        if(UmbrellaDrops.startBtnClickedForTheFirstTime) {
            if(UmbrellaDrops.activated) {
                frames++;
                if (frames % 3 == 0) {
                    Random r = new Random();
                    int randX = r.nextInt(xMax);
                    umbrellaX = randX;
                    Bitmap umbrella = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.umbrella), 200, 200, true);
                    if (umbrellaX >= xMax) {
                        umbrellaX -= ((xMax+umbrellaX+umbrella.getWidth())-xMax);
                    }
                    umbrellaLocations.add(new Point(umbrellaX, 0));
                    canvas.drawBitmap(umbrella, umbrellaX, 0, null);
                    umbrellas.add(umbrella);
                }
            }

            for (int i = 0; i < umbrellas.size(); i++) {
                Bitmap umbrella = umbrellas.get(i);
                Point loc = umbrellaLocations.get(i);
                if (loc.y < yMax) {
                    canvas.drawBitmap(umbrella, loc.x, loc.y, null);
                    if(UmbrellaDrops.activated) {
                        loc.y += fallY;
                        umbrellaLocations.set(i, loc);
                    }
                } else {
                    umbrellas.remove(i);
                    umbrellaLocations.remove(i);
                    i--;
                }
            }

            postInvalidate();

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) { }
        }
    }

}
