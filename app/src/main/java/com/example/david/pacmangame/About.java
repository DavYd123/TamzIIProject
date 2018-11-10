package com.example.david.pacmangame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class About extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        View viewBack1 = findViewById(R.id.backButton1);

        viewBack1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent backMenu1 = new Intent(About.this, MainActivity.class);
                About.this.startActivity(backMenu1);
            }
        });
    }
}
