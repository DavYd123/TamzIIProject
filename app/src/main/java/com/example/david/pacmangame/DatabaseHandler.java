package com.example.david.pacmangame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper
{

    // verze databaze
    private static final int DATABASE_VERSION = 1;

    // nazev
    private static final String DATABASE_NAME = "game";

    // table name
    private static final String TABLE_SCORE = "score";

    // names of table items
    private static final String KEY_ID_SCORE = "_id";
    private static final String KEY_SCORE = "score_value";

    public DatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // creating
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_SCORE_TABLE = "CREATE TABLE " + TABLE_SCORE + "("
                + KEY_ID_SCORE + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_SCORE + " TEXT" + ")";

        db.execSQL(CREATE_SCORE_TABLE);
    }

    // updating
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORE);

        // Create tables again
        onCreate(db);
    }

    // add new score to database
    public void addScore(int score)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_SCORE, score); // score value

        // Inserting Values
        db.insert(TABLE_SCORE, null, values);

        db.close();
    }

    // getting database values
    public String[] getAllScores()
    {

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SCORE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list

        int i = 0;

        String[] data = new String[cursor.getCount()];

        while (cursor.moveToNext()) {

            data[i] = cursor.getString(1);

            i++;

        }
        cursor.close();
        db.close();
        // return score array
        return data;
    }
}