package com.example.david.pacmangame;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

public class NewGame extends View
{
    int width;
    int height;

    // ukazatel skore
    static int score = 0;
    static int life_total = 3;

    // location
    float x1;
    float y1;

    // velikost levelu
    int x_level = 19;
    int y_level = 17;

    // where is pacman
    static int pacman_position_x = 8;
    static int pacman_position_y = 11;

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

    public static int level1[] =
            {
                    4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,
                    4, 5, 5, 5, 5, 5, 5, 5, 4, 5, 5, 5, 5, 5, 5, 5, 4,
                    4, 5, 4, 4, 5, 4, 4, 5, 4, 5, 4, 4, 5, 4, 4, 5, 4,
                    4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 4,
                    4, 5, 4, 4, 5, 4, 5, 4, 4, 4, 5, 4, 5, 4, 4, 5, 4,
                    4, 5, 5, 5, 5, 4, 5, 5, 4, 5, 5, 4, 5, 5, 5, 5, 4,
                    4, 4, 4, 4, 5, 4, 4, 15, 4, 15, 4, 4, 5, 4, 4, 4, 4,
                    15, 15, 15, 4, 5, 4, 15, 15, 15, 15, 15, 4, 5, 4, 15, 15, 15,
                    4, 4, 4, 4, 5, 4, 15, 4, 15, 4, 15, 4, 5, 4, 4, 4, 4,
                    7, 15, 15, 15, 5, 15, 15, 4, 10, 4, 15, 15, 5, 15, 15, 15, 8,
                    4, 4, 4, 4, 5, 4, 15, 4, 4, 4, 15, 4, 5, 4, 4, 4, 4,
                    15, 15, 15, 4, 5, 4, 15, 15, 0, 15, 15, 4, 5, 4, 15, 15, 15,
                    4, 4, 4, 4, 5, 4, 15, 4, 4, 4, 15, 4, 5, 4, 4, 4, 4,
                    4, 5, 5, 5, 5, 5, 5, 5, 4, 5, 5, 5, 5, 5, 5, 5, 4,
                    4, 5, 4, 4, 5, 4, 4, 5, 4, 5, 4, 4, 5, 4, 4, 5, 4,
                    4, 5, 5, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 4, 5, 5, 4,
                    4, 4, 5, 4, 5, 4, 4, 4, 5, 4, 4, 4, 5, 4, 5, 4, 4,
                    4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 4,
                    4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,
            };

    private String level2;

    String[] stringMaps;
    String[] map1;
    String[] map2;

    public static int[] mapa1;
    public static int[] mapa2;

    // random generator
    Random rand = new Random();

    // position of ghost
    static int ghost_x = 8;
    static int ghost_y = 9;

    // delay movement
    int x;

    // starting movement up
    int n;

    // temp for memory of array
    int temp0 = 0;
    int temp1 = 0;
    int temp2 = 0;
    int temp3 = 0;

    // pomocna pro osetreni vyhry
    int temp = 0;

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

    private void loadLevels() throws IOException
    {
        LevelParser levelParser = new LevelParser();

        try
        {
            InputStream inputStream = getResources().openRawResource(R.raw.levels);

            if (inputStream == null)
            {
                throw new Error("Cannot open levels resource input stream");
            }

            level2 = levelParser.convertStreamToString(inputStream);

            stringMaps = level2.split(";");
            map1 = stringMaps[0].split(" ");
            map2 = stringMaps[1].split(" ");

            mapa1 = new int[map1.length];
            mapa2 = new int[map2.length];

            for (int i = 0; i < mapa1.length; i++)
            {
                mapa1[i] = Integer.parseInt(map1[i]);
            }

            for (int i = 0; i < mapa2.length; i++)
            {
                mapa2[i] = Integer.parseInt(map2[i]);
            }

            } catch (IOException e)
        {
            throw new Error("Level load failed");
        }
    }

    public static void switchLevel1()
    {
        for ( int i = 0; i < level1.length; ++i )
        {
            level1[i] = mapa1[i];
        }

        if(level1[195] == 0)
        {
            level1[195] = 15;
        }

        pacman_position_x = 8;
        pacman_position_y = 11;

        ghost_x = 8;
        ghost_y = 9;

        level1[pacman_position_y * 17 + pacman_position_x ] = 0;

        life_total = 3;
        score = 0;
        Log.d("this is my array", "arr: " + level1);
        Log.d("this is my array", "arr: " + mapa1);
    }

    public static void switchLevel2()
    {
        for ( int i = 0; i < level1.length; ++i )
        {
            level1[i] = mapa2[i];
        }

        if(level1[195] == 0)
        {
            level1[195] = 15;
        }

        pacman_position_x = 8;
        pacman_position_y = 11;

        ghost_x = 8;
        ghost_y = 9;

        level1[pacman_position_y * 17 + pacman_position_x] = 0;

        life_total = 3;
        score = 0;
        //Log.d("this is my array", "arr: " + level1);
        // Log.d("this is my array", "arr: " + mapa2);
    }


    private void saveScore()
    {
        // name for preference = IDvalue, value = 0
        SharedPreferences mPrefs = this.getContext().getSharedPreferences("IDvalue", 0);
        SharedPreferences.Editor editor = mPrefs.edit();
        // give key value = 1, and putting highscore with this key value
        editor.putInt("1", score);
        editor.commit();
    }

    public int validateEndGame(int temp)
    {
        // spocita kolik je celkem mealu v poli
        for (int i = 0; i < level1.length; i ++)
        {
            if(level1[i] == 5 || level1[i] == 6)
            {
                temp += 1;
            }
        }
        return temp;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        width = w / y_level;
        height = h / x_level;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        try
        {
            loadLevels();
            //Log.d("this is my array", "arr: " + level2);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        // pokud dojdou zivoty, tak vrati do menu
        if (life_total == 0)
        {
            saveScore();
            System.exit(0);
        }

        for (int i = 0; i < x_level; i++)
        {
            for (int j = 0; j < y_level; j++)
            {
                canvas.drawBitmap(bmp[level1[i * 17 + j]], null,
                        new Rect(j * width, i * height, (j + 1) * width, (i + 1) * height), null);
            }

            // vypisovani skore a zivotu
            TextView livescore = (TextView) ((Activity)getContext()).findViewById(R.id.score1);
            livescore.setText(Integer.toString(score));

            TextView lifes = (TextView) ((Activity)getContext()).findViewById(R.id.lives1);
            lifes.setText(Integer.toString(life_total));
        }

        // n = rand.nextInt(4);

        // Log.d("this is my array", "arr: " + n);

        for (int i = 0; i < level1.length; i ++)
        {
            if(level1[i] == 5 || level1[i] == 6)
            {
                temp += 1;
            }
        }

        if(temp == 0)
        {
            saveScore();
            System.exit(0);
        }

       // Log.d("temp", "arr: " + temp);

        temp = 0;

        // move ghost up
        // delay movement
        if(x == 5)
        {
            if (n == 0)
            {
                // 4 stena, 5 meal, 15 empty
                // kdyz je misto volne a pomocna 0
                if (level1[(ghost_y - 1) * 17 + ghost_x] == 15 && temp0 == 0)
                {
                    level1[ghost_y * 17 + ghost_x] = 15;
                    level1[(ghost_y - 1) * 17 + ghost_x] = 10;
                    ghost_y -= 1;
                }
                // kdyz je misto volne a pomocna 1, tak vykresli meal na puvodni
                else if (level1[(ghost_y - 1) * 17 + ghost_x] == 15 && temp0 == 1)
                {
                    level1[ghost_y * 17 + ghost_x] = 5;
                    level1[(ghost_y - 1) * 17 + ghost_x] = 10;
                    ghost_y -= 1;
                    temp0 = 0;
                }
                // misto je meal a pomocna 0
                else if (level1[(ghost_y - 1) * 17 + ghost_x] == 5 && temp0 == 0)
                {
                    level1[ghost_y * 17 + ghost_x] = 5;
                    level1[(ghost_y - 1) * 17 + ghost_x] = 10;
                    ghost_y -= 1;
                    temp0++;
                }
                // misto je meal a pomocna 1, vykresli meal na puvodni
                else if (level1[(ghost_y - 1) * 17 + ghost_x] == 5 && temp0 == 1)
                {
                    level1[ghost_y * 17 + ghost_x] = 5;
                    level1[(ghost_y - 1) * 17 + ghost_x] = 10;
                    ghost_y -= 1;
                    temp0 = 0;
                }
                // dotknuti pacmana
                else if (level1[(ghost_y - 1) * 17 + ghost_x] == 0 || level1[(ghost_y - 1) * 17 + ghost_x] == 1 || level1[(ghost_y - 1) * 17 + ghost_x] == 2 || level1[(ghost_y - 1) * 17 + ghost_x] == 3)
                {
                    level1[ghost_y * 17 + ghost_x] = 15;
                    level1[(ghost_y - 1) * 17 + ghost_x] = 10;
                    ghost_y -= 1;
                    life_total -= 1;
                    pacman_position_x = 8;
                    pacman_position_y = 11;
                    level1[pacman_position_y * 17 + pacman_position_x] = 0;
                }

                invalidate();
            }

            // move ghost down
            if (n == 1)
            {
                // volno a pomocna 0
                if (level1[(ghost_y + 1) * 17 + ghost_x] == 15 && temp1 == 0)
                {
                    level1[ghost_y * 17 + ghost_x] = 15;
                    level1[(ghost_y + 1) * 17 + ghost_x] = 10;
                    ghost_y += 1;
                }
                // volno a pomocna 1
                else if (level1[(ghost_y + 1) * 17 + ghost_x] == 15 && temp1 == 1)
                {
                    level1[ghost_y * 17 + ghost_x] = 5;
                    level1[(ghost_y + 1) * 17 + ghost_x] = 10;
                    ghost_y += 1;
                    temp1 = 0;
                }
                // meal a pom 0
                else if (level1[(ghost_y + 1) * 17 + ghost_x] == 5 && temp1 == 0)
                {
                    level1[ghost_y * 17 + ghost_x] = 5;
                    level1[(ghost_y + 1) * 17 + ghost_x] = 10;
                    ghost_y += 1;
                    temp1++;
                }
                // meal a pom 1
                else if (level1[(ghost_y + 1) * 17 + ghost_x] == 5 && temp1 == 1)
                {
                    level1[ghost_y * 17 + ghost_x] = 5;
                    level1[(ghost_y + 1) * 17 + ghost_x] = 10;
                    ghost_y += 1;
                    temp1 = 0;
                }

                else if (level1[(ghost_y + 1) * 17 + ghost_x] == 0 || level1[(ghost_y + 1) * 17 + ghost_x] == 1 || level1[(ghost_y + 1) * 17 + ghost_x] == 2 || level1[(ghost_y + 1) * 17 + ghost_x] == 3)
                {
                    level1[ghost_y * 17 + ghost_x] = 5;
                    level1[(ghost_y + 1) * 17 + ghost_x] = 10;
                    ghost_y += 1;
                    life_total -= 1;
                    pacman_position_x = 8;
                    pacman_position_y = 11;
                    level1[pacman_position_y * 17 + pacman_position_x] = 0;
                }

                invalidate();
            }


            // move ghost left
            if (n == 2)
            {
                // volno a pom 0
                if (level1[ghost_y * 17 + ghost_x - 1] == 15 && temp2 == 0)
                {
                    level1[ghost_y * 17 + ghost_x] = 15;
                    level1[ghost_y * 17 + ghost_x - 1] = 10;
                    ghost_x -= 1;
                }
                // volno a pom 1
                else if (level1[ghost_y * 17 + ghost_x - 1] == 15 && temp2 == 1)
                {
                    level1[ghost_y * 17 + ghost_x] = 5;
                    level1[ghost_y * 17 + ghost_x - 1] = 10;
                    ghost_x -= 1;
                    temp2 = 0;
                }
                // meal a pom 0
                else if (level1[ghost_y * 17 + ghost_x - 1] == 5 && temp2 == 0)
                {
                    level1[ghost_y * 17 + ghost_x] = 5;
                    level1[ghost_y * 17 + ghost_x - 1] = 10;
                    ghost_x -= 1;
                    temp2++;
                }
                // meal a pom 1
                else if (level1[ghost_y * 17 + ghost_x - 1] == 5 && temp2 == 1)
                {
                    level1[ghost_y * 17 + ghost_x] = 5;
                    level1[ghost_y * 17 + ghost_x - 1] = 10;
                    ghost_x -= 1;
                    temp2 = 0;
                }

                else if (level1[ghost_y * 17 + ghost_x - 1] == 0 || level1[ghost_y * 17 + ghost_x - 1] == 1 || level1[ghost_y * 17 + ghost_x - 1] == 2 || level1[ghost_y * 17 + ghost_x - 1] == 3)
                {
                    level1[ghost_y * 17 + ghost_x] = 5;
                    level1[ghost_y * 17 + ghost_x - 1] = 10;
                    ghost_x -= 1;
                    life_total -= 1;
                    pacman_position_x = 8;
                    pacman_position_y = 11;
                    level1[pacman_position_y * 17 + pacman_position_x] = 0;
                }

                else if (level1[ghost_y * 17 + ghost_x - 1] == 7)
                {
                    level1[ghost_y * 17 + ghost_x] = 15;
                    level1[ghost_y * 17 + ghost_x + 14] = 10;
                    ghost_x += 14;
                }

                invalidate();
            }


            // move ghost right
            if (n == 3) {
                // volno a 0
                if (level1[ghost_y * 17 + ghost_x + 1] == 15 && temp3 == 0)
                {
                    level1[ghost_y * 17 + ghost_x] = 15;
                    level1[ghost_y * 17 + ghost_x + 1] = 10;
                    ghost_x += 1;
                }
                // volno a 1
                else if (level1[ghost_y * 17 + ghost_x + 1] == 15 && temp3 == 1)
                {
                    level1[ghost_y * 17 + ghost_x] = 5;
                    level1[ghost_y * 17 + ghost_x + 1] = 10;
                    ghost_x += 1;
                    temp3 = 0;
                }
                // meal a 0
                else if (level1[ghost_y * 17 + ghost_x + 1] == 5 && temp3 == 0)
                {
                    level1[ghost_y * 17 + ghost_x] = 5;
                    level1[ghost_y * 17 + ghost_x + 1] = 10;
                    ghost_x += 1;
                    temp3++;
                }
                // meal a 1
                else if (level1[ghost_y * 17 + ghost_x + 1] == 5 && temp3 == 1)
                {
                    level1[ghost_y * 17 + ghost_x] = 5;
                    level1[ghost_y * 17 + ghost_x + 1] = 10;
                    ghost_x += 1;
                    temp3 = 0;
                }

                else if (level1[ghost_y * 17 + ghost_x + 1] == 0 || level1[ghost_y * 17 + ghost_x + 1] == 1 || level1[ghost_y * 17 + ghost_x + 1] == 2 || level1[ghost_y * 17 + ghost_x + 1] == 3)
                {
                    level1[ghost_y * 17 + ghost_x] = 5;
                    level1[ghost_y * 17 + ghost_x + 1] = 10;
                    ghost_x += 1;
                    life_total -= 1;
                    pacman_position_x = 8;
                    pacman_position_y = 11;
                    level1[pacman_position_y * 17 + pacman_position_x] = 0;
                }

                else if (level1[ghost_y * 17 + ghost_x + 1] == 8)
                {
                    level1[ghost_y * 17 + ghost_x] = 15;
                    level1[ghost_y * 17 + ghost_x - 14] = 10;
                    ghost_x -= 14;
                }

                invalidate();
            }

            x = 0;
        }

        x++;

        invalidate();

       // Log.d("my x", "arr: " + x);
    }

    public boolean onTouchEvent(MotionEvent touchEvent)
    {
        switch (touchEvent.getAction())
        {
            // when screen is pushed, get location
            case MotionEvent.ACTION_DOWN:
                {
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();

                n = rand.nextInt(4);

                break;
            }

            case MotionEvent.ACTION_UP:
                {
                // move right
                if (x1 > 450 && y1 > 450 && y1 < 800) {
                    // upravit, jednoduche osetreni aby nemazal steny
                    if (level1[pacman_position_y * 17 + pacman_position_x + 1] != 4) {
                        // pokud connector doprava, tak presun ke connectoru vlevo
                        if (level1[pacman_position_y * 17 + pacman_position_x + 1] == 8) {
                            level1[pacman_position_y * 17 + pacman_position_x] = 15;
                            level1[pacman_position_y * 17 + pacman_position_x - 14] = 0;
                            pacman_position_x -= 14;

                            invalidate();

                        }
                        // pokud sezere small meal +5 score a zmeni na prazdne pole
                        else if (level1[pacman_position_y * 17 + pacman_position_x + 1] == 5) {
                            level1[pacman_position_y * 17 + pacman_position_x] = 15;
                            level1[pacman_position_y * 17 + pacman_position_x + 1] = 0;
                            pacman_position_x += 1;
                            score += 5;

                            invalidate();
                        }
                        // pokud sni special meal, +50 score a boostne na 10 premisteni ?
                        else if (level1[pacman_position_y * 17 + pacman_position_x + 1] == 6) {
                            level1[pacman_position_y * 17 + pacman_position_x] = 15;
                            level1[pacman_position_y * 17 + pacman_position_x + 1] = 16;
                            pacman_position_x += 1;
                            score += 50;

                            invalidate();
                        }

                        // tresen prida +100
                        else if (level1[pacman_position_y * 17 + pacman_position_x + 1] == 14) {
                            level1[pacman_position_y * 17 + pacman_position_x] = 15;
                            level1[pacman_position_y * 17 + pacman_position_x + 1] = 0;
                            pacman_position_x += 1;
                            score += 100;

                            invalidate();
                        }

                        // pokud se dotkne hrac ducha -1 zivot
                        else if (level1[pacman_position_y * 17 + pacman_position_x + 1] == 10)
                        {
                            level1[pacman_position_y * 17 + pacman_position_x] = 15;
                            level1[pacman_position_y * 17 + pacman_position_x + 1] = 10;
                            life_total -= 1;
                            pacman_position_x = 8;
                            pacman_position_y = 11;
                            level1[pacman_position_y * 17 + pacman_position_x] = 0;
                            invalidate();
                        } else {
                            level1[pacman_position_y * 17 + pacman_position_x] = 15;
                            level1[pacman_position_y * 17 + pacman_position_x + 1] = 0;
                            pacman_position_x += 1;

                            invalidate();
                        }

                        // +200 score = +1 life
                    }
                }

                // left
                    // if(x1 - y1 > SWIPE_MIN_DISTANCE && Math.abs(10) >     SWIPE_THRESHOLD_VELOCITY)
                if (x1 < 450 && y1 > 450 && y1 < 800) {
                    if (level1[pacman_position_y * 17 + pacman_position_x - 1] != 4) {
                        // pokud connector doleva, tak presun ke connectoru vpravo
                        if (level1[pacman_position_y * 17 + pacman_position_x - 1] == 7) {
                            level1[pacman_position_y * 17 + pacman_position_x] = 15;
                            level1[pacman_position_y * 17 + pacman_position_x + 14] = 1;
                            pacman_position_x += 14;

                            invalidate();
                        }
                        // pokud sezere small meal +5 score a zmeni na prazdne pole
                        else if (level1[pacman_position_y * 17 + pacman_position_x - 1] == 5) {
                            level1[pacman_position_y * 17 + pacman_position_x] = 15;
                            level1[pacman_position_y * 17 + pacman_position_x - 1] = 1;
                            pacman_position_x -= 1;
                            score += 5;

                            invalidate();
                        }
                        // pokud sni special meal, +50 score a boostne na 10 premisteni ?
                        else if (level1[pacman_position_y * 17 + pacman_position_x - 1] == 6) {
                            level1[pacman_position_y * 17 + pacman_position_x] = 15;
                            level1[pacman_position_y * 17 + pacman_position_x - 1] = 17;
                            pacman_position_x -= 1;
                            score += 50;

                            invalidate();
                        }

                        // tresen prida +100
                        else if (level1[pacman_position_y * 17 + pacman_position_x - 1] == 14) {
                            level1[pacman_position_y * 17 + pacman_position_x] = 15;
                            level1[pacman_position_y * 17 + pacman_position_x - 1] = 1;
                            pacman_position_x -= 1;
                            score += 100;

                            invalidate();
                        }

                        // pokud se dotkne hrac ducha -1 zivot
                        else if (level1[pacman_position_y * 17 + pacman_position_x - 1] == 10) {
                            level1[pacman_position_y * 17 + pacman_position_x] = 15;
                            level1[pacman_position_y * 17 + pacman_position_x - 1] = 10;
                            life_total -= 1;
                            pacman_position_x = 8;
                            pacman_position_y = 11;
                            level1[pacman_position_y * 17 + pacman_position_x] = 0;
                            invalidate();
                        }
                        else
                            {
                            level1[pacman_position_y * 17 + pacman_position_x] = 15;
                            level1[pacman_position_y * 17 + pacman_position_x - 1] = 1;
                            pacman_position_x -= 1;

                            invalidate();
                        }
                        // +200 score = +1 life
                    }
                }

                // up
                if (y1 < 450) {
                    if (level1[(pacman_position_y - 1) * 17 + pacman_position_x] != 4) {
                        // pokud sezere small meal +5 score a zmeni na prazdne pole
                        if (level1[(pacman_position_y - 1) * 17 + pacman_position_x] == 5) {
                            level1[pacman_position_y * 17 + pacman_position_x] = 15;
                            level1[(pacman_position_y - 1) * 17 + pacman_position_x] = 2;
                            pacman_position_y -= 1;
                            score += 5;

                            invalidate();
                        }

                        // pokud sni special meal, +50 score a boostne na 10 premisteni ?
                        else if (level1[(pacman_position_y - 1) * 17 + pacman_position_x] == 6) {
                            level1[pacman_position_y * 17 + pacman_position_x] = 15;
                            level1[(pacman_position_y - 1) * 17 + pacman_position_x] = 18;
                            pacman_position_y -= 1;
                            score += 50;

                            invalidate();
                        }

                        // tresen prida +100
                        else if (level1[(pacman_position_y - 1) * 17 + pacman_position_x] == 14) {
                            level1[pacman_position_y * 17 + pacman_position_x] = 15;
                            level1[(pacman_position_y - 1) * 17 + pacman_position_x] = 2;
                            pacman_position_y -= 1;
                            score += 100;

                            invalidate();
                        }

                        // pokud se dotkne hrac ducha -1 zivot
                        else if (level1[(pacman_position_y - 1) * 17 + pacman_position_x] == 10) {
                            level1[pacman_position_y * 17 + pacman_position_x] = 15;
                            level1[(pacman_position_y - 1) * 17 + pacman_position_x] = 10;
                            pacman_position_y -= 1;
                            life_total -= 1;
                            pacman_position_x = 8;
                            pacman_position_y = 11;
                            level1[pacman_position_y * 17 + pacman_position_x] = 0;
                            invalidate();
                        } else {
                            level1[pacman_position_y * 17 + pacman_position_x] = 15;
                            level1[(pacman_position_y - 1) * 17 + pacman_position_x] = 2;
                            pacman_position_y -= 1;

                            invalidate();
                        }
                    }
                }

                // down
                if (y1 > 800) {
                    if (level1[(pacman_position_y + 1) * 17 + pacman_position_x] != 4) {
                        // pokud sezere small meal +5 score a zmeni na prazdne pole
                        if (level1[(pacman_position_y + 1) * 17 + pacman_position_x] == 5) {
                            level1[pacman_position_y * 17 + pacman_position_x] = 15;
                            level1[(pacman_position_y + 1) * 17 + pacman_position_x] = 3;
                            pacman_position_y += 1;
                            score += 5;

                            invalidate();
                        }

                        // pokud sni special meal, +50 score a boostne na 10 premisteni ?
                        else if (level1[(pacman_position_y + 1) * 17 + pacman_position_x] == 6) {
                            level1[pacman_position_y * 17 + pacman_position_x] = 15;
                            level1[(pacman_position_y + 1) * 17 + pacman_position_x] = 19;
                            pacman_position_y += 1;
                            score += 50;

                            invalidate();
                        }

                        // tresen prida +100
                        else if (level1[(pacman_position_y + 1) * 17 + pacman_position_x] == 14) {
                            level1[pacman_position_y * 17 + pacman_position_x] = 15;
                            level1[(pacman_position_y + 1) * 17 + pacman_position_x] = 3;
                            pacman_position_y += 1;
                            score += 100;

                            invalidate();
                        }

                        // pokud se dotkne hrac ducha -1 zivot
                        else if (level1[(pacman_position_y + 1) * 17 + pacman_position_x] == 10)
                        {
                            level1[pacman_position_y * 17 + pacman_position_x] = 15;
                            level1[(pacman_position_y + 1) * 17 + pacman_position_x] = 10;
                            pacman_position_y = 11;
                            pacman_position_x = 8;
                            level1[pacman_position_y * 17 + pacman_position_x] = 0;
                            life_total -= 1;

                            invalidate();
                        } else {
                            level1[pacman_position_y * 17 + pacman_position_x] = 15;
                            level1[(pacman_position_y + 1) * 17 + pacman_position_x] = 3;
                            pacman_position_y += 1;

                            invalidate();
                        }
                    }
                }
            }
        }

        return true;
    }
}