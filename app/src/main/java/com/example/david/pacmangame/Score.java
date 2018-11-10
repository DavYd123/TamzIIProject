package com.example.david.pacmangame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Score extends Activity
{
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
    }
}
