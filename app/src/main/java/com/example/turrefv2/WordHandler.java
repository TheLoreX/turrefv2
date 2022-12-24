package com.example.turrefv2;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class WordHandler {

    public void LineCounter(String path) {

        MainActivity.countOfLine = 0;
        try {
        String readLine;
        File readFile = new File(Environment.getExternalStorageDirectory(), path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(readFile), "UTF-8"));
        while ((readLine = reader.readLine()) != null && readLine.length() > 0) {
            MainActivity.countOfLine++;
        }
        reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
