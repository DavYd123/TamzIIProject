package com.example.david.pacmangame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Settings extends Activity
{
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
    }
}
