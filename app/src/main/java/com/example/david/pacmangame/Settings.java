package com.example.david.pacmangame;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

public class Settings extends Activity
{
    public static int diff = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        View viewBack2 = findViewById(R.id.backButton2);

        viewBack2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent backMenu2 = new Intent(Settings.this, MainActivity.class);
                Settings.this.startActivity(backMenu2);
            }
        });

        View easy = findViewById(R.id.button);

        easy.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                diff = 10;

                SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = mPrefs.edit();
                editor.putInt("1", diff);
                editor.commit();

                Log.d("Difficulty", "easy " + diff);
            }
        });

        View hard = findViewById(R.id.button2);

        hard.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                diff = 5;

                SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = mPrefs.edit();
                editor.putInt("1", diff);
                editor.commit();

                Log.d("Difficulty", "hard " + diff);
            }
        });

        View insane = findViewById(R.id.button3);

        insane.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                diff = 1;

                SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = mPrefs.edit();
                editor.putInt("1", diff);
                editor.commit();

                Log.d("Difficulty", "insane" + diff);
            }
        });
    }
}
