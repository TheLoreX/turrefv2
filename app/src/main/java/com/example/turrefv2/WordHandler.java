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

        try {

        String readLine;
        File readFile = new File(Environment.getExternalStorageDirectory(), path);
        Log.d("TTST/1", String.valueOf(readFile));
        FileInputStream InputReader = new FileInputStream(readFile);
        Log.d("TTST/2", String.valueOf(readFile));
        BufferedReader reader = new BufferedReader(new InputStreamReader(InputReader, "UTF-8"));
        Log.d("TTST/3", String.valueOf(reader));
        while ((readLine= reader.readLine()) != null) {
            MainActivity.countOfLine++;
        }
        InputReader.close();
        reader.close();
        Log.d("TTST/R", String.valueOf(MainActivity.countOfLine));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
