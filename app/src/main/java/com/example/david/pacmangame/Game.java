package com.example.david.pacmangame;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import static com.example.david.pacmangame.NewGame.*;

public class Game extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        final View button7 = findViewById(R.id.popUp);

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                // create instance of pop up menu
                PopupMenu popup = new PopupMenu(Game.this, button7);

                popup.getMenuInflater().inflate(R.menu.pop_up, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item)
                    {
                        int id = item.getItemId();
                        switch(id)
                        {
                            case R.id.one:
                                Toast.makeText(
                                        Game.this,
                                        "Level 1",
                                        Toast.LENGTH_SHORT
                                ).show();
                                switchLevel1();
                                return true;
                            case R.id.two:
                                Toast.makeText(
                                        Game.this,
                                        "Level 2",
                                        Toast.LENGTH_SHORT
                                ).show();
                                switchLevel2();
                                return true;
                            case R.id.three:
                                System.exit(0);
                                return true;
                            default:
                                return false;
                        }
                    }
                });

                popup.show(); // show pop up menu
            }
        });

    }
}
