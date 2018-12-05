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

public class NewGame extends View
{
    int width;
    int height;

    // ukazatel skore
    int score = 0;
    int life_total = 3;

    // location
    float x1;
    float y1;

    // velikost levelu
    int x_level = 19;
    int y_level = 17;

    // where is pacman
    int pacman_position_x = 8;
    int pacman_position_y = 11;

    int meal = 0;
    int meal2 = 0;

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
                    4, 6, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 4,
                    4, 5, 4, 4, 5, 4, 5, 4, 4, 4, 5, 4, 5, 4, 4, 5, 4,
                    4, 5, 5, 5, 5, 4, 5, 5, 4, 5, 5, 4, 5, 5, 5, 5, 4,
                    4, 4, 4, 4, 5, 4, 4, 15, 4, 15, 4, 4, 5, 4, 4, 4, 4,
                    15, 15, 15, 4, 5, 4, 15, 15, 14, 15, 15, 4, 5, 4, 15, 15, 15,
                    4, 4, 4, 4, 5, 4, 15, 4, 15, 4, 15, 4, 5, 4, 4, 4, 4,
                    7, 15, 15, 15, 5, 15, 15, 4, 10, 4, 15, 15, 5, 15, 15, 15, 8,
                    4, 4, 4, 4, 5, 4, 15, 4, 4, 4, 15, 4, 5, 4, 4, 4, 4,
                    15, 15, 15, 4, 5, 4, 15, 15, 0, 15, 15, 4, 5, 4, 15, 15, 15,
                    4, 4, 4, 4, 5, 4, 15, 4, 4, 4, 15, 4, 5, 4, 4, 4, 4,
                    4, 5, 5, 5, 5, 5, 5, 5, 4, 5, 5, 5, 5, 5, 5, 5, 4,
                    4, 5, 4, 4, 5, 4, 4, 5, 4, 5, 4, 4, 5, 4, 4, 5, 4,
                    4, 6, 5, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 4, 5, 6, 4,
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

        Log.d("this is my array", "arr: " + level1);
        Log.d("this is my array", "arr: " + mapa2);
    }


    private void saveScore()
    {
        //get text view
        TextView scoreView1 = (TextView) ((Activity)getContext()).findViewById(R.id.scoreView);
        //get shared prefs
        SharedPreferences scorePrefs = this.getContext().getSharedPreferences("Prefs", Context.MODE_PRIVATE);
        //get scores
        //int savedScores = scorePrefs.getInt("1", score);

        Log.d("this is my array", "arr: " + scorePrefs);
        //display scores
        //scoreView1.setText(savedScores);
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
            Log.d("this is my array", "arr: " + level2);
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

        // vyhrana hra
        if (meal == 116 && meal2 == 4)
        {
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
                            meal += 1;

                            invalidate();
                        }
                        // pokud sni special meal, +50 score a boostne na 10 premisteni ?
                        else if (level1[pacman_position_y * 17 + pacman_position_x + 1] == 6) {
                            level1[pacman_position_y * 17 + pacman_position_x] = 15;
                            level1[pacman_position_y * 17 + pacman_position_x + 1] = 16;
                            pacman_position_x += 1;
                            score += 50;
                            meal2 += 1;

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
                        else if (level1[pacman_position_y * 17 + pacman_position_x + 1] == 10) {
                            level1[pacman_position_y * 17 + pacman_position_x] = 15;
                            level1[pacman_position_y * 17 + pacman_position_x + 1] = 10;
                            life_total -= 1;

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
                            meal += 1;

                            invalidate();
                        }
                        // pokud sni special meal, +50 score a boostne na 10 premisteni ?
                        else if (level1[pacman_position_y * 17 + pacman_position_x - 1] == 6) {
                            level1[pacman_position_y * 17 + pacman_position_x] = 15;
                            level1[pacman_position_y * 17 + pacman_position_x - 1] = 17;
                            pacman_position_x -= 1;
                            score += 50;
                            meal2 += 1;

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

                            invalidate();
                        } else {
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
                            meal += 1;

                            invalidate();
                        }

                        // pokud sni special meal, +50 score a boostne na 10 premisteni ?
                        else if (level1[(pacman_position_y - 1) * 17 + pacman_position_x] == 6) {
                            level1[pacman_position_y * 17 + pacman_position_x] = 15;
                            level1[(pacman_position_y - 1) * 17 + pacman_position_x] = 18;
                            pacman_position_y -= 1;
                            score += 50;
                            meal2 += 1;

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
                            meal += 1;

                            invalidate();
                        }

                        // pokud sni special meal, +50 score a boostne na 10 premisteni ?
                        else if (level1[(pacman_position_y + 1) * 17 + pacman_position_x] == 6) {
                            level1[pacman_position_y * 17 + pacman_position_x] = 15;
                            level1[(pacman_position_y + 1) * 17 + pacman_position_x] = 19;
                            pacman_position_y += 1;
                            score += 50;
                            meal2 += 1;

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
                            life_total -= 3;

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