package com.example.david.pacmangame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LevelParser
{
    // x - wall 4
    // . - small meal 5
    // o - big meal 6
    // - - empty space 15
    // c - cherry 14
    // l - left arrow 7
    // r - right arrow 8
    // g - ghost 10
    // h - pacman 0

    // ; - level separator

    public LevelParser() throws IOException
    {

    }

    public static String convertStreamToString(InputStream is) throws IOException
    {
        if (is != null)
        {
            StringBuilder sb = new StringBuilder();
            String line;

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                while ((line = reader.readLine()) != null)
                {
                    sb.append(line);
                }

            } finally {
                is.close();
            }

            return sb.toString();

        } else {
            return "";
        }
    }
}