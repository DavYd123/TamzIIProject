package com.example.david.pacmangame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

public class NewGame extends View
{
    int width;
    int height;

    // velikost levelu
    int x_level = 19;
    int y_level = 17;


    public NewGame(Context context)
    {
        super(context);
        init(context);
    }

    public NewGame(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }

    Bitmap[] bmp;

    private int level1[] =
            {
                    4 ,4 ,4 ,4 ,4 ,4 ,4 ,4 ,4 ,4 ,4 ,4 ,4 ,4 ,4 ,4 ,4 ,
                    4 ,5 ,5 ,5 ,5 ,5 ,5 ,5 ,4 ,5 ,5 ,5 ,5 ,5 ,5 ,5 ,4 ,
                    4 ,5 ,4 ,4 ,5 ,4 ,4 ,5 ,4 ,5 ,4 ,4 ,5 ,4 ,4 ,5 ,4 ,
                    4 ,6 ,5 ,5 ,5 ,5 ,5 ,5 ,5 ,5 ,5 ,5 ,5 ,5 ,5 ,6 ,4 ,
                    4 ,5 ,4 ,4 ,5 ,4 ,5 ,4 ,4 ,4 ,5 ,4 ,5 ,4 ,4 ,5 ,4 ,
                    4 ,5 ,5 ,5 ,5 ,4 ,5 ,5 ,4 ,5 ,5 ,4 ,5 ,5 ,5 ,5 ,4 ,
                    4 ,4 ,4 ,4 ,5 ,4 ,4 ,15,4 ,15,4 ,4 ,5 ,4 ,4 ,4 ,4 ,
                    15,15,15,4 ,5 ,4 ,15,15,14,15,15,4 ,5 ,4 ,15,15,15,
                    4 ,4 ,4 ,4 ,5 ,4 ,15,4 ,15,4 ,15,4 ,5 ,4 ,4 ,4 ,4 ,
                    7 ,15,15,15,5 ,15,15,4 ,10,4 ,15,15,5 ,15,15,15,8 ,
                    4 ,4 ,4 ,4 ,5 ,4 ,15,4 ,4 ,4 ,15,4 ,5 ,4 ,4 ,4 ,4 ,
                    15,15,15,4 ,5 ,4 ,15,15,0 ,15,15,4 ,5 ,4 ,15,15,15,
                    4 ,4 ,4 ,4 ,5 ,4 ,15,4 ,4 ,4 ,15,4 ,5 ,4 ,4 ,4 ,4 ,
                    4 ,5 ,5 ,5 ,5 ,5 ,5 ,5 ,4 ,5 ,5 ,5 ,5 ,5 ,5 ,5 ,4 ,
                    4 ,5 ,4 ,4 ,5 ,4 ,4 ,5 ,4 ,5 ,4 ,4 ,5 ,4 ,4 ,5 ,4 ,
                    4 ,6 ,5 ,4 ,5 ,5 ,5 ,5 ,5 ,5 ,5 ,5 ,5 ,4 ,5 ,6 ,4 ,
                    4 ,4 ,5 ,4 ,5 ,4 ,4 ,4 ,5 ,4 ,4 ,4 ,5 ,4 ,5 ,4 ,4 ,
                    4 ,5 ,5 ,5 ,5 ,5 ,5 ,5 ,5 ,5 ,5 ,5 ,5 ,5 ,5 ,5 ,4 ,
                    4 ,4 ,4 ,4 ,4 ,4 ,4 ,4 ,4 ,4 ,4 ,4 ,4 ,4 ,4 ,4 ,4 ,
            };

    void init(Context context)
    {
        bmp = new Bitmap[21];

        bmp[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.pacman_right);
        bmp[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.pacman_left);
        bmp[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.pacman_up);
        bmp[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.pacman_down);
        bmp[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.wall);
        bmp[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.small_meal);
        bmp[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.special_meal);
        bmp[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.connector_left);
        bmp[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.connector_right);
        bmp[9] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ghost_door);
        bmp[10] = BitmapFactory.decodeResource(context.getResources(), R.drawable.pinky);
        bmp[11] = BitmapFactory.decodeResource(context.getResources(), R.drawable.inky);
        bmp[12] = BitmapFactory.decodeResource(context.getResources(), R.drawable.blinky);
        bmp[13] = BitmapFactory.decodeResource(context.getResources(), R.drawable.clyde);
        bmp[14] = BitmapFactory.decodeResource(context.getResources(), R.drawable.cherry);
        bmp[15] = BitmapFactory.decodeResource(context.getResources(), R.drawable.empty);
        bmp[16] = BitmapFactory.decodeResource(context.getResources(), R.drawable.pacman_boosted_r);
        bmp[17] = BitmapFactory.decodeResource(context.getResources(), R.drawable.pacman_boosted_l);
        bmp[18] = BitmapFactory.decodeResource(context.getResources(), R.drawable.pacman_boosted_u);
        bmp[19] = BitmapFactory.decodeResource(context.getResources(), R.drawable.pacman_boosted_d);
        bmp[20] = BitmapFactory.decodeResource(context.getResources(), R.drawable.blue_ghost);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        width = w / y_level;
        height = h / x_level;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        for (int i = 0; i < x_level; i++) {
            for (int j = 0; j < y_level; j++) {
                canvas.drawBitmap(bmp[level1[i * 17 + j]], null,
                        new Rect(j * width, i * height, (j + 1) * width, (i + 1) * height), null);
            }
            // pohyb ducha
            // zajisti prekresleni
            // invalidate();
        }



    }
}
