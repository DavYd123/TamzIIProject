package com.example.david.pacmangame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.view.View;

public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View button1 = findViewById(R.id.start);

        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent Game = new Intent(MainActivity.this, Game.class);
                MainActivity.this.startActivity(Game);
            }
        });

        View button2 = findViewById(R.id.settings);

        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent startSettings = new Intent(MainActivity.this, Settings.class);
                MainActivity.this.startActivity(startSettings);
            }
        });

        View button3 = findViewById(R.id.score);

        button3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent startScore = new Intent(MainActivity.this, Score.class);
                MainActivity.this.startActivity(startScore);
            }
        });

        View button4 = findViewById(R.id.about);

        button4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent startAbout = new Intent(MainActivity.this, About.class);
                MainActivity.this.startActivity(startAbout);
            }
        });
    }
}
