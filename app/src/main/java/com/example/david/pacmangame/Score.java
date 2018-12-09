package com.example.david.pacmangame;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.security.AccessController.getContext;

public class Score extends Activity
{
    static String score222[];
    static String finalni1;
    List<Integer> list = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        View viewBack3 = findViewById(R.id.backButton3);

        viewBack3.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent backMenu3 = new Intent(Score.this, MainActivity.class);
                Score.this.startActivity(backMenu3);
            }
        });

        /*
        SharedPreferences mPrefs = getSharedPreferences("IDvalue",0);
        int score2 = mPrefs.getInt("1", score);*/

        TextView scoreView = (TextView) findViewById(R.id.scoreView);

        DatabaseHandler db = new DatabaseHandler(this);

        score222 = db.getAllScores();

        for (int k = 0 ; k < score222.length; k++)
        {
            if(Integer.parseInt(score222[k]) != 0) {
                list.add(Integer.parseInt(score222[k]));
            }
        }

        Collections.sort(list, Collections.reverseOrder());

        finalni1 = TextUtils.join("\n", list);

        Log.d("this is my array", "arr: " + finalni1);
        scoreView.setText(finalni1);
    }
}
